<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
	<base href="<%=basePath%>" />
	<title>sap接口数据获取</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<style>
		html,body{overflow: hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/SapDwrAction.js"></script>
	<script type="text/javascript">
		function loadData(){
			//DWREngine.setAsync(false);
			var dataValue="";
			var dataValue1="";
			if($("#dateType").val()=='3'){
				if($("#aufnr").val()==''){
					alert("订单不能为空!");
					return;
				}
				dataValue=$("#aufnr").val();
			}
		
			if($("#dateType").val()!='3'&&$("#dateType").val()!='5'){
				if($("#date").val()==''){
					alert("数据时间不能为空!");
					return;
				}
				dataValue=$("#date").val();
			}
			
			dataValue1=$("#plant").val()+"";
			if(dataValue1==null||dataValue1=='null'){
				dataValue1="C01,C02,C04,C05,C07,C08";
			}
		
			$("#submitButton").attr("disabled",true);
			SapDwrAction.getBatchNo(function(batchNo){
				alert("已启动接口获取数据，编号："+batchNo+"，具体结果请查看日志列表");
			//	dataValue=orderplant+"sdf";
				SapDwrAction.loadSapData($("#dateType").val(),dataValue,batchNo,dataValue1,function(data){
				});
			});
		
			$("#submitButton").attr("disabled",false);
			
		}
		
		function interFaceTypeChange(){
		
			if($("#dateType").val()=='3'){
				document.getElementById("td1").style.display="none";
				document.getElementById("td2").style.display="none";
				document.getElementById("td3").style.display="";
				document.getElementById("td4").style.display="";
				document.getElementById("td5").style.display="none";
				document.getElementById("td6").style.display="none";
			}else if($("#dateType").val()=='5'){
				document.getElementById("td1").style.display="none";
				document.getElementById("td2").style.display="none";
				document.getElementById("td3").style.display="none";
				document.getElementById("td4").style.display="none";
				document.getElementById("td5").style.display="none";
				document.getElementById("td6").style.display="none";
			}else if($("#dateType").val()=='2'){
				document.getElementById("td1").style.display="";
				document.getElementById("td2").style.display="";
				document.getElementById("td3").style.display="none";
				document.getElementById("td4").style.display="none";
				document.getElementById("td5").style.display="";
				document.getElementById("td6").style.display="";
			}else{
				document.getElementById("td1").style.display="";
				document.getElementById("td2").style.display="";
				document.getElementById("td3").style.display="none";
				document.getElementById("td4").style.display="none";
				document.getElementById("td5").style.display="none";
				document.getElementById("td6").style.display="none";
			}
		}
	</script>
</head>

<body>
<div>
	<form action="" method="post">
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
				
					<td width="80px" class="label">接口类型：</td>
					<td>
						<select name="dateType" id="dateType" onchange="interFaceTypeChange()">
							<option value="1">排产接口</option>
							<option value="2">排序接口</option>
							<option value="3">订单调整</option>
							<option value="4">批量bom组件</option>
							<option value="5">订单领料同步</option>
						</select>
					</td>
					<td width="80px" class="label" id="td1">
						数据时间：
					</td>
					<td width="180px" id="td2">
					<input type="text" dateFlag="true" name="date" value="${dateStr }" id="date" size="20" maxlength="40" readonly="true"/>
					</td>
						<td width="80px" class="label" id="td3" style="display:none">
						订单编号：
					</td>
					<td width="180px" id="td4" style="display:none">
						<input type="text"  name="aufnr"  id="aufnr" size="20" maxlength="40" />
					</td>
						<td width="80px" class="label" id="td5" style="display:none">
						生产厂：
					</td>
					<td width="180px" id="td6" style="display:none"   >
						<select id="plant" name="plant"  size="4"  multiple  >
							<option value="C01">C01</option>
							<option value="C02">C02</option>
							<option value="C04">C04</option>
							<option value="C05">C05</option>
							<option value="C07">C07</option>
							<option value="C08">C08</option>
						</select>
					</td>
					<td align="left" class="toolbar">
						<div class="toolbar" style="position:relative;left:0" >
							<a href="javascript:loadData();" id="submitButton"><span><img src="<%=iconPath%>/true.gif" />获取</span></a>
						</div>
					</td>
				</tr>
			</thead>
		
		</table>
	</form>
</div>
<br/>
</body>
</html>