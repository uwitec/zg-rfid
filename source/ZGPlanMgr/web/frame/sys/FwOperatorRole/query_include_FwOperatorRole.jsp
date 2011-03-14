<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_OPERATOR_ID" value="<%=map.get("ec_s_t0_OPERATOR_ID")==null?"":map.get("ec_s_t0_OPERATOR_ID")%>"/>
	<input type="hidden"  name="ec_a_OPERATOR_ID" value="operatorId" />
	<input type="hidden"  name="s_operatorId"  value="${pageRequest.filters.operatorId}" />
	<input type="hidden"  name="ec_s_t0_ROLE_ID" value="<%=map.get("ec_s_t0_ROLE_ID")==null?"":map.get("ec_s_t0_ROLE_ID")%>"/>
	<input type="hidden"  name="ec_a_ROLE_ID" value="roleId" />
	<input type="hidden"  name="s_roleId"  value="${pageRequest.filters.roleId}" />
