<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.boco.frame.sys.base.model.ZgMateriel"%>
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
		<title>物料组编辑</title>
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
		<script src="${ctx}/dwr/interface/ZgMaterielDwrAction.js" type=""></script>
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
						var returnValue = window.showModalDialog(ctx+"/zg/materiel/ZgMateriel/materiel_tree.jsp",'',sFeatures);
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
		
		function saveMateriel(){
			if($("form[validator]").valid()){
				var cuid=document.getElementById('cuid').value;
				var materielName=document.getElementById("materielName").value;
				var lgort=document.getElementById('lgort').value;
				var id=document.getElementById('id').value;
				var parentId=document.getElementById('parentName_value').value;
				var advance=document.getElementById('advance').value;
				if(chooseSelf(parentId,cuid)){
					DWREngine.setAsync(false);
					ZgMaterielDwrAction.validNameForUpdate(cuid,parentId,materielName,function(sameNameFlag){
						if(sameNameFlag){
						//	ZgMaterielDwrAction.validIdForUpdate(cuid,parentId,id,function(sameIdFlag){
							//	if(sameIdFlag){
									DWREngine.setAsync(false);
									var lgortObj=document.getElementsByName("lgort");
									var lgort="";
									for(var i=0;i<lgortObj.length;i++){
										if(lgortObj[i].checked==true){
											lgort=lgort+lgortObj[i].value+",";
											
										}
									}
									if(lgort.length==0){
										if('${level}'!='1'){
											alert("请选择仓库!");
											return;
										}
										
									}
									ZgMaterielDwrAction.updateMateriel(cuid,materielName,id,parentId,lgort,advance,'${level}');
									alert('修改成功!');
									
									var obj=new Object();
									obj.parentId=parentId;
									obj.materielName=materielName;
									window.returnValue=obj;
									window.close();
								//}else{
								//	document.getElementById('idValid').style.display="";
							//	}
							//});
						}else{
							document.getElementById('materielNameValid').style.display="";
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
		function chooseSelf(parentId,cuid){
			if(parentId==cuid){
				return false;
			}else{
				return true;
			}
		}
		function nameValidSpanHidden(){
			document.getElementById('materielNameValid').style.display="none";
		}	
		function idValidSpanHidden(){
			document.getElementById('idValid').style.display="none";
		}
		function backTree(){
			window.returnValue=null;
			window.close();
		}
	</script>
	</head>

	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form  action="${ctx}/zg/materiel/ZgMateriel/updateMateriel.do" method="post"
			validator="true">
			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1" style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a href="javascript:saveMateriel()"><span><img
												src="<%=iconPath%>/true.gif" />保存</span> </a>&nbsp
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
							<td class="label">
								<span style="color:red">*</span>物料组ID：
							</td>
							<td>
								<input type="text" readonly="true" name="id" id="id" value="${id }" maxlength="20" size="30"class="required" onfocus="idValidSpanHidden()" />
								<span id="idValid" style="display:none">该机构下有同名物料组ID!</span>
							</td>
						</tr>
						<tr>
							<td  class="label">
								<span style="color:red">*</span>物料组名称：
							</td>	
							<td>
								<c:choose>
										<c:when test="${level=='1'}">
											<input type="text" readOnly="true" id="materielName"  size="30" maxlength = "20" name="materielName" value="${materielName}" onfocus="nameValidSpanHidden()"class="required"/>
											
										</c:when>
										<c:otherwise>
											<input type="text" id="materielName"  size="30" maxlength = "20" name="materielName" value="${materielName}" onfocus="nameValidSpanHidden()"class="required"/>
										
										</c:otherwise>
								</c:choose>
						   		<input type="hidden" name="cuid" value="${cuid }"id="cuid"/>
						   		<span id="materielNameValid" style="display:none ">该机构下有同名物料组!</span>
							</td>
						</tr>
						<tr>
							<td class="label">是否提前领料：</td>
							<td>
								<select name="advance" id="advance">
								<option value="0" <c:if test="${advance=='0' }">selected</c:if>>否</option>
								<option value="1" <c:if test="${advance=='1' }">selected</c:if>>是</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td class="label">上层机构：</td>
							<td>
							${parentName}
								<input type="hidden" size="30"  value="${parentName}" autocompleteTo="true" xtype="tree:1" id="parentName_label" name="parentName_label" columnNameLower="parentName" bmClassId="ZG_MATERIEL" column="m.t0_LABEL_CN" class="required" onfocus="selfValidSapnHidden()" />
						   		<span id="selfValid" style="display:none">不能选择自己作为上层机构!</span>
						   		<span id="sameMateriel" style="display:none">此机构下有同名的物料组!</span>
						   		<input type="hidden" name="parentName_value" id="parentName_value" value="${parentId }"/> 
						<!--    <label>${parentName }</label>
								<input type="hidden" name="parentId" value="${parentId }" id="parentId"/>  -->	
							</td>
						</tr>
						
						<td  class="label">
								所属仓库：
							</td>	
							<td>
								<c:forEach items="${parentLgortList}" var="obj" varStatus="status">
									<c:if test="${status.count%7==0}">
									<br/>
									
									</c:if>	
										<c:choose>
										<c:when test="${level=='1'}">
											 ${obj.LGORT}
														<input readOnly="true" type="hidden" checked="true" name="lgort" value="${obj.LGORT}"/>
										</c:when>
										<c:otherwise>
										 <c:choose>
													<c:when test="${obj.checked=='check'}">
													
														<input type="checkBox" checked="true" name="lgort" value="${obj.LGORT}"/>${obj.LGORT}
													</c:when>
													<c:otherwise>
														<input type="checkBox"  name="lgort" value="${obj.LGORT}"/>${obj.LGORT}
													</c:otherwise>
											</c:choose>
										</c:otherwise>
										</c:choose>
											
	
				
								</c:forEach>
							</td>
						
						<tr>
							<td class="label">标识：</td>
							<td>
								<label>物料组</label>
								<input type="hidden" name="type" value="${type }"/>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</form>
	</body>
</html>