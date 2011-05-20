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
	<title><%=FwDictionary.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=FwDictionary.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}/frame/sys/FwDictionary/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=FwDictionary.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=FwDictionary.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=FwDictionary.ALIAS_DICT_BUSINESS_ID%></td>	
						<td><s:property value="%{model.dictBusinessId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwDictionary.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwDictionary.ALIAS_VALUE%></td>	
						<td><s:property value="%{model.value}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwDictionary.ALIAS_LABEL%></td>	
						<td><s:property value="%{model.label}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwDictionary.ALIAS_DISPLAY_ORDER%></td>	
						<td><s:property value="%{model.displayOrder}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=FwDictionary.ALIAS_EXTEND%></td>	
						<td><s:property value="%{model.extend}" /></td>
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