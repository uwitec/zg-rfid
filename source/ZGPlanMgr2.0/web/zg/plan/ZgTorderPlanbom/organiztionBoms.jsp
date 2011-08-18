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
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
	<script type="text/javascript">
		var orderPlanId = '${orderPlanId}';
		var navTreePanel = null;
		var lgortText;
		var lgort;
		Ext.onReady(function() {
			var navTreePanel = new DRM.NTTreePanel({
				region : 'west',
				width:300,
				templateId	:	'carplan_create_tree_lv1',
				isInit		: true
			});
			var bodyPanel = new Ext.Panel({
				region : 'center',
				html : 	'<iframe id="employee"  src="" name="employee" frameborder="0" width="100%" height="100%" autolayout="true" scrolling="no"></iframe>'
			});
			navTreePanel.on("_click",function(orgArgs, extArgs){
				lgortText = orgArgs.node.attributes.text;
				lgort = orgArgs.node.attributes.cuid;
				var bomId="";
				if(orgArgs.node.isLeaf()){
					bomId = orgArgs.node.attributes.cuid;
					lgort = orgArgs.node.parentNode.attributes.cuid;
					lgortText = orgArgs.node.parentNode.attributes.text;
				}
				document.getElementById("employee").src = '${ctx}/zg/plan/ZgTcarplan/findBomListByLgort.do?lgort='+lgort+'&orderPlanId='+orderPlanId+'&bomId='+bomId;
			});
			var toolbar = new Ext.Panel({
				region		: 'north',
				contentEl	: 'toolbar',
				height		: 30
			});
			var viewport = new Ext.Viewport({
			    layout:"border",
			    items:[navTreePanel,bodyPanel,toolbar]
			});
			doLayout() ;
		});
		
		function doLayout() {
			var maxHeight = top.getContentHeight()-140;
			document.getElementById("employee").style.height = maxHeight + 'px';
		}
		
		function bomSubmit(){
			if(window.frames['employee'].viewPlanOrderBom){
				window.frames['employee'].viewPlanOrderBom(orderPlanId);
			}else{
				window.location.href="${ctx}/zg/plan/ZgTorderPlanbomForBatch/list.do?orderPlanId="+orderPlanId+"&flag=temp";
			}
			
		}
	</script>

  </head>
  	<div id="toolbar">
  	<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a
										href="javascript:bomSubmit();"><span><img
												src="<%=iconPath%>/true.gif" />完成</span> </a> &nbsp;
								</div>
							</td>
						</tr>
					</thead>
				</table>
  	</div>
  
  <body>
	
  </body>
</html>
