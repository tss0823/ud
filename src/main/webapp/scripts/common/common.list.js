$(document).ready(function(){
	$(document).keypress(function(e){
		if(e.which == 13){  //回车后查询
			$("#query").trigger("click");
		}
	});
	
	$("#query").click(function(){
		queryLeft();
	});
	

});

/**
 * 左侧查询
 */
function queryLeft(){
	var doc = topWin.frames[0].frames["frameRight"].window.document;
	$("#pageNum",doc).val(1);
	$("#queryForm",doc).submit();
}

/**
 * 右侧查询
 */
function query(){
	var doc = topWin.frames[0].frames["frameRight"].window.document;
	$("#pageNum",doc).val($("#pageNum").val());
	$("#queryForm",doc).submit();
}