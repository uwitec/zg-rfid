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
	<input type="hidden"  name="ec_s_t0_PARENT_ORG_ID" value="<%=map.get("ec_s_t0_PARENT_ORG_ID")==null?"":map.get("ec_s_t0_PARENT_ORG_ID")%>"/>
	<input type="hidden"  name="ec_a_PARENT_ORG_ID" value="parentOrgId" />
	<input type="hidden"  name="s_parentOrgId"  value="${pageRequest.filters.parentOrgId}" />
	<input type="hidden"  name="ec_s_t0_LEVEL_NUM" value="<%=map.get("ec_s_t0_LEVEL_NUM")==null?"":map.get("ec_s_t0_LEVEL_NUM")%>"/>
	<input type="hidden"  name="ec_a_LEVEL_NUM" value="levelNum" />
	<input type="hidden"  name="s_levelNum"  value="${pageRequest.filters.levelNum}" />
	<input type="hidden"  name="ec_s_t0_SEQ" value="<%=map.get("ec_s_t0_SEQ")==null?"":map.get("ec_s_t0_SEQ")%>"/>
	<input type="hidden"  name="ec_a_SEQ" value="seq" />
	<input type="hidden"  name="s_seq"  value="${pageRequest.filters.seq}" />
	<input type="hidden"  name="ec_s_t0_NOTE" value="<%=map.get("ec_s_t0_NOTE")==null?"":map.get("ec_s_t0_NOTE")%>"/>
	<input type="hidden"  name="ec_a_NOTE" value="note" />
	<input type="hidden"  name="s_note"  value="${pageRequest.filters.note}" />
	<input type="hidden"  name="ec_s_t0_RELATED_BM_CLASS_ID" value="<%=map.get("ec_s_t0_RELATED_BM_CLASS_ID")==null?"":map.get("ec_s_t0_RELATED_BM_CLASS_ID")%>"/>
	<input type="hidden"  name="ec_a_RELATED_BM_CLASS_ID" value="relatedBmClassId" />
	<input type="hidden"  name="s_relatedBmClassId"  value="${pageRequest.filters.relatedBmClassId}" />
	<input type="hidden"  name="ec_s_t0_TYPE" value="<%=map.get("ec_s_t0_TYPE")==null?"":map.get("ec_s_t0_TYPE")%>"/>
