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
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
		<style>
.readOnlyInput {
	border: 0;
	background: transparent;
}
.eXtremeTable {z-index: 2;width:100%;overflow: auto;}
</style>

		<script type="text/javascript">
		
		
		
	$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
				var maxHeight = parent.document.getElementById("listFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
		}
		
		function checkNum(obj) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("本次退料必须小于等退料数量！");
					obj.value = "";
				}
			}else {
				alert("本次退料数量必须为数字！");
				obj.value = "";
			}
		}	
		
		function backBom(num){
			document.getElementById("bomList["+num+"].backNum").style.display="block"
			document.getElementById("bomList["+num+"].backNum").focus();
		}
		
		function buildupSubmitParams(id,state){
			var josnString='';
			
			//<input type="text" name="bomList[${n.count-1}].backNum" id="bomList[${n.count-1}].backNum" maxValue="${obj.WAIT_BACK_NUM}" onchange="checkNum(this)"  style="width:80px" maxlength="13"  style="display:none" />
		//	<input type="hidden" id="bomList[${n.count-1}].orderPlanbomId"
		
			for (var i=0; i<${count}; i++) {
				var backNum=document.getElementById("bomList["+i+"].backNum").value;
				var orderPlanbomId=document.getElementById("bomList["+i+"].orderPlanbomId").value;
				var idnrk=document.getElementById("bomList["+i+"].idnrk").value;
				if(backNum!=''){
					josnString=josnString+'{' ;
					josnString = josnString +'"backNum":'+backNum+',';
					josnString = josnString +'"idnrk":"'+idnrk+'",';
					josnString = josnString +'"orderPlanbomId":"'+orderPlanbomId+'"},';
				}
					
				
			        
			} 
	        if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
	    }
		
	</script>
	</head>
	<% 
	List<SortInfo> list = ((PageRequest)request.getAttribute("pageRequest")).getSortInfos();
	Map map = new HashMap();
	for(SortInfo si:list){
		map.put("ec_s_"+si.getColumnName(),si.getSortOrder());
		String image="";
		if("asc".equalsIgnoreCase(si.getSortOrder())){
			image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-bottom.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
			map.put("ec_image_"+si.getColumnName(),image);
		}else if("desc".equalsIgnoreCase(si.getSortOrder())){
			image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-top.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
			map.put("ec_image_"+si.getColumnName(),image);
		}
	}
%>
	
	<body>
	<form id="ec"
			action="${ctx}/zg/plan/ZgTBomManager/listBackBom.do"
			method="post" validator="true">
					<div id="grid-panel" class="grid-panel">
					<div>
					<%@ include file="query_include_ZgTorderPlan.jsp"%>
				</div>
			<div class="title">退料物料列表</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
					<table cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								
								<td class="tableHeader" width="12%">
									BOM组件编号
								</td>
								<td class="tableHeader">
									BOM组件描述
								</td>
								<td class="tableHeader" width="8%">
									订单编号
								</td>
								<td class="tableHeader">
									生产线
								</td>
								<td class="tableHeader">
									生产厂
								</td>
								<td class="tableHeader" width="8%">
									退料计划编号
								</td>
								<td class="tableHeader">
									基本单位
								</td>
								<td class="tableHeader">
									需退料总量
								</td>
								<td class="tableHeader">
									已退料数量
								</td>
								<td class="tableHeader">
									待退料数量
								</td>
								<td class="tableHeader">
									本次退料
								</td>
							</tr>
							
						</thead>
						<tbody>
							<c:forEach items="${page.result}" var="obj" varStatus="n">
								
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
								
													<tr class="${trcss}"
														onmouseover="this.style.backgroundColor = '#EBF1FF'"
														onmouseout="this.style.backgroundColor = '#FFFFFF'">
										
										
									
									<td align="center">
										${obj.IDNRK}
									</td>
									<td align="left">
										${obj.MAKTX2}
									</td>
									<td align="center">
										${obj.AUFNR}
									</td>
									<td align="center">
										${obj.ARBPL}
									</td>
									<td align="center">
										${obj.PLANT}
									</td>
									<td align="center">
										${obj.PLANID}
									</td>
									<td align="center">
										${obj.MSEHL2}
									</td>
									<td align="center">
										${obj.WAIT_BACK_NUM+obj.BACK_NUM}
									</td>
									<td align="center">
										${obj.BACK_NUM}
									</td>
									<td align="center">
										${obj.WAIT_BACK_NUM}
									</td>
									<td ondblclick="backBom(${n.count-1})" title="双击修改装车数量">
										<input type="text" name="bomList[${n.count-1}].backNum" id="bomList[${n.count-1}].backNum" maxValue="${obj.WAIT_BACK_NUM}" onchange="checkNum(this)"  style="width:80px" maxlength="13"  style="display:none" />
										<input type="hidden" name="bomList[${n.count-1}].orderPlanbomId"  id="bomList[${n.count-1}].orderPlanbomId" value="${obj.CUID }"/>
										<input type="hidden" name="bomList[${n.count-1}].idnrk"  id="bomList[${n.count-1}].idnrk" value="${obj.IDNRK }"/>
										
									</td>
								
									</tr>
								
							</c:forEach>
							
						<tr>
									<td colspan="11">
									<%@include file="/frame/default/ux/pagebar.jsp" %>
									</td>
								</tr>
							
						</tbody>
					</table>
				</div>
		</form>
		
	</body>
</html>
