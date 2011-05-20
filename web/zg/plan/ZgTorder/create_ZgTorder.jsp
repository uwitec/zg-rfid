<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%>新增</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTorder.TABLE_ALIAS%>CreateDiv").accordion();
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/plan/ZgTorder/save.do" method="post" validator="true">
	<div id="<%=ZgTorder.TABLE_ALIAS%>CreateDiv">
		<h3><a href="#"><%=ZgTorder.TABLE_ALIAS%>新增</a></h3>
		<div>
			<table class="formTable">
				<%@ include file="form_include_ZgTorder.jsp" %>
				<tr>
					<td colspan="2">
						<div class="button">
						<a href="javascript:document.forms[0].submit()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
						<a href="javascript:"><span onclick="history.back();"><img src="<%=iconPath%>/ico_007.gif" />后退</span></a>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>