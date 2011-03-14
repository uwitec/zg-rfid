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
	<title><%=ZgTorderPlanbom.TABLE_ALIAS%>查询</title>
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
			accordion=$("#<%=ZgTorderPlanbom.TABLE_ALIAS%>QueryDiv").accordion({
				collapsible: true,
				active   : '0'
			});
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
		});
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/plan/ZgTorderPlanbom/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=ZgTorderPlanbom.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<div id="<%=ZgTorderPlanbom.TABLE_ALIAS%>QueryDiv">
		<h3><a href="#"><%=ZgTorderPlanbom.TABLE_ALIAS%>查询</a></h3>
		<div>
		<table class="formTable">
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_LABEL_CN%></td>
			   <td>
		   			<input type="text" name="s_labelCn" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_ORDER_BOM_ID%></td>
			   <td>
		   			<input type="text" size="30" autocomplete="true" xtype="drm-complex-select" id="orderBomId_label" columnNameLower="orderBomId" bmClassId="ZG_T_ORDERBOM" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="orderBomId_value" name="s_orderBomId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_ORDER_ID%></td>
			   <td>
		   			<input type="text" size="30" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="orderId_value" name="s_orderId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_DEPARTMENT_ID%></td>
			   <td>
		   			<input type="text" size="30" autocomplete="true" xtype="tree:1" id="departmentId_label" columnNameLower="departmentId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="departmentId_value" name="s_departmentId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_USER_ID%></td>
			   <td>
		   			<input type="text" size="30" autocomplete="true" xtype="drm-complex-select" id="userId_label" columnNameLower="userId" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN"/>
		   			<input type="hidden" id="userId_value" name="s_userId"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_PLAN_DATE%></td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_planDate_start" size="30"/>
		   			至　<input type="text" dateFlag="true" name="s_planDate_end" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_PLAN_START_TIME%></td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_planStartTime_start" size="30"/>
		   			至　<input type="text" dateFlag="true" name="s_planStartTime_end" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_PLAN_END_TIME%></td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_planEndTime_start" size="30"/>
		   			至　<input type="text" dateFlag="true" name="s_planEndTime_end" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_STATE%></td>
			   <td>
		   			<input type="text" name="s_state" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_COMPLETE_NUM%></td>
			   <td>
		   			<input type="text" name="s_completeNum" size="30"/>
			   </td>
			</tr>
			<tr bgcolor="#FFFFFF">
			   <td class="tdLabel"><%=ZgTorderPlanbom.ALIAS_CAR_NUM%></td>
			   <td>
		   			<input type="text" name="s_carNum" size="30"/>
			   </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="查询" onclick="batchValidation('listFrame','${ctx}/zg/plan/ZgTorderPlanbom/list.do',document.forms[0])"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</form>
<iframe src="" autolayout="true" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</body>
</html>