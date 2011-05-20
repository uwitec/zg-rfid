<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 
	List<SortInfo> list = ((PageRequest)request.getAttribute("pageRequest")).getSortInfos();
	Map map = new HashMap();
	for(SortInfo si:list){
		map.put("ec_s_"+si.getColumnName(),si.getSortOrder());
		String image="";
		if("asc".equalsIgnoreCase(si.getSortOrder())){
			image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-bottom.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
			map.put("ec_image_"+si.getColumnName(),image);
		}else if("desc".equalsIgnoreCase(si.getSortOrder())){
			image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-top.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
			map.put("ec_image_"+si.getColumnName(),image);
		}
	}
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=FwEmployee.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script src="${ctx}/dwr/interface/ZgBomCarDwrAction.js" type=""></script>
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<script type="text/javascript">
		$(function() {
			init();
			initGridEditor();
		});
		
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame").style.height;
			var maxWidth = parent.document.getElementById("listFrame").style.width;
			maxHeight = maxHeight.replace("px","")*1;
			maxWidth=maxWidth.replace("px","")*1;
			var height = maxHeight-15;
			maxWidth=maxWidth-202;
			document.getElementById("grid-data-panel").style.height=height+'px';
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
		
		
		function edit(bomId,numCode,numCarLabelCn){
			var sFeatures="dialogHeight: 400px;dialogWidth:710px";
			var firstShowQueryUrl="${ctx}/frame/sys/ZgBomCar/queryForCar.do";
			var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
			//returnValue这个是用来取决是否能够提交的returnValue==undefined||returnValue==''
			document.getElementById(numCode).innerText=returnValue.code;
		//	document.getElementById(numCode).value=returnValue.code;
		//	document.getElementById(numCarLabelCn).value=returnValue.labelCn;
			document.getElementById(numCarLabelCn).innerText=returnValue.labelCn;
			//调用dwr更新后台
			ZgBomCarDwrAction.updateCarInfoInBom(bomId,returnValue.cuid,returnValue.code);
		
		/*	if(returnValue){
				document.forms[0].action = "${ctx}/frame/sys/ZgBomCar/updateCarInfoByBomId.do?carInfoId="+returnValue.cuid+"&bomId="+bomId;
				document.forms[0].submit();
				//alert("afa");
			}*/
		}
		/*
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwEmployee/show.do?id='+cuid);
			}
		}
		*/
		//编辑装车数量
		function editCarNum(obj,carNumIdLabel,carNumId,oldCarNumId,bomId){
			if(obj.value==document.getElementById(oldCarNumId).value){
				//没有改变
				document.getElementById(carNumIdLabel).style.display="";
				document.getElementById(carNumId).style.display = "none"; 
				return;
			}else{
				if(!isNumber(obj.value)){
					alert("输入必须为正整数");
					document.getElementById(carNumIdLabel).style.display="";
					document.getElementById(carNumId).style.display = "none"; 
					document.getElementById(carNumId).value=document.getElementById(oldCarNumId).value;
					return;
				}	
				if(obj.value<0){
					alert("输入必须为正整数");
				//	document.getElementById(carNumId).value=document.getElementById(oldCarNumId).value;
					document.getElementById(carNumIdLabel).style.display="";
					document.getElementById(carNumId).style.display = "none"; 
					document.getElementById(carNumId).value=document.getElementById(oldCarNumId).value;
					return;
				}else{
					document.getElementById(carNumId).style.display = "none";
					document.getElementById(carNumIdLabel).innerText=obj.value;
					document.getElementById(carNumIdLabel).style.display="";
					document.getElementById(oldCarNumId).value=obj.value;
					//把值跟新到库表
					ZgBomCarDwrAction.updateCarNumInBom(bomId,obj.value);
				}
			}  
		}
		function carNumChange(carNumIdLabel,carNumId,oldCarNumId,bomId){
			document.getElementById(carNumIdLabel).style.display="none";
			document.getElementById(carNumId).style.display = ""; 
			document.getElementById(carNumId).focus();
		}
		
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="//frame/sys/ZgBomCar/listForStoreCar.do"/>" method="post" style="display: inline;">
<input type="hidden" name="s_orgId" id="s_orgId" value="${orgId }"/>
<input type="hidden" name="s_parentOrgId" id="s_parentOrgId" value="${parentOrgId }"/>
<input type="hidden" name="s_idnrk" id="s_idnrk" value="${pageRequest.filters.idnrk }"/>
<input type="hidden" name="s_carId" id="s_carId" value="${carId }"/>
<input type="hidden" name="s_carName" id="s_carName" value="${carName }"/>
<input type="hidden" name="s_bomCarState" id="s_bomCarState" value="${bomCarState }"/>

	<div>
		<div>
			<%@ include file="query_include_FwEmployee.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">物料列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" onclick="queryColumn(this,'idnrk')" title="排序 BOM组件编号" >BOM组件编号<%=map.get("ec_image_idnrk")==null?"":map.get("ec_image_idnrk")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'msehl')" title="排序 基本单位" >基本单位<%=map.get("ec_image_msehl")==null?"":map.get("ec_image_msehl")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'materiel_name')" title="排序 物料组" >物料组<%=map.get("ec_image_materiel_name")==null?"":map.get("ec_image_materiel_name")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'maktx')" title="排序 物料描述" >BOM组件描述<%=map.get("ec_image_maktx")==null?"":map.get("ec_image_maktx")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'code')" title="排序 车型编号" >车型编号<%=map.get("ec_image_code")==null?"":map.get("ec_image_code")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'car_label_cn')" title="排序 车型名称" >车型名称<%=map.get("ec_image_car_label_cn")==null?"":map.get("ec_image_car_label_cn")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'carnum')" title="排序 车型名称" >装载数量<%=map.get("ec_image_carnum")==null?"":map.get("ec_image_carnum")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}"  >
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.bomId}&"/>
							</td>
							
							<td>${obj.idnrk}</td>
							<td>${obj.msehl}</td>
							<td  align="left">${obj.matkl_labelCn}</td>
							<td align="left">${obj.maktx}</td>
							
							<td ondblclick="edit('${obj.bomId}','${n.count-1}code','${n.count-1}carLabelCn')" title="双击修改车型信息">
								<label id="${n.count-1}code">${obj.code}</label>
							</td>
							<td ondblclick="edit('${obj.bomId}','${n.count-1}code','${n.count-1}carLabelCn')" title="双击修改车型信息">
								<label id ="${n.count-1}carLabelCn">${obj.carLabelCn}</label>
							</td>	
							<td ondblclick="carNumChange('${n.count-1}carNumLabel','${n.count-1}carNum','${n.count-1}oldCarNum','${obj.bomId}')" title="双击填写退料数量">
								<label id="${n.count-1}carNumLabel">${obj.carNum}</label>
								<input type="hidden" id="${n.count-1}oldCarNum" value="${obj.carNum}" />
								<input type="text" id="${n.count-1}carNum" onblur="editCarNum(this,'${n.count-1}carNumLabel','${n.count-1}carNum','${n.count-1}oldCarNum','${obj.bomId}')" value="${obj.carNum}" style="width:80px" maxlength="13"  style="display:none" />
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="13" >&nbsp;<%@include file="/frame/default/ux/pagebar.jsp" %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
</body>
</html>
