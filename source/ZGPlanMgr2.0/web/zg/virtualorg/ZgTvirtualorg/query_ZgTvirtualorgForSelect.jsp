<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.virtualorg.base.model.*" %>
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<base target="_self"/>
	<title><%=ZgTvirtualorg.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgMaterielVirtualorgDwrAction.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		}
		function doLayout() {
			var maxHeight = top.dialogHeight;
			maxHeight=maxHeight.replace("px","")*1;
			var queryPanelH = maxHeight;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
		
		function submitMat(){
					var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			var allMaterielId='';
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					allMaterielId=allMaterielId+items[i].value+","
				}
			}
			if(allMaterielId==''){
				alert("请选择需要添加的物料组！");
				return;
			}
			
				var obj=new Object();
				obj.allMaterielId=allMaterielId;
				window.returnValue=obj;
				window.close();
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/virtualorg/ZgTvirtualorg/listMateridel.do" method="post" target="listFrame">
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
						<input type="hidden" name="s_orgId" id="s_orgId" value="${orgId }"/>
		
			<thead class="querybar">
				<tr>
					 <td class="label">
					   		<%=ZgMateriel.ALIAS_MATERIEL_NAME%>：
					   </td>
					   <td >
				   			<input type="text" maxlength="20" name="s_materielName" id="s_materielName"/>
					   </td>
					   	 <td class="label">
					   		仓库编号：
					   </td>
					   <td >
				   			<input type="text" maxlength="20" name="s_lgort" id="s_lgort"/>
					   </td>
				</tr>
			</thead>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
												<a href="javascript:" onclick="document.forms[0].submit();"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:submitMat()"><span><img src="<%=iconPath%>/true.gif" />确定</span></a>
							<a href="javascript:window.close()"><span><img src="<%=iconPath%>/ico_007.gif" />关闭</span></a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
</div>

	
	
</body>
</html>