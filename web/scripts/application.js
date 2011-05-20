function disableSubmit(finalResult,submitButtonId) {
	if(finalResult) {
		document.getElementById(submitButtonId).disabled = true;
		return finalResult;
	}else {
		return finalResult;
	}
}

function openDialog(url) {
	var dialogHeight = 500;
	var dialogWidth = 500;
	var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
	var w = window.showModalDialog(url,'',sFeatures);
	window.location.reload();
}

function openDialog(url,dialogWidth,dialogHeight) {
	var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
	var w = window.showModalDialog(url,'',sFeatures);
	window.location.reload();
}

function openDialog1(url,dialogWidth,dialogHeight) {
	var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
	return window.showModalDialog(url,'',sFeatures);
}

function batchAdd(action,form) {
	//openDialog(action);
	form.action = action;
    form.submit();
}

function batchValidation(target,action,form) {
	form.target=target;
	form.action = action;
	return true;
}

function batchExport(action) {
	if(parent && parent.document.forms && parent.document.forms[0]) {
		var form = parent.document.forms[0];
		form.action = action;
		form.target = "_blank";
		form.submit();
	}
}

function batchDelete(action,checkboxName,form){
    if (!hasOneChecked(checkboxName)){
            alert('请选择要操作的对象!');
            return;
    }
    if (confirm('确定执行[删除]操作?')){
        form.action = action;
        form.submit();
    }
}

function hasOneChecked(name){
    var items = document.getElementsByName(name);
    if (items.length > 0) {
        for (var i = 0; i < items.length; i++){
            if (items[i].checked == true){
                return true;
            }
        }
    } else {
        if (items.checked == true) {
            return true;
        }
    }
    return false;
}

function setAllCheckboxState(name,state) {
	var elms = document.getElementsByName(name);
	for(var i = 0; i < elms.length; i++) {
		elms[i].checked = state;
	}
}

function getReferenceForm(elm) {
	while(elm && elm.tagName != 'BODY') {
		if(elm.tagName == 'FORM') return elm;
		elm = elm.parentNode;
	}
	return null;
}

function queryColumn(obj,column){
    var formName = 'ec_s_'+column;
    var value = document.forms.ec[formName].value;
    try{
  //  $("input[type=hidden]^='ec_s_'").attr("value","");
      $("input[name^='ec_s']").attr("value","");
    }catch(e){alert(e);};
    var nextValue='';
    if(value==''){
    	nextValue = 'asc';
    }else if(value == 'asc'){
    	nextValue = 'desc';
    }else if(value == 'desc'){
    	nextValue = '';
    }
	document.forms.ec[formName].value=nextValue;
	document.forms.ec.ec_p.value='1';
	document.forms[0].submit();
}

function initAutoComplete(url) {
	var a = $("input[autocomplete]");
	for(var i = 0 ; i < a.length; i++) {
		var o = a[i];
		var xtype = $(o).attr("xtype");
		var columnNameLower = $(o).attr("columnNameLower");
		var bmClassId = $(o).attr("bmClassId");
		var column = $(o).attr("column");
		$("#"+columnNameLower+"_label").css("cursor","pointer");
		var img = "<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
		$("#"+columnNameLower+"_value").after(img);
		if(xtype == 'drm-complex-select') {
			var source = url+"?bmClassId="+bmClassId+"&column="+column;
			$(o).autocomplete_ex({
				source: source,
				minLength: 2,
				select: function(event, ui) {
					$("#"+$(this).attr("columnNameLower")+"_value").val(ui.item.value);
				}
			});
		}else if(xtype.indexOf("tree:") != -1){
			var templateId = xtype.substring(xtype.indexOf(":")+1);
			$("#"+columnNameLower+"_label").attr("readonly",true);
			var sFeatures="dialogHeight: 400px;dialogWidth:300px";
			$(o).bind("click",function() {
				var returnValue = window.showModalDialog(ctx+"/explorer/tree/commonTree.jsp?templateId="+templateId,'',sFeatures);
				if(returnValue) {
					var id = returnValue.id;
					var label = returnValue.label;
					$("#"+$(this).attr("columnNameLower")+"_label").val(label);
					$("#"+$(this).attr("columnNameLower")+"_value").val(id);
				}
			});
		}else if(xtype.indexOf("url:") != -1) {
			var url = xtype.substring(xtype.indexOf(":")+1);
			$(o).bind("click",function() {
				var sFeatures="dialogHeight: 400px;dialogWidth:300px";
				if(url.indexOf("http://") == -1) url = ctx+"/"+url;
				var returnValue = window.showModalDialog(url,'',sFeatures);
				if(returnValue) {
					var id = returnValue.id;
					var label = returnValue.label;
					$("#"+$(this).attr("columnNameLower")+"_label").val(label);
					$("#"+$(this).attr("columnNameLower")+"_value").val(id);
				}
			});
		}
	}
}

