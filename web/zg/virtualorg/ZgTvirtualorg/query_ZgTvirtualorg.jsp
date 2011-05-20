<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.virtualorg.base.model.*" %>
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTvirtualorg.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgMaterielVirtualorgDwrAction.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		var accordion;
		$(function() {
			if(true){
				//$("#formForEmployee").submit();
				//$("#formForMateriel").submit();
			}
				$(window).bind("resize",doLayout);
			doLayout();
		});
		var groupList = [];
		<c:forEach items="${roles}" var="role">
			groupList.push({value:'${role.cuid}',labelCn:'${role.labelCn}'});
		</c:forEach>
		
		/* tabpages */
		function nTabs(tabObj, obj) {
		    var tabList = document.getElementById(tabObj).getElementsByTagName("li");//拿到全部的li
		    //控制你选择了哪个tab的
		    if(obj.id=="tab_title0"){
		    	document.getElementById("whichTab").value="0";
		    }else{
		    	document.getElementById("whichTab").value="1";
		    }
		    for (i = 0; i < tabList.length; i++) {
		        if (tabList[i].id == obj.id) {
		            document.getElementById(tabObj + "_title" + i).className = "current";
		            document.getElementById(tabObj + "_content" + i).style.display = "block";
		        } else {
		        	document.getElementById(tabObj + "_title" + i).className = "";
		            document.getElementById(tabObj + "_content" + i).style.display = "none";
		        }
		    }
		}
		
		function doQueryForEmployee() {
			document.getElementById('userForm').style.display='';
			if(batchValidation('listFrameForEmployee','${ctx}/zg/virtualorg/ZgTvirtualorg/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
		function doQueryForMateriel() {
			document.getElementById('materielForm').style.display='';
			if(batchValidation('listFrameForMateriel','${ctx}/zg/materiel/ZgMateriel/list.do',document.forms[1])) {
				document.forms[1].submit();
			}
			
		}
		
		//新建领料人跟领料组的关系
		function employeeListFunction(){
			var virtualorgId=document.getElementById("virtualorgId").value;
			if(virtualorgId==""){
				alert("请选择某个领料组才能点击新增按钮!");
				return;
			}
			
			var url='${ctx}/zg/virtualorg/ZgTvirtualorg/createEmployeeForVirtualorg.do?virtualorgId='+virtualorgId;
			var dialogHeight=420;
			var dialogWidth=700;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			//进行插入
			if(result){
				var whetherParent=document.getElementById("whetherParent").value;
				var treeCuid=document.getElementById("treeCuid").value;
				document.forms[0].action="${ctx}/zg/virtualorg/ZgTvirtualorg/submitEmployeeForVirtualorg.do?allUserId="+result.allId+"&virtualorgId="+virtualorgId+"&whetherParent="+whetherParent+"&treeCuid="+treeCuid;
				document.forms[0].submit();
			}
		}
		
		//新建物料组跟领料组的关系
		function materielListFunction(){
			var virtualorgId=document.getElementById("virtualorgId").value;
			if(virtualorgId==""){
				alert("请选择某个领料组才能点击新增按钮!");
				return;
			}
			var url='${ctx}/zg/virtualorg/ZgTvirtualorg/queryMateridel.do?orgId='+virtualorgId;
			var dialogHeight=500;
			var dialogWidth=800;
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			if(result){
				var whetherParent=document.getElementById("whetherParent").value;
				var treeCuid=document.getElementById("treeCuid").value;
				document.forms[1].action="${ctx}/zg/materiel/ZgMateriel/submitMaterielForVirtualorg.do?allMaterielId="+result.allMaterielId+"&virtualorgId="+virtualorgId+"&whetherParent="+whetherParent+"&treeCuid="+treeCuid;
				document.forms[1].submit();
			}
		}
		//删除领料人
		function delUser(url){
			var items = document.frames('listFrameForEmployee').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的领料人！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
				 var whetherParent=document.getElementById('whetherParent').value;
				 var treeCuid=document.getElementById('treeCuid').value;
				 
       			 document.frames('listFrameForEmployee').document.forms[0].action = url+"?whetherParent="+whetherParent+"&treeCuid="+treeCuid;
			 	 document.frames('listFrameForEmployee').document.forms[0].submit();
   			 }
		}
		//删除领料组
		function delMateriel(url){
			if($("#whetherParent").val()!='1'){
				alert('请选中具体领料组再进行删除动作');
				return;
			}
			var items = document.frames('listFrameForMateriel').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的领料组！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
				 var whetherParent=document.getElementById('whetherParent').value;
				 var treeCuid=document.getElementById('treeCuid').value;
			
       			 document.frames('listFrameForMateriel').document.forms[0].action = url+"?whetherParent="+whetherParent+"&treeCuid="+treeCuid;
			 	 document.frames('listFrameForMateriel').document.forms[0].submit();
   			 }
		}
		function doLayout() {
			var maxHeight = top.getContentHeight();
		//	var splitH = document.getElementById("split_1").offsetHeight;
			var queryPanelH = maxHeight;
			document.getElementById("tab_content0").style.height = queryPanelH + 'px';
			document.getElementById("tab_content1").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var queryTableH1 = document.getElementById("query_table1").offsetHeight;
			var listFrameH = queryPanelH - queryTableH-35;
			var listFrameH1 = queryPanelH - queryTableH1-75;
			document.getElementById("listFrameForEmployee").style.height = listFrameH + 'px';
			document.getElementById("listFrameForMateriel").style.height = listFrameH1 + 'px';
		//	var operateFrameH = queryPanelH;
		//	document.getElementById("operateFrame").style.height = operateFrameH + 'px';
		//	document.getElementById("_operatePanel").style.height = operateFrameH + 'px';
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<!-- 这隐藏表单域用来分辨是选中了哪个tab的 -->
<input type="hidden" id="whichTab" value="0"/>
<!-- 这个隐藏表单域用来藏起左边树点击的领料组，如果点击的是部门的话，就不存储 -->
<input type="hidden" id="virtualorgId" value=""/>

<input type="hidden" id="whetherParent"/>
<input type="hidden" id="treeCuid"/>
<!-- tab页面1 -->
<div class="tabpage" id="tab">
  <ul>
     <li id="tab_title0" onclick="nTabs('tab',this);" class="current"><a href="javascript:">
        <span>领料人</span></a></li>
          <li id="tab_title1" onclick="nTabs('tab',this);"><a href="javascript:">
            <span>物料组</span></a></li>
  </ul>
  <div class="main" id="mainDiv">
  <div id="tab_content0">
  <div id="userForm">
	<form action="${ctx}/zg/virtualorg/ZgTvirtualorg/list.do?whetherParent='+document.getElementById('whetherParent').value+'&treeCuid='+document.getElementById('treeCuid').value" method="post" target="listFrameForEmployee" id="formForEmployee">
		<input type="hidden" name="s_labelCn" id="s_labelCn"/>
		<input type="hidden" name="s_orgId" id="s_orgId"/>
		<input type="hidden" name="s_orgName" id="s_orgName"/>
        <table  id="query_table" class="query_panel" cellpadding="0" cellspacing="0" >
					<tr>
					   <td class="label">
					   		领料人ID：
					   </td>
					   <td >
				   			<input type="text" maxlength="20" name="s_userId" id="s_userId"/>
					   </td>
					   <td class="label">
					   		领料人名：
					   </td>
					   <td >
				   			<input type="text" maxlength="20" name="s_userName" id="s_userName"/>
					   </td>
					   <td class="label">
					   		性别：
					   </td>
					   <td >
				   			<select id="s_sex" name="s_sex">
				   				<option value="0" selected>全部</option>
				   				<option value="1">男</option>
				   				<option value="2">女</option>
				   			</select>
					   </td>
					   </tr>
					   <tr>
				   			
						<!-- 
						<td class="label">
							<%=ZgTvirtualorg.ALIAS_TYPE%>：
						</td>	
						<td>
							<select id="s_type" name="s_type">
								<option value="" selected>全部</option>
								<option value="ABC">预焊</option>
								<option value="ABD">预装</option>
								<option value="ABE">总装</option>
							</select>
						</td>
						 -->
					</tr>
					<tr>
						<td colspan="9">
							<div class="toolbar">
									<a href="javascript:batchValidation('listFrameForEmployee','${ctx}/zg/virtualorg/ZgTvirtualorg/list.do?whetherParent='+document.getElementById('whetherParent').value+'&treeCuid='+document.getElementById('treeCuid').value,document.forms[0]);document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
									<!-- <a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a> -->
									<a href="javascript:employeeListFunction()"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
									<a href="javascript:delUser('${ctx}/zg/virtualorg/ZgTvirtualorg/delete.do')"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
									<!-- <a href="javascript:exportData(1)"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
									<a href="javascript:exportData(2)"><span><img src="<%=iconPath%>/page_excel.png" />导出全部</span></a> -->
							</div>
						</td>
					</tr>
			</table>
		</form>
		</div>
		<iframe src=""  name="listFrameForEmployee" id="listFrameForEmployee" frameborder="0" width="100%" height="100%" align="top"  style="overflow-y:auto;overflow-x:hidden"></iframe>
	</div>

			
		    <div id="tab_content1" class="none">
			<form action="${ctx}/zg/materiel/ZgMateriel/list.do?whetherParent='+document.getElementById('whetherParent').value+'&treeCuid='+document.getElementById('treeCuid').value" method="post" target="listFrameForMateriel" id="formForMateriel">
				<input type="hidden" maxlength="13" name="s_virtualorgName" id="s_virtualorgName" readonly="readonly"/>
				<input type="hidden" name="s_organizationId" id="s_organizationId"/>
				<input type="hidden" name="s_organizationName" id="s_organizationName" readonly="readonly"/>
				<table  id="query_table1" class="query_panel" cellpadding="0" cellspacing="0">
					<tr>
					   <td class="label">
					   		<%=ZgMateriel.ALIAS_MATERIEL_NAME%>：
					   </td>
					   <td >
				   			<input type="text" maxlength="20" name="s_materielName" id="s_materielName"/>
					   </td>
					   		
						<!-- 
						<td class="label">
							<%=ZgMateriel.ALIAS_VIRTUALORG_TYPE%>：
						</td>	
						<td>
							<select id="s_virtualorgType" name="s_virtualorgType">
								<option value="" selected>全部</option>
								<option value="ABC">预焊</option>
								<option value="ABD">预装</option>
								<option value="ABE">总装</option>
							</select>
						</td>
						 -->
					</tr>
					<tr>
						<td colspan="9">
							<div class="toolbar">
									<a  href="javascript:batchValidation('listFrameForMateriel','${ctx}/zg/materiel/ZgMateriel/list.do?whetherParent='+document.getElementById('whetherParent').value+'&treeCuid='+document.getElementById('treeCuid').value,document.forms[1]);document.forms[1].submit()" style=""><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
									<!-- <a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a> -->
									<a href="javascript:materielListFunction()"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
									<a href="javascript:delMateriel('${ctx}/zg/materiel/ZgMateriel/delete.do')"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a> 
									<!-- <a href="javascript:exportData(1)"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
									<a href="javascript:exportData(2)"><span><img src="<%=iconPath%>/page_excel.png" />导出全部</span></a> -->
							</div>
						</td>
					</tr>
			</table>
		</form>
			<iframe id="listFrameForMateriel" src="" name="listFrameForMateriel" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
		
		</div>
		
		</div>
	</div>
	
	
</body>
</html>