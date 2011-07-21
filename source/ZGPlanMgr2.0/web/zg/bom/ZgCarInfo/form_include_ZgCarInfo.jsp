<%@page import="com.boco.zg.bom.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgCarInfo.jsp"%>
	<s:hidden id="cuid" name="cuid" value = "%{model.cuid}" />

	<tr>
		<td  class="label">
			<span  style="color:red">*</span><%=ZgCarInfo.ALIAS_CODE%>：
		</td>	
		<td>
	   		<input type="text" maxlength="10" value="${model.code}" size="30" name="model.code" id= "model.code" class="required"/>
		</td>
	</tr>
	<tr>
		<td  class="label">
			<span  style="color:red">*</span><%=ZgCarInfo.ALIAS_LABEL_CN%>：
		</td>	
		<td>
	   		<input type="text" maxlength="85" value="${model.labelCn}" size="30" name="model.labelCn" id= "model.labelCn" class="required"/>
		</td>
	</tr>
	<tr>
		<td  class="label">
			<span  style="color:red"> </span><%=ZgCarInfo.ALIAS_DESCRIPTION%>：
		</td>	
		<td>
	   		<input type="text" maxlength="85" value="${model.description}" size="30" name="model.description" id= "model.description"/>
		</td>
	</tr>

	