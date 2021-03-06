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

import com.boco.zg.plan.base.model.ZgTorderbomMoveLog;


@Component
public class ZgTorderbomMoveLogDao extends BaseIbatisDao<ZgTorderbomMoveLog,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderbomMoveLog.class;
	}
	
	public void saveOrUpdate(ZgTorderbomMoveLog entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderbomMoveLog entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderbomMoveLog.insertZG_T_ORDERBOM_MOVE_LOG",entity);
	}
	
	@Override
	public void update(ZgTorderbomMoveLog entity){
		super.getSqlMapClientTemplate().insert("ZgTorderbomMoveLog.updateZG_T_ORDERBOM_MOVE_LOG",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderbomMoveLog.deleteZG_T_ORDERBOM_MOVE_LOG", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderbomMoveLog.BM_CLASS_ID);
		return pageQuery("ZgTorderbomMoveLog.pageSelect",pageRequest);
	}
	
	public List<ZgTorderbomMoveLog> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderbomMoveLog.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderbomMoveLog.pageSelect", parameterObject);
	}

}
