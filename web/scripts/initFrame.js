$(function() {
	//日期控件初始化
	$("input[dateFlag=true]")
		.datepicker({
			showAnim:'',
			showOtherMonths: true,
			changeMonth:true,
			changeYear:true,
			selectOtherMonths: true,
			dateFormat:"yy-mm-dd"
		})
		.css("cursor","pointer")
		.after("<img src='"+ctx+"/resources/images/frame/calendar.png'></img>");
	$(".tableHeader").bind("mouseover",function(){
		this.style.backgroundColor='#DBFFF2';
	});
	$(".tableHeader").bind("mouseout",function(){
		this.style.backgroundColor='#DFEAFB';
	});
	//修正grid-panel尺寸
	var gridPanelW = $(".grid-panel").width();
	$(".grid-panel").width(gridPanelW-2);
	//添加grid鼠标经过高亮显示监听
	$(".odd,.even").bind("mouseover",function(){
		this.style.backgroundColor = "#EBF1FF";
	});
	$(".odd,.even").bind("mouseout",function(){
		this.style.backgroundColor = "#FFFFFF";
	});
	//初始化expand工具条
	$(".expandbtn > img").bind("click",function(){
		var dolayout = this.getAttribute("dolayout");
		var type = this.getAttribute("type");
		if(!type || type == null || type=="" || type == "expand") {
			if(this.src.indexOf("ico_expand.gif") == -1) {
				this.src=ctx+"/resources/images/frame/ico_expand.gif";
			}else {
				this.src=ctx+"/resources/images/frame/ico_noexpand.gif";
			}
			var forAttr = this.getAttribute("for");
			if(forAttr)
				$("*[attr='"+forAttr+"']").toggleClass("unexpand");
		}else if(type == "expandAll") {
			this.setAttribute("type","unexpandAll");
			$(".expandbtn > img").attr("src",ctx+"/resources/images/frame/ico_noexpand.gif");
			$("*[attr]").toggleClass("unexpand",false);
		}else if(type == "unexpandAll") {
			this.setAttribute("type","expandAll")
			$(".expandbtn > img").attr("src",ctx+"/resources/images/frame/ico_expand.gif");
			$("*[attr]").toggleClass("unexpand",true);
		}
		if(dolayout == "true" && doLayout) {
			doLayout();
		}
	});
});