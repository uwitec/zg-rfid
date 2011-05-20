<%@page import="com.boco.zg.storage.base.model.*" %>
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
	<input type="hidden"  name="ec_s_t0_DEPT_ID" value="<%=map.get("ec_s_t0_DEPT_ID")==null?"":map.get("ec_s_t0_DEPT_ID")%>"/>
	<input type="hidden"  name="ec_a_DEPT_ID" value="deptId" />
	<input type="hidden"  name="s_deptId"  value="${pageRequest.filters.deptId}" />
	<input type="hidden"  name="ec_s_t0_CREATOR_ID" value="<%=map.get("ec_s_t0_CREATOR_ID")==null?"":map.get("ec_s_t0_CREATOR_ID")%>"/>
	<input type="hidden"  name="ec_a_CREATOR_ID" value="creatorId" />
	<input type="hidden"  name="s_creatorId"  value="${pageRequest.filters.creatorId}" />
	<input type="hidden"  name="ec_s_t0_ZBZ" value="<%=map.get("ec_s_t0_ZBZ")==null?"":map.get("ec_s_t0_ZBZ")%>"/>
	<input type="hidden"  name="ec_a_ZBZ" value="zbz" />
	<input type="hidden"  name="s_zbz"  value="${pageRequest.filters.zbz}" />
	<input type="hidden"  name="ec_s_t0_STATE" value="<%=map.get("ec_s_t0_STATE")==null?"":map.get("ec_s_t0_STATE")%>"/>
	<input type="hidden"  name="ec_a_STATE" value="state" />
	<input type="hidden"  name="s_state"  value="${pageRequest.filters.state}" />
	<input type="hidden"  name="ec_s_t0_CREATE_DATE" value="<%=map.get("ec_s_t0_CREATE_DATE")==null?"":map.get("ec_s_t0_CREATE_DATE")%>"/>
	<input type="hidden"  name="ec_a_CREATE_DATE" value="createDate" />
	<input type="hidden"  name="s_createDate_start"  value="${pageRequest.filters.createDate_start}" />
	<input type="hidden"  name="s_createDate_end"  value="${pageRequest.filters.createDate_end}" />
