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

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderPlanbomDao extends BaseIbatisDao<ZgTorderPlanbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderPlanbom.class;
	}
	
	public void saveOrUpdate(ZgTorderPlanbom entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderPlanbom entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderPlanbom.insertZG_T_ORDER_PLANBOM",entity);
	}
	
	@Override
	public void update(ZgTorderPlanbom entity){
		super.getSqlMapClientTemplate().insert("ZgTorderPlanbom.updateZG_T_ORDER_PLANBOM",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanbom.deleteZG_T_ORDER_PLANBOM", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlanbom.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanbom.pageSelect",pageRequest);
	}
	
	public List<ZgTorderPlanbom> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlanbom.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.pageSelect", parameterObject);
	}

	/**
	 * 获取待退料的bom
	 * @param aufnr
	 * @param matnr
	 * @return
	 */
	public List<ZgTorderPlanbom> getWaitBackBomListByAufnrIdnrk(String aufnr,
			String matnr) {
		Map paramsMap = new HashMap();
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("idnrk", matnr);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.getWaitBackBomListByAufnrIdnrk",paramsMap);
	}

	public Object save1(ZgTorderPlanbom zgTorderPlanbom) {
		return super.getSqlMapClientTemplate().insert("ZgTorderPlanbom.insertZG_T_ORDER_PLANBOM1",zgTorderPlanbom);
	}


	/**
	 * @param paramsMap
	 * @return
	 */
	public ZgTorderPlanbom getPlanBomByTaskIdPlanTypeTaskBomId(Map paramsMap) {
		List<ZgTorderPlanbom> list =getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.getPlanBomByTaskIdPlanTypeTaskBomId",paramsMap);
		return list.size()>0?list.get(0):null;
	}
	
	/**
	 * @param paramsMap
	 * @return
	 */
	public List<ZgTorderPlanbom> getPlanBomTaskBomId(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.getPlanBomByTaskIdPlanTypeTaskBomId",paramsMap);
	}


	/**
	 * @param planId
	 * @param taskBomId
	 * @return
	 */
	public ZgTorderPlanbom getPlanBomByPlanIdTaskBomId(Map paramsMap) {
		List<ZgTorderPlanbom> list =getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.getPlanBomByPlanIdTaskBomId",paramsMap);
		return list.size()>0?list.get(0):null;
	}

	/**
	 * 根据taskid，plantype设置其他领料计划BOM的需求数量为0 并设置退料数据
	 * @param paramsMap
	 */
	public void updatePlanBomCarNumTo0ByPlanId(Map paramsMap) {
		getSqlMapClientTemplate().update("ZgTorderPlanbom.updatePlanBomCarNumTo0ByPlanId",paramsMap);
	}
	
	/**
	 * 根据taskid，plantype设置其他领料计划BOM的需求数量为0 并设置退料数据
	 * @param paramsMap
	 */
	public void delPlanBomPlanId(Map paramsMap) {
		getSqlMapClientTemplate().delete("ZgTorderPlanbom.delPlanBomPlanId",paramsMap);
	}

	/**
	 * @param paramsMap
	 * @return
	 */
	public int deletePlanBomByOrderBomId(Map paramsMap) {
		return getSqlMapClientTemplate().delete("ZgTorderPlanbom.deletePlanBomByOrderBomId",paramsMap);
	}

	/**
	 * @param paramsMap
	 * @return
	 */
	public  List<ZgTorderPlanbom> getPlanBomByOrderBomId(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.getPlanBomByOrderBomId",paramsMap);
	}
	
	

}
