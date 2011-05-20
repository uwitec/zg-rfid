<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTstorage.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" maxlength="40" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_ORDER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderId_related.value}" autocomplete="true" maxlength="40" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR" class=""/>
	   		<input type="hidden" value="${model.orderId}" id="orderId_value" name="orderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_ARBPL%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.arbpl_related.value}" maxlength="40" autocomplete="true" xtype="tree:1" id="arbpl_label" columnNameLower="arbpl" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.arbpl}" id="arbpl_value" name="arbpl"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_LGORT%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.lgort_related.value}" maxlength="40" autocomplete="true" xtype="tree:1" id="lgort_label" columnNameLower="lgort" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.lgort}" id="lgort_value" name="lgort"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_DEPT_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.deptId_related.value}" maxlength="40" autocomplete="true" xtype="tree:1" id="deptId_label" columnNameLower="deptId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.deptId}" id="deptId_value" name="deptId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_CREATOR_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.creatorId_related.value}" maxlength="40" autocomplete="true" xtype="drm-complex-select" id="creatorId_label" columnNameLower="creatorId" bmClassId="FW_EMPLOYEE" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.creatorId}" id="creatorId_value" name="creatorId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_ZBZ%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zbz}" size="30" maxlength="40" name="zbz"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_CREATE_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="createDateString" readonly="true" name="createDateString" value="${model.createDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorage.ALIAS_STATE%>:
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
			<%=ZgTstorage.ALIAS_TYPE%>:
		</td>	
		<td>
	   		<select name="type">
	   			<c:forEach items="${enumMap['STORAGE_TYPE']}" var="obj">
	   				<c:choose>
	   					<c:when test="${obj.value eq model.type}">
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
			<%=ZgTstorage.ALIAS_PRODUCT_TYPE%>:
		</td>	
		<td>
	   		<select name="productType">
	   			<c:forEach items="${enumMap['PRODUCT_TYPE']}" var="obj">
	   				<c:choose>
	   					<c:when test="${obj.value eq model.productType}">
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
