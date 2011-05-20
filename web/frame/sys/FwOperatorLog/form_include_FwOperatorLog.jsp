<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwOperatorLog.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwOperatorLog.ALIAS_CREATE_ID%>：
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.createId_related.value}" autocomplete="true" xtype="drm-complex-select" id="createId_label" columnNameLower="createId" bmClassId="FW_EMPLOYEE" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.createId}" id="createId_value" name="createId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwOperatorLog.ALIAS_CREATE_DATE%>：
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="createDateString" name="createDateString" value="${model.createDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwOperatorLog.ALIAS_TARGET_ID%>：
		</td>	
		<td>
	   		<input type="text" value="${model.targetId}" size="30" name="targetId"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwOperatorLog.ALIAS_ACTION%>：
		</td>	
		<td>
	   		<input type="text" value="${model.action}" size="30" name="action"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwOperatorLog.ALIAS_OPERATOR_TYPE%>：
		</td>	
		<td>
	   		<input type="text" value="${model.operatorType}" size="30" name="operatorType"  class=""/>
		</td>
	</tr>
