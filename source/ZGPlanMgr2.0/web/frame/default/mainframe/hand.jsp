<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<base href="<%=basePath%>" />
	<title>系统导航</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/widgets/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-treeview/jquery.treeview.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-treeview/jquery.treeview.async.js"></script>
	<script>
		$(function() {
			$("#accordion").accordion();

			$("#navigation").treeview({
				url: "${ctx}/Frame/navTree.do"
			});
			
			$(window).bind('load',function(){
				resizeLayout();
			});
			$(window).bind('resize',function(){
				resizeLayout();
			});
		});
		function resizeLayout() {
			$("#menuTreeDiv").height($(document).height()-60);
		}
	</script>
</head>

<body>
	<div id="accordion" >
		<h3><a href="#">菜单树</a></h3>
		<div id="menuTreeDiv"style="padding-left:0;margin:0">
			<ul id="navigation">
			</ul>
		</div>
	</div>
</body>
</html>
