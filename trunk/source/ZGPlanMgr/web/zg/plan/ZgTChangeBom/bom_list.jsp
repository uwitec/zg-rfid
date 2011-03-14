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
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript">
		$(function() {
			try{
				$("a[attr='viewLink'],input[attr='viewLink']").bind("click",function(e){
					var o = parent.accordion;
					if(o) {
						if(o.accordion( "option" , "active")+'' == '0') {
							o.accordion("activate", 0);
						}
					}
				});
				$("input[dateFlag=true]")
				.datepicker({
					showAnim:'',showOtherMonths: true,
					selectOtherMonths: true,
					dateFormat:"yy-mm-dd"
				});
				if(initLayout) {
					$(window).bind("load",initLayoutOwner);
					$(window).bind("resize",initLayoutOwner);
				}
			}catch(e){}
		});
		function initLayoutOwner() {
			if(parent) {
				var iframes = parent.document.getElementsByTagName("iframe");
				for(var i = 0; i < iframes.length; i++) {
					if(iframes[i].contentWindow == window) {
						if(iframes[i].getAttribute("autolayout") == "true") {
							iframes[i].style.height = (document.body.offsetHeight+20) +"px";
						}
					}
				}
			}
		}
		
		//选择生产线及订单半成品
		function addBom(){
			var orderId=parent.document.getElementById("orderId_value").value;
			if(orderId==''){
				alert('请先选择订单号');
				return;
			}
			var extend1=parent.document.getElementById("s_extend1").value;
			var url='${ctx}/zg/plan/ZgTBomManager/queryBomlListByOrderId.do?orderId='+orderId+'&extend1='+extend1;
			var sFeatures="dialogHeight: 500px;dialogWidth:860px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
		}
		
		
		
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form id="ec"
			action="<c:url value="/zg/storage/ZgTstorage/findBomlList.do"/>"
			method="post" style="display: inline;">
			<input type="hidden" name="storageId" value="${id}" }/>
			<input type="hidden" name="id" value="${id}" }/>
			<input type="hidden" name="type" value="${type}" }/>
			<input type="hidden" name="productType" value="${productType}" }/>
			<div>
				<div>
				</div>
				<div class="datalist">
					<h2>
						物料列表
					</h2>
					<div class="toolbar">
						<c:if test="${ model.state !='8'}">
							<a href="javascript:addBom()"><span><img
										src="<%=iconPath%>/addition.gif" />添加</span>
							</a>
							&nbsp;<a href="javascript:batchDelete('${ctx}/zg/storage/ZgTstorage/deleteBom.do','items',document.forms[0])"><span><img
										src="<%=iconPath%>/ico_009a.gif" />删除</span>
							</a>

						</c:if>
					</div>
					<div class="eXtremeTable">
						<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
							class="tableRegion" width="100%">
							<thead>
								<tr>
									<td class="tableHeader" width="3%">
										<input type='checkbox'
											onclick="setAllCheckboxState('items',this.checked)" />
									</td>
									<td class="tableHeader">
										生产订单编号
									</td>
									<td class="tableHeader">
										生产线
									</td>
									<td class="tableHeader">
										物料号
									</td>
									<td class="tableHeader">
										物料描述
									</td>
									<td class="tableHeader">
										换料数量
									</td>
								</tr>
							</thead>
							<tbody>
								<tr style="padding: 0px;">
									<td colspan="12">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
		</form>
	</body>
</html>
