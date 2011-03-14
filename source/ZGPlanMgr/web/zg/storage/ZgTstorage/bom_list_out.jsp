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
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
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
		
		
		//选择生产线及订单半成品
		function addBom(){
			var arbpl=parent.document.getElementById("arbpl").value;
			var orderId=parent.document.getElementById("orderId").value;
			var lgort=parent.document.getElementById("lgort").value;
			var type  = '${type}';
			if(arbpl==''){
				alert('请先选择订单号和生产线');
				return;
			}
		
			var url='';
			if(type=='1'){
				url='${ctx}/zg/storage/ZgTstoragebom/queryBomlListByArbplOrderId.do?storageId=${id}&arbpl='+arbpl+'&orderId='+orderId+'&type=${type}&productType=${productType}';
			}else if(type=='2'){
				url='${ctx}/zg/storage/ZgTstoragebom/queryBomlListByArbplOrderId.do?storageId=${id}&arbpl='+arbpl+'&orderId='+orderId+'&type=${type}&productType=${productType}&lgort='+lgort;
			}
			//alert(type+':  '+url);
			var sFeatures="dialogHeight: 500px;dialogWidth:860px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
			window.location.href ='${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${id}&type=${type}&productType=${productType}&flag=temp';
			if(returnValue!=undefined){
					alert(returnValue);
			}
			window.location.reload();
		}
		
		function addBom1(){
			
			var arbpl=parent.document.getElementById("arbpl").value;
			var orderId=parent.document.getElementById("orderId").value;
			var lgort=parent.document.getElementById("lgort").value;
			
			//alert(arbpl+'   '+orderId+'  '+lgort);
				
			if(arbpl==''){
				alert('请先选择订单号和生产线');
				return;
			}
			
			var url='${ctx}/zg/storage/ZgTstoragebom/create.do?storageId=${id}&arbpl='+arbpl+'&orderId='+orderId+'&productType=${productType }&type=${type}&lgort='+lgort;
			var dialogHeight=390;
			var dialogWidth=800;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			
			window.location.href ='${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${id}&type=${type}&productType=${productType}&flag=temp';
			if(result!=undefined){
					alert(result);
			}
			window.location.reload();
		}
		
		function checkNum(obj,allNum) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("实际出库数量必须小于可出库数量！");
					obj.value = allNum;
				}
			}else {
				alert("实际出库数量必须为数字！");
				obj.value =allNum;
			}
		}
		
		function saveBom(){
			var form=document.forms[0];
			form.action="${ctx}/zg/storage/ZgTstoragebom/saveInOutBom.do";
			form.submit();
		}
		
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
			<form action="${ctx}/zg/plan/ZgTorderPlanbom/saveOrderPlan.do"
				method="post">
				<input type="hidden" name="orderPlanId" value="${orderPlanId}" />
				<input type="hidden" name="id" value="${id}"}/>
				<input type="hidden" name="productType" value="${productType }"/>
					<input type="hidden" name="type" value="${type }"/>
				<div>
				
	
		<div>
		</div>
		<div class="datalist">
			<h2>订单管理</h2>
			<div class="toolbar">
			<c:if test="${ model.state !='8'}">
					<a href="javascript:addBom()"><span><img onclick="addBom()" src="<%=iconPath%>/addition.gif" />添加</span></a>
					<a href="javascript:batchDelete('${ctx}/zg/storage/ZgTstorage/deleteBom.do','items',document.forms[0])"><span><img src="<%=iconPath%>/ico_009a.gif" />删除</span></a>
				
						</c:if>
			</div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
					<tr>
						<td class="tableHeader" width="3%">
							<input type='checkbox'
								onclick="setAllCheckboxState('items',this.checked)"/>
						</td>
						<td class="tableHeader">
							生产订单编号
						</td>
						<td class="tableHeader">
							生产线
						</td>
						<td class="tableHeader">
							半成品物料号
						</td>
						<td class="tableHeader">
							半成品名称
						</td>
						<td class="tableHeader">
							半成品单位
						</td>
						<td class="tableHeader">
							可出库数量
						</td>
						<td class="tableHeader">
							实际出库数量
						</td>
						<td class="tableHeader">
							备注
						</td>
					</tr>
					<tbody>
						<c:forEach items="${bomEList}" var="obj" varStatus="n">
						<c:if test="${!obj.isDel}">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
							<c:if test="${ model.state !='8'}">
							<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'"
								>
							</c:if>
							<c:if test="${ model.state =='8'}">
							<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
							
							</c:if>
							<td width="3%" align="center">
									<input type="checkbox" name="items"
										value="cuid=${obj.cuid}" />
								</td>
								<td>
									${obj.orderAufnr}
								</td>
								<td>
									${obj.arbpl}
								</td>
								<td>
									${obj.matnr}
								</td>
								<td>
									${obj.idnrk}
								</td>
								<td>
									${obj.msehl1 }
								</td>
								<td>
									${obj.menge}
								</td>
								<td>
								<input type="hidden" name="bomList[${n.count-1}].cuid" value="${obj.cuid}"/>
								<input type="text" maxValue="${obj.menge}" maxlength="13" onchange="checkNum(this,'${obj.menge}')" size="8" name="bomList[${n.count-1}].num" value="${obj.num}"/>
								</td>
								<td>
									<input type="text" maxLength="85"  size="8" name="bomList[${n.count-1}].zbz" value="${obj.zbz}"/>

								</td>
							</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="12" style="line-height: 13px">
								&nbsp;
							</td>
						</tr>
					</tbody>
				</table>
					</div>
	</div>
			</form>
	</body>
</html>
