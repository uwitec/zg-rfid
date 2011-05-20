<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorderPlan.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_ORDER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR" class=""/>
	   		<input type="hidden" value="${model.orderId}" id="orderId_value" name="orderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_DEPARTMENU_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.departmenuId_related.value}" autocomplete="true" xtype="tree:1" id="departmenuId_label" columnNameLower="departmenuId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.departmenuId}" id="departmenuId_value" name="departmenuId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_USER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.userId_related.value}" autocomplete="true" xtype="drm-complex-select" id="userId_label" columnNameLower="userId" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.userId}" id="userId_value" name="userId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_PLAN_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="planDateString" name="planDateString" value="${model.planDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_PLAN_START_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="planStartTimeString" name="planStartTimeString" value="${model.planStartTimeString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_PLAN_END_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="planEndTimeString" name="planEndTimeString" value="${model.planEndTimeString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_STATE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.state}" size="30" name="state"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderPlan.ALIAS_PLAN_TYPE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.planType}" size="30" name="planType"  class=""/>
		</td>
	</tr>
