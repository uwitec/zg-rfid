<%@page import="com.boco.zg.virtualorg.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTvirtualorg.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTvirtualorg.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTvirtualorg.ALIAS_ORG_ID%>:
		</td>	
		<td>
	   		<input type="text" value="${model.orgId}" size="30" name="orgId"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTvirtualorg.ALIAS_NOTE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.note}" size="30" name="note"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTvirtualorg.ALIAS_TYPE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.type}" size="30" name="type"  class=""/>
		</td>
	</tr>
