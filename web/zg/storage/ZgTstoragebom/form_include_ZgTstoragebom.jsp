<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTstoragebom.jsp" %>
	
<script type="text/javascript">
		$(function() {
				var img = "&nbsp;<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
				$("#matnr").after(img);
				$("#idnrk").after(img);
		});
			
			//选择生产线及订单半成品
		function selectBom(){
			var lgort = '${lgort}';
			var type  = '${type}';
			var sFeatures="dialogHeight: 500px;dialogWidth:760px";
			var url='';
			if(type=='1'){
				url='${ctx}/zg/storage/ZgTstoragebom/findBomlListByArbplOrderId.do?storageId=${storageId}&arbpl=${arbpl}&orderId=${orderId}&type=${type}&productType=${productType}';
			}else if(type=='2'){
				//alert(type);
				url='${ctx}/zg/storage/ZgTstoragebom/findBomlListByArbplOrderIdStorageId.do?storageId=${storageId}&arbpl=${arbpl}&orderId=${orderId}&type=${type}&productType=${productType}&lgort='+lgort;
			}
			//alert(url);
			var returnValue = window.showModalDialog(url,'',sFeatures);
			if(returnValue) {
          		var orderAufnr=returnValue.orderAufnr;
          		var arbpl=returnValue.arbpl;
          		var matnr=returnValue.matnr;
          		var idnrk=returnValue.idnrk;
          		var msehl1=returnValue.msehl1;
          		var menge= returnValue.menge;
          		var orderBomId=returnValue.orderBomId;
				$("#orderAufnr").val(orderAufnr);
				$("#arbpl").val(arbpl);
				$("#matnr").val(matnr);
				$("#idnrk").val(idnrk);
				$("#msehl1").val(msehl1);
				$("#menge").val(menge);
				$("#orderBomId").val(orderBomId);
				$("#num").val("");
			}
		}
		
		function copyText(text,ObjectName){
	
			$("#"+ObjectName).val(text);
		}
		
		function checkPlanNum(obj) {
			var max = $("#menge").val()*1;
			var value = obj.value;
			
			
			if(isNumber(value)) {
				if(max < value*1) {
					alert("入库数量量必须小于需求数量！");
					obj.value = "";
				}
			}else {
				alert("计划领取数量必须为数字！");
				obj.value = "";
			}
		}

</script>
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
<input type="hidden" id="cuid" name="cuid" value="${bom.cuid }"/>
<input type="hidden" id="zgTstorageId" name="zgTstorageId" value="${bom.zgTstorageId}"/>
<input type="hidden" id="orderBomId" name="orderBomId" value="${bom.orderBomId}"/>

	<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		订单号*:
	</td>
	<td>
			<input type="text"  maxlength="85"  size="50" readonly="true"  value="${bom.orderAufnr }" id="orderAufnr" name="orderAufnr" />
							
	</td>
</tr>


<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		生产线*:
	</td>
	<td>
		   	<input type="text" size="50" id="arbpl" name="arbpl" maxlength="40" value="${bom.arbpl }"/>
							

	</td>
<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		半成品物料号*:
	</td>
	<td>
		<input type="text"  size="50" maxlength="85" onclick="selectBom()"  value="${bom.matnr }"  readonly="true"  id="matnr" name="matnr" />

	</td>
</tr>
<tr>
	<td class="tdLabel">
		半成品名称*:
	</td>
	<td>
	
		<input type="text" onclick="selectBom()"  id="idnrk" name="idnrk" maxlength="60"
			class="required" size="50" readonly="true" value="${bom.idnrk }"  />
	</td>
</tr>

<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		半成品单位*:
	</td>
	<td>
		<input type="text" id="msehl1" name="msehl1" maxlength="10"
			 size="50"  value="${bom.msehl1 }"  />
	</td>
</tr>
<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		<c:if test="${type=='1'  }">需求总数</c:if>
		<c:if test="${type=='2'  }">可出库数量</c:if>
		*:
	</td>
	<td>
		<input type="text" readonly="true" id="menge" name="menge" maxlength="10"
			 size="50" value="${bom.menge }" />
	</td>
</tr>
<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		<c:if test="${type=='1'  }">入库数量</c:if>
		<c:if test="${type=='2'  }">实际出库数量</c:if>
		*:
	</td>
	<td>
		<input type="text" id="num" onchange="checkPlanNum(this)"  name="num" maxlength="10"
			class="number" size="50"  value="${bom.num }"  />
	</td>
</tr>
<tr bgcolor="#FFFFFF">
	<td class="tdLabel">
		备注:
		
	</td>
	<td column="3">
		<textarea name="textarea " cols="39 " rows="5" on name="ta_zbz"
			onblur="copyText(this.value,'zbz')"
			onpropertychange="checkLength(this,60);" id="ta_zbz">${bom.zbz}</textarea>

		<input type="hidden" id="zbz" name="zbz" value="${bom.zbz }"
			class="" size="50" />
	</td>
</tr>
