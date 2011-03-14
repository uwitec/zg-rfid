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
	<title><%=ZgMateriel.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript">
		var accordion;
		$(function() {
			if(true){
				$("form:first").submit();
			}
		});
		
		function add(){
			openDialog("add_advance_ZgMateriel.jsp",300,40);
			window.location=window.location;
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
	<form action="${ctx}/zg/materiel/ZgMateriel/listForAdvance.do" method="post" target="listFrame">
		<table  id="query_table0" class="query_panel" cellpadding="0" cellspacing="0">
					<tr>
						<td class="label" width="20%">
							<%=ZgMateriel.ALIAS_MATERIEL_NAME%>：
						</td>	
						<td align="left">
					   		<input type="text" maxlength="13" name="s_materielName" id="s_materielName"/>
						</td>
					</tr>
					<tr>
						<td colspan="9">
							<div class="toolbar">
									<a href="javascript:batchValidation('listFrame','${ctx}/zg/materiel/ZgMateriel/listForAdvance.do',document.forms[0]);document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
									<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
									 <a href="javascript:add()"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a> 
									<!-- <a href="javascript:delBomCar('${ctx}/frame/sys/ZgBomCar/delete.do')"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a> -->
									<!-- <a href="javascript:exportData(1)"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
									<a href="javascript:exportData(2)"><span><img src="<%=iconPath%>/page_excel.png" />导出全部</span></a> -->
							</div>
						</td>
					</tr>
			</table>
		</form>
<iframe src="" autolayout="true" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>