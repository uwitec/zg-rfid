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
    <title>装车物料清单</title>
     <base target="_self"/>
     <%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
    <link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
    <link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js" type="text/javascript"></script>
	<script type="text/javascript">
		var carPlanId='${carPlanId }';
		var lgortName= "${lgortName }";
		var lgort="${lgort}";
		$(function() {
			init();
		});
		function init() {
		//	doLayout();
		//	$(window).bind("resize",doLayout);
			$("form:first").submit();
		      
		}
		function doLayout() {
			var maxHeight = top.getContentHeight();
			document.getElementById("_queryPanel").style.height = maxHeight + 'px';
			var tableInfo1 = document.getElementById("tableInfo1").offsetHeight;
			var tableInfo2 = document.getElementById("tableInfo2").offsetHeight;
			var listFrameH = maxHeight - tableInfo1-tableInfo2;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
		
		function generateCarPlan(obj){
			butsetDisable(true);
			var form=document.frames['listFrame'].document.getElementById("carList");
			
				if(null==form){
					form=document.frames['listFrame'].document.getElementById("carList");
				}
				form.action="${ctx}/zg/plan/ZgTcarplan/generateCarPlan.do";
				form.submit();
		
		}
		
		function cancleCarPlan(obj){
			butsetDisable(true);
			var form=document.frames['listFrame'].document.getElementById("carList");
			if(confirm("确定取消该装车计划？")){	
				if(null==form){
					form=document.frames['listFrame'].document.getElementById("carList");
				}
				var obj=document.frames['listFrame'].document.getElementsByName("hasConfirm");
				if(obj!=null&&obj!=undefined&&obj.length>0){
					alert("该装车计划已经有物料刷卡确认不能取消!");
					butsetDisable(false);
					return;
				}
				
				form.action="${ctx}/zg/plan/ZgTcarplan/cancleCarPlan.do?lgort=${lgort }";
				form.submit();
				return;
			}
			butsetDisable(false);
			
		}
		
		function confirmCarPlan(obj){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要领料的物料！");
				return;
			}
			
			butsetDisable(true);
			if(!checkNum()){
				butsetDisable(false);
				return;
			}
	
			
			var result = openDialog1("${ctx}/zg/plan/ZgTcarbom/rfid.jsp?lgort=${lgort}",600,280);
			
			if(result==undefined){
				butsetDisable(false);
				return;
			}
			
			if(!checkForConfirmCarPlan()){
				butsetDisable(false);
				return;
			}
			document.frames['listFrame'].document.getElementById('storageUserId').value=result.cuid;
			var form=document.frames['listFrame'].document.getElementById("carList");
			form.action="${ctx}/zg/plan/ZgTcarplan/confirmCarPlan.do";
			form.submit();
		}
		
		function butsetDisable(state){
			var btns=document.getElementsByName("submitBtn");
			if(btns!=null){
				for(var v=0;v<btns.length;v++){
					btns[v].disabled = state;
				}
			}
			
		}
		
		
		function checkForGenerate(){
			var jsonStr=buildupSubmitParams();
			DWREngine.setAsync(false);
			var result;
		
			ZgTcarplanDwrAction.checkForGenerateCarPlan(jsonStr,"${carPlanId }",function(data){
				if(data){
					result = true;
				}else{
					alert("目前BOM组件已经不是最新数据，请确认！");
					result = false;
				}
			});
			return result;
		}
		
		function checkForConfirmCarPlan(){
			var jsonStr=buildupSubmitParams();
			DWREngine.setAsync(false);
			var result;
			ZgTcarplanDwrAction.checkForSubmitCarPlan(jsonStr,"${carPlanId }",function(data){
				if(data=='OK'){
					result = true;
				}else{
					alert(data);
					result = false;
				}
			});
			return result;
		}
		
		function buildupSubmitParams(){
			var jsonStr='';
			var items = document.frames['listFrame'].document.getElementsByName("items");
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		            	jsonStr=jsonStr+'{' ;
		           		var params =new QueryString(items[i].value);
		           		var orderPlanbomId=params["orderPlanbomId"];
		           		var planNum =params["planNum"];
		           		var carNum =params["carNum"];
		           		var rowNum=params["rowNum"];
		           		var carPlanNum=document.frames['listFrame'].document.getElementById("carbomList["+rowNum+"].planNum").value;
		           		
		           		var idnrk=params["idnrk"];
		           		var aufnr=params["aufnr"];
		           		jsonStr = jsonStr + '"orderPlanbomId":"'+orderPlanbomId+'",';
		           		jsonStr = jsonStr + '"carNum":"'+carNum+'",';
		           		jsonStr = jsonStr + '"idnrk":"'+idnrk+'",';
		           		jsonStr = jsonStr + '"aufnr":"'+aufnr+'",';
		           		jsonStr = jsonStr + '"carPlanNum":"'+carPlanNum+'",';
		           		jsonStr = jsonStr +'"planNum":"'+planNum+'"},';
		        }
		    }
		     if(jsonStr.length>0){
	        	jsonStr=jsonStr.substr(0,jsonStr.length-1);
	        }
	        jsonStr='['+jsonStr+']';
	      //  alert(jsonStr);
	        return jsonStr;
	    }
	    
	    
	    function checkNum(){
	    	var jsonStr='';
			var items = document.frames['listFrame'].document.getElementsByName("items");
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		       		 if(items[i].checked) {
				
		        	var carNum=document.frames['listFrame'].document.getElementById("carbomList["+i+"].planNum").value*1;
		        	if(carNum=='0'){
		        		alert("实际装车数量不能为零，请确认！");
						return;
		        	}
		        	var supItems=document.frames['listFrame'].document.getElementsByName("items"+i);
		        	var carSupNumAll=0;
		        	for (var j = 0; j < supItems.length; j++){
		        	   var carSupNum=document.frames['listFrame'].document.getElementById("carbomList["+i+"].supList["+j+"].carNum").value;
		        	   carSupNumAll=carSupNumAll+carSupNum*1;
		        	}
		        	if(carNum!=carSupNumAll&&supItems.length>0){
		        		alert("装车数量与具体供应商数量不相等，请确认！");
		        		return false;
		        	}
		        	}
		        }
		    }
		    
		    return true;
	    }
	    
		
	</script>
   
