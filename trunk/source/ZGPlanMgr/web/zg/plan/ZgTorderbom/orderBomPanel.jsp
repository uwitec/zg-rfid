<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title>BOM排序字符串调整</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderbomDwrAction.js'></script>
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderDwrAction.js'></script>
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<script type="text/javascript">
		$(function() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			init();
		});
		function init() {
			
			initGridEditor();
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			try {
				var infoPanelH = document.getElementById("infoPanel").offsetHeight
				var infoPanelW = document.getElementById("infoPanel").offsetWidth
				var gridDataPanelH = top.getContentHeight()-infoPanelH-23;
				document.getElementById("grid-data-panel").style.height=gridDataPanelH + 'px';
				document.getElementById("grid-data-panel").style.width=infoPanelW+'px';
				document.getElementById("grid-panel").style.width=infoPanelW+'px';
				
			}catch(e){}
		}
		var sortfList = [];
		<c:forEach items="${sortFEnume}" var="map">
			sortfList.push({value:'${map.value}',labelCn:'${map.name}'});
		</c:forEach>
		function checkChild(key){
			$("*[attr='"+key+"'] input:checkbox").attr("checked",function() { return !this.checked});
		}
		function changeSortf() {
			var items = document.getElementsByName("items");
			var table = document.getElementById("bomTable");
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					var rowIndex = items[i].parentNode.parentNode.rowIndex;
					var inputs = table.rows[rowIndex].getElementsByTagName("input");
					for(var j = 0;j < inputs.length; j++) {
						if(inputs[j].name="sortfs") {
							inputs[j].value=$("#sortfSel").val();
						}
					}
				}
			}
		}
		function submitOrder(obj) {
			obj.disabled = true;
			var lgortObjs=document.getElementsByName("lgorts");
			var idnrkObjs=document.getElementsByName("idnrks");
			var carNumObjs=document.getElementsByName("carNum");
			var cuids=document.getElementsByName("cuids");
			var matklSelfs=document.getElementsByName("matklSelf");
			var flag=true;
			for(var v=0;v<lgortObjs.length;v++){
				var sortf=document.getElementById(cuids[v].value).value;
				if(lgortObjs[v].value==''){
					if(sortf=='ABC'||sortf=='ABD'||sortf=='ABE'){
						alert("组件："+idnrkObjs[v].value+' 仓库号为空，会导致无法领料，请确认!');
						obj.disabled = false;
						flag=false;
						break;
					}
					
				}
				
				if(matklSelfs[v].value==''){
					if(sortf=='ABC'||sortf=='ABD'||sortf=='ABE'){
						alert("组件："+idnrkObjs[v].value+' 未设置自有物料组，会导致无法领料，请确认!');
						obj.disabled = false;
						flag=false;
						break;
					}
					
				}
				
				
				if(carNumObjs[v].value==''||carNumObjs[v].value==0){
					if(sortf=='ABC'||sortf=='ABD'||sortf=='ABE'){
						alert("组件："+idnrkObjs[v].value+" 未设置装车规格，会导致无法领料，请设置装车规格!");
						obj.disabled = false;
						flag=false;
						break;
					}
				}
			}
			if(flag==false){
				return;
			}
			
			if(window.confirm("温馨提示：\n请确认对订单的调整是否均已完成？\n\n确定：提交订单\n取消：继续调整订单")) {
				var items = document.getElementsByName("sortfs");
				var sortfs = [];
				for(var i=0; i<items.length; i++) {
					sortfs.push(items[i].getAttribute("key")+"_"+items[i].value);
				}
				if(sortfs.length >= 0) {
					ZgTorderDwrAction.submitOrder('${orderId}',sortfs,function(data){
						if(data == "success") {
							alert("提交成功");
							if(parent.doQuery)parent.doQuery();
						}else {
							alert("提交失败！");
							obj.disabled = false;
						}
					});
				}else {
					alert("没有数据可以提交！");
					obj.disabled = false;
				}
			}else{
				obj.disabled = false;
			}
		}
		function saveBom(obj) {
			obj.disabled = true;
			var items = document.getElementsByName("sortfs");
			var sortfs = [];
			for(var i=0; i<items.length; i++) {
				sortfs.push(items[i].getAttribute("key")+"_"+items[i].value);
			}
			if(sortfs.length > 0) {
				ZgTorderbomDwrAction.changeSortF(sortfs,function(data){
					if(data == "success") {
						alert("保存成功！");
					}else {
						alert("保存失败！");
					}
					obj.disabled = false;
				});
			}else {
				alert("没有数据可以保存！");
				obj.disabled = false;
			}
		}
		
		function edit(cuid){
			openDialog1('${ctx}/zg/plan/ZgTorderbom/editBomCarInfo.do?bomId='+cuid,400,200);
		}
		function carSet(idnrk,matkl){
			var sFeatures="dialogHeight: 200px;dialogWidth:380px";
			var firstShowQueryUrl="${ctx}/frame/sys/ZgBomCar/queryForCarBomSet.do?idnrk="+idnrk+"&matkl="+matkl;
			var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
		//	document.getElementById(numCode).innerText=returnValue.code;
		//	document.getElementById(numCarLabelCn).innerText=returnValue.labelCn;
			//调用dwr更新后台
		//	ZgBomCarDwrAction.updateCarInfoInBom(bomId,returnValue.cuid,returnValue.code);
		//	alert(idnrk);
			if(returnValue!=null){
				window.location.reload();
			}
		}
		
		function matklSet(matkl,count,idnrk,posnr){
			var sFeatures="dialogHeight: 400px;dialogWidth:300px";
			
			matkl=matkl.substring(matkl.indexOf("(")+1,matkl.indexOf(")"));
		//	alert(ctx+"/zg/plan/ZgTorderbom/materiel_tree.jsp?matkl="+matkl);
			var returnValue = window.showModalDialog(ctx+"/zg/plan/ZgTorderbom/materiel_tree.jsp?matkl="+matkl,'sada',sFeatures);
			if(returnValue) {
				var id = returnValue.id;
				var label=returnValue.label;
				var contect=label;
				label=label.substring(label.indexOf("(")+1,label.indexOf(")"));
				ZgTorderbomDwrAction.setSelfMatkl(idnrk,label,function(data){
					
				});
				$("#"+idnrk+"-"+posnr+"-span").attr("innerText",contect);
				
				var matklSelfs=document.getElementsByName("matklSelf");
				matklSelfs[count].value=label;
			}
		}
	</script>
