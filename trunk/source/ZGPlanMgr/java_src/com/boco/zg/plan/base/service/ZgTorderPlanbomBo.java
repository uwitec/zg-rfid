/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import org.springframework.stereotype.Component;

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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderPlanbomBo extends BaseManager<ZgTorderPlanbom,java.lang.String>{
	private ZgTorderPlanbomDao zgTorderPlanbomDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanbomDao(ZgTorderPlanbomDao dao) {
		this.zgTorderPlanbomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanbomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderPlanbomDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderPlanbomDao.deleteById(id);
	}
	
	public int removeById1(java.lang.String id) {
		return zgTorderPlanbomDao.deleteById1(id);
	}
	
	public List<ZgTorderPlanbom> findByRequest(PageRequest pageRequest) {
		return zgTorderPlanbomDao.findByRequest(pageRequest);
	}
	public Object save1(ZgTorderPlanbom zgTorderPlanbom) {
		return zgTorderPlanbomDao.save1(zgTorderPlanbom);
		
	}
	
	
	
	/**
	 * @param taskId
	 * @param sortf
	 * @param cuid
	 * @return
	 */
	public ZgTorderPlanbom getPlanBomByTaskIdPlanTypeTaskBomId(String taskId,String planType, String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskId", taskId);
		paramsMap.put("planType", planType);
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getPlanBomByTaskIdPlanTypeTaskBomId(paramsMap);
	}
	
	/**
	 * @param taskId
	 * @param sortf
	 * @param cuid
	 * @return
	 */
	public List<ZgTorderPlanbom> getPlanBomTaskBomId(String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getPlanBomTaskBomId(paramsMap);
	}
	
	
	/**
	 * @param cuid
	 * @param cuid2
	 * @return
	 */
	public ZgTorderPlanbom getPlanBomByPlanIdTaskBomId(String planId, String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getPlanBomByPlanIdTaskBomId(paramsMap);
	}

	/**
	 * 根据taskid，plantype设置其他领料计划BOM的需求数量为0 并设置退料数据
	 * @param taskId
	 * @param planType
	 */
	public void updatePlanBomCarNumTo0ByPlanId(String planId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		zgTorderPlanbomDao.updatePlanBomCarNumTo0ByPlanId(paramsMap);
		
	}
	
	/**
	 * 根据taskid，plantype删除其bom
	 * @param taskId
	 * @param planType
	 */
	public void delPlanBomPlanId(String planId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		zgTorderPlanbomDao.delPlanBomPlanId(paramsMap);
		
	}
	/**
	 * @param bomId
	 */
	public int deletePlanBomByOrderBomId(String bomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("bomId", bomId);
		return zgTorderPlanbomDao.deletePlanBomByOrderBomId(paramsMap);
		
	}
	/**
	 * @param bomId
	 * @return
	 */
	public  List<ZgTorderPlanbom> getPlanBomByOrderBomId(String bomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("bomId", bomId);
		return zgTorderPlanbomDao.getPlanBomByOrderBomId(paramsMap);
	}
	/**
	 * 判断某个物料是否已经开始领料
	 * @param bomId
	 * @return
	 */
	public boolean isStartCar(String bomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("bomId", bomId);
		boolean isStartCar=false;
		List<ZgTorderPlanbom> planBomList= zgTorderPlanbomDao.getPlanBomByOrderBomId(paramsMap);
		for(ZgTorderPlanbom bom:planBomList){
			if(bom.getPlanNum()>0){
				isStartCar=true;
				break;
			}
		}
		return isStartCar;
	}
	
	
	
}
