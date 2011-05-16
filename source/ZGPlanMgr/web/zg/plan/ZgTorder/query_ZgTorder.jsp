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

<meta http-equiv="Page-Enter" content="RevealTrans (Duration=1, Transition=12)"> 
<meta http-equiv="Page-Exit" content="RevealTrans (Duration=1, Transition=12)"> 
<html>


<head>
	<base href="<%=basePath%>" />
	<title>订单查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<style>
		html,body{overflow: hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script language="javascript" event="onkeydown" for="document">  
	<!--
	if(event.keyCode==13)  
	{  
	  document.getElementById("queryBtn").click();  
	} 
	-->
	</script>  
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorder/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		/*
		function exportData(){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
			document.forms[0].submit();
			document.forms[0].action = "${ctx}/zg/plan/ZgTorder/list.do";
		}
		*/
		
		function exportData(){
			document.forms[0].action = "${ctx}/zg/plan/ZgTorder/exportBomCar.do";
			document.forms[0].submit();
			document.forms[0].action = "${ctx}/zg/plan/ZgTorder/list.do";
		}
		
		function exportNotMatklSelfData(){
			document.forms[0].action = "${ctx}/zg/plan/ZgTorder/exportNotMatklSelfBom.do";
			document.forms[0].submit();
			document.forms[0].action = "${ctx}/zg/plan/ZgTorder/list.do";
		}
		
		
		function importData()
		{
			window.open("${ctx}/zg/tempFile/Import.jsp?template=importBomCar", "newwindow", "height=480, width=640,scrollbars=no, resizable=yes, toolbar=no, menubar=no, status=no");
		}
		
		function importtMatklSelfData()
		{
			window.open("${ctx}/zg/tempFile/Import.jsp?template=importtMatklSelfData", "newwindow", "height=480, width=640,scrollbars=no, resizable=yes, toolbar=no, menubar=no, status=no");
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/plan/ZgTorder/list.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=ZgTorder.BM_CLASS_ID%>" />
		<input type="hidden" name="s_equalBmClassIdQuery" value="${pageRequest.filters.equalBmClassIdQuery}" />
		<input type="hidden" name="s_inSubBmClassIdQuery" value="${pageRequest.filters.inSubBmClassIdQuery}" />
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td width="20px" align="center">
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					<td width="120px" class="label">
						排产开始时间：
					</td>
					<td width="180px">
						<input type="hidden" id="beforeTime_oldValue" value="${ pcdat_start}"/>
						<input type="text" dateFlag="true" value="${ pcdat_start}"  name="s_pcdat_start" id="s_pcdat_start" readonly = "true" onchange="checkBeforeAndAfterTime('s_pcdat_start','s_pcdat_end','beforeTime_oldValue','开始时间','结束时间',1)"/>
					</td>
					<td width="100px" class="label">排产结束时间：</td>
					<td width="180px">
						<input type="hidden" id="afterTime_oldValue" value="${ pcdat_end}"/>
						<input type="text" dateFlag="true" value="${ pcdat_end}" name="s_pcdat_end" id="s_pcdat_end" readonly = "true" onchange="checkBeforeAndAfterTime('s_pcdat_start','s_pcdat_end','afterTime_oldValue','开始时间','结束时间',2)"/>
					</td>
					<td width="120px" class="label">生产订单处理状态：</td>
					<td>
						<select name="s_orderState">
							<option value="">全部</option>
							<option value="0" selected="selected">待调整</option>
							<option value="4">保存</option>
							<option value="8">已提交</option>
						</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3"></td>
				<td width="120px" class="label">生产订单排序状态：</td>
					<td>
						<select name="s_pflag">
							<option value="" selected="selected">全部</option>
							<option value="C" >未排序</option>
							<option value="X" >已排序</option>
						</select>
					</td>
					<td class="label">生产订单编号：</td>
					<td><input type="text" name="s_labelCn" maxlength="40"/></td>
					<td class="label">生产线：</td>
					<td>
					<select name="s_arbpl">
					<option value="">全部</option>
					<c:forEach items="${arbplList}" var="item">
						<option value="${item.cuid }">${item.cuid }</option>
					</c:forEach>
					</select>
					
					</td>
				<tr>
				<td class="label">生产厂：</td>
					<td>
					<select name="s_plant">
					<option value="">全部</option>
						<c:forEach items="${plantList}" var="item">
							<option value="${item.PLANTID }">${item.PLANTID }</option>
						</c:forEach>
					</select>
					</td>
					<td class="label">客户机型：</td>
					<td><input type="text" name="s_maktx2" maxlength="40"/></td>
					<td class="label">销售订单号：</td>
					<td><input type="text" name="s_kdauf" maxlength="40"/></td>
					
				<tr>
					<td class="label">公司机型：</td>
					<td><input type="text" name="s_maktx1" maxlength="40"/></td>
					<td class="label">成品编号：</td>
					<td><input type="text" name="s_matnr" maxlength="40"/></td>
					<td class="label"></td>
					<td></td>
				</tr>
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a id="queryBtn" href="javascript:if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorder/list.do',document.forms[0]))document.forms[0].submit();"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<c:if test="${pageRequest.filters.planType=='5'}">
								<a href="javascript:" onclick="targetFrame('${ctx}/zg/plan/ZgTorderPlanbomForBatch/findBomBatchList.do')">
									<span><img src="<%=iconPath%>/ico_007.gif" />新单
									</span>
								</a>
							</c:if>
							<!-- <a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a> -->
							<a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出未设置规格数据</span></a>
						<!-- 	<a href="javascript:exportNotMatklSelfData()"><span><img src="<%=iconPath%>/page_excel.png" />导出未设置物料组物料</span></a>-->
							<a href="javascript:importData()"><span><img src="<%=iconPath%>/page_excel.png" />导入规格数据</span></a>
						<!-- 	<a href="javascript:importtMatklSelfData()"><span><img src="<%=iconPath%>/page_excel.png" />导入物料组</span></a>-->
							
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