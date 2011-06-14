package com.boco.zg.plan.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.util.StringHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import sap.LoadRequestProcessThread;
import sap.SapClient;

import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTcarbomDao;
import com.boco.zg.plan.base.dao.ZgTcarplanDao;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.dao.ZgTcarplanExDao;
import com.boco.zg.plan.model.ZgTcarbomEx;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.util.Constants;

import freemarker.template.utility.StringUtil;

@Component
public class ZgTcarplanExBo extends ZgTcarplanBo{
	
	private static Log log=LogFactory.getLog(ZgTcarplanExBo.class);
	
	private ZgTcarplanBo zgTcarplanBo;
	
	private ZgTcarplanExDao  zgTcarplanExDao;
	
	private SapClient getSapClient() {
		return (SapClient)ApplicationContextHolder.getBean("sapClient");
	}
	
	
	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}
	
	private ZgTcarbomBo zgTcarbomBo;
	
	private ZgTcarbomExBo zgTcarbomExBo;
	
	private ZgTorderPlanExBo zgTorderPlanExBo;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private ZgTorderPlanGroupExBo zgTorderPlanGroupExBo;
	
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}
	
	private ZgTorderPlanbomBo zgTorderPlanbomBo;
	

	public void setZgTorderPlanbomBo(ZgTorderPlanbomBo zgTorderPlanbomBo) {
		this.zgTorderPlanbomBo = zgTorderPlanbomBo;
	}


	public void carPlanSubmit(String carPlanId) {
		ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
		storePlanSubmitForBatch(zgTcarplan);
	}
	
	
	
	/**
	 * 仓库领料计划提交
	 * 处理逻辑：
	 * 		根据仓库领料计划id获取其bom组件，遍历
	 * 		orderplanbom表:state由9变为8，开锁  
	 * 		更改 carplan:type变为2(变成领料计划) 状态置为0（未处理状态）   
	 * @param carPlanId
	 */
	public void carPlanSubmitByID(String carPlanId) {
		ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
		ZgTcarbom param = new ZgTcarbom();
		param.setCarPlanId(zgTcarplan.getCuid());
		List<ZgTcarbom> carbomList = zgTcarbomBo.findByProperty(param);
		for(ZgTcarbom carbom : carbomList) {
			String orderPlanbomId = carbom.getOrderPlanbomId();
			ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(orderPlanbomId);
			planbom.setState(Constants.CarPlanStatus.SUBMIT.value());
			zgTorderPlanbomBo.update(planbom);
		}
		zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
		zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
		zgTcarplanBo.update(zgTcarplan);
	}



	/**
	 * 仓库领料计划提交
	 * 处理逻辑：
	 * 		根据仓库领料计划id获取其bom组件，遍历
	 *      orderplanbom表:plan_num=plan_num-(c_plan_num-real_num)；（注：c_plan_num为仓库领料的计划数量，real_num实际数量）
	 *                     complete=complete+real_num 注：complete 为领料计划已经领取数量
	 *                    state：如果complete=carNum则为9已完成 否则为8
	 *      carplan表：状态改为已提交  
	 *      modify by wengqin 2011/05/12 同一个装车计划分多次刷卡，选哪个物料刷哪些物料的装车数量 如果领料计划的物料没有刷卡完，则不计算领料进度
	 * @param carPlanId
	 *  @param storageUserId
	 *   @param orderPlanBomIds 本次需要确认领料的物料
	 *   返回值：该装车计划是否已经全部物料领完
	 */
	public boolean storagePlanSubmitById(String carPlanId,String storageUserId,String orderPlanBomIds,String planType) {
		ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
		ZgTcarbom param = new ZgTcarbom();
		param.setCarPlanId(zgTcarplan.getCuid());
		param.setStorageUserId("null");
		List<ZgTcarbom> carbomList = zgTcarbomBo.findByProperty(param);
		boolean flag=true;
		for(ZgTcarbom carbom : carbomList) {
			String orderPlanbomId = carbom.getOrderPlanbomId();
			if(orderPlanBomIds.contains(orderPlanbomId)||orderPlanBomIds.equals("ALL")){//选哪个物料刷哪些物料的装车数量
				ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(orderPlanbomId);
				Long carNum = planbom.getCarNum()==null?new Long(0):planbom.getCarNum();
				Long planNum = planbom.getPlanNum()==null?new Long(0):planbom.getPlanNum();
				Long completeNum = planbom.getCompleteNum()==null?new Long(0):planbom.getCompleteNum();
				Long waitBackNum=planbom.getWaitBackNum()==null?new Long(0):planbom.getWaitBackNum();
				Long backNum=planbom.getBackNum()==null?new Long(0):planbom.getBackNum();
				
				if(Constants.OrderPlanType.BACK.value().equals(planType)){//退料的更新退料数量
					Long realNum = carbom.getRealNum()==null?new Long(0):carbom.getRealNum();
					Long cPlanNum = carbom.getPlanNum()==null?new Long(0):carbom.getPlanNum();
					waitBackNum=waitBackNum-realNum;
					backNum=backNum+realNum;
					planbom.setWaitBackNum(waitBackNum);
					planbom.setBackNum(backNum);
					planbom.setStorageNum(planbom.getStorageNum()-realNum);
					
					
					if(carNum <= completeNum) {
						planbom.setFinishTime(Calendar.getInstance().getTime());
						planbom.setState(Constants.CarPlanStatus.DONE.value());
					}
					zgTorderPlanbomBo.update(planbom);
				}else {//其他的更新　领料数量
					Long realNum = carbom.getRealNum()==null?new Long(0):carbom.getRealNum();
					Long cPlanNum = carbom.getPlanNum()==null?new Long(0):carbom.getPlanNum();
					planNum = planNum - (cPlanNum - realNum);
					completeNum = completeNum + realNum;
					planbom.setPlanNum(planNum);
					planbom.setCompleteNum(completeNum);
					planbom.setStorageNum(planbom.getStorageNum()+realNum);
					if(carNum <= completeNum) {
						planbom.setFinishTime(Calendar.getInstance().getTime());
						planbom.setState(Constants.CarPlanStatus.DONE.value());
					}
					zgTorderPlanbomBo.update(planbom);;
				}
				
				
				
				//更新仓库管理员
				carbom.setStorageUserId(storageUserId);
				carbom.setCarDate(Calendar.getInstance().getTime());
				zgTcarbomBo.update(carbom);
				
			}else {
				flag=false;
			}
			
		}
		//如果没有领完料，不用更新领料计划状态
		if(flag==true){
			zgTcarplan.setCarState(Constants.CarPlanStatus.SUBMIT.value());
			zgTcarplan.setCarDate(Calendar.getInstance().getTime());
			zgTcarplan.setStorageUserId(storageUserId);
			zgTcarplanBo.update(zgTcarplan);
		}
		return flag;
		
	}
	

	/**
	 * 方法描述：批量仓库领料
	 * 处理逻辑：更新计划领料bom的实际领取数量
	 *         设置计划领料bom的状态为已完成状态
	 * @param zgTcarplan
	 */
	private void storePlanSubmitForBatch(ZgTcarplan zgTcarplan) {
		ZgTcarbom param = new ZgTcarbom();
		param.setCarPlanId(zgTcarplan.getCuid());
		List<ZgTcarbom> carbomList = zgTcarbomBo.findByProperty(param);
		for(ZgTcarbom carbom : carbomList) {
			String orderPlanbomId = carbom.getOrderPlanbomId();
			ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(orderPlanbomId);
			
			
			Long realNum = carbom.getRealNum()==null?new Long(0):carbom.getRealNum();
			Long cPlanNum = carbom.getPlanNum()==null?new Long(0):carbom.getPlanNum();
			Long planNum = cPlanNum;
			planbom.setPlanNum(planNum);
			planbom.setCompleteNum(realNum);
			planbom.setStorageNum(realNum);
			
			planbom.setState(Constants.CarPlanStatus.DONE.value());
			zgTorderPlanbomBo.update(planbom);
		}
//		zgTcarplan.setCarState(Constants.CarPlanStatus.SUBMIT.value());
//		zgTcarplanBo.update(zgTcarplan);
		
	}

	/**
	 * 根据领料人id获取该领料人的领料仓库列表
	 * @param userId
	 * @return
	 */
	public List<Map> getLgortListByUserId(String userId) {
		String sql="select distinct fw.cuid,fw.label_cn from zg_t_carplan t,fw_organization fw where  t.type='2' and t.car_state<>'8' and t.car_user='"+userId+"' and t.storage_id=fw.cuid";
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql);
		
	}
	
	/**
	 * 为仓库领料计划设定仓管员
	 * @param carPlanId
	 * @param storageUserId
	 * @return
	 */
	public boolean updateCarPlanStorageUserId(String carPlanId,String storageUserId){
		ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
		zgTcarplan.setStorageUserId(storageUserId);
		zgTcarplanBo.update(zgTcarplan);
		return false;
	}


	/**
	 * 根据人员id获取譔领料员的计划装车bom列表
	 * @param planType  预装 总装 预焊类型(ABD/ABE/ABC)
	 * @param sessionOperatorId
	 * @return
	 */
	public List<Map> getBomLIstByUserId(String planType, String operatorId,String lgort) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct taskbom.cuid taskbom_id ,taskbom.ORDER_TASK_ID orderTaskId,car.cuid car_Plan_Id,carbom.cuid,orderbom.idnrk,    ORDERBOM.ZDTYL,orderbom.ORDER_ID,");//修改数据时需要参数
		sql.append(" 	orderbom.maktx2,");
		sql.append("	orderbom.cuid as order_bom_id, planbom.cuid as order_planbom_id,");   //修改数据时需要参数
		sql.append(" 	carbom.car_plan_id,");   //修改数据时需要参数
		sql.append(" 	orderbom.aufnr,");
		sql.append(" 	orderbom.zdtyl,");
		sql.append(" 	taskbom.menge car_num,");//呈现：需求数量
		sql.append(" 	nvl(planbom.plan_num,0) plan_num,");
		sql.append(" 	nvl(planbom.complete_num,0) complete_num,");//呈现：完成数量
		sql.append(" 	nvl(planbom.wait_back_num,0) wait_back_num,");//呈现：完成数量
		sql.append(" 	nvl(planbom.back_num,0) back_num,");
		sql.append(" 	carbom.plan_num car_plan_num,");//呈现：计划领取数量
		if(Constants.OrderPlanType.BACK.value().equals(planType)){
			sql.append(" 	nvl(planbom.wait_back_num,0) max_value,");
		}else {
			sql.append("	decode(sign(planbom.car_num - planbom.complete_num), -1, 0,  ");
			sql.append("	 decode(sign((planbom.car_num - planbom.complete_num) - (nvl(planbom.car_num, 0) -  nvl(planbom.plan_num, 0) + carbom.plan_num)),");
			sql.append("	 -1, planbom.car_num - planbom.complete_num,  nvl(planbom.car_num, 0) - nvl(planbom.plan_num, 0) +  carbom.plan_num)   ) max_value,");//呈现：本次需求数量
		}
		
		sql.append("     bom.carnum carCount,orderbom.zbz,carbom.storage_user_id, od.aufnr,  od.arbpl,     od.kdauf,     od.kdpos,  od.maktx1 ");
		sql.append(" from zg_t_carplan       car, ");
		sql.append("	zg_t_carbom        carbom, ");
		sql.append("	zg_t_order_planbom planbom, ");
		sql.append("	zg_t_orderbom      orderbom , zg_t_bom bom,zg_t_order_taskbom taskbom,zg_t_order od");
		sql.append(" where od.cuid=orderbom.order_id and car.car_user = '"+operatorId+"' ");
		sql.append("	and car.car_state <> '8' ");
		sql.append("	and car.cuid = carbom.car_plan_id ");
		sql.append("	and carbom.order_planbom_id = planbom.cuid ");
		sql.append("	and carbom.taskbom_id=taskbom.cuid  and taskbom.order_bom_id=orderbom.cuid  and bom.idnrk = orderbom.idnrk   and car.ORDER_PLAN_TYPE='"+planType+"'");
		sql.append("	and car.storage_id='"+lgort+"' and car.ismanul='1'");

		  
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	

	/**
	 * 根据bom编号获取 bom信息列表 
	 * @param bomCuids
	 * @return
	 */
	public List<Map> getBomListByBomIds(String bomCuids,String operatorId) {
		bomCuids=bomCuids.replace(",", "','");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select distinct null as cuid,    ORDERBOM.ZDTYL,orderbom.ORDER_ID,");
		sql.append("orderbom.cuid order_bom_id,");
		sql.append("orderbom.idnrk,");
		sql.append(" orderbom.maktx2,");
		sql.append("planbom.cuid as order_planbom_id,");
		sql.append("null as car_plan_id,");
		sql.append(" 	taskbom.menge car_num,");//呈现：需求数量
		sql.append("nvl(planbom.plan_num,0) plan_num,");
		sql.append("nvl(planbom.complete_num,0) complete_num,");
		sql.append("0 as car_plan_num,");
		sql.append(" planbom.car_num-nvl(planbom.plan_num,0)   max_value,");
		sql.append("bom.carnum carCount,taskbom.cuid taskbom_id,taskbom.order_task_id orderTaskId, od.aufnr,  od.arbpl,     od.kdauf,     od.kdpos,  od.maktx1");
		sql.append(" from zg_t_order_planbom planbom, zg_t_orderbom orderbom, zg_t_bom bom,zg_t_order_taskbom taskbom,zg_t_order od ");
		sql.append(" where orderbom.order_id=od.cuid and  planbom.taskbom_id=taskbom.cuid         and taskbom.order_bom_id=orderbom.cuid and  planbom.car_num-nvl(planbom.plan_num,0)>0 ");
		sql.append("   and bom.idnrk = orderbom.idnrk    ");
		sql.append(" and taskbom.cuid in ('"+bomCuids+"') ) w where w.taskbom_id not in(");
		sql.append(" select carbom.taskbom_id from zg_t_carplan plan,zg_t_carbom carbom       where plan.car_user='"+operatorId+"' ");
		sql.append("     and plan.car_state='0'        and plan.cuid=carbom.car_plan_id   )");

		
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	/**
	 * 根据bom编号获取 bom信息列表 
	 * @param bomCuids
	 * @return
	 */
	public List<Map> getBomListByBomIds1(String bomCuids,String operatorId,String planType) {
		bomCuids=bomCuids.replace(",", "','");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select distinct null as cuid,    ORDERBOM.ZDTYL,orderbom.ORDER_ID,");
		sql.append("orderbom.cuid order_bom_id,");
		sql.append("orderbom.idnrk,");
		sql.append(" orderbom.maktx2,");
		sql.append("planbom.cuid as order_planbom_id,");
		sql.append("null as car_plan_id,");
		sql.append(" 	taskbom.menge car_num,");//呈现：需求数量
		sql.append("nvl(planbom.plan_num,0) plan_num,");
		sql.append("nvl(planbom.complete_num,0) complete_num,");
		sql.append(" 	nvl(planbom.wait_back_num,0) wait_back_num,");//呈现：完成数量
		sql.append(" 	nvl(planbom.back_num,0) back_num,");
		sql.append("0 as car_plan_num,");
		if(Constants.OrderPlanType.BACK.value().equals(planType)){
			sql.append(" 	nvl(planbom.wait_back_num,0) max_value,");
		}else {
			sql.append(" planbom.car_num-nvl(planbom.plan_num,0)   max_value,");
		}
		sql.append("bom.carnum carCount,taskbom.cuid taskbom_id,taskbom.order_task_id orderTaskId, od.aufnr,  od.arbpl,     od.kdauf,     od.kdpos,  od.maktx1");
		sql.append(" from zg_t_order_planbom planbom, zg_t_orderbom orderbom, zg_t_bom bom,zg_t_order_taskbom taskbom,zg_t_order od ");
		sql.append(" where orderbom.order_id=od.cuid and  planbom.taskbom_id=taskbom.cuid         and taskbom.order_bom_id=orderbom.cuid " );
		if(Constants.OrderPlanType.BACK.value().equals(planType)){
			sql.append(" and nvl(planbom.wait_back_num,0)>0 ");
		}else {
			sql.append(" and  planbom.car_num-nvl(planbom.plan_num,0)>0 ");
		}
				
		sql.append("   and bom.idnrk = orderbom.idnrk    ");
		sql.append(" and taskbom.cuid in ('"+bomCuids+"') ) w where w.taskbom_id not in(");
		sql.append(" select carbom.taskbom_id from zg_t_carplan plan,zg_t_carbom carbom       where plan.car_user='"+operatorId+"' ");
		sql.append("     and plan.car_state='0'        and plan.cuid=carbom.car_plan_id   )");

		
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
	}


	/**
	 * 根据领料员编号,及其目前要装车的仓库获取该领料员的领料计划
	 * @param operatorId
	 * @return
	 */
	public List<Map> getCarPlanByUserId(String operatorId,String planType,String lgort) {
		StringBuffer sql=new StringBuffer();
		
		sql.append("select distinct t.*,car.cuid carId,car.code,car.label_cn,t.storage_id lgort,org.label_cn lgortName from zg_t_carplan t,zg_carinfo car,fw_organization org,zg_t_carbom bom   where  t.storage_id=org.cuid and t.cuid=bom.car_plan_id(+)    and t.car_id=car.cuid and t.car_user='"+operatorId+"' and t.car_state<>'8' and t.ORDER_PLAN_TYPE='"+planType+"'");
		if(!StringHelper.isEmpty(lgort)){
			sql.append(" and t.storage_id='"+lgort+"'");
		}
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	/**
	 * 根据领料员编号获取该领料员的领料计划
	 * @param operatorId
	 * @return
	 */
	public List<Map> getCarPlanByUserId1(String operatorId,String planType) {
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct t.*,task.arbpl,car.cuid carId,car.code,car.label_cn,t.storage_id lgort,org.label_cn lgortName from zg_t_carplan t,zg_carinfo car,fw_organization org,zg_t_carbom bom ,zg_t_order_task task where  t.storage_id=org.cuid and t.cuid=bom.car_plan_id(+)   and bom.order_task_id=task.cuid(+) and t.car_id=car.cuid and t.car_user='"+operatorId+"' and t.car_state<>'8' and t.ORDER_PLAN_TYPE='"+planType+"'");
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	

	/**
	 * 根据领料员编号判断他是否具有没有领取的领料任务
	 * @param operatorId
	 * @return
	 */
	public boolean checkHasCarPlan(String operatorId,String planType) {
		StringBuffer sql=new StringBuffer();
		sql.append("select 1  from zg_t_carplan t where t.car_user = '"+operatorId+"'   and t.car_state <> '8'   and t.ORDER_PLAN_TYPE = '"+planType+"' ");

		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString()).size()>0;
	}


	public String getSysGuid() {
		List<Map> list=((ZgTcarplanDao)this.getEntityDao()).findDynQuery("SELECT to_char(SYSTIMESTAMP,'yyyymmddhhmissff') guid FROM DUAL");
		return list.get(0).get("GUID").toString();
	}


	public Object save1(ZgTcarplan zgTcarplan) {
		return zgTcarplanExDao.save1(zgTcarplan);
	}


	public ZgTcarplanExDao getZgTcarplanExDao() {
		return zgTcarplanExDao;
	}


	public void setZgTcarplanExDao(ZgTcarplanExDao zgTcarplanExDao) {
		this.zgTcarplanExDao = zgTcarplanExDao;
	}


	/**
	 * 保存装车计划
	 * 	1 如果之前没有装车计划 则生成一张装车计划表
	 *  2 装车计划bom分两类处理
	 *  	原来已经存在的bom
	 *  	本次新追加的bom
	 * @param carbomList
	 * @param operatorInfo
	 */
	public void generateCarPlan(String[] items,List<ZgTcarbomEx> carbomList,OperatorInfo operatorInfo,String planType) {
			int num=0;
			
			
			//<!--modify by wengqin 2011/05/12 同一个装车计划分多次刷卡，选哪个物料刷哪些物料的装车数量- 如果领料计划的物料没有刷卡完，则不计算领料进度
			String orderPlanBomIds="";
			for(String item:items){
				Hashtable params = HttpUtils.parseQueryString(item);
				orderPlanBomIds=orderPlanBomIds+"'"+params.get("orderPlanbomId")+"',";
			}
			if(orderPlanBomIds.length()>0){
				orderPlanBomIds=orderPlanBomIds.substring(0,orderPlanBomIds.length()-1);
			}
			//-->
			
			List<ZgTcarbomEx> editList=new ArrayList<ZgTcarbomEx>();
			List<ZgTcarbomEx> newList=new ArrayList<ZgTcarbomEx>();
			if(carbomList==null) return;
			for(ZgTcarbomEx bom:carbomList){
				
				//插入装车计划表
				if(num==0){
					ZgTcarplan zgTcarplan=(ZgTcarplan)getById(bom.getCarPlanId());
					if(null==zgTcarplan){//装车计划还没有生成，生成装车计划
						zgTcarplan=new ZgTcarplan();
						zgTcarplan.setCuid(bom.getCarPlanId());
						zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
						zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
						zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
						zgTcarplan.setOrderPlanType(planType);
						zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
						zgTcarplan.setCarUser(operatorInfo.getOperatorId());
						zgTcarplan.setCarId(bom.getCarId());
						zgTcarplan.setStorageId(bom.getLgort());
						zgTcarplan.setIsManul(Constants.isNotManulFinished);
						save1(zgTcarplan);
					}
					
					
					//删除原来的装车计划bom表
					//zgTcarbomExBo.deleteBomByCarPlanId(bom.getCarPlanId());
					num++;
				}

				if(StringHelper.isEmpty(bom.getCuid())){
					if(orderPlanBomIds.contains(bom.getOrderPlanbomId())){//只有鈗选的才做处理
						newList.add(bom);
					}
					
				}else {
					if(!orderPlanBomIds.contains(bom.getOrderPlanbomId())){//只有鈗选的才做处理//之前有的现在没有勾选　中，则说明不用做装车计划，直接从计划中删除
						if(log.isInfoEnabled()){
							log.info("保存领料计划时　领料计划删除BOM：orderplabbomid:"+bom.getOrderPlanbomId()+"  carbomId:"+bom.getCuid());
						}
						deleteBom(bom.getCuid(),bom.getOrderPlanbomId());
					}else {
						editList.add(bom);
					}
					
				}
			}
			zgTcarbomExBo.saveEditCarPlan(editList, Constants.CarPlanStatus.NEW.value(),planType);
			zgTcarbomExBo.saveNewCarPlan(newList, Constants.CarPlanStatus.SUBMIT.value(),planType);
			
			//改变
		
	}


	public ZgTcarbomExBo getZgTcarbomExBo() {
		return zgTcarbomExBo;
	}


	public void setZgTcarbomExBo(ZgTcarbomExBo zgTcarbomExBo) {
		this.zgTcarbomExBo = zgTcarbomExBo;
	}


	/**
	 * 取消装车订单
	 * 处理逻辑：
	 *  	删除装车计划及装车计划bom表
	 *  	恢复领料计划bom中的计划领取数量
	 * @param planType ABC/ABD/ABE(预焊/预装/总装)
	 * @param operatorInfo
	 */
	public void cancleCarPlan(OperatorInfo operatorInfo,String planType,String lgort) {
		//获取装车bom列表
		List<Map> bomList=getBomLIstByUserId(planType,operatorInfo.getOperatorId(),lgort);
		if(bomList != null && bomList.size()>0) {
			String carPlanId = bomList.get(0).get("CAR_PLAN_ID").toString();
			zgTcarplanBo.deleteCarPlan(carPlanId);
		}if(bomList.size()==0){//则装车单没有BOM直接删除装车单
			zgTcarplanBo.deleteCarByOperatorId(planType,operatorInfo.getOperatorId());
		}
	}


	/**
	 * 仓库管理员确认领料
	 * 	   0 如果还没有生成装计划，则先生成
	 *     1 先可在新追加的bom到zgtcarbom表
	 *     2 设定仓管员
	 *     3 更新carbom表的数量 
	 *     4 更新planbom表的数量及carplan表的状态
	 *     5 更新领料计划表的状态及领料进度
	 *     6 更新领料计划组表的状态及领料进度
	 *     
	 *     modify by wengqin 2011/05/12 同一个装车计划分多次刷卡，选哪个物料刷哪些物料的装车数量 如果领料计划的物料没有刷卡完，则不计算领料进度
	 * @param items 识别哪个物料勾选 
	 * @param carbomList
	 * @param operatorInfo
	 * @param storageUserId 仓管员id
	 */
	public String  confirmCarPlan(String[] items,List<ZgTcarbomEx> carbomList,OperatorInfo operatorInfo,String storageUserId,String planType) {
		String result="noTurn";
		
		//<!--modify by wengqin 2011/05/12 同一个装车计划分多次刷卡，选哪个物料刷哪些物料的装车数量- 如果领料计划的物料没有刷卡完，则不计算领料进度
		String orderPlanBomIds="";
		for(String item:items){
			Hashtable params = HttpUtils.parseQueryString(item);
			orderPlanBomIds=orderPlanBomIds+"'"+params.get("orderPlanbomId")+"',";
		}
		if(orderPlanBomIds.length()>0){
			orderPlanBomIds=orderPlanBomIds.substring(0,orderPlanBomIds.length()-1);
		}
		//-->
		
		
		List<ZgTcarbomEx> newList=new ArrayList<ZgTcarbomEx>();
		
		String carPlanId="";
		if(carbomList.size()>0){
			carPlanId=carbomList.get(0).getCarPlanId();
			int num=0;
			for(ZgTcarbomEx bom:carbomList){
				//插入装车计划表
				if(num==0){
					ZgTcarplan zgTcarplan=(ZgTcarplan)getById(bom.getCarPlanId());
					if(null==zgTcarplan){//装车计划还没有生成，生成装车计划
						zgTcarplan=new ZgTcarplan();
						zgTcarplan.setCuid(bom.getCarPlanId());
						zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
						zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
						zgTcarplan.setCarDate(Calendar.getInstance().getTime());
						zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
						zgTcarplan.setOrderPlanType(planType);
						zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
						zgTcarplan.setCarUser(operatorInfo.getOperatorId());
						zgTcarplan.setCarId(bom.getCarId());
						zgTcarplan.setStorageId(bom.getLgort());
						zgTcarplan.setIsManul(Constants.isNotManulFinished);
						save1(zgTcarplan);
					}
					num++;
				}
					
				if(StringHelper.isEmpty(bom.getCuid())){
					if(orderPlanBomIds.contains(bom.getOrderPlanbomId())){
						newList.add(bom);
					}
				}
			}
			
			//1 先可在新追加的bom到zgtcarbom表
			zgTcarbomExBo.saveNewCarPlan(newList, Constants.CarPlanStatus.SUBMIT.value(),planType);
			//2 更新carbom表的数量 
			zgTcarbomExBo.updateCarboms(orderPlanBomIds,carbomList,false);
			//3 设定仓管员
//			updateCarPlanStorageUserId(carPlanId, storageUserId);
			//4 更新planbom表的数量及carplan表的状态 及仓管员信息
			boolean isCarPlanFinished=storagePlanSubmitById(carPlanId,storageUserId,orderPlanBomIds,planType);
			
			if(isCarPlanFinished){//该装车计划已经领完，则进行相应的状态变化，计划进度
				if(Constants.OrderPlanType.BACK.value().equals(planType)){
					result = doProcessPlanByCarPlanId1(carPlanId,planType);
				}else {
					result = doProcessPlanByCarPlanId(carPlanId);
				}
				
			}
			
			
			
			
		}
		return result;
	}


	/**
	 * 根据装车计划编号计算领料计划进度
	 * @param result
	 * @param carPlanId
	 * @return
	 */
	public String doProcessPlanByCarPlanId( String carPlanId) {
		
		ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
		if(zgTcarplan==null) return "noTurn";
		zgTcarplan.setCarState(Constants.CarPlanStatus.SUBMIT.value());
		zgTcarplan.setCarDate(Calendar.getInstance().getTime());
		zgTcarplanBo.update(zgTcarplan);
		
		String result="noTurn";
		//获取本次装车计划所涉及的订单
		List<ZgTorderPlan> planList=zgTorderPlanExBo.getOrderPlanListByCarPlanId(carPlanId);
		List<ZgTorderPlanGroup> planGrouList=zgTorderPlanGroupExBo.getPlanGroupListByCarPlanId(carPlanId);
		
		//5 更新领料计划表的状态及领料进度
		for(ZgTorderPlan plan:planList){
			String state=zgTorderPlanExBo.getState(plan.getCuid());
			plan.setState(state);
//			if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())){
//				plan.setState(Constants.OrderPlanStatus.SAVE.value());
//			}
		
			double percent=zgTorderPlanExBo.getPercent(plan.getCuid());
			if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
				if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
					percent=0.99;
				}
			}
			plan.setPercent(percent);
			zgTorderPlanBo.update(plan);
			
			if(state.equals(Constants.OrderPlanStatus.FINISHED.value())){//领料计划完成　则回传sap接口
				int batchNo=this.zgTcarplanDao.getSeq("SEQ_BATCH_NO");
				getSapClient().businessHandler("5", plan.getCuid(),batchNo,"");
			}
			
		}
		
		for(ZgTorderPlanGroup group:planGrouList){
			String state=zgTorderPlanGroupExBo.getState(group.getCuid());
//			if(!Constants.OrderPlanStatus.FINISHED.value().equals(state)){
//				state=Constants.OrderPlanStatus.SAVE.value();
//			}
			group.setState(state);
			double percent=zgTorderPlanGroupExBo.getPercent(group.getCuid());
			if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
				result="turn";
				if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
					percent=0.99;
				}
			}
			group.setPercent(percent);
			zgTorderPlanGroupExBo.update(group);
		}
		return result;
	}
	
	/**
	 * 根据装车计划编号计算领料计划进度
	 * @param result
	 * @param carPlanId
	 * @return
	 */
	public String doProcessPlanByCarPlanId1( String carPlanId,String planType) {
		
		ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
		if(zgTcarplan==null) return "noTurn";
		zgTcarplan.setCarState(Constants.CarPlanStatus.SUBMIT.value());
		zgTcarplan.setCarDate(Calendar.getInstance().getTime());
		zgTcarplanBo.update(zgTcarplan);
		
		String result="noTurn";
		//获取本次装车计划所涉及的订单
		List<ZgTorderPlan> planList=zgTorderPlanExBo.getOrderPlanListByCarPlanId(carPlanId);
		List<ZgTorderPlanGroup> planGrouList=zgTorderPlanGroupExBo.getPlanGroupListByCarPlanId(carPlanId);
		
		
		//5 更新领料计划表的状态及领料进度
		for(ZgTorderPlan plan:planList){
			String state="";
			if(plan.getPlanType().equals(Constants.OrderPlanType.BACK.value())){
				state=zgTorderPlanExBo.getStateBack(plan.getCuid());
				plan.setState(state);
				zgTorderPlanBo.update(plan);
			}else {
				state=zgTorderPlanExBo.getState(plan.getCuid());
				
			}
			plan.setState(state);
			zgTorderPlanBo.update(plan);
			
			int batchNo=this.zgTcarplanDao.getSeq("SEQ_BATCH_NO");
			getSapClient().businessHandler("5", plan.getCuid(),batchNo,"");
			
			
		}
		
		for(ZgTorderPlanGroup group:planGrouList){
			String state="";
			if(group.getPlanType().equals(Constants.OrderPlanType.BACK.value())){
				state=zgTorderPlanGroupExBo.getStateBack(group.getCuid());
			}else {
				state=zgTorderPlanGroupExBo.getState(group.getCuid());
				
			}
			group.setState(state);
			zgTorderPlanGroupExBo.update(group);
		}
		return result;
	}
	
	/**
	 * 	   仓库管理员确认领料清单(手工结单)
	 * 		1 保存供应商数量
	 *     2 手工结束计划单并设定仓管员
	 *     3 回传sap
	 * @param carbomList
	 * @param operatorInfo
	 * @param storageUserId 仓管员id
	 */
	public String  confirmCarPlan1(List<ZgTcarbomEx> carbomList,OperatorInfo operatorInfo,String storageUserId) {
			//1保存供应商数量
			zgTcarbomExBo.saveSupBomNums(carbomList, Constants.CarPlanStatus.SUBMIT.value());
			
			String carPlanId=carbomList.get(0).getCarPlanId();
			
			for(ZgTcarbomEx bom:carbomList){
				ZgTcarbom carbom=zgTcarbomBo.getById(bom.getCuid());
				carbom.setStorageUserId(storageUserId);
				carbom.setCarDate(Calendar.getInstance().getTime());
				zgTcarbomBo.update(carbom);
			}
			
			//2 手工结束计划单并设定仓管员
			ZgTcarplan zgTcarplan = zgTcarplanBo.getById(carPlanId);
			zgTcarplan.setCarState(Constants.CarPlanStatus.SUBMIT.value());
			zgTcarplan.setCarDate(Calendar.getInstance().getTime());
			zgTcarplan.setStorageUserId(storageUserId);
			zgTcarplanBo.update(zgTcarplan);
			
			//回传领料进度
			int batchNo=this.zgTcarplanDao.getSeq("SEQ_BATCH_NO");
			getSapClient().businessHandler("5", "C:"+carPlanId,batchNo,"");
			
		return "";
	}


	public void setZgTorderPlanExBo(ZgTorderPlanExBo zgTorderPlanExBo) {
		this.zgTorderPlanExBo = zgTorderPlanExBo;
	}


	public void setZgTorderPlanGroupExBo(ZgTorderPlanGroupExBo zgTorderPlanGroupExBo) {
		this.zgTorderPlanGroupExBo = zgTorderPlanGroupExBo;
	}


	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}


	/**
	 * 生成装车计划时，如果是追加的话，判断前后的仓库是否一样
	 * @param lgort
	 * @param planType
	 * @param sessionOperatorId
	 */
	public boolean checkForLgort(String lgort, String planType,String operatorId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select t.storage_id                         ");
		sql.append("  from zg_t_carplan t                       ");
		sql.append(" where t.car_user = '"+operatorId+"'        ");
		sql.append("   and t.car_state <> '8'                   ");
		sql.append("   and t.ORDER_PLAN_TYPE = '"+planType+"'   ");
		
		List<Map> list= ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()==0){
			return true;
		}else {
			String newLgort=list.get(0).get("STORAGE_ID").toString();
			return lgort.equals(newLgort);
		}
	}

	/**
	 * 删除装车计划bom
	 * @param oldBomList
	 */
	public void deleteBom(List<ZgTcarbomEx> bomList) {
		for(ZgTcarbomEx bom:bomList){
			ZgTcarbom temp=zgTcarbomExBo.getById(bom.getCuid());
			String orderPlanbomId=bom.getOrderPlanbomId();
			long carPlanNum=temp.getPlanNum();
			zgTorderPlanbomExBo.updatePlanNum(orderPlanbomId,carPlanNum);
		}
	}
	
	/**
	 * 删除装车计划bom
	 * @param oldBomList
	 */
	public void deleteBom(String carBomId,String orderPlanbomId) {
		
			
		ZgTcarbom temp=zgTcarbomExBo.getById(carBomId);
		long carPlanNum=temp.getPlanNum();
		zgTorderPlanbomExBo.updatePlanNum(orderPlanbomId,carPlanNum);
		zgTcarbomBo.removeById(carBomId);
	}


	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}


	public String checkPlanHasPause(String carPlanId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select task.aufnr ");
		sql.append(" from zg_t_carplan  cplan,     zg_t_order_task task, zg_t_carbom  cbom,  zg_t_order_planbom pbom,   zg_t_order_plan    plan ");
		sql.append(" where cplan.cuid = '"+carPlanId+"'   and cplan.cuid = cbom.car_plan_id   and cbom.order_planbom_id = pbom.cuid ");
		sql.append("  and pbom.order_plan_id = plan.cuid   and plan.state = '-2'  and task.cuid=plan.order_task_id and cbom.storage_user_id is  null and rownum=1");
		List<Map> list= ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()>0){
			return IbatisDAOHelper.getStringValue(list.get(0),"AUFNR");
		}
		return "";
	}


	/**
	 * 查找某一计划分组下面的某一仓库下的所有bom
	 * @param groupId
	 * @param lgort
	 * @param operatorId
	 * @return
	 */
