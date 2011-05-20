<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTstorageStat.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageStat.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" maxlength="40" name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageStat.ALIAS_ORDER_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.orderId_related.value}" autocomplete="true" xtype="drm-complex-select" id="orderId_label" columnNameLower="orderId" bmClassId="ZG_T_ORDER" column="m.t0_AUFNR" class=""/>
	   		<input type="hidden" value="${model.orderId}" id="orderId_value" name="orderId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageStat.ALIAS_ARBPL%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.arbpl_related.value}" autocomplete="true" xtype="tree:1" id="arbpl_label" columnNameLower="arbpl" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.arbpl}" id="arbpl_value" name="arbpl"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageStat.ALIAS_MANTR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.mantr}" size="30" maxlength="40" name="mantr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageStat.ALIAS_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.num}" size="30" maxlength="15" name="num"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageStat.ALIAS_LGORT%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.lgort_related.value}" autocomplete="true" xtype="tree:1" id="lgort_label" columnNameLower="lgort" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.lgort}" id="lgort_value" name="lgort"/>
		</td>
	</tr>
