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
	<title><%=FwEmployee.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=FwEmployee.TABLE_ALIAS%>ShowDiv").accordion({
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
	<form action="${ctx}//frame/sys/FwEmployee/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div class="form_panel">
			<table class="form_table" cellpadding="1" cellspacing="1" style="margin-top:3px;">
				<caption>
					<label>
						<a href="javascript:"><span>查看员工</span></a>
					</label>
				</caption>
					<tr>	
						<td   class="label"><%=FwEmployee.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
						
						<td class="label"><%=FwEmployee.ALIAS_USER_ID%></td>	
						<td><s:property value="%{model.userId}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_PASSWORD%></td>	
						<td><s:property value="%{model.password}" /></td>
						
						<td class="label"><%=FwEmployee.ALIAS_CREATE_TIME%></td>	
						<td><s:property value="%{model.createTimeString}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_LAST_MODIFY_TIME%></td>	
						<td><s:property value="%{model.lastModifyTimeString}" /></td>
						
						<td class="label"><%=FwEmployee.ALIAS_EFFECT_TIME%></td>	
						<td><s:property value="%{model.effectTimeString}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_STATUS%></td>	
						<td><s:property value="%{model.status}" /></td>
						
						<td class="label"><%=FwEmployeeOrganazation.ALIAS_ORG_ID%></td>	
						<td><s:property value="%{model1.orgId}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_SEX%></td>
						<c:if test="${model.sex eq '2'}">
							<td>女</td>
						</c:if>
						<c:if test="${model.sex eq '1'}">
							<td>男</td>
						</c:if>
						
						<td class="label"><%=FwEmployee.ALIAS_MOBILE%></td>	
						<td><s:property value="%{model.mobile}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_TELEPHONE%></td>	
						<td><s:property value="%{model.telephone}" /></td>
					
						<td class="label"><%=FwEmployee.ALIAS_ADDRESS%></td>	
						<td><s:property value="%{model.address}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_EMAIL%></td>	
						<td><s:property value="%{model.email}" /></td>
					</tr>
					<tr>
						<td class="formToolbar">
							<div class="button" style="text-align: left;">
								<a href="javascript:if(parent.doQuery)parent.doQuery()"><span  onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />后退</span></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
	</form>
</body>
</html>