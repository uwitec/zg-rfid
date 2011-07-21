package com.boco.zg.plan.service;

import java.util.List;

import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.dao.ZgTorderPlanbomExDao;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;

@Component
public class AuditingbomExBo extends ZgTorderPlanbomBo{
	
	private ZgTorderPlanbomExDao zgTorderPlanbomExDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanbomExDao(ZgTorderPlanbomExDao dao) {
		this.zgTorderPlanbomExDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanbomExDao;
	}
	
	public List<ZgTorderPlanbomEx> findBomList(String orderPlanId) {
		ZgTorderPlanbomEx entity = new ZgTorderPlanbomEx();
		entity.setOrderPlanId("'"+orderPlanId+"'");
		return zgTorderPlanbomExDao.findByProperty(entity);
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
	 * @param cuid 批量领料计划编号
	 */
	public void removeByIdAndBomId(String cuid) {
		zgTorderPlanbomExDao.removeByIdAndBomId(cuid);
	}
	
	/**
	 * 根据批量领料计划单编号查找bom组件列表 按仓库排序返回
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
	
}
