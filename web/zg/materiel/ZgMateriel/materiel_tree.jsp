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
		Ext.onReady(function() {
			var navTreePanel = new DRM.NTTreePanel({
				templateId	:	'store_car',
				isInit		: true
			//	showChecked : '${param.showChecked}'
			});
			navTreePanel.on("_click",function(orgArgs, extArgs){
				window.returnValue = {id:orgArgs.node.attributes.cuid,label:orgArgs.node.attributes.text,depth:orgArgs.node.getDepth()};
				window.close();
			});
			navTreePanel.on("_getCheckedNodes",function(selNodes){
				var msg = '';
				Ext.each(selNodes, function(node){
                    if(msg.length > 0){
                        msg += ', ';
                    }
                    msg += '('+node.attributes.cuid+':'+node.text+')';
                });
                Ext.Msg.show({
                    title: '选中的节点信息', 
                    msg: msg.length > 0 ? msg : '无节点选中',
                    icon: Ext.Msg.INFO,
                    minWidth: 200,
                    buttons: Ext.Msg.OK
                });
			});
			var viewport = new Ext.Viewport({
			    layout:"fit",
			    items:[navTreePanel]
			});
		});
	</script>

  </head>
  
  <body>
  		<div id="north">
		</div>
  </body>
</html>
