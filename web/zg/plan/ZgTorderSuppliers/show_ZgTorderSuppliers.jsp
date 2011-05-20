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
	<title><%=ZgTorderSuppliers.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTorderSuppliers.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}/zg/plan/ZgTorderSuppliers/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTorderSuppliers.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTorderSuppliers.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTorderSuppliers.ALIAS_AUFNR%></td>	
						<td><s:property value="%{model.aufnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderSuppliers.ALIAS_IDNRK%></td>	
						<td><s:property value="%{model.idnrk}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderSuppliers.ALIAS_LIFNR%></td>	
						<td><s:property value="%{model.lifnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderSuppliers.ALIAS_LIFNR_NAME%></td>	
						<td><s:property value="%{model.lifnrName}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderSuppliers.ALIAS_PLANT%></td>	
						<td><s:property value="%{model.plant}" /></td>
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