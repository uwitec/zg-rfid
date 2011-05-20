<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
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
		<title>　备注</title>
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
			<script type='text/javascript'
			src='${ctx}/dwr/interface/ZgTorderPlanGroupDwrAction.js'></script>
		<script type="text/javascript">
			function updateZbz(){
				ZgTorderPlanGroupDwrAction.addPlanZbz($("#groupIds").val(),$("#zbz").val(),function(data){
					if(data){
						//alert("操作成功");
						window.returnValue="OK";
						window.close();
					}else{
						alert("系统繁忙 ，请稍后再试");
					}
				});
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
									<a href="javascript:updateZbz()"><span><img
												src="<%=iconPath%>/true.gif" />确定</span> </a>&nbsp
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
								<span style="color:red">*</span>备注：
							</td>	
							<td>
							<input type="hidden" value="${groupIds }" name="groupIds" id="groupIds"/> 
						   		<textarea rows="8" cols="40" onpropertychange="checkLength(this,100);"  name="zbz" id="zbz"> </textarea>
							</td>
						</tr>
						
					</tbody>	
				</table>
			</div>
		</form>
	</body>
</html>