<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.bom.base.model.*" %>
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
	<title><%=ZgCarInfo.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			if(true){
			}
		});
		
		function delCarInfo(url){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的车型信息！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
				document.frames('listFrame').document.forms[0].action = url;
				document.frames('listFrame').document.forms[0].submit();
			}
			
		}
		function targetFrame(url) {
			window.location.assign(url);
		}
		
		function create(url){
			window.location = url;
			document.forms[0].submit();
		}	
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<div id="_queryPanel">
	<form id="eq" action="${ctx}/zg/bom/ZgCarInfo/list.do" method="post" target="listFrame" style="display: inline;">
	<input type="hidden" name="bmClassId" value="<%=ZgCarInfo.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<table  id="query_table" class="query_panel" cellpadding="0" cellspacing="2">
		<tr align="left">
			<td style = "width:80px"  colspan="3"><%=ZgCarInfo.ALIAS_CODE %>：</td>
			<td >
				<input type = "text" size = "30" maxlength = "10" id = "code" name = "code"/>
			</td>			
			<td style = "width:80px" size = "30" maxlength = "85" colspan="3"><%=ZgCarInfo.ALIAS_LABEL_CN %>：</td>
			<td align="left">
				<input type = "text" id = "labelCn" name = "labelCn"/>
			</td>
			<td align="right">
				<div class="toolbar">
					<a id="queryBtn" href="javascript:batchValidation('listFrame','${ctx}/zg/bom/ZgCarInfo/list.do',document.forms[0]);document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
					<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
					<a href="javascript:create('${ctx}/zg/bom/ZgCarInfo/create.do')"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
					<a href="javascript:delCarInfo('${ctx}/zg/bom/ZgCarInfo/delete.do')"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
				</div>
			</td>		
		</tr>
	</table>
</form>
</div>
<iframe src="${ctx}/zg/bom/ZgCarInfo/list.do" name="listFrame" id="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>

</body>
</html>