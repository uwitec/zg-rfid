<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=FwMenu.TABLE_ALIAS%>信息</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=FwMenu.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form action="${ctx}/frame/sys/FwMenu/list.do" method="get" theme="">
			<s:hidden name="cuid" id="cuid" value="%{model.cuid}" />
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
			<div class="form_panel">
				<table class="form_table" cellpadding="0" cellspacing="1"
					style="margin-top: 3px;">
					<tbody attr="tbody_1">
						<tr>
							<td class="label">
								<span style="color: red">*</span>菜单名称：
							</td>
							<td>
								${model.labelCn}

							</td>
						</tr>
						<tr>
							<td class="label">
								<span style="color: red">*</span> 父菜单：
							</td>
							<td>

								${model.parentMenuId_related.value}
							</td>
						</tr>
						<tr>
							<td class="label">
								菜单链接：
							</td>
							<td>
								${model.url}
							</td>
						</tr>
						<tr>
							<td class="label">
								<span style="color: red">*</span> 顺序：
							</td>
							<td>
								${model.displayOrder}
							</td>
						</tr>
						<tr>
							<td class="label">
								<span style="color: red">*</span>级别：
							</td>
							<td>
								${model.levelNum}
							</td>
						</tr>
						<tr>
							<td class="label">
								<span style="color: red">*</span>菜单控制类型：
							</td>
							<td>
								${model.type}
							</td>
						</tr>
				</table>
			</div>
		</form>
	</body>
</html>