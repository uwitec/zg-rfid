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
	<title><%=ZgTorderbom.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			try{
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
							try{
								var H = top.centerFrame.offsetHeight;
								var h = parent.document.getElementById("orderDetailFrame").offsetHeight;
								iframes[i].style.height = ( H - h - 50) +"px";
							}catch(e){}
						}
					}
				}
			}
		}
		function saveBom() {
			$("form:first").submit();
		}
		
		function delBom(){
		    if (!hasOneChecked("items")){
		            alert('请选择要操作的对象!');
		            return;
		    }
		    if (confirm('确定执行[删除]操作?')){
		        document.forms[0].action = "${ctx}/zg/plan/ZgTcarbom/delOrderBomForCarPlan.do";
		        document.forms[0].submit();
		    }
		}
		
		function checkPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("计划领取数量必须小于需求数量！");
					obj.value = "";
				}
			}else {
				alert("计划领取数量必须为数字！");
				obj.value = "";
			}
			document.getElementById('carbomList['+num+'].realNum').value=obj.value;
		}
	</script>
<body>
	<form action="${ctx}/zg/plan/ZgTcarbom/saveCarPlan.do" method="post">
		<input type="hidden" name="carPlanId" value="${carPlanId}"/>
		<c:forEach items="${matnrs}" var="matnr">
		<input type="hidden" name="matnrs" value="${matnr}" />
		</c:forEach>
		<div class="eXtremeTable" >
			<table id="bomTable" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">
				<thead>
					<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader">
							订单编号
						</td>
						<td class="tableHeader">需求数量</td>
						<td class="tableHeader" style="color:red">计划领取数量</td>
						<td class="tableHeader">排序日期</td>
						<td class="tableHeader">销售订单号</td>
						<td class="tableHeader">生产厂</td>
						<td class="tableHeader">生产线</td>
						<td class="tableHeader">物料编号</td>
						<td class="tableHeader">物料描述</td>
						<td class="tableHeader">度量单位</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${bomList}" var="obj" varStatus="n">
					<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<tr class="${trcss}">
						<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj['CUID']}&orderPlanbomId=${obj['ORDER_PLANBOM_ID']}"/>
						</td>
						<td>${obj["AUFNR"]}</td>
						<td>${obj["CAR_NUM"]-obj["COMPLETE_NUM"]}</td>
						<td>
							<input type="hidden" name="carbomList[${n.count-1}].carPlanId" value="${obj['CAR_PLAN_ID']}"/>
							<input type="hidden" name="carbomList[${n.count-1}].cuid" value="${obj['CUID']}"/>
							<input type="hidden" name="carbomList[${n.count-1}].realNum" id="carbomList[${n.count-1}].realNum" value="${obj['PLAN_NUM']}"/>
							<input type="text" maxValue="${obj["CAR_NUM"]-obj["COMPLETE_NUM"]}" onchange="checkPlanNum(this,'${n.count-1}')" size="8" name="carbomList[${n.count-1}].planNum" value="${obj['PLAN_NUM']}"/>
						</td>
						<td>${obj["PXDAT"]}</td>
						<td>${obj["KDAUF"]}</td>
						<td>${obj["PLANT"]}</td>
						<td>${obj["ARBPL"]}</td>
						<td>${obj["MATNR"]}</td>
						<td>${obj["MAKTX1"]}</td>
						<td>${obj["MSEHL1"]}</td>
					</tr>
				</c:forEach>
					<tr>
						<td colspan="10" style="line-height: 13px">&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>