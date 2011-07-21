<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTcarbom.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbom.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=ZgTcarbom.ALIAS_ORDER_PLAN_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderPlanId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderPlanId_label" columnNameLower="orderPlanId" bmClassId="ZG_T_ORDERBOM" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.orderPlanId}" id="orderPlanId_value" name="orderPlanId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=ZgTcarbom.ALIAS_ORDER_BOM_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderBomId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderBomId_label" columnNameLower="orderBomId" bmClassId="ZG_T_ORDERBOM" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.orderBomId}" id="orderBomId_value" name="orderBomId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=ZgTcarbom.ALIAS_ORDER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_LABEL_CN" class="required"/>
	   		<input type="hidden" value="${model.orderId}" id="orderId_value" name="orderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbom.ALIAS_PLAN_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.planNum}" size="30" name="planNum"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbom.ALIAS_REAL_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.realNum}" size="30" name="realNum"  class=""/>
		</td>
	</tr>
