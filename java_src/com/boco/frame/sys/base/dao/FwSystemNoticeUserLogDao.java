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

import com.boco.frame.sys.base.model.FwSystemNoticeUserLog;


@Component
public class FwSystemNoticeUserLogDao extends BaseIbatisDao<FwSystemNoticeUserLog,java.lang.String>{
	public Class getEntityClass() {
		return FwSystemNoticeUserLog.class;
	}
	
	public void saveOrUpdate(FwSystemNoticeUserLog entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(FwSystemNoticeUserLog entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("FwSystemNoticeUserLog.insertFW_SYSTEM_NOTICE_USER_LOG",entity);
	}
	
	@Override
	public void update(FwSystemNoticeUserLog entity){
		super.getSqlMapClientTemplate().insert("FwSystemNoticeUserLog.updateFW_SYSTEM_NOTICE_USER_LOG",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("FwSystemNoticeUserLog.deleteFW_SYSTEM_NOTICE_USER_LOG", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwSystemNoticeUserLog.BM_CLASS_ID);
		return pageQuery("FwSystemNoticeUserLog.pageSelect",pageRequest);
	}
	
	public List<FwSystemNoticeUserLog> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwSystemNoticeUserLog.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("FwSystemNoticeUserLog.pageSelect", parameterObject);
	}

}
