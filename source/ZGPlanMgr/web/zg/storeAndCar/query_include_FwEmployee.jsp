<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_idnrk" value="<%=map.get("ec_s_idnrk")==null?"":map.get("ec_s_idnrk")%>"/>
	<input type="hidden"  name="ec_s_msehl" value="<%=map.get("ec_s_msehl")==null?"":map.get("ec_s_msehl")%>"/>
	<input type="hidden"  name="ec_s_materiel_name" value="<%=map.get("ec_s_materiel_name")==null?"":map.get("ec_s_materiel_name")%>"/>
	<input type="hidden"  name="ec_s_maktx" value="<%=map.get("ec_s_maktx")==null?"":map.get("ec_s_maktx")%>"/>
	<input type="hidden"  name="ec_s_code" value="<%=map.get("ec_s_code")==null?"":map.get("ec_s_code")%>"/>
	<input type="hidden"  name="ec_s_car_label_cn" value="<%=map.get("ec_s_car_label_cn")==null?"":map.get("ec_s_car_label_cn")%>"/>
	<input type="hidden"  name="ec_s_carnum" value="<%=map.get("ec_s_carnum")==null?"":map.get("ec_s_carnum")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_a_USER_ID" value="userId" />
	<input type="hidden"  name="s_userId"  value="${pageRequest.filters.userId}" />
	<input type="hidden"  name="ec_a_PASSWORD" value="password" />
	<input type="hidden"  name="ec_a_CREATE_TIME" value="createTime" />
	<input type="hidden"  name="s_createTime_start"  value="${pageRequest.filters.createTime_start}" />
	<input type="hidden"  name="s_createTime_end"  value="${pageRequest.filters.createTime_end}" />
	<input type="hidden"  name="ec_a_LAST_MODIFY_TIME" value="lastModifyTime" />
	<input type="hidden"  name="s_lastModifyTime_start"  value="${pageRequest.filters.lastModifyTime_start}" />
	<input type="hidden"  name="s_lastModifyTime_end"  value="${pageRequest.filters.lastModifyTime_end}" />
	<input type="hidden"  name="ec_a_EFFECT_TIME" value="effectTime" />
	<input type="hidden"  name="s_effectTime_start"  value="${pageRequest.filters.effectTime_start}" />
	<input type="hidden"  name="s_effectTime_end"  value="${pageRequest.filters.effectTime_end}" />
	<input type="hidden"  name="ec_a_STATUS" value="status" />
	<input type="hidden"  name="s_status"  value="${pageRequest.filters.status}" />
	<input type="hidden"  name="ec_s_t1_RELATED_BM_CLASS_ID" value="<%=map.get("ec_s_t1_RELATED_BM_CLASS_ID")==null?"":map.get("ec_s_t1_RELATED_BM_CLASS_ID")%>"/>
	<input type="hidden"  name="ec_a_RELATED_BM_CLASS_ID" value="relatedBmClassId" />
	<input type="hidden"  name="s_relatedBmClassId"  value="${pageRequest.filters.relatedBmClassId}" />
	<input type="hidden"  name="ec_s_t0_ORG_ID" value="<%=map.get("ec_s_t0_ORG_ID")==null?"":map.get("ec_s_t0_ORG_ID")%>"/>
	<input type="hidden"  name="ec_a_ORG_ID" value="orgId" />
	<input type="hidden"  name="ec_s_t0_SEX" value="<%=map.get("ec_s_t0_SEX")==null?"":map.get("ec_s_t0_SEX")%>"/>
	<input type="hidden"  name="ec_a_SEX" value="sex" />
	<input type="hidden"  name="s_sex"  value="${pageRequest.filters.sex}" />
	<input type="hidden"  name="ec_s_t0_MOBILE" value="<%=map.get("ec_s_t0_MOBILE")==null?"":map.get("ec_s_t0_MOBILE")%>"/>
	<input type="hidden"  name="ec_a_MOBILE" value="mobile" />
	<input type="hidden"  name="s_mobile"  value="${pageRequest.filters.mobile}" />
	<input type="hidden"  name="ec_s_t0_TELEPHONE" value="<%=map.get("ec_s_t0_TELEPHONE")==null?"":map.get("ec_s_t0_TELEPHONE")%>"/>
	<input type="hidden"  name="ec_a_TELEPHONE" value="telephone" />
	<input type="hidden"  name="s_telephone"  value="${pageRequest.filters.telephone}" />
	<input type="hidden"  name="ec_s_t0_ADDRESS" value="<%=map.get("ec_s_t0_ADDRESS")==null?"":map.get("ec_s_t0_ADDRESS")%>"/>
	<input type="hidden"  name="ec_a_ADDRESS" value="address" />
	<input type="hidden"  name="s_address"  value="${pageRequest.filters.address}" />
	<input type="hidden"  name="ec_s_t0_EMAIL" value="<%=map.get("ec_s_t0_EMAIL")==null?"":map.get("ec_s_t0_EMAIL")%>"/>
	<input type="hidden"  name="ec_a_EMAIL" value="email" />
	<input type="hidden"  name="s_email"  value="${pageRequest.filters.email}" />
	<input type="hidden"  name="ec_s_rfidcode" value="<%=map.get("ec_s_rfidcode")==null?"":map.get("ec_s_rfidcode")%>"/>
	
	
	