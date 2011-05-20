<%@page import="com.boco.zg.bom.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>

	<input type="hidden"  name="ec_s_CODE" value="<%=map.get("ec_s_CODE")==null?"":map.get("ec_s_CODE")%>"/>
	<input type="hidden"  name="ec_a_CODE" value="code" />
	<input type="hidden"  name="s_CODE"  value="${pageRequest.filters.code}" />
	<input type="hidden"  name="ec_s_LABEL_CN" value="<%=map.get("ec_s_LABEL_CN")==null?"":map.get("ec_s_LABEL_CN")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_LABEL_CN"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_s_DESCRIPTION" value="<%=map.get("ec_s_DESCRIPTION")==null?"":map.get("ec_s_DESCRIPTION")%>"/>
	<input type="hidden"  name="ec_a_DESCRIPTION" value="description" />
	<input type="hidden"  name="s_DESCRIPTION"  value="${pageRequest.filters.description}" />