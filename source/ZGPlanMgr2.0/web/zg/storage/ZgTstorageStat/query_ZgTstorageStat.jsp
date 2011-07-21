<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTstorageStat.TABLE_ALIAS%>查询</title>
		<%@ include file="/commons/meta.jsp"%>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript">
			$(function() {
				initAutoComplete("${ctx}/autoComplate/findRelationData.do");
				init();
			});
			function init() {
				initSplit();
				doLayout();
				$(window).bind("resize",doLayout);
				$("form:first").submit();
			}
			function doLayout() {
				var maxHeight = top.getContentHeight();
				var queryPanelH = maxHeight;
				document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
				var queryTableH = document.getElementById("query_table").offsetHeight;
				var listFrameH = queryPanelH - queryTableH;
				document.getElementById("listFrame").style.height = listFrameH + 'px';
			}
			
			function exportData(){
				document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
				document.forms[0].submit();
				document.forms[0].action = "${ctx}/zg/storage/ZgTstorageStat/list.do";
			}
		
	</script>
	<style>
		body,html {overflow:hidden}
	</style>
	</head>

	<body>
		<%@ include file="/commons/messages.jsp"%>
		<div id="_queryPanel">
		<form action="${ctx}/zg/storage/ZgTstorageStat/list.do" method="post"
			target="listFrame">
			<input type="hidden" name="s_type" value="${type}" />
			<input type="hidden" name="bmClassId"
				value="<%=ZgTstorageStat.BM_CLASS_ID%>" />
			<input type="hidden" name="s_equalBmClassIdQuery"
				value="${pageRequest.filters.equalBmClassIdQuery}" />
			<input type="hidden" name="s_inSubBmClassIdQuery"
				value="${pageRequest.filters.inSubBmClassIdQuery}" />
			<table  id="query_table" class="query_panel"   cellpadding="0" cellspacing="0">
					<thead class="querybar">
					<tr class="querybar">
						<td width="20px" align="center">
							<img src="${ctx }/resources/images/frame/ico_expand.gif"
								style="cursor: pointer" title="高级查询" alt="" id="img_1"
								border="0" onclick="changeV('1')" />
						</td>
						<td width="80px" class="label">
							仓库名称：
						</td>
						<td width="180px">
							<input type="text" size="20" autocomplete="true" xtype="tree:1" maxlength="40"
								id="lgort_label" columnNameLower="lgort"
								bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" />
							<input type="hidden" id="lgort_value" name="s_lgort" />
						</td>

						<td width="80px" class="label">
							生产订单编号：
						</td>
						<td width="180px">
							<input type="text" size="20"  id="aufnr_value" name="s_aufnr" maxlength="40" />
						<td  width="80px" class="label">
							生产线：
						</td>
						<td width="180px" >
							<input type="text" size="20" autocomplete="true" xtype="tree:1" maxlength="40"
								id="arbpl_label" columnNameLower="arbpl"
								bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" />
							<input type="hidden" id="arbpl_value" name="s_arbpl" />
						</td>
						<td  class="label">
						</td>
						<td >
						</td>

					</tr>
				</thead>
				<tbody id="tbody_1" class="querybar" style="display: none;">
					<tr>
						<td width="20px" align="center" rowspan="3"></td>
							<td class="label">
							半成品名称：
						</td>
						<td>
							<input type="text" id="idnrk" name="s_idnrk" />
						</td>
						<td class="label"></td>
						<td></td>
					
						<td class="label"></td>
						<td></td>
						<td class="label"></td>
						<td></td>
				</tbody>
				<tfoot class="querybar">
					<tr>
						<td colspan="9" align="right" class="toolbar">
							<div class="toolbar">
								<a id="queryBtn" href="javascript:" onclick="if(batchValidation('listFrame','${ctx}/zg/storage/ZgTstorageStat/list.do',document.forms[0]))document.forms[0].submit();"><span><img
											src="<%=iconPath%>/ico_search.gif" />查询</span>
								</a>
								&nbsp;<a href="javascript:"><span
									onclick="document.forms[0].reset()"><img
											src="<%=iconPath%>/ico_007.gif" />重置</span>
								</a>
								<a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
			<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>

		</div>

	</body>
</html>