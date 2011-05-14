/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.materiel.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.zg.virtualorg.base.model.ZgMaterrielVirtualorg;


@Component
public class ZgMaterielDao extends BaseIbatisDao<ZgMateriel,java.lang.String>{
	public Class getEntityClass() {
		return ZgMateriel.class;
	}
	
	public void saveOrUpdate(ZgMateriel entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgMateriel entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		Object primaryKey = super.getSqlMapClientTemplate().insert("ZgMateriel.insertZG_MATERIEL",entity);
		return primaryKey;
	}
	
	@Override
	public void update(ZgMateriel entity){
		super.getSqlMapClientTemplate().insert("ZgMateriel.updateZG_MATERIEL",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgMateriel.deleteZG_MATERIEL_VIRTUALORG", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgMateriel.BM_CLASS_ID);
		return pageQuery("ZgMateriel.pageSelect",pageRequest);
	}
	
	public List<ZgMateriel> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgMateriel.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgMateriel.pageSelect", parameterObject);
	}

	public ZgMaterrielVirtualorg findOneInfoMaterielVirtualorgCuid(
			String materielVirtualorgId) {
		List<ZgMaterrielVirtualorg> zgMaterrielVirtualorgList=this.getSqlMapClientTemplate().queryForList("ZgMaterrielVirtualorg.findOneInfoMaterielVirtualorgCuid", materielVirtualorgId);
		return zgMaterrielVirtualorgList.get(0);
	}

	public String checkAllMaterielName(String materielName) {
		return this.getSqlMapClientTemplate().queryForList("ZgMateriel.checkAllMaterielName",materielName).toString();
	}
	
	public void updateMateriel(ZgMateriel zgMateriel){
		this.getSqlMapClientTemplate().insert("ZgMateriel.updateZG_MATERIEL2", zgMateriel);
	}

	/**
	 * 删除 物料组 的某行信息，用主键cuid
	 */
	public void deleteMaterielByCuid(String cuid) {
		this.getSqlMapClientTemplate().delete("ZgMateriel.deleteZgMaterielByCuid", cuid);
	}
	
	public String saveMateriel(ZgMateriel entity) {
		return super.save(entity).toString();
	}

	public String findLgortByParentId(String parentId) {
		Object object=getSqlMapClientTemplate().queryForObject("ZgMateriel.findLgortByParentId",parentId);
		if(object!=null){
			return object.toString();
		}else{
			return null;
		}
	}

	public String findIdByParentId(String parentId) {
		Object object=getSqlMapClientTemplate().queryForObject("ZgMateriel.findIdByParentId",parentId);
		if(object!=null){
			return object.toString();
		}else{
			return null;
		}
	}
	/**
	 * 删除没有相关数据的物料组
	 * @param cuid
	 */
	public void delMateriel(String cuid) {
		getSqlMapClientTemplate().delete("ZgMateriel.delMateriel",cuid);
	}
	/**
	 * 查找与materiel相关的数据
	 * @param cuid
	 * @return
	 */
	public Number findMaterielDataFromZgMaterielVirtual(String cuid){
		return (Number)getSqlMapClientTemplate().queryForObject("ZgMateriel.getCountZG_MATERIEL_VIRTUALORG",cuid);
	}
	public Number findMaterielDataFromZgTbom(String cuid){
		return (Number)getSqlMapClientTemplate().queryForObject("ZgMateriel.getCountZG_T_BOM",cuid);
	}
	/**
	 * 通过cuid找到物料组信息
	 * @param cuid
	 * @return
	 */
	public ZgMateriel getByCuid(String cuid) {
		return (ZgMateriel)getSqlMapClientTemplate().queryForObject("ZgMateriel.getByCuid",cuid);
	}
	/**
	 * 更新物料组
	 * @param zgMateriel
	 */
	public void updateMaterielForTree(ZgMateriel zgMateriel) {
		getSqlMapClientTemplate().insert("ZgMateriel.updateMateriel",zgMateriel);
	}

	public Number findChildCount(String cuid) {
		return (Number)getSqlMapClientTemplate().queryForObject("ZgMateriel.findChildCount",cuid);
	}
	/**
	 * 新增物料组导航树验证时用到下面2个方法
	 * @param parentId
	 * @return
	 */
	public List findIdForValid(String parentId) {
		return getSqlMapClientTemplate().queryForList("ZgMateriel.findIdForValid",parentId);
	}

	public List findNameForValid(String parentId) {
		return getSqlMapClientTemplate().queryForList("ZgMateriel.findNameForValid",parentId);
	}
	/**
	 * 更新物料组导航树验证时用到下面2个方法
	 * @param cuid
	 * @return
	 */
	public Object findNameByCuid(String cuid) {
		return getSqlMapClientTemplate().queryForObject("ZgMateriel.findNameByCuid",cuid);
	}
	public Object findIdByCuid(String cuid){
		return getSqlMapClientTemplate().queryForObject("ZgMateriel.findIdByCuid",cuid);
	}

	public String findParentId(String cuid) {
		Object object=getSqlMapClientTemplate().queryForObject("ZgMateriel.findParentId",cuid);
		if(object!=null){
			return object.toString();
		}else{
			return null;
		}
	}

	/**
	 * 增加提前领料物料组
	 * @param matId
	 */
	public void addAddvanceMat(String matId) {
		getSqlMapClientTemplate().insert("ZgMateriel.addAddvanceMat",matId);
	}


	

	public int removeVorgById(Map paramsMap) {
		return getSqlMapClientTemplate().delete("ZgMateriel.removeVorgById",paramsMap);
	}

	/**
	 * 获取父物料组的仓库列表
	 * @param id
	 * @return
	 */
	public List<Map> findLgortListById(String id) {
		return getSqlMapClientTemplate().queryForList("ZgMateriel.findLgortListById",id);
	}

	/**
	 * @param id
	 * @param lgort
	 * @return
	 */
	public String getCuidByIdAndLgort(String id, String lgort) {
		Map params=new HashMap();
		params.put("id", id);
		params.put("lgort", lgort);
		Object result=(getSqlMapClientTemplate().queryForObject("ZgMateriel.getCuidByIdAndLgort",params));
		return result==null?"":result.toString();
	}

	/**
	 * @param cuid
	 * @return
	 */
	public List<Map> findLgortListByCuidId(String cuid) {
		return getSqlMapClientTemplate().queryForList("ZgMateriel.findLgortListByCuidId",cuid);
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean validId1(String id) {
		return getSqlMapClientTemplate().queryForList("ZgMateriel.validId1",id).size()==0;
	}



}
