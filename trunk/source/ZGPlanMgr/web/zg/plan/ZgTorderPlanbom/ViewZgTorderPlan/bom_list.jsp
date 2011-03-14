<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<style>
		.readOnlyInput {
			border: 0;
			background: transparent;
		}
		body,html {overflow:hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript">
	$(function(){
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
		var maxHeight = top.getContentHeight();
		var tooltableH = document.getElementById("tooltable").offsetHeight;
		document.getElementById("grid-data-panel").style.height = maxHeight - tooltableH - 19 + "px";
	});
	
	var groupList = [];
	<c:forEach items="${roles}" var="role">
	groupList.push({value:'${role.cuid}',labelCn:'${role.labelCn}'});
	</c:forEach>
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
	var times = [];
	<c:forEach items="${times}" var="time">
		times.push({value:'${time.value}',labelCn:'${time.name}'});
	</c:forEach>
		function changeGroup(obj) {
			if(obj.value != "") {
				CommonDwrAction.getUsersByOrgId(obj.value,function(data){
					buildSel("userSel",data);
				});
			}else {
				buildSel("userSel",[]);
			}
			
		}
		
		function setAll() {
			setValues('groups','groupSel');
			setValues('groupOldNames','groupSel');//为隐藏表单域添值
			setValues('employees','userSel');
			setValues('planDates','planDateInput');
			setValues('planStartTimes','planStartTimeSel');
			setValues('planEndTimes','planEndTimeSel');
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
		
		function setValues(targets,valueEl) {
			var items = document.getElementsByName("items");
			var bomTable = document.getElementById("bomTable");
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
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
		
		function clearValue(id) {
			var num=id.replace('userId','');//只有num部分的
			var newValueLabel=document.getElementById(num+"groupName").value;//改变后的值
			var oldValueLabel=document.getElementById(num+"groupOldLabelCn").value;//原来的值
			var panel = document.getElementById(id).parentNode;
			var inputs = panel.getElementsByTagName("input");
			for(var i = 0; i <inputs.length;i++) {
				//思路：如果下拉框的值变成另一个的话，就清空，否则不清空
				if(newValueLabel==oldValueLabel)
					inputs[i].value = document.getElementById(num+"userLable").value;
				else{
					inputs[i].value = "";
					document.getElementById(num+"groupOldLabelCn").value=document.getElementById(num+"groupName").value;//把新的值赋给旧值
				}
			}
		}
		
		function compareTime(obj,startId,endId) {
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
		function savePlan(id) {
			document.forms[0].submit();
		}
		function submitPlan(id) {
				var jsonString = this.buildupSubmitParams();
				if(jsonString!="false"){
					ZgTorderPlanbomDwrAction.saveZgTorderPlanbom(jsonString,id,function(data){
						if(data) {
								window.location.href="${ctx}/zg/plan/ZgTorderPlan/submitOrderPlan.do?orderPlanId=${orderPlanId}"
						}else {
								alert("提交失败！");
						}
					});	
				}
		}
		
		function buildupSubmitParams(){
			var josnString='';
			var count = ${count};
			for (var i=0; i<count; i++) {
				var cuid = document.getElementById(i+"cuid").value;
				var departmentId = document.getElementById(i+"departmentId").value;
				var userId = document.getElementById(i+"userId").value;
				var planDate = document.getElementById(i+"planDate").value;
				var planStartTime = document.getElementById(i+"planStartTime").value;
				var planEndTime = document.getElementById(i+"planEndTime").value;  
				if(departmentId==""){
					var row = i+1;
					alert("第"+row+"行领料组未填写，不能提交");
					return "false";
				} 
				if(planDate==""){
					var row = i+1;
					alert("第"+row+"行领料日期未填写，不能提交");
					return "false";
				}    
				if(planStartTime==""){
					var row = i+1;
					alert("第"+row+"行开始时间未填写，不能提交");
					return "false";
				}    
				if(planEndTime==""){
					var row = i+1;
					alert("第"+row+"行结束时间未填写，不能提交");
					return "false";
				}                    
	            josnString=josnString+'{' ;
	            josnString = josnString +'"cuid":"'+cuid+'",';
	            josnString = josnString +'"departmentId":"'+departmentId+'",';
	            josnString = josnString +'"userId":"'+userId+'",';
	            josnString = josnString +'"planDate":"'+planDate+'",';
	            josnString = josnString +'"planStartTime":"'+planStartTime+'",';
	            josnString = josnString +'"planEndTime":"'+planEndTime+'"},';  
	        }
	        if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
	    }
	    
	    //领料组改变，清空领料人
	    function deptChange(selObj,lableObjName,valueObjName,num){
	    	var value=selValueChange(selObj,lableObjName,valueObjName);
	    	changeGroup1(value,num+'selUserId');
	    	document.getElementById(num+'userId').value="";
	    	document.getElementById(num+'userLable').value="";
	    }
	    
	    //设置领料人下拉框
	    function changeGroup1(value,userSelName) {
			if(value != "") {
				CommonDwrAction.getUsersByOrgId(value,function(data){
					buildSel(userSelName,data);
				});
			}else {
				buildSel(userSelName,[]);
			}
		}
	    
	    function selValueChange(selObj,lableObjName,valueObjName){
	    	selObj.style.display="none";
	    	var index = selObj.selectedIndex; 
			var text = selObj.options[index].text; 
	    	var lableObj= document.getElementById(lableObjName);
	    	var valueObj= document.getElementById(valueObjName);
	    	lableObj.value=text;
	    	valueObj.value=selObj.value;
	    	lableObj.style.display="";
	    	return valueObj.value;
	    }
	    
	    //领料人一列双击事件
	    function editUser(num){
	   		var value=document.getElementById(num+"userId").value;
	    	var obj=document.getElementById(num+"selUserId");
	    	var deptId=document.getElementById(num+"departmentId").value;
	    	obj.style.display="";
	    	 if(obj.options.length==0){
	    		changeGroup1(deptId,num+'selUserId');
	    	}
	    	var obj1=document.getElementById(num+"userLable");
			obj1.style.display="none";
	    	obj.value=value;
	    
	    
	    }
	    
	    //双击时间列的点击时间
	    function editTime(num,selObjName,valueObjName){
	    	var value=document.getElementById(valueObjName).value;
	    	var obj=document.getElementById(selObjName);
	    	var groupOptions=document.getElementById("planStartTimeSel").options;
	    	obj.style.display="";
	    	if(obj.options.length==0){
	    		for(var v=0;v<groupOptions.length;v++){
	    			var opt = document.createElement("option");
					opt.value=groupOptions[v].value;
					opt.innerHTML = groupOptions[v].text;
					obj.appendChild(opt);
	    		}
	    	}
	    	obj.value=value;
	    	var obj1=document.getElementById(valueObjName).style.display="none";
	    }
	    
	   //开始时间改变，校验
	    function timeStartValueChange(selObj,lableObjName,valueObjName,endTimeobjName){
	    	var startTime=selObj.value;
	    	var endTime=document.getElementById(endTimeobjName).value;
	    	checkStartEndTime(selObj,startTime,endTime);
	    	selValueChange(selObj,lableObjName,valueObjName);
	    }
	    
	        
	   //结束时间改变，校验
	    function timeEndValueChange(selObj,lableObjName,valueObjName,startimeobjName){
	    	var endTime=selObj.value;
	    	var startTime=document.getElementById(startimeobjName).value;
	    	checkStartEndTime(selObj,startTime,endTime);
	    	selValueChange(selObj,lableObjName,valueObjName);
	    }
	    
	    //校验开始结束时间
	    function checkStartEndTime(obj,startTime,endTime){
		    if(startTime!=''&&endTime!=''){
		    	if(startTime>=endTime){
		    		alert('开始时间必须小于结束时间!');
		    		obj.value="";
		    		return false;
		    	}
		    }
	    }
	    
	    function checkStartEnd(obj,startTime,endTime){
		    return checkStartEndTime(obj,$("#"+startTime).val(),$("#"+endTime).val());
	    }
	    
	</script>
</head>
<body>
<div id="tooltable">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
				<c:if test="${state=='8'}">
									<a href="javascript:savePlan('${orderPlanId}')"><span><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
				</c:if>
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
				</td>
			</tr>
				<c:if test="${state=='8'}">
			<tr>
				<td class="toolbar">
			
					领料组:
					<select id="groupSel" onchange="changeGroup(this)">
						<option value="">请选择...</option>
						<c:forEach items="${roles}" var="role">
							<option value="${role.cuid}">${role.labelCn}</option>
						</c:forEach>
					</select>
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
				</td>
			</tr>
				</c:if>
		</thead>
	</table>
</div>
<div class="grid-panel">
	<div class="title">领料计划调整</div>
	<div class="toolbar"></div>
	<div id="grid-data-panel" class="grid-data-panel">
		<form action="${ctx}/zg/plan/ZgTorderPlanbom/saveViewOrderPlan.do" method="post">
			<input type="hidden" name="orderPlanId" value="${orderPlanId}"/>
			<table id="bomTable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader">物料编码</td>
						<td class="tableHeader">物料描述</td>
						<td class="tableHeader">单台用量</td>
						<td class="tableHeader">需求数量</td>
						<td class="tableHeader">单位</td>
						<td class="tableHeader">发料仓库</td>
						<td class="tableHeader" style="color: red">领料组</td>
						<td class="tableHeader">领料人</td>
						<td class="tableHeader" style="color: red">领料日期</td>
						<td class="tableHeader" style="color: red">开始时间</td>
						<td class="tableHeader" style="color: red">结束时间</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${bomList}" var="obj" varStatus="n">
					<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<tr class="${trcss}">
						<td width="3%" align="center">
							<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							<input type="hidden" name="orderPlanboms[${n.count-1}].cuid" id="${n.count-1}cuid" value="${obj.cuid }"/>
						</td>
						<td align="left">${obj.idnrk}</td>
						<td align="left">${obj.maktx2}</td>
						<td align="right">${obj.zdtyl}</td>
						<td align="right">${obj.menge}</td>
						<td>${obj.msehl1}</td>
						<td align="left">${obj.lgort}</td>
							<c:if test="${state=='8'}">
								<td  editable="true" editdata="groupList" changehandle='clearValue("${n.count-1}userId")'>
							<input type="hidden" attr="groups" edittype="value" name="orderPlanboms[${n.count-1}].departmentId" id="${n.count-1}departmentId" value="${obj.departmentId}"/>
							<input type="hidden" name="groupOldNames_label" id="${n.count-1}groupOldLabelCn" value="${obj.departmentId_labelCn}" />
							<input type="text" edittype="labelCn" class="readOnlyInput" readonly="readonly" size="8" name="groups_label" id="${n.count-1}groupName" value="${obj.departmentId_labelCn}"/>
						</td>
							</c:if>
							<c:if test="${state=='-1'}">
								<td >
							<input type="hidden" attr="groups" edittype="value" name="orderPlanboms[${n.count-1}].departmentId" id="${n.count-1}departmentId" value="${obj.departmentId}"/>
							<input type="hidden" name="groupOldNames_label" id="${n.count-1}groupOldLabelCn" value="${obj.departmentId_labelCn}" />
							<input type="text" edittype="labelCn" class="readOnlyInput" readonly="readonly" size="8" name="groups_label" value="${obj.departmentId_labelCn}"/>
						</td>
							</c:if>
							<c:if test="${state=='8'}">
							<td editable="true" editdata="getUserData('${n.count-1}departmentId')">
							<input type="hidden" attr="employees" edittype="value" name="orderPlanboms[${n.count-1}].userId" id="${n.count-1}userId" value="${obj.userId}"/>
							<input type="text" edittype="labelCn" class="readOnlyInput" id="${n.count-1}userLable"  name="employees_label" readonly="readonly" size="6" value="${obj.userId_labelCn}"/>
						</td>
							</c:if>
							<c:if test="${state=='-1'}">
							<td >
							<input type="hidden" attr="employees" edittype="value" name="orderPlanboms[${n.count-1}].userId" id="${n.count-1}userId" value="${obj.userId}"/>
							<input type="text" edittype="labelCn" class="readOnlyInput" id="${n.count-1}userLable"  name="employees_label" readonly="readonly" size="6" value="${obj.userId_labelCn}"/>
						</td>
							</c:if>
							<c:if test="${state=='8'}">
							<td>
							<input type="text" onchange="checkTime(this.value,'领料时间','${n.count-1}planDate')"  dateFlag="trueNoImg"  attr="planDates" class="readOnlyInput" readonly="readonly" size="8" name="orderPlanboms[${n.count-1}].planDate" id="${n.count-1}planDate" value="${obj.planDateString}"/>
						</td>
							</c:if>
							<c:if test="${state=='-1'}">
							<td>
							<input type="text"  attr="planDates" class="readOnlyInput" readonly="readonly" size="8" name="orderPlanboms[${n.count-1}].planDate" id="${n.count-1}planDate" value="${obj.planDateString}"/>
						</td>
							</c:if>
							<c:if test="${state=='8'}">
							<td editable="true" editdata="times" changehandle="compareTime(this,'${n.count-1}planStartTime','${n.count-1}planEndTime')">
							<input type="text" attr="planStartTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planStartTime" id="${n.count-1}planStartTime" value="${obj.planStartTime}"/>
						</td>
							</c:if>
							<c:if test="${state=='-1'}">
							<td>
							<input type="text" attr="planStartTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planStartTime" id="${n.count-1}planStartTime" value="${obj.planStartTime}"/>
						</td>
							</c:if>
						<c:if test="${state=='8'}">
							<td editable="true" editdata="times" changehandle="compareTime(this,'${n.count-1}planStartTime','${n.count-1}planEndTime')">
							<input type="text" attr="planEndTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planEndTime" id="${n.count-1}planEndTime" value="${obj.planEndTime}"/>
						</td>
							</c:if>
							<c:if test="${state=='-1'}">
								<td >
							<input type="text" attr="planEndTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planEndTime" id="${n.count-1}planEndTime" value="${obj.planEndTime}"/>
						</td>
							</c:if>
						
						
						
					
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>