<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_zm_parent_id" value="<%=map.get("ec_s_zm_parent_id")==null?"":map.get("ec_s_zm_parent_id")%>"/>
	<input type="hidden"  name="ec_a_PARENT_ID" value="parentId" />
	<input type="hidden"  name="s_parentId"  value="${pageRequest.filters.parentId}" />
	<input type="hidden"  name="ec_s_zm_id" value="<%=map.get("ec_s_zm_id")==null?"":map.get("ec_s_zm_id")%>"/>
	<input type="hidden"  name="ec_s_zm_materiel_name" value="<%=map.get("ec_s_zm_materiel_name")==null?"":map.get("ec_s_zm_materiel_name")%>"/>
	<input type="hidden"  name="ec_a_MATERIEL_NAME" value="materielName" />
	<input type="hidden"  name="s_materielName"  value="${pageRequest.filters.materielName}" />
	<input type="hidden"  name="ec_s_zm_type" value="<%=map.get("ec_s_zm_type")==null?"":map.get("ec_s_zm_type")%>"/>
	<input type="hidden"  name="ec_a_TYPE" value="type" />
	<input type="hidden"  name="s_type"  value="${pageRequest.filters.type}" />
	<input type="hidden"  name="ec_s_zm_id" value="<%=map.get("ec_s_zm_id")==null?"":map.get("ec_s_zm_id")%>"/>
	<input type="hidden"  name="ec_a_ID" value="id" />
	<input type="hidden"  name="s_id"  value="${pageRequest.filters.id}" />
	<input type="hidden"  name="ec_s_virtualorgName" value="<%=map.get("ec_s_virtualorgName")==null?"":map.get("ec_s_virtualorgName")%>"/>
	<input type="hidden"  name="ec_s_organizationName" value="<%=map.get("ec_s_organizationName")==null?"":map.get("ec_s_organizationName")%>"/>
	<input type="hidden"  name="ec_s_virtualorgType" value="<%=map.get("ec_s_virtualorgType")==null?"":map.get("ec_s_virtualorgType")%>"/>
	<input type="hidden"  name="ec_s_virtualorgNote" value="<%=map.get("ec_s_virtualorgNote")==null?"":map.get("ec_s_virtualorgNote")%>"/>
