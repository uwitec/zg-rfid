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
	<title>组织机构查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<style>
		html,body{overflow: hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
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
			if(batchValidation('listFrame','${ctx}/frame/sys/FwOrganization/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
	function batchDelete(action,checkboxName,form){
	    if (!hasOneChecked(checkboxName)){
	            alert('请选择要操作的对象!');
	            return;
	    }
	    if (confirm('确定执行[删除]操作?')){
	        form.action = action;
	        form.submit();
	    }
	}
	
	function hasOneChecked(name){
	    var items = document.frames['listFrame'].document.getElementsByName(name);
	    if (items.length > 0) {
	        for (var i = 0; i < items.length; i++){
	            if (items[i].checked == true){
	                return true;
	            }
	        }
	    } else {
	        if (items.checked == true) {
	            return true;
	        }
	    }
	    return false;
	}
	
		function exportData(type){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do?exportType="+type;
			document.forms[0].submit();
			document.forms[0].action = "${ctx}/frame/sys/FwOrganization/list.do";
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/frame/sys/FwOrganization/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=FwOrganization.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td width="20px" align="center">
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					<td class="label" width="80px">机构名称：</td>
			   	<td width="180px">
		   			<input type="text" name="s_labelCn" maxlength="40" />
		   			
			   	</td>
			  	<td class="label" width="80px"><%=FwOrganization.ALIAS_PARENT_ORG_ID%>：</td>
			   	<td width="180px">
			   		<input type="text" size="30" maxlength="40" autocomplete="true" xtype="tree:1" id="parentOrgId_label" columnNameLower="parentOrgId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN"/>
			   		<input type="hidden" id="parentOrgId_value" name="s_parentOrgId" value="${parentOrgId }"/>
			   	</td>
			   	<td class="label" width="80px">等级：</td>
			   	<td>
			   		<input type="text" name="s_levelNum" maxlength="40"/>
			   </td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
				   <td width="20px" ></td>
				   <td class="label"  width="80px"><%=FwOrganization.ALIAS_TYPE%>：</td>
				   <td>
			   		<select name="s_type" id="s_type" >
			   			<option value="">全部</option>
						<option value="1">部门</option>
						<option value="2">仓库</option>
					</select>
				   </td>
				   
				   <td class="label"  width="80px"></td>
				   <td>
			   			
				   </td>
				</tr>
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
						<a  id="queryBtn" href="javascript:batchValidation('listFrame','${ctx}/frame/sys/FwOrganization/list.do',document.forms[0]);document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
						 <a href="javascript:targetFrame('${ctx}/frame/sys/FwOrganization/create.do?parentOrgId=${parentOrgId }')"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
						 <a href="javascript:batchDelete('${ctx}/frame/sys/FwOrganization/delete.do','items',document.frames['listFrame'].document.forms['ec'])"><span><img src="<%=iconPath%>/ico_009a.gif" />删除</span></a>
						 <a href="javascript:"><span onclick="document.forms[0].reset()"><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
						 <a href="javascript:exportData(1)"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
						 <a href="javascript:exportData(2)"><span><img src="<%=iconPath%>/page_excel.png" />导出全部</span></a>
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