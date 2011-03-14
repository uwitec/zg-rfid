<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
$.validator.setDefaults({
	submitHandler: function(form) {
		form.submit();
	}
});
$(function() {
	var commonMsg = "输入的数据格式有误！";
	//自定义校验class为FwEmployee.mobile的输入
	
	jQuery.validator.addMethod("number", function(value, element, params) { 
	    var validRegExp = {msg:'必须为正整数且不能为空！',exp:/^[1-9]\d*$/};
	    if(value==''){
	    	return false;
	    }
	    
	    try {
	    	return this.optional(element) || eval(validRegExp.exp).test(value);
	    }catch(e){
	    	alert("校验表达式有误！不能完成校验！");
	    	return false;
	    }
	}); 
	
	$("form[validator]").validate({
		rules: {
			displayOrder : {
				number : true
			},
			
			levelNum : {
				number : true
			},
			cuid : {}
		},
		messages: {
			displayOrder : {
				number : {msg:'必须为正整数且不能为空！',exp:/^[1-9]\d*$/}.msg?
					{msg:'必须为正整数且不能为空！',exp:/^[1-9]\d*$/}.msg:commonMsg
			},
			levelNum : {
				number : {msg:'必须为正整数且不能为空！',exp:/^[1-9]\d*$/}.msg?
					{msg:'必须为正整数且不能为空！',exp:/^[1-9]\d*$/}.msg:commonMsg
			},
			cuid : {}
		}
	});
});
</script>