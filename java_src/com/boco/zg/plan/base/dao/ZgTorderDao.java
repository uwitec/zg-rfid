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

import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderDao extends BaseIbatisDao<ZgTorder,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorder.class;
	}
	
	public void saveOrUpdate(ZgTorder entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorder entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorder.insertZG_T_ORDER",entity);
	}
	
	@Override
	public void update(ZgTorder entity){
		super.getSqlMapClientTemplate().insert("ZgTorder.updateZG_T_ORDER",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorder.deleteZG_T_ORDER", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {//operatorId
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorder.BM_CLASS_ID);
		return pageQuery("ZgTorder.pageSelect",pageRequest);
	}
	
	public List<ZgTorder> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorder.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorder.pageSelect", parameterObject);
	}

	/**
	 * @param pr
	 * @return
	 */
	public Page findByPageRequest1(PageRequest<Map> pr) {
		((Map)pr.getFilters()).put("BM_CLASS_ID", ZgTorder.BM_CLASS_ID);
		return pageQuery("ZgTorder.pageSelect","ZgTorder.count1",pr);
	}

}
