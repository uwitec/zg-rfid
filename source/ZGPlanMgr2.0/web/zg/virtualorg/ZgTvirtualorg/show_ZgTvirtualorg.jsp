<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.virtualorg.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTvirtualorg.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTvirtualorg.TABLE_ALIAS%>ShowDiv").accordion({
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
	<div id="infoPanel">
			<table width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">

							<div class="button" style="text-align: left;">
								<a href="javascript:"><span
									onclick="if(parent.doQueryForEmployee)parent.doQueryForEmployee()"><img
											src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</div>
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
			<div class="form_panel">
				<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top:3px;">
					<tr>	
						<td  class="label"><%=ZgTvirtualorg.ALIAS_USERID%></td>	
						<td  width="30%"><s:property value="%{model.userId}" /></td>
					</tr>
					<tr>	
						<td  class="label"><%=ZgTvirtualorg.ALIAS_USERNAME%></td>	
						<td  width="30%"><s:property value="%{model.userName}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_SEX%></td>	
						<td  width="30%"><s:property value="%{model.sex}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_MOBILE%></td>	
						<td  width="30%"><s:property value="%{model.mobile}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_EMAIL%></td>	
						<td  width="30%"><s:property value="%{model.email}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_TYPE%></td>	
						<td  width="30%"><s:property value="%{model.type}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_LABEL_CN%></td>	
						<td  width="30%"><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_ORG_ID%></td>	
						<td  width="30%"><s:property value="%{model.orgName}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTvirtualorg.ALIAS_NOTE%></td>	
						<td  width="30%"><s:property value="%{model.note}" /></td>
					</tr>
				</table>
			</div>
</body>
</html>