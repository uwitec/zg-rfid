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

import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTcarplanDao extends BaseIbatisDao<ZgTcarplan,java.lang.String>{
	public Class getEntityClass() {
		return ZgTcarplan.class;
	}
	
	public void saveOrUpdate(ZgTcarplan entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTcarplan entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTcarplan.insertZG_T_CARPLAN",entity);
	}
	
	@Override
	public void update(ZgTcarplan entity){
		super.getSqlMapClientTemplate().insert("ZgTcarplan.updateZG_T_CARPLAN",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTcarplan.deleteZG_T_CARPLAN", id);
	}
	
	/**
	 *  通过装车单ID删除装车BOM
	 */
	public void deleteCarBom(java.lang.String id){
		super.getSqlMapClientTemplate().delete("ZgTcarplan.deleteZG_T_CARBOM",id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarplan.BM_CLASS_ID);
		return pageQuery("ZgTcarplan.pageSelect",pageRequest);
	}
	
	public List<ZgTcarplan> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarplan.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTcarplan.pageSelect", parameterObject);
	}

	/**
	 * 批量领料生成的装车计划 主键以A开头
	 * @param zgTcarplan
	 * @return
	 */
	public Object saveCarplan(ZgTcarplan zgTcarplan) {
		BeanUtils.setProperty(zgTcarplan, "relatedBmClassId", zgTcarplan.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTcarplan.insertZG_T_CARPLAN_Auto",zgTcarplan);
	}
	
	/**
	 * 预装 预焊 总装 生成的装车计划 主键以M开头
	 * @param zgTcarplan
	 * @return
	 */
	public Object saveCarplan1(ZgTcarplan zgTcarplan) {
		BeanUtils.setProperty(zgTcarplan, "relatedBmClassId", zgTcarplan.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTcarplan.insertZG_T_CARPLAN_Manual",zgTcarplan);
	}

	/**
	 * 预装 预焊 总装 生成的装车计划 主键以M开头
	 * @param zgTcarplan
	 * @return
	 */
	public Object saveCarplan2(ZgTcarplan zgTcarplan) {
		BeanUtils.setProperty(zgTcarplan, "relatedBmClassId", zgTcarplan.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTcarplan.insertZG_T_CARPLAN_Manual1",zgTcarplan);
	}
	
	/**
	 * 根据用户ID删除其装车计划单
	 * @param planType
	 * @param operatorId
	 */
	public void deleteCarByOperatorId(String planType, String operatorId) {
		Map params=new HashMap();
		params.put("planType", planType);
		params.put("operatorId", operatorId);
		super.getSqlMapClientTemplate().delete("ZgTcarplan.deleteCarByOperatorId",params);
		
	}

	/**
	 * 更新领料状态
	 * @param zgTcarplan
	 */
	public void updateState(ZgTcarplan zgTcarplan) {
		super.getSqlMapClientTemplate().update("ZgTcarplan.updateState",zgTcarplan);
	}

	/**
	 * @param pageRequest
	 * @return
	 */
	public Page findByPageRequest2(PageRequest<Map> pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarplan.BM_CLASS_ID);
		return pageQuery("ZgTcarplan.pageSelect2","ZgTcarplan.count2",pageRequest);
	}

	/**
	 * @param num
	 * @return
	 */
	public String getCuid(int num) {
		Map params=new HashMap();
		params.put("num", num);
		return getSqlMapClientTemplate().queryForObject("ZgTcarplan.getCuid",params).toString();
	}

	/**
	 * @param zgTcarplan
	 */
	public void saveCarplan3(ZgTcarplan zgTcarplan) {
		super.getSqlMapClientTemplate().insert("ZgTcarplan.insertZG_T_CARPLAN_Auto1",zgTcarplan);
	}

}
