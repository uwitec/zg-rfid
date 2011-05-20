/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

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

import com.boco.frame.sys.base.model.Province;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ProvinceDao extends BaseIbatisDao<Province,java.lang.Long>{
	public Class getEntityClass() {
		return Province.class;
	}
	
	public void saveOrUpdate(Province entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getLocationid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(Province entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("Province.insertLOCATION",entity);
	}
	
	@Override
	public void update(Province entity){
		super.getSqlMapClientTemplate().insert("Province.updateLOCATION",entity);
	}
	
	@Override
	public void deleteById(java.lang.Long id) {
		super.getSqlMapClientTemplate().delete("Province.deleteLOCATION", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", Province.BM_CLASS_ID);
		return pageQuery("Province.pageSelect",pageRequest);
	}
	
	public List<Province> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", Province.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("Province.pageSelect", parameterObject);
	}

}
