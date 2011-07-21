<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.pz.modules.base.fwdep.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderbomMoveLog.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/default/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/default/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<style>
		html,body{overflow: hidden;margin: 0;padding: 0}
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
		
		function delEntity(url){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的数据！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
				document.frames('listFrame').document.forms[0].action = url;
				document.frames('listFrame').document.forms[0].submit();
			}
		}
		
			function doQuery() {
			if(document.getElementById("_queryPanel").style.display == 'none') {
				changePanel(document.getElementById("split_1"));
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorderbomMoveLog/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
	</script>
</head>

<body>

<div id="_queryPanel">
<form action="${ctx}/zg/plan/ZgTorderbomMoveLog/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=ZgTorderbomMoveLog.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
			<table  id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="20px" align="center">
							<label class="expandbtn">
								<img src="<%=expandIcon %>" for="tbody_1"/>
							</label>
					</td>
				   <td class="label"  width="80px">：</td>
				   <td colspan="3">
				   </td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_SOURCE_PLANBOM_ID%>：</td>
			   <td>
		   			<input type="text" name="s_sourcePlanbomId" size="30"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_SOURCE_ORDER_TASK_ID%>：</td>
			   <td>
		   			<input type="text" name="s_sourceOrderTaskId" size="30"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_TARGET_ORDER_TASK_ID%>：</td>
			   <td>
		   			<input type="text" name="s_targetOrderTaskId" size="30"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_TARGET_PLANBOM%>：</td>
			   <td>
		   			<input type="text" name="s_targetPlanbom" size="30"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_MOVE_NUM%>：</td>
			   <td>
		   			<input type="text" name="s_moveNum" size="30"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_CREATE_ID%>：</td>
			   <td>
		   			<input type="text" size="30" autocomplete="true" xtype="drm-complex-select" id="createId_label" columnNameLower="createId" bmClassId="FW_EMPLOYEE" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="createId_value" name="s_createId"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_CREATE_DATE%>：</td>
			   <td>
		   			<input type="text" dateFlag="true" name="s_createDate_start" size="30"/>
		   			至　<input type="text" dateFlag="true" name="s_createDate_end" size="30"/>
			   </td>
			</tr>
			<tr>
			   <td class="tdLabel"><%=ZgTorderbomMoveLog.ALIAS_CREATE_USERNAME%>：</td>
			   <td>
		   			<input type="text" name="s_createUsername" size="30"/>
			   </td>
			</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div class="toolbar">
								<a id="queryBtn" href="javascript:batchValidation('listFrame','${ctx}/zg/plan/ZgTorderbomMoveLog/list.do',document.forms[0]);document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
								<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
								<a href="javascript:targetFrame('${ctx}/zg/plan/ZgTorderbomMoveLog/create.do')"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
								<a href="javascript:delEntity('${ctx}/zg/plan/ZgTorderbomMoveLog/delete.do')"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
</div>
<div id="split_1" class="splitbar off"></div>
<div id="_operatePanel" class="operatorPanel" style="display: none;">
	<iframe id="operateFrame" src="" name="operateFrame" frameborder="0" width="100%" scrolling="no"></iframe>
</div>
</body>
</html>