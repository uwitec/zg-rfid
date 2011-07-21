<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTback.jsp" %>
	<s:hidden id="cuid" name="cuid" />
	<input type="hidden" value="${model.createTime}" size="30" name="createTime"  class=""/>
	<input type="hidden" value="${model.updateTime}" size="30" name="updateTime"  class=""/>
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTback.ALIAS_BATCH_NO%>:
		</td>	
		<td>
	   		<input type="text" value="${model.batchNo}" size="30" maxlength="40" name="batchNo"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTback.ALIAS_BATCH_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="batchDateString" maxlength="40" name="batchDateString" value="${model.batchDateString}" class="" size="30" readonly="true"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTback.ALIAS_AUFNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.aufnr}" size="30" maxlength="40" name="aufnr"  class=""/>
		</td>
	</tr>
