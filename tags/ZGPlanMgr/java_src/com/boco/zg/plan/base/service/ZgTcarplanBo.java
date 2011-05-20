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
import com.boco.zg.plan.service.ZgTcarbomExBo;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTcarplanBo extends BaseManager<ZgTcarplan,java.lang.String>{
	protected ZgTcarplanDao zgTcarplanDao;
	private ZgTcarbomExBo zgTcarbomExBo;
	ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTcarplanDao(ZgTcarplanDao dao) {
		this.zgTcarplanDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTcarplanDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTcarplanDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTcarplanDao.deleteById(id);
	}
	
	public List<ZgTcarplan> findByRequest(PageRequest pageRequest) {
		return zgTcarplanDao.findByRequest(pageRequest);
	}
	
	/**
	 * 批量领料生成的装车计划 主键以A开头
	 * @param zgTcarplan
	 * @return
	 */
	public String saveCarplan(ZgTcarplan zgTcarplan) {
		return zgTcarplanDao.saveCarplan(zgTcarplan).toString();
		
	}
	
	/**
	 * 预装 预焊 总装 生成的装车计划 主键以M开头
	 * @param zgTcarplan
	 * @return
	 */
	public String saveCarplan1(ZgTcarplan zgTcarplan) {
		return zgTcarplanDao.saveCarplan1(zgTcarplan).toString();

	}
	
	/**
	 * 预装 预焊 总装 生成的装车计划 主键以M开头
	 * @param zgTcarplan
	 * @return
	 */
	public void saveCarplan2(ZgTcarplan zgTcarplan) {
		zgTcarplanDao.saveCarplan2(zgTcarplan);
	}
	/**
	 * 删除待调整和保存状态的装车单
	 * modify by wengq 2020/06/17: 添加逻辑：删除bom组件的时候改变orderplanbom的计划数量
	 * @param carPlanId
	 */
	public void deleteCarPlan(String carPlanId){
		// <!--modify by wengq 2020/06/17: 添加逻辑：删除bom组件的时候改变orderplanbom的计划数量
		List<Map> list = zgTcarbomExBo.findCarPlanBomByCarPlanId(carPlanId);
		for(Map map:list){
			String orderPlanbomId=map.get("ORDER_PLANBOM_ID").toString();
			long carPlanNum=Long.parseLong(map.get("CAR_PLAN_NUM").toString());
			zgTorderPlanbomExBo.updatePlanNum(orderPlanbomId,carPlanNum);
		}
		//!-->
		zgTcarplanDao.deleteCarBom(carPlanId);
		zgTcarplanDao.deleteById(carPlanId);
	}
	public ZgTcarbomExBo getZgTcarbomExBo() {
		return zgTcarbomExBo;
	}
	public void setZgTcarbomExBo(ZgTcarbomExBo zgTcarbomExBo) {
		this.zgTcarbomExBo = zgTcarbomExBo;
	}
	public ZgTorderPlanbomExBo getZgTorderPlanbomExBo() {
		return zgTorderPlanbomExBo;
	}
	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}
	
	/**
	 * 根据用户ID删除其装车计划单
	 * @param planType
	 * @param operatorId
	 */
	public void deleteCarByOperatorId(String planType, String operatorId) {
		zgTcarplanDao.deleteCarByOperatorId(planType,operatorId);
		
	}
	
	/**
	 * 更新领料状态
	 * @param zgTcarplan
	 */
	public void updateState(ZgTcarplan zgTcarplan) {
		zgTcarplanDao.updateState(zgTcarplan);
		
	}
	/**
	 * @param pageRequest
	 * @return
	 */
	public Page findByPageRequest2(PageRequest<Map> pageRequest) {
		return zgTcarplanDao.findByPageRequest2(pageRequest);
	}
	/**
	 * @param num
	 * @return
	 */
	public String getCuid(int num) {
		return zgTcarplanDao.getCuid(num);
	}
	/**
	 * @param zgTcarplan
	 */
	public void saveCarplan3(ZgTcarplan zgTcarplan) {
		zgTcarplanDao.saveCarplan3(zgTcarplan);
	}
	
	
}
