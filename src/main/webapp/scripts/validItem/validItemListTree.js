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
	if(module == "app"){  //
		var url = appsPath+"/validItem/list.htm?appId="+id;
		$("#appId").val(id);
		$("#frmEdit").attr("src",url);
	}else if(module == "entity"){  //
		var url = appsPath+"/validItem/list.htm?entityId="+id;
		$("#entityId").val(id);
		$("#appId").val(treeNode.hpId);
		$("#frmEdit").attr("src",url);
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
	url = appsPath+"/entity/getTreeList.htm";
	getJsonTreeArr(jsonArray,url,"entity");
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
			var hparentId = parentId;
			var id = data.id;
			var hid = id;
			if(module == "app"){
				id = "app_"+id;
			}
			if(module == "entity"){
				parentId = "app_"+parentId;
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
				hpId : hparentId,
				name : data.name,
				open:openAttr,
				module:module
			};
			
			jsonArray.push(obj);
		}

	}, 'json');
}

