$(function(){
	var appId = $("#appId").val();
	$("#enterNew").click(function(){
		window.location = "enterNew.htm?appId="+appId;
	});
	//校验
	if(!$.validator){
		return;
	}
	$.validator.setDefaults({keyupCheck:true});  //默认设置
	$("#editForm").validate({
		rules:{
			name:{
				required:true,
				maxLength:25
			},
			description:{
				maxLength:50
			},
		},
		messages:{
			name:{
				required:"'名称'不能为空",
				maxLength:"'名称'字符长度不能超过25个",
			},
			description:{
				maxLength:"'描述'字符长度不能超过50个",
			},
		}
	});
	
});

//删除
function remove(id,appId){
	if(confirm("您确认要删除吗？")){
		window.location="delete.htm?id="+id+"&appId="+appId;
	}
}