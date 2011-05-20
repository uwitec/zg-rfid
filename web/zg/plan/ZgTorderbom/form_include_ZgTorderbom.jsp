<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorderbom.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_IDNRK%>:
		</td>	
		<td>
	   		<input type="text" value="${model.idnrk}" size="30" name="idnrk"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_ORDER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.orderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR" class=""/>
	   		<input type="hidden" value="${model.orderId}" id="orderId_value" name="orderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_AUFNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.aufnr}" size="30" name="aufnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_ARBPL%>:
		</td>	
		<td>
	   		<input type="text" value="${model.arbpl}" size="30" name="arbpl"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MATNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.matnr}" size="30" name="matnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MAKTX1%>:
		</td>	
		<td>
	   		<input type="text" value="${model.maktx1}" size="30" name="maktx1"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MAKTX2%>:
		</td>	
		<td>
	   		<input type="text" value="${model.maktx2}" size="30" name="maktx2"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MSEHL1%>:
		</td>	
		<td>
	   		<input type="text" value="${model.msehl1}" size="30" name="msehl1"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MSEHL2%>:
		</td>	
		<td>
	   		<input type="text" value="${model.msehl2}" size="30" name="msehl2"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_ZDTYL%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zdtyl}" size="30" name="zdtyl"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MENGE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.menge}" size="30" name="menge"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_MATKL%>:
		</td>	
		<td>
	   		<input type="text" value="${model.matkl}" size="30" name="matkl"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_SORTF%>:
		</td>	
		<td>
	   		<input type="text" value="${model.sortf}" size="30" name="sortf"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_LGORT%>:
		</td>	
		<td>
	   		<input type="text" value="${model.lgort}" size="30" name="lgort"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_ZBZ%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zbz}" size="30" name="zbz"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorderbom.ALIAS_ZRZQD%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zrzqd}" size="30" name="zrzqd"  class=""/>
		</td>
	</tr>
