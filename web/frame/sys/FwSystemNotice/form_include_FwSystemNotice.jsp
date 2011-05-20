<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwSystemNotice.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwSystemNotice.ALIAS_TITLE%>：
		</td>	
		<td>
	   		<input type="text" value="${model.title}" size="104" name="title"  class="required" maxLength="10"/>
	   		<input type="hidden" value="${model.createId}" name="createId">
			<input type="hidden" value="${model.state}" name="state">
	   		<input type="hidden" value="<fmt:formatDate value="${model.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" />" name="createDate">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=FwSystemNotice.ALIAS_CONTENT%>：
		</td>	
		<td>
			<textarea rows="6"  name="content"  cols="80" class="required" onpropertychange="checkLength(this,500);" >${model.content}</textarea>
		</td>
	</tr>
	
