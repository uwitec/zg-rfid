<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwMenu.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>
		<td  class="label">
			<span style="color:red">*</span>菜单名称：
		</td>	
		<td>
	   		<input type="text"  maxlength="20" value="${model.labelCn}" size="30" maxlength = "40" name="labelCn"  class="required"/>
		</td>
	</tr>
	<tr>
		<td  class="label">
		<span style="color:red">*</span>	父菜单：
		</td>	
		<td>
	   		<input type="text" size="30" maxlength = "40" value="${model.parentMenuId_related.value}" autocomplete="true" xtype="tree:5" id="parentMenuId_label" columnNameLower="parentMenuId" bmClassId="FW_MENU" column="m.t0_LABEL_CN" class=""/>
	   		<input type="hidden"  value="${model.parentMenuId}" id="parentMenuId_value" name="parentMenuId"/>
			<input type="hidden" value="${model.seq}" size="30" name="seq"  />
		</td>
	</tr>
	<tr>
		<td  class="label">
			菜单链接：
		</td>	
		<td>
	   		<input type="text" maxlength="85" value="${model.url}" size="30" name="url"  class=""/>
		</td>
	</tr>
	<tr>
		<td  class="label">
		<span style="color:red">*</span>	顺序：
		</td>	
		<td>
	   		<input type="text" maxlength="6" value="${model.displayOrder}" size="30" name="displayOrder"  class="number"/>
		</td>
	</tr>
	<tr>
		<td  class="label">
			<span style="color:red">*</span>级别：
		</td>	
		<td>
	   		<input type="text" maxlength="2" value="${model.levelNum}" size="30" name="levelNum"  class="number"/>
		</td>
	</tr>
	<tr>
		<td  class="label">
			<span style="color:red">*</span>菜单控制类型：
		</td>	
		<td>
	   		<input type="text" maxlength="20" value="${model.type}" size="30" name="type"  class="required"/>
		</td>
	</tr>
