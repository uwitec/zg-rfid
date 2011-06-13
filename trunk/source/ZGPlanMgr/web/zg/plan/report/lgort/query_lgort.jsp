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
		.ui-timepicker-div .ui-widget-header{ margin-bottom: 8px; }
.ui-timepicker-div dl{ text-align: left; }
.ui-timepicker-div dl dt{ height: 25px; }
.ui-timepicker-div dl dd{ margin: -25px 0 10px 65px; }
.ui-timepicker-div td { font-size: 90%; }
		
	</style>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript">
		$(function() {
			init();
			$("#s_date_start").datetimepicker({
			showSecond: true, //显示秒
			timeFormat: "hh:mm:ss",//格式化时间
			dateFormat:"yy-mm-dd",
			stepHour: 1,//设置步长
			stepMinute: 1,
			stepSecond: 1
			});
			
				$("#s_date_end").datetimepicker({
			showSecond: true, //显示秒
			timeFormat: "hh:mm:ss",//格式化时间
			dateFormat:"yy-mm-dd",
			stepHour: 1,//设置步长
			stepMinute: 1,
			stepSecond: 1
			});

			
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
		
		function queryTypeChange(){
			var queryType=document.getElementById('queryType').value;
			if(queryType=='stat'){
				document.forms[0].action="${ctx}/zg/report/Report/lgortReportStat.do";
			}else{
				document.forms[0].action="${ctx}/zg/report/Report/lgortReport.do";
			}
			document.forms[0].submit();
		}
		
		function exportData(){
			var queryType=document.getElementById('queryType').value;
			if(queryType=='stat'){	
				document.forms[0].action = "${ctx}/zg/report/Report/lgortReportStatExport.do";
				document.forms[0].submit();
				document.forms[0].action = "${ctx}/zg/report/Report/lgortReportStat.do";
			}else{
				document.forms[0].action = "${ctx}/zg/report/Report/lgortReportExport.do";
				document.forms[0].submit();
				document.forms[0].action = "${ctx}/zg/report/Report/lgortReport.do";
			}
		
		}
		
		
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/report/Report/lgortReportStat.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=ZgTorder.BM_CLASS_ID%>" />
		<input type="hidden" name="s_equalBmClassIdQuery" value="${pageRequest.filters.equalBmClassIdQuery}" />
		<input type="hidden" name="s_inSubBmClassIdQuery" value="${pageRequest.filters.inSubBmClassIdQuery}" />
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td width="20px" align="center" >
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					<td class="label" align="right">统计类型：</td>
					<td width="180px"　>
						<select name="queryType" id="queryType" onchange="queryTypeChange();">
							<option value ="stat">领料汇总</option>	
							<option value ="detail">领料明细</option>					
						</select>
					</td>
					<td width="120px" class="label">
						领料开始时间：
					</td>
					<td width="180px"　>
						<input type="text" dateTimeFlag="true" style="width:140px" value="${ date_start}"  name="s_date_start" id="s_date_start" readonly = "true" />
					</td>
					<td width="100px" class="label">领料结束时间：</td>
					<td width="*">
						<input type="text" dateTimeFlag="true"  style="width:140px"  value="${ date_end}" name="s_date_end" id="s_date_end" readonly = "true" />
					</td>
					
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3"></td>
					<td  class="label">生产订单号：</td>
					<td>
						<input type="text" name="s_aufnr" maxlength="40"/>
					</td>
				<td width="120px" class="label">产品编号：</td>
					<td>
							<input type="text" name="s_matnr" maxlength="40"/>
					</td>
					<td class="label">领料部门：</td>
					<td>
					<select name="s_plant">
					<option value="">全部</option>
					<c:forEach items="${ orgList}" var="item">
						<option value="${item.PLANTID }">${item.NAME }</option>
					</c:forEach>
					</select>
					</td>
					
					</tr>
				<tr>
				<td class="label">领料线体：</td>
					<td>
					<select name="s_arbpl">
					<option value="">全部</option>
					<c:forEach items="${arbplList}" var="item">
						<option value="${item.cuid }">${item.cuid }</option>
					</c:forEach>
					</select>
					
					</td>
				<td class="label">物料编码：</td>
					<td>
					<input type="text" name="s_idnrk" maxlength="40"/>
					</td>
					<td class="label">发料仓库</td>
					<td><input type="text" name="s_lgort" maxlength="40"/>  </td>
					
				</tr>
			<tr>
			<td class="label">供应商：</td>
					<td><input type="text" name="s_suppliers" maxlength="40"/></td>
					<td class="label">领料类型</td>
					<td>
					<select name="s_planType">
					<option value="">全部</option>
					<option value="ABC','ABD','ABE">正常领料</option>
					<option value="BACK">退料</option>
					<option value="CHANGE">换料</option>
					<option value="RENEW">补料</option>
					</select>
					
					</td>
					<td class="label"></td>
					<td></td>
			</tr>
					
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a id="queryBtn" href="javascript:queryTypeChange();"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
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