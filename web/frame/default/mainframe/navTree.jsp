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
	<script src="${ctx}/dwr/interface/CommonDwrAction.js" type="text/javascript"></script>
	<script type="text/javascript">
		var navTreePanel = null;
		var path = [];
		Ext.onReady(function() {
			var navtree="navtree";
			if(''!='${loginLgort}'){
				navtree="carPlanTree";
			}
			var navTree = new DRM.NTTreePanel({
				region : "west",
				width : 220,
				title : '系统导航',
				templateId : navtree,
				collapsible : true,
				collapseMode : 'mini',
				frame : false,
				isInit : true,
				listeners : {
					collapse : function(panel){
						window.parent.centerFrame.cols='5,*';
						if(window.parent.bodyFrame.window.resizeWidth){
							window.parent.bodyFrame.window.resizeWidth();
						}
						
					},
					beforeexpand : function(panel){
						window.parent.centerFrame.cols='220,*';
						if(window.parent.bodyFrame.window.resizeWidth){
							window.parent.bodyFrame.window.resizeWidth();
						}
					}
				}
			});
			navTree.on("_click",function(orgArgs, extArgs){
				try {
				path = new Array();
				path.push(orgArgs.node.attributes.text);
				getParentNodeName(orgArgs.node,orgArgs.node.attributes.text);
				
				path = path.reverse();
				top.headFrame.setPath("当前路径："+path.join("->"));
				path = null;
				}catch(e){}
				//CommonDwrAction.getCurrentPath(orgArgs.node.attributes.cuid,function(path){
					//top.headFrame.document.getElementById("path").innerHTML = path;
				//});
				var url = orgArgs.node.attributes.nodeValue.URL;
				if(url)
					top.bodyFrame.location.href = '${ctx}/'+url;
			});
			
			function getParentNodeName(node){
				if(node.parentNode!=null && typeof(node.parentNode)!=undefined){
					var parentNode = node.parentNode;
					var name = parentNode.attributes.text;
					path.push(name);
					//var path = name +","+nodeName;
					getParentNodeName(parentNode);
				}
			}
			
			var emptyPanel = new Ext.Panel({
				region : "center"
			});
			
			var navPanel = new Ext.Panel({
				layout : "border",
				items : [navTree,emptyPanel]
			});
			if('${isLLY}'=='true'){
				navTree.collapsed=true;
			}
			var viewport = new Ext.Viewport({
			    layout:"fit",
			    items:[navPanel]
			});
		});
		
		
	</script>
	
	
	
</head>

<body>
</body>
</html>
