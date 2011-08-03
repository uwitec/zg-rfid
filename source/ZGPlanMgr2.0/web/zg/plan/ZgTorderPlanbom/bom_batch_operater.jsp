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
	String orderPlanId = request.getParameter("orderPlanId");
	System.out.println(orderPlanId);
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
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
		<style>
		.readOnlyInput {
			border: 0;
			background: transparent;
		}
		</style>
		<script type="text/javascript">
		
		
		
		$(function() {
			try{
				
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
				$("input[dateFlag=trueNoImg]")
				.datepicker({
					showAnim:'',
					showOtherMonths: true,
					changeMonth:true,
					changeYear:true,
					selectOtherMonths: true,
					dateFormat:"yy-mm-dd"
				})
				.css("cursor","pointer");
				initGridEditor();
			}catch(e){}
		});
		
		//保存领料计划
		function addPlan(id) {
			if($("#planDate").val()==''){
				alert("请填写领料时间!");
				return;
			}
			document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanForBatch/saveOrderPlanForBatch.do";
			document.forms[0].submit();
			
		}
		
		function addPlanBom(id){
			var id = '<%=orderPlanId%>';
			if(id=='自动生成'){
				id="";
			}
	
			var url='${ctx}/zg/plan/ZgTorderPlanbomForBatch/createOrderPlanForBatch.do?orderPlanId='+id;
			
			window.location.href = url;
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
		function submitPlan(){
			if($("#planDate").val()==''){
				alert("请填写领料时间!");
				return;
			}
			document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlanForBatch.do";
			document.forms[0].submit();
			//window.location.href="${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlanForBatch.do?orderPlanId=${orderPlanId}";
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
		
		var cuids = new Array();
		function changeCarNum(obj,id){
			checkNum(obj);
			changeOther(id);
		}
		
		function changeOther(id){
			if(cuids.length==0)
				cuids[cuids.length] = id;
			else{
				for(i=0;i<cuids.length;i++){//判断是否有相同的
					if(cuids[i]==id){//如果有相同的话
						return;
					}
				}
				cuids[cuids.length] = id;
			}
		}
		
		function checkNum(obj) {
			var value = obj.value;
			if(!isNumber(value)) {
				alert("计划领取必须为数字！");
				obj.value =0;
			}
		}
		
		//进行资源合并的
		function batchUpdate() {
			
			var judge;//用来判断有没有bom组件在计划单中
			var jsonString = this.buildJsonString();//在这里取修改页面上的东西的
			DWREngine.setAsync(false);//DWR同步
			if(jsonString!="false"){
			//	  1：批量计划单为空的
	       	//    2：批量计划单领用的BOM的数量有为0的
	       	//    3: 有领料人有为空的
	       	//    4: 有领料日期为空的
	       	//    5：有开始时间为空的
	       	//    6: 有结束时间为空的
	       	//    9: 其它异常
	       	//    10：一切正常
					ZgTorderPlanbomDwrAction.updateZgTorderPlanbomCarNum(jsonString,function(data){
						judge=data;
					});
			}
			return judge;
		}
		
		function buildJsonString(){
			var josnString='';
			for(var i=0;i<cuids.length;i++)
			{
				var cuid=cuids[i];
				var carNum = document.getElementById(cuid+"carnum").value;
				var groupId=document.getElementById(cuid+"groupId").value;
				var userId=document.getElementById(cuid+"userId").value;
				var planDate=document.getElementById(cuid+"planDate").value;
				var planStartTime=document.getElementById(cuid+"planStartTime").value;
				var planEndTime=document.getElementById(cuid+"planEndTime").value;
			//	alert(document.getElementById(cuid+"meins"));
				var meins=document.getElementById(cuid+"meins").value;
			//	alert(document.getElementById(cuid+"meins"));
				var msehl=document.getElementById(cuid+"msehl").value;
			//	alert(document.getElementById(cuid+"meins"));
				if(carNum==""){
					alert("计划领取数量不能为空！");
					return "false";
				} 
				josnString=josnString+'{' ;
			    josnString = josnString +'"carNum":"'+carNum+'",';
			    josnString = josnString +'"departmentId":"'+groupId+'",';
			    josnString = josnString +'"userId":"'+userId+'",';
			    josnString = josnString +'"planDate":"'+planDate+'",';
			    josnString = josnString +'"planStartTime":"'+planStartTime+'",';
			    josnString = josnString +'"planEndTime":"'+planEndTime+'",';
			     josnString = josnString +'"meins":"'+meins+'",';
			    josnString = josnString +'"msehl1":"'+msehl+'",';
			    josnString = josnString +'"cuid":"'+cuid+'"},';
			}
			if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
		}
		
		function deleteBom(){
		
			var items=document.getElementsByName("items");
			var orderPlanId = '${orderPlanId}';
		
			var josnString='';
			for(var v=0;v<items.length;v++){
				if(items[v].checked){
					josnString=josnString+'{' ;
					josnString = josnString +'"cuid":"'+items[v].value+'"},';
				}
			}
			if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }else{
	        	alert('请选择需要删除的组件');
	        	return;
	        }
	        josnString='['+josnString+']';
	       // alert(josnString);
	        ZgTorderPlanbomDwrAction.deletePlanbomByCuid(josnString,'${orderPlanId}',"<%=session%>",function(data){
						if(data) {
								alert("保存成功！");
						}else {
								alert("提交失败！");
						}
			});	
			window.location="${ctx}/zg/plan/ZgTorderPlanbomForBatch/list.do?orderPlanId=${orderPlanId}&flag=temp";
			//alert('操作成功');
			//window.location.reload();
		}
		
		//以下的方法是为了加 领料组 领料人 领料时间 开始时间 结束时间
		var groupList = [];
		<c:forEach items="${roles}" var="role">
			groupList.push({value:'${role.cuid}',labelCn:'${role.labelCn}'});
		</c:forEach>
		
		
		var unit = [];
		<c:forEach items="${units}" var="unit">
			unit.push({value:'${unit.meins}',labelCn:'${unit.msehl}'});
		</c:forEach>
		
		var times = [];
		<c:forEach items="${times}" var="time">
			times.push({value:'${time.value}',labelCn:'${time.name}'});
		</c:forEach>
		
		function clearValue(id,cuId) {
			var num=id.replace('userId','');//只有num部分的
			var newValueLabel=document.getElementById(cuId+"groupName").value;//改变后的值
			var oldValueLabel=document.getElementById(cuId+"groupOldLabelCn").value;//原来的值
			var panel = document.getElementById(id).parentNode;
			var inputs = panel.getElementsByTagName("input");
			for(var i = 0; i <inputs.length;i++) {
				//思路：如果下拉框的值变成另一个的话，就清空，否则不清空
				if(newValueLabel==oldValueLabel)
					inputs[i].value = document.getElementById(num+"userLable").value;//userLable
				else{
					inputs[i].value = "";
					document.getElementById(cuId+"groupOldLabelCn").value=document.getElementById(cuId+"groupName").value;//把新的值赋给旧值
				}
			}
			changeOther(cuId);
		}
		
		function getUserData(orgId) {
			var users = [];
			var o = document.getElementById(orgId);
			DWREngine.setAsync(false);
			CommonDwrAction.getUsersByOrgId(o.value,function(data){
				var obj = eval(data);
				for(var i = 0; i < obj.length; i++) {
					users.push({value:obj[i].cuid,labelCn:obj[i].labelCn});
				}
			});
			DWREngine.setAsync(true);
			return users;
		}
		
		function compareTime(obj,startId,endId,cuId) {
			changeOther(cuId);
			var start = document.getElementById(startId).value;
			var end = document.getElementById(endId).value;
			if(start != '' && end != '') {
				if(start > end) {
					alert("开始时间不能大于结束时间！");
					var inputs = obj.getElementsByTagName("input");
					for(var i = 0 ; i < inputs.length;i++) {
						inputs[i].value = "";
					}
				}
			}
		}
		
		//时间不能小于今天
		function checkTimeForBatch(timeStr,name,objName,cuId){
			changeOther(cuId);
			var start  = new Date(timeStr.split(/ /g)[0].replace(/-/g, "/"));	
			var dateStr=start.getDate();
			start.setDate(dateStr+1);
			var nowDate=new Date();
			if(start<=nowDate){
				alert(name+'不能早于当前时间');
				document.getElementById(objName).value="";
				return false;
			}
			return true;
		}
		
	</script>
	</head>
	<body>
		<form
			action="${ctx}/zg/plan/ZgTorderPlanbomForBatch/list.do?orderPlanId=${orderPlanId}"
			method="post" validator="true">
			<input type="hidden" id="zgTorderPlanStateData" value="${zgTorderPlan.state}"/>
			
			<c:if test="${zgTorderPlan.state!='0'}">
			<c:if test="${not empty zgTorderPlanCommentList}">
			<div class="datalist">
				<h2>
					意见历史
				</h2>
					<div class="toolbar">
					</div>
				<div class="eXtremeTable">
					<table border="0" cellspacing="1" cellpadding="0"
						class="tableRegion" width="100%">
						<thead>
							<tr>
								<td class="tableHeader" width="20%">
									审核时间
								</td>
								<td class="tableHeader">
									审核人
								</td>
								<td class="tableHeader">
									审核意见
								</td>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${zgTorderPlanCommentList}" var="objlist" varStatus="nn">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<tr class="${trcss}"
											onmouseover="this.style.backgroundColor = '#EBF1FF'"
											onmouseout="this.style.backgroundColor = '#FFFFFF'">
									<td align="center">
									<fmt:formatDate value="${objlist.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td align="center">
										${objlist.chname}
									</td>
									<td align="center">
										${objlist.content}
									</td>
									</tr>
							</c:forEach>
						</tbody>
						</table>
				</div>
				</div>
			</c:if>
			</c:if>
			
			<div class="datalist">
				<h2>
					BOM组件列表
				</h2>
				<div class="toolbar">
					<c:choose>
						<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '1')}"></c:when>
						<c:otherwise>
							<tr style="padding: 0px;">
								<c:choose>
									<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '1')}"></c:when>
									<c:otherwise>
										<a href="javascript:addPlanBom('${orderPlanId}');"><span><img onclick="addPlanBom('${orderPlanId}');"
													src="<%=iconPath%>/addition.gif" />添加</span> </a>
										&nbsp;<a href="javascript:deleteBom()"><span	><img onclick="deleteBom()"
													src="<%=iconPath%>/ico_009a.gif" />删除</span> </a>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="eXtremeTable">
					<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
						class="tableRegion" width="100%">
						<thead>
							<tr>
								<td class="tableHeader" width="3%">
									<input type='checkbox'
										onclick="setAllCheckboxState('items',this.checked)" />
								</td>
								<td class="tableHeader" width="4%">
									仓库名称
								</td>
								<td class="tableHeader" width="9%">
									BOM组件编号
								</td>
								<td class="tableHeader">
									BOM组件描述
								</td>
								<td class="tableHeader">
									计划领取数量
								</td>
								<c:if test="${zgTorderPlan.state=='2'||zgTorderPlan.state=='8'}">
									<td class="tableHeader">
									审核数量
								</td>
									</c:if>
								<td class="tableHeader">
									单位
								</td>
								<td class="tableHeader">
									领料组
								</td>
								<td class="tableHeader">
									领料人
								</td>
								<td class="tableHeader">
									领料日期
								</td>
								<td class="tableHeader">
									开始时间
								</td>
								<td class="tableHeader">
									结束时间
								</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bomForBatchList}" var="obj" varStatus="n">
								<c:if test="${!obj.isDel}">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<c:choose>
										<c:when test="${obj.state=='9' }">
											<tr class="${trcss}"
												onmouseover="this.style.backgroundColor = '#EBF1FF'"
												onmouseout="this.style.backgroundColor = '#FFFFFF'">
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${zgTorderPlan.state eq '8'}">
													<tr class="${trcss}"
														onmouseover="this.style.backgroundColor = '#EBF1FF'"
														onmouseout="this.style.backgroundColor = '#FFFFFF'">
												</c:when>
												<c:otherwise>
													<tr class="${trcss}"
														onmouseover="this.style.backgroundColor = '#EBF1FF'"
														onmouseout="this.style.backgroundColor = '#FFFFFF'">
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>

									<td width="3%" align="center">
										<input type="checkbox" name="items" value="${obj.cuid}" />
									</td>
									<td align="center">
										${obj.lgort_lableCn}
									</td>
									<td align="center">
										${obj.idnrk}
									</td>
									<td align="center">
										${obj.maktx1}
									</td>
									<td align="center">
									
										<c:choose>
											<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '1')}">${obj.carNum}</c:when>
											<c:otherwise>
												<input type="text" id="${obj.cuid}carnum" maxlength="8" name="carnums_label"
													value="${obj.carNum}"
													onchange="changeCarNum(this,'${obj.cuid}')"
													size="8"/>
											</c:otherwise>
										</c:choose>
									</td>
									
									<c:if test="${zgTorderPlan.state=='2'||zgTorderPlan.state=='8'}">
									<td>
										${obj.auditNum}
											</td>
									</c:if>
									
								
									<c:choose>
										<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '1')}">
										<td>
											${obj.departmentId_labelCn}
										</td>
										<td>
											${obj.userId_labelCn}
										</td>
										<td>
											<fmt:formatDate value='${obj.planDate}' pattern='yyyy-MM-dd'/>
										</td>
										<td>
											${obj.planStartTime}
										</td>
										<td>
											${obj.planEndTime}
										</td>
										</c:when>
										<c:otherwise>
										<td editable="true" editdata="unit" style="width:100px">
											<input style="width:100px" type="hidden" attr="units" edittype="value" name="orderPlanboms[${n.count-1}].meins" id="${obj.cuid}meins" value="${obj.meins }"/>
											<input  style="width:100px" type="text" attr="units" edittype="labelCn" class="readOnlyInput" readonly="readonly" size="8" name="unit_label" id="${obj.cuid}msehl" value="${obj.msehl1}"/>
										</td>
										<td editable="true" editdata="groupList" changehandle="clearValue('${n.count-1}userId','${obj.cuid}')">
											<input type="hidden" attr="groups" edittype="value" name="orderPlanboms[${n.count-1}].departmentId" id="${n.count-1}departmentId"/>
											<input type="hidden" attr="groups" edittype="value" id="${obj.cuid}groupId" value="${obj.departmentId}"/>
											<input type="hidden" name="groupOldNames_label" id="${obj.cuid}groupOldLabelCn" value="${obj.departmentId_labelCn}"/>
											<input type="text" edittype="labelCn" class="readOnlyInput" readonly="readonly" size="8" name="groups_label" id="${obj.cuid}groupName" value="${obj.departmentId_labelCn}"/>
										</td>
										
										
										<td editable="true" editdata="getUserData('${n.count-1}departmentId')" changehandle="changeOther('${obj.cuid}')">
											<input type="hidden" attr="employees" edittype="value" name="orderPlanboms[${n.count-1}].userId" id="${n.count-1}userId"/>
											<input type="hidden" attr="employees" edittype="value" id="${obj.cuid}userId" value="${obj.userId}"/>
											<input type="text" edittype="labelCn" class="readOnlyInput" id="${n.count-1}userLable"  name="employees_label" readonly="readonly" size="6" value="${obj.userId_labelCn}"/>
										</td>
										<td>
										<input type="text" onchange="checkTimeForBatch(this.value,'领料时间','${obj.cuid}planDate','${obj.cuid}')"  dateFlag="trueNoImg"  attr="planDates" class="readOnlyInput" readonly="readonly" size="8" name="orderPlanboms[${n.count-1}].planDate" id="${obj.cuid}planDate" value="<fmt:formatDate value='${obj.planDate}' pattern='yyyy-MM-dd'/>"/>
										</td>
										<td editable="true" editdata="times" changehandle="compareTime(this,'${n.count-1}planStartTime','${n.count-1}planEndTime','${obj.cuid}')">
											<input type="hidden" attr="planStartTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" id="${obj.cuid}planStartTime" value="${obj.planStartTime}"/>
											<input type="text" attr="planStartTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planStartTime" id="${n.count-1}planStartTime" value="${obj.planStartTime}"/>
										</td>
										<td editable="true" editdata="times" changehandle="compareTime(this,'${n.count-1}planStartTime','${n.count-1}planEndTime','${obj.cuid}')">
											<input type="hidden" attr="planEndTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" id="${obj.cuid}planEndTime" value="${obj.planEndTime}"/>
											<input type="text" attr="planEndTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planEndTime" id="${n.count-1}planEndTime" value="${obj.planEndTime}"/>
										</td>
										</c:otherwise>
									</c:choose>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
		<br />
		<br />
		<br />
		<br />
		<br />
	</body>
</html>
