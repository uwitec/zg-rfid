/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.virtualorg.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.virtualorg.base.model.*;
import com.boco.zg.virtualorg.base.dao.*;
import com.boco.zg.virtualorg.base.service.*;

/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTvirtualorgBo extends BaseManager<ZgTvirtualorg,java.lang.String>{
	private ZgTvirtualorgDao zgTvirtualorgDao;
	private ZgTvirtualorgEmployeeDao zgTvirtualorgEmployeeDao;
	private ZgMaterrielVirtualorgDao zgMaterrielVirtualorgDao;
	private ZgTvirtualorgExDao zgTvirtualorgExDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTvirtualorgDao(ZgTvirtualorgDao dao) {
		this.zgTvirtualorgDao = dao;
	}
	public void setZgTvirtualorgEmployeeDao(
			ZgTvirtualorgEmployeeDao zgTvirtualorgEmployeeDao) {
		this.zgTvirtualorgEmployeeDao = zgTvirtualorgEmployeeDao;
	}
	public void setZgMaterrielVirtualorgDao(
			ZgMaterrielVirtualorgDao zgMaterrielVirtualorgDao) {
		this.zgMaterrielVirtualorgDao = zgMaterrielVirtualorgDao;
	}
	public void setZgTvirtualorgExDao(ZgTvirtualorgExDao zgTvirtualorgExDao) {
		this.zgTvirtualorgExDao = zgTvirtualorgExDao;
	}
	public EntityDao getEntityDao() {
		return this.zgTvirtualorgDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTvirtualorgDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTvirtualorgDao.deleteById(id);
	}
	
	public List<ZgTvirtualorg> findByRequest(PageRequest pageRequest) {
		return zgTvirtualorgDao.findByRequest(pageRequest);
	}
	
	/**
	 * 查看领料人
	 * 思路:一定要选择虚拟领料组才能新增，否则提示要选择虚拟领料组
	 * @return
	 */
	public Page findByRequestForEmployee(PageRequest<Map> pageRequest) {
		return zgTvirtualorgEmployeeDao.findByRequest(pageRequest);
	}
	
	
	public void insertZgTvirtualorgEmployee(String virtualorgId,
			String oneUserId) {
		zgTvirtualorgEmployeeDao.insertZgTvirtualorgEmployee(virtualorgId,oneUserId);
	}
	
	/**
	 * 插入 领料组领料人关系表 
	 * @return
	 */
	public void insertZgTvirtualorgMateriel(String virtualorgId,
			String oneMaterielId) {
		zgMaterrielVirtualorgDao.insertZgTvirtualorgMateriel(virtualorgId,oneMaterielId);
	}
	
	public Page findByRequestForVirtualorg(PageRequest<Map> pageRequest){
		return zgTvirtualorgExDao.findByPageRequest(pageRequest);
	}
	
	/**
	 * 查看物料组
	 * 思路:一定要选择虚拟领料组才能新增，否则提示要选择虚拟领料组
	 * @return
	 */
	public Page findByRequestForMateriel(PageRequest<Map> pageRequest) {
		return zgMaterrielVirtualorgDao.findByRequest(pageRequest);
	}
	/**
	 * 删除ZG_T_VIRTUALORG 用于树维护
	 * @param id
	 */
	public void removeVirtualById(java.lang.String id) {
		zgTvirtualorgDao.removeVirtualById(id);
	}
	/**
	 * 查找与ZG_T_VIRTUALORG相关的数据，如果有则返回true 否则返回false
	 * @param cuid
	 * @return
	 */
	public boolean findZgTvirtualorgData(String cuid){
		boolean flag=true;
		List<ZgTvirtualorgEmployee> list1= zgTvirtualorgEmployeeDao.getListById(cuid);//cuid 是ZG_T_VIRTUALORG 的cuid
		if(list1.size()>0){
			flag=false;
			return flag;
		}
		List<ZgMaterrielVirtualorg> list2=zgMaterrielVirtualorgDao.getListById(cuid);
		if(list2.size()>0){//存在关联数据，不能删除
			flag=false;
		}
		return flag;
	}
	public ZgTvirtualorg getByCuid(String cuid){
		return zgTvirtualorgDao.getByCuid(cuid);
	}
	public List<ZgMaterrielVirtualorg> getListById(String cuid){
		return zgMaterrielVirtualorgDao.getListById(cuid);
	}
	
	/**
	 * 获取领料人所在的领料组
	 * @param operatorId
	 * @return
	 */
	public List<ZgTvirtualorgEx> getVirtualOrgByOperatorId(String operatorId) {
		return zgTvirtualorgExDao.getVirtualOrgByOperatorId(operatorId);
	}
	public String saveVirtualorgAndReturnCuid(ZgTvirtualorg zgTvirtualorg) {
		return zgTvirtualorgDao.saveVirtualorgAndReturnCuid(zgTvirtualorg);
	}
	public void deleteZgMaterielVirtualorgByOrgId(String virtualorgId) {
		zgMaterrielVirtualorgDao.deleteZgMaterielVirtualorgByOrgId(virtualorgId);
	}
	/**
	 * 找到同一机构下的所有labelcn，用来添加编辑验证是否同名
	 * 有同名则返回false 否则返回true
	 * @param orgId
	 * @param labelCn
	 * @return
	 */
	public boolean validLabelCnForAdd(String orgId, String labelCn) {
		List list =zgTvirtualorgDao.findLabelCnForValid(orgId);
		if(list.contains(labelCn)){
			return false;
		}
		return true;
	}
	/**
	 * 找到同一机构下的所有labelcn，然后取出本来的需更新对象原来的labelcn
	 * 然后查看在剩余的labelcn中是否有同名
	 * @param cuid
	 * @param orgId
	 * @param labelCn
	 * @return
	 */
	public boolean validLabelCnForUpdate(String cuid, String orgId,String labelCn) {
		List list=zgTvirtualorgDao.findLabelCnForValid(orgId);
		if(orgId.equals(zgTvirtualorgDao.findOrgIdByCuid(cuid))){
			list.remove(zgTvirtualorgDao.getLabelCnByCuid(cuid));
		}
		if(list.contains(labelCn)){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取领料员所能领取的生产厂
	 * @param operatorId
	 * @return
	 */
	public  List<Map> getPlantListByOperatorId(String operatorId,String orderPlanType) {
		return zgTvirtualorgExDao.getPlantListByOperatorId(operatorId,orderPlanType);
	}
	
}
