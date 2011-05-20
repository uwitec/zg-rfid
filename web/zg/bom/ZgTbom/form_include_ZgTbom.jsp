<%@page import="com.boco.zg.bom.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="cuid" name="cuid" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@vs@ALIAS_MAKTX}" key="maktx" value="%{model.maktx}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@vs@ALIAS_MATNR}" key="matnr" value="%{model.matnr}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@vs@ALIAS_MSEHL}" key="msehl" value="%{model.msehl}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@vs@ALIAS_MATKL}" key="matkl" value="%{model.matkl}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@vs@ALIAS_ZBZ}" key="zbz" value="%{model.zbz}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@vs@ALIAS_ZRZQD}" key="zrzqd" value="%{model.zrzqd}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@vs@ALIAS_LGORT}" key="lgort" value="%{model.lgort}" cssClass="" required="false" />
	
