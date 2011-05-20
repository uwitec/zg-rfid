<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_SOURCE_ORDER_ID" value="<%=map.get("ec_s_t0_SOURCE_ORDER_ID")==null?"":map.get("ec_s_t0_SOURCE_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_SOURCE_ORDER_ID" value="sourceOrderId" />
	<input type="hidden"  name="s_sourceOrderId"  value="${pageRequest.filters.sourceOrderId}" />
	<input type="hidden"  name="ec_s_t0_SOURCE_IDNRK" value="<%=map.get("ec_s_t0_SOURCE_IDNRK")==null?"":map.get("ec_s_t0_SOURCE_IDNRK")%>"/>
	<input type="hidden"  name="ec_a_SOURCE_IDNRK" value="sourceIdnrk" />
	<input type="hidden"  name="s_sourceIdnrk"  value="${pageRequest.filters.sourceIdnrk}" />
	<input type="hidden"  name="ec_s_t0_TARGET_ORDER_ID" value="<%=map.get("ec_s_t0_TARGET_ORDER_ID")==null?"":map.get("ec_s_t0_TARGET_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_TARGET_ORDER_ID" value="targetOrderId" />
	<input type="hidden"  name="s_targetOrderId"  value="${pageRequest.filters.targetOrderId}" />
	<input type="hidden"  name="ec_s_t0_MOVE_NUM" value="<%=map.get("ec_s_t0_MOVE_NUM")==null?"":map.get("ec_s_t0_MOVE_NUM")%>"/>
	<input type="hidden"  name="ec_a_MOVE_NUM" value="moveNum" />
	<input type="hidden"  name="s_moveNum"  value="${pageRequest.filters.moveNum}" />
	<input type="hidden"  name="ec_s_t0_TARGET_IDNRK" value="<%=map.get("ec_s_t0_TARGET_IDNRK")==null?"":map.get("ec_s_t0_TARGET_IDNRK")%>"/>
	<input type="hidden"  name="ec_a_TARGET_IDNRK" value="targetIdnrk" />
	<input type="hidden"  name="s_targetIdnrk"  value="${pageRequest.filters.targetIdnrk}" />
