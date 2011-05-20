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

import com.boco.zg.plan.base.model.ZgTorderTaskbom;



@Component
public class ZgTorderTaskbomDao extends BaseIbatisDao<ZgTorderTaskbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderTaskbom.class;
	}
	
	public void saveOrUpdate(ZgTorderTaskbom entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderTaskbom entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderTaskbom.insertZG_T_ORDER_TASKBOM",entity);
	}
	
	@Override
	public void update(ZgTorderTaskbom entity){
		super.getSqlMapClientTemplate().insert("ZgTorderTaskbom.updateZG_T_ORDER_TASKBOM",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderTaskbom.deleteZG_T_ORDER_TASKBOM", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderTaskbom.BM_CLASS_ID);
		return pageQuery("ZgTorderTaskbom.pageSelect",pageRequest);
	}
	
	public List<ZgTorderTaskbom> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderTaskbom.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderTaskbom.pageSelect", parameterObject);
	}

}
