<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTbom.TABLE_ALIAS%>编辑</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<%@ include file="/commons/meta.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/widgets/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTbom.TABLE_ALIAS%>EditDiv").accordion();
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/zg/bom/ZgTbom/update.do" method="post">
	<div id="<%=ZgTbom.TABLE_ALIAS%>EditDiv">
		<h3><a href="#"><%=ZgTbom.TABLE_ALIAS%>编辑</a></h3>
		<div>
		<table class="formTable">
			<%@ include file="form_include_ZgTbom.jsp" %>
			<tr>
				<td colspan="2">
					<input id="submitButton" name="submitButton" type="submit" value="提交" />
					<input type="button" value="返回列表" onclick="window.location='${ctx}/zg/bom/ZgTbom/list.do'"/>
					<input type="button" value="后退" onclick="history.back();"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>

<script>
	
	new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
		var finalResult = result;
		
		//在这里添加自定义验证
		
		return disableSubmit(finalResult,'submitButton');
	}});
</script>

</body>

</html>