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
	var id = treeNode.id;
	if(id != 0){  //根节点
		$("#projectId").val(id);
		//url = appsPath+"/application/list.htm";
	}
	queryLeft();
}



var zTree = null;
var rootId = null;
$(document).ready(function() {
	
	$.ajaxSetup({ 
	    async : false
	});
	// 加载获得tree json
	var jsonArray = new Array();
	var url = appsPath+"/project/getTreeList.htm";
	$.post(url, function(arrData) {
		if (arrData == null || arrData.length == 0) {
			return;
		}
		var array = new Array();
		var openId = null;
		for (var i = 0; i < arrData.length; i++) {
			var data = arrData[i];
			var parentId = data.parentId;
			var id = data.id;
			var hid = id;
			//设置第二级展开
			var openAttr = false;
			if(parentId == -1){
				openId = id;
				openAttr = true;
				rootId = id;
			}else if(openId == parentId){
				openAttr = true;
			}
			var obj = {
				id : id,
				hid : hid,
				pId : parentId,
				name : data.name,
				open:openAttr
			};
			
			jsonArray.push(obj);
		}
		zTree = $.fn.zTree.init($("#treeDiv"), setting, jsonArray);

	}, 'json');
	
	if(rootId != null){
		var treeNode = zTree.getNodeByParam("id",rootId);
		zTree.selectNode(treeNode);
	}

});

