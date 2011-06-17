/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderPlanDao extends BaseIbatisDao<ZgTorderPlan,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderPlan.class;
	}
	
	public void saveOrUpdate(ZgTorderPlan entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderPlan entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderPlan.insertZG_T_ORDER_PLAN",entity);
	}
	
	@Override
	public void update(ZgTorderPlan entity){
		super.getSqlMapClientTemplate().insert("ZgTorderPlan.updateZG_T_ORDER_PLAN",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlan.deleteZG_T_ORDER_PLAN", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		return pageQuery("ZgTorderPlan.pageSelect",pageRequest);
	}
	
	public List<ZgTorderPlan> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderPlan.pageSelect", parameterObject);
	}

	public String saveOrderPlan(ZgTorderPlan zgTorderPlan) {
		BeanUtils.setProperty(zgTorderPlan, "relatedBmClassId", zgTorderPlan.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderPlan.insertZG_T_ORDER_PLAN",zgTorderPlan).toString();
	}

	public Object saveOrderPlan1(ZgTorderPlan zgTorderPlan) {
		return super.getSqlMapClientTemplate().insert("ZgTorderPlan.insertZG_T_ORDER_PLAN1",zgTorderPlan);
	}
	public Page findorderPlanByPageRequest(PageRequest<Map> pr) {
		
		return pageQuery("ZgTorderPlan.pageSelect1",pr);
	}

	/**
	 * 删除领料计划下面的BOM
	 * @param planID
	 */
	public void deletePlanBomByPlanId(String planID) {
		getSqlMapClientTemplate().delete("ZgTorderPlan.deletePlanBomByPlanId", planID);
	}

	/**
	 * @param taskId
	 * @return
	 */
	public ZgTorderPlan getPlanByTaskIdPlanType(Map paramsMap) {
		List<ZgTorderPlan> list= getSqlMapClientTemplate().queryForList("ZgTorderPlan.getPlanByTaskId",paramsMap);
		return list.size()>0?list.get(0):null;
	}

	/**
	 * 检验申请单是否已经开始领料，
	 * @param paramsMap
	 * @return
	 */
	public boolean isStartCar(Map paramsMap) {
		List<ZgTorderPlan> list= getSqlMapClientTemplate().queryForList("ZgTorderPlan.isStartCar",paramsMap);
		return list.size()>0;
	}
}
