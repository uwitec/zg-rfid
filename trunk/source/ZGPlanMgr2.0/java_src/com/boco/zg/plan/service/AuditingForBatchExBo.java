package com.boco.zg.plan.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.dao.ZgTorderPlanCommentExDao;
import com.boco.zg.plan.dao.ZgTorderPlanExDao;
import com.boco.zg.plan.dao.ZgTorderPlanFoBatchExDao;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.AuditingbomExBo;
import com.boco.zg.util.Constants;

@Component
public class AuditingForBatchExBo extends BaseManager<ZgTorderPlanEx,java.lang.String>{
	
	private AuditingbomExBo auditingbomExBo;
	
	private ZgTcarplanBo zgTcarplanBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	private ZgTorderPlanExDao zgTorderPlanExDao;
	private ZgTorderPlanFoBatchExDao zgTorderPlanFoBatchExDao;
	private ZgTorderPlanCommentExDao zgTorderPlanCommentExDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanExDao(ZgTorderPlanExDao dao) {
		this.zgTorderPlanExDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanExDao;
	}
	
	public void setZgTorderPlanFoBatchExDao(
			ZgTorderPlanFoBatchExDao zgTorderPlanFoBatchExDao) {
		this.zgTorderPlanFoBatchExDao = zgTorderPlanFoBatchExDao;
	}
	

	public void updateOrderPlanState(String orderPlanId,String state) {
		zgTorderPlanExDao.updateOrderPlanState(orderPlanId, state);
	}
	
	public void submitOrderPlan(String orderPlanId){
		List<ZgTorderPlanbomEx> list = auditingbomExBo.findBomList(orderPlanId);
		Map<String,ZgTcarplan> map = new HashMap<String,ZgTcarplan>();
		for(ZgTorderPlanbomEx obj : list) {
			String lgort = obj.getLgort();
			ZgTcarplan zgTcarplan = map.get(lgort);
			if(zgTcarplan==null) {
				zgTcarplan = new ZgTcarplan();
				zgTcarplan.setCarState("1");
				zgTcarplanBo.save(zgTcarplan);
				map.put(lgort, zgTcarplan);
			}
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(zgTcarplan.getCuid());
//			bom.setOrderBomId(obj.getOrderBomId());//TODO TASKBOMID
			bom.setOrderPlanbomId(obj.getCuid());
//			bom.setOrderId(obj.getOrderId());
			bom.setPlanNum(Double.parseDouble(obj.getMenge()));
			bom.setRealNum(0d);
			zgTcarbomBo.save(bom);
		}
		updateOrderPlanState(orderPlanId,"8");
	}
	
	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}
	
	/**
	 * 获取批量领料计划
	 * @param pr
	 * @return
	 */
	public Page findBatchPlanByPageRequest(PageRequest<Map> pr) {
		return zgTorderPlanFoBatchExDao.findBatchPlanByPageRequest(pr);
	}
	
	/**
	 * 获取审核信息
	 * @param pr
	 * @return
	 */
	public Page findAuditingByPageRequest(PageRequest<Map> pr) {
		return zgTorderPlanCommentExDao.findAuditingByPageRequest(pr);
	}
	
	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 */
	public boolean saveOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
		return zgTorderPlanFoBatchExDao.saveOrderPlanForBatch(zgTorderPlan);
	}
	
	/**
	 * 提交批量领料计划
	 * 处理逻辑：将该订单的bom组件按按仓库分组，每一个仓库的bom组件生成一张装车计划单
	 *         更新领料计划为提交状态
	 * @param orderPlanId 领料计划单编号
	 * @param operatorInfo 创建人
	 */
	public void submitOrderPlanForBatch(ZgTorderPlan zgTorderPlan,OperatorInfo operatorInfo) {
		//查找所有的仓库
		/*List lgortList = zgTorderPlanbomExBo.findAllLgortByOrderPlanId(zgTorderPlan.getCuid());
		if(lgortList!=null && lgortList.size()>0){
			for(int i =0;i<lgortList.size();i++){
				String lgort = (String)lgortList.get(i);
				String carPlanId=this.saveCarplan(lgort,zgTorderPlan,operatorInfo);
			}
		}*/
		//pageRequest.getFilters().put("key",value);     //add custom filter
		List<ZgTorderPlanbomEx> list = auditingbomExBo.findBomListByPlanId(zgTorderPlan.getCuid());
		String lgortId="";
		String carPlanId="";
		String userId = "";
		for(ZgTorderPlanbomEx obj : list) {
			if(Constants.CarPlanStatus.DONE.value().equals(obj.getState())||null==obj.getUserId()){//该bom组件已经领取，则不需要再添加装车计划 或是领料人为空，直接不生成装车计划
				continue;
			}
			if(!(lgortId.equals(obj.getLgort()) && userId.equals(obj.getUserId()))){//仓库或领料人编号不一样，则创建新的装车单
					lgortId=obj.getLgort();
					userId = obj.getUserId()==null?"":obj.getUserId();
					ZgTcarplan zgTcarplan = new ZgTcarplan(); 
					zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
					zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
					zgTcarplan.setCarUser(obj.getUserId());
					zgTcarplan.setStorageId(obj.getLgort());
//					zgTcarplan.setCarDate(obj.getPlanDate());
					zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
					zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
					zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
					zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
					zgTcarplan.setIsManul(Constants.isNotManulFinished);
					zgTcarplan.setCreateUserName(operatorInfo.getUserName());
					zgTcarplan.setCarUserName(operatorInfo.getUserName());
					carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
				}
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(carPlanId);
			bom.setOrderPlanbomId(obj.getCuid());
			bom.setPlanNum(obj.getAuditNum());
			bom.setRealNum(obj.getAuditNum());
			zgTcarbomBo.save(bom);
		}
	}
	
	private String saveCarplan(String lgort, ZgTorderPlan zgTorderPlan,
			OperatorInfo operatorInfo) {
		ZgTcarplan zgTcarplan = new ZgTcarplan(); 
		zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
		zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
		zgTcarplan.setCarUser(zgTorderPlan.getUserId());
		zgTcarplan.setStorageId(lgort);
		zgTcarplan.setCarDate(zgTorderPlan.getPlanDate());
		zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
		zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
		zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
		String carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
		return carPlanId;
	}
	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 */
	public void saveOrderPlan(ZgTorderPlan zgTorderPlan) {
		zgTorderPlanFoBatchExDao.updateOrderPlanForBatch(zgTorderPlan);
	}
	
	/**
	 * 根据领料计划id删除其相应的装车计划
	 * @param orderPlanId
	 */
	public void deleteCarPlanByOrderPlanId(String orderPlanId) {
		zgTorderPlanFoBatchExDao.deleteCarPlanByOrderPlanId(orderPlanId);
	}
	
	/**
	 * 根据领料计划id删除其相应的装车计划BOM
	 * @param orderPlanId
	 */
	public void deleteCarBomByOrderPlanId(String orderPlanId) {
		zgTorderPlanFoBatchExDao.deleteCarBomByOrderPlanId(orderPlanId);
	}
	public void setAuditingbomExBo(AuditingbomExBo auditingbomExBo) {
		this.auditingbomExBo = auditingbomExBo;
	}
	public void setZgTorderPlanCommentExDao(
			ZgTorderPlanCommentExDao zgTorderPlanCommentExDao) {
		this.zgTorderPlanCommentExDao = zgTorderPlanCommentExDao;
	}

	
	
}
