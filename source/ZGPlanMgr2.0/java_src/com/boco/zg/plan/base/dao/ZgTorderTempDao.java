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

import com.boco.zg.plan.base.model.ZgTorderTemp;



@Component
public class ZgTorderTempDao extends BaseIbatisDao<ZgTorderTemp,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderTemp.class;
	}
	
	public void saveOrUpdate(ZgTorderTemp entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderTemp entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderTemp.insertZG_T_ORDER_TEMP",entity);
	}
	
	@Override
	public void update(ZgTorderTemp entity){
		super.getSqlMapClientTemplate().insert("ZgTorderTemp.updateZG_T_ORDER_TEMP",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderTemp.deleteZG_T_ORDER_TEMP", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderTemp.BM_CLASS_ID);
		return pageQuery("ZgTorderTemp.pageSelect",pageRequest);
	}
	
	public List<ZgTorderTemp> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderTemp.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderTemp.pageSelect", parameterObject);
	}

	/**
	 * @param zgTorderTemp
	 * @return
	 */
	public String saveZgTOrder(ZgTorderTemp zgTorderTemp) {
		return super.getSqlMapClientTemplate().insert("ZgTorderTemp.insertZG_T_ORDER",zgTorderTemp).toString();
	}

	/**
	 * @param zgTorderTemp
	 * @return
	 */
	public int updateZgTOrder(ZgTorderTemp zgTorderTemp) {
		return super.getSqlMapClientTemplate().update("ZgTorderTemp.updateZG_T_ORDER",zgTorderTemp);
	}

	/**
	 * @param sapOrder
	 * @return
	 */
	public int updateZgTOrderForChange(ZgTorderTemp sapOrder) {
		return super.getSqlMapClientTemplate().update("ZgTorderTemp.updateZgTOrderForChange",sapOrder);
	}

}
