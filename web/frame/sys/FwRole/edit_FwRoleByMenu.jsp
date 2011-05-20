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
	<script src="${ctx}/dwr/interface/FwRoleAction.js" type=""></script>
	<script type="text/javascript">
		var navTreePanel = null;
		var checkIds =  '${menuIds}';
		var checkIdArray = checkIds.split(",");
		Ext.onReady(function() {
			var bodyPanel = new Ext.Panel({
				region : 'north',
				height : 30,
				contentEl : 'north'
			});
			var navTreePanel = new DRM.NTTreePanel({
				region : 'center',
				templateId	:	'5',
				isInit		: true,
				showChecked : true,
				checkIds	: checkIdArray,
				isExpandAll	: true
			});
			
			navTreePanel.on('append', function(navTreePanel, node){ 

		    });
		    
		    navTreePanel.on("expandnode", function(singelNode) { 

			}, this); 
		    
		    
		    //绑定节点加载之前事件
		   navTreePanel.on('beforeload',function(node){   
	
		   }); 
		    
    
			var childHasChecked = function(node)
       		{
              	var childNodes = node.childNodes;
        		if(childNodes || childNodes.length>0){
	        		for(var i=0;i<childNodes.length;i++){
	            		if(childNodes[i].getUI().checkbox.checked)
	                	return true;
	            	}
        		}
             	return false;
         	} 

 			var parentCheck = function(node ,checked){
        		var checkbox = node.getUI().checkbox;
        		if(typeof checkbox == 'undefined')
            		return false;
        		if(!(checked ^ checkbox.checked))
            		return false;
        		if(!checked && childHasChecked(node))
            		return false;
        		checkbox.checked = checked;
               	node.attributes.checked = checked;
               	node.getUI().checkbox.indeterminate = checked; //半选中状态
        		node.getOwnerTree().fireEvent('check', node, checked);
				var parentNode = node.parentNode;
        		if( parentNode !== null){
            		parentCheck(parentNode,checked);
        		}
   			 }
    
    	//增加checkchange监听
   		navTreePanel.on('checkchange', function(node, checked) {
	            var parentNode = node.parentNode;
	            if(parentNode !== null) {   
	                  parentCheck(parentNode,checked);   
	            }
          	 	//node.expand();
           		node.attributes.checked = checked;     
				node.eachChild(function(child) {     
                child.ui.toggleCheck(checked);    
                child.attributes.checked = checked;     
                child.fireEvent('checkchange', child, checked);

           });     

   	}, navTreePanel);

 

    //获取新增复选框树的值
    function onItemClick(){
		var checkedNodes = DRM.NTTreePanel.getChecked();//tree必须事先创建好.这里要标识识获取那个treepanel的 id
        var checkedIds = [];
        for(var i=0;i<checkedNodes.length;i++)
        {
			if( checkedNodes[i].hasChildNodes()){
                //这里只是获取节点为子节点的id ，如果不需要可以删除。
            }else{
                checkedIds.push(checkedNodes[i].id);
            }           
        }
        return checkedIds.join(',');

    }; 
			var button = new Ext.Button({
				text	: '提交'
			});
			var buttonBack = new Ext.Button({
				text	: '返回'
			});
			button.on('click',function(){
				var treeIds = '';
				selNodes = navTreePanel.getChecked();
				for(var i=0;i<selNodes.length;i++){
					var id = selNodes[i].attributes.cuid;
					if(i==selNodes.length-1){
						treeIds = treeIds+id;
					}else{
						treeIds = treeIds+id+",";
					}
				}
				var labelCn = document.getElementById("labelCn").value;
				var cuid = document.getElementById("cuid").value;
				FwRoleAction.updateRoleByTree(cuid,labelCn,treeIds,function(_b){
					if(_b){
						Ext.Msg.alert('提示','修改成功!',function(){
							//window.location.href = "${ctx}/frame/sys/FwRole/list.do";
							if(parent.doQuery)parent.doQuery()
						});
					}
				});
			});
			
			buttonBack.on('click',function(){
							if(parent.doQuery)parent.doQuery()
			});
			
			//navTreePanel._privateExpand();
			var mainPanel = new Ext.Panel({
				layout:"border",
				region : 'center',
				buttons:[button,buttonBack],
			    items:[bodyPanel,navTreePanel]
			});
			var viewport = new Ext.Viewport({
			    layout:"border",
			    items:[mainPanel]
			});
		});
	</script>

  </head>
  
  <body>
  		<div id="north">
<table><input type="hidden" id="cuid" value="${model.cuid}" /><tr bgcolor="#FFFFFF"><td class="tdLabel"><span class="alterlabel">*</span>权限名称:</td>	<td><input type="text" size="30" maxlength="80" name="labelCn" id="labelCn"  value="${model.labelCn}" class="required"/></td></tr></table>		</div>
	
  </body>
</html>