//	public List<Map> getBomListByLgort(String groupId, String lgort,
//			String operatorId) {
//		return zgTcarplanExDao.getBomListByLgort(groupId,lgort,operatorId);
//	}


	/**
	 * @param bomList
	 */
	public String[] getAufnrsByorderBomIds(List<Map> bomList) {
		String ordreBOmIds="";
		for(Map obj:bomList){
			ordreBOmIds=ordreBOmIds+ IbatisDAOHelper.getStringValue(obj, "TASKBOM_ID")+"','";
		}
		if(ordreBOmIds.length()>0){
			ordreBOmIds=ordreBOmIds.substring(0,ordreBOmIds.length()-3);
		}
		
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct od.aufnr, task.arbpl, od.kdauf, od.kdpos, od.maktx1 ");
		sql.append("  from zg_t_order_taskbom t, zg_t_order_task task, zg_t_order od ");
		sql.append(" where t.order_task_id = task.cuid ");
		sql.append("   and task.order_id = od.cuid ");
		sql.append("   and t.cuid in ('"+ordreBOmIds+"') ");
		String aufnrs="";
		List<Map> list= ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
		
String[] aufnrArbpl1={"","","","",""};
		
		for (Map obj:list) {
			aufnrArbpl1[0]=aufnrArbpl1[0]+IbatisDAOHelper.getStringValue(obj, "AUFNR")+",";
			aufnrArbpl1[1]=aufnrArbpl1[1]+IbatisDAOHelper.getStringValue(obj, "ARBPL")+",";
			aufnrArbpl1[2]=aufnrArbpl1[2]+IbatisDAOHelper.getStringValue(obj, "KDAUF")+",";
			aufnrArbpl1[3]=aufnrArbpl1[1]+IbatisDAOHelper.getStringValue(obj, "KDPOS")+",";
			aufnrArbpl1[4]=aufnrArbpl1[1]+IbatisDAOHelper.getStringValue(obj, "MAKTX1")+",";
		}
		if(aufnrArbpl1[0].length()>0){
			aufnrArbpl1[0]=aufnrArbpl1[0].substring(0,aufnrArbpl1[0].length()-1);
			aufnrArbpl1[1]=aufnrArbpl1[1].substring(0,aufnrArbpl1[1].length()-1);
			aufnrArbpl1[2]=aufnrArbpl1[0].substring(0,aufnrArbpl1[0].length()-1);
			aufnrArbpl1[3]=aufnrArbpl1[1].substring(0,aufnrArbpl1[1].length()-1);
			aufnrArbpl1[4]=aufnrArbpl1[0].substring(0,aufnrArbpl1[0].length()-1);
		}
		
		
		return aufnrArbpl1;
		
	}


	/**
	 * @param bomId
	 * @return
	 */
	public String getLockUser(String bomId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select op.label_cn ");
		sql.append("  from zg_t_carbom bom, zg_t_carplan p, fw_operator op ");
		sql.append(" where bom.taskbom_id = '"+bomId+"' ");
		sql.append("   and bom.car_plan_id = p.cuid ");
		sql.append("   and p.car_state <> '8' ");
		sql.append("   and op.cuid = p.car_user ");
		List<Map> list= ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()>0){
			String userName="";
			for(Map map:list){
				userName=userName+IbatisDAOHelper.getStringValue(map,"LABEL_CN")+",";
			}
			return userName.substring(0,userName.length()-1);
		}
		return "";
	}


	/**
	 * @param carPlanId
	 * @return
	 */
	public List<Map> getCarPlanByCarPlanId(String carPlanId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct t.*,car.cuid carId,car.code,car.label_cn,t.storage_id lgort,org.label_cn lgortName from zg_t_carplan t,zg_carinfo car,fw_organization org,zg_t_carbom bom  where  t.storage_id=org.cuid and t.cuid=bom.car_plan_id(+)   and t.car_id=car.cuid and t.cuid='"+carPlanId+"'");
		return ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
	}


	/**
	 * @param carPlanId
	 * @return
	 */
	public String getSapDataDownLoadPlant(String carPlanId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct plan.plant ");
		sql.append("  from zg_t_carbom           carbom, ");
		sql.append("       zg_t_order_planbom    planbom, ");
		sql.append("       zg_t_order_plan       plan,");
		sql.append("       t_sys_iface_log       l,");
		sql.append("       ZG_T_ORDER_PLANT_TEMP p ");
		sql.append(" where carbom.order_planbom_id = planbom.cuid ");
		sql.append("   and planbom.order_plan_id = plan.cuid ");
		sql.append("   and l.batch_no = p.batch_no ");
		sql.append("   and trunc(l.call_time) >= trunc(sysdate) ");
		sql.append("   and ((l.method_name = 'ZSTFC_CONNECTION_RFID_02' and ");
		sql.append("       p.plant = plan.plant) or ");
		sql.append("       (l.method_name = 'ZSTFC_CONNECTION_RFID_01')) ");
		sql.append("   and l.data_stauts = '0' ");
		sql.append("   and carbom.car_plan_id='"+carPlanId+"'");
		List<Map> list= ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()>0){
			return IbatisDAOHelper.getStringValue(list.get(0), "PLANT").toString();
		}
		return null;
		
	}


	/**
	 * @param planboms
	 */
	public String checkOrderLock(String planboms) {
		StringBuffer sqlBuffer=new StringBuffer();
		
		sqlBuffer.append("select t.aufnr  from zg_t_order_lock t, zg_t_order_planbom planbom, zg_t_order_task task ");
		sqlBuffer.append(" where planbom.order_task_id = task.cuid   and task.aufnr = t.aufnr ");
		sqlBuffer.append("   and planbom.cuid in ('"+planboms+"')");
		
		List<Map> list= ((ZgTcarplanDao)this.getEntityDao()).findDynQuery(sqlBuffer.toString());
		
		if(list.size()>0){
			return IbatisDAOHelper.getStringValue(list.get(0), "AUFNR")+"  订单正在从sap系统中下载数据，请稍后再试!";
		}
		return "OK";
	}
}