<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwRoleMenu.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwRoleMenu.ALIAS_ROLE_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.roleId_related.value}" autocomplete="true" xtype="drm-complex-select" id="roleId_label" columnNameLower="roleId" bmClassId="FW_ROLE" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.roleId}" id="roleId_value" name="roleId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwRoleMenu.ALIAS_MENU_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.menuId_related.value}" autocomplete="true" xtype="url:http://www.163.com" id="menuId_label" columnNameLower="menuId" bmClassId="FW_MENU" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.menuId}" id="menuId_value" name="menuId"/>
		</td>
	</tr>