</head>
<body>
<div id="infoPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
					<a href="javascript:"><span onclick="saveBom(this)"><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
					<a href="javascript:"><span onclick="submitOrder(this)"><img src="<%=iconPath%>/true.gif" />提交</span></a>
				</td>
			</tr>
		</thead>
	</table>
	<iframe id="orderDetailFrame" src="<c:url value="/zg/plan/ZgTorder/show.do?id=${orderId}"/>" autolayout="true" width="100%" frameborder="0" scrolling="no"></iframe>
</div>
<div id="dataPanel">
	<div class="grid-panel" id="grid-panel">
		<div id="title" class="title">计划管理</div>
		<div id="toolbar" class="toolbar">
			<label style="line-height: 9px;">批量设置排序字符串:</label>
			<select id="sortfSel" style="width:60px;font-size: 9px;line-height: 9px">
				<c:forEach items="${sortFEnume}" var="sortfMap">
					<option value="${sortfMap.name}">${sortfMap.name}</option>
				</c:forEach>
			</select>
			<a href="javascript:"><span onclick="changeSortf()"><img src="<%=iconPath%>/true.gif" />确认</span></a>
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
			<table id="bomTable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader">BOM组件编号</td>
						<td class="tableHeader" style="color:red">排序字符串</td>
						<td class="tableHeader">原排序字符串</td>
						<td class="tableHeader">BOM组件描述</td>
						<td class="tableHeader">物料组</td>
						<td class="tableHeader">自有物料组</td>
						<td class="tableHeader">基本单位</td>
						<td class="tableHeader">组件单台用量</td>
						<td class="tableHeader">组件需求用量</td>
						<td class="tableHeader">库存地点</td>
						<td class="tableHeader">大小量纲</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="group" colspan="11" height="25">
							<div class="expandbtn">
								<img src="<%=expandIcon %>" type="expandAll"/>
								<a href="javascript:">全部展开</a>
							</div>
						</td>
					</tr>
					<c:set var="bomNum" value="0"/>
				<c:forEach items="${orderboms}" var="orderbom">
				<c:if test="${orderbom.key.idnrk ne matnr}">
					<tr class="${trcss}">
						<td class="group" colspan="2" height="25" align="left">
							<div class="expandbtn">
								<img src="<%=expandIcon %>" for="${orderbom.key.idnrk}"/>
								<a href="javascript:checkChild('${orderbom.key.idnrk}')" title="选择该组">${orderbom.key.idnrk}</a>
							</div>
						</td>
						<td align="center">${orderbom.key.sortf}
						</td>
						<td align="center">${orderbom.key.sortf}</td>
						<td align="left">
							<c:out value='${orderbom.key.maktx2}'/>
						</td>
						<td align="left">
							<c:if test="${obj.matkl!='()'}">${obj.matkl}</c:if>
						</td>
						<td align="left"  >
							<c:if test="${obj.matklSelf!='()'}">${obj.matklSelf}</c:if>
						</td>
						<td align="center">${orderbom.key.msehl1}</td>
						<td align="center">${orderbom.key.zdtyl}</td>
						<td align="center">${orderbom.key.menge}</td>
						<td align="left">${orderbom.key.lgort_label_cn}</td>
						<td align="center">${orderbom.key.zbz}</td>
					</tr>
				
				</c:if>
					
					<c:forEach items="${orderbom.value}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
							<c:if test="${orderbom.key.idnrk ne matnr}">
						<tr class="${trcss} unexpand"  attr="${orderbom.key.idnrk}" >
							</c:if>
							<c:if test="${orderbom.key.idnrk eq matnr}">
								<tr class="${trcss}">		
							</c:if>
								<td width="3%" align="center">
							<c:if test="${orderbom.key.idnrk eq matnr}">
							<input type="checkbox" name="items" value="id=${obj.cuid}&"/> 
							</c:if>
						</td>
						<c:if test="${empty obj.carNum}">
							<td align="left" style="color:red"   ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">
							<c:if test="${orderbom.key.idnrk ne matnr}">
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</c:if>
						
							&nbsp;${obj.idnrk}</td>
							<c:choose>
							<c:when test="${obj.sortf eq 'CE' || obj.sortf eq 'DE'|| obj.sortf eq 'C'|| obj.sortf eq 'D'|| obj.sortf eq 'E'}">
							<td style="color:red"><input type="text" style="border:0;width:60px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/></td>
							</c:when>
							<c:otherwise>
							<td editable="true" editdata="sortfList" align="center" tyle="color:red" >
								<input type="text" edittype="value" style="border:0;width:30px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/>
								<input type="hidden"  readonly="readonly" name="lgorts"  value="${obj.lgort}"/>
								<input type="hidden"  readonly="readonly" name="cuids"  value="${obj.cuid}_sortfSel"/>
								<input type="hidden"  readonly="readonly" name="idnrks"  value="${obj.idnrk}"/>
								<input type="hidden" readonly="readonly" name="carNum" value="${obj.carNum}"/>
								<input type="hidden" readonly="readonly" name="matklSelf" value="${obj.matklSelf}"/>
							</td>
							</c:otherwise>
							</c:choose>
							<td align="center" style="color:red"  ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">${obj.sortf}</td>
							<td align="left" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')"> ${obj.maktx2}</td>
							<td align="left" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">
							<c:if test="${obj.matkl!='()'}">${obj.matkl}</c:if> </td>
							<td align="left" style="color:red" ondblclick="matklSet('${obj.matkl}','${bomNum },${obj.idnrk}','${obj.posnr }')" title="双击设置物料组">
							<span id="${obj.idnrk}-${obj.posnr }-span"><c:if test="${obj.matklSelf!='()'}">${obj.matklSelf}</c:if></span>
							 </td>
							<td align="center" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">${obj.msehl1}</td>
							<td align="center" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">${obj.zdtyl}</td>
							<td align="center" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">${obj.menge}</td>
							<td align="left" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')"> ${obj.lgort_label_cn}</td>
							<td align="center" style="color:red" ondblclick="carSet('${obj.idnrk}','${obj.matkl }')">${obj.zbz}</td>		
						</c:if>
						<c:if test="${not empty obj.carNum}">
							<td align="left">
							<c:if test="${orderbom.key.idnrk ne matnr}">
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</c:if>
							&nbsp;${obj.idnrk}</td>
							<c:choose>
							<c:when test="${obj.sortf eq 'CE' || obj.sortf eq 'DE'|| obj.sortf eq 'D'|| obj.sortf eq 'E'}">
							<td><input type="text" style="border:0;width:60px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/></td>
							</c:when>
							<c:otherwise>
							<td editable="true" editdata="sortfList" align="center">
								<input type="text" edittype="value" style="border:0;width:30px" readonly="readonly" name="sortfs" id="${obj.cuid}_sortfSel" key="${obj.cuid}" value="${obj.sortf}"/>
								<input type="hidden"  readonly="readonly" name="lgorts"  value="${obj.lgort}"/>
								<input type="hidden"  readonly="readonly" name="cuids"  value="${obj.cuid}_sortfSel"/>
								<input type="hidden"  readonly="readonly" name="idnrks"  value="${obj.idnrk}"/>
								<input type="hidden" readonly="readonly" name="carNum" value="${obj.carNum}"/>
								<input type="hidden" readonly="readonly" name="matklSelf" value="${obj.matklSelf}"/>
							</td>
							</c:otherwise>
							</c:choose>
							<td align="center">${obj.sortf}</td>
							<td align="left"> ${obj.maktx2}</td>
								<td align="left"> <c:if test="${obj.matkl!='()'}">${obj.matkl}</c:if></td>
									<td align="left"  ondblclick="matklSet('${obj.matkl}',${bomNum },'${obj.idnrk}','${obj.posnr}')" title="双击设置物料组"> 
									
									<span id="${obj.idnrk}-${obj.posnr }-span"><c:if test="${obj.matklSelf!='()'}">${obj.matklSelf}</c:if></span>
									
									</td>
							<td align="center">${obj.msehl1}</td>
							<td align="center">${obj.zdtyl}</td>
							<td align="center">${obj.menge}</td>
							<td align="left"> ${obj.lgort_label_cn}</td>
							<td align="center">${obj.zbz}</td>
						
						</c:if>
					</tr>
					<c:set var="bomNum" value="${bomNum+1}"/>
					</c:forEach>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>