<style>

</style>

</head>
<body>
    <table width="100%" height="60%" border="0" cellpadding="0" cellspacing="0" id="Table1">
        <tr>
            <td>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" id="Table2">
                    <tr>
                        <td class="frame1_bd_01">
                        </td>
                        <td class="frame1_bd_02">
                            <table width="*" cellpadding="0" cellspacing="0" class="ow_hdr_mg" id="Table5">
                                <tr>
                                    <td class="ow_hdr_i">
                                        <img src="<%=iconPath%>/ow_hdr_i.gif" border="0"/>
                                    </td>
                                    <td class="ow_hdr_f">
                                      	装车单详细
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td class="frame1_bd_03">
                            <img src="<%=iconPath%>/space.gif"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
        	<td>
        	<form  action="${ctx}/zg/plan/ZgTcarplan/loadCarList.do" method="post" target="listFrame">
				<input type="hidden" name="s_bomCuids" id="s_bomCuids" value="${bomCuids }"/>
				<input type="hidden" name="s_groupId" value="${groupId }"/>
				<input type="hidden" name="s_carPlanId" value="${carPlanId }"/>
				<input type="hidden" name="s_planType" value="${planType }"/>
				<input type="hidden" name="s_carId" value="${carId }"/>
				<input type="hidden" name="s_lgort" value="${lgort }"/>
				<input type="hidden" name="s_lgortName" value="${lgortName }"/>
				<input type="hidden" name="s_advance" value="${advance }"/>
				<input type="hidden" name="s_aufnr" value="${aufnr }"/>
				<input type="hidden" name="s_maktx1" value="${maktx1 }"/>
        	 <table class="formitem" width="100%" cellpadding="0" cellspacing="1">
                <caption>
                    <a href="javascript:"><span>装车信息</span></a>
                </caption>
                <tr>
                    <th width="10%">
                        车次编号：
                    </th>
                    <td width="15%">
                        ${carPlanId }
                    </td>
                        <th width="10%">
                        车型编号：
                    </th>
                      <td width="15%">
                      ${carCode }
                    </td>
                       <th width="10%">
                        领料人：
                    </th>
                    <td width="15%">
                        ${operatorInfo.userName }
                    </td>
                        <th width="10%">
                        仓库：
                    </th>
                      <td width="15%">
                      ${lgortName }(${lgort })
                    </td>
                </tr>
         
            </table>
            </form>
        	</td>
        </tr>
       
        <tr style="vertical-align: top;">
            <td height="100%" style="vertical-align: top;">
            
            	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            	
                <!-- 页面主体 -->
            </td>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" id="Table4">
                    <tr>
                        <td class="frame1_bd_07">
                        </td>
                        <td class="frame1_bd_08">
                            <table cellpadding="0" cellspacing="0" class="ow_btm_mg" id="Table6">
                                <tr>
                                    <td class="ow_btm_msg">
                                    <c:if test="${pause}">
                                     <span style="font-size:14px;font-weight:bold; white-space:nowrap;color:red; ">该订单已经被暂停，请确认!</span>
                                    </c:if>
                                   
                                    </td>
                                    <td class="ow_btm_btn">
                                        <!------------------ 按钮 ------------------>
                                          <c:if test="${!pause}">
                                               <a href="javascript:" class="combtn"  name="submitBtn" onclick="confirmCarPlan(this)" ><span name="submitBtn" >确认领料</span></a>
                                          </c:if>
                                        <c:if test="${hasCar=='1'}">
                                         	<a href="javascript:" class="combtn"  name="submitBtn" onclick="cancleCarPlan(this)"  ><span name="submitBtn" >取消订单</span></a>
                                        </c:if>
                                          <c:if test="${!pause}">
                                        <a href="javascript:" class="combtn"   name="submitBtn" onclick="generateCarPlan(this)" ><span name="submitBtn" >保存</span></a>
                                        </c:if>
                            			<a href="javascript:window.close();" class="combtn"  name="submitBtn"  ><span>关闭</span></a> 
                                        
                                        <!------------------END 按钮 --------------->
                                  </td>
                                </tr>
                            </table>
                        </td>
                        <td class="frame1_bd_09">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>
</html>
