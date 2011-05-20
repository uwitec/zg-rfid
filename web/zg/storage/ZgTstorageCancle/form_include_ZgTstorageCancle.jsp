<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTstorageCancle.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCancle.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" maxlength="40" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCancle.ALIAS_DEPT_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.deptId_related.value}" autocomplete="true" xtype="tree:1" id="deptId_label" columnNameLower="deptId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.deptId}" id="deptId_value" name="deptId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCancle.ALIAS_CREATOR_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.creatorId_related.value}" autocomplete="true" xtype="drm-complex-select" id="creatorId_label" columnNameLower="creatorId" bmClassId="FW_EMPLOYEE" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.creatorId}" id="creatorId_value" name="creatorId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCancle.ALIAS_ZBZ%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zbz}" size="30" maxlength="85" name="zbz"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCancle.ALIAS_STATE%>:
		</td>	
		<td>
	   		<select name="state">
	   			<c:forEach items="${enumMap['STORAGE_STATE']}" var="obj">
	   				<c:choose>
	   					<c:when test="${obj.value eq model.state}">
	   						<option selected="selected" value="${obj.value}">${obj.name}</option>
	   					</c:when>
	   					<c:otherwise>
	   						<option value="${obj.value}">${obj.name}</option>
	   					</c:otherwise>
	   				</c:choose>
	   			</c:forEach>
	   		</select>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCancle.ALIAS_CREATE_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="createDateString" name="createDateString" value="${model.createDateString}" class="" size="30"/>
		</td>
	</tr>
