<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.bom.base.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>">
	<%@ include file="/commons/meta.jsp" %>
	<title><%=ZgTbom.TABLE_ALIAS%>信息</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/widgets/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTbom.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/zg/bom/ZgTbom/list.do" method="get" theme="simple">
	<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
	<div id="<%=ZgTbom.TABLE_ALIAS%>ShowDiv">
		<h3><a href="#"><%=ZgTbom.TABLE_ALIAS%>信息</a></h3>
		<div>
			<table class="formTable">
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_MAKTX%></td>	
					<td><s:property value="%{model.maktx}" /></td>
				</tr>
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_MATNR%></td>	
					<td><s:property value="%{model.matnr}" /></td>
				</tr>
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_MSEHL%></td>	
					<td><s:property value="%{model.msehl}" /></td>
				</tr>
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_MATKL%></td>	
					<td><s:property value="%{model.matkl}" /></td>
				</tr>
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_ZBZ%></td>	
					<td><s:property value="%{model.zbz}" /></td>
				</tr>
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_ZRZQD%></td>	
					<td><s:property value="%{model.zrzqd}" /></td>
				</tr>
				<tr>	
					<td class="tdLabel"><%=ZgTbom.ALIAS_LGORT%></td>	
					<td><s:property value="%{model.lgort}" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="返回列表" onclick="window.location='${ctx}/zg/bom/ZgTbom/list.do'"/>
						<input type="button" value="后退" onclick="history.back();"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>

</body>

</html>