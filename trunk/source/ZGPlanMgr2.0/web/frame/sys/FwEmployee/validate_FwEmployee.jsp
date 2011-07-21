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
	jQuery.validator.addMethod("FwEmployee_mobile", function(value, element, params) { 
	    var validRegExp = {msg:'必须为手机号码',exp:/^(((1[0-9]{1}[0-9]{1}))+\d{8})$/};
	    try {
	    	return this.optional(element) || eval(validRegExp.exp).test(value);
	    }catch(e){
	    	alert("校验表达式有误！不能完成校验！");
	    	return false;
	    }
	}); 
	
	
	jQuery.validator.addMethod("FwEmployee_email", function(value, element, params) { 
	    var validRegExp = {msg:'必须为有效邮箱！',exp:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/};
	    
	    try {
	    	return this.optional(element) || eval(validRegExp.exp).test(value);
	    }catch(e){
	    	alert("校验表达式有误！不能完成校验！");
	    	return false;
	    }
	}); 
	
	$("form[validator]").validate({
		rules: {
			mobile : {
				FwEmployee_mobile : true
			},
			email : {
				FwEmployee_email : true
			},
			cuid : {}
		},
		messages: {
			mobile : {
				FwEmployee_mobile : {msg:'必须为手机号码',exp:/^(((1[0-9]{1}[0-9]{1}))+\d{8})$/}.msg?
					{msg:'必须为手机号码',exp:/^(((1[0-9]{1}[0-9]{1}))+\d{8})$/}.msg:commonMsg
			},
			email: {
				FwEmployee_email : {msg:'必须为有效邮箱',exp:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/}.msg?
					{msg:'必须为有效邮箱',exp:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/}.msg:commonMsg
			},
			cuid : {}
		}
	});
});
</script>