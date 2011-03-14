<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
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
		
		function addBom(){
			
			var url='${ctx}/zg/storage/ZgTstorageCanclebom/queryByCancleId.do?cancleId=${id}&productType=${productType }';
			//alert(url);
			var dialogHeight=390;
			var dialogWidth=800;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			
			window.location.href ='${ctx}/zg/storage/ZgTstorageCancle/findBomlList.do?id=${id}&productType=${productType}&flag=temp';
			if(result!=undefined){
				alert(result);
			}
		
			window.location.reload();
		}
		
		function saveBom(){
			document.forms[0].action="${ctx}/zg/storage/ZgTstorageCanclebom/saveBom.do";
			$("form:first").submit();
		}
		
		
		function checkNum(obj,allNum) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("冲单数量必须小于入库数量！");
					obj.value = allNum;
				}
			}else {
				alert("冲单数量必须为数字！");
				obj.value =allNum;
			}
		}
	</script>
</head>
<%@ include file="/commons/messages.jsp" %>
<form action=""
				method="post">
				<input type="hidden" name="cancleId" value="${id}"}/>
				<input type="hidden" name="productType" value="${productType }"/><div>
		<div>
		</div>
		<div class="datalist">
			<h2>订单管理</h2>
			<div class="toolbar">
					<a href="javascript:addBom()"><span><img onclick="addBom()" src="<%=iconPath%>/addition.gif" />添加</span></a>
					<a href="javascript:batchDelete('${ctx}/zg/storage/ZgTstorageCanclebom/deleteBom.do','items',document.forms[0])"><span><img src="<%=iconPath%>/ico_009a.gif" />删除</span></a>
				
			</div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr>
						<td class="tableHeader" width="3%">
							<input type='checkbox'
								onclick="setAllCheckboxState('items',this.checked)"/>
						</td>
						<td class="tableHeader">
							生产订单编号
						</td>
						<td class="tableHeader">
							生产线
						</td>
						<td class="tableHeader">
							源单号
						</td>
						<td class="tableHeader">
							半成品物料号
						</td>
						<td class="tableHeader">
							半成品名称
						</td>
						<td class="tableHeader">
							半成品单位
						</td>
						<td class="tableHeader">
							可冲单数量
						</td>
						<td class="tableHeader">
							冲单数量
						</td>
						<td class="tableHeader">
							备注
						</td>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${bomECancleList}" var="obj" varStatus="n">
						<c:if test="${!obj.isDel}">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
								<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
								<td width="3%" align="center">
									<input type="checkbox" name="items"
										value="cuid=${obj.cuid}" />
								</td>
								<td>
									${obj.aufnr}
								</td>
								<td>
									${obj.arbpl}
								</td>
								<td>
									${obj.storageId}
								</td>
								<td>
									${obj.matnr}
								</td>
								<td>
									${obj.idnrk }
								</td>
								<td>
									${obj.msehl1}
								</td>
								<td>
									${obj.allNum}
								</td>
								<td>
									<c:if test="${empty view}">
										<input type="text" maxlength="40" maxValue="${obj.allNum}" onchange="checkNum(this,'${obj.allNum}')" size="8" name="cancleBomList[${n.count-1}].num" value="${obj.num}"/>
										
									</c:if>
									<c:if test="${view=='true'}">
									${obj.num}
									</c:if>
									<input type="hidden" name="cancleBomList[${n.count-1}].cuid" value="${obj.cuid}"/>
									<input type="hidden" name="cancleBomList[${n.count-1}].storageCancleId" value="${id}"/>
								</td>
								<td>
									<c:if test="${empty view}">
									<input type="text" maxLength="85"  size="8" name="cancleBomList[${n.count-1}].zbz" value="${obj.zbz}"/>
									</c:if>
									<c:if test="${view=='true'}">
										${obj.zbz}								
									</c:if>
								</td>
							</tr>
							</c:if>
						</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="12" >
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>