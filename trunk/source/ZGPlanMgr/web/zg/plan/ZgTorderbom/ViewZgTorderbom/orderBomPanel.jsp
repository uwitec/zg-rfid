<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderbom.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderbomDwrAction.js'></script>
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			initGridEditor();
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			try {
					var infoPanelH = document.getElementById("infoPanel").offsetHeight
				var infoPanelW = document.getElementById("infoPanel").offsetWidth
				var gridDataPanelH = top.getContentHeight()-infoPanelH-23;
				document.getElementById("grid-data-panel").style.height=gridDataPanelH + 'px';
				document.getElementById("grid-data-panel").style.width=infoPanelW+'px';
				document.getElementById("grid-panel").style.width=infoPanelW+'px';
			}catch(e){}
		}
		
		var sortfList = [];
		<c:forEach items="${sortFEnume}" var="map">
			sortfList.push({value:'${map.value}',labelCn:'${map.name}'});
		</c:forEach>
		
		function changeBomSorft(obj) {
			obj.disabled = true;
			var items = document.getElementsByName("sortfs");
			var sortfs = [];
			for(var i=0; i<items.length; i++) {
				sortfs.push(items[i].getAttribute("key")+"_"+items[i].value);
			}
			if(sortfs.length > 0) {
				ZgTorderbomDwrAction.changeSortF1(sortfs,function(data){
					if(data == "success") {
						alert("保存成功！");
						window.parent.doQuery();
					}else {
						alert("保存失败！");
					}
					obj.disabled = false;
				});
			}else {
				alert("没有数据可以保存！");
				obj.disabled = false;
			}
		}
		
		function matklSet(matkl,idnrk,posnr){
			var sFeatures="dialogHeight: 400px;dialogWidth:300px";
			
			matkl=matkl.substring(matkl.indexOf("(")+1,matkl.indexOf(")"));
			var returnValue = window.showModalDialog(ctx+"/zg/plan/ZgTorderbom/materiel_tree.jsp?matkl="+matkl,'sada',sFeatures);
			if(returnValue) {
				var id = returnValue.id;
				var label=returnValue.label;
				var contect=label;
				label=label.substring(label.indexOf("(")+1,label.indexOf(")"));
				ZgTorderbomDwrAction.setSelfMatkl(idnrk,label,function(data){
					
				});
				$("#"+idnrk+"-"+posnr+"-span").attr("innerText",contect);
				
			//	var matklSelfs=document.getElementsByName("matklSelf");
			//	matklSelfs[count].value=label;
			}
		}
	</script>
</head>
  
<body>
<div id="infoPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
					<a href="javascript:"><span onclick="changeBomSorft(this)"><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
					
				</td>
			</tr>
		</thead>
	</table>
	<iframe id="orderDetailFrame" src="<c:url value="/zg/plan/ZgTorder/show.do?id=${orderId}"/>" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
</div>
<div id="dataPanel">
	<div class="grid-panel" id="grid-panel">
		<div id="title" class="title">领料计划管理</div>
		<div id="toolbar" class="toolbar">
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
			<table id="bomTable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
					<td class="tableHeader" style="color:red"></td>
						<td class="tableHeader" style="color:red">BOM组件编号</td>
						<td class="tableHeader">排序字符串</td>
						<td class="tableHeader">BOM组件描述</td>
						<td class="tableHeader">物料组</td>
						<td class="tableHeader">自有物料组</td>
						<td class="tableHeader">基本单位</td>
						<td class="tableHeader">组件单台用量</td>
						<td class="tableHeader">组件需求用量</td>
						<td class="tableHeader">库存地点</td>
						<td class="tableHeader">大小量纲</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="group" colspan="10" height="25">
							<div class="expandbtn">
								<img src="<%=expandIcon %>" type="expandAll"/>
								<a href="javascript:">全部展开</a>
							</div>
						</td>
					</tr>
				<c:forEach items="${orderboms}" var="orderbom">
					<c:if test="${orderbom.key.idnrk ne matnr}">
					<tr class="${trcss}">
						<td class="group" colspan="2" height="25" align="left">
							<div class="expandbtn">
								<img src="<%=expandIcon %>" for="${orderbom.key.idnrk}"/>
								<a href="javascript:">${orderbom.key.idnrk}</a>
							</div>
						</td>
						
						<td align="center">${orderbom.key.sortf}</td>
						<td align="left">${orderbom.key.maktx2}</td>
							<td align="left">
							<c:if test="${obj.matkl!='()'}">${obj.matkl}</c:if>
						</td>
						<td align="left"  >
							<c:if test="${obj.matklSelf!='()'}">${obj.matklSelf}</c:if>
						</td>
						<td align="center">${orderbom.key.msehl1}</td>
						<td align="center">${orderbom.key.zdtyl}</td>
						<td align="center"></td>
						<td align="left">${orderbom.key.lgort}</td>
						<td align="center">${orderbom.key.zbz}</td>
						
					</tr>
						</c:if>
					<c:forEach items="${orderbom.value}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<c:if test="${orderbom.key.idnrk ne matnr}">
							<tr class="${trcss} unexpand" attr="${orderbom.key.idnrk}">
							<td width="45px"></td>
							<td align="left">&nbsp;&nbsp;${obj.idnrk}</td>
							</c:if>
								<c:if test="${orderbom.key.idnrk eq matnr}">
							<tr class="${trcss}">
								<td align="left" colspan="2">&nbsp;&nbsp;${obj.idnrk}</td>
							</c:if>
						<c:choose>
							<c:when test="${obj.sortf eq 'CE' || obj.sortf eq 'DE'|| obj.sortf eq 'C'|| obj.sortf eq 'D'|| obj.sortf eq 'E'}">
							<td align="center"><input type="text" style="border:0;width:60px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/></td>
							</c:when>
							<c:otherwise>
							<c:if test="${obj.planNum>0}">
							<td align="center">	<input type="text" style="border:0;width:60px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/></td>
							</c:if>
							
							<c:if test="${obj.planNum==0 || empty obj.planNum}">
								<td editable="true" editdata="sortfList" align="center">
								<input type="text" edittype="value" style="border:0;width:60px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/>
							</td>
							</c:if>
						
							</c:otherwise>
							</c:choose>
						<td align="left">${obj.maktx2}</td>
							<td align="left"> <c:if test="${obj.matkl!='()'}">${obj.matkl}</c:if></td>
									<td align="left"  ondblclick="matklSet('${obj.matkl}','${obj.idnrk}','${obj.posnr}')" title="双击设置物料组"> 
									
									<span id="${obj.idnrk}-${obj.posnr }-span"><c:if test="${obj.matklSelf!='()'}">${obj.matklSelf}</c:if></span>
									
									</td><td align="center">${obj.msehl1}</td>
						<td align="center">${obj.zdtyl}</td>
						<td align="center">${obj.menge}</td>
						<td align="left">${obj.lgort}</td>
						<td align="center">${obj.zbz}</td>
					</tr>
					</c:forEach>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
