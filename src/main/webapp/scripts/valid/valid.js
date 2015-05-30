$(document).ready(function(){

	//保存
	$("#saveOrUpdate").click(function(){
//		if(!$("#editForm").validate(vSettings)){
//			return;
//		}
		showLoadTips();
		$.post("saveOrUpdate.htm",getFormParams(),function(data, textStatus){
			if(data == "1"){
				showResultTips();
				query();
			}
		},'json');
	});
	
	var getValidRuleListUrl = appsPath+"/validRule/getJsonList.htm";
	initAllSelListByUrl(getValidRuleListUrl,"ruleId","id","name");
	
	
});


//查询
function query(){
	var pageNum = $("#pageNum").val();
	$("#pageNum",parent.document).val(pageNum);
	$("#queryForm",parent.document).submit();
}