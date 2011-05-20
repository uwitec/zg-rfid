<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_FwEmployee.jsp" %>

<script type="text/javascript">
 function scroll() {
             var reader = document.getElementById('RFIDReader');
             var kCode=document.getElementById("rfidCode");
             if (reader.readyState == 4) {
                 var rl = reader.Code;
                 if (rl == '-1') {//设备未正常连接
                 }else if(rl==''||rl==undefined){//没有卡的时候
                 }else {//正常读取卡号信息
                 	$("#rfidCode").val(rl);
                 }
              }
}

//select 删除option
	function removeOptionFromSelect(selName){
		var slt=document.getElementById(selName);
		slt.remove(slt.selectedIndex);
		
		reAccountId();
	}
	
	//给隐藏表单域的ID重新赋值
	function reAccountId(){
		var a=document.getElementById("orgIds");
		var orgIds="";
		for(var i=0;i<a.options.length;i++){
			if(i!=0)
				orgIds+=",";
			orgIds+=(a.options)[i].value;
		}
		document.getElementById("manageOrgIds").value=orgIds;
	}
	
	function selectManageOrg(){
		var orgIds=$("#manageOrgIds").val();
		var sFeatures="dialogHeight: 400px;dialogWidth:300px";
		var returnValue = window.showModalDialog(ctx+"/frame/sys/FwEmployee/edit_FwOrganazationByTree.jsp?templateId=7&orgIds="+orgIds,'',sFeatures);
		if(returnValue) {
				var id = returnValue.id.substring(1);
				var label = returnValue.label.substring(1);
				
				$("#manageOrgIds").val(id);
				
				var selectObj=document.getElementById("orgIds");
				while (selectObj.options.length>0)
　				　{
　　					selectObj.options.remove(0);
　				　}
			var allName=label.split(',');
			var allId=id.split(',');
			if(id!=""){
				for(var v=0;v<allId.length;v++){
					addOptionToSelect("orgIds",allId[v],allName[v]);
				}
			}
				
		}
	}
	
	
	
</script>
	<s:hidden id="cuid" name="cuid" />
	<input type="hidden" value="${model.status}" size="30" name="status"  class=""/>
	<input type="hidden" value="${model.relatedBmClassId}" size="30" name="relatedBmClassId"  class=""/>
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>
		<td  class="label">
			<span  style="color:red">*</span>RFID卡号(请刷卡)：
		</td>	
		<td>
	   		<input type="text" maxlength="32" value="${model.rfidCode }" size="30" name="rfidCode"  id="rfidCode"/>
		</td>
	</tr>
	<tr>
		<td  class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_LABEL_CN%>：
		</td>	
		<td>
	   		<input type="text" maxlength="10" value="${model.labelCn}" size="30" name="labelCn"  class="required" readOnly="true"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_USER_ID%>：
		</td>	
		<td>
	   		<input type="text" value="${model.userId}" size="30" maxlength="20" name="userId" id="userId" class="required" readOnly="true"/>
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<span  style="color:red">*</span><%=FwEmployee.ALIAS_EFFECT_TIME%>：
		</td>	
		<td>
			   		<input type="hidden" value="${model.password}" size="30" maxlength="9" name="password"  class="required" readOnly="true"/>
		
	   		<input type="text" readOnly="true" id="effectTimeString" name="effectTimeString" value="${model.effectTimeString}" class="required" size="30" readonly = "true"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<span style="color:red">*</span><%=FwEmployee.ALIAS_ORG_ID%>：
		</td>	
		<td>
			<input type="text" size="30" value="${model.orgId_related.value}" maxlength="40" readOnly="true"  class="required"/>
	   		<input type="hidden" value="${model.orgId}" id="orgId_value" name="orgId"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_SEX%>：
		</td>	
		<td>
	   		<select name="sex">
	   			<c:forEach items="${enumMap['SEX']}" var="obj">
	   				<c:choose>
	   					<c:when test="${obj.value eq model.sex}">
	   						<option selected="selected" value="${obj.value}">${obj.name}</option>
	   					</c:when>
	   				</c:choose>
	   			</c:forEach>
	   		</select>
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_MOBILE%>：
		</td>	
		<td>
	   		<input type="text" value="${model.mobile}" size="30" name="mobile" maxlength="12" class="FwEmployee_mobile" readOnly="true" />
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_TELEPHONE%>：
		</td>	
		<td>
	   		<input type="text" value="${model.telephone}" maxlength="12" size="30" name="telephone"  class="" readOnly="true" />
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_ADDRESS%>：
		</td>	
		<td>
	   		<input type="text" value="${model.address}" size="30"  maxlength="85" name="address"  class="" readOnly="true" />
		</td>
	</tr>
	<tr>
		<td class="label">
			<%=FwEmployee.ALIAS_EMAIL%>：
		</td>	
		<td>
	   		<input type="text" value="${model.email}" maxlength="85" size="30" name="email"  class="FwEmployee_email" readOnly="true" />
		</td>
	</tr>
		<tr>
		<td  class="label">
			负责部门：
		</td>
		<td>
		<select id="orgIds" name=""orgIds"" style="width:216px"  size="4"  multiple>
		</select>
			<input type="hidden" value="${orgIds}" name="manageOrgIds" id="manageOrgIds">
		</td>
		</tr>
	<tr>
		<td class="label">
			角色：
		</td>	
		<td>
			<c:forEach items="${fwRoleList}" var="role" varStatus="status">
			<c:if test="${status.count%7==0}">
			<br/>
			
			</c:if>
					<c:choose>
							<c:when test="${fwOperatorRoleMap[role.cuid] eq role.cuid}">
								<input type="checkBox" disabled="true"  checked="true" name="roles" value="${role.cuid }"/>${role.labelCn}
							</c:when>
							<c:otherwise>
								<input type="checkBox" name="roles" disabled="true" readOnly="true"  value="${role.cuid }"/>${role.labelCn}
							</c:otherwise>
					</c:choose>
	
				
			</c:forEach>
				   <OBJECT id="RFIDReader" classid="clsid:93D9C558-C72F-4348-A071-BC594E06A0AA" style="visibility:hidden;"> </OBJECT>
			
		</td>	
	</tr>
	
