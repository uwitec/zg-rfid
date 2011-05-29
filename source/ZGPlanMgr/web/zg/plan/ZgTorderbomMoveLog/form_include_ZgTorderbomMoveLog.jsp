<%@page import="com.pz.modules.base.fwdep.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorderbomMoveLog.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_SOURCE_PLANBOM_ID%>：
		</td>	
		<td>
	   		<input type="text" value="${model.sourcePlanbomId}" size="30" name="sourcePlanbomId"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_SOURCE_ORDER_TASK_ID%>：
		</td>	
		<td>
	   		<input type="text" value="${model.sourceOrderTaskId}" size="30" name="sourceOrderTaskId"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_TARGET_ORDER_TASK_ID%>：
		</td>	
		<td>
	   		<input type="text" value="${model.targetOrderTaskId}" size="30" name="targetOrderTaskId"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_TARGET_PLANBOM%>：
		</td>	
		<td>
	   		<input type="text" value="${model.targetPlanbom}" size="30" name="targetPlanbom"  class=""/>
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
			<%=ZgTorderbomMoveLog.ALIAS_CREATE_ID%>：
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.createId_related.value}" autocomplete="true" xtype="drm-complex-select" id="createId_label" columnNameLower="createId" bmClassId="FW_EMPLOYEE" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.createId}" id="createId_value" name="createId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_CREATE_DATE%>：
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="createDateString" name="createDateString" value="${model.createDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTorderbomMoveLog.ALIAS_CREATE_USERNAME%>：
		</td>	
		<td>
	   		<input type="text" value="${model.createUsername}" size="30" name="createUsername"  class=""/>
		</td>
	</tr>
