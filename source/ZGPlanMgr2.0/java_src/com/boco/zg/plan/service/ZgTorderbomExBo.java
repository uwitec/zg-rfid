/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import sap.SapClient;
import sap.service.HandlerOrderServiceImpl;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTcarplanDao;
import com.boco.zg.plan.base.dao.ZgTorderPlanDao;
import com.boco.zg.plan.base.dao.ZgTorderPlanbomDao;
import com.boco.zg.plan.base.dao.ZgTorderbomDao;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderTask;
import com.boco.zg.plan.base.model.ZgTorderTaskbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.model.ZgTorderbomMoveLog;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTorderTaskBo;
import com.boco.zg.plan.base.service.ZgTorderTaskbomBo;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.base.service.ZgTorderbomMoveLogBo;
import com.boco.zg.plan.dao.ZgTorderPlanGroupExDao;
import com.boco.zg.plan.dao.ZgTorderPlanbomExDao;
import com.boco.zg.plan.dao.ZgTorderbomExDao;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.util.Constants;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderbomExBo extends ZgTorderbomBo {

	private ZgTorderbomDao zgTorderbomDao;
	private ZgTorderbomExDao zgTorderbomExDao;
	
	private ZgTorderPlanExBo zgTorderPlanExBo;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTorderPlanbomExDao zgTorderPlanbomExDao;
	
	private ZgTorderPlanGroupExBo zgTorderPlanGroupExBo;

	private ZgTorderPlanbomBo zgTorderPlanbomBo;
	
	private ZgTorderPlanDao zgTorderPlanDao;
	
	private ZgTorderPlanGroupExDao zgTorderPlanGroupExDao;
	
	private ZgTorderPlanbomDao zgTorderPlanbomDao;
	
	private ZgTcarplanDao zgTcarplanDao;
	
	private ZgTorderbomMoveLogBo zgTorderbomMoveLogBo;
	
	private ZgTorderTaskbomBo zgTorderTaskbomBo;
	
	private ZgTorderTaskBo zgTorderTaskBo;
	
	private ZgTorderBo zgTorderBo;
	
	public static Map<String, String> plantSortfMap=null;
	
	
	public  Map<String, String>  getPlantSortfMap(){
		if(plantSortfMap==null){
			plantSortfMap=zgTorderBo.getPlantSortfMap();
		}
		return plantSortfMap;
	}
	
	/**
	 * @return
	 */
	private HandlerOrderServiceImpl getHandlerOrderServiceImpl() {
		return (HandlerOrderServiceImpl) ApplicationContextHolder
		.getBean("handlerOrderServiceImpl");
	}

	
	public void setZgTorderbomDao(ZgTorderbomDao dao) {
		this.zgTorderbomDao = dao;
	}

	private ZgTorderExBo zgTorderExBo;

	public void setZgTorderExBo(ZgTorderExBo zgTorderExBo) {
		this.zgTorderExBo = zgTorderExBo;
	}
	
	private SapClient getSapClient() {
		return (SapClient)ApplicationContextHolder.getBean("sapClient");
	}

	/**
	 * 查询订单中的半成品
	 * 
	 * @return
	 */
	public List<Map> findBomDEByOrderId(String orderId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct t.matnr1 as matnr from zg_t_orderbom t ");
		sb.append("where t.order_id = '" + orderId + "'");
		return zgTorderbomDao.findDynQuery(sb.toString());
	}

	public List<ZgTorderbom> findBomByMatnrs(String orderId, String[] matnrs) {
		ZgTorderbom entity = new ZgTorderbom();
		entity.setOrderId(orderId);
		String s = "";
		for (String matnr : matnrs) {
			s += "'" + matnr + "',";
		}
		s = s.substring(0, s.length() - 1);
		entity.setSqlQueryString("t0_MATNR1 in (" + s + ")");
		return zgTorderbomDao.findByProperty(entity, "t0_MATNR1,t0_SORTF",
				false);
	}

	public List<ZgTorderbom> findBomByOrderId(String orderId) {
		ZgTorderbom entity = new ZgTorderbom();
		entity.setOrderId(orderId);
		return zgTorderbomDao.findByProperty(entity, "t0_MATNR1,t0_SORTF",
				false);
	}

	/**
	 * 更改制作标识 并把物料插入相应的反序数据中
	 * @param bomId
	 * @param sortf
	 */
	public void updateOrderBomSortf(String bomId, String sortf) {
		ZgTorderbom orderbom = (ZgTorderbom) zgTorderbomDao.getById(bomId);
		
		if(!sortf.equals(orderbom.getSortf())){
			//删除原排序字符串相应的planbom
			//判断是否已经生成planBOM数据
			 List<ZgTorderPlanbom> list=zgTorderPlanbomBo.getPlanBomByOrderBomId(bomId);
			 if(list.size()>0){
				 int delRow=zgTorderPlanbomBo.deletePlanBomByOrderBomId(bomId);
					if(delRow==0) return;
					
					//删除原来的taskBom
					zgTorderTaskbomBo.deleteTaskBomByOrderBomId(bomId);
			 }
			
			
			orderbom.setSortf(sortf);
			zgTorderbomDao.update(orderbom);
			
			//插入新的排序
			//获取目前的排序任务
			ZgTorderTask task=new ZgTorderTask();
			task.setOrderId(orderbom.getOrderId());
			List<ZgTorderTask> taskList=zgTorderTaskBo.findByProperty(task);
			//循环排序任务找到相应的排序任务插入
			for(ZgTorderTask orderTask:taskList){
				String taskSortf=getPlantSortfMap().get(orderTask.getPlant());
				if(taskSortf!=null&&taskSortf.equals(sortf)){
					//插入工单任务BOM ZG_T_ORDER_TASKBOM
					Long menge=orderTask.getPmenge()*orderbom.getZdtyl();
					ZgTorderTaskbom taskbom=getHandlerOrderServiceImpl().saveZgTorderTaskBom(orderTask.getCuid(),menge , bomId);
					
					//该BOM插入领料计划中
					getHandlerOrderServiceImpl().doWithPlanBomBYTaskIdPlantTaskBomId(orderTask.getCuid(),orderTask.getPlant(),taskbom);
				}
			}
			
			
		}
		
		zgTorderExBo.updateOrderState(orderbom.getOrderId(),
				ZgTorderExBo.PLAN_STATE_SAVE);
		
		
	}
	
	/**
	 * 订单提交后修改他的制作标识
	 * 		1 修改bom制作标识
	 * 		2 如果已经生成领料计划的，要把该bom迁移到相应的类型领料计划中
	 * 		3 重新计划领料计划进度
	 * @param sortfs
	 * @return
	 */
	public String updateOrderBomSortf1(String bomId, String sortf) {
		ZgTorderbom orderbom = (ZgTorderbom) zgTorderbomDao.getById(bomId);
		if(!sortf.equals(orderbom.getSortf())){
			//删除原排序字符串相应的planbom
			//判断是否已经瑛料
			boolean isStartCar=zgTorderPlanbomBo.isStartCar(bomId);
			if(isStartCar){//已经开始领料，不允许改标识 
				return orderbom.getIdnrk();
			}
			 List<ZgTorderPlanbom> list=zgTorderPlanbomBo.getPlanBomByOrderBomId(bomId);
			 if(list.size()>0){
				 int delRow=zgTorderPlanbomBo.deletePlanBomByOrderBomId(bomId);
					if(delRow==0) return "";
					//删除原来的taskBom
					zgTorderTaskbomBo.deleteTaskBomByOrderBomId(bomId);
			 }
			
			
			orderbom.setSortf(sortf);
			zgTorderbomDao.update(orderbom);
			
			//插入新的排序
			//获取目前的排序任务
			ZgTorderTask task=new ZgTorderTask();
			task.setOrderId(orderbom.getOrderId());
			List<ZgTorderTask> taskList=zgTorderTaskBo.findByProperty(task);
			//循环排序任务找到相应的排序任务插入
			for(ZgTorderTask orderTask:taskList){
				String taskSortf=getPlantSortfMap().get(orderTask.getPlant());
				if(taskSortf!=null&&taskSortf.equals(sortf)){
					//插入工单任务BOM ZG_T_ORDER_TASKBOM
					Long menge=orderTask.getPmenge()*orderbom.getZdtyl();
					ZgTorderTaskbom taskbom=getHandlerOrderServiceImpl().saveZgTorderTaskBom(orderTask.getCuid(),menge , bomId);
					
					//该BOM插入领料计划中
					getHandlerOrderServiceImpl().doWithPlanBomBYTaskIdPlantTaskBomId(orderTask.getCuid(),orderTask.getPlant(),taskbom);
				}
			}
			
			
			
		}
		return "";
		
		
	}

	private void doProcessGroupPercent(ZgTorderPlanGroup group) {
		if(group==null) return;
		String state="";
		if(group.getPlanType().contains("BACK")){
			state=zgTorderPlanGroupExBo.getStateBack(group.getCuid());
		}else {
			state=zgTorderPlanGroupExBo.getState(group.getCuid());
		}
		/*if(!Constants.OrderPlanStatus.FINISHED.value().equals(state)){
			state=Constants.OrderPlanStatus.SAVE.value();
		}*/
		group.setState(state);
		double percent=zgTorderPlanGroupExBo.getPercent(group.getCuid());
		if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
			if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
				percent=0.99;
			}
		}
		group.setPercent(percent);
		zgTorderPlanGroupExBo.update(group);
	}

	private void doProcessOrderPercent(ZgTorderPlan sourcePlan) {
		if(sourcePlan==null) return;
		String state=zgTorderPlanExBo.getState(sourcePlan.getCuid());
		/*if(!Constants.OrderPlanStatus.FINISHED.value().equals(state)){
			state=Constants.OrderPlanStatus.SAVE.value();
		}*/
		sourcePlan.setState(state);
		double percent=zgTorderPlanExBo.getPercent(sourcePlan.getCuid());
		if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
			if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
				percent=0.99;
			}
		}
		sourcePlan.setPercent(percent);
		zgTorderPlanBo.update(sourcePlan);
		
		if(state.equals(Constants.OrderPlanStatus.FINISHED.value())){//领料计划完成　则回传sap接口
			int batchNo=this.zgTcarplanDao.getSeq("SEQ_BATCH_NO");
			getSapClient().businessHandler("5", sourcePlan.getCuid(),batchNo,"");
		}
	}
	
	
	

	/**
	 * 获取已经提交的领料计划的bom组件
	 * 
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderbomEx> findBomList(PageRequest<Map> pageRequest) {
		return zgTorderbomDao.findBomList(pageRequest);
	}

	/**
	 * 入库管理获取订单生产线列表
	 * 
	 * @return
	 */
	public List<ZgTorderbomEx> findOrderArbplList(Map params) {
		// StringBuffer sb = new StringBuffer();
		// sb.append("select distinct
		// t.aufnr,t.order_id,t.arbpl,o.label_cn,t.maktx1 from zg_t_orderbom
		// t,fw_organization o where t.arbpl=o.cuid");
		// return zgTorderbomDao.findDynQuery(sb.toString());
		return zgTorderbomExDao.findOrderArbplList(params);
	}
	/**
	 * 入库管理获取订单生产线列表
	 * @param pageRequest
	 * @return
	 */
	public Page findOrderArbplByPageRequest(PageRequest pageRequest) {
		return zgTorderbomExDao.findOrderArbplByPageRequest(pageRequest);
	}
	
	/**
	 * 入库管理获取订单生产线列表
	 * @param pageRequest
	 * @return
	 */
	public Page findOrderArbplByPageRequest1(PageRequest pageRequest) {
		return zgTorderbomExDao.findOrderArbplByPageRequest1(pageRequest);
	}

	/**
	 * 出库管理获取订单生产线列表
	 * 
	 * @return
	 */
	public List<ZgTorderbomEx> findOrderArbplList1(Map params) {
		// StringBuffer sb = new StringBuffer();
		// sb.append("select distinct
		// t.aufnr,t.order_id,t.arbpl,o.label_cn,t.maktx1 from zg_t_orderbom
		// t,fw_organization o where t.arbpl=o.cuid");
		// return zgTorderbomDao.findDynQuery(sb.toString());
		return zgTorderbomExDao.findOrderArbplList1(params);
	}

	public void setZgTorderbomExDao(ZgTorderbomExDao zgTorderbomExDao) {
		this.zgTorderbomExDao = zgTorderbomExDao;
	}

	/**
	 * 更新半成品库存状态
	 * 
	 * @param zgTorderbomEx
	 */
	public void updateOrderBomState(ZgTorderbom zgTorderbom) {
		zgTorderbomExDao.updateOrderBomState(zgTorderbom);
	}

	/**
	 * 根据仓库编号查找还有剩余库存的生产线和订单
	 */
	public List<Map> findOrderArbplListByStorageId(String storageId,
			String productType) {

		StringBuffer sb = new StringBuffer();
		sb
				.append(" select distinct ob.aufnr, ob.order_id, ob.arbpl, o.label_cn, ob.maktx1 ");
		sb
				.append(" from zg_t_storage_stat t, zg_t_orderbom ob, fw_organization o ");
		sb.append(" where t.lgort = '" + storageId + "'");
		sb.append(" and t.product_type ='" + productType + "'");
		sb.append(" and t.order_bomid = ob.cuid");
		sb.append("   and ob.arbpl = o.cuid ");

		return zgTorderbomDao.findDynQuery(sb.toString());

	}

	/**
	 * 根据生产线和订单编码查找入库半成品列表
	 * 
	 * @param params
	 * @return
	 */
	public List<ZgTorderbomEx> findBomlListByArbplOrderId(Map params) {
		return zgTorderbomExDao.findBomlListByArbplOrderId(params);

	}

	/**
	 * 根据生产线，订单编码 ，仓库 查找可以出库的半成品
	 * 
	 * @param filters
	 * @return
	 */
	public List<ZgTorderbomEx> findBomlListByArbplOrderId1(Map filters) {
		return zgTorderbomExDao.findBomlListByArbplOrderId1(filters);
	}

	/**
	 * 获取领料延时参数
	 * 
	 * @return
	 */
	public String findOverTimeValue() {
		return zgTorderbomDao.findOverTimeValue();
	}

	/**
	 * 更新领料延时参数
	 * 
	 * @param paramvalue
	 */
	public void saveOverTimeValue(String paramvalue) {
		zgTorderbomDao.saveOverTimeValue(paramvalue);
	}

	/**
	 * 查询所输入的数量是否超出需求总量
	 * 
	 * @param num
	 * @param idnrk
	 * @param aufnr
	 * @return
	 */

	public boolean checkStateForGenerateRightNum(Long num, String idnrk,
			String aufnr) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ZG_T_ORDERBOM t where t.idnrk ='" + idnrk
				+ "' and (nvl(t.menge,0)-nvl(t.storage_num,0)) >=" + num
				+ " and t.aufnr = '" + aufnr + "'");
		return zgTorderbomDao.findDynQuery(sql.toString()).size() > 0;

	}

	/**
	 * 出库表及冲单表－－判断新建（修改）表时，表所传的数量，是否超出总库存的值
	 * 
	 * @param aufnr
	 * @param idnrk
	 * @param num
	 * @return
	 */
	public boolean checkStateForGenerateBomNum(String aufnr, String idnrk,
			long num,String lgort) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ZG_T_STORAGE_STAT m,ZG_T_ORDERBOM t where (nvl(m.NUM,0)-nvl(m.OUTNUM,0)) >="
						+ num
						+ " and  m.ORDER_BOMID = t.cuid and t.idnrk = '"
						+ idnrk + "' and t.aufnr = '" + aufnr + "'    and m.lgort='"+lgort+"'");
		return zgTorderbomDao.findDynQuery(sql.toString()).size() > 0;
	}

	/**
	 * 根据定单分组查找其bom组件
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderbomEx> getBomListByProperty(PageRequest<Map> pageRequest) {
		//查询该订单是否是提前领料
		if(IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "planType", "").contains("BACK")){
			pageRequest.getFilters().put("planType", "BACK");
		}
		if("1".equals(IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "advance", ""))){//提前领料，只查找提前领的bom
			return zgTorderbomExDao.getBomListByPropertyAdvance(pageRequest);
		}else {
			return zgTorderbomExDao.getBomListByProperty(pageRequest);
		}
		
	}
	
	/**
	 * 根据定单分组查找其bom组件
	 * @param pageRequest
	 * @return
	 */
	public List<Map> getBomListByGroupId(PageRequest<Map> pageRequest) {
		//查询该订单是否是提前领料
		if("1".equals(IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "advance", ""))){//提前领料，只查找提前领的bom
			return zgTorderbomExDao.getBomListByGroupIdAdvance(pageRequest);
		}else {
			return zgTorderbomExDao.getBomListByGroupId(pageRequest);
		}
		
	}
	
	/**
	 * 根据定单分组查找其bom组件
	 * @param pageRequest
	 * @return
	 */
	public List<Map> getBomListByGroupId1(PageRequest<Map> pageRequest) {
		//查询该订单是否是提前领料
		if("1".equals(IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "advance", ""))){//提前领料，只查找提前领的bom
			return zgTorderbomExDao.getBomListByGroupIdAdvance1(pageRequest);
		}else {
			return zgTorderbomExDao.getBomListByGroupId1(pageRequest);
		}
		
	}

	/**
	 * 根据订单编号获取其物料详细信息
	 * @param orderId1 源订单
	 * @param orderId2 目标订单
	 * @param both 
	 * @param isDel 订单是否删除状态 -1 为删除状态
	 * @return
	 */
	public List<Map> getBomListByOrderId(String orderId1,String orderId2,String both,String isDel) {
		return zgTorderbomExDao.getBomListByOrderId(orderId1,orderId2,both,isDel);
	}
	
	/**
	 * 根据订单编号获取其物料详细信息
	 * @param orderId1 目标订单
	 * @param orderId2 源订单
	 * @param both
	 * @param isDel 订单是否删除状态 -1 为删除状态
	 * @return
	 */
	public List<Map> getBomListByOrderId1(String orderId1,String orderId2,String both,String isDel) {
		return zgTorderbomExDao.getBomListByOrderId1(orderId1,orderId2,both,isDel);
	}

	/**
	 * 检查移单数量是否大于当前剩余的库存数量
	 * @param obj
	 */
	public boolean checkStorageNum(ZgTorderPlanbom planbom) {
//		if(Constants.OrderStatus.DEL.value().equals(order.getOrderState())){//订单是删除状态 则库存量可以全部使用
			return zgTorderbomExDao.checkStorageNum(planbom);
//		}else {//能使用的数量是completenum-carnum
//			return zgTorderbomExDao.checkStorageNum1(zgTorderbom);
//		}
	}

	/**
	 * 检查移单后目标订单的领取数量是否大于需求数量
	 * @param obj
	 * @param targetOrderId
	 * @return
	 */
	public boolean checktargetCarNum(ZgTorderPlanbom planbom, String targetOrderTaskId) {
		return zgTorderbomExDao.checktargetCarNum(planbom,targetOrderTaskId);
	}
	
	/**
	 * 	改变源订单转单后的BOM数量
	 * @param zgTorderbom
	 */
	public boolean updateSourceBomNum(ZgTorderPlanbom planbom) {
		return zgTorderbomExDao.updateSourceBomNum(planbom);
	}

	/**
	 * 改变目标订单的 bom为数量
	 * @param zgTorderbom
	 * @param targetOrderId
	 */
	public boolean updateTargetBomNum(ZgTorderbom zgTorderbom, String targetOrderId) {
		return zgTorderbomExDao.updateTargetBomNum(zgTorderbom,targetOrderId);
	}
	
	/**
	 *1 检查移单数据是否合法(a 该物料移单数量不可能于该物料的可移单数量 注：可移单数量=complete_num-moveNum
	 *                  b 检查移单后目标订单的领取数量不能大于需求数量 )
	 *2//改变源订单转单后的BOM数量
	 *3//改变目标订单的 bom数量
	 * @param objcetJOSNs
	 * @param targetOrderTaskId
	 */
	public String bomMove(String objcetJOSNs, String sourceOrderTaskId,String targetOrderTaskId,OperatorInfo operatorInfo) {
		String msg="";
		JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
		ZgTorder order=null;
		for(int i=0;i<josnArray.size();i++){
			JSONObject jsonObject = (JSONObject)josnArray.get(i);
			
			ZgTorderPlanbom planbom = (ZgTorderPlanbom)JSONObject.toBean(jsonObject, ZgTorderPlanbom.class);  
//			ZgTorderbom zgTorderbom = (ZgTorderbom)JSONObject.toBean(jsonObject, ZgTorderbom.class);  
//			if(order==null){
//				order=zgTorderExBo.getById(planbom.getOrderId());
//			}
			
			//检查移单数量是否大于当前剩余的库存数量
			if(!checkStorageNum(planbom)){
				msg=msg+planbom.getIdnrk()+",";
				continue;
			}
			
			//检查移单后目标订单的领取数量是否大于需求数量
			if(!checktargetCarNum(planbom,targetOrderTaskId)){
				msg=msg+planbom.getIdnrk()+",";
				continue;
			}
			
			
			
			
			//改变源订单转单后的BOM数量
			doMoveSourceBom(planbom);
			
//			System.out.println(9/0);
			
			//改变目标订单的 bom为数量
			//获取移单目标BOM列表
			ZgTorder tartgTorder=new ZgTorder();
			tartgTorder.setCuid(targetOrderTaskId);
			List<Map> bomList=zgTorderbomExDao.getTargetBomListByOrderIdAndIdnrk(planbom.getIdnrk(),targetOrderTaskId);
			Long moveNum=planbom.getMoveNum();
			for(Map bom:bomList){
				Long maxMoveNum=IbatisDAOHelper.getLongValue(bom,"MAXMOVENUM");
				String planbomId=IbatisDAOHelper.getStringValue(bom, "CUID");
				if(moveNum>0){
					Long realMoveNum=0l;
					if(maxMoveNum>=moveNum){
						
						realMoveNum=moveNum;
						doMoveTargetBom(planbomId,moveNum);
						moveNum=0l;
					}else {
						realMoveNum=maxMoveNum;
						doMoveTargetBom(planbomId,maxMoveNum);
						moveNum=moveNum-maxMoveNum;
					}
					
					//记录移单记录
					ZgTorderbomMoveLog bomMoveLog=new ZgTorderbomMoveLog();
					bomMoveLog.setSourceOrderTaskId(sourceOrderTaskId);
					bomMoveLog.setSourcePlanbomId(planbom.getCuid());
					bomMoveLog.setTargetOrderTaskId(targetOrderTaskId);
					bomMoveLog.setTargetPlanbom(planbomId);
					bomMoveLog.setMoveNum(realMoveNum);
					bomMoveLog.setCreateDate(Calendar.getInstance().getTime());
					bomMoveLog.setCreateId(operatorInfo.getOperatorId());
					bomMoveLog.setCreateUsername(operatorInfo.getUserName());
					bomMoveLog.setOrgId(operatorInfo.getOrgId());
					zgTorderbomMoveLogBo.save(bomMoveLog);
					
				}
			}
			
			
			
			
			
			
		}
		
		return msg;
	}

	/**
	 * @param cuid
	 * @param moveNum
	 */
	private void doMoveTargetBom(String planbomID, Long moveNum) {
		ZgTorderPlanbom tempPlanbom=zgTorderPlanbomBo.getById(planbomID);
		tempPlanbom.setMoveNumIn(tempPlanbom.getMoveNumIn()+moveNum);
		tempPlanbom.setPlanNum(tempPlanbom.getPlanNum()+moveNum);
		tempPlanbom.setCompleteNum(tempPlanbom.getCompleteNum()+moveNum);
		tempPlanbom.setStorageNum(tempPlanbom.getStorageNum()+moveNum);
		if(tempPlanbom.getCompleteNum()>=tempPlanbom.getCarNum()){
			tempPlanbom.setState(Constants.CarPlanStatus.DONE.value());
		}else {
			tempPlanbom.setState(Constants.CarPlanStatus.NEW.value());
		}
		zgTorderPlanbomBo.update(tempPlanbom);
	}

	/**
	 * @param planbom
	 * @return
	 */
	private void doMoveSourceBom(ZgTorderPlanbom planbom) {
		ZgTorderPlanbom tempPlanbom=zgTorderPlanbomBo.getById(planbom.getCuid());
		tempPlanbom.setMoveNum(tempPlanbom.getMoveNum()+planbom.getMoveNum());
		tempPlanbom.setPlanNum(tempPlanbom.getPlanNum()-planbom.getMoveNum());
		tempPlanbom.setCompleteNum(tempPlanbom.getCompleteNum()-planbom.getMoveNum());
		tempPlanbom.setStorageNum(tempPlanbom.getStorageNum()-planbom.getMoveNum());
		if(tempPlanbom.getWaitBackNum()>0){
			if(tempPlanbom.getWaitBackNum()-planbom.getMoveNum()>=0){
				tempPlanbom.setWaitBackNum(tempPlanbom.getWaitBackNum()-planbom.getMoveNum());
			}else {
				tempPlanbom.setWaitBackNum(0l);
			}
			
			//生成退料子记录
			Long manulWaitBackNum=getZgTorderPlanbomBo().getManulWaitBackNumByPbId(planbom.getCuid());
			getZgTorderPlanbomBo().generateWaiBackBom(tempPlanbom,manulWaitBackNum);
			
		}
		if(tempPlanbom.getCompleteNum()>=tempPlanbom.getCarNum()){
			tempPlanbom.setState(Constants.CarPlanStatus.DONE.value());
		}else {
			tempPlanbom.setState(Constants.CarPlanStatus.NEW.value());
		}
		zgTorderPlanbomBo.update(tempPlanbom);
	}

