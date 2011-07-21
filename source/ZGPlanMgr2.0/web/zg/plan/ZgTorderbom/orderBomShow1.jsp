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
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			var maxHeight = parent.document.getElementById("targetOrderBomFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			if(dealIEVersion(6)){
				height=height-25;
			}
			if(height>0){
				document.getElementById("grid-data-panel").style.height=height+'px';
			}
			
		}
		
		function synScroll(obj){
			parent.document.frames('sourceOrderBomFrame').document.getElementById('grid-data-panel').scrollTop=obj.scrollTop;
		}
	</script>
</head>
<body>
<form id="ec" action="" method="post" style="display: inline;">
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
							<td class="tableHeader">待领料数量</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${bomList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td>${obj.IDNRK}</td>
							<td>${obj.POSNR}</td>
							<td align="left">${obj.MAKTX2}</td>
							<td>${obj.SORTF}</td>
							<td>${obj.LGORT}</td>
							<td>${obj.CAR_NUM}</td>
							<td>${obj.CAR_NUM-obj.COMPLETE_NUM}
							<input type="hidden" name="moveMaxNum${n.count-1 }" id="moveMaxNum${n.count-1 }" value="${obj.CAR_NUM-obj.COMPLETE_NUM}"/>
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