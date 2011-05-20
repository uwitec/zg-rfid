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
	<script src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js" type="text/javascript"></script>
	
	<style>
		.readOnlyInput {
			border: 0;
			background: transparent;
		}
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		$(function() {
			try{
				$("a[attr='viewLink'],input[attr='viewLink']").bind("click",function(e){
					var o = parent.accordion;
					if(o) {
						if(o.accordion( "option" , "active")+'' == '0') {
							o.accordion("activate", 0);
						}
					}
				});
				$("input[dateFlag=true]")
				.datepicker({
					showAnim:'',showOtherMonths: true,
					selectOtherMonths: true,
					dateFormat:"yy-mm-dd"
				});
				if(initLayout) {
					$(window).bind("load",initLayoutOwner);
					$(window).bind("resize",initLayoutOwner);
				}
			}catch(e){}
		});
		function initLayoutOwner() {
			if(parent) {
				var iframes = parent.document.getElementsByTagName("iframe");
				for(var i = 0; i < iframes.length; i++) {
					if(iframes[i].contentWindow == window) {
						if(iframes[i].getAttribute("autolayout") == "true") {
							iframes[i].style.height = (document.body.offsetHeight+20) +"px";
						}
					}
				}
			}
		}
		
		function batchGenerate(action,checkboxName,form){
		    if (!hasOneChecked(checkboxName)){
		            alert('请选择要操作的对象!');
		            return;
		    }
		    form.action = action;
		    form.submit();
		}
		
			
		function save(){
			//乐观锁检验
			if(!checkForGenerate()){
				return;
			}
			batchGenerate('${ctx }/zg/plan/ZgTcarbom/addOrderBomForCarPlan.do?carPlanId=${carPlanId}&orderPlanType=${orderPlanType}','items',document.forms[0]);
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
		
		function buildupSubmitParams(){
			var jsonStr='';
			var items = document.getElementsByName("items");
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		            if (items[i].checked == true){
		            	jsonStr=jsonStr+'{' ;
		           		var params =new QueryString(items[i].value);
		           		var orderPlanbomId=params["orderPlanbomId"];
		           		var planNum =params["planNum"];
		           		var carNum =params["carNum"];
		           		jsonStr = jsonStr + '"orderPlanbomId":"'+orderPlanbomId+'",';
		           		jsonStr = jsonStr + '"carNum":"'+carNum+'",';
		           		jsonStr = jsonStr +'"planNum":"'+planNum+'"},';
		            }
		        }
		    }
		     if(jsonStr.length>0){
	        	jsonStr=jsonStr.substr(0,jsonStr.length-1);
	        }
	        jsonStr='['+jsonStr+']';
	        return jsonStr;
	    }
		
		
	</script>
</head>
<body>
	<div id="grid-panel" class="grid-panel">
	<div class="title">订单管理</div>
		<div class="toolbar">
			<a href="javascript:save()"><span><img src="<%=iconPath%>/true.gif" />保存</span> </a> &nbsp;
			<a href="javascript:history.go(-1)"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
		<form action="${ctx}/zg/plan/ZgTorderPlanbom/saveOrderPlan.do" method="post">
			<input type="hidden" name="carPlanId" value="${carPlanId}"/>
			<table id="bomTable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader">排序日期</td>
						<td class="tableHeader">订单编码</td>
						<td class="tableHeader">生产厂</td>
						<td class="tableHeader">生产线</td>
						<td class="tableHeader">BOM组件</td>
						<td class="tableHeader">物料编码</td>
						<td class="tableHeader">物料描述</td>
						<td class="tableHeader">物料组</td>
						<td class="tableHeader">论证清单</td>
						<td class="tableHeader">度量单位</td>
						<td class="tableHeader">物料备注</td>
						<td class="tableHeader">需求数量</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${bomList}" var="obj" varStatus="n">
					<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<tr class="${trcss}">
						<td width="3%" align="center">
							<input type="checkbox" name="items" value="matnr=${obj.matnr}&lgort=${lgort }&planType=${planType }&orderPlanId=${obj.orderPlanId }&orderBomId=${obj.orderBomId }&orderId=${obj.orderId }&carNum=${obj.carNum }&planNum=${obj.planNum }&orderPlanbomId=${obj.orderPlanbomId }&departmentId=${obj.departmentId }" />
						</td>
						<td>${obj.pxdat}</td>
						<td>${obj.orderAufnr}</td>
						<td>${obj.plant}</td>
						<td>${obj.arbpl}</td>
						<td>${obj.idnrk }</td>
						<td>${obj.matnr}</td>
						<td>${obj.maktx1}</td>
						<td>${obj.matkl}</td>
						<td>${obj.zrzqd}</td>
						<td>${obj.msehl1}</td>
						<td>${obj.zbz}</td>
						<td>${obj.carNum- obj.planNum}</td>
					</tr>
				</c:forEach> 
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>