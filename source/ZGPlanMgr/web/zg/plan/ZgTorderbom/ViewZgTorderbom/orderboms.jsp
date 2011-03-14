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
	<script src="${ctx}/dwr/interface/ZgTorderbomDwrAction.js" type=""></script>
	<style>
		.extendTd{
		}
		.extendTd .extendBtn{
			height:18px;
		}
		.extendTd .extendBtn img{
			cursor: pointer;
			width:16px;
			height:18px;
		}
		.extendTd .extendBtn label{
			height:18px;
			line-height: 18px;
		}
    	#matnrListDiv {width:15%;height:100%;float:left;text-align: center;margin-top:9px; }
    	#matnrChildListDiv {width:84%;height:100%;float:left;overflow:hidden;margin:0 0 0 1px}
		#matnrListDiv ul{
			border: 1px solid #d0e5f5;
			text-align: left;
		}
		#matnrListDiv ul li{
			cursor: pointer;
			font-size: 120%;
		}
		#matnrListDiv ul li:hover{
			color: red;
			font-weight: bold;
		}
		#matnrCode {
			font-size: 130%;
			font-weight: bold;
			background-color: #d0e5f5;
			line-height: 20px;
			color: #1d5987;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			try{
				if(initLayout) {
					$(window).bind("load",initLayoutOwner);
					$(window).bind("resize",initLayoutOwner);
				}
				<c:if test="${!(empty firstBom)}">
					openBomList('${firstBom["MATNR"]}');
				</c:if>
			}catch(e){}
		});
		function openBomList(matnr) {
			$("#matnrHide").val(matnr);
			$("#bomListForm").submit();
		}
		function initLayoutOwner() {
			document.getElementById("matnrListDiv").style.width="160px";
			document.getElementById("matnrChildListDiv").style.width = document.body.offsetWidth - 165 + 'px';
			fitLayout();
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<div id="_queryPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="formToolbar">
					<div class="button" style="text-align: left;">
						<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
					</div>
				</td>
			</tr>
		</thead>
	</table>
	<iframe id="orderDetailFrame" src="<c:url value="/zg/plan/ZgTorder/show.do?id=${orderId}"/>" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
</div>
<form id="bomListForm" action="${ctx}/zg/plan/ZgTorderbom/findViewBomList.do" method="post" target="bomListFrame">
	<input type="hidden" name="orderId" value="${orderId}"/>
	<input type="hidden" id="matnrHide" name="matnr"/>
	<div id="matnrListDiv">
		<div id="matnrCode" style="font-size: 12px;">
			<table class="formitem" width="100%" cellpadding="0" cellspacing="1" style="border-top:1px solid #A8CFEB;vertical-align: middle">
				<tr>
					<td class="title" style="padding: 0;margin: 0;line-height: 12px;">
						<div class="button" style="text-align: left">
							<input type="checkbox" onclick="setAllCheckboxState('matnrs',this.checked)" value=""/>
							物料编号
							<a href="javascript:"><span onclick="openBomList('')"><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<ul>
			<c:forEach items="${bomList}" var="obj" varStatus="n">
			<li>
				<input type="checkbox" name="matnrs" value="${obj["MATNR"]}"/>
				<label onclick="openBomList('${obj['MATNR']}')">${obj["MATNR"]}</label>
			</li>
			</c:forEach>
		</ul>
	</div>
	<div id="matnrChildListDiv">
		<iframe id="matnrChildFrame" name="bomListFrame" src="" scrolling="no" width="100%" height="100%" frameborder="0" fitlayout="true"></iframe>
	</div>
</form>
</body>
</html>