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
			var queryPanelH = maxHeight;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<div id="_queryPanel">

<form action="${ctx}/zg/bom/ZgTbom/list1.do" method="post" target="listFrame">

	<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td width="20px" align="center">
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					<td width="80px" class="label">
						订单号：
					</td>
					<td width="180px">
						<input type="text" name="s_aufnr" maxlength="40" />					
					</td>
					<td width="80px" class="label">物料编码：</td>
					<td width="180px">
						<input type="text" name="s_idnrk" maxlength="40" />					
					</td>
					<td width="80px" class="label">超领状态：</td>
					<td>
						<select name="s_overNum" id="s_overNum">
							<option value="" selected="selected">全部</option>
							<option value="1" >未超领</option>
							<option value="0">超领</option>
						</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="unexpand">
			<tr>
				<td width="20px" align="center" rowspan="3"></td>
				<td width="80px" class="label">
						生产厂：
				</td>
				<td>
					<select name="s_plant">
					<option value="">全部</option>
						<c:forEach items="${plantList}" var="item">
							<option value="${item.PLANTID }">${item.PLANTID }</option>
						</c:forEach>
					</select>
				</td>
				<td width="80px" class="label">生产线：</td>
				<td width="180px">
						<select name="s_arbpl">
					<option value="">全部</option>
					<c:forEach items="${arbplList}" var="item">
						<option value="${item.cuid }">${item.cuid }</option>
					</c:forEach>
					</select>			
					</td>
					<td width="80px" class="label"></td>
				<td>
				</td>
			</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
							<a id="queryBtn" href="javascript:if(batchValidation('listFrame','${ctx}/zg/bom/ZgTbom/list1.do',document.forms[0]))document.forms[0].submit();"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
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