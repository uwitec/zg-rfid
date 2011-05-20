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
	<title><%=ZgTorderTemp.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTorderTemp.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
			if(initLayout) {
				$(window).bind("load",initLayout);
				$(window).bind("resize",initLayout);
			}
		});
	</script>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<form action="${ctx}//zg/plan/ZgTorderTemp/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTorderTemp.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTorderTemp.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_LABEL_CN%></td>	
						<td><s:property value="%{model.labelCn}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_AUFNR%></td>	
						<td><s:property value="%{model.aufnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ORDER_STATE%></td>	
						<td><s:property value="%{model.orderState}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_SUBMIT_USER%></td>	
						<td><s:property value="%{model.submitUser}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_SUBMIT_DATE%></td>	
						<td><s:property value="%{model.submitDateString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_MANDT%></td>	
						<td><s:property value="%{model.mandt}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_PXDAT%></td>	
						<td><s:property value="%{model.pxdatString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_PLANT%></td>	
						<td><s:property value="%{model.plant}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_PCDAT%></td>	
						<td><s:property value="%{model.pcdatString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_MATNR%></td>	
						<td><s:property value="%{model.matnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_KDAUF%></td>	
						<td><s:property value="%{model.kdauf}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_KDPOS%></td>	
						<td><s:property value="%{model.kdpos}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_KDTXT%></td>	
						<td><s:property value="%{model.kdtxt}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ZCKPP%></td>	
						<td><s:property value="%{model.zckpp}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_MAKTX2%></td>	
						<td><s:property value="%{model.maktx2}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_MAKTX1%></td>	
						<td><s:property value="%{model.maktx1}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ZZCKS%></td>	
						<td><s:property value="%{model.zzcks}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ATWRT2%></td>	
						<td><s:property value="%{model.atwrt2}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_PMENGE%></td>	
						<td><s:property value="%{model.pmenge}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ZTXT02%></td>	
						<td><s:property value="%{model.ztxt02}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ZDBLC%></td>	
						<td><s:property value="%{model.zdblc}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_BRGEW2%></td>	
						<td><s:property value="%{model.brgew2}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_CRDAT%></td>	
						<td><s:property value="%{model.crdatString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_CPUTM%></td>	
						<td><s:property value="%{model.cputmString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_CRNAM%></td>	
						<td><s:property value="%{model.crnam}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_MRNAM%></td>	
						<td><s:property value="%{model.mrnamString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ZMUZE%></td>	
						<td><s:property value="%{model.zmuzeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_MNAME%></td>	
						<td><s:property value="%{model.mname}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_FBDAT%></td>	
						<td><s:property value="%{model.fbdatString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_FBUZE%></td>	
						<td><s:property value="%{model.fbuzeString}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_FNAME%></td>	
						<td><s:property value="%{model.fname}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_PFLAG%></td>	
						<td><s:property value="%{model.pflag}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_BATCH_NO%></td>	
						<td><s:property value="%{model.batchNo}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_OPERATE_TYPE%></td>	
						<td><s:property value="%{model.operateType}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_PSBH%></td>	
						<td><s:property value="%{model.psbh}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderTemp.ALIAS_ARBPL%></td>	
						<td><s:property value="%{model.arbpl}" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="后退" onclick="history.back();"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>