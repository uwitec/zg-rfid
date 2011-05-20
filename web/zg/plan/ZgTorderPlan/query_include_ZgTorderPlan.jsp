<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_a_ORDER_ID" value="orderId" />
	<input type="hidden"  name="s_orderId"  value="${pageRequest.filters.orderId}" />
	<input type="hidden"  name="ec_a_DEPARTMENU_ID" value="departmenuId" />
	<input type="hidden"  name="s_departmenuId"  value="${pageRequest.filters.departmenuId}" />
	<input type="hidden"  name="ec_a_USER_ID" value="userId" />
	<input type="hidden"  name="s_userId"  value="${pageRequest.filters.userId}" />
	<input type="hidden"  name="ec_a_PLAN_DATE" value="planDate" />
	<input type="hidden"  name="s_planDate_start"  value="${pageRequest.filters.planDate_start}" />
	<input type="hidden"  name="s_planDate_end"  value="${pageRequest.filters.planDate_end}" />
	<input type="hidden"  name="ec_a_PLAN_START_TIME" value="planStartTime" />
	<input type="hidden"  name="s_planStartTime_start"  value="${pageRequest.filters.planStartTime_start}" />
	<input type="hidden"  name="s_planStartTime_end"  value="${pageRequest.filters.planStartTime_end}" />
	<input type="hidden"  name="ec_a_PLAN_END_TIME" value="planEndTime" />
	<input type="hidden"  name="s_planEndTime_start"  value="${pageRequest.filters.planEndTime_start}" />
	<input type="hidden"  name="s_planEndTime_end"  value="${pageRequest.filters.planEndTime_end}" />
	<input type="hidden"  name="ec_a_STATE" value="state" />
	<input type="hidden"  name="s_state"  value="${pageRequest.filters.state}" />
	<input type="hidden"  name="ec_a_PLAN_TYPE" value="planType" />
	<input type="hidden"  name="s_planType"  value="${pageRequest.filters.planType}" />
	
	<input type="hidden"  name="ec_s_PXDAT" value="<%=map.get("ec_s_PXDAT")==null?"":map.get("ec_s_PXDAT")%>"/>
	<input type="hidden"  name="ec_s_AUFNR" value="<%=map.get("ec_s_AUFNR")==null?"":map.get("ec_s_AUFNR")%>"/>
	<input type="hidden"  name="ec_s_PCDAT" value="<%=map.get("ec_s_PCDAT")==null?"":map.get("ec_s_PCDAT")%>"/>
	<input type="hidden"  name="ec_s_KDAUF" value="<%=map.get("ec_s_KDAUF")==null?"":map.get("ec_s_KDAUF")%>"/>
	<input type="hidden"  name="ec_s_PLAN_TYPE" value="<%=map.get("ec_s_PLAN_TYPE")==null?"":map.get("ec_s_PLAN_TYPE")%>"/>
	<input type="hidden"  name="ec_s_MATNR" value="<%=map.get("ec_s_MATNR")==null?"":map.get("ec_s_MATNR")%>"/>
	<input type="hidden"  name="ec_s_MAKTX1" value="<%=map.get("ec_s_MAKTX1")==null?"":map.get("ec_s_MAKTX1")%>"/>
	<input type="hidden"  name="ec_s_PSMNG" value="<%=map.get("ec_s_PSMNG")==null?"":map.get("ec_s_PSMNG")%>"/>
	<input type="hidden"  name="ec_s_PLANT" value="<%=map.get("ec_s_PLANT")==null?"":map.get("ec_s_PLANT")%>"/>
	<input type="hidden"  name="ec_s_ARBPL" value="<%=map.get("ec_s_ARBPL")==null?"":map.get("ec_s_ARBPL")%>"/>
	<input type="hidden"  name="ec_s_DEPARTMENT_NAME" value="<%=map.get("ec_s_DEPARTMENT_NAME")==null?"":map.get("ec_s_DEPARTMENT_NAME")%>"/>
	<input type="hidden"  name="ec_s_USER_NAME" value="<%=map.get("ec_s_USER_NAME")==null?"":map.get("ec_s_USER_NAME")%>"/>
	<input type="hidden"  name="ec_s_PLAN_DATE" value="<%=map.get("ec_s_PLAN_DATE")==null?"":map.get("ec_s_PLAN_DATE")%>"/>
	<input type="hidden"  name="ec_s_PLAN_START_TIME" value="<%=map.get("ec_s_PLAN_START_TIME")==null?"":map.get("ec_s_PLAN_START_TIME")%>"/>
	<input type="hidden"  name="ec_s_PLAN_END_TIME" value="<%=map.get("ec_s_PLAN_END_TIME")==null?"":map.get("ec_s_PLAN_END_TIME")%>"/>
	<input type="hidden"  name="ec_s_STATE" value="<%=map.get("ec_s_STATE")==null?"":map.get("ec_s_STATE")%>"/>
	<input type="hidden"  name="ec_s_CUID" value="<%=map.get("ec_s_CUID")==null?"":map.get("ec_s_CUID")%>"/>
	
	<input type="hidden"  name="s_pxdat_start"  value="${pageRequest.filters.pxdat_start}" />
	<input type="hidden"  name="s_pxdat_end"  value="${pageRequest.filters.pxdat_end}" />
	<input type="hidden"  name="s_aufnr"  value="${pageRequest.filters.aufnr}" />
	<input type="hidden"  name="s_kdauf"  value="${pageRequest.filters.kdauf}" />
	<input type="hidden"  name="s_matnr"  value="${pageRequest.filters.matnr}" />
	
	<input type="hidden"  name="s_maktx1"  value="${pageRequest.filters.maktx1}" />
	<input type="hidden"  name="s_plant"  value="${pageRequest.filters.splant}" />
	<input type="hidden"  name="s_arbpl"  value="${pageRequest.filters.arbpl}" />
	<input type="hidden"  name="s_departmentId"  value="${pageRequest.filters.departmentId}" />
	
	<input type="hidden"  name="s_cuid"  value="${pageRequest.filters.cuid}" />
	
		
	
	
		
		
	
	
	
