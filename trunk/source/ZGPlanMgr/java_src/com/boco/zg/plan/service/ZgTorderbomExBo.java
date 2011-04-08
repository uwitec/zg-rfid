/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import sap.SapClient;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTcarbomDao;
import com.boco.zg.plan.base.dao.ZgTcarplanDao;
import com.boco.zg.plan.base.dao.ZgTorderDao;
import com.boco.zg.plan.base.dao.ZgTorderPlanDao;
import com.boco.zg.plan.base.dao.ZgTorderPlanbomDao;
import com.boco.zg.plan.base.dao.ZgTorderbomDao;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.model.ZgTorderbomMoveLog;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.base.service.ZgTorderbomMoveLogBo;
import com.boco.zg.plan.dao.ZgTorderPlanExDao;
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

	public void updateOrderBomSortf(String bomId, String sortf) {
		ZgTorderbom orderbom = (ZgTorderbom) zgTorderbomDao.getById(bomId);
		orderbom.setSortf(sortf);
		zgTorderbomDao.update(orderbom);
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
	public void updateOrderBomSortf1(String bomId, String sortf) {
		ZgTorderbom orderbom = (ZgTorderbom) zgTorderbomDao.getById(bomId);
		if(orderbom.getPlanNum()==0||orderbom.getPlanNum()==null){//没领取过的才能变更
			if(!sortf.equals(orderbom.getSortf())){//标识改变的则做相应处理
				//获取要迁移目标领料计划
				ZgTorderPlan targetPlan=getOrderPlanByOrderIdPlanType(orderbom.getOrderId(), sortf);
				
				//获取BOM原来在的领料计划
				ZgTorderPlan sourcePlan=getOrderPlanByOrderIdPlanType(orderbom.getOrderId(), orderbom.getSortf());
				
				if(targetPlan==null){//BOM要移向目标领料计划为空 测
					if(sourcePlan!=null){//删除原来的领料计划下面的该bom 测
						zgTorderPlanbomExDao.deleteByOrderBomId(orderbom.getCuid());
						
						//重新计划领料进度
						ZgTorderPlanGroup group = zgTorderPlanGroupExDao.getPlanGroupListByOrderPlanId(sourcePlan.getCuid());
						
						doProcessOrderPercent(sourcePlan);
						
						doProcessGroupPercent(group);
					}
					
				}else {//BOM要移向目标领料计划不为空 测
					if(sourcePlan==null){//BOM原来在的领料计划为空 则得在目标领料计划中插入该bom
						ZgTorderPlanbom planbom = new ZgTorderPlanbom();
						planbom.setOrderPlanId(targetPlan.getCuid());
						planbom.setOrderId(orderbom.getOrderId());
						planbom.setOrderBomId(orderbom.getCuid());
						planbom.setState("0");
						planbom.setCarNum(orderbom.getMenge());
						zgTorderPlanbomDao.save(planbom);
						
						//重新计划领料进度
						ZgTorderPlanGroup group = zgTorderPlanGroupExDao.getPlanGroupListByOrderPlanId(targetPlan.getCuid());
						
						doProcessOrderPercent(targetPlan);
						
						doProcessGroupPercent(group);
						
					}else{//更新原来的领料计划下面的该bom 测
//						zgTorderPlanbomExDao.deleteByOrderBomId(orderbom.getCuid());
						ZgTorderPlanbom planbom=zgTorderPlanbomExDao.getByOrderBomId(orderbom.getCuid());
						if(planbom!=null){
							planbom.setOrderPlanId(targetPlan.getCuid());
							zgTorderPlanbomDao.update(planbom);	
						}
						
						//重新计划领料进度
						ZgTorderPlanGroup targetGroup = zgTorderPlanGroupExDao.getPlanGroupListByOrderPlanId(targetPlan.getCuid());
						
						doProcessOrderPercent(targetPlan);
						
						doProcessGroupPercent(targetGroup);
						
						ZgTorderPlanGroup sourceGroup = zgTorderPlanGroupExDao.getPlanGroupListByOrderPlanId(sourcePlan.getCuid());
						
						doProcessOrderPercent(sourcePlan);
						
						doProcessGroupPercent(sourceGroup);
						
					}
				}
				
				orderbom.setSortf(sortf);
				zgTorderbomDao.update(orderbom);
			}
		}
		
		
		
		
	}

	private void doProcessGroupPercent(ZgTorderPlanGroup group) {
		if(group==null) return;
		String state=zgTorderPlanGroupExBo.getState(group.getCuid());
		/*if(!Constants.OrderPlanStatus.FINISHED.value().equals(state)){
			state=Constants.OrderPlanStatus.SAVE.value();
		}*/
		group.setState(state);
		double percent=zgTorderPlanGroupExBo.getPercent(group.getCuid());
		if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
			if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())){
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
			if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())){
				percent=0.99;
			}
		}
		sourcePlan.setPercent(percent);
		zgTorderPlanBo.update(sourcePlan);
		
		if(state.equals(Constants.OrderPlanStatus.FINISHED.value())){//领料计划完成　则回传sap接口
			int batchNo=this.zgTcarplanDao.getSeq("SEQ_BATCH_NO");
			getSapClient().businessHandler("5", sourcePlan.getCuid(),batchNo);
		}
	}
	
	
	
	private ZgTorderPlan getOrderPlanByOrderIdPlanType(String orderId,String planType){
		ZgTorderPlan plan=new ZgTorderPlan();
		plan.setOrderId(orderId);
		plan.setPlanType(planType);
		List<ZgTorderPlan> planList=zgTorderPlanDao.findByProperty(plan);
		String targetPlanId="";
		if(planList.size()>0){
			return planList.get(0);
		}
		return null;
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
	public boolean checkStorageNum(ZgTorderbom zgTorderbom,ZgTorder order) {
//		if(Constants.OrderStatus.DEL.value().equals(order.getOrderState())){//订单是删除状态 则库存量可以全部使用
			return zgTorderbomExDao.checkStorageNum(zgTorderbom);
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
	public boolean checktargetCarNum(ZgTorderbom zgTorderbom, String targetOrderId) {
		return zgTorderbomExDao.checktargetCarNum(zgTorderbom,targetOrderId);
	}
	
	/**
	 * 	改变源订单转单后的BOM数量
	 * @param zgTorderbom
	 */
	public boolean updateSourceBomNum(ZgTorderbom zgTorderbom) {
		return zgTorderbomExDao.updateSourceBomNum(zgTorderbom);
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
	 * @param targetOrderId
	 */
	public String bomMove(String objcetJOSNs, String targetOrderId) {
		String msg="";
		JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
		ZgTorder order=null;
		for(int i=0;i<josnArray.size();i++){
			JSONObject jsonObject = (JSONObject)josnArray.get(i);
			
			
			ZgTorderbom zgTorderbom = (ZgTorderbom)JSONObject.toBean(jsonObject, ZgTorderbom.class);  
			if(order==null){
				order=zgTorderExBo.getById(zgTorderbom.getOrderId());
			}
			
			//检查移单数量是否大于当前剩余的库存数量
			if(!checkStorageNum(zgTorderbom,order)){
				msg=msg+zgTorderbom.getIdnrk()+",";
				continue;
			}
			//检查移单后目标订单的领取数量是否大于需求数量
			if(!checktargetCarNum(zgTorderbom,targetOrderId)){
				msg=msg+zgTorderbom.getIdnrk()+",";
				continue;
			}
			
			
			
			//改变源订单转单后的BOM数量
			if(zgTorderbom.getBomNum()>=2){//注：注原订单有2个以上bom的编号一样的，则逐一减数量
				List<Map> bomList=zgTorderbomExDao.getBomListByOrderIdAndIdnrk(zgTorderbom.getIdnrk(),order);
				Long moveNum=zgTorderbom.getMoveNum();
				for(Map bom:bomList){
					Long maxMoveNum=IbatisDAOHelper.getLongValue(bom,"MAXMOVENUM");
					String cuid=IbatisDAOHelper.getStringValue(bom, "CUID");
					if(maxMoveNum>=moveNum){
						zgTorderPlanbomExDao.updateSourceBomNum(cuid,moveNum);
						//计算源订单bom领料状态
						ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(cuid);
						doPrecessPlanbom(planbom);
						break;
					}else {
						moveNum=moveNum-maxMoveNum;
						zgTorderPlanbomExDao.updateSourceBomNum(cuid,maxMoveNum);
						//计算源订单bom领料状态
						ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(cuid);
						doPrecessPlanbom(planbom);
					}
					
				
				}
			}else {//只有一个bom，则直接减数量
				updateSourceBomNum(zgTorderbom);
				
				//计算源订单bom领料状态
				ZgTorderPlanbom planbom = zgTorderPlanbomExDao.getByOrderBomId(zgTorderbom.getCuid());
				doPrecessPlanbom(planbom);
			}
			
			
			
		
			
			//改变目标订单的 bom为数量
			//获取移单目标BOM列表
			ZgTorder tartgTorder=new ZgTorder();
			tartgTorder.setCuid(targetOrderId);
			List<Map> bomList=zgTorderbomExDao.getTargetBomListByOrderIdAndIdnrk(zgTorderbom.getIdnrk(),tartgTorder);
			if(bomList.size()==1){//目标bom只有一个，直接更新记录
				updateTargetBomNum(zgTorderbom,targetOrderId);
				
				//计算目标订单bom领料状态
				ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(IbatisDAOHelper.getStringValue(bomList.get(0), "CUID"));
				doPrecessPlanbom(planbom);
			}else {//目标bom有多个，逐条更新记录
				Long moveNum=zgTorderbom.getMoveNum();
				for(Map bom:bomList){
					Long maxMoveNum=IbatisDAOHelper.getLongValue(bom,"MAXMOVENUM");
					String cuid=IbatisDAOHelper.getStringValue(bom, "CUID");
					if(maxMoveNum>=moveNum){
						zgTorderPlanbomExDao.updateTargetBomNum(cuid,moveNum);
						//计算目标订单bom领料状态
						ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(cuid);
						doPrecessPlanbom(planbom);
						break;
					}else {
						moveNum=moveNum-maxMoveNum;
						zgTorderPlanbomExDao.updateTargetBomNum(cuid,maxMoveNum);
						//计算目标订单bom领料状态
						ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(cuid);
						doPrecessPlanbom(planbom);
					}
					
				
				}
			}
			
			
			//记录移单记录
			ZgTorderbomMoveLog bomMoveLog=new ZgTorderbomMoveLog();
			bomMoveLog.setTargetOrderId(targetOrderId);
			bomMoveLog.setTargetIdnrk(zgTorderbom.getIdnrk());
			bomMoveLog.setSourceOrderId(order.getCuid());
			bomMoveLog.setSourceIdnrk(zgTorderbom.getIdnrk());
			bomMoveLog.setMoveNum(zgTorderbom.getMoveNum());
			zgTorderbomMoveLogBo.save(bomMoveLog);
			
			
			
			
		}
		
		return msg;
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
	 * 根据计单编号计算订单的装车计划进度
	 * @param orderId
	 */
	public void doZgtorderProcess(String orderId) {
		//===========计算订单领料进度=====================
		//查找目标订单的领料计划单
		List<ZgTorderPlan> planList= zgTorderPlanExBo.getOrderPlanListByOrderId(orderId);
		// 更新领料计划表的状态及领料进度
		for(ZgTorderPlan plan:planList){
			String state=zgTorderPlanExBo.getState(plan.getCuid());
			plan.setState(state);
			double percent=zgTorderPlanExBo.getPercent(plan.getCuid());
			if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
				if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())){
					percent=0.99;
				}
			}
			plan.setPercent(percent);
			zgTorderPlanBo.update(plan);
		}
		
		//查找目标订单的领料计划分组单
		List<ZgTorderPlanGroup> planGrouList=zgTorderPlanGroupExBo.getPlanGroupListByOrderId(orderId);
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

}
