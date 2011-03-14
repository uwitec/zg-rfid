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
	<script src="${ctx}/dwr/interface/FwEmployeeAction.js" type=""></script>
	<script type="text/javascript">
		var navTreePanel = null;
		var checkIds =  '${param.orgIds}';
		var checkIdArray = checkIds.split(",");
		//alert(checkIdArray);
		Ext.onReady(function() {
			var bodyPanel = new Ext.Panel({
				region : 'north',
				height : 30,
				contentEl : 'north'
			});
			var navTreePanel = new DRM.NTTreePanel({
				region : 'center',
				templateId	:'${param.templateId}',
				isInit		: true,
				showChecked : true,
				checkIds	: checkIdArray,
				isExpandAll	: true//展开全部节点
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
			
			//用到的
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
   		//增加checkchange监听
   		navTreePanel.on('checkchange', function(node, checked) {
	            var parentNode = node.parentNode;
	            if(parentNode !== null) {   
	                 // parentCheck(parentNode,checked);   
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
				text	: '提交'+'${param.partmentValue}'
			});
			var buttonBack = new Ext.Button({
				text	: '返回'
			});
			button.on('click',function(){
				var treeIds = '';
				selNodes = navTreePanel.getChecked();
			    var id='';
			    var label='';
				for(var i=0;i<selNodes.length;i++){
					id = id+','+selNodes[i].attributes.cuid;
					label = label+','+selNodes[i].attributes.text;
					//if(i==selNodes.length-1){
					//	treeIds = treeIds+id;
					//}else{
					//	treeIds = treeIds+id+",";
					//}
				}
					var obj=new Array();
					obj.id=id;
					obj.label=label;
					window.returnValue=obj;
					window.close();
			
			});
			
			buttonBack.on('click',function(){
							window.close()
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
		</div>
  </body>
</html>
