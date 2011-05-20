/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;


@Component
public class ZgTorderPlanbomExDao extends BaseIbatisDao<ZgTorderPlanbomEx,java.lang.String>{
	
	public Class getEntityClass() {
		return ZgTorderPlanbomEx.class;
	}
	
	public void saveOrUpdate(ZgTorderPlanbomEx entity) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateOrderPlan(ZgTorderPlanbom entity) {
		this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.update", entity);
	}

	public List<ZgTorderPlanbomEx> findBatchByProperty(ZgTorderPlanbomEx entity) {
		Map parameterObject = new MapAndObject(new HashMap(),entity);
		List list = getSqlMapClientTemplate().queryForList(
				"ZgTorderPlanbomEx.findBatchByProperty", parameterObject);
		return list;
	}
	
	 public ZgTorderPlanbomEx getOrderPlanbomExById(String id) {
		    Map<String,Object> params=new HashMap<String, Object>();
		    params.put("cuid", id);
		    ZgTorderPlanbomEx object = (ZgTorderPlanbomEx)getSqlMapClientTemplate().queryForObject(getFindByPrimaryKeyQuery(), params);
	        return object;
	    }
	 

	public int updateOrderPlanforBatch(ZgTorderPlanbomEx zgTorderPlanbomEx) {
		return super.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updateZgTorderPlanbomForBatch",zgTorderPlanbomEx);
	}

	public void saveOrderPlanforBatch(ZgTorderPlanbomEx zgTorderPlanbomEx) {
		super.getSqlMapClientTemplate().insert("ZgTorderPlanbomEx.insertZGTorderPlanbomForBatch",zgTorderPlanbomEx);
	}

	/**
	 * 根据批量领料计划编号和bom组件编号删除批量领料计划中的bom组件
	 * @param cuid 批量领料计划编号
	 * @param bomId bom组件编号
	 */
	public void removeByIdAndBomId(String cuid) {
		ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
		zgTorderPlanbom.setCuid(cuid);
		super.getSqlMapClientTemplate().delete("ZgTorderPlanbomEx.deleteZG_T_ORDER_PLANBOM",zgTorderPlanbom);
	}
	
	
	/**
	 * 根据批量领料计划单编号查找bom组件列表 按仓库排序返回
	 * @param orderPlanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ZgTorderPlanbomEx> findBomListByPlanId(ZgTorderPlanbomEx entity) {
		Map parameterObject = new MapAndObject(new HashMap(),entity);
		List list = getSqlMapClientTemplate().queryForList(
				"ZgTorderPlanbomEx.findBomListByPlanId", parameterObject);
		return list;
	}

	public String getCUID() {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("ZgTorderPlanbomEx.getCUID");
	}

	public List findAllLgortByOrderPlanId(String orderPlanId) {
		Map parameterObject = new HashMap();
		parameterObject.put("orderPlanId", orderPlanId);
		List list = getSqlMapClientTemplate().queryForList(
				"ZgTorderPlanbomEx.findAllLgortByOrderPlanId", parameterObject);
		return list;
	}

	public void updateZgTorderPlanbomCarNum(ZgTorderPlanbom entity) {
		this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updateCarNum", entity);
	}

	public void updateOrderPlan1(ZgTorderPlanbom entity) {
		this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.update1", entity);
	}

	/**
	 * 更新领料计划bom表的计划数量
	 * @param orderPlanbomId
	 * @param carPlanNum
	 */
	public boolean updatePlanNum(String orderPlanbomId, long carPlanNum) {
		ZgTorderPlanbom entity=new ZgTorderPlanbom();
		entity.setCuid(orderPlanbomId);
		entity.setPlanNum(carPlanNum);
		return this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updatePlanNum", entity)==1;
	}

	public boolean updateAuditNum(ZgTorderPlanbomEx zgTorderPlanbomEx) {
		return this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updateAuditNum", zgTorderPlanbomEx)==1;
	}

	public List<ZgTorderPlanbomEx> findBomListByGroupId(String groupId) {
		Map paramsBom=new HashMap();
		paramsBom.put("groupId", groupId);
		return this.getSqlMapClientTemplate().queryForList("ZgTorderPlanbomEx.findBomListByGroupId", paramsBom);

	}

