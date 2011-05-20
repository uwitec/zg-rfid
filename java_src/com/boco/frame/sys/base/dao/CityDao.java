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

import com.boco.frame.sys.base.model.City;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class CityDao extends BaseIbatisDao<City,java.lang.Long>{
	public Class getEntityClass() {
		return City.class;
	}
	
	public void saveOrUpdate(City entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getLocationid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(City entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("City.insertLOCATION",entity);
	}
	
	@Override
	public void update(City entity){
		super.getSqlMapClientTemplate().insert("City.updateLOCATION",entity);
	}
	
	@Override
	public void deleteById(java.lang.Long id) {
		super.getSqlMapClientTemplate().delete("City.deleteLOCATION", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", City.BM_CLASS_ID);
		return pageQuery("City.pageSelect",pageRequest);
	}
	
	public List<City> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", City.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("City.pageSelect", parameterObject);
	}

}
