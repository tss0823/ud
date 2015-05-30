$(document).ready(function(){
	//设置module
	moduleInst.setName("validItem");
	
	// 进入新增
	$("#enterNew").unbind(); 
	$("#enterNew").click(function(){
		var title = $(this).attr("param-title");
		var appId = $("#appId",parent.document).val();
		var entityId = $("#entityId",parent.document).val();
		var queryUrl = "appId="+appId+"&entityId="+entityId;
		var url = appsPath+"/"+moduleInst.getName()+"/enterNew.htm?"+queryUrl;
		openWin(title,url);
	});
	
	//保存
	$("#save").unbind();
	$("#save").click(function(){
		if(!$("#editForm").validate(vSettings)){
			return;
		}
		showLoadTips();
		var formParams = getFormParams();
		formParams["propIds"] = getPropIds();
		$.post('save.htm',formParams,function(data, textStatus){
			if(data == "1"){
				showResultTips();
				query();
				var appId = $("#appId").val();
				var entityId = $("#entityId").val();
				var queryUrl = "appId="+appId+"&entityId="+entityId;
				var url = appsPath+"/"+moduleInst.getName()+"/enterNew.htm?"+queryUrl;
				window.location = url;
			}
		},'json');
	});
	
	//修改
	$("#update").unbind();
	$("#update").click(function(){
		if(!$("#editForm").validate(vSettings)){
			return;
		}
		showLoadTips();
		var formParams = getFormParams();
		formParams["propIds"] = getPropIds();
		$.post('update.htm',formParams,function(data, textStatus){
			if(data == "1"){
				showResultTips();
				query();
				var url = appsPath+"/"+moduleInst.getName()+"/enterEdit.htm?id="+$("#id").val();
				window.location = url;
			}
		},'json');
	});
	
	$("#syncValidFile").click(function(){  //同步校验文件爱你
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		confirm("您确认要同步校验文件？",function(){
			showLoadTips();
			var appId = $("#appId",parent.document).val();
			var params = {ids:ids,appId:appId};
			$.post("syncValidFile.htm",params,function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		})
	});
	
	
	
	//校验
	if(typeof($.Validator) != 'undefined'){
		vSettings = {
				rules:{
					enName:{
						required:true,
						maxLength:20
					},
					cnName:{
						required:true,
						maxLength:20
					}
				},
				messages:{
					enName:{
						required:"'英文名称'不能为空",
						maxLength:"'英文名称'字符长度不能超过20个"
					},
					cnName:{
						required:"'中文名称'不能为空",
						maxLength:"'中文名称'字符长度不能超过20个"
					}
				}
			};
	}
});


//查询
function query(){
	var pageNum = $("#pageNum").val();
	$("#pageNum",parent.document).val(pageNum);
	$("#queryForm",parent.document).submit();
}