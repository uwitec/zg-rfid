<%@page import="com.pz.modules.base.fwdep.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_SOURCE_PLANBOM_ID" value="<%=map.get("ec_s_t0_SOURCE_PLANBOM_ID")==null?"":map.get("ec_s_t0_SOURCE_PLANBOM_ID")%>"/>
	<input type="hidden"  name="ec_a_SOURCE_PLANBOM_ID" value="sourcePlanbomId" />
	<input type="hidden"  name="s_sourcePlanbomId"  value="${pageRequest.filters.sourcePlanbomId}" />
	<input type="hidden"  name="ec_s_t0_SOURCE_ORDER_TASK_ID" value="<%=map.get("ec_s_t0_SOURCE_ORDER_TASK_ID")==null?"":map.get("ec_s_t0_SOURCE_ORDER_TASK_ID")%>"/>
	<input type="hidden"  name="ec_a_SOURCE_ORDER_TASK_ID" value="sourceOrderTaskId" />
	<input type="hidden"  name="s_sourceOrderTaskId"  value="${pageRequest.filters.sourceOrderTaskId}" />
	<input type="hidden"  name="ec_s_t0_TARGET_ORDER_TASK_ID" value="<%=map.get("ec_s_t0_TARGET_ORDER_TASK_ID")==null?"":map.get("ec_s_t0_TARGET_ORDER_TASK_ID")%>"/>
	<input type="hidden"  name="ec_a_TARGET_ORDER_TASK_ID" value="targetOrderTaskId" />
	<input type="hidden"  name="s_targetOrderTaskId"  value="${pageRequest.filters.targetOrderTaskId}" />
	<input type="hidden"  name="ec_s_t0_TARGET_PLANBOM" value="<%=map.get("ec_s_t0_TARGET_PLANBOM")==null?"":map.get("ec_s_t0_TARGET_PLANBOM")%>"/>
	<input type="hidden"  name="ec_a_TARGET_PLANBOM" value="targetPlanbom" />
	<input type="hidden"  name="s_targetPlanbom"  value="${pageRequest.filters.targetPlanbom}" />
	<input type="hidden"  name="ec_s_t0_MOVE_NUM" value="<%=map.get("ec_s_t0_MOVE_NUM")==null?"":map.get("ec_s_t0_MOVE_NUM")%>"/>
	<input type="hidden"  name="ec_a_MOVE_NUM" value="moveNum" />
	<input type="hidden"  name="s_moveNum"  value="${pageRequest.filters.moveNum}" />
	<input type="hidden"  name="ec_s_t0_CREATE_ID" value="<%=map.get("ec_s_t0_CREATE_ID")==null?"":map.get("ec_s_t0_CREATE_ID")%>"/>
	<input type="hidden"  name="ec_a_CREATE_ID" value="createId" />
	<input type="hidden"  name="s_createId"  value="${pageRequest.filters.createId}" />
	<input type="hidden"  name="ec_s_t0_CREATE_DATE" value="<%=map.get("ec_s_t0_CREATE_DATE")==null?"":map.get("ec_s_t0_CREATE_DATE")%>"/>
	<input type="hidden"  name="ec_a_CREATE_DATE" value="createDate" />
	<input type="hidden"  name="s_createDate_start"  value="${pageRequest.filters.createDate_start}" />
	<input type="hidden"  name="s_createDate_end"  value="${pageRequest.filters.createDate_end}" />
	<input type="hidden"  name="ec_s_t0_CREATE_USERNAME" value="<%=map.get("ec_s_t0_CREATE_USERNAME")==null?"":map.get("ec_s_t0_CREATE_USERNAME")%>"/>
	<input type="hidden"  name="ec_a_CREATE_USERNAME" value="createUsername" />
	<input type="hidden"  name="s_createUsername"  value="${pageRequest.filters.createUsername}" />
