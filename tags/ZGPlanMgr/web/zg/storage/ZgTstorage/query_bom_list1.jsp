<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<base target="_self"/>
	<title>半成品列表查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		$(function() {
			if(true){
				$("form:first").submit();
			}
			try{
				if(fitLayout) {
					$(window).bind("load",fitLayout);
				}
				if(initSplit) {
					$(window).bind("load",initSplit);
				}
				doLayout();
			}catch(e){}
		});
		
		function generate(){
			var form = listFrame.document.getElementById("ec");
			var items = listFrame.document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag = true;
				}
			}
			if(flag) {
				form.action = "${ctx}/zg/storage/ZgTstoragebom/generateBom.do";
				form.submit();
			}else {
				alert("请选择相应的入库单！");
			}
		}
		
		function doLayout() {
			document.getElementById("div_window").style.height = 420;
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<div id="div_window">
<form action="${ctx}/zg/storage/ZgTstoragebom/findBomlListByArbplOrderId.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=ZgTstorageCanclebom.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="storageId"  value="${storageId}"/>
	<input type="hidden"  name="orderId"  value="${orderId}"/>
	<input type="hidden"  name="arbpl"  value="${arbpl}"/>
	<input type="hidden"  name="productType"  value="${productType}"/>
	<input type="hidden"  name="type"  value="${type}"/>
	<input type="hidden"  name="lgort"  value="${lgort}"/>
	
	<input type="hidden"  name="s_storageId"  value="${storageId}"/>
	<input type="hidden"  name="s_orderId"  value="${orderId}"/>
	<input type="hidden"  name="s_arbpl"  value="${arbpl}"/>
	<input type="hidden"  name="s_productType"  value="${productType}"/>
	<input type="hidden"  name="s_type"  value="${type}"/>
	<input type="hidden"  name="s_lgort"  value="${lgort}"/>
	<table class="formTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="80px" class="label">
						半成品名称：
					</td>
					<td width="120px">
						<input type="text"  value="${ idnrk}"  name="s_idnrk" maxlength="40"/>
					</td >
						<td  width="220px" >
							<div class="button" >
								<a href="javascript:"><span
									onclick="if(batchValidation('listFrame','${ctx}/zg/storage/ZgTstoragebom/findBomlListByArbplOrderId.do',document.forms[0]));document.forms[0].submit();"><img
											src="<%=iconPath%>/ico_search.gif" />查询</span>
								</a>
								&nbsp;<a href="javascript:"><span
									onclick="document.forms[0].reset()"><img
											src="<%=iconPath%>/ico_007.gif" />重置</span>
								</a>
								&nbsp;<a href="javascript:"><span onclick="generate();"><img
											src="<%=iconPath%>/true.gif" />确定</span>
								</a>
							</div>
						</td>
						<td width="*"></td>
					</tr>
			</thead>
		</table>
</form>
<iframe src=""  name="listFrame" frameborder="0" width="100%" height="100%" align="top" />
</div>
</body>
</html>