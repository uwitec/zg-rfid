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
		<script type="text/javascript" src="${ctx}/dwr/interface/ZgTBomManagerDwrAction.js"></script>
		<script type="text/javascript" src="${ctx}/dwr/interface/ZgTorderDwrAction.js"></script>
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
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		});
		
		
		function doLayout() {
			var maxHeight = top.getContentHeight();
			document.getElementById("_orderPanel").style.height = maxHeight + 'px';
			var headTableH = document.getElementById("headTable").offsetHeight;
			var orderTableH= document.getElementById("orderTable").offsetHeight;
			var listFrameH = maxHeight - headTableH-orderTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
		
		
		
		var orderID ="";
		
		
		function saveChangeOrder(){
			butsetDisable(true);
			var items=document.frames('listFrame').document.getElementsByName("items");
			if(items.length<=0){
				alert('请添加物料');
				butsetDisable(false);
				return;
			}
			var buildupSubmitParams=document.frames('listFrame').buildupSubmitParams();
			ZgTBomManagerDwrAction.saveChangOrderBom(buildupSubmitParams,function(data){
				if(data=="success") {
					var form=document.forms[0];
					form.target="_self";
					form.action="${ctx}/zg/plan/ZgTBomManager/save.do";
					form.submit();
				}else {
					alert("系统繁忙，请稍后再试");
					butsetDisable(false);
				}
			});

		}
		
		function butsetDisable(state){
			var btns=document.getElementsByName("submitBtn");
			if(btns!=null){
				for(var v=0;v<btns.length;v++){
					btns[v].disabled = state;
				}
			}
			
		}
		
		function selectOrderArbpl(){
		  var items=document.frames('listFrame').document.getElementsByName("items");
			
		 if(""==$("#orderId_value").val()){
		
			url='${ctx}/zg/plan/ZgTBomManager/queryOrderArbplList.do?type=1';
			var sFeatures="dialogHeight: 500px;dialogWidth:760px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
			if(returnValue) {
				orderID = returnValue.orderID;
				var aufnr =	returnValue.aufnr;
				var arbpl = returnValue.arbpl;
				var arbplName =	returnValue.arbplName;
				var plant = returnValue.plant;
				
				if(orderID!=$("#orderId_value").val()){
					//$("form:first").submit();
				}
				
				$("#orderId_label").val(aufnr);
				$("#orderId_value").val(orderID);
				$("#plant").val(plant);
				$("#arbpl").val(arbpl);
				//generatePlantSelect(orderID);
			   }
			}else{
		    if(items.length<=0){
			url='${ctx}/zg/plan/ZgTBomManager/queryOrderArbplList.do?type=1';
			var sFeatures="dialogHeight: 500px;dialogWidth:760px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
			if(returnValue) {
				orderID = returnValue.orderID;
				var aufnr =	returnValue.aufnr;
				var arbpl = returnValue.arbpl;
				var arbplName =	returnValue.arbplName;
				var plant = returnValue.plant;
				
				if(orderID!=$("#orderId_value").val()){
					//$("form:first").submit();
				}
				
				$("#orderId_label").val(aufnr);
				$("#orderId_value").val(orderID);
					$("#plant").val(plant);
				$("#arbpl").val(arbpl);
			//	generatePlantSelect(orderID);
			   }
			}else{
				alert("请先删除当前订单号下的物料，再进行该操作");
				return;
			}
		}
		
	}
	
	function generatePlantSelect(orderId){
		ZgTorderDwrAction.getPlantByOrderId(orderId,function(data){
			//alert(data);
			DWRUtil.removeAllOptions("plant");  
			DWRUtil.addOptions("plant",data,"PLANT","PLANT");
		});
	}
	function selectectend1(){
	      
	     var items=document.frames('listFrame').document.getElementsByName("items");
	     if(items.length>0){
	      	alert("请先清空物料列表下的物料，再进行该操作");
	      	return;
	     }
	    
	  }
		function submitChangeOrder(){
			butsetDisable(true);
			var items=document.frames('listFrame').document.getElementsByName("items");
			if(items.length<=0){
				alert('请添加物料');
				butsetDisable(false);
				return;
			}
			//alert(items.length);
			for(var i=0;i<items.length;i++){
				
				wait_back_num=document.frames('listFrame').document.getElementById('WAIT_BACK_NUM_'+i).value;
				if(wait_back_num==0){
			    	alert('换料数量为0，请更改');
			    	butsetDisable(false);
					return;
			     }
			}
			
			
			var buildupSubmitParams=document.frames('listFrame').buildupSubmitParams();
			ZgTBomManagerDwrAction.saveChangOrderBom(buildupSubmitParams,function(data){
				if(data=="success") {
					var form=document.forms[0];
					form.target="_self";
					form.action="${ctx}/zg/plan/ZgTBomManager/submit.do";
					form.submit();
				}else {
					alert("系统繁忙，请稍后再试");
					butsetDisable(false);
				}
			});
		}
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
	<div id="_orderPanel" style="height:100px">		
		<form action="${ctx}/zg/plan/ZgTBomManager/findBomListByPlanID.do?planType=${model.planType}" method="post"  target="listFrame" >
			<input type="hidden" id="cuid" name="cuid" value="${model.cuid}" />
			<table id="headTable" width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">

							<div class="button" style="text-align: left;">
								<a href="javascript:saveChangeOrder()"  name="submitBtn" ><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
								<a href="javascript:submitChangeOrder()"  name="submitBtn" ><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
								<a href="javascript:if(parent.doQuery)parent.doQuery()"  name="submitBtn" ><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
							</div>
						</td>
					</tr>
				</thead>
			</table>

			<table id="orderTable"  class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<td class="title" colspan="12">
							<img src="${ctx }/resources/images/frame/ico_noexpand.gif"
								style="cursor: pointer" title="" alt="" id="img_1"
								border="0" onclick="changeV('1')" />
								<c:if test="${model.planType=='CHANGE'}">换料申请单</c:if>
								<c:if test="${model.planType=='BACK'}">退料申请单</c:if>
								<c:if test="${model.planType=='RENEW'}">补领料申请</c:if>
								
						</td>
					</tr>
				</thead>
				<tbody id="tbody_1" style="display: block">
					<tr>
						<td colspan="10"
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
							单据日期：
						</th>
						<td width="15%">
						
							<input type="text" size="15" readonly="true" id="create_date" maxlength="40"
								name="create_date"
								value="<fmt:formatDate value="${model.create_date}" pattern="yyyy-MM-dd HH:mm:ss" />" />
						</td>
						<th>
							订单号：
						</th>
						<td width="15%">
							<input type="text" size="15" onclick="selectOrderArbpl()" maxlength="40"
								readonly="true" id="orderId_label" name="orderId_label"
								columnNameLower="orderId" bmClassId="FW_ORGANIZATION"
								column="m.t0_LABEL_CN"  value=""/>
							<input type="hidden" id="orderId_value" name="orderTaskId" />
							<input type="hidden" id="planType" name="planType"  value="${ model.planType}"/>
						</td>
						<th>
							物料等级：
						</th>
						<td width="12%" onclick="selectectend1()">
					
							<select name="extend1" id="extend1" >
								<option value="A">A级物料</option>
								<option value="B">B级物料</option>
								<option value="C" selected="true">C级物料</option>
							</select>
							
						</td>
						
						
						</tr>
						<tr>	
						<th>
							生产厂：
						</th>
						<td width="5%">
						<input type="text"  readonly="true" name="plant" id="plant"/>
						</td>
						<th>
							生产线：
						</th>
						<td width="5%">
						<input type="text"  readonly="true" name="arbpl" id="arbpl"/>
						</td>

						<th>
							录单人：
						</th>
						<td width="15%">
							${operatorInfo.userName}
							<input type="hidden" id="userId" name="userId"
								value="${model.userId}" />
						</td>

					</tr>
				</tbody>
			</table>
		</form>
			<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
		
		</div>
	

	</body>
</html>
