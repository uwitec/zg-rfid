<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<title>新增提前领料</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	 <base target="_self"/>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript"> 
		function add(){
			if($("#matId").val()==''){
				alert('请输入物料组编号');
				return;
			}
			$("form:first").submit();
		}
	
	</script>
</head>

<body>
	<form action="${ctx}/zg/materiel/ZgMateriel/addAdvance.do" method="post">
		<table  id="query_table0" class="query_panel" cellpadding="0" cellspacing="0">
					<tr>
						<td class="label" width="20%">
							请输入物料组编号：
						</td>	
						<td align="left">
					   		<input type="text" maxlength="13" name="matId" id="matId"/>
									 <a class="a-toolbar" href="javascript:add()"><span><img src="<%=iconPath%>/addition.gif" />确定</span></a> 
						</td>
					</tr>
					<tr>
						<td colspan="9">
							
						</td>
					</tr>
			</table>
		</form>
</body>
</html>