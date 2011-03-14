<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=FwMenu.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			initSplit();
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		}
		function doLayout() {
			var maxHeight = top.getContentHeight();
			var splitH = document.getElementById("split_1").offsetHeight;
			var queryPanelH = maxHeight - splitH;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
			var operateFrameH = queryPanelH;
			document.getElementById("operateFrame").style.height = operateFrameH + 'px';
			document.getElementById("_operatePanel").style.height = operateFrameH + 'px';
		}
		function doQuery() {
			if(document.getElementById("_queryPanel").style.display == 'none') {
				changePanel(document.getElementById("split_1"));
			}
			if(batchValidation('listFrame','${ctx}/frame/sys/FwMenu/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		function delMenu(url){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的菜单！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
       			 document.frames('listFrame').document.forms[0].action = url;
			 	 document.frames('listFrame').document.forms[0].submit();
   			 }
			
		}
	</script>
</head>

<body>
<div id="_queryPanel">
<form action="${ctx}/frame/sys/FwMenu/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=FwMenu.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
		<table  id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="20px" align="center">
						<img src="${ctx }/resources/images/frame/ico_expand.gif" style="cursor: pointer" title="高级查询" alt="" id="img_1" border="0" onclick="changeV('1')"/>
					</td>
				   <td class="label" width="80px" >菜单名称：</td>
				   <td>
			   			<input type="text" name="s_labelCn" maxlength="20"      />
				   </td>
				   <td class="label"  width="80px">父菜单：</td>
				   <td>
			   			<input type="text"  maxlength="20"   autocomplete="true" xtype="tree:5" id="parentMenuId_label" columnNameLower="parentMenuId" bmClassId="FW_MENU" column="m.t0_LABEL_CN"/>
			   			<input type="hidden" id="parentMenuId_value" name="s_parentMenuId"/>
				   </td>
				   <td class="label"  width="80px">级别：</td>
				   <td>
			   			<input type="text" name="s_levelNum"  maxlength="2"  />
				   </td>
				</tr>
			</thead>
			<tbody id="tbody_1" class="querybar" style="display: none;">
				<tr>
					<td width="20px" rowspan="2" ></td>
					  <td class="label" width="80px">顺序：</td>
				   <td>
			   			<input type="text" name="s_displayOrder"  maxlength="20"  />
				   </td>
				   <td class="label" width="80px"></td>
				   <td>
				   </td>
				 
				   
				   <td class="label" width="80px"></td>
				   <td>
				   </td>
				</tr>
			</tbody>
			<tfoot class="querybar">
					<tr>
						<td colspan="7" align="right">
							<div class="toolbar">
								<a id="queryBtn" href="javascript:if(batchValidation('listFrame','${ctx}/frame/sys/FwMenu/list.do',document.forms[0]));document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
								<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
								<a href="javascript:targetFrame('${ctx}/frame/sys/FwMenu/create.do?orgId=${orgId}')"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
								<a href="javascript:delMenu('${ctx}/frame/sys/FwMenu/delete.do')">
									<span><img src="<%=iconPath%>/ico_005.gif" />删除
									</span>
								</a>
							</div>
						</td>
					</tr>
			</tfoot>
		</table>
</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
</div>
<div id="split_1" class="splitbar off"></div>
<div id="_operatePanel" class="operatorPanel" style="display: none;">
	<iframe id="operateFrame" src="" name="operateFrame" frameborder="0" width="100%" scrolling="no"></iframe>
</div>
</body>
</html>