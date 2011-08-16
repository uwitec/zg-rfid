/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;
import com.boco.zg.util.Constants;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderPlanBo extends BaseManager<ZgTorderPlan,java.lang.String>{
	private ZgTorderPlanDao zgTorderPlanDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanDao(ZgTorderPlanDao dao) {
		this.zgTorderPlanDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderPlanDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderPlanDao.deleteById(id);
	}
	
	public List<ZgTorderPlan> findByRequest(PageRequest pageRequest) {
		return zgTorderPlanDao.findByRequest(pageRequest);
	}
	
	public String saveOrderPlan(ZgTorderPlan zgTorderPlan){
		return zgTorderPlanDao.saveOrderPlan(zgTorderPlan);
	}
	public Object saveOrderPlan1(ZgTorderPlan zgTorderPlan) {
		return zgTorderPlanDao.saveOrderPlan1(zgTorderPlan);
	}
	public Page findorderPlanByPageRequest(PageRequest<Map> pageRequest) {
		return zgTorderPlanDao.findorderPlanByPageRequest(pageRequest);
	}
	
	
	/**
	 * 删除领料计划下面的BOM
	 * @param string planID
	 */
	public void deletePlanBomByPlanId(String planID) {
		zgTorderPlanDao.deletePlanBomByPlanId(planID);
	}
	
	/**
	 * @param taskId
	 * @return
	 */
	public ZgTorderPlan getPlanByTaskIdPlanType(String taskId,String planType) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskId", taskId);
		paramsMap.put("planType", planType);
		return zgTorderPlanDao.getPlanByTaskIdPlanType(paramsMap);
	}
	
	/**
	 * //检验申请单是否已经开始领料，
	 * @param planId
	 * @return
	 */
	public boolean isStartCar(String planId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		return zgTorderPlanDao.isStartCar(paramsMap);
	}
	
	/**
	 * 查询变更引起的未审核的换料计划
	 * @param orderTaskId
	 * @return
	 */
	public ZgTorderPlan getPlanNotAudit(String orderTaskId,String materielLevel,String plant) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("orderTaskId", orderTaskId);
		paramsMap.put("planType", Constants.ORDERCHANGESORTF);
		paramsMap.put("materielLevel", materielLevel);
		paramsMap.put("plant", plant);
		List<ZgTorderPlan> list= zgTorderPlanDao.getPlanNotAuditList(paramsMap);
		return list.size()==0?null:list.get(0);
	}
	/**
	 * @return
	 */
	public String getCuid() {
		List<Map> list= zgTorderPlanDao.findDynQuery("SELECT to_char(SYSTIMESTAMP,'yyyymmddhhmissff') cuid FROM DUAL");
		return list.get(0).get("CUID").toString();
	}
	
}
