<%@page import="com.boco.frame.sys.base.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="validate_FwOrganization.jsp"%>
<script type="text/javascript">
	function returnPrincipalsName(){
			reAccountId();
			//把负责人(所选择的)的ID拿到
			var principalsId=document.getElementById("principalsId").value;
			//弹出模式窗体，选择负责人，可以多选的，返回负责人的名字跟对应的ID
			var sFeatures="dialogHeight: 420px;dialogWidth:760px";
			var firstShowQueryUrl="${ctx}/frame/sys/FwOrganization/queryForFwOrganizationPrincipals.do?principalsId="+principalsId;
			var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
			if(returnValue){
				document.getElementById("principalsName").value=returnValue.allName;
				document.getElementById("principalsId").value=returnValue.allId;
				var allName=returnValue.allName.split(',');
				var allId=returnValue.allId.split(',');
				if(returnValue.allId!=""){
					for(var v=0;v<allName.length;v++){
						addOptionToSelect("principals",allId[v],allName[v]);
					}
				}
				reAccountId();
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
		var a=document.getElementById("principals");
		var allPrincipalIds="";
		for(var i=0;i<a.options.length;i++){
			if(i!=0)
				allPrincipalIds+=",";
			allPrincipalIds+=(a.options)[i].value;
		}
		document.getElementById("principalsId").value=allPrincipalIds;
	}
	
	function clearOrgId(){
		$("#parentOrgId_value").val("");
		$("#parentOrgId_label").val("");
	}
</script>
<s:hidden id="cuid" name="cuid" />


<tr>
		<td  class="label">
			<span class="alterlabel">*</span><%=FwOrganization.ALIAS_LABEL_CN%>：
		</td>	
		<td>
	<input type="text" value="${model.labelCn}" size="30" maxlength="85"
				name="labelCn" class="required" />		</td>
	</tr>

	<tr>
		<td  class="label">
			<%=FwOrganization.ALIAS_PARENT_ORG_ID%>：
		</th>
		<td>
			<input type="text" size="30" maxlength="40"
				value="${model.parentOrgId_related.value}" autocomplete="true"
				xtype="tree:1" id="parentOrgId_label" columnNameLower="parentOrgId"
				bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class="" />
			<input type="hidden" value="${model.parentOrgId}"
				id="parentOrgId_value" name="parentOrgId" />
				<a href="javascript:clearOrgId()">置空</a>

		</td>

	</tr>
	<tr style="display:none">
	<td  class="label" style="display:none">
			<%=FwOrganization.ALIAS_TYPE%>：
		</td>
		<td style="display:none">
			<select name="type" id="type">
				<option value="1">
					部门
				</option>
				<option value="2">
					仓库
				</option>
			</select>
			<script type="text/javascript">
					var obj = document.getElementById("type");
					for(i = 0;i<obj.options.length;i++){
						if(obj.options[i].value == '${ model.type}'){
							obj.options[i].selected = true;
						}
					}
			</script>
		</td>
</tr>
<tr>
		<td  class="label">
			<span class="alterlabel">*</span><%=FwOrganization.ALIAS_LEVEL_NUM%>：
		</td>
		<td>
			<input type="text" value="${model.levelNum}" size="30"
				readonly="true" name="levelNum" id="levelNum" maxlength="8"
				class="number" />

		</td>
	</tr>
	<tr>

		<td  class="label">
			<span class="alterlabel">*</span><%=FwOrganization.ALIAS_SEQ%>：
		</td>
		<td>
			<input type="text" value="${model.seq}" size="30" name="seq"
				maxlength="85" class="required"   />

		</td>
		</tr>
		<tr>

		<td  class="label">
			负责人：
		</td>
		<td>
		<select id="principals" name="principals" style="width:216px" ondblclick="removeOptionFromSelect('principals')" size="10"  multiple>
		</select>
			&nbsp;<img src="./resources/images/frame/autocomplete.png" style="cursor: pointer" onclick="returnPrincipalsName()"/>
			<input type="hidden" name="principalsName" id="principalsName"/>
			<input type="hidden" value="${model.principalsId}" name="principalsId" id="principalsId">
		</td>
		</tr>
		<tr>
			<td  class="label">
			<%=FwOrganization.ALIAS_NOTE%>：
		</td>
		<td>

			<input type="text" value="${model.note}" size="30" name="note"
				maxlength="85" class="" />

		</td>

	</tr>
