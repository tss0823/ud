var setting = {
	data : {
		simpleData : {
			enable : true
		}
	}
};


$(document).ready(function() {
	$.ajaxSetup({ 
	    async : false
	});
	// 加载获得tree json
	var jsonArray = new Array();
	var module = $("#module").val();
	var url = appsPath + "/" + module +　"/getTreeList.htm" ;
	$.post(url, function(arrData) {
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
			if(parentId == 0){
				openId = id;
				openAttr = true;
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
		$.fn.zTree.init($("#treeDiv"), setting, jsonArray);
		
		var zTree = $.fn.zTree.getZTreeObj("treeDiv");
		//设置回显,选择
		var destObj = getDestObj();
		var treeId = $("#treeId").val();
		var destId = $("#"+treeId,destObj).val();
		var node = zTree.getNodeByParam("id",destId);
		zTree.selectNode(node);

	}, 'json');
	
	function getDestObj(){
		var destObj = null;
		if(parent.frames['hmainWin']){
			destObj = parent.frames['hmainWin'].document;
		}else if(parent.frames['frameIndex'].frames['frameRight'].frames['frmEdit']){
			destObj = parent.frames['frameIndex'].frames['frameRight'].frames['frmEdit'].document;
		}else{
			destObj = parent.frames['frameIndex'].frames['frameRight'].document;
		}
		return destObj;
	}
	
	//按钮事件
	$("#confirm").click(function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDiv");
		var nodes = zTree.getSelectedNodes();
		var node = nodes[0];
		var destObj = getDestObj();
		var treeId = $("#treeId").val();
		var treeName = $("#treeName").val();
		$("#"+treeId,destObj).val(node.id);
		$("#"+treeName,destObj).val(node.name);
		closeWin();
	});
	
	$("#cancel").click(function(){
		closeWin();
	});

});
