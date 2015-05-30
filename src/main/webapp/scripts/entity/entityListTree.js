var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		//beforeClick: beforeClick,
		onClick: onClick
	}

};

function onClick(event, treeId, treeNode, clickFlag) {
	var id = treeNode.hid;
	var module = treeNode.module;
	if(module == "app"){  
		//var url = appsPath+"/entity/list.htm?appId="+id;
		$("#appId").val(id);
		queryLeft();
	}
}



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
				id = parentId+"_"+id;
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

