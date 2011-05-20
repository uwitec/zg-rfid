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
	<title><%=FwRole.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			if(initLayout) {
				$(window).bind("load",initLayout);
				
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
									onclick="if(parent.doQuery)parent.doQuery()"><img
											src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
							</div>
						</td>
					</tr>
				</thead>
			</table>
</div>

<div>
		<table class="formTable">
		<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
				

<tr>
	<td class="title" colspan="8">

		角色
	</td>
</tr>
</thead>
<tbody id="tbody_1" style="display: block">
	<tr>
		<td colspan="8"
			style="border: 1px solid #A8CFEB; border-width: 0 0 1px 0;">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th width="10%">
			<span class="alterlabel">*</span><%=FwRole.ALIAS_LABEL_CN%>
		</th>
		<td>
			${model.labelCn}

		</td>
	</tr>
	<tr>
		<th width="10%">
			<%=FwOrganization.ALIAS_PARENT_ORG_ID%>：
		</th>
		<td>
		${model.note}

		</td>

	</tr>
	</tbody>
	</table>
	
</body>
</html>