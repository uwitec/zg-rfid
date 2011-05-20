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
	<script type="text/javascript" src="${ctx}/explorer/tree/NavTreePanel.js"></script>
	<style>
		body,html{margin:0;padding:0}
	</style>
	<script type="text/javascript">
		var navTreePanel = null;
		Ext.onReady(function() {
			var navTree = new DRM.NTTreePanel({
				title		: '机构导航',
				templateId	: '1',
				isInit		: true
			});
			
			//这里用来判断是否根节点的
			navTree.on("click",function(node,e){
				if(node.getDepth()==0){
					var orgId = '';
					var url = "frame/sys/FwOrganization/query.do?parentOrgId="+orgId;
					if(url)
						top.bodyFrame.document.getElementById("queryFrame").src = '${ctx}/'+url;
				}
			});
			
			navTree.on("_click",function(orgArgs, extArgs){
				var orgId = orgArgs.node.attributes.cuid;
				var url = "frame/sys/FwOrganization/query.do?parentOrgId="+orgId;
				if(url)
					top.bodyFrame.document.getElementById("queryFrame").src = '${ctx}/'+url;
			});
			var viewport = new Ext.Viewport({
			    layout:"fit",
			    items:[navTree]
			});
		});
	</script>
</head>
<body>
</body>
</html>
