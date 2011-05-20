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

import com.boco.frame.sys.base.model.GenericLocation;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class GenericLocationDao extends BaseIbatisDao<GenericLocation,java.lang.Long>{
	public Class getEntityClass() {
		return GenericLocation.class;
	}
	
	public void saveOrUpdate(GenericLocation entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getLocationid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(GenericLocation entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("GenericLocation.insertLOCATION",entity);
	}
	
	@Override
	public void update(GenericLocation entity){
		super.getSqlMapClientTemplate().insert("GenericLocation.updateLOCATION",entity);
	}
	
	@Override
	public void deleteById(java.lang.Long id) {
		super.getSqlMapClientTemplate().delete("GenericLocation.deleteLOCATION", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", GenericLocation.BM_CLASS_ID);
		return pageQuery("GenericLocation.pageSelect",pageRequest);
	}
	
	public List<GenericLocation> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", GenericLocation.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("GenericLocation.pageSelect", parameterObject);
	}

}
