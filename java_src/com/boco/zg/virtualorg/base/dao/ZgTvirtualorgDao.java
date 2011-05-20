/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.virtualorg.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.virtualorg.base.model.ZgTvirtualorg;


@Component
public class ZgTvirtualorgDao extends BaseIbatisDao<ZgTvirtualorg,java.lang.String>{
	public Class getEntityClass() {
		return ZgTvirtualorg.class;
	}
	
	public void saveOrUpdate(ZgTvirtualorg entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTvirtualorg entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTvirtualorg.insertZG_T_VIRTUALORG",entity);
	}
	
	@Override
	public void update(ZgTvirtualorg entity){
		super.getSqlMapClientTemplate().insert("ZgTvirtualorg.updateZG_T_VIRTUALORG",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTvirtualorg.deletezg_t_virtualorg_employee", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTvirtualorg.BM_CLASS_ID);
		return pageQuery("ZgTvirtualorg.pageSelect",pageRequest);
	}
	
	
	public List<ZgTvirtualorg> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTvirtualorg.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTvirtualorg.pageSelect", parameterObject);
	}
	public void removeVirtualById(java.lang.String cuid) {
		super.getSqlMapClientTemplate().delete("ZgTvirtualorg.deleteZG_T_VIRTUALORG", cuid);
	}

	public ZgTvirtualorg getByCuid(String cuid) {
		return (ZgTvirtualorg)getSqlMapClientTemplate().queryForObject("ZgTvirtualorg.getByCuid", cuid);
	}

	public String saveVirtualorgAndReturnCuid(ZgTvirtualorg zgTvirtualorg) {
		return super.save(zgTvirtualorg).toString();
	}
	/**
	 * 找到同一机构下的所有labelcn，用来添加编辑验证是否同名
	 * @param orgId
	 * @return
	 */
	public List findLabelCnForValid(String orgId) {
		return getSqlMapClientTemplate().queryForList("ZgTvirtualorg.findLabelCnForValid",orgId);
	}
	
	/**
	 * 通过cuid获取labelcn
	 * @param cuid
	 * @return
	 */
	public Object getLabelCnByCuid(String cuid) {
		return getSqlMapClientTemplate().queryForObject("ZgTvirtualorg.getLabelCnByCuid",cuid);
	}

	public Object findOrgIdByCuid(String cuid) {
		return getSqlMapClientTemplate().queryForObject("ZgTvirtualorgEx.findOrgIdByCuid",cuid);
	}
}
