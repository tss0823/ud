$(document).ready(function(){
	//设置module
	moduleInst.setName("genFile");
	

	//下载
	$("a[id='download']").click(function(){
		var id = $(this).attr("param-id");
		$("#id").val(id);
		$("#downloadForm").submit();
		
	});
	
	
})

