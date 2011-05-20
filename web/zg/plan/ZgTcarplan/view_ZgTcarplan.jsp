<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<html>
	<head>
	<base target="_self"/>
		<style type="text/css">
<!--
.itable {
	empty-cells: hide;
	border-collapse: collapse;
	border: 1px solid #ccc;
}

.itable td {
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
	padding: 5px;
}
-->
</style>
<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
<script type="text/javascript">
	function printAll(){
  		window.print();
    }
	
</script>
	</head>
	<body>
			<table   style="FONT-SIZE: x-small; BORDER-COLLAPSE: collapse"
								borderColor=#111111 cellSpacing=0 cellPadding=2 width="100%"
								border=0>
			  <tr>
			    <td align="center" valign="middle"><h1>装车计划</h1></td>
			  </tr>
			</table>
	       一、装车计划信息
          <%@include file="/zg/plan/ZgTcarplan/viewCarplan.jsp"%>
           <table><tr><td height="30">&nbsp;</td></tr></table>
           二、BOM信息
           <%@include file="/zg/plan/ZgTcarplan/viewCarboms.jsp"%>
           <table>
           		<tr>
           			<td class="toolbar">
           				<a href="javascript:printAll()" ><span><img src="${ctx}/resources/css/default/images/icons/ico_008.gif" />打印</span></a>
					</td>
           		</tr>
           </table>
	</body>
</html>