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
	<title><%=FwOperator.TABLE_ALIAS%>查询</title>
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
			accordion=$("#<%=FwOperator.TABLE_ALIAS%>QueryDiv").accordion({
				collapsible: true,
				active   : '0'
			});
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/frame/sys/FwOperator/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=FwOperator.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<div id="<%=FwOperator.TABLE_ALIAS%>QueryDiv">
		<h3><a href="#"><%=FwOperator.TABLE_ALIAS%>查询</a></h3>
		<div>
		<table class="formTable">
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_LABEL_CN%></td>
			   <td>
		   			<input type="text" maxlength="40" name="s_labelCn" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_USER_ID%></td>
			   <td>
		   			<input type="text" name="s_userId" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_PASSWORD%></td>
			   <td>
		   			<input type="text" name="s_password" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_CREATE_TIME%></td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_createTime_start" size="30" readonly ="true"/>
		   			至　<input type="text" dateFlag="true" name="s_createTime_end" size="30" readonly ="true"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_LAST_MODIFY_TIME%></td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_lastModifyTime_start" size="30" readonly ="true"/>
		   			至　<input type="text" dateFlag="true" name="s_lastModifyTime_end" size="30" readonly ="true"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_EFFECT_TIME%></td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_effectTime_start" size="30" readonly ="true"/>
		   			至　<input type="text" dateFlag="true" name="s_effectTime_end" size="30" readonly ="true"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_STATUS%></td>
			   <td>
		   			<input type="text" name="s_status" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=FwOperator.ALIAS_RELATED_BM_CLASS_ID%></td>
			   <td>
		   			<input type="text" name="s_relatedBmClassId" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="查询" onclick="batchValidation('listFrame','${ctx}/frame/sys/FwOperator/list.do',document.forms[0])"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>
<iframe src="" autolayout="true" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>