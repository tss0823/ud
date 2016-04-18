$(document).ready(function(){
	//设置module
	moduleInst.setName("template");
	
	$("#confirm").click(function(){
		var ids = getChkIds();
		if(!ids){
			return;
		}
		if(ids.indexOf(",") != -1){
			alert("仅选择一项");
			return;
		}
		var destObj = getDestObj();
		var id = $("#id").val();
		var name = $("#name").val();
		$("#"+id,destObj).val(ids.split("|")[0]);
		$("#"+name,destObj).val(ids.split("|")[1]);
		closeWin();
	});
	$("#cancel").click(function(){
		closeWin();
	});
	
	//保存模版
	$("#save").unbind();
	$("#save").click(function(){
		var params = getFormParams();
		$.ajaxFileUpload
		(
				{
					url:'save.htm',
					secureuri:false,
					fileElementId:'tplFile',
					dataType: 'json',
					data:params,
					success: function (data, status)
					{
						showResultTips();
					},
					error: function (data, status, e)
					{
						alert(e);
					}
				}
		)
		
	});
	//修改模版
	$("#update").unbind();
	$("#update").click(function(){
		var params = getFormParams();
		$.ajaxFileUpload
		(
				{
					url:'update.htm',
					secureuri:false,
					fileElementId:'tplFile',
					dataType: 'json',
					data:params,
					success: function (data, status)
					{
						showResultTips();
					},
					error: function (data, status, e)
					{
						alert(e);
					}
				}
		)
		
	});
	
	//下载
	$("a[id='download']").click(function(){
		var id = $(this).attr("param-id");
		$("#id").val(id);
		$("#downloadForm").submit();
		
	});
	
	//校验
	if(typeof($.Validator) != 'undefined'){
		vSettings = {
				rules:{
					name:{
						required:true,
						maxLength:20
					},
					type:{
						required:true,
						maxLength:25
					}
				},
				messages:{
					name:{
						required:"'名称'不能为空",
						maxLength:"'名称'字符长度不能超过20个"
					}
				}
			};
	}
	
})

function getDestObj(){
	var destObj = null;
	if(parent.frames['hmainWin']){
		destObj = parent.frames['hmainWin'].document;
	}else if(parent.frames['frameIndex'].frames['frameRight'].frames['frmEdit']){
		destObj = parent.frames['frameIndex'].frames['frameRight'].frames['frmEdit'].document;
	}else{
		destObj = parent.frames['frameIndex'].frames['frameRight'].document;
	}
	return destObj;
}
	