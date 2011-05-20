/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanForBatchEx;


@Component
public class AuditingFoBatchExDao extends BaseIbatisDao<ZgTorderPlanEx,java.lang.String>{
	
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
}
