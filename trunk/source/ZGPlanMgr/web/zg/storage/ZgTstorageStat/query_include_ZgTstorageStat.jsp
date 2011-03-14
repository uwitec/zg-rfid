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
	<input type="hidden"  name="ec_s_t0_ORDER_ID" value="<%=map.get("ec_s_t0_ORDER_ID")==null?"":map.get("ec_s_t0_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_ID" value="orderId" />
	<input type="hidden"  name="ec_s_t0_3_3_t0_ARBPL" value="<%=map.get("ec_s_t0_3_3_t0_ARBPL")==null?"":map.get("ec_s_t0_3_3_t0_ARBPL")%>"/>
	<input type="hidden"  name="ec_s_t0_3_3_t0_MATNR" value="<%=map.get("ec_s_t0_3_3_t0_MATNR")==null?"":map.get("ec_s_t0_3_3_t0_MATNR")%>"/>
	<input type="hidden"  name="ec_s_t0_IDNRK" value="<%=map.get("ec_s_t0_IDNRK")==null?"":map.get("ec_s_t0_IDNRK")%>"/>
	<input type="hidden"  name="ec_s_t0_3_3_t0_MSEHL1" value="<%=map.get("ec_s_t0_3_3_t0_MSEHL1")==null?"":map.get("ec_s_t0_3_3_t0_MSEHL1")%>"/>
	<input type="hidden"  name="s_orderId"  value="${pageRequest.filters.orderId}" />
	<input type="hidden"  name="ec_s_t0_ORDER_BOMID" value="<%=map.get("ec_s_t0_ORDER_BOMID")==null?"":map.get("ec_s_t0_ORDER_BOMID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_BOMID" value="orderBomId" />
	<input type="hidden"  name="s_orderBomId"  value="${pageRequest.filters.orderBomId}" />
	<input type="hidden"  name="ec_s_t0_OUTNUM" value="<%=map.get("ec_s_t0_OUTNUM")==null?"":map.get("ec_s_t0_OUTNUM")%>"/>
	<input type="hidden"  name="ec_a_OUTNUM" value="outNum" />
	<input type="hidden"  name="s_outNum"  value="${pageRequest.filters.outNum}" />
	<input type="hidden"  name="ec_s_t0_NUM" value="<%=map.get("ec_s_t0_NUM")==null?"":map.get("ec_s_t0_NUM")%>"/>
	<input type="hidden"  name="ec_a_NUM" value="num" />
	<input type="hidden"  name="s_num"  value="${pageRequest.filters.num}" />
	<input type="hidden"  name="ec_s_t0_PRODUCT_TYPE" value="<%=map.get("ec_s_t0_PRODUCT_TYPE")==null?"":map.get("ec_s_t0_PRODUCT_TYPE")%>"/>
	<input type="hidden"  name="ec_a_PRODUCT_TYPE" value="productType" />
	<input type="hidden"  name="s_productType"  value="${pageRequest.filters.productType}" />
	<input type="hidden"  name="ec_s_t0_LGORT" value="<%=map.get("ec_s_t0_LGORT")==null?"":map.get("ec_s_t0_LGORT")%>"/>
	<input type="hidden"  name="ec_s_t0_2_2_t0_LABEL_CN" value="<%=map.get("ec_s_t0_2_2_t0_LABEL_CN")==null?"":map.get("ec_s_t0_2_2_t0_LABEL_CN")%>"/>
	
	<input type="hidden"  name="ec_a_LGORT" value="lgort" />
	<input type="hidden"  name="s_lgort"  value="${pageRequest.filters.lgort}" />
	<input type="hidden"  name="s_type"  value="${pageRequest.filters.type}" />
	<input type="hidden"  name="s_aufnr"  value="${pageRequest.filters.aufnr}" />
	<input type="hidden"  name="s_arbpl"  value="${pageRequest.filters.arbpl}" />
	<input type="hidden"  name="s_idnrk"  value="${pageRequest.filters.idnrk}" />
	