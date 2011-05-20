<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTstorageCanclebom.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
<!-- 	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCanclebom.ALIAS_LABEL_CN%>:
		</td>	
		<td>
	   		<input type="text" value="${model.labelCn}" size="30" name="labelCn"  class=""/>
		</td>
	</tr>-->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCanclebom.ALIAS_STORAGE_CANCLE_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.storageCancleId_related.value}" autocomplete="true" xtype="drm-complex-select" id="storageCancleId_label" columnNameLower="storageCancleId" bmClassId="ZG_T_STORAGE_CANCLE" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.storageCancleId}" id="storageCancleId_value" name="storageCancleId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCanclebom.ALIAS_STORAGE_ID%>:
		</td>	
		<td>
	   		<input type="text" size="30" maxlength="40" value="${model.storageId_related.value}" autocomplete="true" xtype="drm-complex-select" id="storageId_label" columnNameLower="storageId" bmClassId="ZG_T_STORAGE" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.storageId}" id="storageId_value" name="storageId"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCanclebom.ALIAS_MANTR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.orderBomId}" size="30" maxlength="40" name="mantr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCanclebom.ALIAS_NUM%>:
		</td>	
		<td>
	   		<input type="text" value="${model.num}" size="30" maxlength="40" name="num"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTstorageCanclebom.ALIAS_ZBZ%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zbz}" size="30" maxlength="85" name="zbz"  class=""/>
		</td>
	</tr>
