<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.boco.zg.virtualorg.base.model.ZgTvirtualorg"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>

	<head>
		<base href="<%=basePath%>" />
		<base target="_self"/><!-- 提交给自己 -->
		<title>物料组等级设置</title>
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
	
		function returnMsg(){
			window.returnValue=$("#materielLevel").val();
			window.close();
		}
	</script>
	</head>

	<body>
		<form id="tt" action="${ctx}/zg/virtualorg/ZgTvirtualorg/saveVirtualorg.do" method="post"
			validator="true">
			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1" style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a href="javascript:returnMsg()"><span><img
												src="<%=iconPath%>/true.gif" />保存</span> </a>&nbsp
									<a href="javascript:window.close();"><span><img
												src="<%=iconPath%>/ico_007.gif" />关闭</span> </a>
								</div>
							</td>
						</tr>
					</thead>
				</table>
			</div>
			<div class="form_panel">
					<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top: 3px;">
						<tbody attr="tbody_1">
						<tr>
							<td class="label">
								<span style="color:red">*</span>物料组编号：
							</td>
							<td>
								${param.materielId }
							</td>
						</tr>
						<tr>
							<td class="label">
								<span style="color:red">*</span>物料组等级：
							</td>
							<td>
								<select id="materielLevel">
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								</select>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</form>
	</body>
</html>