<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorderbomMoveLog.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_SOURCE_ORDER_ID%>：
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.sourceOrderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="sourceOrderId_label" columnNameLower="sourceOrderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR" class=""/>
	   		<input type="hidden" value="${model.sourceOrderId}" id="sourceOrderId_value" name="sourceOrderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_SOURCE_IDNRK%>：
		</td>	
		<td>
	   		<input type="text" value="${model.sourceIdnrk}" size="30" name="sourceIdnrk"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_TARGET_ORDER_ID%>：
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.targetOrderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="targetOrderId_label" columnNameLower="targetOrderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR" class=""/>
	   		<input type="hidden" value="${model.targetOrderId}" id="targetOrderId_value" name="targetOrderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_MOVE_NUM%>：
		</td>	
		<td>
	   		<input type="text" value="${model.moveNum}" size="30" name="moveNum"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_TARGET_IDNRK%>：
		</td>	
		<td>
	   		<input type="text" value="${model.targetIdnrk}" size="30" name="targetIdnrk"  class=""/>
		</td>
	</tr>
