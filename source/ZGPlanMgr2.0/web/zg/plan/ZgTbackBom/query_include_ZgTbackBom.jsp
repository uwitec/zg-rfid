<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_AUFNR" value="<%=map.get("ec_s_t0_AUFNR")==null?"":map.get("ec_s_t0_AUFNR")%>"/>
	<input type="hidden"  name="ec_a_AUFNR" value="aufnr" />
	<input type="hidden"  name="s_aufnr"  value="${pageRequest.filters.aufnr}" />
	<input type="hidden"  name="ec_s_t0_MATNR" value="<%=map.get("ec_s_t0_MATNR")==null?"":map.get("ec_s_t0_MATNR")%>"/>
	<input type="hidden"  name="ec_a_MATNR" value="matnr" />
	<input type="hidden"  name="s_matnr"  value="${pageRequest.filters.matnr}" />
	<input type="hidden"  name="ec_s_t0_MAKTX" value="<%=map.get("ec_s_t0_MAKTX")==null?"":map.get("ec_s_t0_MAKTX")%>"/>
	<input type="hidden"  name="ec_a_MAKTX" value="maktx" />
	<input type="hidden"  name="s_maktx"  value="${pageRequest.filters.maktx}" />
	<input type="hidden"  name="ec_s_t0_MENGE_D" value="<%=map.get("ec_s_t0_MENGE_D")==null?"":map.get("ec_s_t0_MENGE_D")%>"/>
	<input type="hidden"  name="ec_a_MENGE_D" value="mengeD" />
	<input type="hidden"  name="s_mengeD"  value="${pageRequest.filters.mengeD}" />
	<input type="hidden"  name="ec_s_t0_LGORT_D" value="<%=map.get("ec_s_t0_LGORT_D")==null?"":map.get("ec_s_t0_LGORT_D")%>"/>
	<input type="hidden"  name="ec_a_LGORT_D" value="lgortD" />
	<input type="hidden"  name="s_lgortD"  value="${pageRequest.filters.lgortD}" />
	<input type="hidden"  name="ec_s_t0_BUDAT" value="<%=map.get("ec_s_t0_BUDAT")==null?"":map.get("ec_s_t0_BUDAT")%>"/>
	<input type="hidden"  name="ec_a_BUDAT" value="budat" />
	<input type="hidden"  name="s_budat_start"  value="${pageRequest.filters.budat_start}" />
	<input type="hidden"  name="s_budat_end"  value="${pageRequest.filters.budat_end}" />
	<input type="hidden"  name="ec_s_t0_CPUTM" value="<%=map.get("ec_s_t0_CPUTM")==null?"":map.get("ec_s_t0_CPUTM")%>"/>
	<input type="hidden"  name="ec_a_CPUTM" value="cputm" />
	<input type="hidden"  name="s_cputm_start"  value="${pageRequest.filters.cputm_start}" />
	<input type="hidden"  name="s_cputm_end"  value="${pageRequest.filters.cputm_end}" />
