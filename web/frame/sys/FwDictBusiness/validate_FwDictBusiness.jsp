<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
$.validator.setDefaults({
	submitHandler: function(form) {
		form.submit();
	}
});
$(function() {
	var commonMsg = "输入的数据格式有误！";
	$("form[validator]").validate({
		rules: {
			cuid : {}
		},
		messages: {
			cuid : {}
		}
	});
});
</script>