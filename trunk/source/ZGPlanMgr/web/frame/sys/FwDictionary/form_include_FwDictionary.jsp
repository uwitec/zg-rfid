<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwDictionary.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwDictionary.ALIAS_DICT_BUSINESS_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40"　value="${model.dictBusinessId_related.value}" autocomplete="true" xtype="drm-complex-select" id="dictBusinessId_label" columnNameLower="dictBusinessId" bmClassId="FW_DICT_BUSINESS" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.dictBusinessId}" id="dictBusinessId_value" name="dictBusinessId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwDictionary.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" 　maxlength="40"　name="labelCn"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwDictionary.ALIAS_VALUE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.value}" size="30" maxlength="40"　name="value"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwDictionary.ALIAS_LABEL%>:
		</td>	
		<td>
	   		<input type="text" value="${model.label}" size="30" maxlength="40" name="label"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwDictionary.ALIAS_DISPLAY_ORDER%>:
		</td>	
		<td>
	   		<input type="text" value="${model.displayOrder}" size="30" maxlength="40" name="displayOrder"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=FwDictionary.ALIAS_EXTEND%>:
		</td>	
		<td>
	   		<input type="text" value="${model.extend}" size="30" maxlength="40" name="extend"  class=""/>
		</td>
	</tr>
