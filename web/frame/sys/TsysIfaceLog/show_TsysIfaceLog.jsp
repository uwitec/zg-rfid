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
	<title><%=TsysIfaceLog.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=TsysIfaceLog.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}//frame/sys/TsysIfaceLog/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=TsysIfaceLog.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=TsysIfaceLog.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_SER_CALLER%></td>	
						<td><s:property value="%{model.serCaller}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_SER_SUPPLIER%></td>	
						<td><s:property value="%{model.serSupplier}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_INTERFACE_NAME%></td>	
						<td><s:property value="%{model.interfaceName}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_METHOD_NAME%></td>	
						<td><s:property value="%{model.methodName}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_DATA_STAUTS%></td>	
						<td><s:property value="%{model.dataStauts}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_PARAMETERS%></td>	
						<td><s:property value="%{model.parameters}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_RESULT%></td>	
						<td><s:property value="%{model.result}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_CALL_TIME%></td>	
						<td><s:property value="%{model.callTimeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=TsysIfaceLog.ALIAS_REMARK%></td>	
						<td><s:property value="%{model.remark}" /></td>
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