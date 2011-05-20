<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTcarbomSuppliers.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbomSuppliers.ALIAS_CAR_BOM_ID%>:
		</td>	
		<td>
	   		<input type="text" value="${model.carBomId}" size="30" name="carBomId"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbomSuppliers.ALIAS_LIFNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.lifnr}" size="30" name="lifnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbomSuppliers.ALIAS_LIFNR_NAME%>:
		</td>	
		<td>
	   		<input type="text" value="${model.lifnrName}" size="30" name="lifnrName"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbomSuppliers.ALIAS_CAR_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.carNum}" size="30" name="carNum"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTcarbomSuppliers.ALIAS_SUPPLIERS_ID%>:
		</td>	
		<td>
	   		<input type="text" value="${model.suppliersId}" size="30" name="suppliersId"  class=""/>
		</td>
	</tr>