/**
	 * 更新planbom领料状态
	 * @param planbom
	 */
	private void doPrecessPlanbom(ZgTorderPlanbom planbom) {
		if(planbom.getCarNum() > planbom.getCompleteNum()) {
			planbom.setState(Constants.CarPlanStatus.NEW.value());
		}else{
			planbom.setState(Constants.CarPlanStatus.DONE.value());
		}
		zgTorderPlanbomBo.update(planbom);
	}

	/**
	 * 根据订单任务编号编号计算订单的装车计划进度
	 * @param id 为orderId或taskId
	 * @param type:task 为具体任务 order为订单
	 */
	public void doZgtorderProcess(String id,String type) {
		//===========计算订单领料进度=====================
		//查找目标订单的领料计划单
		List<ZgTorderPlan> planList=null;
		if(type.equals("order")){//整单计算
			planList= zgTorderPlanExBo.getOrderPlanListByOrderId(id);
		}else {
			planList= zgTorderPlanExBo.getOrderPlanListByOrderTaskId(id);
		}
		
		// 更新领料计划表的状态及领料进度
		for(ZgTorderPlan plan:planList){
			String state="";
			if(plan.getPlanType().contains("BACK")){
				state=zgTorderPlanExBo.getStateBack(plan.getCuid());
			}else {
				state=zgTorderPlanExBo.getState(plan.getCuid());
			}
			plan.setState(state);
			double percent=zgTorderPlanExBo.getPercent(plan.getCuid());
			if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
				if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
					percent=0.99;
				}
			}
			plan.setPercent(percent);
			zgTorderPlanBo.update(plan);
		}
		
		//查找目标订单的领料计划分组单
		List<ZgTorderPlanGroup> planGrouList=null;
		if(type.equals("order")){//整单计算
			planGrouList=zgTorderPlanGroupExBo.getPlanGroupListByOrderId(id);
		}else {
			planGrouList=zgTorderPlanGroupExBo.getPlanGroupListByOrderTaskId(id);
		}
		
		for(ZgTorderPlanGroup group:planGrouList){
			doProcessGroupPercent(group);
		}
	}

	public ZgTorderPlanExBo getZgTorderPlanExBo() {
		return zgTorderPlanExBo;
	}

	public void setZgTorderPlanExBo(ZgTorderPlanExBo zgTorderPlanExBo) {
		this.zgTorderPlanExBo = zgTorderPlanExBo;
	}

	public ZgTorderPlanBo getZgTorderPlanBo() {
		return zgTorderPlanBo;
	}

	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}

	public ZgTorderPlanGroupExBo getZgTorderPlanGroupExBo() {
		return zgTorderPlanGroupExBo;
	}

	public void setZgTorderPlanGroupExBo(ZgTorderPlanGroupExBo zgTorderPlanGroupExBo) {
		this.zgTorderPlanGroupExBo = zgTorderPlanGroupExBo;
	}

	public ZgTorderPlanbomExDao getZgTorderPlanbomExDao() {
		return zgTorderPlanbomExDao;
	}

	public void setZgTorderPlanbomExDao(ZgTorderPlanbomExDao zgTorderPlanbomExDao) {
		this.zgTorderPlanbomExDao = zgTorderPlanbomExDao;
	}

	public ZgTorderPlanbomBo getZgTorderPlanbomBo() {
		return zgTorderPlanbomBo;
	}

	public void setZgTorderPlanbomBo(ZgTorderPlanbomBo zgTorderPlanbomBo) {
		this.zgTorderPlanbomBo = zgTorderPlanbomBo;
	}


	public ZgTorderPlanDao getZgTorderPlanDao() {
		return zgTorderPlanDao;
	}

	public void setZgTorderPlanDao(ZgTorderPlanDao zgTorderPlanDao) {
		this.zgTorderPlanDao = zgTorderPlanDao;
	}

	public ZgTorderPlanbomDao getZgTorderPlanbomDao() {
		return zgTorderPlanbomDao;
	}

	public void setZgTorderPlanbomDao(ZgTorderPlanbomDao zgTorderPlanbomDao) {
		this.zgTorderPlanbomDao = zgTorderPlanbomDao;
	}

	public ZgTorderPlanGroupExDao getZgTorderPlanGroupExDao() {
		return zgTorderPlanGroupExDao;
	}

	public void setZgTorderPlanGroupExDao(
			ZgTorderPlanGroupExDao zgTorderPlanGroupExDao) {
		this.zgTorderPlanGroupExDao = zgTorderPlanGroupExDao;
	}

	public ZgTcarplanDao getZgTcarplanDao() {
		return zgTcarplanDao;
	}

	public void setZgTcarplanDao(ZgTcarplanDao zgTcarplanDao) {
		this.zgTcarplanDao = zgTcarplanDao;
	}

	public ZgTorderbomMoveLogBo getZgTorderbomMoveLogBo() {
		return zgTorderbomMoveLogBo;
	}

	public void setZgTorderbomMoveLogBo(ZgTorderbomMoveLogBo zgTorderbomMoveLogBo) {
		this.zgTorderbomMoveLogBo = zgTorderbomMoveLogBo;
	}
	 public void deleteById(java.lang.String id) {
		  zgTorderbomDao.deleteById(id);
	}

	/**
	 *  更新物料的自有物料组编号
	 * @param idnrk
	 * @param matkl
	 */
	public void setSelfMatkl(String idnrk, String matkl) {
		zgTorderbomExDao.setSelfMatkl(idnrk,matkl);
	}

	/**
	 * @return the zgTorderTaskbomBo
	 */
	public ZgTorderTaskbomBo getZgTorderTaskbomBo() {
		return zgTorderTaskbomBo;
	}

	/**
	 * @param zgTorderTaskbomBo the zgTorderTaskbomBo to set
	 */
	public void setZgTorderTaskbomBo(ZgTorderTaskbomBo zgTorderTaskbomBo) {
		this.zgTorderTaskbomBo = zgTorderTaskbomBo;
	}

	/**
	 * @return the zgTorderTaskBo
	 */
	public ZgTorderTaskBo getZgTorderTaskBo() {
		return zgTorderTaskBo;
	}

	/**
	 * @param zgTorderTaskBo the zgTorderTaskBo to set
	 */
	public void setZgTorderTaskBo(ZgTorderTaskBo zgTorderTaskBo) {
		this.zgTorderTaskBo = zgTorderTaskBo;
	}


	/**
	 * @return the zgTorderBo
	 */
	public ZgTorderBo getZgTorderBo() {
		return zgTorderBo;
	}


	/**
	 * @param zgTorderBo the zgTorderBo to set
	 */
	public void setZgTorderBo(ZgTorderBo zgTorderBo) {
		this.zgTorderBo = zgTorderBo;
	}

}
