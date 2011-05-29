<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<html>
<head>
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			var num="${fn:length(bomList)}";
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			var maxHeight = parent.document.getElementById("sourceOrderBomFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			if(dealIEVersion(6)){
				height=height-25;
			}
			if(height>0){
				document.getElementById("grid-data-panel").style.height=height+'px';
			}
			
		}
		
		function checkMoveNum(obj,num) {
			if(parent.document.getElementById('targetOrderId').value==''){
				obj.value = '';
				alert("请先选择目标订单");
				return;
			}
			var max = obj.getAttribute("maxValue")*1;
			var oldValue=obj.getAttribute("oldValue")*1;
			var value = obj.value;
			var targetmax=parent.document.frames('targetOrderBomFrame').document.getElementById('moveMaxNum'+num).value;
			if(value==''){
				return;
			}
			if(isNumber(value)) {
				if(targetmax<(value*1)){
					alert("物料："+obj.attr+" 目标订单移单后该BOM数量不能超过需求数量");
					obj.value = oldValue;
					return;
				}
				if(max < value*1) {
					alert("物料："+obj.attr+" 移单数量必须小于可移单数量");
					obj.value = oldValue;
					return;
				}
				
			}else {
				alert("物料："+obj.attr+" 移单数量必须为数字！");
				obj.value = oldValue;
				viewObj.innerText=oldValue;
				return;
			}
			
			obj.setAttribute("oldValue",value);
		}
		
		function synScroll(obj){
			parent.document.frames('targetOrderBomFrame').document.getElementById('grid-data-panel').scrollTop=obj.scrollTop;
		}
	</script>
</head>
<body>
<form id="ec" action="${ctx }/zg/plan/ZgTorderBom/bomMove.do" method="post" style="display: inline;">
	<input type="hidden" id="rowNum" value="${fn:length(bomList)}"/>
	<div>
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">BOM列表</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel" onscroll="synScroll(this)">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">BOM组件</td>
							<td class="tableHeader">项目编号</td>
							<td class="tableHeader">物料描述</td>
							<td class="tableHeader">制作标识</td>
							<td class="tableHeader">库存地点</td>
							<td class="tableHeader">需求数量</td>
							<td class="tableHeader">备料库存</td>
							<td class="tableHeader">实际移单数量</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${bomList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td>${obj.IDNRK}</td>
							<td>${obj.POSNR}</td>
							<td align="left">${obj.MAKTX2}</td>
							<td >${obj.SORTF}</td>
							<td>${obj.LGORT}</td>
							<td>${obj.CAR_NUM}</td>
							<td>
									<c:set var ="maxValue" value="${obj.COMPLETE_NUM}"></c:set>
										${maxValue}
							</td>
							<td>
								<input type="hidden" name="bomList[${n.count-1}].cuid" id="bomList[${n.count-1}].cuid" value="${obj.CUID}"/>
								<input type="hidden" name="bomList[${n.count-1}].idnrk" id="bomList[${n.count-1}].idnrk" value="${obj.IDNRK }"/>
								<input type="hidden" name="bomList[${n.count-1}].matkl" id="bomList[${n.count-1}].matkl" value="${obj.MATKL }"/>
								<input type="hidden" name="bomList[${n.count-1}].lgort" id="bomList[${n.count-1}].lgort" value="${obj.LGORT }"/>
								<input type="hidden" name="bomList[${n.count-1}].orderId" id="bomList[${n.count-1}].orderId" value="${obj.ORDER_ID }"/>
								<input type="hidden" name="bomList[${n.count-1}].orderTaskId" id="bomList[${n.count-1}].orderId" value="${obj.ORDER_TASK_ID }"/>
								<input type="hidden" name="bomList[${n.count-1}].posnr" id="bomList[${n.count-1}].posnr" value="${obj.POSNR }"/>
								<input type="text" maxValue="${maxValue}" oldValue="0" maxlength="13" attr="${obj.IDNRK }"
								 onchange="checkMoveNum(this,'${n.count-1}')" size="8" name="bomList[${n.count-1}].moveNum"
								 id="bomList[${n.count-1}].moveNum" />
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="11" style="line-height: 13px">&nbsp;
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
</body>
</html>