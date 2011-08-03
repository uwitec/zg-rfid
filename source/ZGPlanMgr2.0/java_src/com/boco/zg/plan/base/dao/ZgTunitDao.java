/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTunit;


@Component
public class ZgTunitDao extends BaseIbatisDao<ZgTunit,java.lang.String>{
	public Class getEntityClass() {
		return ZgTunit.class;
	}
	
	public void saveOrUpdate(ZgTunit entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTunit entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTunit.insertZG_T_UNIT",entity);
	}
	
	@Override
	public void update(ZgTunit entity){
		super.getSqlMapClientTemplate().insert("ZgTunit.updateZG_T_UNIT",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTunit.deleteZG_T_UNIT", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTunit.BM_CLASS_ID);
		return pageQuery("ZgTunit.pageSelect",pageRequest);
	}
	
	public List<ZgTunit> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTunit.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTunit.pageSelect", parameterObject);
	}

}
