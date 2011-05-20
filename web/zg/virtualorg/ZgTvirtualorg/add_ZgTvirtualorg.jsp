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
		<title>领料组新增</title>
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
	
		function saveVirtualorg(){
			if($("form[validator]").valid()){
			//	document.forms[0].submit();
				var labelCn=document.getElementById('labelCnId').value;
				var orgId=document.getElementById('orgId').value;
				var note=document.getElementById('noteId').value;
				var type=document.getElementById('type').value;
				var obj=new Object();
				DWREngine.setAsync(false);
				ZgTvirtualorgDwrAction.validLabelCnForAdd(orgId,labelCn,function(sameLabelCn){
					if(sameLabelCn){
						DWREngine.setAsync(false);
						ZgTvirtualorgDwrAction.saveVirtualorgAndReturnCuid(labelCn,orgId,note,type,function(cuid){
							obj.cuid=cuid;
						});
						obj.labelCn=labelCn;
						window.returnValue=obj;
						window.close();
					}else{
						document.getElementById('labelCnIdValid').style.display="";
					}
				});
			}
		}
		function labelCnIdValidHidden(){
			document.getElementById('labelCnIdValid').style.display="none";
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
									<a href="javascript:saveVirtualorg()"><span><img
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
								<span style="color:red">*</span>领料组名称：
							</td>	
							<td>
						   		<input type="text" id="labelCnId"  size="30" maxlength = "20" name="labelCn" onfocus="labelCnIdValidHidden()" class="required"/>
						   		<span id="labelCnIdValid" style="display:none">该机构下有相同名称的领料组!</span>
							</td>
						</tr>
						<tr>
							<td  class="label">
							描述：
							</td>	
							<td>
						   		<input type="text" maxlength="20" size="30" id="noteId" name="note"  class=""/>
							</td>
						</tr>
						<tr>
							<td  class="label">
								领料组类型：
							</td>	
							<td>
								<select name="type" id="type" >
									<option value="ALL"></option>
									<option value="ABC">预焊</option>
									<option value="ABD">预装</option>
									<option value="ABE">总装</option>
								</select>
							</td>
						</tr>
						<tr>
							<td  class="label">
								所属机构：
							</td>	
							<td>
						   		<label>${orgName}</label>
						   		<input type="hidden" name="orgId" value="${orgId }" id="orgId"/>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</form>
	</body>
</html>