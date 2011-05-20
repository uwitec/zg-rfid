<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";

	String template = request.getParameter("template");
%>

<html>
	<head>
		<title>装车物料清单</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css"
			href="${ctx}/resources/css/${_theme}/images/frame/style.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css"
			rel="stylesheet" />
		<script src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js"
			type="text/javascript"></script>

		<style>
</style>

	</head>

	<body>
		<form >

			<table width="100%" height="75%" border="0" cellpadding="0"
				cellspacing="0" id="Table1">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="Table2">
							<tr>
								<td class="frame1_bd_01">
								</td>
								<td class="frame1_bd_02">
									<table width="*" cellpadding="0" cellspacing="0"
										class="ow_hdr_mg" id="Table5">
										<tr>
											<td class="ow_hdr_i">
												<img src="<%=iconPath%>/ow_hdr_i.gif" border="0" />
											</td>
											<td class="ow_hdr_f">
												导入日志
											</td>
										</tr>
									</table>
								</td>
								<td class="frame1_bd_03">
									<img src="<%=iconPath%>/space.gif" />
								</td>
							</tr>
						</table>
					</td>
				</tr>


				<tr style="vertical-align: top;">
					<td height="100%" style="vertical-align: top;" align="center">

						<!-- 页面主体 -->

						<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
							class="tableRegion" width="100%">
							<thead>
								<tr>
									<td class="tableHeader">
										错误行：
									</td>
									<td class="tableHeader">
										错误日志：
									</td>

								</tr>
							</thead>

							<c:forEach items="${errorMap}" var="obj" varStatus="n">
								<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
								<tr class="${trcss}" title="双击选择">

									<td align="left">
										${obj.key}
									</td>
									<td align="left">
										${obj.value}
									</td>
								</tr>
							</c:forEach>

						</table>

					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="Table4">
							<tr>
								<td class="frame1_bd_07">
								</td>
								<td class="frame1_bd_08">
									<table cellpadding="0" cellspacing="0" class="ow_btm_mg"
										id="Table6">
										<tr>
											<td class="ow_btm_msg">
											</td>
											<td class="ow_btm_btn">
												<!------------------ 按钮 ------------------>

												<input onclick="javascript:window.close();" type="submit" class="combtn"
													value="关闭" />
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
		</form>
	</body>
</html>
