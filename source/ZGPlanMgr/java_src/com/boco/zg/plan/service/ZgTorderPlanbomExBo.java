package com.boco.zg.plan.service;

import java.util.List;
import java.util.Map;

import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.dao.ZgTorderPlanbomExDao;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.storage.base.model.ZgTstorage;
import com.boco.zg.storage.model.ZgTstoragebomEx;

@Component
public class ZgTorderPlanbomExBo extends ZgTorderPlanbomBo{
	
	private ZgTorderPlanbomExDao zgTorderPlanbomExDao;
	private ZgTorderPlanbom zgTorderPlanbom;
	
	public ZgTorderPlanbom getZgTorderPlanbom() {
		return zgTorderPlanbom;
	}
	public void setZgTorderPlanbom(ZgTorderPlanbom zgTorderPlanbom) {
		this.zgTorderPlanbom = zgTorderPlanbom;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanbomExDao(ZgTorderPlanbomExDao dao) {
		this.zgTorderPlanbomExDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanbomExDao;
	}
	
	public List<ZgTorderPlanbomEx> findBomList(String orderPlanId) {
		ZgTorderPlanbomEx entity = new ZgTorderPlanbomEx();
		entity.setOrderPlanId("'"+orderPlanId+"'");
		List<ZgTorderPlanbomEx> zgTorderPlanbomExList=zgTorderPlanbomExDao.findByProperty(entity);
		
		return zgTorderPlanbomExList;
	}
	
	public List<ZgTorderPlanbomEx> findBomListByGroupId(String groupId) {
		List<ZgTorderPlanbomEx> zgTorderPlanbomExList=zgTorderPlanbomExDao.findBomListByGroupId(groupId);
		return zgTorderPlanbomExList;
	}
	
	public void updateOrderPlan(ZgTorderPlanbom entity) {
		zgTorderPlanbomExDao.updateOrderPlan(entity);
	}
	public List<ZgTorderPlanbomEx> findBomBatchList(String orderPlanId) {
		ZgTorderPlanbomEx entity = new ZgTorderPlanbomEx();
		entity.setOrderPlanId("'"+orderPlanId+"'");
		return zgTorderPlanbomExDao.findBatchByProperty(entity);
	}
	
	public ZgTorderPlanbomEx getOrderPlanbomExById(String id){
		return zgTorderPlanbomExDao.getOrderPlanbomExById(id);
	}
	
	public void update(ZgTorderPlanbomEx zgTorderPlanbom) {
		
	}
	public int updateOrderPlanforBatch(ZgTorderPlanbomEx zgTorderPlanbomEx) {
		return zgTorderPlanbomExDao.updateOrderPlanforBatch(zgTorderPlanbomEx);
	}
	public void saveOrderPlanforBatch(ZgTorderPlanbomEx zgTorderPlanbomEx) {
		zgTorderPlanbomExDao.saveOrderPlanforBatch(zgTorderPlanbomEx);
	}
	
	/**
	 * 根据批量领料计划编号号删除批量领料计划中的bom组件
	 * 
	 * @param cuid
	 *            批量领料计划编号
	 */
	public void removeByIdAndBomId(String cuid) {
		zgTorderPlanbomExDao.removeByIdAndBomId(cuid);
	}
	
	/**
	 * 根据批量领料计划单编号查找bom组件列表 按仓库排序返回
	 * 
	 * @param orderPlanId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByPlanId(String orderPlanId) {
		ZgTorderPlanbomEx entity = new ZgTorderPlanbomEx();
		entity.setOrderPlanId(orderPlanId);
		return zgTorderPlanbomExDao.findBomListByPlanId(entity);
	}
	public String getCUID() {
		// TODO Auto-generated method stub
		return zgTorderPlanbomExDao.getCUID();
	}
	public List findAllLgortByOrderPlanId(String cuid) {
		return zgTorderPlanbomExDao.findAllLgortByOrderPlanId(cuid);
	}
	public void updateZgTorderPlanbomCarNum(ZgTorderPlanbom entity) {
		zgTorderPlanbomExDao.updateZgTorderPlanbomCarNum(entity);
	}
	
	public void updateOrderPlan1(ZgTorderPlanbom entity) {
		zgTorderPlanbomExDao.updateOrderPlan1(entity);
	}
		
	/**
	 * 更新领料计划bom表的计划数量
	 * 
	 * @param orderPlanbomId
	 * @param carPlanNum
	 */
	public boolean updatePlanNum(String orderPlanbomId, long carPlanNum) {
		return zgTorderPlanbomExDao.updatePlanNum(orderPlanbomId,carPlanNum) ;
		
	}
	public boolean updateAuditNum(ZgTorderPlanbomEx zgTorderPlanbomEx) {
		return zgTorderPlanbomExDao.updateAuditNum(zgTorderPlanbomEx) ;
	}
	
	/**
	 * 获取领料计划下的详细bom
	 * 
	 * @param orderPlanId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByOrderPlanId(String orderPlanId) {
		List<ZgTorderPlanbomEx> zgTorderPlanbomExList=zgTorderPlanbomExDao.findBomListByOrderPlanId(orderPlanId);
		return zgTorderPlanbomExList;
	}
	
	/**
	 * 获取领料计划下的详细bom(提前领料的bom)
	 * 
	 * @param orderPlanId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByOrderPlanIdAdvance(
			String orderPlanId) {
		List<ZgTorderPlanbomEx> zgTorderPlanbomExList=zgTorderPlanbomExDao.findBomListByOrderPlanIdAdvance(orderPlanId);
		return zgTorderPlanbomExList;
	}
	
	/**
	 * 查找订单分组下面的详细bom(提前领料的分组bom)
	 * 
	 * @param groupId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByGroupIdAdvance(String groupId) {
		List<ZgTorderPlanbomEx> zgTorderPlanbomExList=zgTorderPlanbomExDao.findBomListByGroupIdAdvance(groupId);
		return zgTorderPlanbomExList;
	}
	
	/**
	 * 设置物料为领料完成
	 * 
	 * @param cuid
	 */
	public boolean finishBom(String planBomId) {
		return zgTorderPlanbomExDao.finishBom(planBomId);
	}
	/**
	 * @param orderId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByOrderId(String orderId) {
		List<ZgTorderPlanbomEx> zgTorderPlanbomExList=zgTorderPlanbomExDao.findBomListByOrderId(orderId);
		return zgTorderPlanbomExList;
	}
	
}
