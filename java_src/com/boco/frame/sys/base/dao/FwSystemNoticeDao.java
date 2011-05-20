/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.frame.sys.base.model.FwSystemNotice;


@Component
@SuppressWarnings("unchecked")
public class FwSystemNoticeDao extends BaseIbatisDao<FwSystemNotice,java.lang.String>{
	public Class getEntityClass() {
		return FwSystemNotice.class;
	}
	
	public void saveOrUpdate(FwSystemNotice entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(FwSystemNotice entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("FwSystemNotice.insertFW_SYSTEM_NOTICE",entity);
	}
	
	@Override
	public void update(FwSystemNotice entity){
		super.getSqlMapClientTemplate().insert("FwSystemNotice.updateFW_SYSTEM_NOTICE",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("FwSystemNotice.deleteFW_SYSTEM_NOTICE", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwSystemNotice.BM_CLASS_ID);
		return pageQuery("FwSystemNotice.pageSelect",pageRequest);
	}
	
	public List<FwSystemNotice> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwSystemNotice.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("FwSystemNotice.pageSelect", parameterObject);
	}

	/**
	 * 根据用户编号获取其系统公告
	 * @param operatorId
	 * @return
	 */
	public List<FwSystemNotice> getListByOperatorId(String operatorId) {
		Map paramsMap = new HashMap();
		paramsMap.put("operatorId", operatorId);
		return getSqlMapClientTemplate().queryForList("FwSystemNotice.getListByOperatorId",paramsMap);
	}

}
