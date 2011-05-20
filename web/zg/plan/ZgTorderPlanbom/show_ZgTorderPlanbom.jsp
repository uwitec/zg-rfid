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
	<title><%=ZgTorderPlanbom.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTorderPlanbom.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}/zg/plan/ZgTorderPlanbom/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTorderPlanbom.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTorderPlanbom.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_ORDER_BOM_ID%></td>	
						<td><s:property value="%{model.orderBomId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_ORDER_ID%></td>	
						<td><s:property value="%{model.orderId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_DEPARTMENT_ID%></td>	
						<td><s:property value="%{model.departmentId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_USER_ID%></td>	
						<td><s:property value="%{model.userId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_PLAN_DATE%></td>	
						<td><s:property value="%{model.planDateString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_PLAN_START_TIME%></td>	
						<td><s:property value="%{model.planStartTimeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_PLAN_END_TIME%></td>	
						<td><s:property value="%{model.planEndTimeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_STATE%></td>	
						<td><s:property value="%{model.state}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_COMPLETE_NUM%></td>	
						<td><s:property value="%{model.completeNum}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_CAR_NUM%></td>	
						<td><s:property value="%{model.carNum}" /></td>
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