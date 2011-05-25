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

import com.boco.zg.plan.base.model.ZgTorderTask;



@Component
public class ZgTorderTaskDao extends BaseIbatisDao<ZgTorderTask,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderTask.class;
	}
	
	public void saveOrUpdate(ZgTorderTask entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderTask entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderTask.insertZG_T_ORDER_TASK",entity);
	}
	
	@Override
	public void update(ZgTorderTask entity){
		super.getSqlMapClientTemplate().insert("ZgTorderTask.updateZG_T_ORDER_TASK",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderTask.deleteZG_T_ORDER_TASK", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderTask.BM_CLASS_ID);
		return pageQuery("ZgTorderTask.pageSelect",pageRequest);
	}
	
	public List<ZgTorderTask> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderTask.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderTask.pageSelect", parameterObject);
	}

	
	/**
	 * @param paramsMap
	 * @return
	 */
	public ZgTorderTask findTaskByAufnrArbplPlant(Map paramsMap) {
		List<ZgTorderTask> list=getSqlMapClientTemplate().queryForList("ZgTorderTask.findTaskByAufnrArbplPlant",paramsMap);
		return list.size()==0?null:list.get(0);
	}


	

}
