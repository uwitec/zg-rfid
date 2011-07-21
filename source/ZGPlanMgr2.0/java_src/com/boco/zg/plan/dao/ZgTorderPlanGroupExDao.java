/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderPlanGroupExDao extends ZgTorderPlanGroupDao{
	public Class getEntityClass() {
		return ZgTorderPlanGroup.class;
	}
	

	public void saveOrUpdate(ZgTorderPlanGroup entity) {
		// TODO Auto-generated method stub
		
	}
	
	
    /**
	 * 改变订单领料状态
     * @param id
     * @param state
     * @return
     */
	public int changeOrderState(String id, String state) {
		ZgTorderPlanGroup zgTorderPlanGroup=new ZgTorderPlanGroup();
		zgTorderPlanGroup.setCuid(id);
		zgTorderPlanGroup.setState(state);
		return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.updateZG_T_ORDER_PLAN_GROUP_state",zgTorderPlanGroup);
	}


	/**
	 * 把排序号排在psbh后面的订单统一前移一位
	 * @param psbh 排序号
	 * @param date 日期
	 * @param sorft 分类(预装 ，预焊，总装)
	 * @param arbpl 生产线
	 * @param moveNum 移动的位数
	 */
	public int changeGroupPsbh(Long indexNo, String date, String sorft,String arbpl,int moveNum,String plant) {
		Map map=new HashMap();
		map.put("indexNo", indexNo);
		map.put("date", date);
		map.put("sorft", sorft);
		map.put("arbpl", arbpl);
		map.put("moveNum", moveNum);	
		map.put("plant", plant);
		
		if(moveNum>0){
			//查看是否有相同的排序号
//			List<ZgTorderPlanGroup> list=getSqlMapClientTemplate().queryForList("ZgTorderPlanGroupEx.findTheSamePsbh",map);
//			if(list.size()>0){
				return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.changeGroupPsbh",map);
//			}
		}else {
			return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.changeGroupPsbh",map);
		}
//		return 1;
	}


	public int updateGroupName(ZgTorderPlanGroup zgTorderPlanGroup) {
		return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.updateGroupName",zgTorderPlanGroup);

	}


	public List<ZgTorderPlanGroup> getPlanGroupList(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroupEx.getPlanGroupList",paramsMap);
	}
	
	public List<ZgTorderPlanGroup> getPlanGroupList1(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroupEx.getPlanGroupList1",paramsMap);
	}


	/**
	 * 根据装车计划表编号获取其涉及的领料计划组列表
	 * @param carPlanId
	 */
	public List<ZgTorderPlanGroup> getPlanGroupListByCarPlanId(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroupEx.getPlanGroupListByCarPlanId",paramsMap);
	}
	
	/**
	 * 根据领料计划表编号获取其涉及的领料计划组列表
	 * @param carPlanId
	 */
	public ZgTorderPlanGroup getPlanGroupListByOrderPlanId(String orderPlanId) {
		Map paramsMap=new HashMap();
		paramsMap.put("orderPlanId", orderPlanId);
		return (ZgTorderPlanGroup)getSqlMapClientTemplate().queryForObject("ZgTorderPlanGroupEx.getPlanGroupListByOrderPlanId",paramsMap);
	}


	/**
	 * 根据id改变订单分组的内部排序号
	 * @param ouPlanGroup
	 */
	public int changeIndexNoByGroupId(ZgTorderPlanGroup ouPlanGroup) {
		return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.changeIndexNoByGroupId", ouPlanGroup);
	}


	/**
	 * 更新某计划分组下面的领料计划状态
	 * @param groupId
	 * @param value
	 */
	public int updateZgtorderPlanStateByGroupId(String groupId, String state) {
		Map paramsMap=new HashMap();
		paramsMap.put("groupId", groupId);
		paramsMap.put("state", state);
		return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.updateZgtorderPlanStateByGroupId", paramsMap);
	}

	/**
	 * 根据订单编号查找其订单计划分组启示录
	 * @param orderId
	 */
	public List<ZgTorderPlanGroup> getPlanGroupListByOrderTaskId(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroupEx.getPlanGroupListByOrderTaskId", paramsMap);
	}


	/**
	 * @param planId
	 * @param state
	 */
	public int updateGroupStateByOrderPlanId(String planId, String state) {
		Map paramsMap=new HashMap();
		paramsMap.put("planId", planId);
		paramsMap.put("state", state);
		return getSqlMapClientTemplate().update("ZgTorderPlanGroupEx.updateGroupStateByOrderPlanId", paramsMap);
		
	}


	/**
	 * @param paramsMap
	 * @return
	 */
	public List<ZgTorderPlanGroup> getPlanGroupListByOrderId(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanGroupEx.getPlanGroupListByOrderId", paramsMap);

	}




}
