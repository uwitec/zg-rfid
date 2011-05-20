<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTcarbomSuppliers.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTcarbomSuppliers.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
			if(initLayout) {
				$(window).bind("load",initLayout);
				$(window).bind("resize",initLayout);
			}
		});
	</script>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<form action="${ctx}/zg/plan/ZgTcarbomSuppliers/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTcarbomSuppliers.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTcarbomSuppliers.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTcarbomSuppliers.ALIAS_CAR_BOM_ID%></td>	
						<td><s:property value="%{model.carBomId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbomSuppliers.ALIAS_LIFNR%></td>	
						<td><s:property value="%{model.lifnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbomSuppliers.ALIAS_LIFNR_NAME%></td>	
						<td><s:property value="%{model.lifnrName}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbomSuppliers.ALIAS_CAR_NUM%></td>	
						<td><s:property value="%{model.carNum}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbomSuppliers.ALIAS_SUPPLIERS_ID%></td>	
						<td><s:property value="%{model.suppliersId}" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="后退" onclick="history.back();"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>