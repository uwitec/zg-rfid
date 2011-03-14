<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTstorage.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTstorage.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
	</script>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<form action="${ctx}/zg/storage/ZgTstorage/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTstorage.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTstorage.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_ORDER_ID%></td>	
						<td><s:property value="%{model.orderId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_ARBPL%></td>	
						<td><s:property value="%{model.arbpl}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_LGORT%></td>	
						<td><s:property value="%{model.lgort}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_DEPT_ID%></td>	
						<td><s:property value="%{model.deptId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_CREATOR_ID%></td>	
						<td><s:property value="%{model.creatorId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_ZBZ%></td>	
						<td><s:property value="%{model.zbz}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_CREATE_DATE%></td>	
						<td><s:property value="%{model.createDateString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_STATE%></td>	
						<td><s:property value="%{model.state}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_TYPE%></td>	
						<td><s:property value="%{model.type}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTstorage.ALIAS_PRODUCT_TYPE%></td>	
						<td><s:property value="%{model.productType}" /></td>
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