	/**
	 * 获取领料计划下的详细bom
	 * @param orderPlanId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByOrderPlanId(String orderPlanId) {
		Map paramsBom=new HashMap();
		paramsBom.put("orderPlanId", orderPlanId);
		return this.getSqlMapClientTemplate().queryForList("ZgTorderPlanbomEx.findBomListByOrderPlanId", paramsBom);
	}

	/**
	 * 获取领料计划下的详细bom(提前领料的bom)
	 * @param orderPlanId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByOrderPlanIdAdvance(
			String orderPlanId) {
		Map paramsBom=new HashMap();
		paramsBom.put("orderPlanId", orderPlanId);
		return this.getSqlMapClientTemplate().queryForList("ZgTorderPlanbomEx.findBomListByOrderPlanIdAdvance", paramsBom);
	}

	/**
	 * 查找订单分组下面的详细bom(提前领料的分组bom)
	 * @param groupId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByGroupIdAdvance(String groupId) {
		Map paramsBom=new HashMap();
		paramsBom.put("groupId", groupId);
		return this.getSqlMapClientTemplate().queryForList("ZgTorderPlanbomEx.findBomListByGroupIdAdvance", paramsBom);
	}

	
	public ZgTorderPlanbom getByOrderBomId(String orderBomId) {
		Map paramsBom=new HashMap();
		paramsBom.put("orderBomId", orderBomId);
		return (ZgTorderPlanbom) this.getSqlMapClientTemplate().queryForObject("ZgTorderPlanbomEx.getByOrderBomId", paramsBom);
	}

	/**
	 * 
	 * @param zgTorderbom
	 * @param targetOrderId
	 * @return
	 */
	public ZgTorderPlanbom getPlanbomBySourceOrderbom(ZgTorderbom zgTorderbom,
			String targetOrderId) {
		Map paraMap=new HashMap();
		paraMap.put("targerOrderId", targetOrderId);
		paraMap.put("idnrk", zgTorderbom.getIdnrk());
		paraMap.put("matkl", zgTorderbom.getMatkl());
		paraMap.put("posnr", zgTorderbom.getPosnr());
		paraMap.put("moveNum", zgTorderbom.getMoveNum());
		paraMap.put("lgort", zgTorderbom.getLgort());
		return (ZgTorderPlanbom) this.getSqlMapClientTemplate().queryForObject("ZgTorderPlanbomEx.getPlanbomBySourceOrderbom", paraMap);
	}

	/**
	 * @param cuid
	 */
	public boolean deleteByOrderBomId(String orderBomId) {
		Map paraMap=new HashMap();
		paraMap.put("orderBomId", orderBomId);
		return  this.getSqlMapClientTemplate().delete("ZgTorderPlanbomEx.deleteByOrderBomId", paraMap)>0;

	}

	/**
	 * 设置物料为领料完成
	 * @param planBomId
	 */
	public boolean finishBom(String planBomId) {
		Map paraMap=new HashMap();
		paraMap.put("planBomId", planBomId);
		return  this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updateBomState", paraMap)>0;
	}

	/**
	 * @param orderId
	 * @return
	 */
	public List<ZgTorderPlanbomEx> findBomListByOrderId(String orderId) {
		Map paramsBom=new HashMap();
		paramsBom.put("orderId", orderId);
		return this.getSqlMapClientTemplate().queryForList("ZgTorderPlanbomEx.findBomListByOrderId", paramsBom);
	}

	/**
	 * 源bom 移单更新数据,move为移单数量 
	 * @param cuid planbom主键编号
	 * @param moveNum 移单数量
	 */
	public void updateSourceBomNum(String cuid, Long moveNum) {
		Map paramsBom=new HashMap();
		paramsBom.put("cuid", cuid);
		paramsBom.put("moveNum", moveNum);
		this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updateSourceBomNum", paramsBom);

	}

	/**
	 * 目标BOM 移单更新数据
	 * @param cuid
	 * @param moveNum
	 */
	public void updateTargetBomNum(String cuid, Long moveNum) {
		Map paramsBom=new HashMap();
		paramsBom.put("cuid", cuid);
		paramsBom.put("moveNum", moveNum);
		this.getSqlMapClientTemplate().update("ZgTorderPlanbomEx.updateTargetBomNum", paramsBom);
	}

}
