<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTbackBom.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_AUFNR%>：
		</td>	
		<td>
	   		<input type="text" value="${model.aufnr}" size="30" name="aufnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_MATNR%>：
		</td>	
		<td>
	   		<input type="text" value="${model.matnr}" size="30" name="matnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_MAKTX%>：
		</td>	
		<td>
	   		<input type="text" value="${model.maktx}" size="30" name="maktx"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_MENGE_D%>：
		</td>	
		<td>
	   		<input type="text" value="${model.mengeD}" size="30" name="mengeD"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_LGORT_D%>：
		</td>	
		<td>
	   		<input type="text" value="${model.lgortD}" size="30" name="lgortD"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_BUDAT%>：
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="budatString" name="budatString" value="${model.budatString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="label">
			<%=ZgTbackBom.ALIAS_CPUTM%>：
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="cputmString" name="cputmString" value="${model.cputmString}" class="" size="30"/>
		</td>
	</tr>
