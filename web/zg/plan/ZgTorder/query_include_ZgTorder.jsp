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
	<input type="hidden"  name="ec_s_t0_ORDER_STATE" value="<%=map.get("ec_s_t0_ORDER_STATE")==null?"":map.get("ec_s_t0_ORDER_STATE")%>"/>
	<input type="hidden"  name="ec_a_ORDER_STATE" value="orderState" />
	<input type="hidden"  name="s_orderState"  value="${pageRequest.filters.orderState}" />
	<input type="hidden"  name="ec_s_t0_SUBMIT_USER" value="<%=map.get("ec_s_t0_SUBMIT_USER")==null?"":map.get("ec_s_t0_SUBMIT_USER")%>"/>
	<input type="hidden"  name="ec_a_SUBMIT_USER" value="submitUser" />
	<input type="hidden"  name="s_submitUser"  value="${pageRequest.filters.submitUser}" />
	<input type="hidden"  name="ec_s_t0_SUBMIT_DATE" value="<%=map.get("ec_s_t0_SUBMIT_DATE")==null?"":map.get("ec_s_t0_SUBMIT_DATE")%>"/>
	<input type="hidden"  name="ec_a_SUBMIT_DATE" value="submitDate" />
	<input type="hidden"  name="s_submitDate_start"  value="${pageRequest.filters.submitDate_start}" />
	<input type="hidden"  name="s_submitDate_end"  value="${pageRequest.filters.submitDate_end}" />
	<input type="hidden"  name="ec_s_t0_MANDT" value="<%=map.get("ec_s_t0_MANDT")==null?"":map.get("ec_s_t0_MANDT")%>"/>
	<input type="hidden"  name="ec_a_MANDT" value="mandt" />
	<input type="hidden"  name="s_mandt"  value="${pageRequest.filters.mandt}" />
	<input type="hidden"  name="ec_s_t0_PXDAT" value="<%=map.get("ec_s_t0_PXDAT")==null?"":map.get("ec_s_t0_PXDAT")%>"/>
	<input type="hidden"  name="ec_a_PXDAT" value="pxdat" />
	<input type="hidden"  name="s_pxdat_start"  value="${pageRequest.filters.pxdat_start}" />
	<input type="hidden"  name="s_pxdat_end"  value="${pageRequest.filters.pxdat_end}" />
	<input type="hidden"  name="ec_s_t0_PLANT" value="<%=map.get("ec_s_t0_PLANT")==null?"":map.get("ec_s_t0_PLANT")%>"/>
	<input type="hidden"  name="ec_a_PLANT" value="plant" />
	<input type="hidden"  name="s_plant"  value="${pageRequest.filters.plant}" />
	<input type="hidden"  name="ec_s_t0_MIPOS" value="<%=map.get("ec_s_t0_MIPOS")==null?"":map.get("ec_s_t0_MIPOS")%>"/>
	<input type="hidden"  name="ec_a_MIPOS" value="mipos" />
	<input type="hidden"  name="s_mipos"  value="${pageRequest.filters.mipos}" />
	<input type="hidden"  name="ec_s_t0_PCDAT" value="<%=map.get("ec_s_t0_PCDAT")==null?"":map.get("ec_s_t0_PCDAT")%>"/>
	<input type="hidden"  name="ec_a_PCDAT" value="pcdat" />
	<input type="hidden"  name="ec_s_t0_ARBPL1" value="<%=map.get("ec_s_t0_ARBPL1")==null?"":map.get("ec_s_t0_ARBPL1")%>"/>

	<input type="hidden"  name="ec_s_t0_ARBPL" value="<%=map.get("ec_s_t0_ARBPL")==null?"":map.get("ec_s_t0_ARBPL")%>"/>
	<input type="hidden"  name="ec_a_ARBPL" value="arbpl" />
	<input type="hidden"  name="s_arbpl"  value="${pageRequest.filters.arbpl}" />
	<input type="hidden"  name="s_arbpl1"  value="${pageRequest.filters.arbpl1}" />
	<input type="hidden"  name="ec_s_t0_MATNR" value="<%=map.get("ec_s_t0_MATNR")==null?"":map.get("ec_s_t0_MATNR")%>"/>
	<input type="hidden"  name="ec_a_MATNR" value="matnr" />
	<input type="hidden"  name="s_matnr"  value="${pageRequest.filters.matnr}" />
	<input type="hidden"  name="ec_s_t0_KDAUF" value="<%=map.get("ec_s_t0_KDAUF")==null?"":map.get("ec_s_t0_KDAUF")%>"/>
	<input type="hidden"  name="ec_a_KDAUF" value="kdauf" />
	<input type="hidden"  name="s_kdauf"  value="${pageRequest.filters.kdauf}" />
	<input type="hidden"  name="ec_s_t0_KDPOS" value="<%=map.get("ec_s_t0_KDPOS")==null?"":map.get("ec_s_t0_KDPOS")%>"/>
	<input type="hidden"  name="ec_a_KDPOS" value="kdpos" />
	<input type="hidden"  name="s_kdpos"  value="${pageRequest.filters.kdpos}" />
	<input type="hidden"  name="ec_s_t0_KDTXT" value="<%=map.get("ec_s_t0_KDTXT")==null?"":map.get("ec_s_t0_KDTXT")%>"/>
	<input type="hidden"  name="ec_a_KDTXT" value="kdtxt" />
	<input type="hidden"  name="s_kdtxt"  value="${pageRequest.filters.kdtxt}" />
	<input type="hidden"  name="ec_s_t0_ZCKPP" value="<%=map.get("ec_s_t0_ZCKPP")==null?"":map.get("ec_s_t0_ZCKPP")%>"/>
	<input type="hidden"  name="ec_a_ZCKPP" value="zckpp" />
	<input type="hidden"  name="s_zckpp"  value="${pageRequest.filters.zckpp}" />
	<input type="hidden"  name="ec_s_t0_MAKTX2" value="<%=map.get("ec_s_t0_MAKTX2")==null?"":map.get("ec_s_t0_MAKTX2")%>"/>
	<input type="hidden"  name="ec_a_MAKTX2" value="maktx2" />
	<input type="hidden"  name="s_maktx2"  value="${pageRequest.filters.maktx2}" />
	<input type="hidden"  name="ec_s_t0_MAKTX1" value="<%=map.get("ec_s_t0_MAKTX1")==null?"":map.get("ec_s_t0_MAKTX1")%>"/>
	<input type="hidden"  name="ec_a_MAKTX1" value="maktx1" />
	<input type="hidden"  name="s_maktx1"  value="${pageRequest.filters.maktx1}" />
	<input type="hidden"  name="ec_s_t0_ZZCKS" value="<%=map.get("ec_s_t0_ZZCKS")==null?"":map.get("ec_s_t0_ZZCKS")%>"/>
	<input type="hidden"  name="ec_a_ZZCKS" value="zzcks" />
	<input type="hidden"  name="s_zzcks"  value="${pageRequest.filters.zzcks}" />
	<input type="hidden"  name="ec_s_t0_ATWRT2" value="<%=map.get("ec_s_t0_ATWRT2")==null?"":map.get("ec_s_t0_ATWRT2")%>"/>
	<input type="hidden"  name="ec_a_ATWRT2" value="atwrt2" />
	<input type="hidden"  name="s_atwrt2"  value="${pageRequest.filters.atwrt2}" />
	<input type="hidden"  name="ec_s_t0_PSMNG" value="<%=map.get("ec_s_t0_PSMNG")==null?"":map.get("ec_s_t0_PSMNG")%>"/>
	<input type="hidden"  name="ec_a_PSMNG" value="psmng" />
	<input type="hidden"  name="s_psmng"  value="${pageRequest.filters.psmng}" />
	<input type="hidden"  name="ec_s_t0_PMENGE" value="<%=map.get("ec_s_t0_PMENGE")==null?"":map.get("ec_s_t0_PMENGE")%>"/>
	<input type="hidden"  name="ec_a_PMENGE" value="pmenge" />
	<input type="hidden"  name="s_pmenge"  value="${pageRequest.filters.pmenge}" />
	<input type="hidden"  name="ec_s_t0_ZTXT02" value="<%=map.get("ec_s_t0_ZTXT02")==null?"":map.get("ec_s_t0_ZTXT02")%>"/>
	<input type="hidden"  name="ec_a_ZTXT02" value="ztxt02" />
	<input type="hidden"  name="s_ztxt02"  value="${pageRequest.filters.ztxt02}" />
	<input type="hidden"  name="ec_s_t0_ZDBLC" value="<%=map.get("ec_s_t0_ZDBLC")==null?"":map.get("ec_s_t0_ZDBLC")%>"/>
	<input type="hidden"  name="ec_a_ZDBLC" value="zdblc" />
	<input type="hidden"  name="s_zdblc"  value="${pageRequest.filters.zdblc}" />
	<input type="hidden"  name="ec_s_t0_BRGEW2" value="<%=map.get("ec_s_t0_BRGEW2")==null?"":map.get("ec_s_t0_BRGEW2")%>"/>
	<input type="hidden"  name="ec_a_BRGEW2" value="brgew2" />
	<input type="hidden"  name="s_brgew2"  value="${pageRequest.filters.brgew2}" />
	<input type="hidden"  name="ec_s_t0_CRDAT" value="<%=map.get("ec_s_t0_CRDAT")==null?"":map.get("ec_s_t0_CRDAT")%>"/>
	<input type="hidden"  name="ec_a_CRDAT" value="crdat" />
	<input type="hidden"  name="s_crdat_start"  value="${pageRequest.filters.crdat_start}" />
	<input type="hidden"  name="s_crdat_end"  value="${pageRequest.filters.crdat_end}" />
	<input type="hidden"  name="ec_s_t0_CPUTM" value="<%=map.get("ec_s_t0_CPUTM")==null?"":map.get("ec_s_t0_CPUTM")%>"/>
	<input type="hidden"  name="ec_a_CPUTM" value="cputm" />
	<input type="hidden"  name="s_cputm_start"  value="${pageRequest.filters.cputm_start}" />
	<input type="hidden"  name="s_cputm_end"  value="${pageRequest.filters.cputm_end}" />
	<input type="hidden"  name="ec_s_t0_CRNAM" value="<%=map.get("ec_s_t0_CRNAM")==null?"":map.get("ec_s_t0_CRNAM")%>"/>
	<input type="hidden"  name="ec_a_CRNAM" value="crnam" />
	<input type="hidden"  name="s_crnam"  value="${pageRequest.filters.crnam}" />
	<input type="hidden"  name="ec_s_t0_MRNAM" value="<%=map.get("ec_s_t0_MRNAM")==null?"":map.get("ec_s_t0_MRNAM")%>"/>
	<input type="hidden"  name="ec_a_MRNAM" value="mrnam" />
	<input type="hidden"  name="s_mrnam_start"  value="${pageRequest.filters.mrnam_start}" />
	<input type="hidden"  name="s_mrnam_end"  value="${pageRequest.filters.mrnam_end}" />
	<input type="hidden"  name="ec_s_t0_ZMUZE" value="<%=map.get("ec_s_t0_ZMUZE")==null?"":map.get("ec_s_t0_ZMUZE")%>"/>
	<input type="hidden"  name="ec_a_ZMUZE" value="zmuze" />
	<input type="hidden"  name="s_zmuze_start"  value="${pageRequest.filters.zmuze_start}" />
	<input type="hidden"  name="s_zmuze_end"  value="${pageRequest.filters.zmuze_end}" />
	<input type="hidden"  name="ec_s_t0_MNAME" value="<%=map.get("ec_s_t0_MNAME")==null?"":map.get("ec_s_t0_MNAME")%>"/>
	<input type="hidden"  name="ec_a_MNAME" value="mname" />
	<input type="hidden"  name="s_mname"  value="${pageRequest.filters.mname}" />
	<input type="hidden"  name="ec_s_t0_FBDAT" value="<%=map.get("ec_s_t0_FBDAT")==null?"":map.get("ec_s_t0_FBDAT")%>"/>
	<input type="hidden"  name="ec_a_FBDAT" value="fbdat" />
	<input type="hidden"  name="s_fbdat_start"  value="${pageRequest.filters.fbdat_start}" />
	<input type="hidden"  name="s_fbdat_end"  value="${pageRequest.filters.fbdat_end}" />
	<input type="hidden"  name="ec_s_t0_FBUZE" value="<%=map.get("ec_s_t0_FBUZE")==null?"":map.get("ec_s_t0_FBUZE")%>"/>
	<input type="hidden"  name="ec_a_FBUZE" value="fbuze" />
	<input type="hidden"  name="s_fbuze_start"  value="${pageRequest.filters.fbuze_start}" />
	<input type="hidden"  name="s_fbuze_end"  value="${pageRequest.filters.fbuze_end}" />
	<input type="hidden"  name="ec_s_t0_FNAME" value="<%=map.get("ec_s_t0_FNAME")==null?"":map.get("ec_s_t0_FNAME")%>"/>
	<input type="hidden"  name="ec_a_FNAME" value="fname" />
	<input type="hidden"  name="s_fname"  value="${pageRequest.filters.fname}" />
	<input type="hidden"  name="ec_s_t0_PFLAG" value="<%=map.get("ec_s_t0_PFLAG")==null?"":map.get("ec_s_t0_PFLAG")%>"/>
	<input type="hidden"  name="ec_a_PFLAG" value="pflag" />
	<input type="hidden"  name="s_pflag"  value="${pageRequest.filters.pflag}" />
	
	<input type="hidden"  name="s_pcdat_start"  value="${pageRequest.filters.pcdat_start}" />
	<input type="hidden"  name="s_pcdat_end"  value="${pageRequest.filters.pcdat_end}" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	
