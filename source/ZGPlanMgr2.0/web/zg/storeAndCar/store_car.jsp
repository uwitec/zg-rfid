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
	<script type="text/javascript" src="${ctx}/dwr/interface/MaterielMaintenanceDwrAction.js"></script>
	<script src="${ctx}/scripts/URLEncoder.js" type=""></script>
	<script src="${ctx}/dwr/interface/ExplorerTreeAction.js" type=""></script>
	<script src="${ctx}/dwr/interface/ZgMaterielDwrAction.js" type=""></script>
	<script type="text/javascript">
		var navTreePanel = null;
		var orgId=null;
		var parentId=null;
		var orgName =null;
		var obj=null;
		var whetherParent=0;
		var leafFlage=new Boolean()
		leafFlage=false;
		var parentName=null;
		var level=0;
		Ext.onReady(function() {
			
			var navTreePanel = new DRM.NTTreePanel({
				title		: '物料组导航',
				region : 'west',
				width:200,
				templateId	:	'store_car',
				isInit		: true,
				tools:[
					{id:'plus',
						handler:function(){
							if(orgId==null){
								alert("选择仓库或物料组才能增加物料组!");
							}else if(orgId!='root'){//不是根时,增加的是物料组　
								var sFeatures="dialogHeight: 300px;dialogWidth:550px";
							    var firstShowQueryUrl="${ctx}/zg/materiel/ZgMateriel/addMateriel.do?parentOrgId="+orgId+"&parentOrgName="+URLEncoder.encode(orgName);		
								var returnValue=window.showModalDialog(firstShowQueryUrl,'',sFeatures);
								var materielName=returnValue.materielName;
								var cuid=returnValue.cuid;
								if(cuid!=null){
									if(leafFlage==false){
										obj.node.appendChild({
											text:materielName,
											//要把节点的cuid带过来
											leaf:true,
											cuid:cuid,
											bmClassId:'ZG_MATERIEL'
										});
									}else{
									//	obj.node.attributes.leaf=false;
										// 处理此节点下无子节点时，增加子节点时
										obj.node.leaf = false;
										obj.node.appendChild({
											text:materielName,
											leaf:true,
											cuid:cuid,
											expanded:true,
											bmClassId:'ZG_MATERIEL'
										});
										obj.node.lastChild.remove();
										obj.node.expand();
									}
									alert('新增成功!');
									org=null;
								}else{
									alert('新增失败!');							
								}
							}else{
								alert("选择仓库或物料组才能增加物料组!");
							}
						}
					},
					{id:'close',
						handler:function(){
							if(level==1) {alert("不能删除一级物料组!");return;}
							DWREngine.setAsync(false);
						//	ZgMaterielDwrAction.hasBom(orgId,function(result){
								//if(!result){
									DWREngine.setAsync(false);
									ZgMaterielDwrAction.hasBom(orgId,function(dataFlag){
										if(!dataFlag){//dataflag为true的时候不存在数据可以删除
											if(confirm("确认删除此物料组?")){
												ZgMaterielDwrAction.delMateriel(orgId);
												orgId=null;
												obj.node.remove();
												obj=null;
											}
										}else{
											alert("该物料组下有相关数据，不能删除!");
										}
									});
							//	}else{
							//		alert("必须先删除上层物料组才能删除该物料组!");
							//	}
							//});
						}
					},
					{id:'gear',
						handler:function(){
							//if(level==1) {alert("不能编辑一级物料组!");return;}
							DWREngine.setAsync(false);
							ZgMaterielDwrAction.isLorgNode(orgId,function(lorgNode){
								if(lorgNode!=false&&orgId!=null&&obj!=null){
									var sFeatures="dialogHeight: 300px;dialogWidth:550px";
									var firstShowQueryUrl="${ctx}/zg/materiel/ZgMateriel/editMateriel.do?id="+orgId+"&orgName="+URLEncoder.encode(orgName)+"&parentName="+URLEncoder.encode(parentName)+"&level="+level;
									var returnValue=window.showModalDialog(firstShowQueryUrl,'',sFeatures);
									if(returnValue!=null){
										if(returnValue.parentId==obj.node.parentNode.attributes.cuid){
											obj.node.setText(returnValue.materielName);
										}else{
											if(level==1) return;
										//找到父节点，并把原来的节点前台删除，在父节点后加载一个节点
											obj.node.setText(returnValue.materielName);
											var name=returnValue.materielName;
											var cuid=returnValue.parentId;
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
										//			alert(nodes[i].attributes.text);
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
								}else{
									alert("选择物料组才能编辑!");
								}
							});
						}
					} 
				]
			});
			var bodyPanel = new Ext.Panel({
				region : 'center',
				html : '<iframe id="storeCar" width="100%" height="100%" frameborder="0" src=""></iframe>'
			});
			navTreePanel.on("click",function(node,e){
				if(node.id=="store_car-root"){
					orgId='root';
				}
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
				if(node.id=="store_car-root"){
					storeCar.document.getElementById("s_orgId").value="";
					storeCar.document.getElementById("s_parentOrgId").value="";
					storeCar.document.getElementById("s_carName").value="";
					storeCar.document.getElementById("s_storeName").value="";
					storeCar.document.getElementById("s_materielGroupName").value="";
					storeCar.document.getElementById("s_idnrk").value="";	
					storeCar.document.getElementById("s_carId").value="";
					storeCar.document.getElementById("s_bomCarState").value="8";					
					
					storeCar.document.getElementById("form1").submit();
				}
			});
			
			
			navTreePanel.on("_click",function(orgArgs, extArgs){
				level=orgArgs.node.getDepth() ;
				obj=orgArgs;
				orgId = orgArgs.node.attributes.cuid;
				var parentOrgId = orgArgs.node.parentNode;//父节点
				parentId=orgArgs.node.parentNode.attributes.cuid;
				parentName=orgArgs.node.parentNode.attributes.text;
				orgName = orgArgs.node.attributes.text;
				//这里为了拿到所点节点的最上层仓库ID
				if(parentOrgId!='[Node store_car-root]'){
					while(parentOrgId.parentNode!='[Node store_car-root]'){
						parentOrgId=parentOrgId.parentNode;
					}
				}
				parentOrgId=parentOrgId.attributes.cuid;//最上层仓库ID
				//这里因为最上层的父节点是没有ID的，所以要判断是否定义了
				//如果父节点没有定义的话，就证明是 仓库 ，点击仓库的时候，是不会去激发右边的窗体的
				//if(typeof(parentOrgId)=='undefined'){
				//	return;
				//}
				//把子页的隐藏表单域也改变了
				storeCar.document.getElementById("s_orgId").value=orgId;
				if(typeof(parentOrgId)=='undefined'){
					storeCar.document.getElementById("s_parentOrgId").value="";
				}else{
					storeCar.document.getElementById("s_parentOrgId").value=parentOrgId;
				}				
				//这里去改变右上放的条件&&刷新右下方的grid
				storeCar.document.getElementById("s_storeName").value=orgArgs.node.parentNode.attributes.text;//父节点名称
				storeCar.document.getElementById("s_materielGroupName").value=orgName;//本节点名称			
				//2.刷新右下方的grid
				storeCar.document.getElementById("form1").submit();
			});
			var viewport = new Ext.Viewport({
			    layout:"border",
			    items:[navTreePanel,bodyPanel]
			});
			top.bodyFrame.document.getElementById("storeCar").src = '${ctx}/frame/sys/ZgBomCar/queryForStoreCar.do';
		});
	</script>

  </head>
  
  <body>
	
  </body>
</html>
