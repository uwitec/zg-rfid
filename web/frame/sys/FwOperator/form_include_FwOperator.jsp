<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwOperator.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" maxlength="85" name="labelCn"  class="required"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_USER_ID%>:
		</td>	
		<td>
	   		<input type="text" value="${model.userId}" size="30" maxlength="40" name="userId"  class="required"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_PASSWORD%>:
		</td>	
		<td>
	   		<input type="text" value="${model.password}" size="30" maxlength="85" name="password"  class="required"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_CREATE_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="createTimeString" name="createTimeString" value="${model.createTimeString}" class="required" size="30" readonly ="true"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_LAST_MODIFY_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="lastModifyTimeString" name="lastModifyTimeString" value="${model.lastModifyTimeString}" class="required" size="30" readonly ="true"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_EFFECT_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="effectTimeString" name="effectTimeString" value="${model.effectTimeString}" class="required" size="30" readonly ="true"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=FwOperator.ALIAS_STATUS%>:
		</td>	
		<td>
	   		<input type="text" value="${model.status}" size="30" maxlength="40" name="status"  class="required"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwOperator.ALIAS_RELATED_BM_CLASS_ID%>:
		</td>	
		<td>
	   		<input type="text" value="${model.relatedBmClassId}" size="30" maxlength="40" name="relatedBmClassId"  class=""/>
		</td>
	</tr>
