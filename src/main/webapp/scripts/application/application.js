$(function(){
	//设置module
	moduleInst.setName("application");
	
	$("#select").click(function(){
		var url = appsPath+"/template/select.htm?id=templateId&name=templateName";
		openSelectWin("选择模版",url,0.25,0.75);
	});
	
	$.ajaxSetup({ 
	    async : true
	});
	//实体项
	$("a[id='entityList']").click(function(){
		var url = appsPath+"/entity/list.htm?appId="+$(this).attr("param-id");
		var title = $(this).attr("param-title");
		openWin(title,url);
	});
	//配置项
	$("a[id='configItemList']").click(function(){
		var url = appsPath+"/configItem/list.htm?appId="+$(this).attr("param-id");
		var title = $(this).attr("param-title");
		openWin(title,url);
	});
	//升级
	$("a[id='upgrade']").click(function(){
		showLoadTips();
		var params = {id:$(this).attr("param-id")};
		$.post("upgrade.htm",params,function(data, textStatus){
			if(data == "1"){
				showResultTips();
			}
		},'json');
	});
	
	
	//构建SQL
	$("a[id='buildSql']").ajaxStart(function(){
		showLoadTips();
	})
	.ajaxComplete(function(){
		showResultTips();
	});
	$("a[id='buildSql']").click(function(){
		var params = {id:$(this).attr("param-id")};
		$.post("buildSql.htm",params,function(data, textStatus){
			if(data.result == "1"){
				alertText(data.data);
			}
		},'json');
	});
	//构建工程
	$("a[id='buildApp']").click(function(){
		var params = {id:$(this).attr("param-id")};
		$.post("buildApp.htm",params,function(data, textStatus){
			if(data == "1"){
			}
		},'json');
	});

	//上传SQL
	$("a[id='uploadSql']").click(function(){
		
		var params = {id:$(this).attr("param-id")};
		$.ajaxFileUpload
		(
				{
					url:'uploadSql.htm',
					secureuri:false,
					fileElementId:'sqlFile',
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
	//获得插入SQL
	$("a[id='getInsertSql']").click(function(){

		var params = {id:$(this).attr("param-id")};
		$.ajaxFileUpload
		(
			{
				url:'getInsertSql.htm',
				secureuri:false,
				fileElementId:'sqlFile',
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
					enName:{
						required:true,
						maxLength:20
					},
					cnName:{
						required:true,
						maxLength:20
					},
					templatePath:{
						required:true,
						maxLength:100
					},
					packageName:{
						required:true,
						maxLength:60
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
					},
					templatePath:{
						required:"'模板路径'不能为空",
						maxLength:"'模板路径'字符长度不能超过100个"
					},
					packageName:{
						required:"'包名'不能为空",
						maxLength:"'包名'字符长度不能超过60个"
					}
				}
			};
	}
})

//查询
function query(){
	var pageNum = $("#pageNum").val();
	$("#pageNum",parent.document).val(pageNum);
	$("#queryForm",parent.document).submit();
}