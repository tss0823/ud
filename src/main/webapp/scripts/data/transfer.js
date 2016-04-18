$(document).ready(function(){
	
	$.ajaxSetup({ 
	    async : false
	});
	
	var projectDataList = null;
	var url = appsPath + "/project/getSelList.htm";
	$.getJSON(url,function(dataList){
		projectDataList = dataList;
	});
	
	var appAllDataList = null;
	var appDataList = new Array();
	url = appsPath + "/application/getSelList.htm";
	$.getJSON(url,function(dataList){
		appAllDataList = dataList;
	});
	
	//来源项目
	initSelListByListData(projectDataList,"fromProjectId","id","name");
	
	//拷贝到项目
	initSelListByListData(projectDataList,"toProjectId","id","name");
	
	//绑定事件
	$("#fromProjectId").change(function(){
		var id = $(this).val();
		while(appDataList.length > 0){
			appDataList.pop();
		}
		for(var i = 0; i < appAllDataList.length; i++){
			var data = appAllDataList[i];
			if(data.projectId == id){
				appDataList.push(data);
			}
		}
		$("#fromAppId").empty();
		initSelListByListData(appDataList,"fromAppId","id","cnName");
	});
	
	$("#toProjectId").change(function(){
		var id = $(this).val();
		while(appDataList.length > 0){
			appDataList.pop();
		}
		for(var i = 0; i < appAllDataList.length; i++){
			var data = appAllDataList[i];
			if(data.projectId == id){
				appDataList.push(data);
			}
		}
		$("#toAppId").empty();
		initSelListByListData(appDataList,"toAppId","id","cnName");
	});
	
	//展现实体列表
	$("#fromAppId").change(function(){
		var id = $(this).val();
		//根据appid获得实体列表
		var url = appsPath + "/entity/getEntityListByAppId.htm";
		$.getJSON(url,{appId:id},function(dataList){
			//处理动态列表
			$(".tb_list #dyTr").remove();
			var arr = new Array();
			for(var i = 0; i < dataList.length; i++){
				var data = dataList[i];
				arr.push("<tr id='dyTr'>");
				arr.push("<td>");
				arr.push("<input type='checkbox' id='chkItem' name='entityIds' value='"+data.id+"'/>");
				arr.push("</td>");
				arr.push("<td>");
				arr.push(i+1);
				arr.push("</td>");
				arr.push("<td>");
				arr.push(data.enName);
				arr.push("</td>");
				arr.push("<td>");
				arr.push(data.cnName);
				arr.push("</td>");
				arr.push("<td>");
				arr.push(data.shortName);
				arr.push("</td>");
				arr.push("</tr>");
			}
			$(".tb_list").append(arr.join(""));
		});
	});

	
	$("#save").click(function(){
		showLoadTips("数据传输中，请稍候...");
		$.post("saveTransfer.htm",getFormParams(),function(rdata){
			var result = rdata.result;
			if(result == '0'){  //失败
				alert(rdata.msg);
			}else{  //成功
				showResultTips("数据传输成功");
				window.location = "transfer.htm"
			}
		},'json');
	});
});