<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
			<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link href="${ctx}/widgets/extremecomponents/extremecomponents.css"
			type="text/css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script src="${ctx}/dwr/interface/ZgTorderbomDwrAction.js" type=""></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/ZgTbomDwrAction.js'></script>
		<style>
.extendTd {
	
}

.extendTd .extendBtn {
	height: 18px;
}

.extendTd .extendBtn img {
	cursor: pointer;
	width: 16px;
	height: 18px;
}

.extendTd .extendBtn label {
	height: 18px;
	line-height: 18px;
}

#matnrListDiv {
	width: 15%;
	height: 100%;
	float: left;
	text-align: center;
}

#matnrChildListDiv {
	width: 84%;
	height: 100%;
	float: left;
	overflow: auto
}

#matnrListDiv ul {
	border: 1px solid #d0e5f5;
	text-align: left;
}

#matnrListDiv ul li {
	cursor: pointer;
	font-size: 120%;
}

#matnrListDiv ul li:hover {
	color: red;
	font-weight: bold;
}

#matnrCode {
	font-size: 130%;
	font-weight: bold;
	background-color: #d0e5f5;
	line-height: 20px;
	color: #1d5987;
}
</style>
		<script type="text/javascript">
		$(function() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			var img = "<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
			$("#orderId_value").after(img);
			$("#arbpl_value").after(img);
			$("#lgort_value").after(img);
			
			try{
				$("input[dateFlag=true]")
				.datepicker({
					showOtherMonths: true,
					selectOtherMonths: true,
					dateFormat:"yy-mm-dd"
				});
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
			}catch(e){}
		});

		//选择生产线及订单
		function selectOrderArbpl(){
		var type='${type}';
			var productType='${productType}';
			var lgort=$("#lgort_value").val();

			var sFeatures="dialogHeight: 500px;dialogWidth:760px";
			var url='';
			if(type=='1'){//入库
				url='${ctx}/zg/storage/ZgTstorage/queryOrderArbplList.do?type='+type;
			}if(type=='2'){//出库
				if(lgort==''){
					alert('请选择仓库！');
					return;
				}
				url='${ctx}/zg/storage/ZgTstorage/queryOrderArbplList.do?lgort='+lgort+'&type='+type+'&productType='+productType;
			}
			var returnValue = window.showModalDialog(url,'',sFeatures);
			if(returnValue) {
				var orderID = returnValue.orderID;
				var aufnr =	returnValue.aufnr;
				var arbpl = returnValue.arbpl;
				var arbplName =	returnValue.arbplName;
					if(orderID!=$("#orderId_value").val()){//订单和生产线值改变 则删除相应的半成品
					document.frames['listFrame'].document.location="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&flag=del";
					document.frames['listFrame'].document.location.href="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&flag=temp";
					
				}
				$("#orderId_label").val(aufnr);
				$("#orderId_value").val(orderID);
				$("#arbpl_label").val(arbplName);
				$("#arbpl_value").val(arbpl);
			}
		}
		
		function check(){
			if($("#planDate").val()==''){
				alert('请填写单据日期');
				return false;
			}
			
			
			if($("#orderId_value").val()==''){
				alert('请选择订单号');
				return false;
			}
			
			if($("#lgort_value").val()==''){
				alert('请选择仓库名称');
				return false;
			}
			
			return true;
			
		}
		
		function addStorage(){
			document.frames['listFrame'].saveBom();
			if(!checkforformchanged()){
				return;
			}
			
			if(!check()){
				return;
			}
			var form=document.forms[0];
			form.action="${ctx}/zg/storage/ZgTstorage/save.do?update=true";
			form.submit();
		}
		
		function submitStorage(){
			document.frames['listFrame'].saveBom();
			if(!checkforformchanged()){
				return;
			}
			if(!check()){
				return;
			}
			var type='${type}';
			if(type==2){//出库
				var form=document.forms[0];
				form.action="${ctx}/zg/storage/ZgTstorage/submit.do?update=true";
				form.submit();
				return;
			}
			//此处校验
			var inNum="";
			var flag=true;
			var orderIdAndIdnrkList=document.frames('listFrame').document.getElementsByName("orderIdAndIdnrk");
			var idnrkList=new Array();
			var orderIdList=new Array();
			var items=document.frames('listFrame').document.getElementsByName("items");
			for(var i=0;i<items.length;i++){
				orderIdList[i]=orderIdAndIdnrkList[i].value.substring(0,orderIdAndIdnrkList[i].value.lastIndexOf('/'));
				idnrkList[i]=orderIdAndIdnrkList[i].value.substring(orderIdAndIdnrkList[i].value.lastIndexOf('/')+1,orderIdAndIdnrkList[i].value.length);
				inNum=document.frames('listFrame').document.getElementById('num_'+i).value;
				DWREngine.setAsync(false);
				ZgTbomDwrAction.isEnoughForProductOut(orderIdList[i],idnrkList[i],inNum,function(data){
								if(data =='OK') {
									flag=true;
								}else {
									alert("半成品"+idnrkList[i]+":"+data+",半成品入库失败!");
									flag=false;
								}
						});  
			     if(!flag) return;
			}
			if(flag){
				var form=document.forms[0];
				form.action="${ctx}/zg/storage/ZgTstorage/submit.do?update=true";
				form.submit();
			}
		}
		
		function backToList(){
			parent.document.forms[0].submit();
		}
		
		function selectLgort(){
			var sFeatures="dialogHeight: 400px;dialogWidth:300px";
			var returnValue = window.showModalDialog(ctx+"/explorer/tree/commonTree.jsp?templateId="+1,'',sFeatures);
			var id = returnValue.id;
			var label = returnValue.label;
	
			var type='${type}';
			if(type=='2'){//出库的时候，仓库改了，则后面的生产线及订单，及半成品要清空
				if(id!=$("#lgort_value").val()){
					$("#orderId_label").val("");
					$("#orderId_value").val("");
					$("#arbpl_label").val("");
					$("#arbpl_value").val("");
					document.frames['listFrame'].document.location="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&flag=del";
					document.frames['listFrame'].document.location.href="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&flag=temp";
					
				}
			}
			$("#lgort_label").val(label);
			$("#lgort_value").val(id);
		}
		//在修改半成品完成，提交（保存）时进行检验，看数据是否最新
		function checkforformchanged(){
			DWREngine.setAsync(false);
			var result = '';
			var type='${type}';
			var lgort=$("#lgort_value").val();
			if(type == '1'){
			ZgTorderbomDwrAction.checkForGenerateOrderChanged("${model.cuid}",function(data){
				if(data=='OK'){
					result = true;
				}else{
					alert(data);
					result = false;
				}
			});
			return result;
			}
			if(type == '2'){
			ZgTorderbomDwrAction.checkForGenerateStorageOutChanged("${model.cuid}",lgort,function(data){
				if(data=='OK'){
					result = true;
				}else{
					alert(data);
					result = false;
				}
			});
			return result;
			}
			
		}
		
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form action="${ctx}/zg/storage/ZgTstorage/save.do" method="post">
			<input type="hidden" id="cuid" name="cuid" value="${model.cuid}" />
				<input type="hidden" id="id" name="id" value="${model.cuid}" />
				<input type="hidden" name="type" value="${ type }" />
				<input type="hidden" name="productType" value="${ productType }" />
				<input type="hidden" name="state" value="${ model.state }" />
			<table width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">
						
							<div class="button" style="text-align: left;">
							<c:if test="${empty view}">
								<a href="javascript:addStorage()""><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
								<a href="javascript:submitStorage()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
							</c:if>
						
							<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
							</div>
						</td>
					</tr>
				</thead>
			</table>

			<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<td class="title" colspan="8">
							<img src="${ctx }/resources/images/frame/ico_noexpand.gif"
								style="cursor: pointer" title="高级查询" alt="" id="img_1"
								border="0" onclick="changeV('1')" />
							单据
							<s:property value="%{model.aufnr}" />
						</td>
					</tr>
				</thead>
				<tbody id="tbody_1" style="display: block">
					<tr>
						<td colspan="8"
							style="border: 1px solid #A8CFEB; border-width: 0 0 1px 0;">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="20"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
								单据日期:
						</th>
							<td width="15%">
								<input type="text" size="15" readonly="true" id="createDate"　readonly ="true" maxlength="40"
									name="createDate"
									value="<fmt:formatDate value="${model.createDate}" pattern="yyyy-MM-dd" />" />
							</td>
						<th>
								仓库名称:
							</th>
							<td width="15%">
								<input type="text" size="15" onclick="selectLgort()" maxlength="40" 
									value="${model.lgort_related.value }" xtype="tree:1"
									id="lgort_label" columnNameLower="lgort"
									bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" />
								<input type="hidden" id="lgort_value" name="lgort"
									value="${model.lgort }" />
							</td>
							<th>
								订单号:
							</th>
							<td width="15%">
								<input type="text" size="15" onclick="selectOrderArbpl()" maxlength="40"
									value="${model.orderId_related.value }" readonly="true"
									id="orderId_label" name="orderId_label"
									columnNameLower="orderId" bmClassId="FW_ORGANIZATION"
									column="m.t0_LABEL_CN" />
								<input type="hidden" id="orderId_value" name="orderId"
									value="${model.orderId }" />
							</td>
						<th>
								生产线:
						</th>
							<td width="15%">
								<input type="text" size="15"  onclick="selectOrderArbpl()" 
									value="${model.arbpl_related.value }" readonly="true"
									id="arbpl_label" columnNameLower="arbpl"
									bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" maxlength="40" />
								<input type="hidden" id="arbpl_value" name="arbpl"
									value="${model.arbpl }" />
							</td>

					</tr>
					<tr>

						
							<th>
								部门名称:
						</th>
							<td width="15%">
								${model.deptId_related.value}
								<input type="hidden" id="deptId" name="deptId"
									value="${model.deptId}" />
							</td>
								<th>
								录单人:
							</th>
							<td width="15%">
								${model.creatorId_related.value}
								<input type="hidden" id="creatorId" name="creatorId"
									value="${model.creatorId}" />
							</td>
							<th>
								备注:
						</th>
							<td colspan="5">
								<input type="text" size="15" maxlenth="170"
									value="${model.zbz }" name="zbz" id="zbz" />
							</td>
					</tr>
					<tr>

					</tr>
				</tbody>
			</table>
		</form>
		<iframe
				src="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&view=${view}"
				autolayout="true" name="listFrame" frameborder="0" width="100%"
				height="100%" align="top" scrolling="no" />
	</body>
</html>