function showObject(id,bmClassId,relatedBmClassId,nameSpace) {
	//var sFeatures="dialogHeight: 500px,dialogWidth:400px";
	url = ctx+"/"+nameSpace+"/"+converClassName(relatedBmClassId)+"/show.do?id="+id;
	window.location.href=url;
	//var returnValue = window.showModalDialog(url,'',sFeatures);
	return false;
}

function initSplit() {
	$("div[id^='split']").bind("click", function(){
		changePanel(this);
	}); 
}

function changePanel(obj) {
	$(obj).toggleClass("off");
	var operatePanel = document.getElementById("_operatePanel");
	var queryPanel = document.getElementById("_queryPanel");
	if(operatePanel.style.display != 'none'){
		operatePanel.style.display = 'none';
		queryPanel.style.display = '';
	}else {
		operatePanel.style.display = '';
		queryPanel.style.display = 'none';
	}
}
function initLayout() {
	if(parent) {
		var iframes = parent.document.getElementsByTagName("iframe");
		for(var i = 0; i < iframes.length; i++) {
			if(iframes[i].contentWindow == window) {
				if(iframes[i].getAttribute("autolayout") == "true") {
					iframes[i].style.height = (document.body.offsetHeight) +"px";
				}
			}
		}
	}
}

function fitLayout() {
	var iframes = document.getElementsByTagName("iframe");
	for(var i = 0 ; i < iframes.length;i++ ) {
		if(iframes[i].getAttribute("fitlayout") == 'true') {
			var obj = document.getElementById("_queryPanel");
			if(obj) {
				var fitHeight = getFitHeight(obj);
				iframes[i].style.height = fitHeight + 'px';
				iframes[i].style.width = $(top.bodyFrame).width() + 'px';
			}
		}
	}
	//$(window).bind("resize",fitLayout);
}

function getFitHeight(obj) {
	var maxH = $(top.bodyFrame).height();
	var otherH = 0;
	var o = obj.firstChild;
	for(;o && o.getAttribute('fitLayout') != 'true';o = o.nextSibling) {
		otherH += $(o).height();
	}
	return maxH - otherH - 11;
}
//用于textarea限制长度 用法 onpropertychange="checkLength(this,500);" 
function checkLength(obj,maxlength){
  	if(obj.value.length > maxlength){
  		obj.value = obj.value.substring(0,maxlength);
 	}
 }
 
 function changeV(objID) {
    if (document.getElementById("tbody_" + objID).style.display == "none") {
        document.getElementById("img_" + objID).src = "resources/images/frame/ico_noexpand.gif";
        document.getElementById("tbody_" + objID).style.display = "block"
    }
    else {
        document.getElementById("img_" + objID).src = "resources/images/frame/ico_expand.gif";
        document.getElementById("tbody_" + objID).style.display = "none"
    }
}

function targetFrame(url) {
	changePanel(document.getElementById("split_1"));
	document.getElementById("operateFrame").contentWindow.location.href = url;
}

function resizeWidth() {
//	if($("#listFrame")){
//		var maxWidth = $("#listFrame").width() ;
//		if(window.doLayout){
//			window.doLayout();
//		}
//		if(window.frames['listFrame']){
//			window.frames['listFrame'].$("#grid-data-panel").width(maxWidth);
	//		window.frames['listFrame'].$("#grid-panel").width(maxWidth);
	//	}
	//}
}