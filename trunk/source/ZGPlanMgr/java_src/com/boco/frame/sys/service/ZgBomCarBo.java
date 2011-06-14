package com.boco.frame.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.ZgBomCar;
import com.boco.frame.sys.dao.ZgBomCarDao;
import com.boco.frame.sys.model.ExplorerTree;
import com.boco.frame.sys.model.ExplorerTreeNode;
import com.boco.frame.sys.model.TreeParam;
import com.boco.zg.bom.base.model.ZgCarInfo;

@Component
public class ZgBomCarBo extends BaseManager<ZgBomCar,java.lang.String>{

	private ZgBomCarDao zgBomCarDao;
	
	public void setZgBomCarDao(ZgBomCarDao zgBomCarDao) {
		this.zgBomCarDao = zgBomCarDao;
	}
	
	public EntityDao getEntityDao() {
		return this.zgBomCarDao;
	}
	
	public Page findByPageRequestForStoreCar(PageRequest<Map> pageRequest) {
		return zgBomCarDao.findByPageRequest(pageRequest);
	}
	
	public Page findByPageRequestForMateriel(PageRequest<Map> pageRequest) {
		return zgBomCarDao.findByPageRequestForMaterirel(pageRequest);
	}

	public String findParentOrgIdName(String parentOrgId) {
		return zgBomCarDao.findParentOrgIdName(parentOrgId);
	}

	public String findmaterielGroupName(String orgId) {
		return zgBomCarDao.findmaterielGroupName(orgId);
	}
	public List<ZgCarInfo> findCarList(PageRequest<Map> pageRequest){
		return zgBomCarDao.findCarList(pageRequest);
	}
	public void updateByBomId(String bomId,String cuid,String code){
		zgBomCarDao.updateByBomId(bomId,cuid,code);
	}
	public void updateCarNum(ZgBomCar entity){
		zgBomCarDao.updateCarNum(entity);
	}
	public void removeById(String id){
		zgBomCarDao.deleteById(id);
	}
	public Page findCarInfoByPageRequest(PageRequest pageRequest){
		return zgBomCarDao.findCarInfoByPageRequest(pageRequest);
	}

	public void removeByIdNotRealDet(String string) {
		zgBomCarDao.removeByIdNotRealDet(string);
	}
	//设置bom组件的车型及装车数量
	public void updateCarBomNum(ZgBomCar entity){
		zgBomCarDao.updateCarBomNum(entity);
	}

}
