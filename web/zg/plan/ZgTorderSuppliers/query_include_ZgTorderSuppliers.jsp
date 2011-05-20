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
	<input type="hidden"  name="ec_s_t0_IDNRK" value="<%=map.get("ec_s_t0_IDNRK")==null?"":map.get("ec_s_t0_IDNRK")%>"/>
	<input type="hidden"  name="ec_a_IDNRK" value="idnrk" />
	<input type="hidden"  name="s_idnrk"  value="${pageRequest.filters.idnrk}" />
	<input type="hidden"  name="ec_s_t0_LIFNR" value="<%=map.get("ec_s_t0_LIFNR")==null?"":map.get("ec_s_t0_LIFNR")%>"/>
	<input type="hidden"  name="ec_a_LIFNR" value="lifnr" />
	<input type="hidden"  name="s_lifnr"  value="${pageRequest.filters.lifnr}" />
	<input type="hidden"  name="ec_s_t0_LIFNR_NAME" value="<%=map.get("ec_s_t0_LIFNR_NAME")==null?"":map.get("ec_s_t0_LIFNR_NAME")%>"/>
	<input type="hidden"  name="ec_a_LIFNR_NAME" value="lifnrName" />
	<input type="hidden"  name="s_lifnrName"  value="${pageRequest.filters.lifnrName}" />
	<input type="hidden"  name="ec_s_t0_PLANT" value="<%=map.get("ec_s_t0_PLANT")==null?"":map.get("ec_s_t0_PLANT")%>"/>
	<input type="hidden"  name="ec_a_PLANT" value="plant" />
	<input type="hidden"  name="s_plant"  value="${pageRequest.filters.plant}" />
