$(function(){

	//设置module
	moduleInst.setName("configItem");
	
	var appId = $("#appId").val();
	
	$("#enterNew").unbind();
	$("#enterNew").click(function(){
		window.location = "enterNew.htm?appId="+appId;
	});
	$("#enterEditAll").click(function(){
		window.location = "enterEditAll.htm?appId="+appId;
	});
	
	$("#list").click(function(){
		window.location = "list.htm?appId="+appId;
	});
	
	
	// 删除
	$("a[id='remove']").unbind();
	$("a[id='remove']").click(function(){
		var key = $(this).attr("param-key");
		var appId = $(this).attr("param-appId");
		confirm("您确认需要删除吗？",function(){
			showLoadTips();
			$.post("delete.htm",{key:key,appId:appId},function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		});
		
	});
	
	//删除所有
	$("#deleteAll").click(function(){
		confirm("您确认需要删除吗？",function(){
			showLoadTips();
			$.post("deleteAll.htm",{appId:appId},function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		});
		
	});
	
	$("#update").unbind();
	$("#update").click(function(){
		if(!$("#editForm").validate(vSettings)){
			return;
		}
		showLoadTips();
		var params = getFormParams();
		$.post("update.htm",params,function(data, textStatus){
			if(data == "1"){
				showResultTips();
				query();
				window.location = "enterEdit.htm?key="+params.key+"&appId="+params.appId;
			}
		},'json');
	});
	
	$("#updateAll").click(function(){
		if(!$("#editForm").validate(vSettings)){
			return;
		}
		showLoadTips();
		var params = getFormParams();
		$.post("updateAll.htm",params,function(data, textStatus){
			if(data == "1"){
				showResultTips();
				query();
				window.location = "enterEditAll.htm?appId="+params.appId;
			}
		},'json');
	});


	$.ajaxSetup({ 
	    async : true
	});


	



	//上传prop文件
	$("#uploadProp").click(function(){
		
		var params = {appId:appId};
		$.ajaxFileUpload
		(
				{
					url:'uploadProp.htm',
					secureuri:false,
					fileElementId:'propFile',
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
	
	//校验
	if(typeof($.Validator) != 'undefined'){
		vSettings = {
				rules:{
					key:{
						required:true,
						maxLength:100
					},
					vals:{
						maxLength:200
					}
				},
				messages:{
					key:{
						required:"'名称'不能为空",
						maxLength:"'名称'字符长度不能超过100个",
					},
					vals:{
						maxLength:"'值'字符长度不能超过200个",
					}
				}
			};
	}

	
});


function query(){
	var win = topWin.frames["hmainWin"].window;
	win.location.reload();
}

