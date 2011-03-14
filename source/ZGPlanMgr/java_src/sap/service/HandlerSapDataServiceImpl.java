package sap.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.base.dao.BaseDao;
import javacommon.util.StringHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import sap.SapBusiService;
import sap.model.SapResult;
import sun.util.logging.resources.logging;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTorderPlanbomDao;
import com.boco.zg.plan.base.model.ZgTback;
import com.boco.zg.plan.base.model.ZgTbackBom;
import com.boco.zg.plan.base.model.ZgTbackBomTemp;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTbackBo;
import com.boco.zg.plan.base.service.ZgTbackBomBo;
import com.boco.zg.plan.base.service.ZgTbackBomTempBo;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;
import com.sap.conn.jco.JCoTable;

@Component
public class HandlerSapDataServiceImpl implements HandlerSapDataService {

	private BaseDao baseDao;
	
	private static final Log log=LogFactory.getLog(HandlerSapDataService.class);

	private String NOTINSTR="CUID,SORTF,BATCH_NO,OPERATE_TYPE,PLANT,STORAGE_NUM,STORAGE_STATE";
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	private ZgTorderPlanbomDao getZgTorderPlanbomDao(){
		return (ZgTorderPlanbomDao) ApplicationContextHolder
		.getBean("zgTorderPlanbomDao");
	}
	
	private ZgTbackBomTempBo getZgTbackBomTempBo(){
		return (ZgTbackBomTempBo) ApplicationContextHolder
		.getBean("zgTbackBomTempBo");
	}
	
	private ZgTbackBomBo getZgTbackBomBo(){
		return (ZgTbackBomBo) ApplicationContextHolder
		.getBean("zgTbackBomBo");
	}
	
	
	private ZgTorderExBo getZgTorderExBo(){
		return (ZgTorderExBo) ApplicationContextHolder
		.getBean("zgTorderExBo");
	}
	
	private SapBusiService getSapBusiService(){
		return (SapBusiService) ApplicationContextHolder
		.getBean("sapBusiService");
	}
	
	
	
	/**
	 * add by wengq  202-06-23  重复推送 就是第一次推送有订单1，第二次推送也有订单1
	 *                        只插入不是重复推送的数据
	 * @param aufnrs 重复推送的订单号 格式：'','',''...
	 */
	public void addSapBomsData(int batchNo,Integer operateType,String aufnrs) {
		String updateOrderIdSql = "update Zg_t_Orderbom_Temp_All t set t.order_id = (select cuid from ZG_T_ORDER o where o.aufnr = t.aufnr  and o.arbpl=t.arbpl and rownum=1) WHERE BATCH_NO = "+batchNo;
		this.baseDao.executeSql(updateOrderIdSql);
		StringBuffer insertBuffer = new StringBuffer();
		insertBuffer.append("INSERT INTO zg_t_orderbom(CUID,ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr)" +
				" select sys_guid(),ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr from (SELECT distinct ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr FROM Zg_t_Orderbom_Temp_All a WHERE BATCH_NO = "+batchNo);
		if(aufnrs!=null&&aufnrs.length()>0){
			insertBuffer.append("  and a.aufnr||'-'||a.arbpl not in ("+aufnrs+")");
		}
		if(operateType!=null){
			insertBuffer.append(" and operate_type="+operateType);
		}
		insertBuffer.append(" and exists(select 1 from ZG_T_ORDER_temp e where e.aufnr =a.aufnr and batch_no="+batchNo+"))");
		this.baseDao.executeSql(insertBuffer.toString());
	}

	
	/**
	 * modify by wengq 增加：排序数据 重复推送 就是第一次推送有订单1，第二次推送也有订单1
	 *                     只插入不是重复推送的数据
	 * modify by wengq:遍历些次订单并插入，如果aufnr ,和arbpl相同，plant不同，则只更新plant,不做插入处理
	 *@params aufnrs 重复推送的订单号 格式: '','',''...
	 */
	public void addSapOrderData(int batchNo,Integer operateType,String aufnrs,String px) {
		StringBuffer insertBuffer = new StringBuffer();
		
		StringBuffer selectBuffer=new StringBuffer();
		selectBuffer.append(" select  distinct t.aufnr, t.arbpl,t.plant,t.pxdat from zg_t_order_temp t  where batch_no = "+batchNo);
		
		if(aufnrs!=null&&aufnrs.length()>0){
			selectBuffer.append(" and t.aufnr not in ("+aufnrs+")");
		}
	
		
		if(operateType!=null){
			selectBuffer.append(" and operate_type="+operateType);

		}
		
		//遍历此次要插入的订单
		List<Map> list=this.baseDao.findDynQuery(selectBuffer.toString());
		
		int count =0;
		for(Map order:list){
			count++;
			String aufnr=IbatisDAOHelper.getStringValue(order, "AUFNR", "");
			String arbpl=IbatisDAOHelper.getStringValue(order, "ARBPL", "");

			String sql =" select t.plant,cuid from zg_t_order t where t.aufnr='"+aufnr+"'";
			List<Map> tempList=this.baseDao.findDynQuery(sql);
			Date pxDate=(Date) order.get("PXDAT");
			String newPlant=IbatisDAOHelper.getStringValue(order, "PLANT","");
			if(tempList!=null&&tempList.size()>0){
				aufnrs=aufnr+","+aufnr+"-"+arbpl;
				
			}else {//不存在的
			 	insertBuffer = new StringBuffer();
			 	String orderId=(String) this.baseDao.getSqlMapClientTemplate().queryForObject("Base.selectGUID");
			 	
			
				
				if(StringHelper.isEmpty(newPlant)){
					insertBuffer.append("insert into ZG_T_ORDER(CUID,AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH)" +
							"select '"+orderId+"',AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,'"+px+"' as PFLAG,LABEL_CN,PSBH from ZG_T_ORDER_temp temp where  batch_no ="+batchNo
							+" and aufnr='"+aufnr+"'  and rownum=1 ");
				}else {
					insertBuffer.append("insert into ZG_T_ORDER(CUID,AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH)" +
							"select '"+orderId+"',AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,'"+px+"' as PFLAG,LABEL_CN,PSBH from ZG_T_ORDER_temp temp where  batch_no ="+batchNo
							+" and aufnr='"+aufnr+"'  and temp.plant='"+newPlant+"' and rownum=1 ");
				}
				
				if(operateType!=null){
					insertBuffer.append(" and operate_type="+operateType);
				}
				this.baseDao.executeSql(insertBuffer.toString());
			}
		}
		
		
	
	}

	public void updateRelation(int batchNo) {
		String sql = "select e.matnr,e.idnrk,e.aufnr,e.MENGE,e.ZDTYL,e.MATKL,e.posnr,e.arbpl from zg_t_orderbom_temp e WHERE BATCH_NO="+batchNo;
		List<Map> list = this.baseDao.queryBySql(sql);
		for(Map map:list){
			String matnr = (String)map.get("MATNR");
			String idnrk = (String)map.get("IDNRK");
			String aufnr = (String)map.get("AUFNR");
			BigDecimal menge = (BigDecimal)map.get("MENGE");
			BigDecimal zdtyl = (BigDecimal)map.get("ZDTYL");
			String matkl = (String)map.get("MATKL");
			String posnr=IbatisDAOHelper.getStringValue(map, "POSNR", "");
			String arbpl=IbatisDAOHelper.getStringValue(map, "ARBPL", "");
			String updateSql = "update zg_t_orderbom_temp_all t set t.matnr1 = '"+matnr+"',MENGE="+menge+",ZDTYL="+zdtyl+",MATKL='"+matkl+
			"' where  BATCH_NO="+batchNo+" and  t.idnrk = '"+idnrk+"' and t.aufnr = '"+aufnr+"' and trim(t.posnr)=trim('"+posnr+"') and t.arbpl='"+arbpl+"'";
			this.baseDao.executeSql(updateSql);
		}
	}

	public void insertDynamicTable(String tableName, Map map) {
		baseDao.insertDynamicTable(tableName, map);
	}

	public void deletePcOrderBoms(int batchNo) {
		String sql = "DELETE FROM ZG_T_ORDERBOM t where EXISTS(SELECT 1 FROM ZG_T_ORDER O WHERE O.CUID=T.ORDER_ID AND O.order_state = '-1' and exists "+
		"(select 1 from ZG_T_ORDER_temp e where e.aufnr=t.aufnr and  e.batch_no ="+batchNo+"))";
		this.baseDao.executeSql(sql);
	}

	public void deletePcOrders(int batchNo) {
		String sql = "DELETE FROM ZG_T_ORDER t where t.order_state = '-1' and exists "+
		"(select 1 from ZG_T_ORDER_temp e where e.aufnr=t.aufnr and  e.batch_no ="+batchNo+")";
		this.baseDao.executeSql(sql);
	}
	
