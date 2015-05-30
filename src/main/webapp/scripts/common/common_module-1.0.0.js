var Module = function(){
	this.id = null;
	this.name = null;
}

Module.prototype.setName = function(_name){
	this.name = _name;
}
Module.prototype.getName = function(){
	return this.name;
}

var moduleInst = new Module();;

$(document).ready(function(){
	
	// 进入新增
	$("#enterNew").click(function(){
		var prop = $(this).attr("param-id-prop");
		var id = $(this).attr("param-id");
		var title = $(this).attr("param-title");
		var queryUrl = "id="+id;
		if(typeof(prop) != 'undefined'){
			queryUrl = prop+"="+id;
		}
		var url = appsPath+"/"+moduleInst.getName()+"/enterNew.htm?"+queryUrl;
		openWin(title,url);
	});
	
	// 进入修改
	$("a[id='enterEdit']").click(function(){
		var id = $(this).attr("param-id");
		var title = $(this).attr("param-title");
		var url = appsPath+"/"+moduleInst.getName()+"/enterEdit.htm?id="+id;
		openWin(title,url);
	});
	
	
	// 保存
	$("#save").click(function(){
		if(!$("#editForm").validate(vSettings)){
			return;
		}
		var prop = $(this).attr("param-id-prop");
		var id = $(this).attr("param-id");
		var queryUrl = null;
		if(typeof(prop) != 'undefined'){
			queryUrl = prop+"="+id;
		}
		showLoadTips();
		$.post("save.htm",getFormParams(),function(data, textStatus){
			if(data == "1"){
				showResultTips();
				var returnUrl = "enterNew.htm";
				if(queryUrl != null){
					returnUrl += "?"+queryUrl;
				}
				window.location = returnUrl;
				query();
			}
		},'json');
	});
	
	// 修改
	$("#update").click(function(){
		if(!$("#editForm").validate(vSettings)){
			return;
		}
		showLoadTips();
		var prop = $(this).attr("param-id-prop");
		var id = $(this).attr("param-id");
		$.post("update.htm",getFormParams(),function(data, textStatus){
			if(data == "1"){
				showResultTips();
				query();
				var queryUrl;
				if(typeof(prop) != 'undefined'){
					queryUrl = prop+"="+id;
				}else{
					queryUrl = "id="+$("#id").val();
				}
				window.location = "enterEdit.htm?"+queryUrl;
			}
		},'json');
	});
	
	// 删除
	$("a[id='remove']").click(function(){
		var id = $(this).attr("param-id");
		confirm("您确认需要删除吗？",function(){
			showLoadTips();
			$.post("delete.htm",{id:id},function(data, textStatus){
				if(data == "1"){
					showResultTips();
					query();
				}
			},'json');
		});
		
	});
	
	
});

function query(){
	var doc = topWin.frames[0].frames["frameRight"].window.document;
	$("#pageNum",doc).val($("#pageNum").val());
	$("#queryForm",doc).submit();
}