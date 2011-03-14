<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>
	<head>
		<base href="<%=basePath%>" />
		<title>NavTree</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/ext.jsp"%>
		<script type="text/javascript"
			src="${ctx}/explorer/tree/NavTreePanel.js"></script>
		<script src="${ctx}/dwr/interface/ExplorerTreeAction.js" type=""></script>
		<script src="${ctx}/dwr/interface/FwRoleAction.js" type=""></script>
		<script src="<c:url value="/scripts/application.js"/>" type="text/javascript"></script>
		<script type="text/javascript">
		var navTreePanel = null;
		Ext.onReady(function() {
			var bodyPanel = new Ext.Panel({
				region : 'north',
				contentEl : 'north',
				heigth :30
			});
			var navTreePanel = new DRM.NTTreePanel({
				region : 'center',
				templateId	:	'5',
				isInit		: true,
				showChecked : true,
				isExpandAll	: true
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
				FwRoleAction.saveRoleByTree(labelCn,treeIds,function(_b){
					if(_b){
						Ext.Msg.alert('提示','新增成功!',function(){
							//alert(parent.doQuery);
							//window.location.href = "${ctx}/frame/sys/FwRole/list.do";
							if(parent.doQuery)parent.doQuery()
						});
					}
				});
			});
			buttonBack.on('click',function(){
							if(parent.doQuery)parent.doQuery()
			});
			var mainPanel = new Ext.Panel({
				layout:"border",
				buttons:[button,buttonBack],
			    items:[bodyPanel,navTreePanel]
			});
			var viewport = new Ext.Viewport({
			    layout:"fit",
			    items:[mainPanel]
			});
		});
	</script>

	</head>

	<body>
		<div id="north">
			<table><tr bgcolor="#FFFFFF"><td class="tdLabel"><span class="alterlabel">*</span>权限名称:</td>	<td><input type="text" maxlength="80" value="" size="30" name="labelCn" id="labelCn" class="required"/></td></tr></table>
		</div>
	</body>
</html>
