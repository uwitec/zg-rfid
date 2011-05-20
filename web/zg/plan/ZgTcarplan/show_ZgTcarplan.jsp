<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTcarplan.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
	</script>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<div>
		<table class="formitem" width="100%" cellpadding="0" cellspacing="1" style="border-top:1px solid #A8CFEB; margin-top:3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						<img src="${ctx }/resources/images/frame/ico_noexpand.gif"
								style="cursor: pointer" title="高级查询" alt="" id="img_1"
								border="0" onclick="changeV('1')" />
						装车单
					</td>
				</tr>
			</thead>
				<tbody id="tbody_1" style="display: block">	
						   <%-- <table border="0" cellpadding="0" cellspacing="0">
					        <tr>
					        	<td width="20"></td>
					        	<td width="200">
					        		<table width="*" cellpadding="0" cellspacing="0">
					            		<tr>
							        		<td class="ow_hdr_i"><img src="<%=iconPath%>/ow_hdr_i.gif" border="0" /></td>
							        		<td class="ow_hdr_f"> 流程向导 </td>
					            		</tr>
					          		</table>
					        	</td>
					            <td style="background:none;">
					            	<table width="*" class="flowTable" style="margin: 0;padding:0" border="0" cellspacing="0" cellpadding="0">
						            	<tr>
						            	<c:if test="${type=='carPlan'}">
						            	 <td><img src="<%=iconPath%>/flow/flow_1.gif" width="106" height="35" />	</td>
										   <td><img src="<%=iconPath%>/flow/flow_2.gif" width="106" height="35" /></td>
										   <td><img src="<%=iconPath%>/flow/flow_4.gif" width="106" height="35" /></td>
										   <td><img src="<%=iconPath%>/flow/flow_6.gif" width="106" height="35" /></td>
										   </c:if>
										   
										   	<c:if test="${type=='storage'}">
						            	
						            	 <td><img src="<%=iconPath%>/flow/flow_1.gif" width="106" height="35" />	</td>
										   <td><img src="<%=iconPath%>/flow/flow_2.gif" width="106" height="35" /></td>
										   <td><img src="<%=iconPath%>/flow/flow_2.gif" width="106" height="35" /></td>
										   <td><img src="<%=iconPath%>/flow/flow_7.gif" width="106" height="35" /></td>
										   </c:if>
						            	
							              
												
										</tr>
						            	<tr align="center">
						                	<td>BOM制作标识变更</td>
						                	<td>领料计划</td>
						                	<td>装车计划</td>
						                	<td>仓库领料</td>
						            	</tr>
					            	</table>
					            </td>
							</tr>
					    </table>--%>
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
					<th>单据编号：</th>
					<td width="15%">${model.cuid}</td>
					<th>单据日期</th>
					<td width="15%">${model.createDateString}</td>
					<th>领料人</th>
					<td width="15%">${model.carUser_related.value}</td>
				</tr>
				<tr>	
					<th>领料部门</th>
					<td>${model.carUser_dept_Id_related.value}</td>
					<th>仓库编号</th>
					<td>${model.storageId}</td>
					<th>仓库名称</th>
					<td>${model.storageId_related.value}</td>
				</tr>
		</tbody>
		</table>
	</div>
</body>
</html>