	public void doUpdateChange(int batchNo, String aufnr,String arbpl) {
		StringBuffer querySql=new StringBuffer();
		querySql=new StringBuffer();
		querySql.append("select t.* ");
		querySql.append("  from zg_t_orderbom_temp_all t                                           ");
		querySql.append(" where t.batch_no = "+batchNo+"                                           ");
		querySql.append("  and t.aufnr = '"+aufnr+"'                                              ");
		querySql.append("  and t.arbpl = '"+arbpl+"'                                              ");
		querySql.append("   and t.operate_type  is not  null                                       ");
		List bomList = this.baseDao.findDynQuery(querySql.toString());
		
		StringBuffer findTempAufnrSql = new StringBuffer();
		//新增情况不用做任何业务判断，直接将数据插入bom表
		this.addSapBomsData(batchNo,1,aufnr,arbpl);
		//查询调整和作废的bom
		findTempAufnrSql.append("select t.* ");
		findTempAufnrSql.append("  from zg_t_orderbom_temp_all t                                           ");
		findTempAufnrSql.append(" where t.batch_no = "+batchNo+"                                           ");
		findTempAufnrSql.append("  and t.aufnr = '"+aufnr+"'                                              ");
		findTempAufnrSql.append("  and t.arbpl = '"+arbpl+"'                                              ");
		findTempAufnrSql.append("   and t.operate_type <> 1                                                ");
		List<Map> list = this.baseDao.findDynQuery(findTempAufnrSql.toString());
		List<Map> updateList = new ArrayList<Map>();
		List<Map> backList = new ArrayList<Map>();
		for(Map map : list) {
			Long operateType = IbatisDAOHelper.getLongValue(map, "OPERATE_TYPE")==null?-1l:IbatisDAOHelper.getLongValue(map, "OPERATE_TYPE");
			Long menge = IbatisDAOHelper.getLongValue(map, "MENGE")==null?0l:IbatisDAOHelper.getLongValue(map, "MENGE");
			String matnr = IbatisDAOHelper.getStringValue(map, "MATNR","");
			String matnr1 = IbatisDAOHelper.getStringValue(map, "MATNR1","");
			String idnrk = IbatisDAOHelper.getStringValue(map, "IDNRK","");
			String posnr=IbatisDAOHelper.getStringValue(map, "POSNR", "");
			
			if(operateType.intValue() == 2) {//调整
				Map planBom = getPlanBom(aufnr,matnr,matnr1,idnrk,posnr,arbpl);
				if(planBom == null) { //还未生成领料计划，直接更新数据
					this.updateBomMenge(map,planBom);
				}else{
					Long completeNum = IbatisDAOHelper.getLongValue(planBom, "COMPLETE_NUM");
					if(completeNum == null || completeNum.intValue() == 0) {//还未领取，直接更新数据
						this.updateBomMenge(map,planBom);
					}else if(menge >= completeNum){//新的需求数目大于完成数目，直接更新数据
						this.updateBomMenge(map,planBom);
					}else if(menge < completeNum){//新的需求数目小于完成数目，需要生成退货单（同时更新数据）
						this.updateBomMenge(map,planBom);
//						this.createBackPlan(map);
					}
				}
			}else if(operateType.intValue() == 3) {//作废，生成退货单（同时更新数据）
				Map planBom = getPlanBom(aufnr,matnr,matnr1,idnrk,posnr,arbpl);
				if(null!=planBom){
					String planNum=IbatisDAOHelper.getStringValue(planBom, "PLAN_NUM");
					if("0".equals(planNum)) {
						this.deleteBom(map);
						this.deletePlanBom(map);
					}else {
						this.updateBomMenge(map,planBom);
//						this.createBackPlan(map);
					}
				}else {
					this.deleteBom(map);
				}
				
			}
		}
		
	
		
		if(bomList.size()>0){//bom有变化 ，重新计算领料进度
			//重新计算领料进度
			List<Map> orderList= this.baseDao.findDynQuery("select t.*  from zg_t_order t where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"'");
			if(orderList.size()>0){
				for(Map map:orderList){
					String orderId=IbatisDAOHelper.getStringValue(map, "CUID","");
					getZgTorderbomExBo().doZgtorderProcess(orderId);
				}
				
			}
		}
		
		
		
	}
	
	/**
	 * @return
	 */
	private ZgTorderbomExBo getZgTorderbomExBo() {
		return (ZgTorderbomExBo) ApplicationContextHolder
				.getBean("zgTorderbomExBo");
	}

	/**
	 * 删除领料计划bom
	 * @param map
	 */
	private void deletePlanBom(Map map) {
		String orderBomId=map.get("CUID").toString();
		StringBuffer deleteBuffer=new StringBuffer();
		deleteBuffer.append("delete  from zg_t_order_planbom p where p.order_bom_id='"+orderBomId+"'");
		this.baseDao.executeSql(deleteBuffer.toString());
	}

	/**
	 * 更新bom，但是不做订单调整的相关处理，用于重复推送订但是不是调单调整的时候处理
	 * @param batchNo
	 * @param aufnr
	 */
	public void doUpdateChangeForPxRepeat(int batchNo, String aufnr,String arbpl) {
		
		StringBuffer findTempAufnrSql = new StringBuffer();
		//新增情况不用做任何业务判断，直接将数据插入bom表
		this.addSapBomsData(batchNo,1,aufnr,arbpl);
		//查询调整和作废的bom
		findTempAufnrSql.append("select t.* ");
		findTempAufnrSql.append("  from zg_t_orderbom_temp_all t                                           ");
		findTempAufnrSql.append(" where t.batch_no = "+batchNo+"                                           ");
		findTempAufnrSql.append("   and t.aufnr = '"+aufnr+"'                                              ");
		findTempAufnrSql.append(" and t.arbpl = '"+arbpl+"'                                              ");
		findTempAufnrSql.append("   and t.operate_type <> 1                                                ");
		List<Map> list = this.baseDao.findDynQuery(findTempAufnrSql.toString());
		for(Map map : list) {
			Long operateType = IbatisDAOHelper.getLongValue(map, "OPERATE_TYPE");
			if(operateType.intValue() == 2) {//调整
				this.updateBomMenge(map,new HashMap());
			}else if(operateType.intValue() == 3) {//作废
					this.deleteBom(map);
			}
		}
	}
	
	private Map getPlanBom(String aufnr, String matnr,String matnr1,String idnrk,String posnr,String arbpl) {
		StringBuffer findPlanBomSql = new StringBuffer();
		findPlanBomSql.append("select pb.cuid,ob.cuid as order_bom_id,ob.order_id,pb.car_num,nvl(pb.plan_num,0) plan_num,pb.complete_num  ");
		findPlanBomSql.append("  from zg_t_orderbom ob, zg_t_order_planbom pb  ,zg_t_order_plan p                                  ");
		findPlanBomSql.append(" where ob.cuid = pb.order_bom_id                                                   ");
		findPlanBomSql.append("   and p.cuid=pb.order_plan_id                                                     ");
		findPlanBomSql.append("    and p.plan_type in ('ABE','ABC','ABD')                                         ");
		findPlanBomSql.append("   and ob.aufnr = '"+aufnr+"'                                                      ");
		findPlanBomSql.append("   and ob.idnrk = '"+idnrk+"'                                                      ");
		findPlanBomSql.append("   and ob.posnr = '"+posnr+"'                                                      ");
		findPlanBomSql.append("   and ob.arbpl = '"+arbpl+"'                                                      ");
		
		List<Map> list = this.baseDao.findDynQuery(findPlanBomSql.toString());
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	private void deleteBom(Map map) {
		Long menge = IbatisDAOHelper.getLongValue(map, "MENGE");
		String aufnr = IbatisDAOHelper.getStringValue(map, "AUFNR");
		String matnr = IbatisDAOHelper.getStringValue(map, "MATNR");
		String matnr1 = IbatisDAOHelper.getStringValue(map, "MATNR1");
		String idnrk = IbatisDAOHelper.getStringValue(map, "IDNRK");
		String posnr = IbatisDAOHelper.getStringValue(map, "POSNR","");
		String arbpl=IbatisDAOHelper.getStringValue(map, "ARBPL", "");
		//删除订单BOM表需求数量
		StringBuffer updateBomMengeSql = new StringBuffer();
		updateBomMengeSql.append("delete from zg_t_orderbom   ");
		updateBomMengeSql.append(" where aufnr = '"+aufnr+"'  ");
		updateBomMengeSql.append("   and matnr = '"+matnr+"'  ");
		updateBomMengeSql.append("   and matnr1 = '"+matnr1+"'");
		updateBomMengeSql.append("   and idnrk = '"+idnrk+"'  ");
		updateBomMengeSql.append("   and ARBPL = '"+arbpl+"'  ");
		if("".equals(posnr)){
			updateBomMengeSql.append("   and posnr is null    ");
		}else{
			updateBomMengeSql.append("   and posnr = '"+posnr+"'  ");
		}
		this.baseDao.executeSql(updateBomMengeSql.toString());
	}
	
	/**
	 * 更新orderplanbom,除了排序号，
	 * @param map
	 */
	public void updateBomMenge(Map map,Map planBom) {
		Long zdtyl=IbatisDAOHelper.getLongValue(map, "ZDTYL");
		String matkl = IbatisDAOHelper.getStringValue(map, "MATKL","");
		Long menge = IbatisDAOHelper.getLongValue(map, "MENGE");
		String aufnr = IbatisDAOHelper.getStringValue(map, "AUFNR");
		String matnr = IbatisDAOHelper.getStringValue(map, "MATNR");
		String matnr1 = IbatisDAOHelper.getStringValue(map, "MATNR1");
		String idnrk = IbatisDAOHelper.getStringValue(map, "IDNRK");
		String posnr = IbatisDAOHelper.getStringValue(map, "POSNR");
		String arbpl=IbatisDAOHelper.getStringValue(map, "ARBPL");
		
		StringBuffer updateBomMengeSql = new StringBuffer();
		updateBomMengeSql.append("update zg_t_orderbom ob set ");
		Set set = map.keySet();
		for (Object key : set) {
			if(!NOTINSTR.contains(key.toString())){
				String value=map.get(key)==null?"":map.get(key).toString();
				updateBomMengeSql.append( key+"='"+value+"',");
			}
			
		}
		String sql=updateBomMengeSql.toString();
		sql=sql.substring(0,sql.length()-1);
		updateBomMengeSql=new StringBuffer();
		updateBomMengeSql.append(sql);
		//修改订单BOM表
	
		updateBomMengeSql.append(" where aufnr = '"+aufnr+"'                      ");
		updateBomMengeSql.append("   and idnrk = '"+idnrk+"'                      ");
		updateBomMengeSql.append("   and posnr = '"+posnr+"'                      ");
		updateBomMengeSql.append("   and arbpl = '"+arbpl+"'                      ");
		this.baseDao.executeSql(updateBomMengeSql.toString());
//		Map planBom = getPlanBom(aufnr,matnr,matnr1,idnrk,posnr,arbpl);
		if(planBom != null) {
			Long completeNum = IbatisDAOHelper.getLongValue(planBom, "COMPLETE_NUM");
			String orderBomId = IbatisDAOHelper.getStringValue(planBom, "ORDER_BOM_ID");
			String planBomId=IbatisDAOHelper.getStringValue(planBom, "CUID");
			if(!StringUtils.isBlank(orderBomId)) {
				StringBuffer updatePlanBomSql = new StringBuffer();
				if(menge <= completeNum){//已领料数量大于需求数量，生产待退料数量
					updatePlanBomSql.append("update zg_t_order_planbom t set state='9', t.car_num = '"+menge+"',t.WAIT_BACK_NUM= "+Math.abs(menge-completeNum));
					updatePlanBomSql.append(" where cuid = '"+planBomId+"'              ");
				}else {
					updatePlanBomSql.append("update zg_t_order_planbom set state='0',car_num = '"+menge+"' ");
					updatePlanBomSql.append(" where cuid = '"+planBomId+"'              ");
				}
				
				this.baseDao.executeSql(updatePlanBomSql.toString());
			}
		}
	}
	
	private ZgTbackBo zgTbackBo;
	
	public void setZgTbackBo(ZgTbackBo zgTbackBo) {
		this.zgTbackBo = zgTbackBo;
	}
	
	private ZgTbackBomBo zgTbackBomBo;

	public void setZgTbackBomBo(ZgTbackBomBo zgTbackBomBo) {
		this.zgTbackBomBo = zgTbackBomBo;
	}

	private void createBackPlan(Map map) {
		Long batchNo = IbatisDAOHelper.getLongValue(map, "BATCH_NO");
		Long menge = IbatisDAOHelper.getLongValue(map, "MENGE");
		String aufnr = IbatisDAOHelper.getStringValue(map, "AUFNR");
		String matnr = IbatisDAOHelper.getStringValue(map, "MATNR");
		String matnr1 = IbatisDAOHelper.getStringValue(map, "MATNR1");
		String idnrk = IbatisDAOHelper.getStringValue(map, "IDNRK");
		String posnr = IbatisDAOHelper.getStringValue(map, "POSNR");
		String arbpl = IbatisDAOHelper.getStringValue(map, "ARBPL");
		//创建退料计划
		Date date = new Date();
		ZgTback zgTback = new ZgTback();
		zgTback.setAufnr(aufnr);
		zgTback.setBatchNo(batchNo);
		zgTback.setBatchDate(date);
		zgTback.setCreateTime(date);
		zgTback.setUpdateTime(date);
		String backCuid = "";
		Map planBom = getPlanBom(aufnr,matnr,matnr1,idnrk,posnr,arbpl);
		if(planBom != null) {
			String orderId = IbatisDAOHelper.getStringValue(planBom, "ORDER_ID");
			String orderBomId = IbatisDAOHelper.getStringValue(planBom, "ORDER_BOM_ID");
			Long completeNum = IbatisDAOHelper.getLongValue(planBom, "COMPLETE_NUM");
			if(completeNum == null) completeNum = 0l;
			if(menge>=completeNum){
				return;
			}else {
				zgTbackBo.save(zgTback);
				backCuid = zgTback.getCuid();
				if(backCuid == null) throw new RuntimeException("创建退料计划失败！");
				//创建退料明细
				ZgTbackBom zgTbackBom = new ZgTbackBom();
//				zgTbackBom.setBackCuid(backCuid);
//				zgTbackBom.setOrderId(orderId);
//				zgTbackBom.setOrderBomId(orderBomId);
//				zgTbackBom.setIdnrk(idnrk);
//				zgTbackBom.setMatkl(IbatisDAOHelper.getStringValue(map, "MATKL"));
//				zgTbackBom.setMatnr(matnr);
//				zgTbackBom.setMaktx1(IbatisDAOHelper.getStringValue(map, "MAKTX1"));
//				
//				zgTbackBom.setBackNumber(Math.abs(menge-completeNum));
//				zgTbackBom.setCancelNumber(0l);
//				zgTbackBom.setCreateTime(date);
//				zgTbackBom.setUpdateTime(date);
				zgTbackBomBo.save(zgTbackBom);
			}
		
		}
	}
	
	/**
	 * 批量领料计划bom接口bom组件更新处理
	 *  临时表中状态为2:先把原来的bom删除，再插入，状态为1的直接插入
	 * @param batchNo
	 */
	public void handleBatchBomByBo(int batchNo){
		StringBuffer delBuffer=new StringBuffer();
		delBuffer.append(" delete from zg_t_bom  t where exists (select 1 from zg_t_bom_temp temp ");
		delBuffer.append(" where temp.batch_no="+batchNo);
		delBuffer.append(" and temp.operate_type=2 ");
		delBuffer.append(" and temp.idnrk=t.idnrk"  );
		delBuffer.append(" and temp.lifnr=t.plantid) ");
		this.baseDao.executeSql(delBuffer.toString());
		
		StringBuffer insertBuffer=new StringBuffer();
		insertBuffer.append("insert into zg_t_bom(cuid,idnrk,maktx,msehl,matkl,zbz,zrzqd,lgort,plant,Plantid,TYPE) ");
		insertBuffer.append(" select sys_guid(),temp.idnrk,temp.maktx2,temp.msehl2,temp.matkl,temp.zbz,temp.zrzqd,temp.lgort,Temp.name1 ,temp.lifnr,'2' ");
		insertBuffer.append("from zg_t_bom_temp temp where temp.batch_no="+batchNo);
		this.baseDao.executeSql(insertBuffer.toString());


	}
	

	/**
	 * 更新订单信息
	 * @param aufnr 
	 * @param arbpl
	 * @param batchNo
	 * @param isPx 是否排序状态 ，如果是排序状态，则更新pflag为“X”
	 * @param plant工厂字段
	 */
	public void doUpdateOrder(String aufnr,String arbpl, int batchNo,String px,String plant) {
		StringBuffer updateOrderSql = new StringBuffer();
		updateOrderSql.append("update zg_t_order t");
		updateOrderSql.append(" set (SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,psbh) =");
		updateOrderSql.append("(Select SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,'"+plant+"',MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,'"+px +"' as PFLAG,LABEL_CN,psbh  from zg_t_order_temp temp ");
		updateOrderSql.append(" where temp.aufnr='"+aufnr+"' and temp.arbpl='"+arbpl+"' and temp.batch_no="+batchNo+" and     rownum=1)");
		updateOrderSql.append(" where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"'");
		this.baseDao.executeSql(updateOrderSql.toString());
		
	}
	
	public static void main(String[] args) {
		new HandlerSapDataServiceImpl().addSapBomsData(12, 1,"aufnr","arbpl");
	}
	
	/**
	 * 添加bom，如果isAddOrder该bom的订单处于排序状态，则同时把该bom插入到orderplanbom表中
	 */
	public void addSapBomsData(int batchNo,Integer operateType,String aufnr,String arbpl) {
		String updateOrderIdSql = "update Zg_t_Orderbom_Temp_All t set t.order_id = (select cuid from ZG_T_ORDER o where o.aufnr = t.aufnr and t.arbpl=o.arbpl and rownum=1) WHERE  aufnr='"+aufnr+"' and arbpl='"+arbpl+"' and BATCH_NO = "+batchNo;
		this.baseDao.executeSql(updateOrderIdSql);
		StringBuffer insertBuffer = new StringBuffer();
		StringBuffer sqlBuffer=new StringBuffer();
		insertBuffer.append("INSERT INTO zg_t_orderbom(CUID,ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr)" +
				"SELECT SYS_GUID(),ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr FROM Zg_t_Orderbom_Temp_All a WHERE BATCH_NO = "+batchNo+"   and a.aufnr='"+aufnr+"'     and a.arbpl='"+arbpl+"'");
		
		sqlBuffer.append("select temp.menge, b.cuid orderBomId,  t.cuid orderId,   temp.sortf from Zg_t_Orderbom_Temp_All temp,    zg_t_order  t,      zg_t_orderbom      b ");
		sqlBuffer.append("   where temp.order_id = t.cuid   and t.pflag = 'X'     and b.order_id = t.cuid   and b.idnrk = temp.idnrk   and b.arbpl = t.arbpl   and b.posnr = temp.posnr   ");
		sqlBuffer.append("  and temp.batch_no = "+batchNo+"    and temp.aufnr='"+aufnr+"'   and temp.arbpl='"+arbpl+"'");
		
		if(operateType!=null){
			insertBuffer.append(" and operate_type="+operateType);
			sqlBuffer.append(" and temp.operate_type="+operateType);
		}
		
		//插入orderbom表
		this.baseDao.executeSql(insertBuffer.toString());
		
		//插入orderplanbom表
		List<Map> list= this.baseDao.queryBySql(sqlBuffer.toString());
		
		Map planMap=new HashMap();
		if(list.size()>0){
			Map map=list.get(0);
			String orderId=IbatisDAOHelper.getStringValue(map, "ORDERID");
			List<Map> orderPlanList=this.baseDao.queryBySql("select * from zg_t_order_plan t where t.order_id='"+orderId+"'");
			for(Map obj:orderPlanList){
				planMap.put(IbatisDAOHelper.getStringValue(obj, "PLAN_TYPE"), IbatisDAOHelper.getStringValue(obj, "CUID"));
			}
			
		}
		
		for(Map map:list){
		
			String orderId=IbatisDAOHelper.getStringValue(map, "ORDERID");
			String orderBomId=IbatisDAOHelper.getStringValue(map, "ORDERBOMID");
			String sortf=IbatisDAOHelper.getStringValue(map, "SORTF","");
			Long menge=IbatisDAOHelper.getLongValue(map, "MENGE");
			
			String orderPlanId=IbatisDAOHelper.getStringValue(planMap, sortf,"");
			if(StringHelper.isEmpty(orderPlanId)){
				continue;
			}
			
			ZgTorderPlanbom planbom = new ZgTorderPlanbom();
			planbom.setOrderPlanId(orderPlanId);
			planbom.setOrderId(orderId);
			planbom.setOrderBomId(orderBomId);
			planbom.setState(Constants.CarPlanStatus.NEW.value());
			planbom.setCarNum(menge);
			getZgTorderPlanbomDao().save(planbom);
		}
		
	}

	public void synBomMateriel() {
		//这个是插入ZG_MATERIEL表的，这里插入了仓库
		StringBuffer sql=new StringBuffer();
		sql.append("insert into ZG_MATERIEL                                 ");
		sql.append("  (cuid, ID, PARENT_ID, MATERIEL_NAME, Type,lgort)            ");
		sql.append("  select sys_guid(), temp.lgort, '', temp.label_cn, '1', temp.lgort ");
		sql.append("    from (select distinct t.lgort, fo.label_cn          ");
		sql.append("            from zg_t_orderbom t, FW_ORGANIZATION fo    ");
		sql.append("           where t.lgort = fo.cuid(+)                   ");
		sql.append("             and t.lgort is not null                    ");
        sql.append("             and (t.lgort is not null or t.matkl is not null) ");
        sql.append("             and t.lgort not in                               ");
        sql.append("                 (select nvl(zm.id, 'null') from ZG_MATERIEL zm)) temp");
        this.baseDao.executeSql(sql.toString());
        
        //插入ZG_MATERIEL表的，这里插入了物料组
        sql=new StringBuffer();
        sql.append("insert into ZG_MATERIEL                                                  ");
        sql.append("  (cuid, ID, PARENT_ID, MATERIEL_NAME, Type, lgort)                      ");
        sql.append("  select sys_guid(),temp.matkl,                                          ");
        sql.append("         (select zm.cuid from ZG_MATERIEL zm where zm.id = temp.lgort),  ");
        sql.append("        temp.name, '2', temp.lgort                                      ");
        sql.append("    from (select t.matkl, t.lgort   ,t1.name                                     ");
        sql.append("            from zg_t_orderbom t  ,ZG_MATERIEL_TEMP T1                                       ");
        sql.append("          where  t.matkl=t1.id(+) and t.lgort is not null and t.matkl is not null        ");
        sql.append("             and t.matkl || '-' || t.lgort not in                        ");
        sql.append("                 (select zm.id || '-' ||zm.lgort                      ");
        sql.append("                    from ZG_MATERIEL zm)                                 ");
        sql.append("           group by t.lgort, t.matkl,t1.name) temp                       ");
        this.baseDao.executeSql(sql.toString());
        
        //同步zgtbom表
        sql=new StringBuffer();
        sql.append("insert into ZG_T_BOM                                                                     ");
        sql.append("  (CUID, IDNRK, MAKTX, MATKL, LGORT, TYPE, MSEHL)                                        ");
        sql.append("  select sys_guid(), temp.IDNRK,temp.MAKTX2,temp.MATKL, temp.LGORT, '1',   temp.MSEHL2   ");
		sql.append("    from (select distinct t.IDNRK,   t.MAKTX2, t.MATKL, t.LGORT,  t.MSEHL2               ");
		sql.append("            from zg_t_orderbom t                                                         ");
		sql.append("           where t.matkl is not null                                                     ");
		sql.append("           and t.lgort is not null and                                                   ");
		sql.append("          not exists (select 1 from zg_t_bom bom where bom.idnrk=t.idnrk                 ");
		sql.append("          and bom.matkl=t.matkl and bom.lgort=t.lgort )     ) temp                       ");
	    this.baseDao.executeSql(sql.toString());
	    
	    //删除重复记录
	    sql=new StringBuffer();
	    sql.append("   delete from zg_t_bom t where t.rowid in (                                             ");
	    sql.append(" select  max(t.rowid) from zg_t_bom t where t.type='1' group by t.matkl,t.lgort,t.idnrk having count(1)>1 )");  
	    this.baseDao.executeSql(sql.toString());
	    
	    //因为同步等原因可能会导致bom重复下载，这里主要是删除重复的bom
	    sql=new StringBuffer();
	    sql.append("   delete from zg_t_orderbom t where t.rowid in (                                         "); 
	    sql.append("  select max(t.rowid)                                                                      ");
	    sql.append(" from zg_t_orderbom t,zg_t_order_planbom bom                                               ");
	    sql.append("     where           bom.order_bom_id=t.cuid                                               ");
	    sql.append("	and (bom.plan_num is null and  bom.complete_num  is null)                              ");
	    sql.append("    group by t.idnrk, t.order_id, t.aufnr, t.arbpl, t.posnr   having count(1) > 1)         ");
	    this.baseDao.executeSql(sql.toString());
  
	    sql=new StringBuffer();
	    sql.append("   delete from zg_t_order_planbom bom                                                      ");
	    sql.append("    where not exists                                                                       ");
	    sql.append("    (select 1 from zg_t_orderbom t where t.cuid = bom.order_bom_id)                        ");
	    sql.append("  and  not exists (select 1 from zg_t_bom v where v.cuid=bom.bom_id)");
	    this.baseDao.executeSql(sql.toString());
	    
	    sql=new StringBuffer();
	    sql.append("delete  from zg_t_order_plan t where not exists (select 1 from zg_t_order_planbom bom where t.cuid=bom.order_plan_id)");
	    this.baseDao.executeSql(sql.toString());
	}

	public void saveZgTorderAide(String orderId, String plant, Date pxDate,String poskey) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zg_t_order_aide t where trim(t.order_id)=trim('"+orderId+"') and trim(t.plant)=trim('"+plant+"')");
		
		if(this.baseDao.queryBySql(sql.toString()).size()>0){
			updateZgTorderAide(orderId,plant,pxDate);
		}else {
			sql=new StringBuffer();
			sql.append("insert into zg_t_order_aide  (cuid, order_id, px_date, plant,poskey) values  ((select sys_guid() from dual), '"+orderId+"', to_date('"+pxDate+"','yyyy-MM-dd'), '"+plant+"','"+poskey+"')");
			this.baseDao.executeSql(sql.toString());
		}
	
	}

	public void updateZgTorderAide(String orderId, String plant, Date pxDate) {
		StringBuffer sql=new StringBuffer();
		sql.append("  update zg_t_order_aide t set t.px_date=to_date('"+pxDate+"','yyyy-MM-dd') where t.order_id='"+orderId+"' and t.plant='"+plant+"'");
		this.baseDao.executeSql(sql.toString());
	}

	/**
	 * rfid中存在并且接口过来的数据中不存在的订单列表
	 * @param batchNo
	 */
	public void deleteNotExistOrder(int batchNo) {
		StringBuffer sql=new StringBuffer();
		sql.append("select temp.pxdat from zg_t_order_temp temp where  temp.batch_no='"+batchNo+"' and rownum=1");
		List<Map> list=this.baseDao.queryBySql(sql.toString());
		
		if(list.size()>0){
			Date date=(Date) list.get(0).get("PXDAT");
			sql=new StringBuffer();
			sql.append(" select  t.*,aide.*,aide.plant delplant  from zg_t_order t, zg_t_order_aide aide             ");
			sql.append(" where t.cuid = aide.order_id                                  ");
			sql.append("   and to_char(aide.px_date,'yyyy-MM-dd') = '"+TimeFormatHelper.getFormatDate(date, TimeFormatHelper.DATE_FORMAT)+"'                   ");
			sql.append("   and not exists (select 1                                    ");
			sql.append("          from zg_t_order_temp temp                            ");
			sql.append("         where temp.batch_no = "+batchNo                        );
			sql.append("           and temp.poskey = aide.poskey)                      ");
			
			List<Map> notExistOrderList=this.baseDao.queryBySql(sql.toString());
			
			//删除不存在的订单
			deleteNotExistOrder(notExistOrderList);
			
			
		}
			
		
	}

	/**
	 * 删除不存在的订单
	 * @param notExistOrderList
	 */
	//TODO WENGQ(目前没有备料库表，所以直接物料删除，以后这一部分改成如果领了料的订单删除的话得存备料库表)
	private void deleteNotExistOrder(List<Map> notExistOrderList) {
		for(Map orderMap:notExistOrderList){
			String orderId=orderMap.get("ORDER_ID").toString();
			String plant=orderMap.get("DELPLANT").toString();
			String aufnr=orderMap.get("AUFNR").toString();
			
			deleteOrderPlanGroupByOrderId(orderId,plant);
			deleteOrderPlanBomByOrderId(orderId,plant);
			deleteOrderPlanByOrderId(orderId,plant);

//			deleteOrderBomByOrderId(orderId,plant);
			deleteOrderAideByOrderId(orderId,plant);
			deleteOrderByOrderIdPlant(orderId,plant);
		}
	}
	

	/**
	 * 根据订单编号删除领料计划分组表
	 * @param orderId
	 */
	private void deleteOrderPlanGroupByOrderId(String orderId,String plant){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from zg_t_order_plan_group t where exists(                      ");
		sql.append("       select 1 from zg_t_order_plan plan ,zg_t_group_order_plan gop    ");
		sql.append("       where plan.cuid=gop.order_plan_id                                ");
		sql.append("       and gop.group_id=t.cuid                                          ");
		sql.append("       and plan.order_id='"+orderId+"'   and plan.plant='"+plant+"'    )");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 根据订单编号删除领料计划
	 * @param orderId
	 */
	private void deleteOrderPlanByOrderId(String orderId,String plant){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from zg_t_order_plan plan where plan.order_id='"+orderId+"'");
		if(!StringHelper.isEmpty(plant)){
			sql.append(" and plan.plant='"+plant+"'")	;
		}
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 根据订单编号删除领料计划
	 * @param orderId
	 */
	private void deleteOrderPlanBomByOrderId(String orderId,String plant){
		StringBuffer sql=new StringBuffer();
		if(!StringHelper.isEmpty(plant)){
			sql.append("delete from zg_t_order_planbom t where  exists(select 1 FROM Zg_t_Order_Plan plan where plan.order_id='"+orderId+"' and plan.plant='"+plant+"' and plan.cuid=t.order_plan_id)");

		}else {
			sql.append("delete from zg_t_order_planbom t where  t.order_id='"+orderId+"'");
		}
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 根据订单编号删除订单BOM
	 * @param orderId
	 */
	private void deleteOrderBomByOrderId(String orderId){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from zg_t_orderbom t where t.order_id='"+orderId+"'");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 根据订单编号删除订单辅表
	 * @param orderId
	 */
	private void deleteOrderAideByOrderId(String orderId,String plant){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from zg_t_order_aide aide where aide.order_id='"+orderId+"' and aide.plant='"+plant+"'");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 根据订单编号删除订单表
	 * @param orderId
	 */
	private void deleteOrderByOrderId(String orderId){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from zg_t_order t where t.cuid='"+orderId+"'");
		this.baseDao.executeSql(sql.toString());
	}
	

	/**
	 * 根据订单编号删除订单表排序数据
	 * @param orderId
	 */
	private void deleteOrderByOrderIdPlant(String orderId,String plant){
		StringBuffer sql=new StringBuffer();
		sql.append("select t.plant from zg_t_order t where t.cuid='"+orderId+"'");
		List<Map> list=this.baseDao.findDynQuery(sql.toString());
		if(list.size()>0){
			String allPlant=list.get(0).get("PLANT")==null?"":list.get(0).get("PLANT").toString();
			if(!StringHelper.isEmpty(allPlant)){
				if(allPlant.startsWith(plant)){
					allPlant=allPlant.replace(plant+",", "");
				}else {
					allPlant=allPlant.replace(","+plant	,"");
				}
				sql=new StringBuffer();
				sql.append("update zg_t_order t set t.plant='"+allPlant+"' where t.cuid='"+orderId+"'");
				this.baseDao.executeSql(sql.toString());
			}
			
		}
	}

	/**
	 * 判断订单是否是新增订单
	 * @param posKey
	 * @param aufnr
	 * @return  －1　不是新增　0　是新增　1　之前的数据是排产数据 2生产厂新增（生产厂为新增，订单不是新增）
	 */
	public int isAddOrder(String posKey, String aufnr,String arbpl) {
		StringBuffer sql=new StringBuffer();
		sql.append("select  * from zg_t_order_aide t where t.poskey='"+posKey+"'");
		if(this.baseDao.queryBySql(sql.toString()).size()>0){
			return -1;
		}else {//
			
			sql=new StringBuffer();
			sql.append("select * from zg_t_order  t where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"' and t.pflag ='X' ");
			if(this.baseDao.queryBySql(sql.toString()).size()>0){//2生产厂新增（生产厂为新增，订单不是新增）
				return 2;
			}
			
			

			sql=new StringBuffer();
			sql.append("select * from zg_t_order  t where t.aufnr='"+aufnr+"' and t.pflag ='X' ");
			if(this.baseDao.queryBySql(sql.toString()).size()>0){//3 订单编码已经存在　，但是生产线不存在，属于拆分生产线
				return 3;
			}
			
			
			
			sql=new StringBuffer();
			sql.append("select * from ZG_T_ORDER T WHERE T.AUFNR ='"+aufnr+"' AND T.PFLAG IS  null ");
			if(this.baseDao.queryBySql(sql.toString()).size()>0){//1　之前的数据是排产数据
				return 1;
			}
			
		}
		return 0;
	}

	/**
	 * 判断需求的排序数量是否发生变化
	 * @param posKey
	 * @param psmng 订单项数量
	 * @param pmenge　排序数量
	 * @return
	 */
	public boolean isPsmngNumChanged(String posKey, String psmng, String pmenge) {
		StringBuffer sql=new StringBuffer();
		sql.append("select *  from zg_t_order_aide aide, zg_t_order t    ");
		sql.append(" where aide.poskey = '"+posKey+"'                    ");
		sql.append("   and aide.order_id = t.cuid                        ");
		sql.append("   and t.psmng = '"+psmng+"'                         ");
		sql.append("   and t.pmenge = '"+pmenge+"'                       "); 
		return this.baseDao.queryBySql(sql.toString()).size()==0;
	}

	/**
	 * 根据poskey更新order生产厂，plant字段更新的时候，如果原来的值为C03,后面的值为C02，则变成C03,C02,则不是直接覆盖更新
	 * @param posKey
	 * @param plant 新的生产厂
	 */
	public void doUpdateZgTorderPlantByPosKey(String posKey, String plant,String oldPlant) {
		StringBuffer sql=new StringBuffer();
		sql.append(" update zg_t_order t set t.plant =replace(t.plant,'"+oldPlant+"','"+plant+"') where                          ");
		sql.append("   exists (select 1 from zg_t_order_aide aide where aide.order_id=t.cuid   and aide.poskey='"+posKey+"')     ");
		this.baseDao.executeSql(sql.toString());
	}

	/**
	 * 更新订单辅表
	 * @param posKey
	 */
	public void doUpdateZgTorderAidePoskey(String posKey,int batchNo) {
		StringBuffer sql=new StringBuffer();
		sql.append("  update zg_t_order_aide t set (t.plant,t.px_date)= (select temp.plant,temp.pxdat from zg_t_order_temp temp where temp.poskey='"+posKey+"' and temp.batch_no='"+batchNo+"' and rownum=1) where t.poskey='"+posKey+"' ");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 根据poskey更新订单信息
	 * @param posKey
	 */
	public void doUpdateZGtorderByPosKey(String posKey,int batchNo){
		StringBuffer sql=new StringBuffer();
		sql.append("    update zg_t_order t set (SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,psbh)    ");
		sql.append("   =(select SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,psbh                      ");
		sql.append("   from zg_t_order_temp temp where temp.poskey='"+posKey+"' and temp.batch_no='"+batchNo+"' and rownum=1)	  where exists (select 1 from zg_t_order_aide aide where aide.order_id=t.cuid   and aide.poskey='"+posKey+"')                                                                              ");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 插入订单
	 * @param posKey
	 */
	public String saveZgTorder(String posKey,int batchNo){
		StringBuffer insertBuffer = new StringBuffer();
	 	String orderId=(String) this.baseDao.getSqlMapClientTemplate().queryForObject("Base.selectGUID");
		
		insertBuffer.append("insert into ZG_T_ORDER(CUID,AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH)" +
					"select '"+orderId+"',AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH from ZG_T_ORDER_temp temp where  temp.poskey ='"+posKey+"'" +
							"  and temp.batch_no='"+batchNo+"' and rownum=1");
		this.baseDao.executeSql(insertBuffer.toString());
		
		return orderId;
	}
	
	/**
	 * 根据poskey插入orderbom表　
	 * @param batchNo
	 * @param posKey
	 */
	public void addSapBomsDataByPosKey(int batchNo, String posKey,String orderId){
		StringBuffer insertBuffer = new StringBuffer();
		insertBuffer.append("   INSERT INTO zg_t_orderbom(CUID,ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr) ");
		insertBuffer.append("		select sys_guid(),ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,'"+orderId+"',AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr from      ");
		insertBuffer.append("    (SELECT distinct ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,a.AUFNR,a.ARBPL,a.MATNR,a.MAKTX1,a.MAKTX2,MSEHL1,MSEHL2,a.LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr   ");
		insertBuffer.append("    FROM Zg_t_Orderbom_Temp_All a,zg_t_order_temp temp WHERE temp.poskey='"+posKey+"' and a.batch_no='"+batchNo+"' and  temp.aufnr=a.aufnr and temp.arbpl=a.arbpl    and temp.batch_no ='"+batchNo+"')");
		this.baseDao.executeSql(insertBuffer.toString());
	}
	
	/**
	 * 判断需求的排序数量是否发生变化
	 * @param posKey
	 * @param psmng 订单项数量
	 * @param pmenge　排序数量
	 * @return
	 */
	public boolean isPsmngNumChangedByOrderId(String orderId, String psmng,String pmenge){
		StringBuffer sql=new StringBuffer();
		sql.append("select *  from zg_t_order t      ");
		sql.append("   where  t.cuid='"+orderId+"'   ");
		sql.append("   and t.psmng = '"+psmng+"'     ");
		sql.append("   and t.pmenge = '"+pmenge+"'   "); 
		return this.baseDao.queryBySql(sql.toString()).size()==0;
	}
	
	/**
	 * 根据orderId更新订单信息
	 * @param orderId
	 */
	public void doUpdateZGtorderByOrderId(String orderId,String posKey,int batchNo){
		StringBuffer sql=new StringBuffer();
		sql.append("    update zg_t_order t set (SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,psbh,plant)    ");
		sql.append("   =(select SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,psbh ,plant                     ");
		sql.append("   from zg_t_order_temp temp where temp.poskey='"+posKey+"' and temp.batch_no='"+batchNo+"' and rownum=1)	  where t.cuid='"+orderId+"'                                                                              ");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 更新orderplanbom,除了排序号，
	 * @param map
	 */
	public void updateBomMenge(Map map,String orderId) {
		Long zdtyl=IbatisDAOHelper.getLongValue(map, "ZDTYL");
		String matkl = IbatisDAOHelper.getStringValue(map, "MATKL","");
		Long menge = IbatisDAOHelper.getLongValue(map, "MENGE");
		String aufnr = IbatisDAOHelper.getStringValue(map, "AUFNR");
		String matnr = IbatisDAOHelper.getStringValue(map, "MATNR");
		String matnr1 = IbatisDAOHelper.getStringValue(map, "MATNR1");
		String idnrk = IbatisDAOHelper.getStringValue(map, "IDNRK");
		String posnr = IbatisDAOHelper.getStringValue(map, "POSNR");
		String arbpl=IbatisDAOHelper.getStringValue(map, "ARBPL");
		
		StringBuffer updateBomMengeSql = new StringBuffer();
		updateBomMengeSql.append("update zg_t_orderbom ob set ");
		Set set = map.keySet();
		for (Object key : set) {
			if(!NOTINSTR.contains(key.toString())){
				if(key.toString().equals("ORDER_ID")){
					updateBomMengeSql.append( key+"='"+orderId+"',");
				}else {
					String value=map.get(key)==null?"":map.get(key).toString();
					updateBomMengeSql.append( key+"='"+value+"',");
				}
			
			}
			
		}
		String sql=updateBomMengeSql.toString();
		sql=sql.substring(0,sql.length()-1);
		updateBomMengeSql=new StringBuffer();
		updateBomMengeSql.append(sql);
		//修改订单BOM表
	
		updateBomMengeSql.append(" where aufnr = '"+aufnr+"'                      ");
		updateBomMengeSql.append("   and matnr = '"+matnr+"'                      ");
		updateBomMengeSql.append("   and matnr1 = '"+matnr1+"'                    ");
		updateBomMengeSql.append("   and idnrk = '"+idnrk+"'                      ");
		updateBomMengeSql.append("   and posnr = '"+posnr+"'                      ");
		updateBomMengeSql.append("   and arbpl = '"+arbpl+"'                      ");
		this.baseDao.executeSql(updateBomMengeSql.toString());
		Map planBom = getPlanBom(aufnr,matnr,matnr1,idnrk,posnr,arbpl);
		if(planBom != null) {
			String orderBomId = IbatisDAOHelper.getStringValue(planBom, "ORDER_BOM_ID");
			if(!StringUtils.isBlank(orderBomId)) {
			StringBuffer updatePlanBomSql = new StringBuffer();
			updatePlanBomSql.append("update zg_t_order_planbom set car_num = '"+menge+"' ");
			updatePlanBomSql.append(" where order_bom_id = '"+orderBomId+"'              ");
				this.baseDao.executeSql(updatePlanBomSql.toString());
			}
		}
	}
	
	/**
	 * 更新领料计划生产厂
	 * @param orderId　订单编号
	 * @param plant　　　新的生产厂
	 * @param oldPlant　旧的生产厂
	 */
	public void doUpdateZgTorderPlanPlant(String orderId, String plant,	String oldPlant){
		StringBuffer sql=new StringBuffer();
		sql.append("  update zg_t_order_plan t set t.plant='"+plant+"' where t.order_id='"+orderId+"' and t.plant='"+oldPlant+"'");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 更新orderbom的生产线
	 * @param orderId 订单编号
	 * @param arbpl　　　新生产线
	 * @param oldArbpl　旧生产线
	 */
	public void doUpdateZgTorderBomArbpl(String orderId, String arbpl,String oldArbpl){
		StringBuffer sql=new StringBuffer();
		sql.append("  update zg_t_orderbom t set t.arbpl='"+arbpl+"' where t.order_id='"+orderId+"' and t.arbpl='"+oldArbpl+"'");
		this.baseDao.executeSql(sql.toString());
		
	}
	
	/**
	 * 插入订单
	 * @param posKey
	 */
	public String saveZgTorderByAufnr(String aufnr,int batchNo){
		StringBuffer insertBuffer = new StringBuffer();
	 	String orderId=(String) this.baseDao.getSqlMapClientTemplate().queryForObject("Base.selectGUID");
		
		insertBuffer.append("insert into ZG_T_ORDER(CUID,AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH)" +
					"select '"+orderId+"',AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH from ZG_T_ORDER_temp temp where  temp.aufnr ='"+aufnr+"'" +
							"  and temp.batch_no='"+batchNo+"' and rownum=1");
		this.baseDao.executeSql(insertBuffer.toString());
		return orderId;
	}
	
	/**
	 * 根据aufnr插入orderbom表　
	 * @param batchNo
	 * @param posKey
	 */
	public void addSapBomsDataByAufnr(int batchNo, String aufnr, String orderId){
		StringBuffer insertBuffer = new StringBuffer();
		insertBuffer.append("   INSERT INTO zg_t_orderbom(CUID,ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr) ");
		insertBuffer.append("		select sys_guid(),ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,'"+orderId+"',AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr from      ");
		insertBuffer.append("    (SELECT distinct ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,a.AUFNR,a.ARBPL,a.MATNR,a.MAKTX1,a.MAKTX2,MSEHL1,MSEHL2,a.LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr   ");
		insertBuffer.append("    FROM Zg_t_Orderbom_Temp_All a,zg_t_order_temp temp WHERE temp.aufnr='"+aufnr+"' and temp.aufnr=a.aufnr and temp.arbpl=a.arbpl    and temp.batch_no ='"+batchNo+"' and a.batch_no='"+batchNo+"')");
		this.baseDao.executeSql(insertBuffer.toString());
		
	}
	
	/**
	 * 更新订单表的生产厂字段
	 * @param orderId
	 * @param string
	 */
	public void doUpdateZgTorderPlanPlant(String orderId, String plant){
		StringBuffer sql=new StringBuffer();
		sql.append("update zg_t_order t set t.plant='"+plant+"' where t.cuid='"+orderId+"'");
		baseDao.executeSql(sql.toString());
		
	}
	/**
	 * 处理供应商信息
	 * @param batchNo
	 */
	public void handleSuppliersData(int batchNo){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from zg_t_order_suppliers t where t.aufnr ");
		sql.append(" in (select temp.aufnr from zg_t_order_temp temp where temp.batch_no='"+batchNo+"' )");
		this.baseDao.executeSql(sql.toString());
		
		sql=new StringBuffer();
		sql.append("insert into zg_t_order_suppliers  (cuid, aufnr, idnrk, lifnr, lifnr_name, plant) ");
		sql.append("  select sys_guid(), temp.aufnr, temp.idnrk,temp.lifnr, temp.name1, temp.plant from zg_t_order_suppliers_temp temp ");
		sql.append("   where temp.batch_no = '"+batchNo+"'");
		this.baseDao.executeSql(sql.toString());
	
	}
	
	
	/**
	 * 拆分生产线时，订单新增，订单状态和原来的订单状态一样
	 * @param posKey
	 * @param batchNo
	 * @param orderState 订单状态
	 * @return
	 */
	public String saveZgTorderForChangeArbpl(String posKey, int batchNo,String orderState) {
		StringBuffer insertBuffer = new StringBuffer();
	 	String orderId=(String) this.baseDao.getSqlMapClientTemplate().queryForObject("Base.selectGUID");
	 	
		insertBuffer.append("insert into ZG_T_ORDER(CUID,AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH)" +
					"select '"+orderId+"',AUFNR,'"+orderState+"',SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,PFLAG,LABEL_CN,PSBH from ZG_T_ORDER_temp temp where  temp.poskey ='"+posKey+"'" +
							"  and temp.batch_no='"+batchNo+"' and rownum=1");
		this.baseDao.executeSql(insertBuffer.toString());
		
		return orderId;
	}
	

	/**
	 * 更新订单bom的制作标识为原来订单的制作标识
	 * @param orderId
	 * @param oldOrderId
	 */
	public void updateOrderBomSorft(String orderId, String oldOrderId){
		StringBuffer sql=new StringBuffer();
		sql.append(" update zg_t_orderbom t set t.sortf=                                  ");
		sql.append("	 (select nvl(temp.sortf,t.sortf) from zg_t_orderbom temp where    ");
		sql.append("			   t.idnrk=temp.idnrk                                     "); 
		sql.append("			  and nvl(t.posnr,'null')=nvl(temp.posnr,'null')          ");
		sql.append("			  and nvl(t.matkl,'null')=nvl(temp.matkl,'null')          ");
		sql.append("			  and nvl(t.lgort,'null')=nvl(temp.lgort,'null')          ");
		sql.append("			  and temp.order_id='"+oldOrderId+"')   ");
		sql.append("			  where t.order_id='"+orderId+"'    ");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 排序数据处理（订单新增）
	 * 处理逻辑：1　插入order表
	 * 　　　　　2　插入订单辅表
	 * 		　　3　插入订单bom表
	 * @param plant
	 * @param posKey
	 * @param pxDate
	 */
	public void pxOrderAdd(String plant, String posKey, Date pxDate,int batchNo) {
		String orderId=saveZgTorder(posKey,batchNo);
		saveZgTorderAide(orderId,plant,pxDate,posKey);
		addSapBomsDataByPosKey(batchNo, posKey,orderId);
	}

	/**
	 * 排序数据处理（排序订单新增生产厂）
	 * 处理逻辑：　添加订单辅表
	 * 　　　　　　更新订单表
	 * @param aufnr
	 * @param plant
	 * @param newPlant
	 * @param arbpl
	 * @param posKey
	 * @param pxDate
	 */
	public void pxOrderAddPlanInfo(String aufnr,String plant, String newPlant, String arbpl, String posKey,
			Date pxDate) {
		String sql="select t.cuid,t.plant from zg_t_order  t where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"' and t.pflag ='X'";
		List<Map> orderList=this.baseDao.queryBySql(sql);
		String orderId=orderList.get(0).get("CUID").toString();
		String oldPlant=orderList.get(0).get("PLANT")==null?"":orderList.get(0).get("PLANT").toString();
		saveZgTorderAide(orderId,plant,pxDate,posKey);
		if(!oldPlant.contains(newPlant)){
			oldPlant=oldPlant+","+plant;
		}
		doUpdateZgTorderPlanPlant(orderId, oldPlant);
	}

	/**
	 * 排序订单数据处理（某一张订单，之前是排序数据，目前来了排序数据时的处理逻辑）
	 * 处理逻辑：1　如果生产线发生变化，则更新订单bom的生产线
	 * 　　　　　2　更新订单辅表　更新订单表
	 * 　　　　　3　如果订单需要数量改变，则相应的更新订单bom的数量
	 * @param aufnr
	 * @param plant
	 * @param arbpl
	 * @param posKey
	 * @param psmng
	 * @param pmenge
	 * @param pxDate
	 * @throws Exception 
	 */
	public void handlePcToPxData(String aufnr, String plant, String arbpl, String posKey,String psmng, String pmenge, Date pxDate,int batchNo) throws Exception {
		//获取原来的生产厂
		String sql="select t.cuid,t.plant,t.arbpl  from zg_t_order t where t.aufnr = '"+aufnr+"' and rownum=1 ";
		String orderId="";
		List<Map> tempList=this.baseDao.queryBySql(sql);
		if(tempList.size()>0){
			orderId=tempList.get(0).get("CUID").toString();
			String oldArbpl=tempList.get(0).get("ARBPL")==null?"":tempList.get(0).get("ARBPL").toString();
			if(!oldArbpl.equals(arbpl)){//生产线发生变化　
				//更新orderbom的生产线
				doUpdateZgTorderBomArbpl(orderId,arbpl,oldArbpl);
			}
		}
		
		
		//判断需求的,i排序数量是否发生变化，如果发生变化，则得去变更相应的　orderbom,orderplanBom表
		boolean  isPsmngNumChangeed = isPsmngNumChangedByOrderId(orderId,psmng,pmenge);
		
		//保存订单辅表
		saveZgTorderAide(orderId,plant,pxDate,posKey);
		
		//根据orderId更新订单信息
		doUpdateZGtorderByOrderId(orderId,posKey,batchNo);
		
		
		// 比对
		CompareSapDataService service = getCompareSapDataService();
		try {
			service.compareBomDataByBatchNoAndAufnr(batchNo,aufnr,arbpl);	// 比对完后处理数据
			
			doUpdateChange(batchNo,aufnr,arbpl);
			
		} catch (Exception e) {
			log.error("compareBomDataByBatchNoAndAufnr方法，对比数据失败,批次--"+batchNo+" :",e);
			throw new Exception("比对数据失败,"+e.getMessage());
		}
		
//		if(isPsmngNumChangeed){//订单排序数量改变，则需要更新相应的　orderbom,orderplanBom表
			//查找本次数据该订单的bom数据
//			sql=" select t.* from zg_t_orderbom_temp_all t,zg_t_order_temp temp where  temp.poskey='"+posKey+"' and temp.batch_NO='"+batchNo+"' and t.batch_no='"+batchNo+"' and temp.aufnr=t.aufnr and temp.arbpl=t.arbpl";
//			List<Map> bomList=this.baseDao.queryBySql(sql);
//			for(Map bom:bomList){
//				updateBomMenge(bom);
//			}
//		}
	}

	/**
	 * 排序接口订单修改
	 * 	逻辑：
	 * 　　　　1　判断订单的生产线是否改变，如果改变，则要相应的去更新orderbom的生产线信息
	 *        2 更新订单辅表，更新订单信息
	 *        3　判断订单需求数是否发生变化，如果发生变化，则需要更新相应的物料bom的需求数量等信息
 	 * @param arbpl
	 * @param posKey
	 * @param psmng
	 * @param pmenge
	 * @param isPsbhChange 是否需要改变生产排序号
	 * @throws Exception 
	 */
	public void pxOrderInfoModify(	String arbpl, String posKey, String psmng, String pmenge,int batchNo,boolean  isPsbhChange,String psbh) throws Exception {
		//获取原来的生产厂,生产线
		String sql="select t.cuid,t.plant,t.arbpl,t.aufnr,t.psbh  from zg_t_order_aide aide, zg_t_order t where aide.poskey = '"+posKey+"'   and aide.order_id = t.cuid";
		String orderId="";
		String aufnr="";
		List<Map> tempList=this.baseDao.queryBySql(sql);
		String plant="";
		if(tempList.size()>0){
			orderId=tempList.get(0).get("CUID").toString();
			aufnr=tempList.get(0).get("AUFNR").toString();
			plant=IbatisDAOHelper.getStringValue(tempList.get(0), "PLANT");
			String oldPlant=tempList.get(0).get("PLANT")==null?"":tempList.get(0).get("PLANT").toString();
			
			String oldArbpl=tempList.get(0).get("ARBPL")==null?"":tempList.get(0).get("ARBPL").toString();
			
			if(!oldArbpl.equals(arbpl)){//生产线发生变化　
				//更新orderbom的生产线
				doUpdateZgTorderBomArbpl(orderId,arbpl,oldArbpl);
			}
			
		}
		
		
		//判断需求的排序数量是否发生变化，如果发生变化，则得去变更相应的　orderbom,orderplanBom表
		boolean  isPsmngNumChangeed = isPsmngNumChanged(posKey,psmng,pmenge);
		
		//更新订单辅表
		doUpdateZgTorderAidePoskey(posKey,batchNo);
		//根据poskey更新订单信息
		doUpdateZGtorderByPosKey(posKey,batchNo);
		
		// 比对
		CompareSapDataService service = getCompareSapDataService();
		try {
			service.compareBomDataByBatchNoAndAufnr(batchNo,aufnr,arbpl);	// 比对完后处理数据
			
			doUpdateChange(batchNo,aufnr,arbpl);
			
			
			
		} catch (Exception e) {
			log.error("compareBomDataByBatchNoAndAufnr方法，对比数据失败,批次--"+batchNo+" :",e);
			throw new Exception("比对数据失败,"+e.getMessage());
		}
		
		if(isPsbhChange){//更新领料计划排序号
			sql="update zg_t_order_plan_group t set t.psbh='"+psbh+"' where exists "
					+"(select 1  from zg_t_order_plan p,zg_t_group_order_plan op where p.order_id='"+orderId+"' and p.plant='"+plant+"' "
					+"and op.order_plan_id=p.cuid and t.cuid=op.group_id and t.INDEXNO=10000)";
			this.baseDao.executeSql(sql);
		}
	
		
//		if(isPsmngNumChangeed){//订单排序数量改变，则需要更新相应的　orderbom,orderplanBom表
			//查找本次数据该订单的bom数据
//			sql=" select t.* from zg_t_orderbom_temp_all t,zg_t_order_temp temp where  temp.poskey='"+posKey+"' and temp.batch_No='"+batchNo+"' and temp.aufnr=t.aufnr and temp.arbpl=t.arbpl  and t.batch_no='"+batchNo+"'";
//			List<Map> bomList=this.baseDao.queryBySql(sql);
//			for(Map bom:bomList){
//				updateBomMenge(bom,orderId);
//			}
//		}
	}

	/**
	 * 生成领料计划
	 * 处理逻辑：　判断之前是否已经生成相应的领料计划，如果没有则生成，有则不处理
	 */
	public void generateCarPlan(int batchNo) {
		//遍历本批次的订单，生成领料计划
		List<Map> list = this.baseDao.queryBySql("select distinct t.cuid,t.arbpl,temp.plant,t.order_state,t.aufnr  from zg_t_order_temp temp,zg_t_order t where temp.aufnr=t.aufnr  and temp.arbpl=t.arbpl and  temp.batch_no="+batchNo);
		for(Map map:list){
			String orderId=map.get("CUID").toString();
			String plant=IbatisDAOHelper.getStringValue(map, "PLANT", "").trim();
			String orderState=IbatisDAOHelper.getStringValue(map, "ORDER_STATE");
			String aufnr=IbatisDAOHelper.getStringValue(map,"AUFNR");
			
			List<String> softList=getZgTorderExBo().getOrderSoftByPlant(plant);
			List listOrderPlan =null;

			try {
				listOrderPlan=this.baseDao.queryBySql("select  * from  zg_t_order_plan t where t.order_id='"+orderId+"'  and t.plan_type='"+softList.get(0)+"'");
			} catch (Exception e) {
				continue;
			}
			if(listOrderPlan!=null&&listOrderPlan.size()>0){//如果原来已经生成装车计划，则不重新生成 如果生成的计划生产厂为空，则补上
				this.baseDao.executeSql("update zg_t_order_plan t set t.plant='"+plant+"' where t.order_id='"+orderId+"'  and t.plan_type='"+softList.get(0)+"' and  t.plant is null ");
			}else {
				String planState="-1";
				if(Constants.OrderPlanStatus.SUBMIT.value().equals(orderState)){//订单是提交状态
					planState="0";
				}
				getZgTorderExBo().createPlan(orderId,softList,null,planState,plant,Constants.isNotManulFinished);
			}
			
		}
	}

	/**
	 * 添加订单（该订单原来存在，拆分生产线 ）
	 * 处理逻辑：
	 * 			1　插入订单表（插入的订单状态和原来已经存在的订单一致）
	 * 　　　　　　2　插入订单辅表、
	 * 　　　　　　3　插入订单bom
	 *          4 更新订单bom的制作标识为原来存在的bom的制作标识一致
	 * @param aufnr
	 * @param plant
	 * @param posKey
	 * @param pxDate
	 */
	public void addOrderForChangeArbpl( String aufnr,	String plant, String posKey, Date pxDate,int batchNo) {
		//查询原来生产线订单的信息
		String sql="select t.cuid,t.order_state from zg_t_order  t where t.aufnr='"+aufnr+"' and rownum=1";
		List<Map> orderList = this.baseDao.queryBySql(sql);
		String orderState="0";
		String oldOrderId="";
		if(orderList.size()>0){
			orderState=orderList.get(0).get("ORDER_STATE")==null?"":orderList.get(0).get("ORDER_STATE").toString()	;
			oldOrderId=orderList.get(0).get("CUID")==null?"":orderList.get(0).get("CUID").toString()	;
		}
		
		String orderId=saveZgTorderForChangeArbpl(posKey,batchNo,orderState);
		
		saveZgTorderAide(orderId,plant,pxDate,posKey);
		addSapBomsDataByPosKey(batchNo, posKey,orderId);
		
		if(!StringHelper.isEmpty(oldOrderId)&&orderState.equals(Constants.OrderPlanStatus.SUBMIT.value())){//更新订单bom的制作标识为原来订单的制作标识
			updateOrderBomSorft(orderId,oldOrderId);
		}
		
		//处理原生产线的订单
		doProcessSourceArbplOrder(posKey,batchNo);
		
		
	}
	
	/**
	 * 订单拆分生产线的时候，假如由原来的外1线拆分到外2线，由外1线的物料需求数据都得做相应的变化，并生成相应的待退料数据等
	 * @param posKey
	 * @param batchNo
	 */
	private void doProcessSourceArbplOrder(String posKey, int batchNo) {
		String sql = "";
	 	
	 	//查询sap过来的数据
		List list = getTempOrderByPoskeyBatchNo(posKey, batchNo);
		
		if(list.size()>0){
			Map map=(Map) list.get(0);
			String sourceArbpl=IbatisDAOHelper.getStringValue(map, "ARBPL1");
			Long pmenge=IbatisDAOHelper.getLongValue(map, "PMENGE");
			String aufnr=IbatisDAOHelper.getStringValue(map, "AUFNR");
			StringBuffer upSql=new StringBuffer();
			
			//获取原生产线订单
			Map orderMap=getOrderMapByAufntArbpl(aufnr,sourceArbpl);
			String sourceOrderId=IbatisDAOHelper.getStringValue(orderMap, "CUID");
			Long sourceOrderMenge=IbatisDAOHelper.getLongValue(orderMap, "PMENGE");
			
			boolean isStartCar=isStartCar(sourceOrderId);
	
			if(pmenge>=sourceOrderMenge){//排序数量＝原来订单的排序数量
				if(!isStartCar){//如果没有开始领料，则直接删除订单
					deleteOrderDateByOrderId(sourceOrderId);
					return;
				}
			}
			//===============订单已经开始领料，生成相应的待退料数据============================
			//更新订单排序数量
			updateOrderPmengeByOrderId(pmenge, sourceOrderId);
			
			//获取该订单bom
			List<Map> bomTempList = getSapBomListByBatchNoPoskey(posKey, batchNo);
			
			for(Map bom:bomTempList){//更新订单BOM　数量并生成待退料信息
				Long menge = IbatisDAOHelper.getLongValue(bom, "MENGE");
				String matnr = IbatisDAOHelper.getStringValue(bom, "MATNR","");
				String matnr1 = IbatisDAOHelper.getStringValue(bom, "MATNR1","");
				String idnrk = IbatisDAOHelper.getStringValue(bom, "IDNRK","");
				String posnr=IbatisDAOHelper.getStringValue(bom, "POSNR", "");
				//获取zgtorderplanbom
				Map planBom = getPlanBom(aufnr,matnr,matnr1,idnrk,posnr,sourceArbpl);
				
				List<Map> bomList =this.baseDao.queryBySql("select t.cuid,t.menge from zg_t_orderbom t where t.order_id='"+sourceOrderId+"' and t.idnrk='"+idnrk+"' and t.posnr='"+posnr+"'");
				if(bomList.size()>0){
					String bomId=IbatisDAOHelper.getStringValue(bomList.get(0), "CUID");
					Long oldMenge=IbatisDAOHelper.getLongValue(bomList.get(0), "MENGE");
					menge=oldMenge-menge;
					
					//更新zgtorderbom menge数量
					sql="  update zg_t_orderbom t set t.menge="+menge+" where t.cuid='"+bomId+"'";
					this.baseDao.executeSql(sql);
					
					//生成待退料数量
					productBackBomData(menge, planBom);
				}
			}
			
			getZgTorderbomExBo().doZgtorderProcess(sourceOrderId);
			
		}
		
		
	}

	/**
	 * 生成待退料数据 当已领料数量大于需求数量，生产待退料数量，否则直接更新需求数量
	 * @param menge　新需求数
	 * @param planBom　原来的planbom信息
	 */
	private void productBackBomData(Long menge, Map planBom) {
		if(planBom != null) {
			Long completeNum = IbatisDAOHelper.getLongValue(planBom, "COMPLETE_NUM");
			String orderBomId = IbatisDAOHelper.getStringValue(planBom, "ORDER_BOM_ID");
			String planBomId=IbatisDAOHelper.getStringValue(planBom, "CUID");
			if(!StringUtils.isBlank(orderBomId)) {
				StringBuffer updatePlanBomSql = new StringBuffer();
				if(menge <= completeNum){//已领料数量大于需求数量，生产待退料数量
					updatePlanBomSql.append("update zg_t_order_planbom t set state='9', t.car_num = "+menge+",t.WAIT_BACK_NUM= "+Math.abs(menge-completeNum));
					updatePlanBomSql.append(" where cuid = '"+planBomId+"'              ");
				}else {
					updatePlanBomSql.append("update zg_t_order_planbom set state='0',car_num = "+menge);
					updatePlanBomSql.append(" where cuid = '"+planBomId+"'              ");
				}
				
				this.baseDao.executeSql(updatePlanBomSql.toString());
			}
		}
	}

	/**
	 * 根据poskey和版本号　查找从sap过来的bom数据
	 * @param posKey
	 * @param batchNo
	 * @return
	 */
	private List<Map> getSapBomListByBatchNoPoskey(String posKey, int batchNo) {
		String sql;
		sql="SELECT distinct ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,a.AUFNR,a.ARBPL,a.MATNR,a.MAKTX1,a.MAKTX2,MSEHL1,MSEHL2,a.LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr   "
		   +"    FROM Zg_t_Orderbom_Temp_All a,zg_t_order_temp temp WHERE temp.poskey='"+posKey+"' and a.batch_no='"+batchNo+"' and  temp.aufnr=a.aufnr and temp.arbpl=a.arbpl    and temp.batch_no ='"+batchNo+"'";
		List<Map> bomTempList=this.baseDao.queryBySql(sql);
		return bomTempList;
	}

	/**
	 * 更新订单排序数量
	 * @param pmenge
	 * @param sourceOrderId
	 */
	private void updateOrderPmengeByOrderId(Long pmenge, String sourceOrderId) {
		String sql;
		sql="update zg_t_order t set t.pmenge=t.pmenge-"+pmenge+" where  t.cuid='"+sourceOrderId+"'";
		this.baseDao.executeSql(sql);
	}

	
	/**
	 * 查询原生产线订单是否已经开始领料
	 * @param orderId
	 * @return
	 */
	private boolean isStartCar(String orderId) {
		//
		String sql="select * from zg_t_order_planbom bom  where bom.order_id='"+orderId+"' and bom.complete_num>0";
		return this.baseDao.queryBySql(sql).size()>0;
	}

	/**
	 * 根据订单号和生产线获取订单信息
	 * @param aufnr
	 * @param arbpl
	 * @return
	 */
	private Map getOrderMapByAufntArbpl(String aufnr, String arbpl) {
		List<Map> orderList=this.baseDao.queryBySql("select * from zg_t_order t where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"'");
		
		if(orderList.size()==0){
			return null;
		}
		return orderList.get(0);
	}

	/**
	 * 根据poskey和batchNo查询sap过来的临时订单
	 * @param posKey
	 * @param batchNo
	 * @return
	 */
	private List getTempOrderByPoskeyBatchNo(String posKey, int batchNo) {
		String sql;
		sql="select temp.arbpl,temp.arbpl1,temp.pmenge,temp.aufnr from ZG_T_ORDER_temp temp " +
				"where  temp.poskey ='"+posKey+"'   and temp.batch_no='"+batchNo+"' and rownum=1";
		List list=this.baseDao.queryBySql(sql);
		return list;
	}

	/**
	 * 删除订单相关数据
	 * @param sourceOrderId
	 */
	private void deleteOrderDateByOrderId(String orderId) {
		deleteOrderPlanBomByOrderId(orderId,"");
		deleteOrderPlanByOrderId(orderId,"");
		deleteOrderBomByOrderId(orderId);
		deleteOrderByOrderId(orderId);
	}

	/**
	 * 变更接口处理订单状态
	 * 	处理逻辑：
	 * 		查看状态表是否有记录过来，如果没有，则说明订单要恢复正常状态（如果订单已经完成则不需要恢复订单状态）
	 * 		订单删除，　置FREEZE为－1删除状态
	 * 　　　订单冻结　则FREEZE为4　订单整单冻结状态
	 * 	
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 */
	public void doProcessOrderState(int batchNo, String aufnr, String arbpl){
		StringBuffer sql=new StringBuffer("select * from zg_t_STATTAB_temp t where t.batch_no='"+batchNo+"'");
		
		List<Map> list=this.baseDao.findDynQuery(sql.toString());
		
		
		if(list.size()>0){
			String state=list.get(0).get("STAT")==null?"":list.get(0).get("STAT").toString();
			
			if(Constants.InterFaceOrderState.Del.value().equals(state)){//订单删除，　置orderState为－1删除状态
				updateOrderState(aufnr,arbpl,Constants.OrderStatus.DEL.value());
			}
			if(Constants.InterFaceOrderState.Freeze.value().equals(state)){//订单冻结　则FREEZE为4　订单整单冻结状态
				updateorderFreeze(aufnr,arbpl,Constants.OrderFreezeState.FREEZE.value());
			}
			if(Constants.InterFaceOrderState.Finished.value().equals(state)){//订单完成
				updateOrderState(aufnr,arbpl,Constants.OrderStatus.FINISHED.value());
				updateorderFreeze(aufnr,arbpl,Constants.OrderFreezeState.normal.value());
			}
			if(StringHelper.isEmpty(state)){//状态为空，正常状态
				String orderState = getOrderStateByAufnrArbpl(aufnr, arbpl);
				
				if((!Constants.OrderStatus.FINISHED.value().equals(orderState))&&(!Constants.OrderStatus.SUBMIT.value().equals(orderState))){//订单状态是完成的时候不用改变订单状态
					updateOrderState(aufnr,arbpl,Constants.OrderStatus.NEW.value());
				}
				
				updateorderFreeze(aufnr,arbpl,Constants.OrderFreezeState.normal.value());
			}
			
		}else {//订单恢复正常状态
			//获取订单状态
			String orderState = getOrderStateByAufnrArbpl(aufnr, arbpl);
			
			if((!Constants.OrderStatus.FINISHED.value().equals(orderState))&&(!Constants.OrderStatus.SUBMIT.value().equals(orderState))){//订单状态是完成的时候不用改变订单状态
				updateOrderState(aufnr,arbpl,Constants.OrderStatus.NEW.value());
			}
			
			updateorderFreeze(aufnr,arbpl,Constants.OrderFreezeState.normal.value());
		}
		
		
	}

	/**
	 * 获取订单状态
	 * @param aufnr
	 * @param arbpl
	 * @return
	 */
	private String getOrderStateByAufnrArbpl(String aufnr, String arbpl) {
		StringBuffer sql;
		List<Map> list;
		sql=new StringBuffer("select t.order_state from  zg_t_order t  where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"'");
		list=this.baseDao.findDynQuery(sql.toString());
		String orderState="0";
		if(list.size()>0){
			orderState=IbatisDAOHelper.getStringValue(list.get(0), "ORDER_STATE");
		}
		return orderState;
	}

	/**
	 * 订单的冻结字段更新
	 * @param aufnr
	 * @param value
	 */
	private void updateorderFreeze(String aufnr,String arbpl, String freeze) {
		StringBuffer sql=new StringBuffer();
		sql.append("update zg_t_order t set t.freeze='"+freeze+"' where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"'");
		this.baseDao.executeSql(sql.toString());
		
	}

	/**
	 * 修改订单状态
	 * @param aufnr
	 * @param value
	 */
	private void updateOrderState(String aufnr,String arbpl, String state) {
		StringBuffer sql=new StringBuffer();
		sql.append("update zg_t_order t set t.order_state='"+state+"' where t.aufnr='"+aufnr+"' and t.arbpl='"+arbpl+"'");
		this.baseDao.executeSql(sql.toString());
	}
	
	/**
	 * 获取需求同步回sap的bom领料数据
	 * @param synTable
	 */
	@SuppressWarnings("unchecked")
	public void getSynBomData(JCoTable synTable,List<Map> synBomList,int batchNo){
		//获取订单领料bom数据（需要同步回sap的）
		List<Map> orderBomList=this.baseDao.getSqlMapClientTemplate().queryForList("SapBom.getSynSapOrderBom");
		
		//获取批量领料bom数据（需要同步回sap的）
		List<Map> batchBomList=this.baseDao.getSqlMapClientTemplate().queryForList("SapBom.getSynSapBatchBom");
		int num=0;
		for(Map bom:orderBomList){
			synBomList.add(bom);
			parseDataToTable(synTable, num, bom);
			num++;
		}
		
		for(Map bom:batchBomList){
			synBomList.add(bom);
			parseDataToTable(synTable, num, bom);
			num++;
		}
		
		try {
//			SapBusiService.parseDataXls(synTable, Constants.InterFaceMethod.SYN.value(), "", batchNo) ;
			getSapBusiService().parseData(synTable, Constants.InterFaceMethod.SYN.value(), "",batchNo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("写历史记录失败",e);
		}

		
	}
	
	/**
	 * 获取指定领料号单的待同步装车bom数据
	 * @param orderPlanId 如果是订单领料　则些字段是领料计划单id ,如果是批量领料，则些字段是装车单id
	 * @param synTable
	 * @param synBomList
	 */
	@SuppressWarnings("unch")
	public void getSynBomDataByOrderPlanId(String orderPlanId, JCoTable synTable,List<Map> synBomList,int batchNo){
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("orderPlanId", orderPlanId);
		
		//判断该订单是订单领料还是批量领料
		boolean isOrderPlan=this.isOrderPlan(orderPlanId);
		List<Map> bomList=new ArrayList<Map>();
		if(isOrderPlan){//订单领料
			bomList=this.baseDao.getSqlMapClientTemplate().queryForList("SapBom.getSynSapOrderBomByOrderPlanId",paramsMap);
		}else {//批量领料
			bomList=this.baseDao.getSqlMapClientTemplate().queryForList("SapBom.getSynSapBatchBom",paramsMap);
		}
		
		int num=0;
		for(Map bom:bomList){
			synBomList.add(bom);
			parseDataToTable(synTable, num, bom);
			num++;
		}
		
		try {
//			SapBusiService.parseDataXls(synTable, Constants.InterFaceMethod.SYN.value(), "", batchNo);
			getSapBusiService().parseData(synTable, Constants.InterFaceMethod.SYN.value(), "",batchNo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

	/**
	 * 判断是否批量领料
	 * @param orderPlanId
	 */
	private boolean isOrderPlan(String orderPlanId) {
		StringBuffer sqlBuffer=new StringBuffer("select 1 from Zg_t_Order_Plan t where t.cuid='"+orderPlanId+"'  and t.plan_type<>'3'");
		return this.baseDao.queryBySql(sqlBuffer.toString()).size()>0;
	}

	/**
	 * 把map里面的数据加到table中
	 * @param synTable
	 * @param num
	 * @param bom
	 */
	private void parseDataToTable(JCoTable synTable, int num, Map bom) {
		try {
			String mandt=IbatisDAOHelper.getStringValue(bom, "MANDT");
			String zrfpid=IbatisDAOHelper.getStringValue(bom, "ZRFPID");
			String aufnr=IbatisDAOHelper.getStringValue(bom, "AUFNR");
			String zrfpos=IbatisDAOHelper.getStringValue(bom, "ZRFPOS");
			String matnr=IbatisDAOHelper.getStringValue(bom, "MATNR");
			String maktx1=IbatisDAOHelper.getStringValue(bom, "MAKTX1");
//			String arbpl=IbatisDAOHelper.getStringValue(bom, "ARBPL");
			String idnrk=IbatisDAOHelper.getStringValue(bom, "IDNRK");
			String maktx2=IbatisDAOHelper.getStringValue(bom, "MAKTX2");
			String msehl1=IbatisDAOHelper.getStringValue(bom, "MSEHL1");
			String zdtyl=IbatisDAOHelper.getStringValue(bom, "ZDTYL");
			String menge=IbatisDAOHelper.getStringValue(bom, "MENGE");
			String lgmng=IbatisDAOHelper.getStringValue(bom, "LGMNG");
			String lifnr=IbatisDAOHelper.getStringValue(bom, "LIFNR");
			String name1=IbatisDAOHelper.getStringValue(bom, "NAME1");
			String sortf=IbatisDAOHelper.getStringValue(bom, "SORTF");
			String lgort=IbatisDAOHelper.getStringValue(bom, "LGORT");
			String lltyp=IbatisDAOHelper.getStringValue(bom, "LLTYP");
			String mipos=IbatisDAOHelper.getStringValue(bom,"MIPOS");
			String kdauf=IbatisDAOHelper.getStringValue(bom,"KDAUF");
			//String posnr=IbatisDAOHelper.getStringValue(bom,"POSNR");
			
			synTable.appendRow();
	        //定位到第0行
			synTable.setRow(num);
			//设定该行对应变量
//			synTable.setValue("MIPOS",mipos );
			synTable.setValue( "KDAUF",kdauf);
			synTable.setValue("MANDT",mandt );
			synTable.setValue( "ZRFPID",zrfpid);
			synTable.setValue("ZRFPOS",zrfpos);
			synTable.setValue("AUFNR",aufnr);
			synTable.setValue("MATNR",matnr);
			synTable.setValue("MAKTX1",maktx1);
//			synTable.setValue("ARBPL",arbpl);
			synTable.setValue("IDNRK",idnrk);
			synTable.setValue("MAKTX2",maktx2);
			synTable.setValue("MSEHL1",msehl1);
			synTable.setValue("ZDTYL",zdtyl);
			synTable.setValue("MENGE",menge);
			synTable.setValue("LGMNG",lgmng);
			synTable.setValue("LIFNR",lifnr);
			synTable.setValue("NAME1",name1);
			synTable.setValue("SORTF",sortf);
			synTable.setValue("LGORT",lgort);
			synTable.setValue("LLTYP",lltyp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//	synTable.setValue("POSNR",posnr);
	}
	
	/**
	 * 标记装车计划为已同步
	 * @param synBomList
	 */
	public void updateCarPlanSynFlag(List<Map> synBomList){
//		String sqlString="update zg_t_carplan t set t.syn='1' where t.cuid in ('')";
		for(Map bom:synBomList){
			String carPlanIds=IbatisDAOHelper.getStringValue(bom, "CARPLANCUID");
			carPlanIds=carPlanIds.replace(",", "','");
			this.baseDao.executeSql("update zg_t_carplan t set t.syn='1' where t.cuid in ('"+carPlanIds+"')");
		}
	}
	
	private CompareSapDataService getCompareSapDataService() {
		return (CompareSapDataService) ApplicationContextHolder
				.getBean("compareSapDataServiceImpl");
	}

	/**
	 * 获取指定领料号单的待同步装车bom数据
	 * @param orderPlanId 如果是订单领料　则些字段是领料计划单id ,如果是批量领料，则些字段是装车单id
	 * @param synTable
	 * @param synBomList
	 */
	@SuppressWarnings("unchecked")
	public void getSynBomDataByCarPlanId(String carPlanId, JCoTable synTable,List<Map> synBomList,int batchNo){
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("carPlanId", carPlanId);
		
		List<Map> bomList=this.baseDao.getSqlMapClientTemplate().queryForList("SapBom.getSynSapOrderBomByCarPlanId",paramsMap);
		
		int num=0;
		for(Map bom:bomList){
			synBomList.add(bom);
			parseDataToTable(synTable, num, bom);
			num++;
		}
		
		try {
//			SapBusiService.parseDataXls(synTable, Constants.InterFaceMethod.SYN.value(), "", batchNo);
			getSapBusiService().parseData(synTable, Constants.InterFaceMethod.SYN.value(), "",batchNo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
	
	/**
	 * 		退料逻辑处理
	 * 		1 检查退料记录合法性(是否需要退料)
	 *   	2 同步退料数据 ，planbom中相应的bom ：WAIT_BACK_NUM=WAIT_BACK_NUM-num
	 * 										  BACK_NUM=BACK_NUM+num
	 * 										  COMPLETE_NUM= COMPLETE_NUM-num
	 * 										  PLAN_NUM=PLAN_NUM-num
	 * 										  STORAGE_NUM=STORAGE_NUM-num
	 *      3 插入退料记录到zg_t_back_bom表
	 *      4 重新记录订单的领料进度
	 * @param batchNo
	 */
	public SapResult handlerBackBOM(int batchNo){
		ZgTbackBomTemp zgTbackBomTemp=new ZgTbackBomTemp();
		zgTbackBomTemp.setBatchNo(Long.parseLong(batchNo+""));
		List<ZgTbackBomTemp> list=getZgTbackBomTempBo().findByProperty(zgTbackBomTemp);
		
		SapResult sapResult=new SapResult();
		
		for(ZgTbackBomTemp temp:list){//检查
			Long waitBackNum=getWaitBackNumByAufnrIdnrk(temp.getAufnr(), temp.getMatnr());
			if(waitBackNum<temp.getMengeD()){//sap传过来的退料数量小于本系统的待退料 数量，则数据异常
				sapResult.setType("E");
				sapResult.setNumber("003");
				sapResult.setMessage("订单"+temp.getAufnr() +" 物料"+temp.getMatnr()+" 在RFID系统中没有该退料记录");
				return sapResult;
			}
		}
		
		//2 同步退料数据
		/*：WAIT_BACK_NUM=WAIT_BACK_NUM-num
		 * 										  BACK_NUM=BACK_NUM+num
		 * 										  COMPLETE_NUM= COMPLETE_NUM-num
		 * 										  PLAN_NUM=PLAN_NUM-num
		 * 										  STORAGE_NUM=STORAGE_NUM-num*/
		for(ZgTbackBomTemp temp:list){
//			Long waitBackNum=getWaitBackNumByAufnrIdnrk(temp.getAufnr(), temp.getMatnr());
			List<ZgTorderPlanbom> bomList=getZgTorderPlanbomDao().getWaitBackBomListByAufnrIdnrk(temp.getAufnr(),temp.getMatnr());
			for(ZgTorderPlanbom bom:bomList){
				bom.setWaitBackNum(bom.getWaitBackNum()-temp.getMengeD());
				bom.setBackNum(bom.getBackNum()+temp.getMengeD());
				bom.setCompleteNum(bom.getCompleteNum()-temp.getMengeD());
				bom.setPlanNum(bom.getPlanNum()-temp.getMengeD());
				bom.setStorageNum(bom.getStorageNum()-temp.getMengeD());
				getZgTorderPlanbomDao().update(bom);
			}
		}
		
        //3 插入退料记录到zg_t_back_bom表
		getZgTbackBomBo().insertBackBomLog(batchNo);
		
		//4重新记录订单的领料进度
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("  select t.* from zg_t_order t ,zg_t_back_bom_temp temp where temp.batch_no="+batchNo+" and temp.aufnr=t.aufnr");
		List<Map> orderList= this.baseDao.queryBySql(sqlBuffer.toString());
		for(Map order:orderList){
			String orderId=IbatisDAOHelper.getStringValue(order, "CUID","");
			getZgTorderbomExBo().doZgtorderProcess(orderId);
		}
		
//		StringBuffer insertBuffer=new StringBuffer();
//		insertBuffer.append(" insert into zg_t_back_bom ");
//		insertBuffer.append("  (cuid, aufnr, matnr, maktx, menge_d, lgort_d, budat, cputm)");
//		insertBuffer.append(" select cuid, aufnr, matnr, maktx, menge_d, lgort_d, budat, cputm from zg_t_back_bom_temp temp");
//		insertBuffer.append(" where temp.batch_no='"+batchNo+"'");
//		this.baseDao.executeSql(insertBuffer.toString());
		
		sapResult.setType("S");
		sapResult.setNumber("001");
		sapResult.setMessage("数据写入成功");
		return sapResult;
		
		
	}
	
	/**
	 * 根据订单号和物料号查询其待退料数据
	 * @param aufnr
	 * @param idnrk
	 */
	public Long getWaitBackNumByAufnrIdnrk(String aufnr,String idnrk){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("  select sum(t.wait_back_num) waitbacknum from ");
		sqlBuffer.append(" zg_t_order_planbom t, zg_t_orderbom bom");
		sqlBuffer.append("   where bom.aufnr = '"+aufnr+"'");
		sqlBuffer.append("     and bom.idnrk = '"+idnrk+"'");
		sqlBuffer.append("     and bom.cuid = t.order_bom_id");
		sqlBuffer.append("   group by bom.aufnr, bom.idnrk");
		List<Map> list= this.baseDao.queryBySql(sqlBuffer.toString());
		if(list.size()==0) return 0l;
		return IbatisDAOHelper.getLongValue(list.get(0), "WAITBACKNUM");
	}
	
}
