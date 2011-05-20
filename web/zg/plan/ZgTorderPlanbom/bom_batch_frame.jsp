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
		<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<style>
.readOnlyInput {
	border: 0;
	background: transparent;
}
</style>
		<script type="text/javascript">
		$(function() {
			try{
				initAutoCompleteTo("${ctx}/autoComplate/findRelationData.do");
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
			}catch(e){}
		});
		function initAutoCompleteTo(url) {
			var a = $("input[autocompleteTo]");
			for(var i = 0 ; i < a.length; i++) {
			
				var o = a[i];
				var xtype = $(o).attr("xtype");
				var columnNameLower = $(o).attr("columnNameLower");
				var bmClassId = $(o).attr("bmClassId");
				var column = $(o).attr("column");
				$("#"+columnNameLower+"_label").css("cursor","pointer");
				$("#"+columnNameLower+"_value").val(document.getElementById("orgId_value").value);//初始化值为空value
				var img = "<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
				$("#"+columnNameLower+"_value").after(img);
			if(xtype.indexOf("tree:") != -1){
					var templateId = xtype.substring(xtype.indexOf(":")+1);
					$("#"+columnNameLower+"_label").attr("readonly",true);
					var sFeatures="dialogHeight: 400px;dialogWidth:300px";
					$(o).bind("click",function() {
						var returnValue = window.showModalDialog(ctx+"/explorer/tree/commonTree.jsp?templateId="+templateId,'',sFeatures);
						if(returnValue) {
							var id = returnValue.id.substring(0);//截取了第一个逗号
							var label = returnValue.label.substring(0);//截取了第一个逗号
							$("#"+$(this).attr("columnNameLower")+"_value").val(id);
							$("#"+$(this).attr("columnNameLower")+"_label").val(label);
							changeGroup(id);
						}
					});
				}
			}
		}
		
		//保存领料计划
		function addPlan(id,obj) {
			obj.disabled=true;
			//先保存操作的
			if(window.frames["resconfigResult"].batchUpdate){
			
				judge=window.frames["resconfigResult"].batchUpdate();
				
				//	  1：批量计划单为空的
		       	//    2：批量计划单领用的BOM的数量有为0的
		       	//    3: 有领料人有为空的
		       	//    4: 有领料日期为空的
		       	//    5：有开始时间为空的
		       	//    6: 有结束时间为空的
		       	//    9: 其它异常
		       	//    10：一切正常
				if(judge==1){
					alert("BOM组件列表为空，不能保存！");
					obj.disabled=false;
					return;
				}else if(judge==2){
					alert("BOM组件计划领取数量不能为0，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==3){
					alert("领料人不能为空，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==4){
					alert("领料日期不能为空，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==5){
					alert("开始时间不能为空，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==6){
					alert("结束时间不能为空，请确认！");
					obj.disabled=false;
					return;
				}
				
				var zgTorderPlanStateData=resconfigResult.document.getElementById("zgTorderPlanStateData").value;
				
				if($("#planDate").val()==''){
					alert("请填写领料时间!");
					obj.disabled=false;
					return;
				}
				document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanForBatch/saveOrderPlanForBatch.do?zgTorderPlanStateData="+zgTorderPlanStateData;
				document.forms[0].submit();
			}else{
				alert("请按完成后再保存！");
			}
			
		}
		
		function addPlanBom(id){
			
			if(id=='自动生成'){
				id="";
			}
			
			var url='${ctx}/zg/plan/ZgTorderPlanbomForBatch/createOrderPlanForBatch.do?orderPlanId='+id;
			var dialogHeight=360;
			var dialogWidth=700;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			
			window.location.href ='${ctx}/zg/plan/ZgTorderPlanbomForBatch/findBomBatchList.do?orderPlanId=${orderPlanId}&flag=temp';
			if(result!=undefined){
					alert(result);
			}
		
			window.location.reload();
		}
		
		function editPlanBom(id,orderPlanId){
			
			var url='${ctx}/zg/plan/ZgTorderPlanbomForBatch/editOrderPlanForBatch.do?id='+id+'&orderPlanId='+orderPlanId;
			
			var dialogHeight=360;
			var dialogWidth=700;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			
			window.location.href ='${ctx}/zg/plan/ZgTorderPlanbomForBatch/findBomBatchList.do?orderPlanId=${orderPlanId}&flag=temp';
			if(result!=undefined){
					alert(result);
			}
		
			window.location.reload();
		}
		
		//提交领料计划
		//思路：这里在提交之前，要进行选择‘审核人’ --弹出模式窗体
		//    把orderPlanId和userid(所选的审核人的Id)插进去
		function submitPlan(str,obj){
			obj.disabled=true;
			//先保存操作的
			var judge;//用来判断有没有bom组件在计划单中

			if(window.frames["resconfigResult"].batchUpdate)
			{
				judge=window.frames["resconfigResult"].batchUpdate();
				
				//	  1：批量计划单为空的
		       	//    2：批量计划单领用的BOM的数量有为0的
		       	//    3: 有领料人有为空的
		       	//    4: 有领料日期为空的
		       	//    5：有开始时间为空的
		       	//    6: 有结束时间为空的
		       	//    9: 其它异常
		       	//    10：一切正常
				if(judge==1){
					alert("BOM组件列表为空，不能提交审核！");
					obj.disabled=false;
					return;
				}else if(judge==2){
					alert("BOM组件计划领取数量不能为0，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==3){
					alert("领料人不能为空，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==4){
					alert("领料日期不能为空，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==5){
					alert("开始时间不能为空，请确认！");
					obj.disabled=false;
					return;
				}else if(judge==6){
					alert("结束时间不能为空，请确认！");
					obj.disabled=false;
					return;
				}
				
				if($("#planDate").val()==''){
					alert("请填写领料时间!");
					obj.disabled=false;
					return;
				}
				
				//弹出选择‘审核’人的窗体  url,'',sFeatures
				var sFeatures="dialogHeight: 500px;dialogWidth:760px";
				var firstShowQueryUrl="${ctx}/zg/plan/ZgTorderPlanForBatch/queryForAuditingPeople.do";
				var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
				if(returnValue){
					document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlanForBatch.do?userId="+returnValue.userId;
					document.forms[0].submit();
					return;
					//window.location.href="${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlanForBatch.do?orderPlanId=${orderPlanId}";
				}
				obj.disabled=false;
			}
			else
			{
				alert('请点击完成后才提交！');
			}
		}
		
		function init(state){
			if($("#cuid").val()=='自动生成'){
			   $("#b_submit").attr("disabled",true);
			}
			//	document.forms[0].submit();
			if(state=='8'){//表单状态为提交状态
				  $("#b_save").attr("disabled",true);
			}
		}
		
		function backToList(){
			parent.document.forms[0].submit();
		}
		
		//批量设置栏的限制
		function checkNumTitle(obj) {
			var value = obj.value;
			if(!isNumber(value)) {
				alert("计划领取必须为数字！");
				obj.value =0;
			}
		}
		
		function checkStartEnd(obj,startTime,endTime){
		    return checkStartEndTime(obj,$("#"+startTime).val(),$("#"+endTime).val());
	    }
	    
	    //校验开始结束时间
	    function checkStartEndTime(obj,startTime,endTime){
		    if(startTime!=''&&endTime!=''){
		    	if(startTime>endTime){
		    		alert('开始时间必须小于结束时间!');
		    		obj.value="";
		    		return false;
		    	}
		    }
	    }
		
		//批量设置的领料组设置
		function changeGroup(orgId) {
			if(orgId!= "") {
				CommonDwrAction.getUsersByOrgId(orgId,function(data){
					buildSel("userSel",data);
				});
			}else {
				buildSel("userSel",[]);
			}
			
		}
		
		function buildSel(id,data) {
			var targetEl = document.getElementById(id);
			if(targetEl) {
				targetEl.innerHTML = "";
				var emptyOpt = document.createElement("option");
				emptyOpt.value="";
				emptyOpt.innerHTML = "请选择...";
				targetEl.appendChild(emptyOpt);
				for(var i = 0; i< data.length; i++) {
					var labelCn = data[i].labelCn;
					var cuid = data[i].cuid;
					var opt = document.createElement("option");
					opt.value=cuid;
					opt.innerHTML = labelCn;
					targetEl.appendChild(opt);
				}
			}
		}
		
		//批量设置
		function setAll() {
			setValues('carnums','carnumSel');
			setValues1('groups','orgId');
			setValues1('groupOldNames','orgId');
			setValues('employees','userSel');
			setValues('planDates','planDateInput');
			setValues('planStartTimes','planStartTimeSel');
			setValues('planEndTimes','planEndTimeSel');
		}
		
		function setValues1(targets,valueEl) {
			var items = resconfigResult.document.getElementsByName("items");
			var bomTable = resconfigResult.document.getElementById("ec_table");
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					if(window.frames["resconfigResult"].changeOther){
						window.frames["resconfigResult"].changeOther(items[i].value);
					}
					var row = bomTable.rows[i+1];
					var inputs = row.getElementsByTagName("input");
					for(var j = 0 ; j < inputs.length;j++) {
						if(inputs[j].getAttribute("attr")==targets) {
							inputs[j].value = $("#"+valueEl+"_value").val();
						}
						if(inputs[j].name==targets+"_label") {
							inputs[j].value = $("#"+valueEl+"_label").val();
						}
					}
				}
			}
		}
		
		function setValues(targets,valueEl) {
			var items = resconfigResult.document.getElementsByName("items");
			var bomTable = resconfigResult.document.getElementById("ec_table");
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					if(window.frames["resconfigResult"].changeOther){
						window.frames["resconfigResult"].changeOther(items[i].value);
					}
					var row = bomTable.rows[i+1];
					var inputs = row.getElementsByTagName("input");
					for(var j = 0 ; j < inputs.length;j++) {
						if(inputs[j].getAttribute("attr")==targets) {
							inputs[j].value = $("#"+valueEl).val();
						}
						if(inputs[j].name==targets+"_label") {
							inputs[j].value = getSelLabel(valueEl);
						}
					}
				}
			}
		}
		
		function getSelLabel(valueEl) {
			var el = document.getElementById(valueEl);
			if(el.tagName == "SELECT") {
				var opts = el.getElementsByTagName("option");
				for(var i = 0 ;i < opts.length; i++) {
					if(opts[i].selected) {
						if(opts[i].innerHTML.indexOf("请选择")== -1)
							return opts[i].innerHTML;
						else
							return "";
					}
				}
			}else {
				return el.value;
			}
		}
		
		
		
	</script>
	</head>
	<body onload="init(${zgTorderPlan.state});">
		<table width="100%" cellpadding="0" cellspacing="1"
			style="border: 1px solid #A8CFEB;">
			<thead>
				<tr>
				<td class="toolbar">
				</td>
					<td class="formToolbar">
						<div class="button" style="text-align: left;">
						<c:choose>
							<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '1')}"></c:when>
							<c:otherwise>
							<a href="javascript:"><span  onclick="addPlan('${orderPlanId}',this)"><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
							<a href="javascript:"><span onclick="submitPlan('${orderPlanId}',this)"><img src="<%=iconPath%>/true.gif" />提交审核</span></a>
							</c:otherwise>
						</c:choose>
							<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
						</div>
					</td>
				</tr>
			</thead>
		</table>
		<form
			action="${ctx}/zg/plan/ZgTorderPlanForBatch/saveOrderPlanForBatch.do"
			method="post">
			<input type="hidden" name="orderPlanId" value="${orderPlanId}" />
			<input type="hidden" value="5" name="planType" class="required" />
			<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<td class="title" colspan="8">
							单据编码:${orderPlanId}
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
						<td>
							<input type="hidden" value="${zgTorderPlan.state }"  name="state" id="state"/>
								<input type="hidden" value="${orderPlanId}" name="cuid" id="cuid"
									class="required" size="37" readonly="true" />
						</td>
					</tr>
					<c:if test="${(zgTorderPlan.state eq '2')||(zgTorderPlan.state eq '4')||(zgTorderPlan.state eq '0')}">
					<tr>
						<td class="toolbar">
							<div class="button" style="text-align: left;">
							计划领取数量：
							<input type="text" id="carnumSel" size="8" maxlength="8" onchange="checkNumTitle(this)" value="0"/>
							领料组:
							
							<input type="text" size="10"  maxlength="40" autocompleteTo="true" 
							xtype="tree:1" id="orgId_label" columnNameLower="orgId" bmClassId="FW_ORGANIZATION" 
							column="m.t0_LABEL_CN" />
	   						<input type="hidden" id="orgId_value" />
							
							
							领料人:
							<select style="width:100px" id="userSel">
								<option value="">请选择...</option>
							</select>
							领料时间:
							<input type="text" size="8" dateFlag="true" onchange="checkTime(this.value,'领料时间','planDateInput')" id="planDateInput" value="${obj.planDateString}"/>
							开始时间:
							<select onchange="checkStartEnd(this,'planStartTimeSel','planEndTimeSel');" style="width:80px" id="planStartTimeSel">
								<option value="">请选择...</option>
								<c:forEach items="${times}" var="time">
									<option value="${time.value}">${time.name}</option>
								</c:forEach>
							</select>
							结束时间:
							<select onchange="checkStartEnd(this,'planStartTimeSel','planEndTimeSel');" style="width:80px" id="planEndTimeSel">
								<option value="">请选择...</option>
								<c:forEach items="${times}" var="time">
									<option value="${time.value}">${time.name}</option>
								</c:forEach>
							</select>
							<a href="javascript:"><span onclick="setAll()"><img src="<%=iconPath%>/ico_006.gif" />批量设置</span></a>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</form>
	</body>
</html>
<iframe id="resconfigResult"
	src="${ctx}/zg/plan/ZgTorderPlanbomForBatch/list.do?orderPlanId=${orderPlanId}"
	width="100%" style="height:378px" frameborder="0" marginwidth="0"
	marginheight="0" onload=""></iframe>