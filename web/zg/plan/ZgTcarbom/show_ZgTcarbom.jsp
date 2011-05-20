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
	<title><%=ZgTcarbom.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTcarbom.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}/zg/plan/ZgTcarbom/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTcarbom.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTcarbom.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTcarbom.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbom.ALIAS_ORDER_PLAN_ID%></td>	
						<td><s:property value="%{model.orderPlanId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbom.ALIAS_ORDER_BOM_ID%></td>	
						<td><s:property value="%{model.orderBomId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbom.ALIAS_ORDER_ID%></td>	
						<td><s:property value="%{model.orderId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbom.ALIAS_PLAN_NUM%></td>	
						<td><s:property value="%{model.planNum}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTcarbom.ALIAS_REAL_NUM%></td>	
						<td><s:property value="%{model.realNum}" /></td>
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