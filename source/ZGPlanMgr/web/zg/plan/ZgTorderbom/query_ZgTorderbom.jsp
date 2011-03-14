<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderbom.TABLE_ALIAS%>查询</title>
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
			accordion=$("#<%=ZgTorderbom.TABLE_ALIAS%>QueryDiv").accordion({
				collapsible: true,
				active   : '0'
			});
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/plan/ZgTorderbom/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=ZgTorderbom.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<div id="<%=ZgTorderbom.TABLE_ALIAS%>QueryDiv">
		<h3><a href="#"><%=ZgTorderbom.TABLE_ALIAS%>查询</a></h3>
		<div>
		<table class="formTable">
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_IDNRK%></td>
			   <td>
		   			<input type="text" name="s_idnrk" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_ORDER_ID%></td>
			   <td>
		   			<input type="text" size="30" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR"/>
		   			<input type="hidden" id="orderId_value" name="s_orderId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_AUFNR%></td>
			   <td>
		   			<input type="text" name="s_aufnr" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_ARBPL%></td>
			   <td>
		   			<input type="text" name="s_arbpl" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MATNR%></td>
			   <td>
		   			<input type="text" name="s_matnr" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MAKTX1%></td>
			   <td>
		   			<input type="text" name="s_maktx1" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MAKTX2%></td>
			   <td>
		   			<input type="text" name="s_maktx2" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MSEHL1%></td>
			   <td>
		   			<input type="text" name="s_msehl1" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MSEHL2%></td>
			   <td>
		   			<input type="text" name="s_msehl2" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_ZDTYL%></td>
			   <td>
		   			<input type="text" name="s_zdtyl" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MENGE%></td>
			   <td>
		   			<input type="text" name="s_menge" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_MATKL%></td>
			   <td>
		   			<input type="text" name="s_matkl" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_SORTF%></td>
			   <td>
		   			<input type="text" name="s_sortf" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_LGORT%></td>
			   <td>
		   			<input type="text" name="s_lgort" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_ZBZ%></td>
			   <td>
		   			<input type="text" name="s_zbz" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderbom.ALIAS_ZRZQD%></td>
			   <td>
		   			<input type="text" name="s_zrzqd" size="30"/>
			   </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="查询" onclick="batchValidation('listFrame','${ctx}/zg/plan/ZgTorderbom/list.do',document.forms[0])"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>
<iframe src="" autolayout="true" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>