<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTcarplan.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_CAR_STATE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.carState}" size="30" name="carState"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_CREATE_USER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.createUserId_related.value}" autocomplete="true" xtype="drm-complex-select" id="createUserId_label" columnNameLower="createUserId" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.createUserId}" id="createUserId_value" name="createUserId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_CREATE_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="createDateString" name="createDateString" value="${model.createDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_CAR_USER%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.carUser_related.value}" autocomplete="true" xtype="drm-complex-select" id="carUser_label" columnNameLower="carUser" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.carUser}" id="carUser_value" name="carUser"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_CAR_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="carDateString" name="carDateString" value="${model.carDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_STORAGE_USER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.storageUserId_related.value}" autocomplete="true" xtype="drm-complex-select" id="storageUserId_label" columnNameLower="storageUserId" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.storageUserId}" id="storageUserId_value" name="storageUserId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_STORAGE_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.storageId_related.value}" autocomplete="true" xtype="tree:1" id="storageId_label" columnNameLower="storageId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.storageId}" id="storageId_value" name="storageId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarplan.ALIAS_REMARK%>:
		</td>	
		<td>
	   		<input type="text" value="${model.remark}" size="30" name="remark"  class=""/>
		</td>
	</tr>
