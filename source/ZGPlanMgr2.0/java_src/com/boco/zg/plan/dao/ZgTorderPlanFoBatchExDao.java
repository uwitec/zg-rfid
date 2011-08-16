/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanForBatchEx;
import com.boco.frame.sys.base.model.FwEmployee;


@Component
public class ZgTorderPlanFoBatchExDao extends BaseIbatisDao<ZgTorderPlanEx,java.lang.String>{
	
	public Class getEntityClass() {
		return ZgTorderPlanForBatchEx.class;
	}
	

	/**
	 * 获取批量领料计划
	 * @param pr
	 * @return
	 */
	public Page findBatchPlanByPageRequest(PageRequest<Map> pr) {
		((Map)pr.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanForBatchEx.pageSelectBatchPlan",pr);
	}
	
	

	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 * @return
	 */
	public boolean saveOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
//		return this.getSqlMapClientTemplate().insert("",zgTorderPlan)==1;
		return true;
	}
	
	/**
	 * wjz,查找出审核人信息
	 * @param zgTorderPlan
	 */
	public List<FwEmployee> findAllAuditingPeople(PageRequest<Map> pageRequest){
		return this.getSqlMapClientTemplate().queryForList("FwEmployee.findTheAuitingPeople",pageRequest.getFilters());
	}
	
	
	

	public void updateOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
		this.getSqlMapClientTemplate().update("ZgTorderPlanForBatchEx.updateOrderPlanForBatch",zgTorderPlan);
	}

	/**
	 *  根据领料计划id删除其相应的装车计划
	 * @param orderPlanId
	 */
	public void deleteCarPlanByOrderPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.delete_ZG_T_Carplan_By_OrderplanId",orderPlanId);
	}

	/**
	 * 根据领料计划id删除其相应的装车计划BOM 
	 * @param orderPlanId
	 */
	public void deleteCarBomByOrderPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.delete_ZG_T_Carbom_By_OrderplanId",orderPlanId);
	}


	public void saveOrUpdate(ZgTorderPlanEx entity) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 根据领料计划id删除领料计划
	 * @param orderPlanId
	 */
	public void deleteOrderPlan(String orderPlanId){
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.deleteOrderPlan",orderPlanId);
	}
	/**
	 * 
	 * 根据批量领料计划id删除BOM
	 * @param orderPlanId
	 */
	public void deleteOrderPlanBomByPlanId(String orderPlanId){
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.deleteOrderPlanBomByPlanId",orderPlanId);
	}
	/**
	 * 根据领料计划ID删除相应的COMMENT
	 * @param orderPlanId
	 */
	public void deleteOrderPlanCommentByPlanId(String orderPlanId){
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.deleteOrderPlanCommentByPlanId",orderPlanId);
	}


	/**
	 * @param orderPlanId
	 * @return
	 */
	public boolean isStartCar(String orderPlanId) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanForBatchEx.isStartCar",orderPlanId).size()>0;
	}


	/**
	 * @param orderPlanId
	 */
	public void deleteNotStartCarPlanByPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.deleteNotStartCarPlanByPlanId",orderPlanId);
	}


	/**
	 * @param orderPlanId
	 */
	public void deleteNotStartPlanBomByPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.deleteNotStartPlanBomByPlanId",orderPlanId);
		
	}


	/**
	 * @param orderPlanId
	 */
	public void deleteCarPlanByPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanForBatchEx.deleteCarPlanByPlanId",orderPlanId);
		
	}
	
}
