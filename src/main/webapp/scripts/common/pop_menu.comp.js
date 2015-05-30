$(document).ready(function(){
	$("input[id='popBtn']").click(function(){
		if($(this).val() == "打开"){
			$(this).parent().find(".pop_menu").show();
			$(this).val("关闭");
		}else{
			$(this).parent().find(".pop_menu").hide();
			$(this).val("打开");
		}
	});
	
});