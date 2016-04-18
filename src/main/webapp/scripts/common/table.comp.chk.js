$(document).ready(function(){	
	var fixItem = "fixItem";
	
	//添加
	$("#addItem").click(function(){
		$("#tbList tr:last").clone().appendTo("#tbList");
	});
	
	//批量复制
	$("#copyItemBatch").click(function(){
		var ids = getChkIds();
		if(!ids){
			alert("请选择您要复制的项");
			return;
		}
		$("#tbList tr").each(function(){
			var chkState = $(this).find("td #chkItem").attr("checked");
			if(chkState){
				$(this).clone().appendTo("#tbList");
			}
		});
	});
	
	//批量删除
	$("#delItemBatch").click(function(){
		var ids = getChkIds();
		if(!ids){
			alert("请选择您要删除的项");
			return;
		}
		var trLen = $("#tbList tr").length;
		var delLen = ids.split(",").length;
		if(trLen - delLen < 2){
			alert("必须保留一项");
			return;
		}
		$.post("deleteBatch.htm",{ids:ids},function(d){
			if(d == "1"){
				$("#tbList tr").each(function(){
					var chkState = $(this).find("td #chkItem").attr("checked");
					if(chkState){
						$(this).remove();
					}
				});
			}else{
				alert("删除失败");
			}
		},"json");
	});
	
	//扩展添加特殊行 start
	//id行
	$("#chkId").click(function(){
		if($(this).prop("checked")){
			var obj = $("#"+fixItem).clone();
			obj.find("input:eq(1)").val("id");
			obj.find("input:eq(2)").val("ID");
			obj.find("input:eq(3)").val("20");
			obj.find("select:eq(0)").find("option[value='java.lang.Long']").attr("selected","selected");
			obj.find("select:eq(1)").find("option[value='0']").attr("selected","selected");
			obj.find("select:eq(2)").find("option[value='1']").attr("selected","selected");
			obj.find("select:eq(3)").find("option[value='3']").attr("selected","selected");
			obj.appendTo("#tbList");
		}else{
			$("#tbList").find("input[value='id']").parents("tr").remove();
		}
		
	});
	//创建时间行
	$("#chkGmtCreate").click(function(){
		if($(this).prop("checked")){
			var obj = $("#"+fixItem).clone();
			obj.find("input:eq(1)").val("gmtCreate");
			obj.find("input:eq(2)").val("创建时间");
			obj.find("input:eq(3)").val("");
			obj.find("select:eq(0)").find("option[value='java.util.Date']").attr("selected","selected");
			obj.find("select:eq(1)").find("option[value='0']").attr("selected","selected");
			obj.find("select:eq(2)").find("option[value='0']").attr("selected","selected");
			obj.appendTo("#tbList");
		}else{
			$("#tbList").find("input[value='gmtCreate']").parents("tr").remove();
		}
		
	});
	//修改时间行
	$("#chkGmtModify").click(function(){
		if($(this).prop("checked")){
			var obj = $("#"+fixItem).clone();
			obj.find("input:eq(1)").val("gmtModify");
			obj.find("input:eq(2)").val("修改时间");
			obj.find("input:eq(3)").val("");
			obj.find("select:eq(0)").find("option[value='java.util.Date']").attr("selected","selected");
			obj.find("select:eq(1)").find("option[value='0']").attr("selected","selected");
			obj.find("select:eq(2)").find("option[value='0']").attr("selected","selected");
			obj.appendTo("#tbList");
		}else{
			$("#tbList").find("input[value='gmtModify']").parents("tr").remove();
		}
		
	});
	//删除状态行
	$("#chkDelState").click(function(){
		if($(this).prop("checked")){
			var obj = $("#"+fixItem).clone();
			obj.find("input:eq(1)").val("delState");
			obj.find("input:eq(2)").val("删除状态（0：已删除；1：未删除）");
			obj.find("input:eq(3)").val("");
			obj.find("select:eq(0)").find("option[value='java.lang.Boolean']").attr("selected","selected");
			obj.find("select:eq(1)").find("option[value='0']").attr("selected","selected");
			obj.find("select:eq(2)").find("option[value='0']").attr("selected","selected");
			obj.appendTo("#tbList");
		}else{
			$("#tbList").find("input[value='delState']").parents("tr").remove();
		}
		
	});
	//end
	
	//删除
	$(document).on("click","#delItem",function(){
		var parentTr = $(this).parents("tr");
		if($("#tbList tr").length == 2){
			alert("必须保留一项");
			return;
		}
		
		var id = $(this).attr("param-id");
		if(id){   //后台db删除
			confirm("您确认需要删除？",function(){
				$.post("delete.htm",{id:id},function(d){
					if(d == "1"){
						//alert(parentTr.length);
						parentTr.remove();
					}else{
						alert("删除失败");
					}
				},"json");
			});
			
		}else{
			parentTr.remove();
		}
	});
	
	//鼠标移动事件
	$("#tbList").find("tr").each(function(){
		$(this).find("td input").mouseenter(function(){
			$(this).parent().parent().find("td :text").css("background","#FFE4C4");
			$(this).parent().parent().find("td select").css("background","#FFE4C4");
		});
		$(this).mouseout(function(){
			$(this).find("td :text").css("background","#FFFFFF");
			$(this).find("td select").css("background","#FFFFFF");
		});
		
	});
	
	
});