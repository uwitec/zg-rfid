<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<title>领料员任务界面</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTorderDwrAction.js"></script>


	<script>
		var num=0;
		var groupId="${planGroupList[0].cuid}";
		function opensub()
		{
		window.open("zc.htm", "newwindow", "height=450, width=800,scrollbars=yes, resizable=yes, toolbar=no, menubar=no, status=no");
		}
		
			$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
			var form =document.getElementById("form0");
			if(form!=null){
				form.submit();
			}
		      
		}
		function doLayout() {
			var maxHeight = top.getContentHeight();
			var maxWidth = top.getContentWidth();
			document.getElementById("_queryPanel").style.height = maxHeight + 'px';
			var listFrameH0 = maxHeight - 78-document.getElementById("contentDiv0").offsetHeight;
			var listFrameH1 = maxHeight -78-document.getElementById("contentDiv1").offsetHeight;
			var listFrameH2 = maxHeight -78-document.getElementById("contentDiv2").offsetHeight;
			var listFrameH3 = maxHeight -78-document.getElementById("contentDiv3").offsetHeight;
			var listFrameH4 = maxHeight -78-document.getElementById("contentDiv4").offsetHeight;
			var listFrameH5 = maxHeight -78-document.getElementById("contentDiv5").offsetHeight;
		//	alert(listFrameH0+"   "+maxWidth);
			document.getElementById("listFrame0").style.height = listFrameH0 + 'px';
			document.getElementById("listFrame1").style.height = listFrameH1 + 'px';
			document.getElementById("listFrame2").style.height = listFrameH2 + 'px';
			document.getElementById("listFrame3").style.height = listFrameH3 + 'px';
			document.getElementById("listFrame4").style.height = listFrameH4 + 'px';
			document.getElementById("listFrame5").style.height = listFrameH5 + 'px';
			document.getElementById("listFrame0").style.width = maxWidth + 'px';
			document.getElementById("listFrame1").style.width = maxWidth + 'px';
			document.getElementById("listFrame2").style.width = maxWidth + 'px';
			document.getElementById("listFrame3").style.width = maxWidth + 'px';
			document.getElementById("listFrame4").style.width = maxWidth + 'px';
			document.getElementById("listFrame5").style.width = maxWidth + 'px';
		}
		
		/* tabpages */
		function nTabs(tabObj, obj) {
		    var tabList = document.getElementById(tabObj).getElementsByTagName("li");
		//   	alert( obj.id);
		 //  alert(tabList);
		//    alert(tabList.length);
		    for (i = 0; i < tabList.length; i++) {
		    //	alert(tabList[i].id+"    "+ obj.id);
		        if (tabList[i].id == obj.id) {
		        	//alert(i);
		        	if(i==0){
		        		groupId="${planGroupList[0].cuid}";
		        	}
		        	if(i==1){
		        		groupId="${planGroupList[1].cuid}";
		        	}
		        	if(i==2){
		        		groupId="${planGroupList[1].cuid}";
		        	}
		        	if(i==3){
		        		groupId="${planGroupList[1].cuid}";
		        	}
		        	if(i==4){
		        		groupId="${planGroupList[1].cuid}";
		        	}
		        	if(i==5){
		        		groupId="${planGroupList[1].cuid}";
		        	}
		            document.getElementById(tabObj + "_title" + i).className = "current";
		            document.getElementById(tabObj + "_content" + i).style.display = "block";
		            doLayout();
		            num=i;
		            var form=document.getElementById('form'+i);
		     	  	form.submit();
		        } else {
		            document.getElementById(tabObj + "_title" + i).className = "";
		            document.getElementById(tabObj + "_content" + i).style.display = "none";
		        }
		    }
		}
		
		function plantOnChange(plant){
		//	if("${orderPlanType}"=="ABE"){
				window.location="${ctx}/zg/plan/ZgTcarplan/query1.do?type=2&arbpl=${arbpl}&orderPlanType=${orderPlanType}&plant="+plant;
		//	}else{
		//		window.location="${ctx}/zg/plan/ZgTcarplan/query1.do?type=2&orderPlanType=${orderPlanType}&plant="+plant;
		//	}
			
		}
		function orderOnChange(orderInfo){
   			var orderArr=orderInfo.split("-");
   			$("#s_groupId5").val(orderArr[0]);
   			$("#s_advance5").val(orderArr[1]);
			document.getElementById("aufnr_span").innerText=orderArr[2];
			var groupTab=document.getElementById("tab_title5");
			nTabs('tab',groupTab);
			ZgTorderDwrAction.getOrderListByPlanGroupId(orderArr[0],function(data){
				$("#order_span1").attr('innerText',orderArr[2]);
				$("#arbpl_span1").attr('innerText',data[0].arbpl);
				$("#maktx2_span1").attr('innerText',data[0].maktx2);
				$("#kdauf_span1").attr('innerText',data[0].kdauf);
				$("#plant_span1").attr('innerText',data[0].plant);
				$("#maktx1_span1").attr('innerText',data[0].maktx1);
				$("#matnr_span1").attr('innerText',data[0].matnr);
				$("#pmenge_span1").attr('innerText',data[0].pmenge);
				$("#psmng_span1").attr('innerText',data[0].psmng);
				$("#pxdat_span1").attr('innerText',data[0].pxdat.toLocaleDateString().replace("年","-").replace("月","-").replace("日",""));
				$("#pcdat_span1").attr('innerText',data[0].pcdat.toLocaleDateString().replace("年","-").replace("月","-").replace("日",""));
				
				
			});
		}
		
	</script>
    <style>
    body,html {overflow:hidden}
        .graph
        {
            position: relative;
            width: 100%;
            border: 1px solid #B1D632;
            padding: 2px;
            margin-bottom: .5em;
        }
        .graph .bar
        {
            display: block;
            position: relative;
            background: #B1D632;
            text-align: center;
            color: #333;
            height: 2em;
            line-height: 2em;
        }
        .graph .bar span
        {
            position: absolute;
            left: 1em;
        }
        .carfont
        {
            font-size: 14px;
            font-style: normal;
            color: #FF0000;
            text-decoration: underline;
            font-family: 宋体, Arial, Helvetica, sans-serif;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <!--基本信息-->
    <div  id="_queryPanel">
	<table id="tableInfo1" width="100%" cellpadding="0" cellspacing="1" class="formitem">
        <caption>
        <c:choose>
        <c:when test="${orderPlanType=='ABC'}">预焊线今日领料
        	&nbsp;&nbsp;&nbsp;&nbsp;生产厂：
	         <select name="plant" id="plant" style="width:100px" onChange="plantOnChange(this.value)">
	       		<c:forEach items="${plantListABC}" var="obj">
	       		 	<c:if test="${obj.ORG_ID!=defaultPlant }"><option value="${ obj.ORG_ID}"   >${ obj.ORG_ID}</option></c:if>
 					<c:if test="${obj.ORG_ID==defaultPlant }"><option value="${ obj.ORG_ID}" selected  >${ obj.ORG_ID}</option></c:if>	       		
 				</c:forEach>
	        </select>
        </c:when>
         <c:when test="${orderPlanType=='ABD'}">预装线今日领料
         	&nbsp;&nbsp;&nbsp;&nbsp;生产厂：
	         <select name="plant" id="plant" style="width:100px" onChange="plantOnChange(this.value)">
	       		<c:forEach items="${plantListABD}" var="obj">
	       		 	<c:if test="${obj.ORG_ID!=defaultPlant }"><option value="${ obj.ORG_ID}"   >${ obj.ORG_ID}</option></c:if>
 					<c:if test="${obj.ORG_ID==defaultPlant }"><option value="${ obj.ORG_ID}" selected  >${ obj.ORG_ID}</option></c:if>	       		
 				</c:forEach>
	        </select>
         </c:when>
          <c:when test="${orderPlanType=='ABE'}">总装线今日领料
          	&nbsp;&nbsp;&nbsp;&nbsp;生产厂：
	         <select name="plant" id="plant" style="width:100px" onChange="plantOnChange(this.value)">
	       		<c:forEach items="${plantListABE}" var="obj">
	       		 	<c:if test="${obj.ORG_ID!=defaultPlant }"><option value="${ obj.ORG_ID}"   >${ obj.ORG_ID}</option></c:if>
 					<c:if test="${obj.ORG_ID==defaultPlant }"><option value="${ obj.ORG_ID}"  selected >${ obj.ORG_ID}</option></c:if>	       		
 				</c:forEach>
	        </select>
	        </c:when>
	        <c:when test="${orderPlanType=='CHANGE'}">换料领料单
          	&nbsp;&nbsp;&nbsp;&nbsp;生产厂：
	         <select name="plant" id="plant" style="width:100px" onChange="plantOnChange(this.value)">
	       		<c:forEach items="${plantListCHANGE}" var="obj">
	       		 	<c:if test="${obj.ORG_ID!=defaultPlant }"><option value="${ obj.ORG_ID}"   >${ obj.ORG_ID}</option></c:if>
 					<c:if test="${obj.ORG_ID==defaultPlant }"><option value="${ obj.ORG_ID}"  selected >${ obj.ORG_ID}</option></c:if>	       		
 				</c:forEach>
	        </select>
          </c:when>
        </c:choose>
        
      &nbsp;&nbsp;&nbsp;&nbsp;  更多订单: 
      <select name="aufnr" id="aufnr" style="width:100px" onChange="orderOnChange(this.value)">
      <option value=""   >请选择</option>
	       		<c:forEach items="${planGroupList}" var="obj">
	       		 	<option value="${ obj.cuid}-${obj.advance }-${obj.labelCn }"   >${ obj.labelCn}</option>
 				</c:forEach>
	        </select>
        </caption>
    </table>    
    <!-- tab页面1 -->
    <div class="tabpage" id="tab">
        <ul>
        <c:forEach items="${planGroupList}" var="group" varStatus="n" begin="0" end="4">
         <form id="form${n.count-1 }" action="${ctx}/zg/plan/ZgTcarplan/list.do" method="post" target="listFrame${n.count-1 }">
           <input type="hidden" name="bmClassId" value="<%=ZgTorderPlan.BM_CLASS_ID%>"/>
			<input type="hidden" name="s_planType" value="${orderPlanType}"/>
			<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
			<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
			<input type="hidden" name="s_arbpl" value="${arbpl }"/>
			<input type="hidden" name="s_groupId" value="${group.cuid }"/>
			<input type="hidden" name="onload" id="onload" value="${onload }"/>
			<input type="hidden" name="s_advance" id="s_advance" value="${group.advance }"/>
        	<c:choose>
        		<c:when test="${n.count==1}">
        		<input type="hidden" name="s_groupId0" id="s_groupId0" value="${group.cuid }"/>
        			<li id="tab_title0" onclick="nTabs('tab',this);" class="current"><a href="javascript:">
               	 	<span>
               	 	<c:choose>
						<c:when test="${fn:length(orderList0)==1}">
							<c:forEach items="${orderList0}" var="obj">${obj.aufnr}(当前订单)</c:forEach>
						</c:when>
						<c:otherwise> 
						<c:forEach items="${orderList0}" var="obj" begin="0" end="0">${obj.aufnr}(合并订单)</c:forEach>
						</c:otherwise>
               	 	</c:choose>
               	 	</span></a></li>
        		</c:when>
        		<c:when test="${n.count==2}">
        		<input type="hidden" name="s_groupId1" id="s_groupId1" value="${group.cuid }"/>
        			<li id="tab_title1" onclick="nTabs('tab',this);"><a href="javascript:">
               	 	<span>
               	 	<c:choose>
						<c:when test="${fn:length(orderList1)==1}">
							<c:forEach items="${orderList1}" var="obj">${obj.aufnr}</c:forEach>
						</c:when>
						<c:otherwise> 
						<c:forEach items="${orderList1}" var="obj" begin="0" end="0">${obj.aufnr}(合并订单)</c:forEach>
						</c:otherwise>
               	 	</c:choose>
               	 	</span></a></li>
        		</c:when>
        		<c:when test="${n.count==3}">
        		<input type="hidden" name="s_groupId2" id="s_groupId2" value="${group.cuid }"/>
        			<li id="tab_title2" onclick="nTabs('tab',this);"><a href="javascript:">
               	 	<span>
               	 	<c:choose>
						<c:when test="${fn:length(orderList2)==1}">
							<c:forEach items="${orderList2}" var="obj">${obj.aufnr}</c:forEach>
						</c:when>
						<c:otherwise> 
						<c:forEach items="${orderList2}" var="obj" begin="0" end="0">${obj.aufnr}(合并订单)</c:forEach>
						</c:otherwise>
               	 	</c:choose>
               	 	</span></a></li>
        		</c:when>
        			<c:when test="${n.count==4}">
        			<input type="hidden" name="s_groupId3" id="s_groupId3" value="${group.cuid }"/>
        			<li id="tab_title3" onclick="nTabs('tab',this);"><a href="javascript:">
               	 	<span>
               	 	<c:choose>
						<c:when test="${fn:length(orderList3)==1}">
							<c:forEach items="${orderList3}" var="obj">${obj.aufnr}</c:forEach>
						</c:when>
						<c:otherwise> 
						<c:forEach items="${orderList3}" var="obj" begin="0" end="0">${obj.aufnr}(合并订单)</c:forEach>
						</c:otherwise>
               	 	</c:choose>
               	 	</span></a></li>
        		</c:when>
        			<c:when test="${n.count==5}">
        		<input type="hidden" name="s_groupId4" id="s_groupId4" value="${group.cuid }"/>
        			<li id="tab_title4" onclick="nTabs('tab',this);"><a href="javascript:">
               	 	<span>
               	 	<c:choose>
						<c:when test="${fn:length(orderList4)==1}">
							<c:forEach items="${orderList4}" var="obj">${obj.aufnr}</c:forEach>
						</c:when>
						<c:otherwise> 
						<c:forEach items="${orderList4}" var="obj" begin="0" end="0">${obj.aufnr}(合并订单)</c:forEach>
						</c:otherwise>
               	 	</c:choose>
               	 	</span></a></li>
        		</c:when>
        	</c:choose>
        	
        	</form>
        </c:forEach>
        
        
        
        <form id="form5" action="${ctx}/zg/plan/ZgTcarplan/list.do" method="post" target="listFrame5">
           <input type="hidden" name="bmClassId" value="<%=ZgTorderPlan.BM_CLASS_ID%>"/>
			<input type="hidden" name="s_planType" value="${orderPlanType}"/>
			<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
			<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
			<input type="hidden" name="s_arbpl" value="${arbpl }"/>
			<input type="hidden" name="s_groupId" id="s_groupId5" value="${group.cuid }"/>
			<input type="hidden" name="onload" id="onload" value="${onload }"/>
			<input type="hidden" name="s_advance" id="s_advance5" value="${group.advance }"/>
        		<input type="hidden" name="s_groupId5" id="s_groupId5" value="${group.cuid }"/>
        			<li id="tab_title5" onclick="nTabs('tab',this);" class="current"><a href="javascript:">
               	 	<span id="aufnr_span">
               	 	
               	 	</span></a></li>
        	</form>
        
        
        
        
        </ul>
         <div class="main">
        <div id="tab_content0">
        <div id="contentDiv0">
            <table class="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
					</td>
				</tr>
			</thead>
			
			<c:forEach items="${orderList0}" var ="obj" begin="0" end ="0">
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
					<th width="10%">
						订单排产日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						订单排序日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产订单编号：
					</th>
					<td width="15%">
					${obj.aufnr }
					</td>
					<th width="10%">
						生产线：
					</th>
					<td width="15%">
					${obj.arbpl }
					</td>
						
					
				</tr>
				<tr>
<th width="10%">
						客户机型：
					</th>
					<td width="15%">
						${obj.maktx2 }
					</td>
					<th width="10%">
						销售单号：
					</th>
					<td width="15%">
					${obj.kdauf }
					</td>
					<th width="10%">
						生产厂：
					</th>
					<td width="15%">
							${obj.plant }
					</td>
					<th width="10%">
						公司机型：
					</th>
					<td >
						${obj.maktx1 }
					</td>
					
				
				</tr>
				<tr><th width="10%">
						成品需求数量：
					</th>
					<td>
						${obj.psmng }
					</td>
					
					<th width="10%">
						成品排序数量：
					</th>
					<td width="15%">
					${obj.pmenge }
					</td>
					<th width="10%">
						成品编号：
					</th>
					<td width="15%">
							${obj.matnr }
					</td>
					<c:if test="${fn:length(orderList0)>1}">
						<c:set var="orders" value=""></c:set>
								<c:forEach items="${orderList0}" var="obj" varStatus="n">
									<c:if test="${n.count==1}">
										<c:set var="orders" value="${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count>1}">
										<c:set var="orders" value="${orders},${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count%12==0}">
										<c:set var="orders" value="${orders}<br/>"></c:set>
									</c:if>
								</c:forEach>
					<th width="10%">
					生产订单明细：
					</th>
					<td width="15%"   title="${fn: replace(orders,'<br/>','')}">
								<c:if test="${fn:length(orders)>20}">
								${fn:substring(orders,0,20)}...
								</c:if>
									<c:if test="${fn:length(orders)<=20}">
								${orders }
								</c:if>
							</td>
						
						</c:if>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		</div>
		            	<iframe id="listFrame0" src="" name="listFrame0" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
		
        </div>
        <div id="tab_content1" class="none">
         <div id="contentDiv1">
            <table class="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
					</td>
				</tr>
			</thead>
			
			<c:forEach items="${orderList1}" var ="obj" begin="0" end ="0">
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
					<th width="10%">
						生产排产日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产排序日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产订单编号：
					</th>
					<td width="15%">
					${obj.aufnr }
					</td>
					<th width="10%">
						生产线：
					</th>
					<td width="15%">
					${obj.arbpl }
					</td>
						
					
				</tr>
				<tr>
<th width="10%">
						客户机型：
					</th>
					<td width="15%">
						${obj.maktx2 }
					</td>
					<th width="10%">
						销售单号：
					</th>
					<td width="15%">
					${obj.kdauf }
					</td>
					<th width="10%">
						生产厂：
					</th>
					<td width="15%">
							${obj.plant }
					</td>
					<th width="10%">
						公司机型：
					</th>
					<td >
						${obj.maktx1 }
					</td>
					
				
				</tr>
				<tr><th width="10%">
						成品需求数量：
					</th>
					<td>
						${obj.psmng }
					</td>
					
					<th width="10%">
						成品排序数量：
					</th>
					<td width="15%">
					${obj.pmenge }
					</td>
					<th width="10%">
						成品编号：
					</th>
					<td width="15%">
							${obj.matnr }
					</td>
					<c:if test="${fn:length(orderList1)>1}">
						<c:set var="orders" value=""></c:set>
								<c:forEach items="${orderList1}" var="obj" varStatus="n">
									<c:if test="${n.count==1}">
										<c:set var="orders" value="${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count>1}">
										<c:set var="orders" value="${orders},${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count%12==0}">
										<c:set var="orders" value="${orders}<br/>"></c:set>
									</c:if>
								</c:forEach>
					<th width="10%">
					生产订单明细：
					</th>
					<td width="15%"  colspan="3" title="${fn: replace(orders,'<br/>','')}">
								<c:if test="${fn:length(orders)>20}">
								${fn:substring(orders,0,20)}...
								</c:if>
									<c:if test="${fn:length(orders)<=20}">
								${orders }
								</c:if>
							</td>
						
						</c:if>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		</div>
            <!-- 列表框 -->
          <iframe id="listFrame1" src="" name="listFrame1" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
        </div>
        
        
        <div id="tab_content2" class="none">
         <div id="contentDiv2">
            <table class="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
					</td>
				</tr>
			</thead>
			
			<c:forEach items="${orderList2}" var ="obj" begin="0" end ="0">
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
					<th width="10%">
						生产排产日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产排序日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产订单编号：
					</th>
					<td width="15%">
					${obj.aufnr }
					</td>
					<th width="10%">
						生产线：
					</th>
					<td width="15%">
					${obj.arbpl }
					</td>
						
					
				</tr>
				<tr>
				<th width="10%">
						客户机型：
					</th>
					<td width="15%">
						${obj.maktx2 }
					</td>
					<th width="10%">
						销售单号：
					</th>
					<td width="15%">
					${obj.kdauf }
					</td>
					<th width="10%">
						生产厂：
					</th>
					<td width="15%">
							${obj.plant }
					</td>
					<th width="10%">
						公司机型：
					</th>
					<td >
						${obj.maktx1 }
					</td>
					
				
				</tr>
				<tr><th width="10%">
						成品需求数量：
					</th>
					<td>
						${obj.psmng }
					</td>
					
					<th width="10%">
						成品排序数量：
					</th>
					<td width="15%">
					${obj.pmenge }
					</td>
					<th width="10%">
						成品编号：
					</th>
					<td width="15%">
							${obj.matnr }
					</td>
					<c:if test="${fn:length(orderList2)>1}">
						<c:set var="orders" value=""></c:set>
								<c:forEach items="${orderList2}" var="obj" varStatus="n">
									<c:if test="${n.count==1}">
										<c:set var="orders" value="${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count>1}">
										<c:set var="orders" value="${orders},${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count%12==0}">
										<c:set var="orders" value="${orders}<br/>"></c:set>
									</c:if>
								</c:forEach>
					<th width="10%">
					生产订单明细：
					</th>
					<td width="15%"  colspan="3" title="${fn: replace(orders,'<br/>','')}">
								<c:if test="${fn:length(orders)>20}">
								${fn:substring(orders,0,20)}...
								</c:if>
									<c:if test="${fn:length(orders)<=20}">
								${orders }
								</c:if>
							</td>
						
						</c:if>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		</div>
            <!-- 列表框 -->
          <iframe id="listFrame2" src="" name="listFrame2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
        </div>
        
        <div id="tab_content3" class="none">
         <div id="contentDiv3">
            <table class="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
					</td>
				</tr>
			</thead>
			
			<c:forEach items="${orderList3}" var ="obj" begin="0" end ="0">
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
					<th width="10%">
						生产排产日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产排序日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产订单编号：
					</th>
					<td width="15%">
					${obj.aufnr }
					</td>
					<th width="10%">
						生产线：
					</th>
					<td width="15%">
					${obj.arbpl }
					</td>
						
					
				</tr>
				<tr>
				<th width="10%">
						客户机型：
					</th>
					<td width="15%">
						${obj.maktx2 }
					</td>
					<th width="10%">
						销售单号：
					</th>
					<td width="15%">
					${obj.kdauf }
					</td>
					<th width="10%">
						生产厂：
					</th>
					<td width="15%">
							${obj.plant }
					</td>
					<th width="10%">
						公司机型：
					</th>
					<td >
						${obj.maktx1 }
					</td>
					
				
				</tr>
				<tr><th width="10%">
						成品需求数量：
					</th>
					<td>
						${obj.psmng }
					</td>
					
					<th width="10%">
						成品排序数量：
					</th>
					<td width="15%">
					${obj.pmenge }
					</td>
					<th width="10%">
						成品编号：
					</th>
					<td width="15%">
							${obj.matnr }
					</td>
					<c:if test="${fn:length(orderList3)>1}">
						<c:set var="orders" value=""></c:set>
								<c:forEach items="${orderList3}" var="obj" varStatus="n">
									<c:if test="${n.count==1}">
										<c:set var="orders" value="${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count>1}">
										<c:set var="orders" value="${orders},${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count%12==0}">
										<c:set var="orders" value="${orders}<br/>"></c:set>
									</c:if>
								</c:forEach>
					<th width="10%">
					生产订单明细：
					</th>
					<td width="15%"  colspan="3" title="${fn: replace(orders,'<br/>','')}">
								<c:if test="${fn:length(orders)>20}">
								${fn:substring(orders,0,20)}...
								</c:if>
									<c:if test="${fn:length(orders)<=20}">
								${orders }
								</c:if>
							</td>
						
						</c:if>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		</div>
            <!-- 列表框 -->
          <iframe id="listFrame3" src="" name="listFrame3" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
        </div>
        
        <div id="tab_content4" class="none">
         <div id="contentDiv4">
            <table class="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
					</td>
				</tr>
			</thead>
			
			<c:forEach items="${orderList4}" var ="obj" begin="0" end ="0">
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
					<th width="10%">
						生产排产日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产排序日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产订单编号：
					</th>
					<td width="15%">
					${obj.aufnr }
					</td>
					<th width="10%">
						生产线：
					</th>
					<td width="15%">
					${obj.arbpl }
					</td>
						
					
				</tr>
				<tr>
				<th width="10%">
						客户机型：
					</th>
					<td width="15%">
						${obj.maktx2 }
					</td>
					<th width="10%">
						销售单号：
					</th>
					<td width="15%">
					${obj.kdauf }
					</td>
					<th width="10%">
						生产厂：
					</th>
					<td width="15%">
							${obj.plant }
					</td>
					<th width="10%">
						公司机型：
					</th>
					<td >
						${obj.maktx1 }
					</td>
					
				
				</tr>
				<tr><th width="10%">
						成品需求数量：
					</th>
					<td>
						${obj.psmng }
					</td>
					
					<th width="10%">
						成品排序数量：
					</th>
					<td width="15%">
					${obj.pmenge }
					</td>
					<th width="10%">
						成品编号：
					</th>
					<td width="15%">
							${obj.matnr }
					</td>
					<c:if test="${fn:length(orderList4)>1}">
						<c:set var="orders" value=""></c:set>
								<c:forEach items="${orderList4}" var="obj" varStatus="n">
									<c:if test="${n.count==1}">
										<c:set var="orders" value="${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count>1}">
										<c:set var="orders" value="${orders},${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count%12==0}">
										<c:set var="orders" value="${orders}<br/>"></c:set>
									</c:if>
								</c:forEach>
					<th width="10%">
					生产订单明细：
					</th>
					<td width="15%"  colspan="3" title="${fn: replace(orders,'<br/>','')}">
								<c:if test="${fn:length(orders)>20}">
								${fn:substring(orders,0,20)}...
								</c:if>
									<c:if test="${fn:length(orders)<=20}">
								${orders }
								</c:if>
							</td>
						
						</c:if>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		</div>
            <!-- 列表框 -->
          <iframe id="listFrame4" src="" name="listFrame4" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
        </div>
        
        
        
          <div id="tab_content5" class="none">
         <div id="contentDiv5">
            <table class="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
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
					<th width="10%">
						生产排产日期：
					</th>
					<td width="15%">
							<span id="pcdat_span1"></span>
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产排序日期：
					</th>
					<td width="15%">
						<span id="pxdat_span1"></span>
					<fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" />
					</td>
					<th width="10%">
						生产订单编号：
					</th>
					<td width="15%">
					<span id="order_span1"></span>
					</td>
					<th width="10%">
						生产线：
					</th>
					<td width="15%">
						<span id="arbpl_span1"></span>
					</td>
						
					
				</tr>
				<tr>
				<th width="10%">
						客户机型：
					</th>
					<td width="15%">
						<span id="maktx2_span1"></span>
					</td>
					<th width="10%">
						销售单号：
					</th>
					<td width="15%">
					<span id="kdauf_span1"></span>
					</td>
					<th width="10%">
						生产厂：
					</th>
					<td width="15%">
						<span id="plant_span1"></span>
					</td>
					<th width="10%">
						公司机型：
					</th>
					<td >
						<span id="maktx1_span1"></span>
					</td>
					
				
				</tr>
				<tr><th width="10%">
						成品需求数量：
					</th>
					<td>
						<span id="psmng_span1"></span>
					</td>
					
					<th width="10%">
						成品排序数量：
					</th>
					<td width="15%">
					<span id="pmenge_span1"></span>
					</td>
					<th width="10%">
						成品编号：
					</th>
					<td width="15%">
							<span id="matnr_span1"></span>
					</td>
					
						
					
				</tr>
			</tbody>
		</table>
		</div>
            <!-- 列表框 -->
          <iframe id="listFrame5" src="" name="listFrame5" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
        </div>
        
    </div>
    </div>
    </div>

</body>
</html>