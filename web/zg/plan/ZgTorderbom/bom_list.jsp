<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderbom.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<style>
		.datalist{overflow-x: hidden;width: auto;}
		.eXtremeTable{width: auto;overflow-x: hidden;}
		.eXtremeTable .tableRegion{width: auto;overflow-x: hidden;}
	</style>
	<script type="text/javascript">
		$(function() {
			try{
				if(initLayout) {
					$(window).bind("load",initLayoutOwner);
					$(window).bind("resize",initLayoutOwner);
				}
			}catch(e){}
		});
		function initLayoutOwner() {
			if(parent) {
				var iframes = parent.document.getElementsByTagName("iframe");
				for(var i = 0; i < iframes.length; i++) {
					if(iframes[i].contentWindow == window) {
						iframes[i].style.width = parent.document.getElementById("matnrChildListDiv").offsetWidth +'px';
					}
				}
			}
			document.getElementById("datalist").style.width = document.getElementById("bomTable").offsetWidth + 2 + 'px';
		}
		function saveBom() {
			$("form:first").submit();
		}
		function changeSortf() {
			var items = document.getElementsByName("items");
			var bomTable = document.getElementById("bomTable");
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					var row = bomTable.rows[i+1];
					var sel = row.getElementsByTagName("select");
					var opts = sel[0].getElementsByTagName("option");
					for(var j = 0 ; j < opts.length;j++) {
						if(opts[j].innerHTML == $("#sortfSel").val()) {
							opts[j].selected = true;
							break;
						}
					}
				}
			}
		}
	</script>
<body scroll="auto">
	<form action="${ctx}/zg/plan/ZgTorderbom/changeSortF.do" method="post">
		<input type="hidden" name="orderId" value="${orderId}"/>
		<c:forEach items="${matnrs}" var="matnr">
		<input type="hidden" name="matnrs" value="${matnr}" />
		</c:forEach>
		<div class="datalist" id="datalist">
			<h2>领料计划管理</h2>
			<div class="toolbar">
				<div class="button" style="width:390px;margin-right:40px;vertical-align: center;background-color: #FFFFFF">
					<a href="javascript:saveBom()"><span><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
					<a href="javascript:exportBom()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
					<label style="line-height: 9px;">批量设置排序字符串:</label>
					<select id="sortfSel" style="width:60px;font-size: 9px;line-height: 9px">
						<c:forEach items="${sortFEnume}" var="sortfMap">
							<option value="${sortfMap.name}">${sortfMap.name}</option>
						</c:forEach>
					</select>
					<a href="javascript:"><span onclick="changeSortf()"><img src="<%=iconPath%>/true.gif" />确认</span></a>
				</div>
			</div>
			<div class="eXtremeTable" >
				<table id="bomTable" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" style="color:red">排序字符串</td>
							<td class="tableHeader">原排序字符串</td>
							<td class="tableHeader">物料号</td>
							<td class="tableHeader">BOM组件</td>
							<td class="tableHeader">物料描述</td>
							<td class="tableHeader">度量单位</td>
							<td class="tableHeader">组件单台用量</td>
							<td class="tableHeader">组件需求用量</td>
							<td class="tableHeader">库存地点</td>
							<td class="tableHeader">物料备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${bomList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td>
								<select id="${obj.cuid}_sortfSel" name="sortfs" style="width:60px;">
									<c:forEach items="${sortFEnume}" var="map">
										<c:choose>
											<c:when test="${map.value eq obj.sortf}">
												<option value="${obj.cuid}_${map.value}" selected="selected">${map.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${obj.cuid}_${map.value}">${map.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							<td>${obj.sortfH}</td>
							<td>${obj.matnr1}</td>
							<td>${obj.idnrk}</td>
							<td>${obj.maktx1}</td>
							<td>${obj.msehl1}</td>
							<td>${obj.zdtyl}</td>
							<td>${obj.menge}</td>
							<td>${obj.lgort}</td>
							<td>${obj.zbz}</td>
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
	</form>
</body>
</html>