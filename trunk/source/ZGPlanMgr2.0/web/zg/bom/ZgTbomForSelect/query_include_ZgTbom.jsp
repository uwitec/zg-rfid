<%@page import="com.boco.zg.bom.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>

	<input type="hidden"  name="ec_s_MAKTX" value="<%=map.get("ec_s_MAKTX")==null?"":map.get("ec_s_MAKTX")%>"/>
	<input type="hidden"  name="ec_a_MAKTX" value="maktx" />
	<input type="hidden"  name="s_maktx"  value="${pageRequest.filters.maktx}" />
	<input type="hidden"  name="ec_s_MATNR" value="<%=map.get("ec_s_MATNR")==null?"":map.get("ec_s_MATNR")%>"/>
	<input type="hidden"  name="ec_a_MATNR" value="matnr" />
	<input type="hidden"  name="s_matnr"  value="${pageRequest.filters.matnr}" />
	<input type="hidden"  name="ec_s_MSEHL" value="<%=map.get("ec_s_MSEHL")==null?"":map.get("ec_s_MSEHL")%>"/>
	<input type="hidden"  name="ec_a_MSEHL" value="msehl" />
	<input type="hidden"  name="s_msehl"  value="${pageRequest.filters.msehl}" />
	<input type="hidden"  name="ec_s_MATKL" value="<%=map.get("ec_s_MATKL")==null?"":map.get("ec_s_MATKL")%>"/>
	<input type="hidden"  name="ec_a_MATKL" value="matkl" />
	<input type="hidden"  name="s_matkl"  value="${pageRequest.filters.matkl}" />
	<input type="hidden"  name="ec_s_ZBZ" value="<%=map.get("ec_s_ZBZ")==null?"":map.get("ec_s_ZBZ")%>"/>
	<input type="hidden"  name="ec_a_ZBZ" value="zbz" />
	<input type="hidden"  name="s_zbz"  value="${pageRequest.filters.zbz}" />
	<input type="hidden"  name="ec_s_ZRZQD" value="<%=map.get("ec_s_ZRZQD")==null?"":map.get("ec_s_ZRZQD")%>"/>
	<input type="hidden"  name="ec_a_ZRZQD" value="zrzqd" />
	<input type="hidden"  name="s_zrzqd"  value="${pageRequest.filters.zrzqd}" />
	<input type="hidden"  name="ec_s_LGORT" value="<%=map.get("ec_s_LGORT")==null?"":map.get("ec_s_LGORT")%>"/>
	<input type="hidden"  name="ec_a_LGORT" value="lgort" />
	<input type="hidden"  name="s_lgort"  value="${pageRequest.filters.lgort}" />
