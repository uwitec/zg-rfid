/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.util.Constants;


@Component
public class ZgTorderPlanExDao extends BaseIbatisDao<ZgTorderPlanEx,java.lang.String>{
	
	public Class getEntityClass() {
		return ZgTorderPlanEx.class;
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanEx.pageSelect",pageRequest);
	}

	public void saveOrUpdate(ZgTorderPlanEx entity) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateOrderPlanState(String orderPlanId,String state) {
		ZgTorderPlan entity = new ZgTorderPlan();
		entity.setCuid(orderPlanId);
		entity.setState(state);
		this.getSqlMapClientTemplate().update("ZgTorderPlanEx.updateState",entity);
	}

	/**
	 * 获取批量领料计划
	 * @param pr
	 * @return
	 */
	public Page findBatchPlanByPageRequest(PageRequest<Map> pr) {
		((Map)pr.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanEx.pageSelectBatchPlan",pr);
	}

	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 * @return
	 */
	public boolean saveOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
//		return this.getSqlMapClientTemplate().insert("",zgTorderPlan)==1;
		return true;
	}

	public void updateOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
		this.getSqlMapClientTemplate().update("ZgTorderPlanEx.updateOrderPlanForBatch",zgTorderPlan);
	}

	/**
	 *  根据领料计划id删除其相应的装车计划
	 * @param orderPlanId
	 */
	public void deleteCarPlanByOrderPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanEx.delete_ZG_T_Carplan_By_OrderplanId",orderPlanId);
	}

	/**
	 * 根据领料计划id删除其相应的装车计划BOM 
	 * @param orderPlanId
	 */
	public void deleteCarBomByOrderPlanId(String orderPlanId) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanEx.delete_ZG_T_Carbom_By_OrderplanId",orderPlanId);
	}

	/**
	 * 设置计划的领料组，领料人
	 * 	   默认的为第一条计划的领料组和领料人
	 * @param orderPlanId
	 */
	@SuppressWarnings("unchecked")
	public void updateOrderDeptId(String orderPlanId) {
		StringBuffer sb=new StringBuffer();
		sb=sb.append(" select * from (select t.department_id, t.user_id ,t.plan_date,t.plan_start_time,t.plan_end_time ");
		sb.append("  from zg_t_order_planbom t, zg_t_orderbom ob ");
		sb.append("  where t.order_plan_id = '"+orderPlanId+"' ");
		sb.append("   and t.order_bom_id = ob.cuid           and t.department_id is not null ");
		sb.append("  order by idnrk)  where rownum = 1 ");
		List<Map> list=findDynQuery(sb.toString());
		if(null!=list&&list.size()>0){
			Map map=list.get(0);
			String deptId=(String) map.get("DEPARTMENT_ID");
			String userId=(String) map.get("USER_ID");
			Date planDate=(Date) map.get("PLAN_DATE");
			String planStartTime=(String) map.get("PLAN_START_TIME");
			String planEndTime=(String) map.get("PLAN_END_TIME");

			
			Map params=new HashMap<String, Object>();
//			params.put("", value)
			String updateStr="";
			if(null==planDate){
				updateStr=" update zg_t_order_plan t set t.departmenu_id='"+deptId+ "',t.user_id='"+userId+"' where t.cuid='"+orderPlanId+"'";
			}else {
				updateStr=" update zg_t_order_plan t set t.departmenu_id='"+deptId+ "',t.user_id='"+userId+"',t.plan_date=to_date('"+planDate+"','yyyy-mm-dd') where t.cuid='"+orderPlanId+"'";
			}
			findDynQuery(updateStr);
		}
		
	}

	/**
	 *   领料计划表中的bom按领料人，领料部门，仓库分组列表
	 * @param orderPlanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getDeptIdUserIdLgortListByOrderPlanId(String orderPlanId) {
		Map params=new HashMap();
		params.put("orderPlanId", orderPlanId);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.getDeptIdUserIdLgortListByOrderPlanId",params);
	}

	/**
	 * 根据领料计划单编号，仓库编号 ，领料部门编号，领料人编号生成查找领料计划bom插入到zg_t_carbom表中
	 * @param orderPlanId
	 * @param storageId
	 * @param deptId
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	public int generateCarBomByOrderPlanId(String carPlanId,String orderPlanId,String storageId, String deptId, String userId) {
		Map params=new HashMap();
		params.put("orderPlanId", orderPlanId);
		params.put("storageId", storageId);
		params.put("deptId", deptId);
		params.put("userId", userId);
		params.put("carPlanId", carPlanId);
		return getSqlMapClientTemplate().update("ZgTorderPlanEx.generateCarBomByOrderPlanId",params);
	}

	/**
	 * 根据领料计划单编号，仓库编号 ，领料部门编号，领料人编号生成查找领料计划bom,更改zg_t_order_planBom的计划领数量和状态（状态为已完成）
	 * @param orderPlanId
	 * @param storageId
	 * @param deptId
	 * @param userId
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public int updateZgtOrderPlanBomByOrderPlanId(String orderPlanId,
			String storageId, String deptId, String userId, String state) {
		Map params=new HashMap();
		params.put("orderPlanId", orderPlanId);
		params.put("storageId", storageId);
		params.put("deptId", deptId);
		params.put("userId", userId);
		params.put("state", state);
		return getSqlMapClientTemplate().update("ZgTorderPlanEx.updateZgtOrderPlanBomByOrderPlanId",params);

	}

	/**
	 * /更新orderbom状态
	 * @param orderPlanId
	 * @param state
	 */
	public int updateOrderPlanBomState(String orderPlanId, String state) {
		Map params=new HashMap();
		params.put("orderPlanId", orderPlanId);
		params.put("state", state);
		return getSqlMapClientTemplate().update("ZgTorderPlanEx.updateOrderPlanBomState",params);
	}

	/**
	 * 
	 */
	public String getSysCuid() {
		List<Map> list=findDynQuery("SELECT 'M'||to_char(SYSTIMESTAMP,'yyyymmddhhmissff') sysGuid FROM DUAL");
		Map map=list.get(0);
		return map.get("SYSGUID").toString();
	}

	/**
	 * 判断在该装车单的相应的仓库，领料组，领料人下面有没有待装车的bom组件
	 * @param orderPlanId
	 * @param storageId
	 * @param deptId
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasCarBomByorderPlanId(String orderPlanId, String storageId,
			String deptId, String userId) {
		Map params=new HashMap();
		params.put("orderPlanId", orderPlanId);
		params.put("storageId", storageId);
		params.put("deptId", deptId);
		params.put("userId", userId);
		List<Map> list=getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.getCarBomByOrderPlanId",params);
		return list.size()>0;
	}

	public List<ZgTorderPlanEx> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.pageSelect", parameterObject);
	}
	
	
	/**
	 * wjz,插入历史记录表
	 * 思路：把单ID orderPlanId 和 userId 插进去
	 *     这里插入后，是为了在审核的时候能够把 审核人 跟 审核单 关联上
	 * @param zgTorderPlan
	 */
	public void insertZgTorderPlanCommentOrderIdUserId(ZgTorderPlanComment zgTorderPlanComment){
		this.getSqlMapClientTemplate().insert("ZgTorderPlanComment.insert_ZgTorderPlanComment_orderId_userId", zgTorderPlanComment);
	}

	/**
	 * 更新领料计划bom表的计划数量
	 * @param orderPlanbomId
	 * @param carPlanNum
	 */
	public boolean updateOrderPlanBomState(String orderPlanbomId,long carPlanNum) {
		return false;
	}

	/**
	 * 获取当天以及之前未完成的 当天之后提前领料的订单总数
	 * @param type 总装、预装、预焊类型
	 * @return
	 */
	public int getCurDateTotalOrderCount(Map paramsMap) {
		return Integer.parseInt(getSqlMapClientTemplate().queryForObject("ZgTorderPlanEx.getCurDateTotalOrderCount",paramsMap).toString());
	}

	/**
	 * 获取当天完成的订单数量
	 * @param type
	 * @return
	 */
	public int getCurDateFinishedOrderCount(Map paramsMap) {
		return Integer.parseInt(getSqlMapClientTemplate().queryForObject("ZgTorderPlanEx.getCurDateFinishedOrderCount",paramsMap).toString());

	}

	/**
	 * 获取订单列表 当前排序日期的以及当排序日期后面的有特殊领料的订单
	 * @param planType
	 * @param pcDate
	 * @param operatorId
	 * @param arbpl
	 * @param plant
	 * @return
	 */
	public List<ZgTorderPlanEx> findZgTorderPlanList(String planType,String pcDate,String operatorId,String arbpl,String plant,String psbhChange,Map params) {
		params.put("planType", planType);
		params.put("pxDate", pcDate);
		params.put("operatorId", operatorId);
		params.put("arbpl", arbpl);
		params.put("plant", plant);
		params.put("psbhChange", psbhChange);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.pageSelect", params);
	}
	
	/**
	 * 获取订单列表 当前排序日期的订单
	 * @param planType
	 * @param pcDate
	 * @param operatorId
	 * @param arbpl
	 * @param plant
	 * @return
	 */
	public List<ZgTorderPlanEx> findZgTorderPlanList1(String planType,String pcDate,String operatorId,String arbpl,String plant,String psbhChange,Map params) {
		params.put("planType", planType);
		params.put("pxDate", pcDate);
		params.put("operatorId", operatorId);
		params.put("arbpl", arbpl);
		params.put("plant", plant);
		params.put("psbhChange", psbhChange);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.pageSelect1", params);
	}

	public List<ZgTorderPlan> getOrderPlanListByCarPlanId(String carPlanId) {
		Map params=new HashMap();
		params.put("carPlanId", carPlanId);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.getOrderPlanListByCarPlanId", params);
	}

	/**
	 * 获取当天的订单总数
	 * @param paramsMap
	 * @return
	 */
	public int getCurDateTotalOrderCount1(Map paramsMap) {
		return Integer.parseInt(getSqlMapClientTemplate().queryForObject("ZgTorderPlanEx.getCurDateTotalOrderCount1",paramsMap).toString());

	}

	/**
	 * 根据订单编号查找其领料计划
	 * @param orderId
	 * @return
	 */
	public List<ZgTorderPlan> getOrderPlanListByOrderTaskId(String orderTaskId) {
		Map params=new HashMap();
		params.put("orderTaskId", orderTaskId);
		params.put("sortf", Constants.NEEDPLANSORTF);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.getOrderPlanListByOrderTaskId", params);
	}

	/**
	 * @param orderId
	 * @return
	 */
	public List<ZgTorderPlan> getOrderPlanListByOrderId(String orderId) {
		Map params=new HashMap();
		params.put("orderId", orderId);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanEx.getOrderPlanListByOrderId", params);

	}

}
