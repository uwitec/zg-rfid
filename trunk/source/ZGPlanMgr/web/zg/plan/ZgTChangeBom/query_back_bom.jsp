<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_noexpand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title>退料物料查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTBomManagerDwrAction.js"></script>
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
		function query(){
		    var createDate_start=$("#s_createDate_start").val()
		    var createDate_end=$("#s_createDate_end").val()
		    if(createDate_start>createDate_end){
		      alert("开始时间不能大于结束时间")
		      
		    }
		   
		    
			var planStat= $("#s_state").val();
			
			
			if(planStat=='-8'){
			  
				$("#deletePlanButton").attr("style","display:''")
				
			}else{
				$("#deletePlanButton").attr("style","display:none");
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listBackBom.do',document.forms[0]))document.forms[0].submit();
			document.forms[0].submit();
			
		  
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listBackBom.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
		function addPlan(){
			var type=$("#s_type").val();
			targetFrame('${ctx }/zg/plan/ZgTBomManager/createForChange.do?type='+type);
		}
		
		function backBom(){
			var form = listFrame.document.getElementById("ec");
			form.action="${ctx }/zg/plan/ZgTBomManager/backBom.do";
			form.submit();
		}
		
		function backBom(){
			var buildupSubmitParams=document.frames('listFrame').buildupSubmitParams();
			ZgTBomManagerDwrAction.chckBackBomNum(buildupSubmitParams,function(data){
				if(data=="success") {
					var form=document.frames('listFrame').forms[0];
					form.action="${ctx}/zg/plan/ZgTBomManager/backBom.do";
					form.submit();
				}else {
					alert(data);
				}
			});

		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/plan/ZgTBomManager/listBackBom.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=ZgTorderPlan.BM_CLASS_ID%>"/>
		<input type="hidden" name="s_planType" value="${pageRequest.filters.planType}"/>
		<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
		<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="20px" align="center">
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					</td>
						<td class="label" width="80px">生产订单：</td>
						<td><input type="text" size="20" name="s_aufnr"/></td>
					<td width="80px" class="label">
						生产厂：
					</td>
					<td width="120px">
						<select name="s_plant">
							<c:forEach items="${planList}" var="item">
								<option value="${item.ORG_ID }">${item.ORG_ID }</option>
							</c:forEach>
						</select>
					</td>
					
					<td width="80px" class="label">
						生产线：
					</td>
					<td width="120px">
						<select name="s_arbpl">
							<c:forEach items="${arbplList}" var="item">
								<option value="${item.cuid }">${item.cuid }</option>
							</c:forEach>
						</select>
					</td>
				
					
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar expand">
				<tr>
				
					<td width="20px" align="center" rowspan="3" ></td>
					<td width="80px" class="label">物料号：</td>
					<td width="120px">
						<input type="text" size="20" name="s_matnr"/>
					</td>
					<td width="80px" class="label">状态：</td>
					<td width="80px" colSpan="3">
						<select name="s_state" id="s_state">
						    <option value="">全部</option>
						    <option value="0" selected="select">待完成</option>
						    <option value="9">已完成</option>
						</select>
					</td>
				</tr>
					
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
							
							<a href="javascript:query()" id="queryBtn"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<a id="deletePlanButton" style="display:none" href="javascript:deletePlan()"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
							<a href="javascript:addPlan();"><span><img src="<%=iconPath%>/addition.gif" />新单</span></a>
							<a href="javascript:backBom();"><span><img src="<%=iconPath%>/true.gif" />确定退料</span></a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no" ></iframe>
</div>
<div id="split_1" class="splitbar off"></div>
<div id="_operatePanel" class="operatorPanel" style="display: none;">
	<iframe id="operateFrame" src="" name="operateFrame" frameborder="0" width="100%" align="top" scrolling="no"></iframe>
</div>
</body>
</html>
