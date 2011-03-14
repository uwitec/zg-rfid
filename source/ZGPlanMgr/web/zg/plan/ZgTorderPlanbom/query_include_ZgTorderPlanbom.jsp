<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_LABEL_CN" value="<%=map.get("ec_s_t0_LABEL_CN")==null?"":map.get("ec_s_t0_LABEL_CN")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_s_t0_ORDER_BOM_ID" value="<%=map.get("ec_s_t0_ORDER_BOM_ID")==null?"":map.get("ec_s_t0_ORDER_BOM_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_BOM_ID" value="orderBomId" />
	<input type="hidden"  name="s_orderBomId"  value="${pageRequest.filters.orderBomId}" />
	<input type="hidden"  name="ec_s_t0_ORDER_ID" value="<%=map.get("ec_s_t0_ORDER_ID")==null?"":map.get("ec_s_t0_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_ID" value="orderId" />
	<input type="hidden"  name="s_orderId"  value="${pageRequest.filters.orderId}" />
	<input type="hidden"  name="ec_s_t0_DEPARTMENT_ID" value="<%=map.get("ec_s_t0_DEPARTMENT_ID")==null?"":map.get("ec_s_t0_DEPARTMENT_ID")%>"/>
	<input type="hidden"  name="ec_a_DEPARTMENT_ID" value="departmentId" />
	<input type="hidden"  name="s_departmentId"  value="${pageRequest.filters.departmentId}" />
	<input type="hidden"  name="ec_s_t0_USER_ID" value="<%=map.get("ec_s_t0_USER_ID")==null?"":map.get("ec_s_t0_USER_ID")%>"/>
	<input type="hidden"  name="ec_a_USER_ID" value="userId" />
	<input type="hidden"  name="s_userId"  value="${pageRequest.filters.userId}" />
	<input type="hidden"  name="ec_s_t0_PLAN_DATE" value="<%=map.get("ec_s_t0_PLAN_DATE")==null?"":map.get("ec_s_t0_PLAN_DATE")%>"/>
	<input type="hidden"  name="ec_a_PLAN_DATE" value="planDate" />
	<input type="hidden"  name="s_planDate_start"  value="${pageRequest.filters.planDate_start}" />
	<input type="hidden"  name="s_planDate_end"  value="${pageRequest.filters.planDate_end}" />
	<input type="hidden"  name="ec_s_t0_PLAN_START_TIME" value="<%=map.get("ec_s_t0_PLAN_START_TIME")==null?"":map.get("ec_s_t0_PLAN_START_TIME")%>"/>
	<input type="hidden"  name="ec_a_PLAN_START_TIME" value="planStartTime" />
	<input type="hidden"  name="s_planStartTime_start"  value="${pageRequest.filters.planStartTime_start}" />
	<input type="hidden"  name="s_planStartTime_end"  value="${pageRequest.filters.planStartTime_end}" />
	<input type="hidden"  name="ec_s_t0_PLAN_END_TIME" value="<%=map.get("ec_s_t0_PLAN_END_TIME")==null?"":map.get("ec_s_t0_PLAN_END_TIME")%>"/>
	<input type="hidden"  name="ec_a_PLAN_END_TIME" value="planEndTime" />
	<input type="hidden"  name="s_planEndTime_start"  value="${pageRequest.filters.planEndTime_start}" />
	<input type="hidden"  name="s_planEndTime_end"  value="${pageRequest.filters.planEndTime_end}" />
	<input type="hidden"  name="ec_s_t0_STATE" value="<%=map.get("ec_s_t0_STATE")==null?"":map.get("ec_s_t0_STATE")%>"/>
	<input type="hidden"  name="ec_a_STATE" value="state" />
	<input type="hidden"  name="s_state"  value="${pageRequest.filters.state}" />
	<input type="hidden"  name="ec_s_t0_COMPLETE_NUM" value="<%=map.get("ec_s_t0_COMPLETE_NUM")==null?"":map.get("ec_s_t0_COMPLETE_NUM")%>"/>
	<input type="hidden"  name="ec_a_COMPLETE_NUM" value="completeNum" />
	<input type="hidden"  name="s_completeNum"  value="${pageRequest.filters.completeNum}" />
	<input type="hidden"  name="ec_s_t0_CAR_NUM" value="<%=map.get("ec_s_t0_CAR_NUM")==null?"":map.get("ec_s_t0_CAR_NUM")%>"/>
	<input type="hidden"  name="ec_a_CAR_NUM" value="carNum" />
	<input type="hidden"  name="s_carNum"  value="${pageRequest.filters.carNum}" />
