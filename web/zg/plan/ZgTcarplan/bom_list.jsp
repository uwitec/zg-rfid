<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
		<base target="_parent" />
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
		<style>
</style>
		<script type="text/javascript">
		$(function() {
			initLayoutOwner();
		});
		function initLayoutOwner() {
			var maxHeight = parent.document.getElementById("bomListFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
		}
		
		function savePlan(id) {
			document.forms[0].submit();
		}
		
		function setAllCheckboxState1(name,state) {
			var elms = document.getElementsByName(name);
			for(var i = 0; i < elms.length; i++) {
				elms[i].checked = state;
				parent.updateBom(elms[i],i);
			}
		}
		
		//思路：这里会去修改整个数据字符串的（某个定位字符串中）的计划领取数量的值
		function checkPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("计划领取数量必须小于可领取数量！");
					obj.value = document.getElementById(num+'oldRealNumForUse').value;
				}
			}else {
				alert("计划领取数量必须为数字！");
				obj.value = document.getElementById(num+'oldRealNumForUse').value;
			}
			document.getElementById(num+'oldRealNumForUse').value=obj.value;
			
			//思路：这里会去修改整个数据字符串的（某个定位字符串中）的计划领取数量的值********************
			//var allData=parent.document.getElementById("itemsValue").value;
			//allData.indexOf(document.getElementById(num+'oldRealNumForUse').value);
			
		}
	
		
	</script>
	</head>
	<body scrolling="no">
		<%@ include file="/commons/messages.jsp"%>
		<div class="grid-panel">
			<form action="${ctx}/zg/plan/ZgTorderPlanbom/saveOrderPlan.do"
				method="post">
				<input type="hidden" name="orderPlanId" value="${orderPlanId}" />
				<div id="grid-data-panel" class="grid-data-panel">
					<table cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<td class="tableHeader" width="3%">
									<input type='checkbox'
										onclick="setAllCheckboxState1('items',this.checked)">
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
								<td class="tableHeader">
									排序日期
								</td>
								<td class="tableHeader">
									订单编码
								</td>
								<td class="tableHeader">
									生产厂
								</td>
								<td class="tableHeader">
									生产线
								</td>
								<td class="tableHeader">
									BOM组件
								</td>
								<td class="tableHeader">
									物料描述
								</td>
								<td class="tableHeader">
									成品物料号
								</td>
								<td class="tableHeader">
									成品描述
								</td>
								<td class="tableHeader">
									需求数量
								</td>
								<td class="tableHeader">
									物料组
								</td>
								<td class="tableHeader">
									论证清单
								</td>
								<td class="tableHeader">
									度量单位
								</td>
								<td class="tableHeader">
									物料备注
								</td>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bomList}" var="obj" varStatus="n">
								<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
								<tr class="${trcss}">
									<c:set var="tempValue"
										value="matnr=${obj.matnr}&lgort=${obj.lgort }&planType=${planType }&orderPlanId=${obj.orderPlanId }&orderBomId=${obj.orderBomId }&orderId=${obj.orderId }&carNum=${obj.carNum }&planNum=${obj.planNum }&orderPlanbomId=${obj.orderPlanbomId }&departmentId=${obj.departmentId }" />
									<c:choose>
										<c:when test="${fn:indexOf(itemsValue,tempValue) == -1}">
											<td width="3%" align="center">
												<input type="checkbox" name="items" id="${n.count-1}item"
													value="matnr=${obj.matnr}&lgort=${obj.lgort }&planType=${planType }&orderPlanId=${obj.orderPlanId }&orderBomId=${obj.orderBomId }&orderId=${obj.orderId }&carNum=${obj.carNum }&planNum=${obj.planNum }&orderPlanbomId=${obj.orderPlanbomId }&departmentId=${obj.departmentId }"
													onclick="parent.updateBom(this,'${n.count-1}');" />
											</td>
										</c:when>
										<c:otherwise>
											<td width="3%" align="center">
												<input type="checkbox" checked="true" name="items"
													id="${n.count-1}item"
													value="matnr=${obj.matnr}&lgort=${obj.lgort }&planType=${planType }&orderPlanId=${obj.orderPlanId }&orderBomId=${obj.orderBomId }&orderId=${obj.orderId }&carNum=${obj.carNum }&planNum=${obj.planNum }&orderPlanbomId=${obj.orderPlanbomId }&departmentId=${obj.departmentId }"
													onclick="parent.updateBom(this,'${n.count-1}');" />
											</td>

										</c:otherwise>
									</c:choose>
									<td>
										<fmt:formatDate value="${obj.planDate}" pattern="yyyy-MM-dd" />
									</td>
									<td>
										${obj.planStartTime}
									</td>
									<td>
										${obj.planEndTime}
									</td>
									<td>
										${fn:substring(obj.pxdat,0,10)}
									</td>
									<td>
										${obj.orderAufnr}
									</td>
									<td>
										${obj.plant}
									</td>
									<td>
										${obj.arbpl}
									</td>
									<td>
										${obj.idnrk }
									</td>
									<td>
										${obj.maktx2 }
									</td>
									<td>
										${obj.matnr}
									</td>
									<td>
										${obj.maktx1}
									</td>
									<td>
										${obj.carNum- obj.planNum}
									</td>
									<td>
										${obj.matkl}
									</td>
									<td>
										${obj.zrzqd}
									</td>
									<td>
										${obj.msehl1}
									</td>
									<td>
										${obj.zbz}
									</td>

								</tr>
							</c:forEach>
							<tr>
								<td colspan="12">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
