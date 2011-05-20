function initGridEditor() {
	$("td[editable=true]").bind("click",focusEdit);
	$("td[editable=true]").bind("changeHandle",changeHandle);
}

function changeHandle(event) {
	var handle = this.getAttribute("changehandle");
	if(handle)
		eval(handle);
}

function createSelect(data,val) {
	var targetEl = document.createElement("select");
	var emptyOpt = document.createElement("option");
	emptyOpt.value="";
	emptyOpt.innerHTML = "请选择...";
	targetEl.appendChild(emptyOpt);
	for(var i = 0; i< data.length; i++) {
		var labelCn = data[i].labelCn;
		var value = data[i].value;
		var opt = document.createElement("option");
		opt.value=value;
		opt.innerHTML = labelCn;
		targetEl.appendChild(opt);
		if(value == val) {
			opt.selected = true;
		}
	}
	return targetEl;
}
var valueBorn;
var valueLabelCnBorn;
function focusEdit(event) {
	var target = event.srcElement;
	var td = target;
	if(target.tagName == "INPUT") {
		td = target.parentNode;
	}
	$(td).unbind("click");
	var inputs = td.getElementsByTagName("input");
	var value = "";
	var valueLabelCn = "";
	for(var i = 0; i < inputs.length; i++) {
		inputs[i].style.display="none";
		if(inputs[i].getAttribute("edittype") == "value") {
			value = inputs[i].value;
		}
		if(inputs[i].getAttribute("edittype") == "labelCn") {
			valueLabelCn = inputs[i].value;
		}
	}
	valueBorn=value;
	valueLabelCnBorn=valueLabelCn;
	var select = td.getElementsByTagName("select");
	var editdata = td.getAttribute("editdata");
	var data = eval(editdata);
	var select = createSelect(data,value);
	td.appendChild(select);
	$(select).focus();
	$(select).bind("blur",blurEdit);
}

function blurEdit(event) {
	var select = event.srcElement;
	var text = $(select).find("option:selected").text();
	if(text == "请选择...") text = valueLabelCnBorn;
	var inputs = select.parentNode.getElementsByTagName("input");
	for(var i = 0; i < inputs.length; i++) {
		inputs[i].style.display="";
		var edittype = inputs[i].getAttribute("edittype");
		if(edittype == "value") {
			if(select.value!=""){
				inputs[i].value = select.value;
			}
			else{
				inputs[i].value = valueBorn;
			}
		}else if(edittype == "labelCn") {
			inputs[i].value = text;
		}
	}
	$(select.parentNode).trigger("changeHandle");
	$(select.parentNode).one("click",focusEdit);
	$(select).unbind("blur");
	select.removeNode(true);
}