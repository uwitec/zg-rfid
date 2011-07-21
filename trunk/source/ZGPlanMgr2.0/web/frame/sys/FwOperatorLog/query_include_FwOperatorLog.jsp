<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_CREATE_ID" value="<%=map.get("ec_s_t0_CREATE_ID")==null?"":map.get("ec_s_t0_CREATE_ID")%>"/>
	<input type="hidden"  name="ec_a_CREATE_ID" value="createId" />
	<input type="hidden"  name="s_createId"  value="${pageRequest.filters.createId}" />
	<input type="hidden"  name="ec_s_t0_CREATE_DATE" value="<%=map.get("ec_s_t0_CREATE_DATE")==null?"":map.get("ec_s_t0_CREATE_DATE")%>"/>
	<input type="hidden"  name="ec_a_CREATE_DATE" value="createDate" />
	<input type="hidden"  name="s_createDate_start"  value="${pageRequest.filters.createDate_start}" />
	<input type="hidden"  name="s_createDate_end"  value="${pageRequest.filters.createDate_end}" />
	<input type="hidden"  name="ec_s_t0_TARGET_ID" value="<%=map.get("ec_s_t0_TARGET_ID")==null?"":map.get("ec_s_t0_TARGET_ID")%>"/>
	<input type="hidden"  name="ec_a_TARGET_ID" value="targetId" />
	<input type="hidden"  name="s_targetId"  value="${pageRequest.filters.targetId}" />
	<input type="hidden"  name="ec_s_t0_ACTION" value="<%=map.get("ec_s_t0_ACTION")==null?"":map.get("ec_s_t0_ACTION")%>"/>
	<input type="hidden"  name="ec_a_ACTION" value="action" />
	<input type="hidden"  name="s_action"  value="${pageRequest.filters.action}" />
	<input type="hidden"  name="ec_s_t0_OPERATOR_TYPE" value="<%=map.get("ec_s_t0_OPERATOR_TYPE")==null?"":map.get("ec_s_t0_OPERATOR_TYPE")%>"/>
	<input type="hidden"  name="ec_a_OPERATOR_TYPE" value="operatorType" />
	<input type="hidden"  name="s_operatorType"  value="${pageRequest.filters.operatorType}" />
