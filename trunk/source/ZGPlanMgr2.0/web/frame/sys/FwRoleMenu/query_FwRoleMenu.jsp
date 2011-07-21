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
	<title><%=FwRoleMenu.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript">
		var accordion;
		$(function() {
			if(true){
				$("form:first").submit();
			}
			$("button ,input:button,input:submit").button();
			accordion=$("#<%=FwRoleMenu.TABLE_ALIAS%>QueryDiv").accordion({
				collapsible: true,
				active   : '0'
			});
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/frame/sys/FwRoleMenu/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=FwRoleMenu.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<div id="<%=FwRoleMenu.TABLE_ALIAS%>QueryDiv">
		<h3><a href="#"><%=FwRoleMenu.TABLE_ALIAS%>查询</a></h3>
		<div>
		<table class="formTable">
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwRoleMenu.ALIAS_ROLE_ID%></td>
			   <td>
		   			<input type="text" size="30" maxlength="40" autocomplete="true" xtype="drm-complex-select" id="roleId_label" columnNameLower="roleId" bmClassId="FW_ROLE" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="roleId_value" name="s_roleId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwRoleMenu.ALIAS_MENU_ID%></td>
			   <td>
		   			<input type="text" size="30" maxlength="40" autocomplete="true" xtype="url:http://www.163.com" id="menuId_label" columnNameLower="menuId" bmClassId="FW_MENU" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="menuId_value" name="s_menuId"/>
			   </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="查询" onclick="batchValidation('listFrame','${ctx}/frame/sys/FwRoleMenu/list.do',document.forms[0])"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>
<iframe src="" autolayout="true" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>