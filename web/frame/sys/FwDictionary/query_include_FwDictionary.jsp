<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_DICT_BUSINESS_ID" value="<%=map.get("ec_s_t0_DICT_BUSINESS_ID")==null?"":map.get("ec_s_t0_DICT_BUSINESS_ID")%>"/>
	<input type="hidden"  name="ec_a_DICT_BUSINESS_ID" value="dictBusinessId" />
	<input type="hidden"  name="s_dictBusinessId"  value="${pageRequest.filters.dictBusinessId}" />
	<input type="hidden"  name="ec_s_t0_LABEL_CN" value="<%=map.get("ec_s_t0_LABEL_CN")==null?"":map.get("ec_s_t0_LABEL_CN")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_s_t0_VALUE" value="<%=map.get("ec_s_t0_VALUE")==null?"":map.get("ec_s_t0_VALUE")%>"/>
	<input type="hidden"  name="ec_a_VALUE" value="value" />
	<input type="hidden"  name="s_value"  value="${pageRequest.filters.value}" />
	<input type="hidden"  name="ec_s_t0_LABEL" value="<%=map.get("ec_s_t0_LABEL")==null?"":map.get("ec_s_t0_LABEL")%>"/>
	<input type="hidden"  name="ec_a_LABEL" value="label" />
	<input type="hidden"  name="s_label"  value="${pageRequest.filters.label}" />
	<input type="hidden"  name="ec_s_t0_DISPLAY_ORDER" value="<%=map.get("ec_s_t0_DISPLAY_ORDER")==null?"":map.get("ec_s_t0_DISPLAY_ORDER")%>"/>
	<input type="hidden"  name="ec_a_DISPLAY_ORDER" value="displayOrder" />
	<input type="hidden"  name="s_displayOrder"  value="${pageRequest.filters.displayOrder}" />
	<input type="hidden"  name="ec_s_t0_EXTEND" value="<%=map.get("ec_s_t0_EXTEND")==null?"":map.get("ec_s_t0_EXTEND")%>"/>
	<input type="hidden"  name="ec_a_EXTEND" value="extend" />
	<input type="hidden"  name="s_extend"  value="${pageRequest.filters.extend}" />
