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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.dwrp.Batch;
import org.springframework.stereotype.Component;

import sap.SapBusiService;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderLock;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderTask;
import com.boco.zg.plan.base.model.ZgTorderTemp;
import com.boco.zg.plan.base.service.ZgTbackBomBo;
import com.boco.zg.plan.base.service.ZgTbackBomTempBo;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.base.service.ZgTorderLockBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTorderTaskBo;
import com.boco.zg.plan.base.service.ZgTorderTaskbomBo;
import com.boco.zg.plan.base.service.ZgTorderTempBo;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;
import com.sap.conn.jco.JCoTable;

@Component
public class HandlerSapDataServiceImpl implements HandlerSapDataService {

	private BaseDao baseDao;
	
	public static Map<String, String> plantSortfMap=null;
	
	public  Map<String, String>  getPlantSortfMap(){
		if(plantSortfMap==null){
			plantSortfMap=getZgTorderBo().getPlantSortfMap();
		}
		return plantSortfMap;
	}
	
	private static final Log log=LogFactory.getLog(HandlerSapDataService.class);

	private String NOTINSTR="CUID,SORTF,BATCH_NO,OPERATE_TYPE,PLANT,STORAGE_NUM,STORAGE_STATE";
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	
	private ZgTorderPlanbomBo getZgTorderPlanbomBo(){
		return (ZgTorderPlanbomBo) ApplicationContextHolder
		.getBean("zgTorderPlanbomBo");
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
	
	private ZgTorderBo getZgTorderBo(){
		return (ZgTorderBo) ApplicationContextHolder
		.getBean("zgTorderBo");
	}
	
	private SapBusiService getSapBusiService(){
		return (SapBusiService) ApplicationContextHolder
		.getBean("sapBusiService");
	}
	
	public ZgTorderTempBo getZgTorderTempBo(){
		return (ZgTorderTempBo) ApplicationContextHolder
		.getBean("zgTorderTempBo");
	}
	
	
	private ZgTorderTaskBo getZgTorderTaskBo(){
		return (ZgTorderTaskBo) ApplicationContextHolder
		.getBean("zgTorderTaskBo");
	}
	
	
	
	
	
	
	
	
	private ZgTorderPlanBo getZgTorderPlanBo(){
		return (ZgTorderPlanBo) ApplicationContextHolder
		.getBean("zgTorderPlanBo");
	}
	
	/**
	 * @return
	 */
	private ZgTorderbomBo getZgTorderbomBo() {
		return (ZgTorderbomBo) ApplicationContextHolder
				.getBean("zgTorderbomBo");
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

	
	private Map getPlanBom(String aufnr, String matnr,String matnr1,String idnrk,String posnr,String arbpl) {
		StringBuffer findPlanBomSql = new StringBuffer();
		findPlanBomSql.append("select ob.arbpl,pb.cuid,ob.cuid as order_bom_id,ob.order_id,pb.car_num,nvl(pb.plan_num,0) plan_num,pb.complete_num  ");
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
	

	
	public static void main(String[] args) {
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("update zg_t_orderbom t set t.matkl_self=(select bom.matkl_self from zg_t_bom bom where t.idnrk=bom.idnrk and rownum=1) ");
		sqlBuffer.append(" where exists (select * from zg_t_order_task task where to_char(task.px_date,'yyyy-mm-dd')='2011-02-26' and t.order_id=task.order_id)");
		System.out.println(sqlBuffer.toString());
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
        sql.append("         (select zm.cuid from ZG_MATERIEL zm where zm.id = temp.lgort and rownum=1),  ");
        sql.append("        temp.matkl, '2', temp.lgort                                      ");
        sql.append("    from (select t.matkl, t.lgort                                       ");
        sql.append("            from zg_t_orderbom t                                        ");
        sql.append("          where   t.lgort is not null and t.matkl is not null        ");
        sql.append("             and t.matkl || '-' || t.lgort not in                        ");
        sql.append("                 (select zm.id || '-' ||zm.lgort                      ");
        sql.append("                    from ZG_MATERIEL zm)                                 ");
        sql.append("           group by t.lgort, t.matkl) temp                       ");
        this.baseDao.executeSql(sql.toString());
        
        //同步zgtbom表
        sql=new StringBuffer();
        sql.append("insert into ZG_T_BOM                                                                     ");
        sql.append("  (CUID, IDNRK, MAKTX, MATKL, LGORT, TYPE, MSEHL,matkl_self)                                        ");
        sql.append("  select sys_guid(), temp.IDNRK,temp.MAKTX2,temp.MATKL, temp.LGORT, '1',   temp.MSEHL2,matkl_self   ");
		sql.append("    from (select distinct t.IDNRK,   t.MAKTX2, t.MATKL, t.LGORT,  t.MSEHL2 ,t.matkl_self              ");
		sql.append("            from zg_t_orderbom t                                                         ");
		sql.append("           where t.matkl is not null                                                     ");
		sql.append("           and t.lgort is not null and                                                   ");
		sql.append("          not exists (select 1 from zg_t_bom bom where bom.idnrk=t.idnrk                 ");
		sql.append("          )     ) temp                       ");
	    this.baseDao.executeSql(sql.toString());
	    
	    //提前领料物料组管理 
	    sql=new StringBuffer();
	    sql.append("update zg_materiel m    set m.advance = '1'  where m.id in (select t.Id   from zg_materiel t    where t.advance = '1'         and t.type = '2')");
	    this.baseDao.executeSql(sql.toString());
	}



	/**
	 * rfid中存在并且接口过来的数据中不存在的订单列表
	 * @param batchNo
	 */
	public void deleteNotExistOrder(int batchNo) {
		if(log.isInfoEnabled()){
			log.info("线程号："+batchNo+"enter the method deleteNotExistOrder");
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select temp.pxdat from zg_t_order_temp temp where  temp.batch_no='"+batchNo+"' and rownum=1");
		List<Map> list=this.baseDao.queryBySql(sql.toString());
		
		//获取本次过来的生产厂
		String plants=getPlantByBatchNo(batchNo);
		
		if(list.size()>0){
			Date date=(Date) list.get(0).get("PXDAT");
			sql=new StringBuffer();
			sql.append(" select t.cuid order_id, task.cuid task_id, task.plant, task.arbpl,task.aufnr ");
			sql.append("  from zg_t_order t, zg_t_order_task task                          ");
			sql.append(" where t.cuid = task.order_id                                      ");
			sql.append("   and to_char(task.px_date,'yyyy-MM-dd') = '"+TimeFormatHelper.getFormatDate(date, TimeFormatHelper.DATE_FORMAT)+"'   ");
			sql.append("   and not exists (select 1                                        ");
			sql.append("          from zg_t_order_temp temp                                ");
			sql.append("         where temp.batch_no = "+batchNo                           );
			sql.append("           and temp.aufnr = t.aufnr                                ");
			sql.append("           and temp.plant = task.plant                             ");
			sql.append("           and temp.arbpl=task.arbpl)                              ");
			sql.append("   and task.flag <> '-1'                                      ");
			sql.append("   and task.plant in ('"+plants+"')                                    ");
			
			List<Map> notExistOrderList=this.baseDao.queryBySql(sql.toString());
			if(log.isInfoEnabled()){
				log.info("线程号："+batchNo+"找到 "+notExistOrderList.size()+" 张需要删除的订单");
			}
			
			//删除不存在的订单
			deleteNotExistOrder(batchNo,notExistOrderList);
			
			
		}
		
		if(log.isInfoEnabled()){
			log.info("线程号："+batchNo+"leave the method deleteNotExistOrder");
		}
			
		
	}

	/**
	 * @param batchNo
	 * @return
	 */
	private String getPlantByBatchNo(int batchNo) {
		List<Map> plantList=this.baseDao.queryBySql("select * from ZG_T_ORDER_PLANT_TEMP t where t.batch_no="+batchNo);
		String plant="";
		for(Map obj:plantList){
			plant=plant+IbatisDAOHelper.getStringValue(obj,"PLANT")+"','";
		}
		if(plant.length()>0){
			plant=plant.substring(0,plant.length()-3);
		}
		return plant;
	}
	
	public ZgTorderLockBo getZgTorderLockBo(){
		return (ZgTorderLockBo) ApplicationContextHolder
		.getBean("zgTorderLockBo");
	}


	/**
	 *如果订单已经开始领料，则设置删除标志，做退料处理
	 * @param notExistOrderList
	 */
	//TODO WENGQ(目前没有备料库表，所以直接物料删除，以后这一部分改成如果领了料的订单删除的话得存备料库表)
	private void deleteNotExistOrder(int batchNo,List<Map> notExistOrderList) {
		if(log.isInfoEnabled()){
			log.info("线程号："+batchNo+"enter the method deleteNotExistOrder");
		}
		
		ZgTorderLock zgTorderLock=getZgTorderLockBo().getById("DEL");
		for(Map orderMap:notExistOrderList){
			
			String orderId=orderMap.get("ORDER_ID").toString();
			String plant=orderMap.get("PLANT").toString();
			String aufnr=orderMap.get("AUFNR").toString();
			String arbpl=orderMap.get("ARBPL").toString();
			String taskId=orderMap.get("TASK_ID").toString();
			
			//订单加锁，同步不允许领料
			zgTorderLock.setAufnr(aufnr);
			getZgTorderLockBo().update(zgTorderLock);
			
			if(log.isInfoEnabled()){
				log.info("线程号："+batchNo+" 开始删除订单"+aufnr+"-"+arbpl+"-"+plant+" task_id:"+taskId);
			}
		
			//判断该排序数据是否已经开始领料
			boolean isStart=isOrderTaskStartCar(taskId);
			if(isStart){//已经开始领料 生成退料数据
				// 设置订单任务表为删除标志
				
			}
			
			String planType=getPlantSortfMap().get(plant);
			ZgTorderPlan plan=getZgTorderPlanBo().getPlanByTaskIdPlanType(taskId,planType);
			
			if(isStart){//已经开始领料，则产生退料数据
				//更新ordertask 需求量为0
				deleteZgTorderTaskById(taskId);
				
				//更新taskbom需求量为0
				getZgTorderTaskbomBo().updateTaskBomMengeTO0ByTaskId(taskId);
				
				
				// 更新plan需求量为0 并产生退料数据
				if(plan!=null){
					getZgTorderPlanbomBo().updatePlanBomCarNumTo0ByPlanId(plan.getCuid());
				}
				
				
			}else {//没有开始领料的，直接做删除处理
				getZgTorderTaskBo().removeById(taskId);
				
				//更新taskbom需求量为0
				getZgTorderTaskbomBo().delTaskBomTaskId(taskId);
				
				// 更新plan需求量为0 并产生退料数据
				if(plan!=null){
					getZgTorderPlanbomBo().delPlanBomPlanId(plan.getCuid());
					getZgTorderPlanBo().removeById(plan.getCuid());
				}
				
			}
			
			//订单解锁
			zgTorderLock.setAufnr("");
			getZgTorderLockBo().update(zgTorderLock);
			
		}
		if(log.isInfoEnabled()){
			log.info("线程号："+batchNo+"leave the method deleteNotExistOrder");
		}
	}

	private ZgTorderTaskbomBo getZgTorderTaskbomBo(){
		return (ZgTorderTaskbomBo) ApplicationContextHolder
		.getBean("zgTorderTaskbomBo");
	}
	
	/**
	 * @param taskId
	 * @return
	 */
	private boolean isOrderTaskStartCar(String taskId) {
		String sql="select * from zg_t_order_planbom bom  where bom.order_task_id='"+taskId+"' and bom.plan_num>0";
		return this.baseDao.queryBySql(sql).size()>0;
	}

	/**
	 * //删除订单时设置退料数据
	 * @param taskId
	 */
	private void doBackBomForDelOrder(String taskId) {
		this.baseDao.executeSql("update zg_t_order_planbom t set t.wait_back_num=t.complete_num where t.order_task_id='"+taskId+"'");
		
	}

	/**
	 * 设置删除标志
	 * @param taskId
	 */
	private void deleteZgTorderTaskById(String taskId) {
		ZgTorderTask task=getZgTorderTaskBo().getById(taskId);
		task.setFlag(ZgTorderTask.DELSTATE);
		task.setPmenge(0l);
		getZgTorderTaskBo().update(task);
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
			String posnr=IbatisDAOHelper.getStringValue(bom,"POSNR");
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
			synTable.setValue("POSNR",posnr);
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
	 * @return
	 */
	private HandlerOrderServiceImpl getHandlerOrderServiceImpl() {
		return (HandlerOrderServiceImpl) ApplicationContextHolder
		.getBean("handlerOrderServiceImpl");
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
	 * 处理自有物料组
	 * 需在系统中增加一物料组，该物料组是在原来物料组的基础上进行细分，对于SAP每个同步过来的物料都需维护该物料组，
	 * 只要维护过一次下一次就下载时就不用再进行给维护，系统进行记忆功能，维护是在BOM标识调整时进行维护，
	 * 没有维护不能把生产订单下发领料。
	 */
	public void handlerMatklSelfData(int batchNo,String type){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select to_char(t.pcdat,'yyyy-mm-dd') pcdat,to_char(t.pxdat,'yyyy-mm-dd') pxdat from zg_t_order_temp t where t.batch_no='"+batchNo+"' and rownum=1");
		List<Map> list=this.baseDao.findDynQuery(sqlBuffer.toString());
		if(list.size()>0){
			//排产日期
			String pcDatStr=list.get(0).get("PCDAT").toString();
			String pxDatStr=list.get(0).get("PXDAT").toString();
		
			if("pc".equals(type)){//排产接口 ，则更新当天的排产数据
				sqlBuffer=new StringBuffer();
				sqlBuffer.append("update zg_t_orderbom t set t.matkl_self=(select bom.matkl_self from zg_t_bom bom where t.idnrk=bom.idnrk and rownum=1) ");
				sqlBuffer.append(" where exists (select 1 from zg_t_order od where to_char(od.pcdat,'yyyy-mm-dd')='"+pcDatStr+"' and t.order_id=od.cuid)");
				
			}else {//排序和变更接口 则更新当天的排序数据
				sqlBuffer=new StringBuffer();
				sqlBuffer.append("update zg_t_orderbom t set t.matkl_self=(select bom.matkl_self from zg_t_bom bom where t.idnrk=bom.idnrk and rownum=1) ");
				sqlBuffer.append(" where exists (select * from zg_t_order_task task where to_char(task.px_date,'yyyy-mm-dd')='"+pxDatStr+"' and t.order_id=task.order_id)");
				this.baseDao.executeSql(sqlBuffer.toString());
			}
			
			this.baseDao.executeSql(sqlBuffer.toString());
			
			//TODO 初期 如果没有设置过的bom自有物料组默认为原来的物料组
			sqlBuffer=new StringBuffer();
			sqlBuffer.append("update zg_t_orderbom t set t.matkl_self=t.matkl where  t.sortf in ('ABC','ABE','ABD') and t.matkl_self is null ");
			this.baseDao.executeSql(sqlBuffer.toString());
		}
		
		
	}


	/**
	 * 拆单 合单
	 * 假如原来 只有一张订单(w01)，现在过来两张(w02,w03)，则直接拆单,把原来的w01改成w02并改需求数量,然后亲增一条w03
	 * 
	 * 如果原来是w01,w02,w03:
	 * 		只过来w01,则w02,w03做删除处理，w01需求数量改变
	 * 		过来w02,w04,w05 则先找出相同的w02，然后w01变w04,w03变w05
	 * 
	 * @param aufnr
	 * @param pxDateStr
	 * @param orderCou
	 */
	public void doProdessPxOrder(int batchNo,String aufnr,String plant, String pxDateStr, Long sapCou) {
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doProdessPxOrder aufnr:"+aufnr+" pxdateStr:"+pxDateStr+" count:"+sapCou);
		}
		
		plant=StringHelper.isEmpty(plant)?"null":plant;
		
		HandlerOrderServiceImpl handlerOrderServiceImpl=getHandlerOrderServiceImpl();
		//查询RFID系统中的订单信息
		List<ZgTorder> rfidOrderList = getHandlerOrderServiceImpl().getRfidOrderListByAufnrPlant(aufnr,plant);
		
		//查询SAP此次过来的订单信息
		List<ZgTorderTemp> sapOrderList =getHandlerOrderServiceImpl().getSapOrderListByAufnrPlant(aufnr,plant,batchNo);
		
		//===================处理订单排产数据的插入 ,处理没有过来排产数据直接过来排序数据的那种 及更新order信息====
		String orderId=getHandlerOrderServiceImpl().doProcessPcdate(batchNo, aufnr, rfidOrderList, sapOrderList);
		//=============================================================================
		
		if(plant.equals("null")&&rfidOrderList.size()==0){//工单变更接口 工厂为空 刚只插入orderbom表
			//处理新增物料 处理
			int addRow=getHandlerOrderServiceImpl().doBgAddBomData(batchNo, aufnr, "", orderId);
			return;
		}
		
		//===================处理订单排序的插入==========================================
		String sortf=getPlantSortfMap().get(plant);
		String sapIsDoArbpls="";
		
		//更新ORDER表
		
		//A、找两边生产线生产厂都存在的订单，这种只需要更新需求数量
		for(ZgTorderTemp orderTemp:sapOrderList){//测试过 查找RFID,SAP两边数据都存在的排序数据，进行更新
			for(ZgTorder order:rfidOrderList){
				if(order.getArbpl().equals(orderTemp.getArbpl())){//测试过
//					if(!orderTemp.getPmenge().equals(order.getPmenge())){
						//排序数量不等，则需要更新排序数量
						order.setArbpl(orderTemp.getArbpl());
						order.setPxdat(orderTemp.getPxdat());
						order.setPmenge(orderTemp.getPmenge());
						getZgTorderTaskBo().updateZgtorderTaskByOrder(order,ZgTorderTask.NUMULSTATE);
//					}
					
					//处理新增物料 处理
					int addRow=getHandlerOrderServiceImpl().doPxAddBomData(batchNo, aufnr, plant, order);
					
					//处理修改的物料
					int editRow = getHandlerOrderServiceImpl().doPxEditBomData(batchNo, aufnr, plant, order);
					
					//处理删除的物料
					int delRow=getHandlerOrderServiceImpl().doPxDelBomData(batchNo, aufnr, plant, order);
					
					//该生产线加入已经处理标识 
					sapIsDoArbpls=sapIsDoArbpls+","+order.getArbpl();
					
					if(addRow>0||editRow>0||delRow>0){
						getZgTorderbomExBo().doZgtorderProcess(order.getTaskId(),"task");
					}
					break;
				}
			}
			
			//计算领料进度
		}
		
		//B、处理SAP中存在，但是持RFID中不存在的排序数据
		String rfidIsDoArbpls=sapIsDoArbpls;
		for(ZgTorderTemp orderTemp:sapOrderList){// 查找SAP过来的数据中没有处理的部分，随机改成在rfid中没有处理的部分
			if(!sapIsDoArbpls.contains(orderTemp.getArbpl())){//查找SAP过来的数据中没有处理的部分
				sapIsDoArbpls=sapIsDoArbpls+","+orderTemp.getArbpl();//该生产线加入已经处理标识 
				
				boolean flag=false;
				for(ZgTorder order:rfidOrderList){
					if(!rfidIsDoArbpls.contains(order.getArbpl())){//测试过 随机改成在rfid中没有处理的部分
						
						order.setArbpl(orderTemp.getArbpl());
						order.setPxdat(orderTemp.getPxdat());
						order.setPmenge(orderTemp.getPmenge());
						getZgTorderTaskBo().updateZgtorderTaskByOrder(order,ZgTorderTask.NUMULSTATE);
						
						//处理新增物料
						int addRow=getHandlerOrderServiceImpl().doPxAddBomData(batchNo, aufnr, plant, order);
						
						//处理修改的物料
						int editRow=getHandlerOrderServiceImpl().doPxEditBomData(batchNo, aufnr, plant, order);
						
						//处理删除的物料
						int delRow=getHandlerOrderServiceImpl().doPxDelBomData(batchNo, aufnr, plant, order);
						
						//该生产线加入已经处理标识 
						sapIsDoArbpls=sapIsDoArbpls+","+order.getArbpl();
						
						rfidIsDoArbpls=rfidIsDoArbpls+","+order.getArbpl();//该生产线加入已经处理标识 
						flag=true;
						
						if(addRow>0||editRow>0||delRow>0){//重新计算领料进度
							getZgTorderbomExBo().doZgtorderProcess(order.getTaskId(),"task");
						}
						break;
						
						
					}
				}
				
				if(!flag){// 在rfid 中没有找到可以更新的订单，直接做插入处理
					//查找orderTask中是否存在 该记录，如果存在 则直接将之前记录的删除标志设回正常即可
					ZgTorderTask task=getZgTorderTaskBo().findTaskByAufnrArbplPlant(orderTemp.getAufnr(),orderTemp.getArbpl(),orderTemp.getPlant());
					
					if(task==null){//测试过
						//插入ordertask
						orderTemp.setCuid(orderId);
						String taskId=getZgTorderTaskBo().saveZgtorderTask(orderTemp);
						//插入taskBom
						getZgTorderbomBo().saveZgtorderTaskBomByOrderIdTaskId(orderId,taskId,sortf,orderTemp.getArbpl(),orderTemp.getPlant(),batchNo);
					}else {//测试过 原来已经有排序数据，则恢复删除标识就可以了
						task.setPxDate(orderTemp.getPxdat());
						task.setPmenge(orderTemp.getPmenge());
						task.setFlag(ZgTorderTask.NUMULSTATE);
						getZgTorderTaskBo().update(task);
						
						ZgTorder order=new ZgTorder();
						order.setArbpl(orderTemp.getArbpl());
						order.setCuid(orderId);
						order.setTaskId(task.getCuid());
						
						//处理新增物料
						getHandlerOrderServiceImpl().doPxAddBomData(batchNo, aufnr, plant, order);
						
						//处理修改的物料
						getHandlerOrderServiceImpl().doPxEditBomData(batchNo, aufnr, plant, order);
						
						//处理删除的物料
						getHandlerOrderServiceImpl().doPxDelBomData(batchNo, aufnr, plant, order);
						
						
					}
				}
				
			}
		}
		
		//C、处理RFID在还没有被 处理的排序数据
		for(ZgTorder order:rfidOrderList){
			if(!rfidIsDoArbpls.contains(order.getArbpl())){//测试过 处理RFID在还没有被 处理的排序数据 这部分数据做直接删除 退料处理 
				
				
				//更新planbom需求量为0，并产生退料数据
				String planType=getPlantSortfMap().get(plant);
				ZgTorderPlan plan=getZgTorderPlanBo().getPlanByTaskIdPlanType(order.getTaskId(),planType);
				
				boolean isStartCar=getHandlerOrderServiceImpl().isStartCar(plan.getCuid());
				if(isStartCar){//已经开始领料，则产生退料数据
					//更新ordertask 需求量为0
					order.setPmenge(0l);
					getZgTorderTaskBo().updateZgtorderTaskByOrder(order,ZgTorderTask.DELSTATE);
					
					//更新taskbom需求量为0
					getZgTorderTaskbomBo().updateTaskBomMengeTO0ByTaskId(order.getTaskId());
					
					// 更新plan需求量为0 并产生退料数据
					getZgTorderPlanbomBo().updatePlanBomCarNumTo0ByPlanId(plan.getCuid());
					
				}else {//没有开始领料的，直接做删除处理
					getZgTorderTaskBo().removeById(order.getTaskId());
					
					//更新taskbom需求量为0
					getZgTorderTaskbomBo().delTaskBomTaskId(order.getTaskId());
					
					// 更新plan需求量为0 并产生退料数据
					if(plan!=null){
						getZgTorderPlanbomBo().delPlanBomPlanId(plan.getCuid());
						getZgTorderPlanBo().removeById(plan.getCuid());
					}
				}
				
				
				getZgTorderbomExBo().doZgtorderProcess(order.getTaskId(),"task");
				
			}
		}
		
		//=============================================================================
		
		
		
	}
	
	/* 
	 * 处理的字段信息有：zgtorder pcdat psmng arbpl
	 * 	物料的添加和减少
	 * 
	 * arbpl(排产线):排序接口里arbpl1是排产线体，排产接口里arbpl是排产线体
	 */
	public void doProdessChangeOrder(int batchNo, String aufnr,ZgTorderTemp sapOrder) {
		
		//获取rfid这边的生产订单信息
		ZgTorder rfidTorder=new ZgTorder();
		rfidTorder.setAufnr(aufnr);
		rfidTorder=getZgTorderBo().findByPropertyUnique(rfidTorder);
		
		//更新订单信息
		sapOrder.setCuid(rfidTorder.getCuid());
		sapOrder.setArbpl1(sapOrder.getArbpl());
		getZgTorderTempBo().updateZgTOrder(sapOrder);
		
		//对比bom并处理
		//处理新增物料 处理
		getHandlerOrderServiceImpl().doChangeAddBomData(batchNo, aufnr,rfidTorder.getCuid());
		
		//处理修改BOM数量
		getHandlerOrderServiceImpl().doPcEditBomData(batchNo, aufnr,rfidTorder.getCuid());
		
		//处理删除的物料
		ZgTorder orderTorder=new ZgTorder();
		orderTorder.setCuid(rfidTorder.getCuid());
		int delRow=getHandlerOrderServiceImpl().doPxDelBomData(batchNo, aufnr,"",orderTorder);
		
		
		if(delRow>0){
			getZgTorderbomExBo().doZgtorderProcess(rfidTorder.getCuid(),"order");
		}
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
	 * 生成领料计划
	 * 处理逻辑：　判断之前是否已经生成相应的领料计划，如果没有则生成，有则不处理
	 */
	public void generateCarPlan(int batchNo) {
		//遍历本批次的订单，生成领料计划
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select distinct  t.cuid orderId,t.order_state,task.aufnr,task.plant,task.arbpl,task.cuid  from zg_t_order_temp temp, zg_t_order t,zg_t_order_task task where temp.aufnr = t.aufnr ");
		sqlBuffer.append(" and temp.aufnr=task.aufnr   and temp.arbpl=task.arbpl   and temp.plant=task.plant  and t.cuid=task.order_id   and temp.batch_no ="+batchNo);
		List<Map> list = this.baseDao.queryBySql(sqlBuffer.toString());
		for(Map map:list){
			String taskId=map.get("CUID").toString();
			String orderId=map.get("ORDERID").toString();
			String plant=IbatisDAOHelper.getStringValue(map, "PLANT", "").trim();
			String orderState=IbatisDAOHelper.getStringValue(map, "ORDER_STATE");
			String aufnr=IbatisDAOHelper.getStringValue(map,"AUFNR");

			String sortf=getPlantSortfMap().get(plant);
			
			List<String> softList=new ArrayList<String>();
			softList.add(sortf);
			List listOrderPlan =null;

			try {
				listOrderPlan=this.baseDao.queryBySql("select  * from  zg_t_order_plan t where t.order_task_id='"+taskId+"'  and t.plan_type='"+sortf+"'");
			} catch (Exception e) {
				continue;
			}
			if(listOrderPlan.size()==0){//如果原来没有生成领料计划，则生成领料计划
				String planState="-1";
				if(Constants.OrderPlanStatus.SUBMIT.value().equals(orderState)){//订单是提交状态
					planState="0";
				}
				getZgTorderExBo().createPlan(orderId,taskId,plant,planState,Constants.isNotManulFinished);
			}
			
		}
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
	 * 插入订单
	 * @param posKey
	 */
	public String saveZgTorderByAufnr(String aufnr,int batchNo){
		StringBuffer insertBuffer = new StringBuffer();
	 	String orderId=(String) this.baseDao.getSqlMapClientTemplate().queryForObject("Base.selectGUID");
		
		insertBuffer.append("insert into ZG_T_ORDER(CUID,AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,LABEL_CN,PSBH)" +
					"select '"+orderId+"',AUFNR,ORDER_STATE,SUBMIT_USER,SUBMIT_DATE,MANDT,PXDAT,PLANT,MIPOS,PCDAT,ARBPL,ARBPL1,MATNR,KDAUF,KDPOS,KDTXT,ZCKPP,MAKTX2,MAKTX1,ZZCKS,ATWRT2,PSMNG,PMENGE,ZTXT02,ZDBLC,BRGEW2,CRDAT,CPUTM,CRNAM,MRNAM,ZMUZE,MNAME,FBDAT,FBUZE,FNAME,LABEL_CN,PSBH from ZG_T_ORDER_temp temp where  temp.aufnr ='"+aufnr+"'" +
							"  and temp.batch_no='"+batchNo+"' and rownum=1");
		this.baseDao.executeSql(insertBuffer.toString());
		return orderId;
	}



	/* (non-Javadoc)
	 * @see sap.service.HandlerSapDataService#doProdessPcOrder(int, com.boco.zg.plan.base.model.ZgTorderTemp)
	 */
	public void doProdessPcOrder(int batchNo, ZgTorderTemp temp) {
		
		ZgTorder order=new ZgTorder();
		order.setAufnr(temp.getAufnr());
		List<ZgTorder> list=getZgTorderBo().findByProperty(order);
		
		
		if(list.size()==0){//订单不存在则进行插入
			String orderId=saveZgTorderByAufnr(temp.getAufnr(),batchNo);
			addSapBomsDataByAufnr(batchNo, temp.getAufnr(),orderId);
		}else {//修改相关信息
			
			//修改订单信息
			String orderId=list.get(0).getCuid();
			temp.setCuid(orderId);
			temp.setArbpl1(temp.getArbpl());
			getZgTorderTempBo().updateZgTOrder(temp);
			
			//处理新增物料
			getHandlerOrderServiceImpl().doChangeAddBomData(batchNo, temp.getAufnr(),orderId);
			
			//处理修改BOM数量
			getHandlerOrderServiceImpl().doPcEditBomData(batchNo, temp.getAufnr(),orderId);
			
			//处理删除的物料
			ZgTorder orderTorder=new ZgTorder();
			orderTorder.setCuid(orderId);
			int delRow=getHandlerOrderServiceImpl().doPxDelBomData(batchNo, temp.getAufnr(),"",orderTorder);
			
			if(delRow>0){
				getZgTorderbomExBo().doZgtorderProcess(orderId,"order");
			}
			
		}
		
	}



	/* (non-Javadoc)
	 * @see sap.service.HandlerSapDataService#handlerOrderInfoSyn(int)
	 */
	public void handlerOrderInfoSyn( JCoTable synTable,int batchNo) {
		Map paramsMap=new HashMap();
		paramsMap.put("batchNo", batchNo);
		List<Map> orderList=this.baseDao.getSqlMapClientTemplate().queryForList("SapBom.OrderInfoSynByBatchNo",paramsMap);
		this.baseDao.getSqlMapClientTemplate().insert("SapBom.insertOrderInfoSynByBatchNo",paramsMap);
		int num=0;
		for(Map order:orderList){
			parseDataToJcoTable(synTable, num, order);
			num++;
		}
		
	
	}
	
	/**
	 * 把map里面的数据加到table中
	 * @param synTable
	 * @param num
	 * @param bom
	 */
	private void parseDataToJcoTable(JCoTable synTable, int num, Map bom) {
			
		synTable.appendRow();
		// 定位到第0行
		synTable.setRow(num);

		Set set = bom.keySet();
		for (Object key : set) {
			synTable.setValue(key.toString(), IbatisDAOHelper.getStringValue(bom, key.toString(), ""));

		}
	}



	
	
	
}
