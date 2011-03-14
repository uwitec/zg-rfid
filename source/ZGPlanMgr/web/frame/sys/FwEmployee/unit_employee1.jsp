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
	<script src="${ctx}/dwr/interface/ExplorerTreeAction.js" type=""></script>
	<script type="text/javascript">
		var navTreePanel = null;
		var code='code';
		Ext.onReady(function() {
			var navTreePanel = new DRM.NTTreePanel({
				title		: '机构导航',
				region : 'west',
				width:200,
				templateId	:	'1',
				isInit		: true
			});
			var bodyPanel = new Ext.Panel({
				region : 'center',
				html : '<iframe id="employee" width="100%" height="100%" frameborder="0" src=""></iframe>'
			});
			
			//这里用来判断是否根节点的
			navTreePanel.on("click",function(node,e){
				//点击根节点的时候，就显示全部数据
				if(node.getDepth()==0){//根结点
					var orgName = node.attributes.text;
					top.bodyFrame.document.getElementById("employee").src = '${ctx}/frame/sys/FwEmployee/query.do?orgId=';
				}
			});
			
			navTreePanel.on("_click",function(orgArgs, extArgs){
				var orgId = orgArgs.node.attributes.cuid;
				var orgName = orgArgs.node.attributes.text;
				top.bodyFrame.document.getElementById("employee").src = '${ctx}/frame/sys/FwEmployee/query.do?orgId='+orgId;
			});
			var viewport = new Ext.Viewport({
			    layout:"border",
			    items:[navTreePanel,bodyPanel]
			});
		});
	</script>

  </head>
  
  <body>
	
  </body>
</html>
