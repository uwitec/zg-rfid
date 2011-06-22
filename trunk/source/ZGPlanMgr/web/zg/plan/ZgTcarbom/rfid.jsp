<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<base target="_self"/>
	<title>仓管刷卡确认领料</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script src="${ctx}/dwr/interface/FwEmployeeDwrAction.js" type=""></script>
	<style>
		.readOnlyInput {
			border: 0;
			background: transparent;
		}
	</style>
	  <%
		String lgort=request.getParameter("lgort");
	 %>
	<script type="text/javascript">
		$(function() {
			if(initLayout) {
				$(window).bind("load",initLayout);
				$("#labelCn").val('sysadmin');
                	$("#userId").val('0');
                 	$("#cuid").val('0');
			}
			
		});
		
		function scroll() {
             var reader = document.getElementById('RFIDReader');
             var kCode=document.getElementById("kCode");
             var userId;
             if (reader.readyState == 4) {
                 var rl = reader.Code;
               //  var rl="1E C4 70 6F";
                if(rl=='1E C4 70 6F '){
                 	$("#labelCn").val('sysadmin');
                 	$("#userId").val('0');
                 	$("#cuid").val('0');
                 	return;
                 }
                 if (rl == '-1') {//设备未正常连接
                    //alert('设备连接失败，请重启设备');
                 }else if(rl==''){//没有卡的时候
                 }else if(rl != undefined && rl != null){//正常读取卡号信息
                 	if(kCode.value!=rl){
                 		var userId="";
                 		kCode.value=rl;
                 		var flag=true;
                 		DWREngine.setAsync(false);
                 		FwEmployeeDwrAction.getStorageUserByRfidCode(rl,"<%=lgort%>",function(data){//根据卡号获取仓库管理员信息
                 			if(null!==data){
                 				$("#labelCn").val(data.LABEL_CN==null?'':data.LABEL_CN);
                 				$("#userId").val(data.USER_ID==null?'':data.USER_ID);
                 				$("#effectTime").val(data.EFFECT_TIME==null?'':data.EFFECT_TIME);
                 				$("#dept").val(data.DEPTNAME==null?'':data.DEPTNAME);
                 				$("#telephone").val(data.TELEPHONE==null?'':data.TELEPHONE);
                 				$("#sex").val(data.SEX==null?'':data.SEX);
                 				$("#email").val(data.EMAIL==null?'':data.EMAIL);
                 				$("#cuid").val(data.CUID==null?'':data.CUID);
                 			}else{
                 				$("#labelCn").val('');
                 				$("#userId").val('');
                 				$("#effectTime").val('');
                 				$("#dept").val('');
                 				$("#telephone").val('');
                 				$("#sex").val('');
                 				$("#email").val('');
                 				$("#cuid").val('');
								alert("未找到该仓管员信息,请确认...");
                 			 	flag=false;
                 			}
                 			
                 			
                 		});
                 		
                 	}
                    
                 }
             }
         }
         
         function returnMsgAndClose(){
         	var cuid=$("#cuid").val();
         	var labelCn=$("#labelCn").val();
         	var zbz=$("#zbz").val();
         	if(cuid==''||cuid==undefined){
         		alert('请仓管员刷卡确认');
         		return;
         	}
         	var returnObj=new Array();
         	returnObj.cuid=cuid;
         	returnObj.labelCn=labelCn;
         	returnObj.zbz=zbz;
         	//alert(zbz);
         	window.returnValue=returnObj;
         	window.close();
	     }
	         
	</script>
</head>
<body onload="setInterval('scroll()',1000)">
	<form action="${ctx}//frame/sys/FwEmployee/list.do" method="get" theme="simple">
		<div class="form_panel">
			<table class="form_table" cellpadding="1" cellspacing="1" style="margin-top:3px;">
				<caption>
					<label>
				
							<div class="button" style="text-align: left;">
									<span>请刷卡确认仓管员信息</span>
								<a href="javascript:returnMsgAndClose();"><span ><img src="<%=iconPath%>/true.gif" />确定</span></a>
							</div>
					</label>
				</caption>
					<tr>	
						<td class="label" width="10%"><%=FwEmployee.ALIAS_LABEL_CN%>：</td>	
						<td><input id="labelCn" class="readOnlyInput" />
						<input id="cuid" type="hidden" class="readOnlyInput" />
						</td>
						
						<td class="label" width="40%"><%=FwEmployee.ALIAS_USER_ID%>：</td>	
						<td><input id="userId" class="readOnlyInput" /></td>
					</tr>
					<tr>	
						
					<td class="label" width="10%"><%=FwEmployee.ALIAS_EFFECT_TIME%>：</td>	
						<td><input id="effectTime" class="readOnlyInput" /></td>
							<td class="label"><%=FwEmployee.ALIAS_ORG_ID%>：</td>	
						<td width="40%"><input id="dept" class="readOnlyInput" /></td>
					</tr>
					<tr>	
						<td class="label"><%=FwEmployee.ALIAS_TELEPHONE%>：</td>	
						<td><input id="telephone" class="readOnlyInput" /></td>
					
						<td class="label"><%=FwEmployee.ALIAS_SEX%>：</td>	
						<td><input id="sex" class="readOnlyInput" /></td>
					</tr>
					<tr>	
						<td class="label">
						<%=FwEmployee.ALIAS_EMAIL%>：</td>	
						<td><input id="email" class="readOnlyInput" /></td>
			
					</tr>
					
					<tr>	
						<td class="label">
					　领料备注：</td>	
						<td colspan="3">	<textarea name="zbz" id="zbz" onpropertychange="if(value.length>50) value=value.substr(0,50)"
								 cols=40 rows=2></textarea></td>
					</tr>
					<tr>	
						<td>
						<OBJECT id="RFIDReader" classid="clsid:93D9C558-C72F-4348-A071-BC594E06A0AA" style="visibility:hidden;"> </OBJECT>
				       <input type="hidden" id="kCode"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>