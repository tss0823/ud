var setting = {
		check: {
			enable: true
		},
	data : {
		simpleData : {
			enable : true
		}
	}

};





var zTree = null;
var rootId = null;
$(document).ready(function() {
	$.ajaxSetup({ 
	    async : false
	});
	var jsonArray = new Array();
	var url = appsPath+"/project/getTreeList.htm";
	getJsonTreeArr(jsonArray,url,"project");
	url = appsPath+"/application/getTreeList.htm";
	getJsonTreeArr(jsonArray,url,"app");
	url = appsPath+"/entity/getTreeList.htm";
	getJsonTreeArr(jsonArray,url,"entity");
	url = appsPath+"/valid/getTreeList.htm";
	getJsonTreeArr(jsonArray,url,"valid");
	
	//获得保存的property,处理是否选中
	url = appsPath+"/validItem/getPropItemTreeList.htm?validItemId="+$("#id").val();
	var propJsonArray = new Array();
	getJsonTreeArr(propJsonArray,url,"itemProp");
	for(var i = 0; i < jsonArray.length; i++){
		var data = jsonArray[i];
		if(data.module != "valid"){
			continue;
		}
		for(var j = 0; j < propJsonArray.length; j++){
			var propData = propJsonArray[j];
			if(propData.hid == data.hid){
				data["checked"] = true;
				break;
			}
		}
	}
	//初始化树
	zTree = $.fn.zTree.init($("#treeDiv"), setting, jsonArray);

});

//加载获得tree json
function getJsonTreeArr(jsonArray,url,module){
	$.post(url, function(arrData) {
		if (arrData == null || arrData.length == 0) {
			return;
		}
		var array = new Array();
		for (var i = 0; i < arrData.length; i++) {
			var data = arrData[i];
			var parentId = data.parentId;
			var id = data.id;
			var hid = id;
			if(module == "app"){
				id = "app_"+id;
			}else if(module == "entity"){
				parentId = "app_"+parentId;
				id = "entity_"+id;
			}else if(module == "valid"){
				parentId = "entity_"+parentId;
				id = "valid_"+id;
			}else if(module == "itemProp"){
				parentId = "valid_"+parentId;
				id = "itemProp_"+id;
			}
			//设置第二级展开
			var openAttr = false;
			if(parentId == -1){
				openAttr = true;
				rootId = id;
			}
			var obj = {
				id : id,
				hid : hid,
				pId : parentId,
				name : data.name,
				open:openAttr,
				module:module
			};
			
			jsonArray.push(obj);
		}

	}, 'json');
}

function getPropIds(){
	var nodes = zTree.getCheckedNodes(true);
	var arr = new Array();
	for(var i = 0; i < nodes.length; i++){
		var node = nodes[i];
		if(node.module == 'valid'){
			arr.push(node.hid);
		}
	}
	return arr;
	
}