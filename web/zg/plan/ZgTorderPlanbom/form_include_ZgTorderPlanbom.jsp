<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorderPlanbom.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_ORDER_BOM_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderBomId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderBomId_label" columnNameLower="orderBomId" bmClassId="ZG_T_ORDERBOM" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.orderBomId}" id="orderBomId_value" name="orderBomId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_ORDER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.orderId}" id="orderId_value" name="orderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_DEPARTMENT_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.departmentId_related.value}" autocomplete="true" xtype="tree:1" id="departmentId_label" columnNameLower="departmentId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.departmentId}" id="departmentId_value" name="departmentId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_USER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.userId_related.value}" autocomplete="true" xtype="drm-complex-select" id="userId_label" columnNameLower="userId" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.userId}" id="userId_value" name="userId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_PLAN_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="planDateString" name="planDateString" value="${model.planDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_PLAN_START_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="planStartTimeString" name="planStartTimeString" value="${model.planStartTimeString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_PLAN_END_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="planEndTimeString" name="planEndTimeString" value="${model.planEndTimeString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_STATE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.state}" size="30" name="state"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_COMPLETE_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.completeNum}" size="30" name="completeNum"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlanbom.ALIAS_CAR_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.carNum}" size="30" name="carNum"  class=""/>
		</td>
	</tr>
