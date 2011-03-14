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
	<script src="${ctx}/scripts/URLEncoder.js" type=""></script>
	<script src="${ctx}/dwr/interface/ZgTvirtualorgDwrAction.js" type=""></script>
	<script type="text/javascript">
		var navTreePanel = null;
		var orgId=null;
		var orgName=null;
		var parentOrgId =null;
		var whetherParent=null;
		var parentOrgName=null;
		var obj=null;
		var leafFlage=new Boolean()
		leafFlage=false; 
		Ext.onReady(function() {
			
			var navTreePanel = new DRM.NTTreePanel({
				title		: '领料组导航',
				region : 'west',
				width:200,
				templateId	:	'dummy_team_tree',
				isInit		: true ,
				tools : [
			    {id:'plus',
			    	handler:function(){
			    		var sFeatures="dialogHeight: 300px;dialogWidth:550px";
			    		if(orgId!=null&&whetherParent==0){
							var firstShowQueryUrl="${ctx}/zg/virtualorg/ZgTvirtualorg/addVirtualorg.do?orgId="+orgId+"&orgName="+URLEncoder.encode(orgName);
							var returnValue=window.showModalDialog(firstShowQueryUrl,'',sFeatures);
							var cuid=returnValue.cuid;
							var labelCn=returnValue.labelCn;
							if(cuid!=null){
								obj.node.appendChild({
									text:labelCn,//labelCn 带过来
									//要把节点的cuid带过来
									leaf:true,
									cuid:cuid,
									bmClassId:'ZG_T_VIRTUALORG'
								});
								alert('新增成功!');
							}else{
								alert('新增失败!');							
							}
						}else{
							alert("必须选择部门才能增加!");
						}
			    	}
			    }, 
			    {id:'close',
			   		 handler:function(){
				   		 	if(orgId==null||whetherParent==0){
				   		 		alert("必须选择领料组才能删除！");
				   		 	}
				   		 	else{ 
				   			 	//查询是否有与此节点相关的数据
				   			 	DWREngine.setAsync(false);
				   			 	ZgTvirtualorgDwrAction.findZgTvirtualorgData(orgId,function(data){
					   		 		if(data){
					   		 			if(confirm('确定删除此节点?')){
					   		 				//window.location.href="${ctx}/zg/virtualorg/ZgTvirtualorg/delVirtualorg.do?orgId="+orgId;
					   		 				ZgTvirtualorgDwrAction.delVirtualorg(orgId);
					   		 				orgId=null;
					   		 				obj.node.remove();
					   		 				obj=null;
					   		 			}
					   		 		}
						   		 	else{
					   		 			alert("此领料组下有相关数据，不能删除！");
					   		 		}
				   			 	});
				   			 	
				    	}
			    	}
			    }, 
			    {id:'gear',
			    	handler:function(){
			    		if(typeof(parentOrgId)=='undefined'||orgId==null||obj==null){
			    			alert("必须选择领料组才能编辑！");
			    		}
			    		else{
				    		var sFeatures="dialogHeight: 300px;dialogWidth:550px";
				    		var firstShowQueryUrl="${ctx}/zg/virtualorg/ZgTvirtualorg/editVirtualorg.do?cuid="+orgId+"&orgName="+URLEncoder.encode(parentOrgName);
				    		var returnValue=window.showModalDialog(firstShowQueryUrl,'',sFeatures);
				    		if(returnValue!=null){
				    			if(returnValue.orgId==obj.node.parentNode.attributes.cuid){
				    				obj.node.setText(returnValue.labelCn);
				    			}else{
				    				obj.node.setText(returnValue.labelCn);
				    				var cuid=returnValue.orgId;
				    				
				    				var secondNodes=navTreePanel.getRootNode().childNodes;
									var flag=false;
									//遍历树找到新的父节点 然后把节点转过去
									var confirmFind= findNode(secondNodes,cuid);
									function findNode(nodes,cuid){
										for(var i=0;i<nodes.length;i++){
											if(nodes[i].isLeaf()==false){
												flag=findNode(nodes[i].childNodes,cuid);
												if(flag==true){
													return true;
												}
											}
											if(cuid==nodes[i].attributes.cuid){
								//				alert('找到了');
												nodes[i].appendChild(obj.node);//关键句子就是把这个节点转移
											    if(nodes[i].isLeaf()||nodes[i].childNodes.length==1){
												//对于叶子节点和没有展开过的二级节点 展开的时候后台的节点会加上来，前台加上，就会有2个。故删掉一个
													nodes[i].lastChild.remove();
												}
												nodes[i].leaf=false;
												nodes[i].expand();
												return true;
											}
										}
									}
									if(typeof(confirmFind)=='undefined'){//对于叶子节点和没有展开过的二级节点,前台找不到节点，故得删掉前台，展开式会加载后台的
										obj.node.remove();
									}
									obj=null;
									orgId=null;
									flag=false;
						    	}
							}
			    		}
			    	}
			    }	 
			   	]
			});
			
			var bodyPanel = new Ext.Panel({
				region : 'center',
				html : '<iframe id="dummyTeam" width="100%" height="100%" frameborder="0" src=""></iframe>'
			});
			navTreePanel.on("click",function(node,e){
				if(node.isLeaf()){
					leafFlage=true;
				}else{
					leafFlage=false;
					node.expand();
				}
			//	alert(leafFlage);
			});
			
			//这里用来判断是否根节点的
			navTreePanel.on("click",function(node,e){
				//点击根节点的时候，就显示全部数据
				if(node.id=="dummy_team_tree-root"){
					dummyTeam.document.getElementById("whichTab").value="0";
					dummyTeam.document.getElementById("whetherParent").value="";
					dummyTeam.document.getElementById("treeCuid").value="";
					dummyTeam.document.getElementById("virtualorgId").value="";	
					dummyTeam.document.getElementById("s_labelCn").value="";
					dummyTeam.document.getElementById("s_orgId").value="";
					dummyTeam.document.getElementById("s_orgName").value="";
					dummyTeam.document.getElementById("s_userId").value="";
					dummyTeam.document.getElementById("s_userName").value="";
					dummyTeam.document.getElementById("s_sex").value="0";					
					
					dummyTeam.document.getElementById("formForEmployee").action="${ctx}/zg/virtualorg/ZgTvirtualorg/list.do";
					dummyTeam.document.getElementById("formForEmployee").submit();
						
					dummyTeam.document.getElementById("formForMateriel").action="${ctx}/zg/materiel/ZgMateriel/list.do";
					dummyTeam.document.getElementById("formForMateriel").submit();
				}
			});
			
			
			navTreePanel.on("_click",function(orgArgs, extArgs){
				obj=orgArgs;
				orgId = orgArgs.node.attributes.cuid;
			//	alert(orgId);
				parentOrgId = orgArgs.node.parentNode.attributes.cuid;//父节点的ID
				parentOrgName=orgArgs.node.parentNode.attributes.text;
				//用于树的维护
			    orgName = orgArgs.node.attributes.text;
			 //   alert(orgId+"  "+parentOrgId+"  "+orgName+"  "+parentOrgName);
				
				whetherParent=0;//0就是点击1级节点，1就是点击2级节点
				if(typeof(parentOrgId)!='undefined'){//如果没有定义，证明是第一级节点
					whetherParent=1;
				}
				
				dummyTeam.document.getElementById("whetherParent").value=whetherParent;
				dummyTeam.document.getElementById("treeCuid").value=orgId;
				//这里为了拿到所点节点的最上层仓库ID
				//if(parentOrgId!='[Node store_car-root]'){
				//	while(parentOrgId.parentNode!='[Node store_car-root]'){
				//		parentOrgId=parentOrgId.parentNode;
				//	}
				//}
				//parentOrgId=parentOrgId.attributes.cuid;//最上层仓库ID
				//这里因为最上层的父节点是没有ID的，所以要判断是否定义了
				//如果父节点没有定义的话，就证明是 仓库 ，点击仓库的时候，是不会去激发右边的窗体的
				//if(typeof(parentOrgId)=='undefined'){
				//	return;
				//}
				//把子页的隐藏表单域也改变了
				//storeCar.document.getElementById("s_orgId").value=orgId;
				//storeCar.document.getElementById("s_parentOrgId").value=parentOrgId;
				
				//这里去改变右上放的条件&&刷新右下方的grid
				//storeCar.document.getElementById("s_storeName").value=orgArgs.node.parentNode.attributes.text;//父节点名称
				//storeCar.document.getElementById("s_materielGroupName").value=orgName;//本节点名称
				
				if(whetherParent==1){
					dummyTeam.document.getElementById("virtualorgId").value=orgId;
					
				}else{
					dummyTeam.document.getElementById("virtualorgId").value="";
				}
				
				
				
				var whichTab=dummyTeam.document.getElementById("whichTab").value;
				//if(whichTab=="0"){
					if(whetherParent==0){
						dummyTeam.document.getElementById("s_labelCn").value="";
					
						dummyTeam.document.getElementById("s_orgId").value=orgId;
						dummyTeam.document.getElementById("s_orgName").value=orgName;
					}else{
						dummyTeam.document.getElementById("s_labelCn").value=orgName;
						
						dummyTeam.document.getElementById("s_orgId").value=parentOrgId;
						dummyTeam.document.getElementById("s_orgName").value=parentOrgName;
					}
					
					
					
				//}else if(whichTab=="1"){
					if(whetherParent==0){
						dummyTeam.document.getElementById("s_virtualorgName").value="";
					
						dummyTeam.document.getElementById("s_organizationId").value=orgId;
						dummyTeam.document.getElementById("s_organizationName").value=orgName;
					}else{
						dummyTeam.document.getElementById("s_virtualorgName").value=orgName;
						
						dummyTeam.document.getElementById("s_organizationId").value=parentOrgId;
						dummyTeam.document.getElementById("s_organizationName").value=parentOrgName;
					}
				
					
				//}
				dummyTeam.document.getElementById("formForEmployee").action="${ctx}/zg/virtualorg/ZgTvirtualorg/list.do?whetherParent="+whetherParent+"&treeCuid="+orgId;
				dummyTeam.document.getElementById("formForEmployee").submit();
					
				dummyTeam.document.getElementById("formForMateriel").action="${ctx}/zg/materiel/ZgMateriel/list.do?whetherParent="+whetherParent+"&treeCuid="+orgId;
				dummyTeam.document.getElementById("formForMateriel").submit();
			});
			
			var viewport = new Ext.Viewport({
			    layout:"border",
			    items:[navTreePanel,bodyPanel]
			});
			top.bodyFrame.document.getElementById("dummyTeam").src = '${ctx}/zg/virtualorg/ZgTvirtualorg/query.do';
		});
	</script>

  </head>
  
  <body>
  
  </body>
</html>
