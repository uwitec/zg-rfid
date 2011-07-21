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

import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderPlanGroupDao extends BaseIbatisDao<ZgTorderPlanGroup,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderPlanGroup.class;
	}
	
	public void saveOrUpdate(ZgTorderPlanGroup entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderPlanGroup entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderPlanGroup.insertZG_T_ORDER_PLAN_GROUP",entity);
	}
	
	@Override
	public void update(ZgTorderPlanGroup entity){
		super.getSqlMapClientTemplate().insert("ZgTorderPlanGroup.updateZG_T_ORDER_PLAN_GROUP",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanGroup.deleteZG_T_ORDER_PLAN_GROUP", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlanGroup.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanGroup.pageSelect",pageRequest);
	}
	
	public List<ZgTorderPlanGroup> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlanGroup.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroup.pageSelect", parameterObject);
	}

	/**
	 * 当前天：看到的是当前天之前的没有完成的订单，当前天的订单，当前天之后的提前订单
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderPlanGroup> findGroupByList(PageRequest<Map> pageRequest) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroup.pageSelect",pageRequest.getFilters());
	}
	

	/**
	 * 当前天：当前天的订单
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderPlanGroup> findGroupByList1(PageRequest<Map> pageRequest) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroup.pageSelect1",pageRequest.getFilters());
	}

}
