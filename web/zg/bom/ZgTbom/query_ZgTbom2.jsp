<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTbomDwrAction.js'></script>
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
			var queryPanelH = maxHeight ;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
		//批量领料bom出备料库库，更新已出库数量
		function bathchOut(){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要出库的物料！");
				return;
			}
			
			var idnrkList=new Array();
			var orderPlanBomIdList=new Array();
			var numList=document.frames('listFrame').document.getElementsByName("thisOutNum");
			var flag=true;
			for(var i=0;i<items.length;i++){
				if(items[i].checked){
					DWREngine.setAsync(false);
					orderPlanBomIdList[i]=items[i].value.substring(0,items[i].value.lastIndexOf('/'));//orderplanbom表CUID
					idnrkList[i]=items[i].value.substring(items[i].value.lastIndexOf('/')+1,items[i].value.length);//物料号
					if(numList[i].value!='0'){
						ZgTbomDwrAction.updateOutNumForBathchOut(orderPlanBomIdList[i],numList[i].value,function(data){
								if(data =='success') {
								//	alert("物料："+idnrkList[i]+"成功出库"+numList[i].value+"件!");
								}else {
									alert("物料："+idnrkList[i]+"出库失败!");
									flag=false;
								}
						});  
					}
				}
			}
			if(flag){
				 alert('操作成功！');
			}
			
			document.forms[0].submit();
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<div id="_queryPanel">
<form action="${ctx}/zg/bom/ZgTbom/list2.do" method="post" target="listFrame">
	<table id="query_table" class="query_panel querybar" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td width="20px" align="center">
						<label class="expandbtn">
						</label>
					</td>
					<td width="80px" class="label">
						物料编码：
					</td>
					<td width="180px">
						<input type="text" name="s_idnrk" maxlength="40" />					
					</td>
					<td width="80px" class="label">物料描述：</td>
					<td width="180px">
						<input type="text" name="s_maktx" maxlength="40" />					
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="unexpand">
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
							<a id="queryBtn" href="javascript:if(batchValidation('listFrame','${ctx}/zg/bom/ZgTbom/list2.do',document.forms[0]))document.forms[0].submit();"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a id="queryBtn" href="javascript:bathchOut()"><span><img src="<%=iconPath%>/ico_search.gif" />确认出库</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
						<!-- <a href="javascript:exportData('${orderPlanType}')"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>  -->	
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	
</form>
<iframe src="" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no"/>
</div>
</body>
</html>