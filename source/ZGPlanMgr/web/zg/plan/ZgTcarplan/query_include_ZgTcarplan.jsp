<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_CUID" value="<%=map.get("ec_s_CUID")==null?"":map.get("ec_s_CUID")%>"/>
	<input type="hidden"  name="ec_s_DEPARTMENT_NAME" value="<%=map.get("DEPARTMENT_NAME")==null?"":map.get("DEPARTMENT_NAME")%>"/>
	
	
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_s_CAR_STATE" value="<%=map.get("ec_s_CAR_STATE")==null?"":map.get("ec_s_CAR_STATE")%>"/>
	<input type="hidden"  name="ec_a_CAR_STATE" value="CAR_STATE" />
	<input type="hidden"  name="s_CAR_STATE"  value="${pageRequest.filters.CAR_STATE}" />
	<input type="hidden"  name="ec_s_t0_CREATE_USER_ID" value="<%=map.get("ec_s_t0_CREATE_USER_ID")==null?"":map.get("ec_s_t0_CREATE_USER_ID")%>"/>
	<input type="hidden"  name="ec_a_CREATE_USER_ID" value="createUserId" />
	<input type="hidden"  name="s_createUserId"  value="${pageRequest.filters.createUserId}" />
	<input type="hidden"  name="ec_s_CREATE_DATE" value="<%=map.get("ec_s_CREATE_DATE")==null?"":map.get("ec_s_CREATE_DATE")%>"/>
	<input type="hidden"  name="ec_a_CREATE_DATE" value="createDate" />
	<input type="hidden"  name="s_createDate_start"  value="${pageRequest.filters.createDate_start}" />
	<input type="hidden"  name="s_createDate_end"  value="${pageRequest.filters.createDate_end}" />
	<input type="hidden"  name="ec_s_CARUSER_LABELCN" value="<%=map.get("ec_s_CARUSER_LABELCN")==null?"":map.get("ec_s_CARUSER_LABELCN")%>"/>
	<input type="hidden"  name="ec_a_CAR_USER" value="carUser" />
	<input type="hidden"  name="s_carUser"  value="${pageRequest.filters.carUser}" />
	<input type="hidden"  name="ec_s_t0_CAR_DATE" value="<%=map.get("ec_s_t0_CAR_DATE")==null?"":map.get("ec_s_t0_CAR_DATE")%>"/>
	<input type="hidden"  name="ec_a_CAR_DATE" value="carDate" />
	<input type="hidden"  name="s_carDate_start"  value="${pageRequest.filters.carDate_start}" />
	<input type="hidden"  name="s_carDate_end"  value="${pageRequest.filters.carDate_end}" />
	<input type="hidden"  name="ec_s_t0_STORAGE_USER_ID" value="<%=map.get("ec_s_t0_STORAGE_USER_ID")==null?"":map.get("ec_s_t0_STORAGE_USER_ID")%>"/>
	<input type="hidden"  name="ec_a_STORAGE_USER_ID" value="storageUserId" />
	<input type="hidden"  name="s_storageUserId"  value="${pageRequest.filters.storageUserId}" />
	<input type="hidden"  name="ec_s_STORAGE_ID" value="<%=map.get("ec_s_STORAGE_ID")==null?"":map.get("ec_s_STORAGE_ID")%>"/>
	<input type="hidden"  name="ec_s_STORAGE_NAME" value="<%=map.get("ec_s_STORAGE_NAME")==null?"":map.get("ec_s_STORAGE_NAME")%>"/>
	
	
	<input type="hidden"  name="ec_a_STORAGE_ID" value="storageId" />
	<input type="hidden"  name="s_storageId"  value="${pageRequest.filters.storageId}" />
	<input type="hidden"  name="ec_s_t0_REMARK" value="<%=map.get("ec_s_t0_REMARK")==null?"":map.get("ec_s_t0_REMARK")%>"/>
	<input type="hidden"  name="ec_a_REMARK" value="remark" />
	<input type="hidden"  name="s_remark"  value="${pageRequest.filters.remark}" />
	
	<input type="hidden"  name="ec_s_CARUSER_ID" value="<%=map.get("ec_s_CARUSER_ID")==null?"":map.get("ec_s_CARUSER_ID")%>"/>
	
	<input type="hidden"  name="s_type"  value="${pageRequest.filters.type}" />
	<input type="hidden"  name="s_orderPlanType"  value="${pageRequest.filters.orderPlanType}" />
	<input type="hidden"  name="s_carState"  value="${pageRequest.filters.carState}" />
	<input type="hidden"  name="s_cuid"  value="${pageRequest.filters.cuid}" />
	
