<%@page import="com.boco.zg.plan.base.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="validate_ZgTorderPlanbom.jsp"%>

<script type="text/javascript">
<!--
	function copyText(text,ObjectName){
	
		$("#"+ObjectName).val(text);
	}
	
	function selectBom(){
		if($("#lgort_value").val()==''){
			alert('请先选择仓库!');
			return;
		}
		var sFeatures="dialogHeight: 400px;dialogWidth:720px";
		var url="zg/bom/ZgTbom/queryBomForSelect.do?lgort="+$("#lgort_value").val()+"&orderPlanId=${orderPlanId}";
		if(url.indexOf("http://") == -1) url = ctx+"/"+url;
		var returnValue = window.showModalDialog(url,'',sFeatures);
		if(returnValue) {
			$("#bomId").val(returnValue.bomId);
			$("#maktx1").val(returnValue.maktx);
			$("#msehl1").val(returnValue.msehl);
			$("#bomId_labelCn").val(returnValue.bomName);
		}
		var carNumObj=document.getElementById("carNum");
		carNumObj.focus(); 
		
	}
	

//-->
</script>

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<input type="hidden" id="bomId" name="bomId" value="${modelEx.bomId}"
			readonly="true"  size="50" class="required" />
		<input type="hidden" value="${orderPlanId}" size="30"  id="orderPlanId" name="orderPlanId" class="" />
		<input type="hidden" value="${modelEx.cuid}" size="30"  id="cuid" name="cuid" class="" />

<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		仓库名称(点击选择):
	</td>
	<td>
		<input type="text" size="50" value="${modelEx.lgort_lableCn}"
			autocomplete="true" xtype="tree:1" id="lgort_lableCn" name="lgort_lableCn"
			columnNameLower="lgort" bmClassId="FW_ORGANIZATION"
			column="m.t0_LABEL_CN" class="required" onChange="alert('aaaa');" />
		<input type="hidden" value="${modelEx.lgort}" id="lgort_value"
			name="lgort" />
	</td>
</tr>


<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		BOM组件(点击选择):
	</td>
	<td>
		<input type="text" id="bomId_labelCn" name="bomId_labelCn"
			readonly="true" value="${modelEx.bomId_labelCn}"
			onclick="selectBom()"  size="50" class="required" />

	</td>
<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		物料描述:
	</td>
	<td>
		<input type="text" id="maktx1" name="maktx1" value="${modelEx.maktx1}"
			readonly="true" class="required" size="50"  />

	</td>
</tr>
<tr>
	<td class="tdLabel">
		度量单位:
	</td>
	<td>
		<input type="text" id="msehl1" name="msehl1" value="${modelEx.msehl1}"
			class="required" size="50" readonly="true" />

	</td>
</tr>

<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		计划领取数量*:
	</td>
	<td>
		<input type="text" id="carNum" name="carNum" maxlength="10"
			value="${modelEx.carNum}" class="number" size="50" />
	</td>
</tr>
<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		备注:
		
	</td>
	<td column="3">
		<textarea name="textarea " cols="49 " rows="5" on name="ta_zbz"
			onblur="copyText(this.value,'zbz')"
			onpropertychange="checkLength(this,60);" id="ta_zbz">${modelEx.zbz}</textarea>

		<input type="hidden" id="zbz" name="zbz" value="${modelEx.zbz}"
			class="" size="50" />
	</td>
</tr>

