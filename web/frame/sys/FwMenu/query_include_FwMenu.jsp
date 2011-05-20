<%@page import="com.boco.frame.sys.base.model.*" %>
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
	<input type="hidden"  name="ec_s_t0_PARENT_MENU_ID" value="<%=map.get("ec_s_t0_PARENT_MENU_ID")==null?"":map.get("ec_s_t0_PARENT_MENU_ID")%>"/>
	<input type="hidden"  name="ec_a_PARENT_MENU_ID" value="parentMenuId" />
	<input type="hidden"  name="s_parentMenuId"  value="${pageRequest.filters.parentMenuId}" />
	<input type="hidden"  name="ec_s_t0_URL" value="<%=map.get("ec_s_t0_URL")==null?"":map.get("ec_s_t0_URL")%>"/>
	<input type="hidden"  name="ec_a_URL" value="url" />
	<input type="hidden"  name="s_url"  value="${pageRequest.filters.url}" />
	<input type="hidden"  name="ec_s_t0_DISPLAY_ORDER" value="<%=map.get("ec_s_t0_DISPLAY_ORDER")==null?"":map.get("ec_s_t0_DISPLAY_ORDER")%>"/>
	<input type="hidden"  name="ec_a_DISPLAY_ORDER" value="displayOrder" />
	<input type="hidden"  name="s_displayOrder"  value="${pageRequest.filters.displayOrder}" />
	<input type="hidden"  name="ec_s_t0_SEQ" value="<%=map.get("ec_s_t0_SEQ")==null?"":map.get("ec_s_t0_SEQ")%>"/>
	<input type="hidden"  name="ec_a_SEQ" value="seq" />
	<input type="hidden"  name="s_seq"  value="${pageRequest.filters.seq}" />
	<input type="hidden"  name="ec_s_t0_LEVEL_NUM" value="<%=map.get("ec_s_t0_LEVEL_NUM")==null?"":map.get("ec_s_t0_LEVEL_NUM")%>"/>
	<input type="hidden"  name="ec_a_LEVEL_NUM" value="levelNum" />
	<input type="hidden"  name="s_levelNum"  value="${pageRequest.filters.levelNum}" />
	<input type="hidden"  name="ec_s_t0_TYPE" value="<%=map.get("ec_s_t0_TYPE")==null?"":map.get("ec_s_t0_TYPE")%>"/>
	<input type="hidden"  name="ec_a_TYPE" value="type" />
	<input type="hidden"  name="s_type"  value="${pageRequest.filters.type}" />
