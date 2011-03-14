<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.boco.frame.sys.base.model.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>机构管理查看</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<div id="infoPanel">
			<table width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">

							<div class="button" style="text-align: left;">
								<a href="javascript:"><span
									onclick="if(parent.doQuery)parent.doQuery()"><img
											src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</div>
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}" />
		<div class="form_panel">
	<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top:3px;">
		<tbody attr="tbody_1">
						<tr>
								<td  class="label">
								<span class="alterlabel">*</span><%=FwOrganization.ALIAS_LABEL_CN%>：
						</td>
							<td width="30%">
								${model.labelCn}

							</td>
							</tr><tr>
								<td  class="label">
								<%=FwOrganization.ALIAS_PARENT_ORG_ID%>：
							</td>
							<td width="30%">
								${model.parentOrgId_related.value}

							</td>

						</tr>
						<tr>
								<td  class="label">
								<%=FwOrganization.ALIAS_TYPE%>：
							</td>
							<td>
								<select name="type" id="type" disabled="true">
									<option value="1">
										部门
									</option>
									<option value="2">
										仓库
									</option>
								</select>
								<script type="text/javascript">
					var obj = document.getElementById("type");
					for(i = 0;i<obj.options.length;i++){
						if(obj.options[i].value == '${ model.type}'){
							obj.options[i].selected = true;
						}
					}
			</script>
							</td>
</tr><tr>
								<td  class="label">
								<span class="alterlabel">*</span><%=FwOrganization.ALIAS_LEVEL_NUM%>：
							</td>
							<td>
								${model.levelNum}

							</td>
						</tr>
						<tr>

							<td  class="label">
								<span class="alterlabel">*</span><%=FwOrganization.ALIAS_SEQ%>：
							</td>
							<td>
								${model.seq}
							</td>
							</tr>
							<tr>

							<td  class="label">
								负责人：
							</td>
							<td>
								${model.principalsName}
							</td>
							</tr>
							<tr>
								<td  class="label">
								角色描述：
							</td>
							<td>

								${model.note}

							</td>

						</tr>	
		</table>
		</div>
	</body>
</html>