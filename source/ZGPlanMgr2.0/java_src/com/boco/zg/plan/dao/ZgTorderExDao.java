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
import com.boco.zg.plan.model.ZgTorderEx;
import com.boco.zg.util.Constants;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderExDao extends ZgTorderDao{
	public Class getEntityClass() {
		return ZgTorderEx.class;
	}
	

	public void saveOrUpdate(ZgTorderPlanGroup entity) {
	}


	public List<ZgTorder> getOrderInfoListByGroupId(String groupId) {
		Map paramsMap=new HashMap();
		paramsMap.put("groupId", groupId);
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getOrderInfoListByGroupId",paramsMap);
	}


	public List<ZgTorder> getOrderInfoListByorderPlanId(String orderPlanId) {
		Map paramsMap=new HashMap();
		paramsMap.put("orderPlanId", orderPlanId);
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getOrderInfoListByorderPlanId",paramsMap);
	}




	/**
	 * 根据源订单获取目标订单
	 * @param sourceOrderId
	 * @return
	 */
	public List<Map> listForSelectByOrderId(String sourceOrderId) {
		Map paramsMap=new HashMap();
		paramsMap.put("sourceOrderId", sourceOrderId);
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.listForSelectByOrderId",paramsMap);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {//operatorId
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorder.BM_CLASS_ID);
		return pageQuery("ZgTorderEx.pageSelect",pageRequest);
	}


	/**
	 * 根据订单编号更新其领料计划标记为手工完结单
	 * @param orderId
	 */
	public void updateOrderPlanToManul(String orderId) {
		Map paramsMap=new HashMap();
		paramsMap.put("orderId", orderId);
		getSqlMapClientTemplate().update("ZgTorderEx.updateOrderPlanToManul",paramsMap);
	}
	
	
	//////////////////订单成品下线确认3个约束条件//////////////////////
	/**
	 * 约束条件一：本次下线数量<=已排序数量-已下线数量
	 * @param cuid
	 * @return
	 */
	public Long getMaxFinishNumFromOrder(String cuid){
		return Long.parseLong(getSqlMapClientTemplate().queryForObject("ZgTorderEx.getMaxFinishNumFromOrder",cuid).toString());
	}
	
	/**约束条件二：初步判断订单BOM表和领料计划BOM中的BOM数量是不是一样
	 * 根据订单表id查询订单BOM表和领料计划BOM的记录数
	 * @param orderId
	 * @return 
	 */
	public List getFromOrderBom(String orderId){
		Map paramsMap =new HashMap();
		paramsMap.put("orderId", orderId);
		paramsMap.put("sortf", Constants.NEEDPLANSORTF);
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getFromOrderBom",orderId);
	}
	public List getFromOrderPlanBom(String orderId){
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getFromOrderPlanBom",orderId);
	}
	/**
	 * 约束条件三：再次判断所有bom够不够下线：单台用量*本次下线数量 <=已领取数量-移单数量-已退料数量-已出库数量
	 */
	public List<Map> getAllBomFromOrderBomAndOrderPlanBom(String orderId){
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getAllBomFromOrderBomAndOrderPlanBom",orderId);
	}
//////////////////订单成品下线更新下线数量和BOM出库数量//////////////////////
	/**
	 * 更新已下线数量
	 */
	public void updateOrderPublishNum(String orderId,Long finishNum){
		Map paramsMap=new HashMap();
		paramsMap.put("orderId", orderId);
		paramsMap.put("finishNum", finishNum);
		getSqlMapClientTemplate().update("ZgTorderEx.updateOrderPublishNum",paramsMap);
	}
	/**
	 * 更新领料计划中已领取的BOM数量
	 * @param planBomId   领料计划表ID
	 */
	public void updatePlanBomPublishNum(String planBomId,Long finishNum){
		Map paramsMap=new HashMap();
		paramsMap.put("planBomId", planBomId);
		paramsMap.put("finishNum", finishNum);
		getSqlMapClientTemplate().update("ZgTorderEx.updatePlanBomPublishNum",paramsMap);
	}


	/**
	 * 判断是否所有的bom都已经生成领料计划
	 * @param orderId
	 * @return
	 */
	public List isCountInOrderBomAndOrderPlanBomEqual(String orderId) {
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.isCountInOrderBomAndOrderPlanBomEqual",orderId);
	}
	
	/**
	 * 返回某订单所有的半成品
	 * @param orderId
	 * @return
	 */
	public List<Map> getAllProduct(String orderId){
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getAllProduct",orderId);
	}
	/**
	 * 成品下线，更新库存汇总表 已投入使用数量
	 * @param storageCuid  库存汇总表ID
	 * @param newUsedNum   更新后的已投入使用数量
	 */
	public void updateProductNum(String storageCuid, Long newUsedNum) {
		Map map=new HashMap();
		map.put("storageCuid", storageCuid);
		map.put("newUsedNum", newUsedNum);
		getSqlMapClientTemplate().update("ZgTorderEx.updateProductNum",map);
	}

	/**
	 * 统计某订单BOM中半成品的类别数
	 * @param orderId
	 * @return
	 */
	public List<Map> getProdCountFromOrderBom(String orderId) {
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getProdCountFromOrderBom",orderId);
	}


	/**
	 * 根据生产订单编号,登陆人编号获取其负责的该订单的生产厂
	 * @param orderId
	 * @param userId
	 */
	public List<Map> getPlantByOrderId(String orderId, String userId) {
		Map map=new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return getSqlMapClientTemplate().queryForList("ZgTorderEx.getPlantByOrderId",map);
		
	}




}
