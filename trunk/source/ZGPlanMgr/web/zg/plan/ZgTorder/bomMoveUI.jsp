<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
<link type="text/css" href="${ctx}/resources/css/default/images/frame/style.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/dwr/interface/ZgTorderbomDwrAction.js"></script>

<script type="text/javascript">

	$(function() {
		init();
	});
	function init() {
		doLayout();
		$(window).bind("resize",doLayout);
	}				
			
		
    function selectSourceOrder(){
    	var result=openDialog1("${ctx}/zg/plan/ZgTorder/queryForSelect.do",800,600);
    	if(result!=undefined){
	    	document.getElementById("sourceOrderDetailFrame").src="${ctx}/zg/plan/ZgTorder/showByTaskId.do?id="+result;
	    	document.getElementById("sourceOrderBomFrame").src="${ctx}/zg/plan/ZgTorderbom/showBom.do?orderId1="+result;
	    	document.getElementById("sourceOrderId").value=result;
	    	
	    	document.getElementById("targetOrderDetailFrame").src="${ctx}//zg/plan/ZgTorder/showByTaskId.do?id=";
    		document.getElementById("targetOrderBomFrame").src="${ctx}/zg/plan/ZgTorderbom/showBom1.do?orderId1=";
  			document.getElementById("targetOrderId").value='';	
    	}
    }
    
      function selectTargetOrder(){
      	var sourceOrderId=document.getElementById("sourceOrderId").value;
      	if(sourceOrderId==''){
      		alert('请选先择源订单');
      		return;
      	}
    	//var result=openDialog1("${ctx}/zg/plan/ZgTorder/listForSelectByOrderId.do?sourceOrderId="+sourceOrderId,800,400);
    	var result=openDialog1("${ctx}/zg/plan/ZgTorder/queryForSelect.do?flag=target&sourceOrderId="+sourceOrderId,800,600);
    	if(result!=undefined){
    		document.getElementById("targetOrderDetailFrame").src="${ctx}//zg/plan/ZgTorder/showByTaskId.do?id="+result;
    		document.getElementById("targetOrderBomFrame").src="${ctx}/zg/plan/ZgTorderbom/showBom1.do?orderId1="+result+"&orderId2="+sourceOrderId+"&both=true";
  			document.getElementById("targetOrderId").value=result;	
  			
	    	document.getElementById("sourceOrderBomFrame").src="${ctx}/zg/plan/ZgTorderbom/showBom.do?orderId1="+sourceOrderId+"&orderId2="+result+"&both=true";
    	}
    	
    }
    
    function doLayout() {
			var maxHeight = top.getContentHeight()-180;
			if(dealIEVersion(6)){
				maxHeight=maxHeight-25;
			}
			maxHeight=maxHeight/2;
			document.getElementById("sourceOrderBomFrame").style.height = maxHeight + 'px';
			document.getElementById("targetOrderBomFrame").style.height = maxHeight+ 'px';
	}
	
	function bomMove(){
		var sourceOrderId=document.getElementById("sourceOrderId").value;
		var targetOrderId=document.getElementById("targetOrderId").value;
		if(sourceOrderId==''){
			alert("请选择源订单");
			return;
		}
		if(targetOrderId==''){
			alert("请选择目标订单");
			return;
		}
		
		var form=document.frames('sourceOrderBomFrame').document.forms[0];
		var jsonStr=buildupSubmitParams();
		if(jsonStr=='[]'){
			alert("请输入实际移单数量");
			return;
		}
		DWREngine.setAsync(false);
		ZgTorderbomDwrAction.bomMove(jsonStr,sourceOrderId,targetOrderId,function(data){
				if(data=='OK'){
					result = true;
					alert('操作成功!')
					document.getElementById("sourceOrderBomFrame").src="${ctx}/zg/plan/ZgTorderbom/showBom.do?orderId1="+sourceOrderId+"&orderId2="+targetOrderId+"&both=true";
    				document.getElementById("targetOrderBomFrame").src="${ctx}/zg/plan/ZgTorderbom/showBom1.do?orderId1="+targetOrderId+"&orderId2="+sourceOrderId+"&both=true";
    	
				}else{
					alert(data+"数量不符合移单规则，请确认!");
					result = false;
				}
		});
	}
	
	function buildupSubmitParams(){

			var jsonStr='';
			var items = document.getElementsByName("items");
			var form=document.frames('sourceOrderBomFrame');
			var rowNum=(form.document.getElementById("rowNum").value)*1;
				
		    if (rowNum > 0) {
		        for (var i = 0; i < rowNum; i++){
						var cuid=form.document.getElementById("bomList["+i+"].cuid").value;
						var idnrk=form.document.getElementById("bomList["+i+"].idnrk").value;
						var posnr=form.document.getElementById("bomList["+i+"].posnr").value;
						var moveNum=form.document.getElementById("bomList["+i+"].moveNum").value;
						var orderTaskId=form.document.getElementById("bomList["+i+"].orderTaskId").value;
						
						
		        		if(moveNum!=''){
		        			jsonStr=jsonStr+'{' ;
			           		jsonStr = jsonStr + '"cuid":"'+cuid+'",';
			           		jsonStr = jsonStr + '"idnrk":"'+idnrk+'",';
			           		jsonStr = jsonStr + '"posnr":"'+posnr+'",';
			           		jsonStr = jsonStr + '"orderTaskId":"'+orderTaskId+'",';
			           		jsonStr = jsonStr +'"moveNum":"'+moveNum+'"},';
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
	<body class="rm_body" style=" margin:0 3px;" scroll="no">
	<input type="hidden" id="sourceOrderId" value=""/>
	<input type="hidden" id="targetOrderId" value=""/>
		<div class="mls_list">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top" style="height:230px;" >
			  	<div class="mfrwebpart" style="height:230px;" >
					<div class="mfrtitle">源订单&nbsp;&nbsp;<img src="${ctx }/resources/images/frame/autocomplete.png" style="cursor: pointer" onclick="selectSourceOrder()"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:bomMove()">确定移单</a>
					</div>
					<div class="mfrmain" id="div_s_1_chart" style=" height:200px;">
						<iframe  id="sourceOrderDetailFrame" src="${ctx}/zg/plan/ZgTorder/showByTaskId.do?id=" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
						<iframe  id="sourceOrderBomFrame" src="${ctx}/zg/plan/ZgTorderbom/showBom.do?orderId1=" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
					
					</div>
			   </div>
			  </td>
			  </tr>
			  <tr>
              <td valign="top" style="height:230px;" >
			  	<div class="mfrwebpart"  valign="top" style="height:230px;">
					<div class="mfrtitle">目标订单&nbsp;&nbsp;<img src="${ctx }/resources/images/frame/autocomplete.png" style="cursor: pointer" onclick="selectTargetOrder()"/></div>
					<div class="mfrmain" id="div_s_1_chart"  style=" height:230px;">
					<iframe id="targetOrderDetailFrame" src="${ctx}/zg/plan/ZgTorder/showByTaskId.do?id=" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
					<iframe id="targetOrderBomFrame"  src="${ctx}/zg/plan/ZgTorderbom/showBom1.do?orderId1=" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
					</div>
			   </div>
			  </td>
            </tr>
          </table>
		</div>
	</body>
</html>