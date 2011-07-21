<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>" />
	<base target="_self"/>
	<title><%=ZgTstoragebom.TABLE_ALIAS%>新增</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTstoragebom.TABLE_ALIAS%>CreateDiv").accordion();
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
<form action="${ctx}/zg/storage/ZgTstoragebomEx/saveBomForStorage.do" method="post" validator="true">
	<table class="formTable">
		<tr class="toolbar">
			<td width="220px" style="padding-left:10px">
				<input type="submit"  value="保存"  class="commonButton queryButton"/>
				<input type="button" value="退出" class="commonButton queryExButton" 
					onclick="window.close()"/>
			</td>
		</tr>
	</table>
	<div id="<%=ZgTstoragebom.TABLE_ALIAS%>EditDiv">
		<div>
		<table class="formTable" >
				<%@ include file="form_include_ZgTstoragebom.jsp" %>
		</table>
		</div>
	</div>
</form>
</body>
</html>