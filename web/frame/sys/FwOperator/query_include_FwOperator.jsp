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
	<input type="hidden"  name="ec_s_t0_USER_ID" value="<%=map.get("ec_s_t0_USER_ID")==null?"":map.get("ec_s_t0_USER_ID")%>"/>
	<input type="hidden"  name="ec_a_USER_ID" value="userId" />
	<input type="hidden"  name="s_userId"  value="${pageRequest.filters.userId}" />
	<input type="hidden"  name="ec_s_t0_PASSWORD" value="<%=map.get("ec_s_t0_PASSWORD")==null?"":map.get("ec_s_t0_PASSWORD")%>"/>
	<input type="hidden"  name="ec_a_PASSWORD" value="password" />
	<input type="hidden"  name="s_password"  value="${pageRequest.filters.password}" />
	<input type="hidden"  name="ec_s_t0_CREATE_TIME" value="<%=map.get("ec_s_t0_CREATE_TIME")==null?"":map.get("ec_s_t0_CREATE_TIME")%>"/>
	<input type="hidden"  name="ec_a_CREATE_TIME" value="createTime" />
	<input type="hidden"  name="s_createTime_start"  value="${pageRequest.filters.createTime_start}" />
	<input type="hidden"  name="s_createTime_end"  value="${pageRequest.filters.createTime_end}" />
	<input type="hidden"  name="ec_s_t0_LAST_MODIFY_TIME" value="<%=map.get("ec_s_t0_LAST_MODIFY_TIME")==null?"":map.get("ec_s_t0_LAST_MODIFY_TIME")%>"/>
	<input type="hidden"  name="ec_a_LAST_MODIFY_TIME" value="lastModifyTime" />
	<input type="hidden"  name="s_lastModifyTime_start"  value="${pageRequest.filters.lastModifyTime_start}" />
	<input type="hidden"  name="s_lastModifyTime_end"  value="${pageRequest.filters.lastModifyTime_end}" />
	<input type="hidden"  name="ec_s_t0_EFFECT_TIME" value="<%=map.get("ec_s_t0_EFFECT_TIME")==null?"":map.get("ec_s_t0_EFFECT_TIME")%>"/>
	<input type="hidden"  name="ec_a_EFFECT_TIME" value="effectTime" />
	<input type="hidden"  name="s_effectTime_start"  value="${pageRequest.filters.effectTime_start}" />
	<input type="hidden"  name="s_effectTime_end"  value="${pageRequest.filters.effectTime_end}" />
	<input type="hidden"  name="ec_s_t0_STATUS" value="<%=map.get("ec_s_t0_STATUS")==null?"":map.get("ec_s_t0_STATUS")%>"/>
	<input type="hidden"  name="ec_a_STATUS" value="status" />
	<input type="hidden"  name="s_status"  value="${pageRequest.filters.status}" />
	<input type="hidden"  name="ec_s_t0_RELATED_BM_CLASS_ID" value="<%=map.get("ec_s_t0_RELATED_BM_CLASS_ID")==null?"":map.get("ec_s_t0_RELATED_BM_CLASS_ID")%>"/>
	<input type="hidden"  name="ec_a_RELATED_BM_CLASS_ID" value="relatedBmClassId" />
	<input type="hidden"  name="s_relatedBmClassId"  value="${pageRequest.filters.relatedBmClassId}" />
