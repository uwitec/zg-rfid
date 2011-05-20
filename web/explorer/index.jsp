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
	<title>NavTree</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/ext.jsp" %>
	<script src="${ctx}/dwr/interface/ExplorerTreeAction.js" type=""></script>
	<script src="${ctx}/dwr/interface/ContextMenuAction.js" type=""></script>
	<script type="text/javascript" src="${ctx}/explorer/ExplorerFramePanel.js"></script>
	<script type="text/javascript">
		Ext.onReady(function() {
			var navTreeClickType = 'click';
			var explorer = new DRM.ExplorerFramePanel({
				/*hasResGrid	: true,  //包含资源维护
				hasResObject: false, //包含资源对象
				hasResView	: true,  //包含资源视图
				hasResReport: true,   //包含资源报表*/
				navTreeClickType : navTreeClickType
			});
			
			//explorer.addTabToRes("sdfsd","123123");
			var viewport = new Ext.Viewport({
			    layout	: "fit",
			    items	: [explorer]
			});
		});
	</script>
</head>
<body>
</body>
</html>
