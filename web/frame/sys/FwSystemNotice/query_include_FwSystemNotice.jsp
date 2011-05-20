<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_CONTENT" value="<%=map.get("ec_s_t0_CONTENT")==null?"":map.get("ec_s_t0_CONTENT")%>"/>
	<input type="hidden"  name="ec_a_CONTENT" value="content" />
	<input type="hidden"  name="s_content"  value="${pageRequest.filters.content}" />
	<input type="hidden"  name="ec_s_t0_CREATE_ID" value="<%=map.get("ec_s_t0_CREATE_ID")==null?"":map.get("ec_s_t0_CREATE_ID")%>"/>
	<input type="hidden"  name="ec_a_CREATE_ID" value="createId" />
	<input type="hidden"  name="s_createId"  value="${pageRequest.filters.createId}" />
	<input type="hidden"  name="ec_s_t0_CREATE_DATE" value="<%=map.get("ec_s_t0_CREATE_DATE")==null?"":map.get("ec_s_t0_CREATE_DATE")%>"/>
	<input type="hidden"  name="ec_a_CREATE_DATE" value="createDate" />
	<input type="hidden"  name="s_createDate_start"  value="${pageRequest.filters.createDate_start}" />
	<input type="hidden"  name="s_createDate_end"  value="${pageRequest.filters.createDate_end}" />
	<input type="hidden"  name="ec_s_t0_TITLE" value="<%=map.get("ec_s_t0_TITLE")==null?"":map.get("ec_s_t0_TITLE")%>"/>
	<input type="hidden"  name="ec_a_TITLE" value="title" />
	<input type="hidden"  name="s_title"  value="${pageRequest.filters.title}" />
	<input type="hidden"  name="ec_s_t0_STATE" value="<%=map.get("ec_s_t0_STATE")==null?"":map.get("ec_s_t0_STATE")%>"/>
	<input type="hidden"  name="ec_a_STATE" value="state" />
	<input type="hidden"  name="s_state"  value="${pageRequest.filters.state}" />
