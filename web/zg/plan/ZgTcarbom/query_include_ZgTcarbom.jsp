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
	<input type="hidden"  name="ec_s_t0_ORDER_PLAN_ID" value="<%=map.get("ec_s_t0_ORDER_PLAN_ID")==null?"":map.get("ec_s_t0_ORDER_PLAN_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_PLAN_ID" value="orderPlanId" />
	<input type="hidden"  name="s_orderPlanId"  value="${pageRequest.filters.orderPlanId}" />
	<input type="hidden"  name="ec_s_t0_ORDER_BOM_ID" value="<%=map.get("ec_s_t0_ORDER_BOM_ID")==null?"":map.get("ec_s_t0_ORDER_BOM_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_BOM_ID" value="orderBomId" />
	<input type="hidden"  name="s_orderBomId"  value="${pageRequest.filters.orderBomId}" />
	<input type="hidden"  name="ec_s_t0_ORDER_ID" value="<%=map.get("ec_s_t0_ORDER_ID")==null?"":map.get("ec_s_t0_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_ID" value="orderId" />
	<input type="hidden"  name="s_orderId"  value="${pageRequest.filters.orderId}" />
	<input type="hidden"  name="ec_s_t0_PLAN_NUM" value="<%=map.get("ec_s_t0_PLAN_NUM")==null?"":map.get("ec_s_t0_PLAN_NUM")%>"/>
	<input type="hidden"  name="ec_a_PLAN_NUM" value="planNum" />
	<input type="hidden"  name="s_planNum"  value="${pageRequest.filters.planNum}" />
	<input type="hidden"  name="ec_s_t0_REAL_NUM" value="<%=map.get("ec_s_t0_REAL_NUM")==null?"":map.get("ec_s_t0_REAL_NUM")%>"/>
	<input type="hidden"  name="ec_a_REAL_NUM" value="realNum" />
	<input type="hidden"  name="s_realNum"  value="${pageRequest.filters.realNum}" />
