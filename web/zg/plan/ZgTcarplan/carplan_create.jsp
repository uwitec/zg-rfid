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
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/explorer/tree/NavTreePanel.js"></script>
		<script src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js" type="text/javascript"></script>
			<style>
	</style>
		<script type="text/javascript">
		var storeTree;
		Ext.onReady(function() {
			initTimeSpanView();
			storeTree = new DRM.NTTreePanel({
				templateId	: '${treeTemplateId}',
				templateName: '仓库',
				isInit		: true,
				region		: 'west',
				width		: 200,
				treeObject  : {
					relatedTreeTemplateCuid : 	'${treeTemplateId}',
					boName					:	'sqlTreeBo',
					templateName			:	'仓库',
					treeParams:{
						s_pxdat_Start : '${ pxdat_Start}',
						s_pxdat_End : '${ pxdat_End}'
					}
				}
			});
			
			
			storeTree.on("_click",function(orgArgs, extArgs){
				//判断仓库号是否相同
				//思路：相同的话，就不用清空隐藏表单域中的itemsValue,如果不相同的话，就清空
				if(document.getElementById('s_lgort').value!=""){
					if(orgArgs.node.isLeaf()){//点的是叶子节点
						if(document.getElementById('s_lgort').value!=orgArgs.node.parentNode.attributes.cuid){
							document.getElementById("itemsValue").value="";
						}
					}else{//点的不是叶子节点
						if(document.getElementById('s_lgort').value!=orgArgs.node.attributes.cuid){
							document.getElementById("itemsValue").value="";
						}
					}
				}
			
				document.getElementById('s_lgort').value=orgArgs.node.attributes.cuid;
				document.getElementById('s_idnrk').value='';
				
				if(orgArgs.node.isLeaf()){
					document.getElementById('s_idnrk').value=orgArgs.node.attributes.cuid;
					document.getElementById('s_lgort').value=orgArgs.node.parentNode.attributes.cuid;//父仓库号
					
				}
				document.forms[0].submit();
			});
			var toolbar = new Ext.Panel({
				region		: 'north',
				contentEl	: 'toolbar',
				height		:56
			});
			var panel = new Ext.Panel({
				region		: 'center',
				html		: '	<iframe id="bomListFrame"  src="" name="bomListFrame" frameborder="0" width="100%" height="100%" autolayout="true" scrolling="no"></iframe>'
			});
			var viewport = new Ext.Viewport({
			    layout:"border",
			    items:[storeTree,panel,toolbar]
			});
			
			doLayout();
			
		});
		
		function changePxdat() {
			var start = document.getElementById("s_pxdat_Start").value;
			var end = document.getElementById("s_pxdat_End").value;
			DWREngine.setAsync(false);
			storeTree.initTree('${treeTemplateId}',{
				relatedTreeTemplateCuid : 	'${treeTemplateId}',
				boName					:	'sqlTreeBo',
				templateName			:	'仓库',
				treeParams:{
					s_pxdat_Start : start,
					s_pxdat_End : end
				}
			});
			DWREngine.setAsync(true);
			storeTree.root.expand();
		}
		function doLayout() {
			var maxHeight = top.getContentHeight()-60;
			document.getElementById("bomListFrame").style.height = maxHeight + 'px';
		}
		
		function batchGenerate(action,checkboxName,form){
			alert(document.getElementById("itemsValue").value);
		    if (document.getElementById("itemsValue").value==""){//这里是判断itemsValue隐藏表单域是否有值
		            alert('请选择要操作的对象!');
		            return;
		    }
		    if (confirm('确定执行[生成]操作?')){
		    	if(!checkForGenerate()){//这里检测是否最新数据的
		    		return;
		    	}
		    	action+="&itemsValue="+cancelEspecialSign(document.getElementById("itemsValue").value);
		        form.action = action;
		        form.submit();
		    }
		}
		//把&全变成,
		function cancelEspecialSign(objStr){
			objStr=objStr.replaceAll("&","@");
			return objStr;
		}
		String.prototype.replaceAll = function(s1,s2) { 
			return this.replace(new RegExp(s1,"gm"),s2); 
		}
		
		function hasOneChecked(name){
		    var items = document.frames['bomListFrame'].document.getElementsByName(name);
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		            if (items[i].checked == true){
		                return true;
		            }
		        }
		    } else {
		        if (items.checked == true) {
		            return true;
		        }
		    }
		    return false;
		}
		
		function query(){
		
			if($("#s_lgort").val()==''&&$("#s_orderAufnr").val()==''){
				alert('请选择订单编号！');
				return;
			}
			batchValidation('bomListFrame','${ctx}/zg/plan/ZgTcarplan/findBomList.do',document.forms[0]);
			document.forms[0].submit();
		}
	
		function initTimeSpanView(){
			if("${overTime}"=='0'){//超时不可领
				//document.getElementById("timeSpan").style.display="none";
				//$("#s_pxdat_Start").attr("type","hidden");
			}else{
				//document.getElementById("timeSpan").style.display="";
			}
		}
				
		function checkForGenerate(){
			var jsonStr=buildupSubmitParams();
			DWREngine.setAsync(false);
			var result;
			ZgTcarplanDwrAction.checkForGenerateCarPlan(jsonStr,function(data){
				if(data){
					result = true;
				}else{
					alert("目前BOM组件已经不是最新数据，请确认！");
					result = false;
				}
			});
			return result;
		}
		
		//计算数组的长度
		//mainStr:全字符串
		//subStr:分隔符
		function arrayLength(mainStr,subStr){
			 var count = 0;
			 var offset = 0;
			 do{
			    offset=mainStr.indexOf(subStr);//在字符串tempStr中查看一下是否有subStr字符
			    if(offset != -1){//如果有subStr字符的话，就截断读取过的部分
			    	mainStr=mainStr.substring(offset+1);
			    	count++;
			    }
			 }while(offset != -1);
			 return count;
		}
		
		function buildupSubmitParams(){
			var jsonStr='';
			//var items = document.frames['bomListFrame'].document.getElementsByName("items");//之前用的，因为用了记住BOM，所以这里费了
			var temps=document.getElementById("itemsValue").value;//临时值
			//处理成数组
			var items=new Array(arrayLength(temps,','));
			for(var w=0;w<items.length;w++){
				items[w]=temps.substring(0, temps.indexOf(","));
				temps=temps.substring(temps.indexOf(",")+1);
			}
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		            jsonStr=jsonStr+'{' ;
		           	var params =new QueryString(items[i]);
		            var orderPlanbomId=params["orderPlanbomId"];
		           	var planNum =params["planNum"];
		           	var carNum =params["carNum"];
		           	jsonStr = jsonStr + '"orderPlanbomId":"'+orderPlanbomId+'",';
		           	jsonStr = jsonStr + '"carNum":"'+carNum+'",';
		           	jsonStr = jsonStr +'"planNum":"'+planNum+'"},';
		        }
		    }
		     if(jsonStr.length>0){
	        	jsonStr=jsonStr.substr(0,jsonStr.length-1);
	        }
	        jsonStr='['+jsonStr+']';
	        alert(jsonStr);
	        return jsonStr;
	    }
	    
	    //选中 和 取消 选中的处理
		function updateBom(obj,num){
			if(obj.checked){//选中时
				if(document.getElementById("itemsValue").value.indexOf(bomListFrame.document.getElementById(num+"item").value)==-1){
					var oneItemValue=bomListFrame.document.getElementById(num+"item").value;
					document.getElementById("itemsValue").value+=oneItemValue+",";
				}
			}else{//取消选中
				var oneItemValue=bomListFrame.document.getElementById(num+"item").value;
				document.getElementById("itemsValue").value=document.getElementById("itemsValue").value.replace(oneItemValue+",");
			}
		}
		
	</script>
	</head>

	<body>
		<div id="toolbar" >
			<form action="${ctx}/zg/plan/ZgTcarplan/findBomList.do" method="post"
				target="bomListFrame">
				<input type="hidden" value="${operatorId }" name="s_operatorId" />
				<input type="hidden" value="${carPlanId }" name="carPlanId" id="carPlanId" />
				<input type="hidden" id="itemsValue" name="itemsValue"/>
				
				<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a
										href="javascript:batchGenerate('${ctx }/zg/plan/ZgTcarplan/generateCarPlan.do?orderPlanType=${orderPlanType }&carPlanId=${carPlanId }','items',document.frames['bomListFrame'].document.forms[0])"><span><img
												src="<%=iconPath%>/true.gif" />生成</span> </a> &nbsp;
									<a href="javascript:"><span
										onclick="if(parent.doQuery)parent.doQuery()"><img
												src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
								</div>
							</td>
						</tr>
					</thead>
				</table>

				<table class="formTable">
					<thead>
						<tr class="querybar">
							<td>
								数据源：
							</td>
							<td>
								<select name="s_planType" >
									<c:if test="${orderPlanType=='ABD'}">
										<option value="ABD">
											预装领料计划
										</option>
									</c:if>
									<c:if test="${orderPlanType=='ABC'}">
										<option value="ABC">
											预焊领料计划
										</option>
									</c:if>
									<c:if test="${orderPlanType=='ABE'}">
										<option value="ABE">
											总装领料计划
										</option>
									</c:if>

								</select>
							</td>
							<td>
								<input type="hidden" name="s_overTime" value="${overTime }" />
								<span id="timeSpan">
								
								<c:if test="${overTime=='1'}">
									从：
									<input type="text" dateFlag="true" value="${ pxdat_Start}" id="s_pxdat_Start"
										name="s_pxdat_Start" maxlength="40" onchange="changePxdat()"/>
									到：
									<input type="text" dateFlag="true" value="${ pxdat_End}" id="s_pxdat_End"
										name="s_pxdat_End" maxlength="40" onchange="changePxdat()"/>
								
								</c:if>
								
									<c:if test="${overTime ne '1'}">
									
										<input type="hidden" value="${ pxdat_Start}" id="s_pxdat_Start"
										name="s_pxdat_Start" maxlength="40" onchange="changePxdat()"/>
									<input type="hidden"  value="${ pxdat_End}" id="s_pxdat_End"
										name="s_pxdat_End" maxlength="40" onchange="changePxdat()"/>
							
								
								</c:if>
								
								
									
								</span>
									
								订单编号：
								<input type="text" size="15" name="s_orderAufnr"   id="s_orderAufnr" maxlength="40"/>
								仓库编号：
								<input type="text" size="4" name="s_lgort" id="s_lgort" maxlength="40"/>
								<input type="hidden" size="4" name="s_idnrk" id="s_idnrk" />
									&nbsp;	&nbsp;
									<a class="button" href="javascript:query();"><span ><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
										<a class="button" href="javascript:document.forms[0].reset()"><span ><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							
							</td>
						
						</tr>
					</thead>
				</table>
			</form>
		</div>
	</body>
</html>
