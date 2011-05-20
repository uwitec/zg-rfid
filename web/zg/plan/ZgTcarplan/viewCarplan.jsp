<%@ page contentType="text/html;charset=UTF-8" %>
			<table  style="FONT-SIZE: x-small; BORDER-COLLAPSE: collapse"
								borderColor=#111111 cellSpacing=0 cellPadding=2 width="100%"
								border=1>
			  <tr>
			    <td width="92" height="35">单据编号:</td>
			    <td width="101">${zgTcarplan.cuid}</td>
			    <td width="92">单据日期：</td>
			    <td colspan="3">${zgTcarplan.createDateString}</td>
			  </tr>
			  <tr>
			    <td height="35">领料员编号：</td>
			    <td colspan="5">${zgTcarplan.carUser}</td>
			  </tr>
			  <tr>
			    <td height="38">领料员：</td>
			    <td>${zgTcarplan.carUser_related.value}</td>
			    <td>领料部门：</td>
			    <td width="83">${zgTcarplan.carUser_dept_Id_related.value}</td>
			    <td width="92">仓库编号：</td>
			    <td width="92">${zgTcarplan.storageId}</td>
			  </tr>
			  <tr>
			    <td height="35">仓库名称 ：</td>
			    <td colspan="2">${zgTcarplan.storageId_related.value}</td>
			    <td>单据状态：</td>
			    <td colspan="2">${zgTcarplan.carState_enum.value}</td>
			  </tr>
		 </table>