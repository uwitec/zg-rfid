<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
		<input type="hidden"  name="ec_s_t0_CUID" value="<%=map.get("ec_s_t0_CUID")==null?"":map.get("ec_s_t0_CUID")%>"/>
	
	<input type="hidden"  name="ec_s_t0_LABEL_CN" value="<%=map.get("ec_s_t0_LABEL_CN")==null?"":map.get("ec_s_t0_LABEL_CN")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_s_t0_ORDER_ID" value="<%=map.get("ec_s_t0_ORDER_ID")==null?"":map.get("ec_s_t0_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_ID" value="orderId" />
	<input type="hidden"  name="s_orderId"  value="${pageRequest.filters.orderId}" />
	<input type="hidden"  name="ec_s_t0_ARBPL" value="<%=map.get("ec_s_t0_ARBPL")==null?"":map.get("ec_s_t0_ARBPL")%>"/>
	<input type="hidden"  name="ec_a_ARBPL" value="arbpl" />
	<input type="hidden"  name="s_arbpl"  value="${pageRequest.filters.arbpl}" />
	<input type="hidden"  name="ec_s_t0_LGORT" value="<%=map.get("ec_s_t0_LGORT")==null?"":map.get("ec_s_t0_LGORT")%>"/>
	<input type="hidden"  name="ec_a_LGORT" value="lgort" />
	<input type="hidden"  name="s_lgort"  value="${pageRequest.filters.lgort}" />
	<input type="hidden"  name="ec_s_t0_DEPT_ID" value="<%=map.get("ec_s_t0_DEPT_ID")==null?"":map.get("ec_s_t0_DEPT_ID")%>"/>
	<input type="hidden"  name="ec_a_DEPT_ID" value="deptId" />
	<input type="hidden"  name="s_deptId"  value="${pageRequest.filters.deptId}" />
	<input type="hidden"  name="ec_s_t0_CREATOR_ID" value="<%=map.get("ec_s_t0_CREATOR_ID")==null?"":map.get("ec_s_t0_CREATOR_ID")%>"/>
	<input type="hidden"  name="ec_a_CREATOR_ID" value="creatorId" />
	<input type="hidden"  name="s_creatorId"  value="${pageRequest.filters.creatorId}" />
	<input type="hidden"  name="ec_s_t0_ZBZ" value="<%=map.get("ec_s_t0_ZBZ")==null?"":map.get("ec_s_t0_ZBZ")%>"/>
	<input type="hidden"  name="ec_a_ZBZ" value="zbz" />
	<input type="hidden"  name="s_zbz"  value="${pageRequest.filters.zbz}" />
	<input type="hidden"  name="ec_s_t0_CREATE_DATE" value="<%=map.get("ec_s_t0_CREATE_DATE")==null?"":map.get("ec_s_t0_CREATE_DATE")%>"/>
	<input type="hidden"  name="ec_a_CREATE_DATE" value="createDate" />
	<input type="hidden"  name="s_createDate_start"  value="${pageRequest.filters.createDate_start}" />
	<input type="hidden"  name="s_createDate_end"  value="${pageRequest.filters.createDate_end}" />
	<input type="hidden"  name="ec_s_t0_STATE" value="<%=map.get("ec_s_t0_STATE")==null?"":map.get("ec_s_t0_STATE")%>"/>
	<input type="hidden"  name="ec_a_STATE" value="state" />
	<input type="hidden"  name="s_state"  value="${pageRequest.filters.state}" />
	<input type="hidden"  name="ec_s_t0_TYPE" value="<%=map.get("ec_s_t0_TYPE")==null?"":map.get("ec_s_t0_TYPE")%>"/>
	<input type="hidden"  name="ec_a_TYPE" value="type" />
	<input type="hidden"  name="s_type"  value="${pageRequest.filters.type}" />
	<input type="hidden"  name="ec_s_t0_PRODUCT_TYPE" value="<%=map.get("ec_s_t0_PRODUCT_TYPE")==null?"":map.get("ec_s_t0_PRODUCT_TYPE")%>"/>
	<input type="hidden"  name="ec_a_PRODUCT_TYPE" value="productType" />
	<input type="hidden"  name="s_productType"  value="${pageRequest.filters.productType}" />

	<input type="hidden"  name="s_aufnr"  value="${pageRequest.filters.aufnr}" />
	<input type="hidden"  name="s_carUser"  value="${pageRequest.filters.carUser}" />
	
	