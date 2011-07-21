<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<script src="${ctx}/dwr/interface/CheckMaterielNameDwrAction.js" type=""></script>
<%@ include file="validate_ZgMateriel.jsp" %>
<script type="text/javascript">
	function returnMaterielName(){
			//弹出模式窗体，选择仓库或者物料组
			var sFeatures="dialogHeight: 500px;dialogWidth:150px";
			var firstShowQueryUrl="${ctx}/zg/virtualorg/ZgTvirtualorg/materielForVirtualorg.jsp";
			var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
			document.getElementById("storageName").value=returnValue.orgName;
			document.getElementById("storageId").value=returnValue.orgId;
			document.getElementById("materielStorageName").value=returnValue.realStorageName;
			document.getElementById("materielStorageId").value=returnValue.parentId;
	}
	
	function checkTheNameUseDwr(obj){
		DWREngine.setAsync(false);//dwr同步
		CheckMaterielNameDwrAction.checkMaterielName(obj.value,function(data){
			if(data=="0")
				document.getElementById("materielNameDiv").innerText="此名字可用!";
			else
				document.getElementById("materielNameDiv").innerText="此名字已存在了!";
		});
	}
	
	function returnTheAllVirtualorgNames(){
		var sFeatures="dialogHeight: 500px;dialogWidth:700px";
		var firstShowQueryUrl="${ctx}/zg/virtualorg/ZgTvirtualorg/createFindByRequestForVirtualorg.do";
		var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
		if(returnValue){
			document.getElementById("virtualorgName").value=returnValue.allName;
			document.getElementById("orgId").value=returnValue.allId;
		}
	}
</script>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>
		<td class="label">
			物料组ID:
		</td>	
		<td>
			<input type="hidden" value="${materielVirtualorgId}" name="materielVirtualorgId" id="materielVirtualorgId"/>
			<input type="hidden" value="${zgMaterrielVirtualorg.matkl}" name="materielCuid" id="materielCuid"/>
	   		<input type="text" readonly="true" value="${zgMaterrielVirtualorg.materielId}" size="30" name="materielId" id="materielId"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<span style="color:red">*</span>上层物料组:
		</td>	
		<td>
			<input type="text" value="${zgMaterrielVirtualorg.storageName}" size="30" name="storageName" id="storageName" style="cursor: pointer" readOnly="readOnly" onclick="returnMaterielName()"/>
			<img src="./resources/images/frame/autocomplete.png"/>
	   		<input type="hidden" value="${zgMaterrielVirtualorg.storageId}" name="storageId" id="storageId"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<span style="color:red">*</span>物料组名称:
		</td>	
		<td>
	   		<input type="text" value="${zgMaterrielVirtualorg.materielName}" size="30" name="materielName" id="materielName" onChange="checkTheNameUseDwr(this)"/><div id="materielNameDiv" style="color:red"></div>
		</td>
	</tr>
	<tr>
		<td class="label">
			<span style="color:red">*</span>领料组:
		</td>	
		<td>
			<textarea rows="2" cols="24" name="virtualorgName" id="virtualorgName" onclick="returnTheAllVirtualorgNames()">${zgMaterrielVirtualorg.virtualorgName}</textarea>
			&nbsp;<img src="./resources/images/frame/autocomplete.png"/>
	   		<input type="hidden" value="${zgMaterrielVirtualorg.orgId}" name="orgId" id="orgId"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<span style="color:red">*</span>仓库:
		</td>	
		<td>
	   		<input type="text" value="${zgMaterrielVirtualorg.materielStorageName}" size="30" name="materielStorageName" id="materielStorageName" readOnly="readOnly"/>
	   		<input type="hidden" value="${zgMaterrielVirtualorg.materielStorageId}" name="materielStorageId" id="materielStorageId"/>
		</td>
	</tr>
