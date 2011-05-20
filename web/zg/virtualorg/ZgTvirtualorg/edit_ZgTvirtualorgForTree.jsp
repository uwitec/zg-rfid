<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.boco.zg.virtualorg.base.model.ZgTvirtualorg"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>

	<head>
		<base href="<%=basePath%>" />
		<base target="_self"/><!-- 提交给自己 -->
		<title>领料组编辑</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script src="${ctx}/dwr/interface/ZgTvirtualorgDwrAction.js" type=""></script>
		<script type="text/javascript">
		$(function() {
			initAutoCompleteTo("${ctx}/autoComplate/findRelationData.do");
		});
		
		function initAutoCompleteTo(url) {
			var a = $("input[autocompleteTo]");
			for(var i = 0 ; i < a.length; i++) {
				var o = a[i];
				var xtype = $(o).attr("xtype");
				var columnNameLower = $(o).attr("columnNameLower");
				var bmClassId = $(o).attr("bmClassId");
				var column = $(o).attr("column");
				$("#"+columnNameLower+"_label").css("cursor","pointer");
				var img = "<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
				$("#"+columnNameLower+"_value").after(img);
				if(xtype.indexOf("tree:") != -1){
					var templateId = xtype.substring(xtype.indexOf(":")+1);
					$("#"+columnNameLower+"_label").attr("readonly",true);
					var sFeatures="dialogHeight: 400px;dialogWidth:300px";
					$(o).bind("click",function() {
						var returnValue = window.showModalDialog(ctx+"/zg/virtualorg/ZgTvirtualorg/zgTvirtualorg_tree.jsp",'',sFeatures);
						if(returnValue) {
							var id = returnValue.id.substring(0);//截取了第一个逗号
							var label = returnValue.label.substring(0);//截取了第一个逗号
							$("#"+$(this).attr("columnNameLower")+"_label").val(label);
							$("#"+$(this).attr("columnNameLower")+"_value").val(id);
						}
					});
				}
			}
		}
		
		function saveVirtualorg(){
			if($("form[validator]").valid()){
				var orgId=document.getElementById('orgId_value').value;
				var cuid=document.getElementById('cuid').value;
				var labelCn=document.getElementById('labelCnID').value;
				if(chooseSelf(orgId,cuid)){
					DWREngine.setAsync(false);
					ZgTvirtualorgDwrAction.validLabelCnForUpdate(cuid,orgId,labelCn,function(sameName){
						if(sameName){
							document.forms[0].submit();
							alert('修改成功!');
							var obj=new Object();
							obj.labelCn=labelCn;
							obj.orgId=orgId;
							obj.type=document.getElementById('typeId').value;
							window.returnValue=obj;
							window.close();
						}else{
							document.getElementById('labelCnIdValid').style.display="";
						}
					});
				}else{
					document.getElementById('selfValid').style.display="";
				}
			}
		}
		//选择自己作为父节点验证
		function selfValidSapnHidden(){
			document.getElementById('selfValid').style.display="none";
		}
		function chooseSelf(orgId,cuid){
			if(orgId==cuid){
				return false;
			}else{
				return true;
			}
		}
		function labelCnIdValidHidden(){
			document.getElementById('labelCnIdValid').style.display="none";
		}
		function backTree(){
			window.returnValue=null;
			window.close();
		}
	</script>
	</head>

	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form id="tt" action="${ctx}/zg/virtualorg/ZgTvirtualorg/updateVirtualorg.do" method="post"
			validator="true">
			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1" style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a href="javascript:saveVirtualorg()"><span><img
												src="<%=iconPath%>/true.gif" />提交</span> </a>&nbsp
									<a href="javascript:backTree()"><span><img
												src="<%=iconPath%>/ico_007.gif" />关闭</span> </a>
								</div>
							</td>
						</tr>
					</thead>
				</table>
				</div>
				<div class="form_panel">
					<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top: 3px;">
						<tbody attr="tbody_1">
						<tr>
							<td  class="label" >
								<span style="color:red">*</span>领料组名称：
							</td>	
							<td>
						   		<input type="text" id="labelCnID" maxlength="20"  size="30" maxlength = "20" name="labelCn" value="${labelCn }"onfocus="labelCnIdValidHidden()" class="required"/>
						   		<input type="hidden" name="cuid" id="cuid" value="${cuid}"/>
						   		<span id="labelCnIdValid" style="display:none">该机构下有相同名称的领料组!</span>
							</td>
						</tr>
						<tr>
							<td  class="label">
								所属机构：
							</td>	
							<td>
						    	<input type="text" size="30"  value="${orgName}" autocompleteTo="true" xtype="tree:1" id="orgId_label" name="orgId_label" columnNameLower="orgId" bmClassId="ZG_T_VIRTUALORG" column="m.t0_LABEL_CN" class="required"onfocus="selfValidSapnHidden()" />
						   		<span id="selfValid" style="display:none">不能选择自己作为上层机构!</span>
						   		<span id="virtualValid" style="display:none">不能选择领料组作为上层机构!</span>
						   		<input type="hidden" name="orgId"id="orgId_value" value="${orgId }"/> 
							</td>
						</tr>
						<tr>
							<td  class="label">
							描述：
							</td>	
							<td>
						   		<input type="text" maxlength="20" size="30"id="noteId" name="note" value="${note }" class=""/>
							</td>
						</tr>
						<tr>
							<td  class="label">
								领料组类型：
							</td>	
							<td>
								<select name="type" id="typeId" >
									<option value="ABC" <c:if test="${type=='ABC'}">selected</c:if>>预焊</option>
									<option value="ABD" <c:if test="${type== 'ABD'}">selected</c:if>>预装</option>
									<option value="ABE" <c:if test="${type=='ABE' }">selected</c:if>>总装</option>
								</select>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</form>
	</body>
</html>