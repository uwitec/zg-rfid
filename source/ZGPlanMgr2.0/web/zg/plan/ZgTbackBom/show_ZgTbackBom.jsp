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
	<title><%=ZgTbackBom.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/default/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/default/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/default/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
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
									onclick="if(parent.doQuery)parent.doQuery()"><img
											src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</div>
	
	<form action="${ctx}/zg/plan/ZgTbackBom/list.do" method="get" theme="simple">
			<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		
		<div class="form_panel">
			<table class="form_table" cellpadding="1" cellspacing="1" style="margin-top:3px;">
				
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_AUFNR%></td>	
						<td><s:property value="%{model.aufnr}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_MATNR%></td>	
						<td><s:property value="%{model.matnr}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_MAKTX%></td>	
						<td><s:property value="%{model.maktx}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_MENGE_D%></td>	
						<td><s:property value="%{model.mengeD}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_LGORT_D%></td>	
						<td><s:property value="%{model.lgortD}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_BUDAT%></td>	
						<td><s:property value="%{model.budatString}" /></td>
					</tr>
					<tr>	
						<td class="label"><%=ZgTbackBom.ALIAS_CPUTM%></td>	
						<td><s:property value="%{model.cputmString}" /></td>
					</tr>
				</table>
			</div>
	</form>
	
	
</body>
</html>