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

var zTree = null ;

var module = null;

function onClick(event, treeId, treeNode, clickFlag) {
	//展开或者折叠
	zTree.expandNode(treeNode,true);
	var id = treeNode.id;
	var parentId = treeNode.parentId;
	refreshFrmEdit(id,parentId);
}


function refreshFrmEdit(id,parentId){
	var url = appsPath+"/"+module+"/enterEdit.htm?id="+id;
	if(parentId == 0){  //根节点
		url = appsPath+"/"+module+"/enterNew.htm?id="+id;
	}
	$("#frmEdit").attr("src",url);
}

function refresh(id,parentId){
	loadTree();
	var treeNode = zTree.getNodeByParam("id",id);
	zTree.selectNode(treeNode);
	zTree.expandNode(treeNode,true);
	refreshFrmEdit(id,parentId);
}

//加载json树
function loadTree(){
	var jsonArray = new Array();
	var rootId = null;
	$.post(appsPath+"/"+module+"/getTreeList.htm", function(arrData) {
		if (arrData == null || arrData.length == 0) {
			return;
		}
		var array = new Array();
		var level = 0;
		var openId = null;
		for (var i = 0; i < arrData.length; i++) {
			var data = arrData[i];
			var parentId = data.parentId;
			var id = data.id;
			var hid = id;
			//设置第二级展开
			var openAttr = false;
			if(parentId == 0){  //父级为0，则该节点为根节点
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
				parentId : parentId,
				name : data.name,
				open:openAttr
			};
			
			jsonArray.push(obj);
		}
		zTree = $.fn.zTree.init($("#treeDiv"), setting, jsonArray);

	}, 'json');
	return rootId;
}


$(document).ready(function() {
	module = $("#module").val();
	$.ajaxSetup({ 
	    async : false
	});
	//初始化右侧url
	var totalCount = $("#totalCount").val();
	if(totalCount == 0){
		$("#frmEdit").attr("src",appsPath+"/"+module+"/enterNewRoot.htm");
	}
	var rootId = loadTree();
	if(rootId != null){
		refreshFrmEdit(rootId,0);
		var treeNode = zTree.getNodeByParam("id",rootId);
		zTree.selectNode(treeNode);
	}
	
});

