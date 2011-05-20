<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_CAR_BOM_ID" value="<%=map.get("ec_s_t0_CAR_BOM_ID")==null?"":map.get("ec_s_t0_CAR_BOM_ID")%>"/>
	<input type="hidden"  name="ec_a_CAR_BOM_ID" value="carBomId" />
	<input type="hidden"  name="s_carBomId"  value="${pageRequest.filters.carBomId}" />
	<input type="hidden"  name="ec_s_t0_LIFNR" value="<%=map.get("ec_s_t0_LIFNR")==null?"":map.get("ec_s_t0_LIFNR")%>"/>
	<input type="hidden"  name="ec_a_LIFNR" value="lifnr" />
	<input type="hidden"  name="s_lifnr"  value="${pageRequest.filters.lifnr}" />
	<input type="hidden"  name="ec_s_t0_LIFNR_NAME" value="<%=map.get("ec_s_t0_LIFNR_NAME")==null?"":map.get("ec_s_t0_LIFNR_NAME")%>"/>
	<input type="hidden"  name="ec_a_LIFNR_NAME" value="lifnrName" />
	<input type="hidden"  name="s_lifnrName"  value="${pageRequest.filters.lifnrName}" />
	<input type="hidden"  name="ec_s_t0_CAR_NUM" value="<%=map.get("ec_s_t0_CAR_NUM")==null?"":map.get("ec_s_t0_CAR_NUM")%>"/>
	<input type="hidden"  name="ec_a_CAR_NUM" value="carNum" />
	<input type="hidden"  name="s_carNum"  value="${pageRequest.filters.carNum}" />
	<input type="hidden"  name="ec_s_t0_SUPPLIERS_ID" value="<%=map.get("ec_s_t0_SUPPLIERS_ID")==null?"":map.get("ec_s_t0_SUPPLIERS_ID")%>"/>
	<input type="hidden"  name="ec_a_SUPPLIERS_ID" value="suppliersId" />
	<input type="hidden"  name="s_suppliersId"  value="${pageRequest.filters.suppliersId}" />
