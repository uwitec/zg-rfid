<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwEmployee.jsp" %>

	<input type="hidden" value="${model.status}" size="30" name="status"  class=""/>
	<input type="hidden" value="${model.relatedBmClassId}" size="30" name="relatedBmClassId"  class=""/>
	<input type="hidden" value="${model.cuid}" size="30" name="cuid"  class=""/>
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>
		<td  class="label">
			<span  style="color:red">*</span>RFID卡号(请刷卡)：
		</td>	
		<td>
			${model.rfidCode }
		</td>
	</tr>

		<td class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_USER_ID%>：
		</td>	
		<td>
			${model.userId}		
		</td>
	</tr>
	

	<tr>
		<td class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_EFFECT_TIME%>：
		</td>	
		<td>
			${model.effectTimeString}
		</td>
	</tr>
	<tr>
		<td class="label">
			<span style="color:red">*</span><%=FwEmployee.ALIAS_ORG_ID%>：
		</td>	
		<td>
			${model.orgId_related.value}
		</td>
	</tr>
		<tr>
		<td  class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_LABEL_CN%>：
		</td>	
		<td>
	   		<input type="text" maxlength="10" value="${model.labelCn}" size="30" name="labelCn"  class="required"/>
		</td>
	</tr>
		<tr>
		<td class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_PASSWORD%>：
		</td>	
		<td>
	   		<input type="text" value="${model.password}" size="30" maxlength="80" name="password"  class="required"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_SEX%>：
		</td>	
		<td>
	   		<select name="sex">
	   			<c:forEach items="${enumMap['SEX']}" var="obj">
	   				<c:choose>
	   					<c:when test="${obj.value eq model.sex}">
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
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_MOBILE%>：
		</td>	
		<td>
	   		<input type="text" value="${model.mobile}" size="30" name="mobile" maxlength="12" class="FwEmployee_mobile"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_TELEPHONE%>：
		</td>	
		<td>
	   		<input type="text" value="${model.telephone}" maxlength="12" size="30" name="telephone"  class=""/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_ADDRESS%>：
		</td>	
		<td>
	   		<input type="text" value="${model.address}" size="30"  maxlength="85" name="address"  class=""/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_EMAIL%>：
		</td>	
		<td>
	   		<input type="text" value="${model.email}" maxlength="85" size="30" name="email"  class="FwEmployee_email"/>
		</td>
	</tr>
		<tr>
		<td  class="label">
			负责部门：
		</td>
		<td>
				<select id="orgIds" name=""orgIds"" style="width:216px" size="4"  multiple>
		
		${ orgNames}
		</tr>
	<tr>
		<td class="label">
			角色：
		</td>	
		<td>
		
			<c:forEach items="${fwOperatorRolelist}" var="role" varStatus="status">
			<c:if test="${status.count%7==0}">
			<br/>
			
			</c:if>
			${role.roleId_related.value },
				
			</c:forEach>
				   <OBJECT id="RFIDReader" classid="clsid:93D9C558-C72F-4348-A071-BC594E06A0AA" style="visibility:hidden;"> </OBJECT>
			
		</td>	
	</tr>
	
