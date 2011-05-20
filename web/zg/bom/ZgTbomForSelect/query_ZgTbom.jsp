<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			if(true){
				$("form:first").submit();
			}
			$("input[dateFlag=true]")
			.datepicker({
				showAnim:'',showOtherMonths: true,
				selectOtherMonths: true,
				dateFormat:"yy-mm-dd"
			})
			.css({cursor:"pointer",lineHeight:"18px",width:"80px"})
			.after("<img src='${ctx}/resources/images/frame/calendar.png'></img>");
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/zg/bom/ZgTbom/listBomForSelect.do" method="post" target="listFrame">
<table class="formTable">
		<tr class="toolbar">
			<td width="50px" style="padding-left:10px">
				<input type="submit" value="查询" class="commonButton queryButton" 
					onclick="batchValidation('listFrame','${ctx}/zg/bom/ZgTbom/listBomForSelect.do',document.forms[0])" />
			</td>
			<td>
			
			<input type="hidden" value="${lgort}" name="s_lgort" />
			<input type="hidden" value="${orderPlanId}" name="s_orderPlanId" />
				BOM组件：
				<input type="text"  name="s_label_cn" maxlength="40"/>
				物料描述：
				<input type="text"  name="s_maktx" maxlength="40"/>
			</td>
			
		</tr>
	</table>
	
</form>
<iframe src="${ctx}/zg/bom/ZgTbom/listBomForSelect.do" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>