<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwOperatorRole.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperatorRole.ALIAS_OPERATOR_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.operatorId_related.value}" autocomplete="true" xtype="drm-complex-select" id="operatorId_label" columnNameLower="operatorId" bmClassId="FW_OPERATOR" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.operatorId}" id="operatorId_value" name="operatorId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperatorRole.ALIAS_ROLE_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.roleId_related.value}" autocomplete="true" xtype="drm-complex-select" id="roleId_label" columnNameLower="roleId" bmClassId="FW_ROLE" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.roleId}" id="roleId_value" name="roleId"/>
		</td>
	</tr>
