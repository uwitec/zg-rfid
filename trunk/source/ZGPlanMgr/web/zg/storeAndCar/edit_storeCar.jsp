<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.boco.frame.sys.base.model.ZgBomCar"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#ZgBomCarEditDiv").accordion();
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
		});
		function saveBomCar(){
		//	if($("form[validator]").valid())
		//	{
				document.forms[0].submit();
		//	}
		} 
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form action="${ctx}/frame/sys/ZgBomCar/update.do?bomId=${bomId }" method="post"
			validator="true">

			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a href="javascript:saveBomCar()"><span><img
												src="<%=iconPath%>/true.gif" />提交</span>
									</a>
									<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img
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
						<%@ include file="form_include_ZgBomCar.jsp"%>
					</tbody>
				</table>
			</div>
		</form>
	</body>
</html>