
$(function() {
		alert('asdf');
		});
var INC_PATH = {
	EXPLORER  : ctx + '/explorer'
};
function $importjs(_path){
	document.write("<script type='text/javascript' src='" + _path + "'></scr" + "ipt>");
}

function isNotNull(_var){
    if(typeof(_var)!='undefined' && _var!=null && _var!='null' && _var!='NaN' && _var!=''){
        return true
    }else{
        return false
    }
}

function isNumber(oNum) { 
	if(!oNum) return false; 
	var strP=/^\d+(\.\d+)?$/; 
	if(!strP.test(oNum)) return false; 
	try{ 
		if(parseFloat(oNum)!=oNum) return false; 
	}catch(ex){ 
		return false; 
	} 
	return true; 
}


function converClassName(name) {
	if(name == null) return null;
	var filteredName = name;
	if(filteredName.indexOf("_") >= 0 && filteredName == filteredName.toUpperCase()) {
		filteredName = filteredName.toLowerCase();
	}
	if(filteredName.indexOf("_") == -1) {
		filteredName = filteredName.toLowerCase();
		return filteredName.substring(0, 1).toUpperCase()+filteredName.substring(1);
	}
	var resArray = [];
	if (filteredName != "" && filteredName.length > 0) {
		resArray.push(filteredName.substring(0, 1).toUpperCase());
		for (var i = 1; i < filteredName.length; i++) {
			var preChart = filteredName.substring(i - 1, i);
			var c = filteredName.substring(i, i + 1);
			if(c == "_") {
				continue;
			}
			if(preChart == "_" && c != "_" 
				&& resArray[resArray.length - 1] != resArray[resArray.length - 1].toUpperCase()){
				resArray.push(c.toUpperCase());
				continue;
			}else {
				resArray.push(c);
			}
		}
	}
	return resArray.join("");
}

//时间不能小于今天
function checkTime(timeStr,name,objName){
	var start  = new Date(timeStr.split(/ /g)[0].replace(/-/g, "/"));	
	var dateStr=start.getDate();
	start.setDate(dateStr+1);
	var nowDate=new Date();
	//alert(start+'    '+nowDate);
	if(start<=nowDate){
		alert(name+'不能早于当前时间');
		document.getElementById(objName).value="";
		return false;
	}
	return true;

}

//判断前面的时间不能大于后面的时间，后面的时间不能小于前面的时间（但2者可以相等）,如果有错误，就恢复到原先所选的值
function checkBeforeAndAfterTime(objBefore,objAfter,objOldValue,messagesBefore,messagesAfter,type){
	var beforeTime=document.getElementById(objBefore).value;
	var afterTime=document.getElementById(objAfter).value;
	
	if(beforeTime!=''&&afterTime!=''){
		if(beforeTime>afterTime){
			alert(messagesBefore+"时间不能大于"+messagesAfter);
			if(type==1){
				document.getElementById(objBefore).value=document.getElementById(objOldValue).value;//恢复原状
			}else{
				document.getElementById(objAfter).value=document.getElementById(objOldValue).value;//恢复原状
			}
		}
	
		document.getElementById(objOldValue).value=document.getElementById(objBefore).value;
	
	}
	
	
}

function changeGroup(obj) {
	if(obj.value != "") {
		CommonDwrAction.getUsersByOrgId(obj.value,function(data){
			buildSel("userSel",data);
		});
	}else {
		buildSel("userSel",[]);
	}
}

function buildSel(id,data) {
	var targetEl = document.getElementById(id);
	if(targetEl) {
		targetEl.innerHTML = "";
		var emptyOpt = document.createElement("option");
		emptyOpt.value="";
		emptyOpt.innerHTML = "请选择...";
		targetEl.appendChild(emptyOpt);
		for(var i = 0; i< data.length; i++) {
			var labelCn = data[i].labelCn;
			var cuid = data[i].cuid;
			var opt = document.createElement("option");
			opt.value=cuid;
			opt.innerHTML = labelCn;
			targetEl.appendChild(opt);
		}
	}
}

/**
 * 转换参数字符串为数组 如：id=13?deptId=11
 * 转换后可以读取:var params=new QueryString(str);
				var id=params["id"];
				var aufnr=params["deprId"];
 * @param {} str
 */
function QueryString(str)
{
	var name, value, i;
	var arrtmp = str.split("&");
	for (i = 0; i < arrtmp.length; i++) {
		num = arrtmp[i].indexOf("=");
		if (num > 0) {
			name = arrtmp[i].substring(0, num);
			value = arrtmp[i].substr(num + 1);
			this[name] = value;
		}
	}
}
	//select 添加option
	function addOptionToSelect(selName,id,text)
	{
	   var slt=document.getElementById(selName);
	   var objOption=document.createElement("OPTION");
	   objOption.value=id;
	   objOption.text=text;
	   slt.add(objOption);
	}	
	
	//判断IE是否是某个版本
function dealIEVersion(version){
	var theVersion = 7;
	if(navigator.appName == "Microsoft Internet Explorer") 
    { 
           if(navigator.appVersion.match(/6./i)=='6.') 
           {
               //IE6下下载
           	   theVersion = 6;
           	      
           }
           if(navigator.appVersion.match(/7./i)=='7.') 
           {
                 //IE7下下载
           	     theVersion = 7;
           }
           if(navigator.appVersion.match(/8./i)=='8.') 
           {
           	    //IE8下下载
                 theVersion = 8;
           }
           
           
    } 
    if(theVersion==version){
    	return true;
    }else{
    	return false;
    }
    
    function toBreakWord(objName,intLen){   
		var obj=document.getElementById(objName);   
		var strContent=obj.innerHTML;     
		var strTemp="";   
		while(strContent.length>intLen){   
			strTemp+=strContent.substr(0,intLen)+" ";     
			strContent=strContent.substr(intLen,strContent.length);     
		}   
		strTemp+=""+strContent;   
		obj.innerHTML=strTemp;   
}   
   
}
	
	