$(function(){
	
	//设置module
	moduleInst.setName("entity");
	
	var appId = $("#appId",parent.document).val();
	
	//属性项列表
	$("a[id='propList']").click(function(){
		var url = appsPath+"/property/list.htm?entityId="+$(this).attr("param-id");
		var title = $(this).attr("param-title");
		openWin(title,url);
	});
	//属性项详情
	$("a[id='propDetail']").click(function(){
		var url = appsPath+"/property/listDetail.htm?entityId="+$(this).attr("param-id");
		var title = $(this).attr("param-title");
		openWin(title,url);
	});
	
	// 保存
	$("#copy").click(function(){
		var ids = getChkIds();
		if(!ids){
			return;
		}
		if(ids.indexOf(",") != -1){
			alert("仅选择一项");
			return;
		}
		confirm("您确认要复制？",function(){
			showLoadTips();
			var params = {id:ids};
			$.post("copy.htm",params,function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		});
	});
	
	
	$("#syncApp").click(function(){  //同步工程
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		confirm("您确认要同步工程？",function(){
			showLoadTips();
			var params = {ids:ids,appId:appId};
			$.post("syncApp.htm",params,function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		})
	});
	$("#syncSql").click(function(){  //同步数据库
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		showLoadTips();
		var params = {ids:ids,appId:appId};
		$.post("syncSql.htm",params,function(data, textStatus){
			if(data.result == "1"){
				showResultTips();
				alertText(data.data);
			}
		},'json');
	});
	
	$("#syncValid").click(function(){  //同步校验
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		showLoadTips();
		var params = {ids:ids,appId:appId};
		$.post("syncValid.htm",params,function(data, textStatus){
			if(data == "1"){
				showResultTips();
			}
		},'json');
	});
	
	$("#buildApp").click(function(){  //构建工程
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		confirm("您确认要构建工程？",function(){
			showLoadTips();
			var params = {ids:ids,appId:appId};
			$.post("buildApp.htm",params,function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		})
	});
	$("#buildSql").click(function(){  //同步数据库
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		showLoadTips();
		var params = {ids:ids,appId:appId};
		$.post("buildSql.htm",params,function(data, textStatus){
			if(data.result == "1"){
				showResultTips();
				alertText(data.data);
			}
		},'json');
	});
	$("#buildBeanConfig").click(function(){  //构建实体配置
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		showLoadTips();
		var params = {ids:ids,appId:appId};
		$.post("buildBeanConfig.htm",params,function(data, textStatus){
			if(data.result == "1"){
				showResultTips();
				alertText(data.data);
			}
		},'json');
	});
	
	$("#buildDelSql").click(function(){  //构建删除数据库
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		showLoadTips();
		var params = {ids:ids,appId:appId};
		$.post("buildDelSql.htm",params,function(data, textStatus){
			if(data.result == "1"){
				showResultTips();
				alertText(data.data);
			}
		},'json');
	});
	
	$("#deleteAll").click(function(){  //删除所有
		var ids = getChkIds();
		if(!ids){
			alert("请选择一项");
			return;
		}
		confirm("您确认要删除？",function(){
			showLoadTips();
			var params = {ids:ids};
			$.post("deleteAll.htm",params,function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		});
		
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
//function query(){
//	var pageNum = $("#pageNum").val();
//	$("#pageNum",parent.document).val(pageNum);
//	$("#queryForm",parent.document).submit();
//}

