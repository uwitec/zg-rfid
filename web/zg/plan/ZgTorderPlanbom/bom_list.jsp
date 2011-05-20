<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String sortColumn = (String)request.getAttribute("sortColumn");
String isAsc = (String)request.getAttribute("isAsc");
Map<String,String> map = new HashMap<String,String>();
map.put(sortColumn,isAsc);
%>
<%!
public String getImg(Map<String,String> map,String column,HttpServletRequest request) {
	String image = "";
	if(map.get(column) == null) {
	}else if("true".equals(map.get(column))){
		image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-bottom.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
	}else if("false".equals(map.get(column))){
		image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-top.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
	}
	return image;
}
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
			var items = document.getElementsByName("items");//有多少行
			var bomTable = document.getElementById("bomTable");//表格的id
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {//哪个有打钩
					var row = bomTable.rows[i+1];//表格某一行的对象
					var inputs = row.getElementsByTagName("input");//某一行有标签input，组成一个组合
					for(var j = 0 ; j < inputs.length;j++) {
						if(inputs[j].getAttribute("attr")==targets) {
							//alert($("#"+valueEl).val());
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
		function savePlan(id,obj) {
			obj.disabled=true;
			document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanbom/saveOrderPlan.do";
			document.forms[0].submit();
			obj.disabled=false;
		}
		function submitPlan(id,obj) {
				obj.disabled=true;
				var jsonString = this.buildupSubmitParams(obj);
				if(jsonString!="false"){
					ZgTorderPlanbomDwrAction.saveZgTorderPlanbom(jsonString,id,function(data){
						if(data) {
								window.location.href="${ctx}/zg/plan/ZgTorderPlan/submitOrderPlan.do?orderPlanId=${orderPlanId}"
						}else {
								alert("提交失败！");
								obj.disabled=false;
						}
					});	
				}
		}
		
		function buildupSubmitParams(obj){
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
					obj.disabled=false;
					return "false";
				} 
				if(planDate==""){
					var row = i+1;
					alert("第"+row+"行领料日期未填写，不能提交");
					obj.disabled=false;
					return "false";
				}    
				if(planStartTime==""){
					var row = i+1;
					alert("第"+row+"行开始时间未填写，不能提交");
					obj.disabled=false;
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
	    function queryColumn(obj,column){
		    var value = document.forms.ec["isAsc"].value;
		    var sortColumn = document.forms.ec["sortColumn"].value;
		    if(sortColumn != column) {
		    	value = '';
		    }
		    var nextValue='';
		    if(value==''){
		    	nextValue = 'true';
		    }else if(value == 'true'){
		    	nextValue = 'false';
		    }else if(value == 'false'){
		    	nextValue = '';
		    }
			document.forms.ec["isAsc"].value=nextValue;
			document.forms.ec["sortColumn"].value = column;
			document.forms[0].action=ctx+"/zg/plan/ZgTorderPlanbom/findBomList.do";
			document.forms[0].submit();
		}
	</script>
</head>
<body>
<div id="tooltable">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<a href="javascript:savePlan('${orderPlanId}',this)"><span><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
					<a href="javascript:submitPlan('${orderPlanId}',this)"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
				</td>
			</tr>
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
		</thead>
	</table>
</div>
<div class="grid-panel">
	<div class="title">领料计划调整</div>
	<div class="toolbar"></div>
	<div id="grid-data-panel" class="grid-data-panel">
		<form id="ec" action="${ctx}/zg/plan/ZgTorderPlanbom/findBomList.do" method="post">
			<input type="hidden" name="orderPlanId" value="${orderPlanId}"/>
			<!-- 排序参数 -->
			<input type="hidden" name="sortColumn" value="${sortColumn}"/>
			<input type="hidden" name="isAsc" value="${isAsc}"/>
			<table id="bomTable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader" width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader" onclick="queryColumn(this,'idnrk')">物料编码<%=getImg(map,"idnrk",request)%></td>
						<td class="tableHeader" onclick="queryColumn(this,'maktx1')">物料描述<%=getImg(map,"maktx1",request)%></td>
						<td class="tableHeader" onclick="queryColumn(this,'zdtyl')">单台用量<%=getImg(map,"zdtyl",request)%></td>
						<td class="tableHeader" onclick="queryColumn(this,'menge')">需求数量<%=getImg(map,"menge",request)%></td>
						<td class="tableHeader" onclick="queryColumn(this,'msehl1')">单位<%=getImg(map,"msehl1",request)%></td>
						<td class="tableHeader" onclick="queryColumn(this,'lgort')">发料仓库<%=getImg(map,"lgort",request)%></td>
						<td class="tableHeader" style="color: red" onclick="queryColumn(this,'departmentId_labelCn')">领料组<%=getImg(map,"departmentId_labelCn",request)%></td>
						<td class="tableHeader" onclick="queryColumn(this,'userId_labelCn')">领料人<%=getImg(map,"userId_labelCn",request)%></td>
						<td class="tableHeader" style="color: red" onclick="queryColumn(this,'planDateString')">领料日期<%=getImg(map,"planDateString",request)%></td>
						<td class="tableHeader" style="color: red" onclick="queryColumn(this,'planStartTime')">开始时间<%=getImg(map,"planStartTime",request)%></td>
						<td class="tableHeader" style="color: red" onclick="queryColumn(this,'planEndTime')">结束时间<%=getImg(map,"planEndTime",request)%></td>
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
						<td align="center">${obj.zdtyl}</td>
						<td align="center">${obj.menge}</td>
						<td>${obj.msehl1}</td>
						<td align="center"><c:out value='${obj.lgort_lableCn}'/></td>
						<td editable="true" editdata="groupList" changehandle='clearValue("${n.count-1}userId")'>
							<input type="hidden" attr="groups" edittype="value" name="orderPlanboms[${n.count-1}].departmentId" id="${n.count-1}departmentId" value="${obj.departmentId}"/>
							<input type="hidden" name="groupOldNames_label" id="${n.count-1}groupOldLabelCn" value="${obj.departmentId_labelCn}" />
							<input type="text" edittype="labelCn" class="readOnlyInput" readonly="readonly" size="8" name="groups_label" id="${n.count-1}groupName" value="${obj.departmentId_labelCn}"/>
						</td>
						<td editable="true" editdata="getUserData('${n.count-1}departmentId')">
							<input type="hidden" attr="employees" edittype="value" name="orderPlanboms[${n.count-1}].userId" id="${n.count-1}userId" value="${obj.userId}"/>
							<input type="text" edittype="labelCn" class="readOnlyInput" id="${n.count-1}userLable"  name="employees_label" readonly="readonly" size="6" value="${obj.userId_labelCn}"/>
						</td>
						<td>
							<input type="text" onchange="checkTime(this.value,'领料时间','${n.count-1}planDate')"  dateFlag="trueNoImg"  attr="planDates" class="readOnlyInput" readonly="readonly" size="8" name="orderPlanboms[${n.count-1}].planDate" id="${n.count-1}planDate" value="${obj.planDateString}"/>
						</td>
						<td editable="true" editdata="times" changehandle="compareTime(this,'${n.count-1}planStartTime','${n.count-1}planEndTime')">
							<input type="text" attr="planStartTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planStartTime" id="${n.count-1}planStartTime" value="${obj.planStartTime}"/>
						</td>
						<td editable="true" editdata="times" changehandle="compareTime(this,'${n.count-1}planStartTime','${n.count-1}planEndTime')">
							<input type="text" attr="planEndTimes" edittype="value" class="readOnlyInput" readonly="readonly" size="6" name="orderPlanboms[${n.count-1}].planEndTime" id="${n.count-1}planEndTime" value="${obj.planEndTime}"/>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>