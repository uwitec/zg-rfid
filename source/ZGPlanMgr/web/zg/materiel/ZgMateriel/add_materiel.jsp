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
		<title>物料组新增</title>
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
	
		function saveMateriel(){
			if($("form[validator]").valid()){
				var materielName=document.getElementById('materielName').value;
				var parentId=document.getElementById('parentId').value;
				var id=document.getElementById('id').value;
				var obj=new Object();
				DWREngine.setAsync(false);
				ZgMaterielDwrAction.validName(parentId,materielName,function(sameNameFlag){
					if(sameNameFlag){
						ZgMaterielDwrAction.validId(parentId,id,function(sameIdFlag){
							if(sameIdFlag){
								DWREngine.setAsync(false);
								ZgMaterielDwrAction.saveMateriel(materielName,parentId,id,function(cuid){
									obj.cuid=cuid;
								});
								obj.materielName=materielName;
								window.returnValue=obj;
								window.close();
							}else{
								document.getElementById('idValid').style.display="";
							}
						});
					}else{
						document.getElementById('materielNameValid').style.display="";
					}
				});
			}
		}
		function nameValidSpanHidden(){
			document.getElementById('materielNameValid').style.display="none";
		}	
		function idValidSpanHidden(){
			document.getElementById('idValid').style.display="none";
		}
		function backTree(){
			window.close();
		}
	</script>
	</head>

	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form id="tt" action="${ctx}/zg/virtualorg/ZgTvirtualorg/saveVirtualorg.do" method="post"
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
							<td  class="label">
								<span style="color:red">*</span>物料组名称：
							</td>	
							<td>
						   		<input type="text" id="materielName"  size="30" maxlength = "20" name="materielName" onfocus="nameValidSpanHidden()" class="required"/>
						   		<span id="materielNameValid" style="display:none ">该机构下有同名物料组!</span>
							</td>
						</tr>
						
						<tr>
							<td class="label">
								<span style="color:red">*</span>物料组ID：
							</td>
							<td>
								<input type="text" name="Id" id="id" maxlength="20" size="30"class="required" onfocus="idValidSpanHidden()"/>
								<span id="idValid" style="display:none">该机构下有同名物料组ID!</span>
							</td>
						</tr>
						
						<tr>
							<td  class="label">
								上层机构名称：
							</td>	
							<td>
								<label>${parentName}</label>
						   		<input type="hidden" name="parentId" value="${parentOrgId }" id="parentId"/>
							</td>
						</tr>
						<tr>
							<td class="label"> 标识：</td>
							<td>
								<label>物料组</label>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</form>
	</body>
</html>