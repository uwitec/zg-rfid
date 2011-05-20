<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			doLayout();
			if(parent.doLayout) {
				parent.doLayout();
			}
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			var iframes = parent.document.getElementsByTagName("iframe");
			for(var i=0; i < iframes.length; i++) {
				if(iframes[i].contentWindow == window) {
					iframes[i].style.height = document.getElementById("form_panel").offsetHeight + 8 + 'px';
				}
			}
		}
	</script>
</head>
<body>
    <div id="form_panel" class="form_panel">
		<table class="form_table" cellpadding="0" cellspacing="1">
			<caption>
				<label class="expandbtn">
					<img src="<%=expandIcon %>" for="tbody_1" dolayout="true"/>
					<a href="javascript:"><span>订单<s:property value="%{model.aufnr}" /></span></a>
				</label>
			</caption>
			<tbody class="unexpand" attr="tbody_1">
				<tr>	
					<td class="label">排产日期</td>
					<td><s:property value="%{model.pcdatString}" /></td>
					<td class="label">订单编号</td>
					<td><s:property value="%{model.aufnr}" /></td>
					<td class="label">生产线</td>
					<td><s:property value="%{model.arbpl}" /></td>
					<td class="label">客户机型</td>
					<td><s:property value="%{model.maktx2}" /></td>
				</tr>
				<tr>	
					<td class="label">销售订单号</td>
					<td><s:property value="%{model.kdauf}" /></td>
					<td class="label">生产厂</td>
					<td><s:property value="%{model.plant}" /></td>
					<td class="label">公司机型</td>
					<td><s:property value="%{model.maktx1}" /></td>
					<td class="label">订单项数量</td>
					<td><s:property value="%{model.psmng}" /></td>
				</tr>
				<tr>	
					<td class="label">物料号</td>
					<td><s:property value="%{model.matnr}" /></td>
					<td class="label">备注</td>
					<td><s:property value="%{model.orderState_enum.value}" /></td>
					<td class="label"></td>
					<td></td>
					<td class="label"></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>