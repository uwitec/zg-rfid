<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_TsysIfaceLog.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_SER_CALLER%>:
		</td>	
		<td>
	   		<input type="text" value="${model.serCaller}" size="30" maxlength="40" name="serCaller"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_SER_SUPPLIER%>:
		</td>	
		<td>
	   		<input type="text" value="${model.serSupplier}" size="30" maxlength="40" name="serSupplier"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_INTERFACE_NAME%>:
		</td>	
		<td>
	   		<input type="text" value="${model.interfaceName}" size="30" maxlength="40" name="interfaceName"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_METHOD_NAME%>:
		</td>	
		<td>
	   		<input type="text" value="${model.methodName}" size="30" maxlength="40" name="methodName"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_DATA_STAUTS%>:
		</td>	
		<td>
	   		<input type="text" value="${model.dataStauts}" size="30" maxlength="40" name="dataStauts"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_PARAMETERS%>:
		</td>	
		<td>
	   		<input type="text" value="${model.parameters}" size="30" maxlength="40" name="parameters"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_RESULT%>:
		</td>	
		<td>
	   		<input type="text" value="${model.result}" size="30" maxlength="40" name="result"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_CALL_TIME%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="callTimeString" name="callTimeString" value="${model.callTimeString}" class="" size="30" readonly ="true"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=TsysIfaceLog.ALIAS_REMARK%>:
		</td>	
		<td>
	   		<input type="text" value="${model.remark}" size="30" maxlength="40" name="remark"  class=""/>
		</td>
	</tr>
