package com.boco.frame.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javacommon.base.BaseIbatisDao;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwMenu;
import com.boco.frame.sys.base.model.ZgBomCar;
import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.zg.bom.base.model.ZgCarInfo;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanForBatchEx;

@Component
public class ZgBomCarDao extends BaseIbatisDao<ZgBomCar,java.lang.String>{
	public Class getEntityClass() {
		return ZgBomCar.class;
	}

	
	
	public Page findByPageRequest(PageRequest pageRequest){
		if(((Map)pageRequest.getFilters()).get("orgId").toString()!=""){
			Long type=findMaterielType(((Map)pageRequest.getFilters()).get("orgId").toString());
			((Map)pageRequest.getFilters()).put("zgBomCarType", type);
		}
		return pageQuery("ZgBomCar.pageSelect",pageRequest);
	}

	/**
	 * 查看某物料组是仓库还是仓库
	 * 1　仓库
	 * 2　物料组　
	 */
	public Long findMaterielType(String zgMaterielCuid){
		ZgMateriel zgMateriel=new ZgMateriel();
		zgMateriel.setCuid(zgMaterielCuid);
		List<ZgMateriel> zgMaterielList=this.getSqlMapClientTemplate().queryForList("ZgBomCar.findTheZgMaterielType",zgMateriel);
		Long typeNum=new Long(0);
		if(zgMaterielList.size()!=0)
			typeNum=zgMaterielList.get(0).getType();
		return typeNum;
	}


	public void saveOrUpdate(ZgBomCar entity) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 查看仓库名字
	 * @param parentOrgId 父id的cuid
	 * @return
	 */
	public String findParentOrgIdName(String parentOrgId) {
		ZgMateriel zgMateriel=new ZgMateriel();
		zgMateriel.setParentId(parentOrgId);
		List<ZgMateriel> zgMaterielList=this.getSqlMapClientTemplate().queryForList("ZgBomCar.findParentOrgIdNameByCuid", zgMateriel);
		return zgMaterielList.get(0).getMaterielName();
	}



	public String findmaterielGroupName(String orgId) {
		ZgMateriel zgMateriel=new ZgMateriel();
		zgMateriel.setId(orgId);
		List<ZgMateriel> zgMaterielList=this.getSqlMapClientTemplate().queryForList("ZgBomCar.findmaterielGroupNameById",zgMateriel);
		return zgMaterielList.get(0).getMaterielName();
	}
	public List<ZgCarInfo> findCarList(PageRequest<Map> pageRequest){
		return this.getSqlMapClientTemplate().queryForList("ZgCarInfo.findCarList",pageRequest.getFilters());
	}
	
	public void updateByBomId(String bomId,String cuid,String code){
		ZgBomCar entity=new ZgBomCar();
		entity.setBomId(bomId);
		entity.setCarinfoId(cuid);
		super.update(entity);
	}
	
	public void  updateCarNum(ZgBomCar entity){
		super.prepareObjectForSaveOrUpdate(entity);
		Object primaryKey = getSqlMapClientTemplate().update("ZgBomCar.updateCarNum", entity);
	}
	public Page findCarInfoByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgCarInfo.BM_CLASS_ID);
		String statementName="ZgCarInfo.pageSelect";
		Number totalCount = (Number) this.getSqlMapClientTemplate().queryForObject("ZgCarInfo.count",pageRequest.getFilters());
		Page page = new Page(pageRequest,totalCount.intValue());
		//其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
		Map otherFilters = new HashMap();
		otherFilters.put("offset", page.getFirstResult());
		otherFilters.put("pageSize", page.getPageSize());
		otherFilters.put("lastRows", page.getFirstResult() + page.getPageSize());
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		
		//混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		List list = getSqlMapClientTemplate().queryForList(statementName, parameterObject,page.getFirstResult(),page.getPageSize());
		page.setResult(list);
		return page;
	}

	public void removeByIdNotRealDet(String string) {
		getSqlMapClientTemplate().update("ZgTbom.deleteButNotRealDet", string);
	}
	/**
	 * 更新bom组件的车型信息和装车数量
	 * @param entity
	 */
	public void updateCarBomNum(ZgBomCar entity) {
		super.prepareObjectForSaveOrUpdate(entity);
		Object primaryKey = getSqlMapClientTemplate().update("ZgBomCar.updateCarBomNum", entity);		
	}

	
}
