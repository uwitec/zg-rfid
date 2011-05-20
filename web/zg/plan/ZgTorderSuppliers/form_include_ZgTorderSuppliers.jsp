<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorderSuppliers.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderSuppliers.ALIAS_AUFNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.aufnr}" size="30" name="aufnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderSuppliers.ALIAS_IDNRK%>:
		</td>	
		<td>
	   		<input type="text" value="${model.idnrk}" size="30" name="idnrk"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderSuppliers.ALIAS_LIFNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.lifnr}" size="30" name="lifnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderSuppliers.ALIAS_LIFNR_NAME%>:
		</td>	
		<td>
	   		<input type="text" value="${model.lifnrName}" size="30" name="lifnrName"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderSuppliers.ALIAS_PLANT%>:
		</td>	
		<td>
	   		<input type="text" value="${model.plant}" size="30" name="plant"  class=""/>
		</td>
	</tr>
