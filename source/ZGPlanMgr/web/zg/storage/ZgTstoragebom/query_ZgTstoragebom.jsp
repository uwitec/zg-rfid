<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTstoragebom.TABLE_ALIAS%>查询</title>
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
			accordion=$("#<%=ZgTstoragebom.TABLE_ALIAS%>QueryDiv").accordion({
				collapsible: true,
				active   : '0'
			});
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
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
<form action="${ctx}/zg/storage/ZgTstoragebom/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=ZgTstoragebom.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<div id="<%=ZgTstoragebom.TABLE_ALIAS%>QueryDiv">
		<h3><a href="#"><%=ZgTstoragebom.TABLE_ALIAS%>查询</a></h3>
		<div>
		<table class="formTable">
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTstoragebom.ALIAS_LABEL_CN%></td>
			   <td>
		   			<input type="text" name="s_labelCn" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTstoragebom.ALIAS_ZG_T_STORAGE_ID%></td>
			   <td>
		   			<input type="text" size="30" maxlength="40" autocomplete="true" xtype="drm-complex-select" id="zgTstorageId_label" columnNameLower="zgTstorageId" bmClassId="ZG_T_STORAGE" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="zgTstorageId_value" name="s_zgTstorageId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTstoragebom.ALIAS_MANTR%></td>
			   <td>
		   			<input type="text" name="s_mantr" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTstoragebom.ALIAS_NUM%></td>
			   <td>
		   			<input type="text" name="s_num" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTstoragebom.ALIAS_ZBZ%></td>
			   <td>
		   			<input type="text" name="s_zbz" size="30" maxlength="40"/>
			   </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="查询" onclick="batchValidation('listFrame','${ctx}/zg/storage/ZgTstoragebom/list.do',document.forms[0])"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>
<iframe src="" autolayout="true" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>