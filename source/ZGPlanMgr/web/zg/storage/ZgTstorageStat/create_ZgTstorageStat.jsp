<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTstorageStat.TABLE_ALIAS%>新增</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTstorageStat.TABLE_ALIAS%>CreateDiv").accordion();
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
			$("input[dateFlag=true]")
			.datepicker({
				showAnim:'',showOtherMonths: true,
				selectOtherMonths: true,
				dateFormat:"yy-mm-dd"
			})
			.css("cursor","pointer")
			.after("<img src='${ctx}/resources/images/frame/calendar.png'></img>");
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/storage/ZgTstorageStat/save.do" method="post" validator="true">
	<div id="<%=ZgTstorageStat.TABLE_ALIAS%>CreateDiv">
		<h3><a href="#"><%=ZgTstorageStat.TABLE_ALIAS%>新增</a></h3>
		<div>
		<table class="formTable">
			<%@ include file="form_include_ZgTstorageStat.jsp" %>
			<tr>
				<td colspan="2">
					<input id="submitButton" name="submitButton" type="submit" value="提交" />
					<input type="button" value="后退" onclick="history.back();"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>
</body>
</html>