<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=FwOperator.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=FwOperator.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}/frame/sys/FwOperator/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=FwOperator.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=FwOperator.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_USER_ID%></td>	
						<td><s:property value="%{model.userId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_PASSWORD%></td>	
						<td><s:property value="%{model.password}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_CREATE_TIME%></td>	
						<td><s:property value="%{model.createTimeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_LAST_MODIFY_TIME%></td>	
						<td><s:property value="%{model.lastModifyTimeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_EFFECT_TIME%></td>	
						<td><s:property value="%{model.effectTimeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_STATUS%></td>	
						<td><s:property value="%{model.status}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwOperator.ALIAS_RELATED_BM_CLASS_ID%></td>	
						<td><s:property value="%{model.relatedBmClassId}" /></td>
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