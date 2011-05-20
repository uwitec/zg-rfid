<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.bom.base.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>"/>
	<%@ include file="/commons/meta.jsp" %>
	<title><%=ZgTbom.TABLE_ALIAS%>信息</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgCarInfo.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/zg/bom/ZgCarInfo/list.do" method="get" theme="simple">
	<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
	
		<div id="infoPanel">
			<table class="form_table" cellpadding="1" cellspacing="1" style="margin-top:3px;">
				<thead>
					<tr>
						<td class="formToolbar">
							<div class="button" style="text-align: left;">
								<a href="javascript:"><span
									onclick="history.back();"><img
											src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</div>
				<div class="form_panel">
	<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top:3px;">
		<tbody attr="tbody_1">
			<tr>
						<td   class="label"><%=ZgCarInfo.ALIAS_CODE%>：</td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>		
						<td   class="label"><%=ZgCarInfo.ALIAS_LABEL_CN%>：</td>	
						<td ><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td   class="label"><%=ZgCarInfo.ALIAS_DESCRIPTION%>：</td>	
						<td ><s:property value="%{model.description}" /></td>
					</tr>
				</table>
			</div>
</form>

</body>

</html>