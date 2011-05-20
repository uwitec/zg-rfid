<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_IDNRK" value="<%=map.get("ec_s_t0_IDNRK")==null?"":map.get("ec_s_t0_IDNRK")%>"/>
	<input type="hidden"  name="ec_a_IDNRK" value="idnrk" />
	<input type="hidden"  name="s_idnrk"  value="${pageRequest.filters.idnrk}" />
	<input type="hidden"  name="ec_s_t0_ORDER_ID" value="<%=map.get("ec_s_t0_ORDER_ID")==null?"":map.get("ec_s_t0_ORDER_ID")%>"/>
	<input type="hidden"  name="ec_a_ORDER_ID" value="orderId" />
	<input type="hidden"  name="s_orderId"  value="${pageRequest.filters.orderId}" />
	<input type="hidden"  name="ec_s_t0_AUFNR" value="<%=map.get("ec_s_t0_AUFNR")==null?"":map.get("ec_s_t0_AUFNR")%>"/>
	<input type="hidden"  name="ec_a_AUFNR" value="aufnr" />
	<input type="hidden"  name="s_aufnr"  value="${pageRequest.filters.aufnr}" />
	<input type="hidden"  name="ec_s_t0_ARBPL" value="<%=map.get("ec_s_t0_ARBPL")==null?"":map.get("ec_s_t0_ARBPL")%>"/>
	<input type="hidden"  name="ec_a_ARBPL" value="arbpl" />
	<input type="hidden"  name="s_arbpl"  value="${pageRequest.filters.arbpl}" />
	<input type="hidden"  name="ec_s_t0_MATNR" value="<%=map.get("ec_s_t0_MATNR")==null?"":map.get("ec_s_t0_MATNR")%>"/>
	<input type="hidden"  name="ec_a_MATNR" value="matnr" />
	<input type="hidden"  name="s_matnr"  value="${pageRequest.filters.matnr}" />
	<input type="hidden"  name="ec_s_t0_MAKTX1" value="<%=map.get("ec_s_t0_MAKTX1")==null?"":map.get("ec_s_t0_MAKTX1")%>"/>
	<input type="hidden"  name="ec_a_MAKTX1" value="maktx1" />
	<input type="hidden"  name="s_maktx1"  value="${pageRequest.filters.maktx1}" />
	<input type="hidden"  name="ec_s_t0_MAKTX2" value="<%=map.get("ec_s_t0_MAKTX2")==null?"":map.get("ec_s_t0_MAKTX2")%>"/>
	<input type="hidden"  name="ec_a_MAKTX2" value="maktx2" />
	<input type="hidden"  name="s_maktx2"  value="${pageRequest.filters.maktx2}" />
	<input type="hidden"  name="ec_s_t0_MSEHL1" value="<%=map.get("ec_s_t0_MSEHL1")==null?"":map.get("ec_s_t0_MSEHL1")%>"/>
	<input type="hidden"  name="ec_a_MSEHL1" value="msehl1" />
	<input type="hidden"  name="s_msehl1"  value="${pageRequest.filters.msehl1}" />
	<input type="hidden"  name="ec_s_t0_MSEHL2" value="<%=map.get("ec_s_t0_MSEHL2")==null?"":map.get("ec_s_t0_MSEHL2")%>"/>
	<input type="hidden"  name="ec_a_MSEHL2" value="msehl2" />
	<input type="hidden"  name="s_msehl2"  value="${pageRequest.filters.msehl2}" />
	<input type="hidden"  name="ec_s_t0_ZDTYL" value="<%=map.get("ec_s_t0_ZDTYL")==null?"":map.get("ec_s_t0_ZDTYL")%>"/>
	<input type="hidden"  name="ec_a_ZDTYL" value="zdtyl" />
	<input type="hidden"  name="s_zdtyl"  value="${pageRequest.filters.zdtyl}" />
	<input type="hidden"  name="ec_s_t0_MENGE" value="<%=map.get("ec_s_t0_MENGE")==null?"":map.get("ec_s_t0_MENGE")%>"/>
	<input type="hidden"  name="ec_a_MENGE" value="menge" />
	<input type="hidden"  name="s_menge"  value="${pageRequest.filters.menge}" />
	<input type="hidden"  name="ec_s_t0_MATKL" value="<%=map.get("ec_s_t0_MATKL")==null?"":map.get("ec_s_t0_MATKL")%>"/>
	<input type="hidden"  name="ec_a_MATKL" value="matkl" />
	<input type="hidden"  name="s_matkl"  value="${pageRequest.filters.matkl}" />
	<input type="hidden"  name="ec_s_t0_SORTF" value="<%=map.get("ec_s_t0_SORTF")==null?"":map.get("ec_s_t0_SORTF")%>"/>
	<input type="hidden"  name="ec_a_SORTF" value="sortf" />
	<input type="hidden"  name="s_sortf"  value="${pageRequest.filters.sortf}" />
	<input type="hidden"  name="ec_s_t0_LGORT" value="<%=map.get("ec_s_t0_LGORT")==null?"":map.get("ec_s_t0_LGORT")%>"/>
	<input type="hidden"  name="ec_a_LGORT" value="lgort" />
	<input type="hidden"  name="s_lgort"  value="${pageRequest.filters.lgort}" />
	<input type="hidden"  name="ec_s_t0_ZBZ" value="<%=map.get("ec_s_t0_ZBZ")==null?"":map.get("ec_s_t0_ZBZ")%>"/>
	<input type="hidden"  name="ec_a_ZBZ" value="zbz" />
	<input type="hidden"  name="s_zbz"  value="${pageRequest.filters.zbz}" />
	<input type="hidden"  name="ec_s_t0_ZRZQD" value="<%=map.get("ec_s_t0_ZRZQD")==null?"":map.get("ec_s_t0_ZRZQD")%>"/>
	<input type="hidden"  name="ec_a_ZRZQD" value="zrzqd" />
	<input type="hidden"  name="s_zrzqd"  value="${pageRequest.filters.zrzqd}" />
