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
				//title		: '物料组导航',
				//region : 'west',
				autoWidth:true,
				autoHeight:true,
				templateId	:	'store_car',
				isInit		: true
			});
			
			navTreePanel.on("_click",function(orgArgs, extArgs){
				var orgId = orgArgs.node.attributes.cuid;
				var parentOrgId = orgArgs.node.parentNode;//.attributes.cuid;//父节点的ID
				var orgName = orgArgs.node.attributes.text;
				//这里为了拿到所点节点的最上层仓库ID
				if(parentOrgId!='[Node store_car-root]'){
					//这里的循环是用来拿到最上层仓库的，这里是考虑到有多级的
					while(parentOrgId.parentNode!='[Node store_car-root]'){
						parentOrgId=parentOrgId.parentNode;
					}
				}
				var parentId=parentOrgId.attributes.cuid;//最上层仓库ID
				var parentName=parentOrgId.attributes.text;//最上层仓库的名字
				var realStorageName;//真正的仓库名字 
				//这里因为最上层的父节点是没有ID的，所以要判断是否定义了
				//如果父节点没有定义的话，就证明是 仓库 ，点击仓库的时候，是不会去激发右边的窗体的
				if(typeof(parentId)=='undefined'){
					//进了这里证明是点击仓库节点
					realStorageName=orgName;
					parentId=orgId;
				}else{
					realStorageName=parentName;
				}
				var obj=new Object();
				obj.orgId=orgId;
				obj.orgName=orgName;
				obj.parentId=parentId;
				obj.realStorageName=realStorageName;
				window.returnValue=obj;
				window.close();
				/*
				//把子页的隐藏表单域也改变了
				storeCar.document.getElementById("s_orgId").value=orgId;
				storeCar.document.getElementById("s_parentOrgId").value=parentOrgId;
				
				//这里去改变右上放的条件&&刷新右下方的grid
				storeCar.document.getElementById("s_storeName").value=orgArgs.node.parentNode.attributes.text;//父节点名称
				storeCar.document.getElementById("s_materielGroupName").value=orgName;//本节点名称
				
				//2.刷新右下方的grid
				storeCar.document.getElementById("form1").submit();
				*/
			});
			
			var viewport = new Ext.Viewport({
			    //layout:"border",
			    border:false,
			    autoWidth:true,
				autoHeight:true,
			    autoScroll:true,
			    items:[navTreePanel]
			});
			//top.bodyFrame.document.getElementById("storeCar").src = '${ctx}/frame/sys/ZgBomCar/queryForStoreCar.do';
		});
	</script>

  </head>
  
  <body>
	
  </body>
</html>
