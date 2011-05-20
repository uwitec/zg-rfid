<%@page import="com.boco.zg.virtualorg.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_ztv_label_cn" value="<%=map.get("ec_s_ztv_label_cn")==null?"":map.get("ec_s_ztv_label_cn")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	
	<input type="hidden"  name="ec_s_ztv_org_id" value="<%=map.get("ec_s_ztv_org_id")==null?"":map.get("ec_s_ztv_org_id")%>"/>
	<input type="hidden"  name="ec_a_ORG_ID" value="orgId" />
	
	<input type="hidden"  name="ec_s_ztv_note" value="<%=map.get("ec_s_ztv_note")==null?"":map.get("ec_s_ztv_note")%>"/>
	<input type="hidden"  name="ec_a_NOTE" value="note" />
	<input type="hidden"  name="s_note"  value="${pageRequest.filters.note}" />
	<input type="hidden"  name="ec_s_ztv_type" value="<%=map.get("ec_s_ztv_type")==null?"":map.get("ec_s_ztv_type")%>"/>
	<input type="hidden"  name="ec_a_TYPE" value="type" />
	<input type="hidden"  name="s_type"  value="${pageRequest.filters.type}" />
	<input type="hidden"  name="ec_s_fo_userId" value="<%=map.get("ec_s_fo_userId")==null?"":map.get("ec_s_fo_userId")%>"/>
	<input type="hidden"  name="ec_s_fo_userName" value="<%=map.get("ec_s_fo_userName")==null?"":map.get("ec_s_fo_userName")%>"/>
	<input type="hidden"  name="ec_s_fe_sex" value="<%=map.get("ec_s_fe_sex")==null?"":map.get("ec_s_fe_sex")%>"/>
	<input type="hidden"  name="ec_s_fe_mobile" value="<%=map.get("ec_s_fe_mobile")==null?"":map.get("ec_s_fe_mobile")%>"/>
	<input type="hidden"  name="ec_s_fe_email" value="<%=map.get("ec_s_fe_email")==null?"":map.get("ec_s_fe_email")%>"/>