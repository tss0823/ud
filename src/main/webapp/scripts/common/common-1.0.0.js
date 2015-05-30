var appsPath;
var imagesPath;
var authorityPath;
var topWin;
var dateSettings;
$(document).ready(function(){
	
	//取消默认动作
//	if (!event || !event.preventDefault) {
//	    event.preventDefault = function() {
//	        event.returnValue = false; //ie
//	    };
//	}else{
//		event.preventDefault();
//	}
	
	//校验全局设置
	if(typeof($.Validator) != 'undefined'){
		$.Validator.setDefaults({debug:false});
	}	
	
	//jquery ajax全局设置
	$.ajaxSetup({ 
	    traditional:true
	});
	
     
	
	topWin = getTopWin();
	
	//全局设置
	appsPath = $("#appsPath").val();
	authorityPath = $("#authorityPath").val();
	imagesPath = $("#imagesPath").val();
	
	dateSettings = {
		    showOn: "button",
			buttonImage: imagesPath+"/imagesCommon/frame/v1/calendar.gif",
			buttonImageOnly: true
		} ;
	
	//全选、反选
	$("#chkAll").click(function(){
		var state = $(this).prop("checked");
		$(":checkbox[id='chkItem']").prop("checked",state);
	});
	
	//子项选择处理
	$(":checkbox[id='chkItem']").each(function(){
		$(this).click(function(){
			var state = true;
			$(":checkbox[id='chkItem']").each(function(){
				if(!$(this).prop("checked")){
					state = false;
					return false;
				}
			});
			$("#chkAll").prop("checked",state);
		});
	});
	
	//switch 处理
	//top
	$("#switch_top").click(function(){
		if($(this).find("div").attr("class") == "sw-top"){
			$("#header").hide(100);
			$("#main").height("660px");
			$(this).find("div").attr("class","sw-bottom");
		}else{
			$("#header").show();
			$("#main").height("600px");
			$(this).find("div").attr("class","sw-top");
		}
	})
	//left
	$("#switch_left").click(function(){
		if($(this).find("div").attr("class") == "sw-left"){
			$("#left").hide(100);
			$("#right").css("width","98%");
			$(this).find("div").attr("class","sw-right");
		}else{
			$("#left").show();
			$("#right").width("83%");
			$(this).find("div").attr("class","sw-left");
		}
	})
	//query
	$("#switch_query").click(function(){
		if($(this).find("div").attr("class") == "sw-top"){
			$("#query_block_expand").hide(100);
			$("#main").height("640px");
			$(this).find("div").attr("class","sw-bottom");
		}else{
			$("#query_block_expand").show();
			$("#main").height("600px");
			$(this).find("div").attr("class","sw-top");
		}
	})
	
	//注销
	$("#logout").click(function(){
		confirm("您确定要注销",function(){
			top.window.location = "logout.htm";
		});
		

		
	});
	
	//鼠标移动事件
	$(".tb_list").find("tr").each(function(){
		$(this).find("td").mouseover(function(){
			$(this).parent().addClass("td_mouse_on");
		});
		$(this).find("td").mouseout(function(){
			$(this).parent().removeClass("td_mouse_on");
		});
		
	});
	
	

	
});

//顶部窗口
function getTopWin(){
	var topWin = window.top;
	var parentWin = window.parent;
	while(topWin != parentWin){
		parentWin = parentWin.parent;
	}
	return parentWin;
}
//根据父key 获得数据字典集合
//提交到dc
function getDcDataDicByParentKey(parentKey){
	return getJsonp("","common/getDataDicByParentKey.htm",{key:parentKey});
}
//根据父code 获得数据字典集合
//提交到dc
function getDcDataDicByParentCode(parentCode){
	return getJsonp("","common/getDataDicByParentCode.htm",{code:parentCode});
}

//根据父code 获得数据字典集合
function getDataDicByParentCode(parentCode){
	return getAuthonJsonp("dataDic/getDataDicByParentCode.htm",{code:parentCode});
}
//根据parentid 获得数据字典集合
function getDataDicByParentParentid(parentid){
	return getAuthonJsonp("dataDic/getDataDicByParentParentid.htm",{parentid:parentid});
}
//获得组织机构
function getStructureById(id){
	return getAuthonJsonp("structure/getStructure.htm",{id:id});
}
//获得行政区划
function getAdminAreaById(id){
	return getAuthonJsonp("adminArea/getAdminArea.htm",{id:id});
}
//获得数据字典
function getAdminAreaById(id){
	return getAuthonJsonp("dataDic/getDataDic.htm",{id:id});
}
//获得模块
function getModuleById(id){
	return getAuthonJsonp("module/getModule.htm",{id:id});
}
//获得角色
function getRoleById(id){
	return getAuthonJsonp("role/getRole.htm",{id:id});
}

function dateBind(id){
	if(typeof($.datepicker) == "undefined"){
		return;
	}
	$.datepicker.setDefaults($.datepicker.regional[ "zh-CN" ] );
	//$( "#"+id ).datepicker(dateSettings);
	$( "#"+id ).datepicker();
	$( "#"+id ).attr("style","background:url('"+imagesPath+"/imagesCommon/frame/v1/calendar.gif') no-repeat scroll right center transparent;cursor:hand");
}

/**
 * 获得权限系统请求跨域链接json
 * @param url
 * @param params
 * @returns
 */
function getAuthonJsonp(url,params){
	if(!authorityPath){
		//alert("authorityPath");
	}
	return getJsonp(authorityPath,url,params);
}

/**
 * 获得请求跨域链接json数据
 * @param baseUrl  基础url
 * @param url  模块查询url
 * @param params 参数
 * @returns
 */
function getJsonp(baseUrl,url,params){
	var json = null;
	$.ajax({  
		url: baseUrl+"/"+url, 
		type: "GET",
		async: false,
		dataType: 'jsonp',
		jsonp:false,
		data: params,
		timeout: 5000, 
		success: function (data){
			alert(data);
		}, 
		error: function (xhr, ajaxOptions, thrownError){ 
			if(xhr.status == 200){
				json = $.parseJSON(xhr.responseText);
			}
		}
	});
	return json;
}


function getChkIds(){
	var ids = "";
	$(":checkbox[id='chkItem']").each(function(){
		if($(this).prop("checked")){
			ids+=$(this).val()+",";
		}
	});
	if(ids == ""){
		alert("请选择一条记录");
		return false;
	}else{
		return ids.substring(0, ids.length-1);
	}
}
// #######  弹出窗口处理 start #######


var openWinObj;
function openTopWin(title,url,scale_width,scale_height){
	scale_width = scale_width || 0.8;
	scale_height = scale_height || 0.8;
	var width = document.body.offsetWidth*scale_width;
	var height = document.body.offsetHeight*scale_height;
	openWinObj = $.dialog({
		id:'hmainWin',
		name:'hmainWin',
	    title: title,
	    lock: true,
		width: width,
		height: height,
		focus:true,
	    content: 'url:'+url
	});
}


/**
 * 打开普通弹出窗口
 * @param title
 * @param url
 */
function openWin(title,url,scale_width,scale_height){
	var topWin = getTopWin();
	topWin.openTopWin(title,url,scale_width,scale_height);
}

function openSelectTopWin(title,url,scale_width,scale_height){
	var width = document.body.offsetWidth*scale_width;
	var height = document.body.offsetHeight*scale_height;
	openWinObj = $.dialog({
		id:"hframeId",
		title: title,
		lock: true,
		width: width,
		height: height,
	    max: false,
	    min: false,
		content: 'url:'+url
	});
}

var OpenWinCls = function(){
	
}
var openWinClsIns = new OpenWinCls();

OpenWinCls.prototype.alert = function(msg,fun_alert){
	closeLoadTips();
	$.dialog({
		title:'警告',
		lock: true,
		parent:openWinObj,
		icon: 'alert.gif',
	    content: msg,
	    ok: function(){
	    	winFocus();
			if(typeof(fun_alert) == "function"){
				fun_alert();
			}
			return true;
		}
	});
}
OpenWinCls.prototype.alertText = function(msg,fun_alert){
	$.dialog({
		title:'警告',
		lock: true,
		parent:openWinObj,
		icon: 'alert.gif',
		content: '<textarea style="width:550px;height:300px">'+msg+'</textarea>',
		ok: function(){
			winFocus();
			if(typeof(fun_alert) == "function"){
				fun_alert();
			}
			return true;
		}
	});
}
OpenWinCls.prototype.confirm = function(msg,fun_confirm,fun_cancel){
	$.dialog({
		title:'确认',
		lock: true,
		parent:openWinObj,
		icon: 'confirm.gif',
		content: msg,
		ok: function(){
			if(typeof(fun_confirm) == "function"){
				fun_confirm();
			}
			return true;
		},
		cancel: function(){
			if(typeof(fun_cancel) == "function"){
				fun_cancel();
			}
			return true;
		}
	});
}
OpenWinCls.prototype.error = function(msg,ok_fun){
	$.dialog({
		title:'错误',
	    lock: true,
	    parent:openWinObj,
	    content: msg,
	    icon: 'error.gif',
	    ok: function () {
	    	winFocus();
	    	if(typeof(ok_fun) == "function"){
	    		ok_fun();
	    	}
	        return true;
	    }
	});
}

//弹出文本对话框
alertText = function(msg,fun){
	var topWin = getTopWin();
	if(msg){
		msg = msg.toString();
	}
	topWin.openWinClsIns.alertText(msg,fun);
}

//弹出错误对话框
alertError = function(msg,ok_fun){
	var topWin = getTopWin();
	if(msg){
		msg = msg.toString();
	}
	topWin.openWinClsIns.error(msg,ok_fun);
}

window.alert = function(msg,fun){
	//event.preventDefault();
	var topWin = getTopWin();
	if(msg){
		msg = msg.toString();
	}
	topWin.openWinClsIns.alert(msg,fun);
}
window.confirm = function(msg,fun_confirm){
	//event.preventDefault();
	var topWin = getTopWin();
	return topWin.openWinClsIns.confirm(msg,fun_confirm);
}

/**
 * 打开选择弹出窗口
 * @param title
 * @param url
 * @param scale_width
 * @param scale_height
 */
function openSelectWin(title,url,scale_width,scale_height){
	var topWin = getTopWin();
	topWin.openSelectTopWin(title,url,scale_width,scale_height);
}
/**
 * 打开通用选择弹出窗口
 * @param title
 * @param url
 * @param scale_width
 * @param scale_height
 */
function openCommonSelectWin(title,module,treeId,treeName){
	var url = appsPath+"/common/selectParent.htm?module="+module+"&treeId="+treeId+"&treeName="+treeName;
	openSelectWin(title,url,0.25,0.75);
}

/**
 * 关闭弹出窗口
 */
function closeWin(){
	var topWin = getTopWin();
	if(topWin.openWinObj){
		topWin.openWinObj.close();
	}
}
/**
 * 窗口获得焦点
 */
function winFocus(){
	var topWin = getTopWin();
	if(topWin.openWinObj){
		topWin.openWinObj.zindex();  //置顶
	}
}

var loadTipsObj = null;
var resultTipsObj = null;
function topLoadTips(msg){
	var content = msg || "数据处理中...";
	loadTipsObj = $.dialog({
		id:"loadTipsId",
		title:"提示",
		lock:true,
	    drag: false,
	    max: false,
	    min: false,
	    content: content
	});
}

//加载提示窗口
function showLoadTips(){
	var topWin = getTopWin();
	topWin.topLoadTips();
}
//自定义消息加载提示窗口
function showLoadTips(msg){
	var topWin = getTopWin();
	topWin.topLoadTips(msg);
}

function closeLoadTips(){
	var topWin = getTopWin();
	if(topWin.loadTipsObj){
		topWin.loadTipsObj.close();
	}
}


function topResultTips(){
	resultTipsObj = $.dialog({
		id:"resultTipsId",
		title:"提示",
		lock:true,
	    drag: false,
	    max: false,
	    min: false,
	    time:1,
	    content: "<font color='green'>操作成功</font>"
	});
}

function showResultTips(){
	closeLoadTips();
	var topWin = getTopWin();
	topWin.topResultTips();
}






// #######  弹出窗口处理 end #######


// #######  分页处理 start #######

//上一页
function prevPage(){
  var num = $("#pageNum").val();
  num = parseInt(num)-1;
  goPage(num);
}

//下一页
function nextPage(){
  var num = $("#pageNum").val();
  num = parseInt(num)+1;
  goPage(num);
}
//分页提交
function goPage(num){
	$("#pageNum").val(num);
	query();
}
//end


//other common

//获得提交表单数据
function getFormParams(){
	var paramsArray = $("form").serializeArray();
	var params = {};
	while(paramsArray.length > 0){
		var data = paramsArray.pop();
		var oldValue = params[data.name]
		if(typeof(oldValue) != "undefined"){  //存在数据
			if(typeof(oldValue) == "object"){
				oldValue.push(data.value); //add
			}else{
				var arr = new Array();
				arr.push(oldValue);
				arr.push(data.value);
				oldValue = arr;
			}
			params[data.name] = oldValue;
		}else{
			params[data.name] = data.value;
		}
		
	}
	return params;
}

//初始化select集合数据
function initSelListByUrl(url,id,key,value){
	$.post(url,function(dataList){
		initSelListByListData(dataList,id,key,value,false);
	},'json');
}
//初始化select集合数据
function initAllSelListByUrl(url,id,key,value){
	$.post(url,function(dataList){
		initSelListByListData(dataList,id,key,value,true);
	},'json');
}

/**
 * 
 * @param dataList 数据集
 * @param id  select id
 * @param key 数据项 key
 * @param value 数据项 value
 * @param isAll 是否初始化所有的select id
 */
function initSelListByListData(dataList,id,key,value,isAll){
	if(isAll){
		$(document).find("select[id="+id+"]").each(function(){
			initSingleSelListByListData(dataList,$(this),key,value);
		});
	}else{
		initSingleSelListByListData(dataList,$("#"+id),key,value);
	}
	
}
function initSingleSelListByListData(dataList,idObj,key,value){
	var dbId = idObj.attr("param-id");
	var strArr = new Array();
	for(var index in dataList){
		var data = dataList[index];
		strArr.push("<option value='");
		var keyVal = data[''+key+''];
		strArr.push(keyVal);
		strArr.push("'");
		if(dbId && dbId == keyVal){
			strArr.push("selected='selected'");
		}
		strArr.push(">");
		strArr.push(data[''+value+'']);
		strArr.push("</option>");
	}
	idObj.append(strArr.join(""));
}

function updateSingleSelListByListData(dataList,idObj,key,value){
	var dbId = idObj.attr("param-id");
	var strArr = new Array();
	strArr.push("<option value=''>请选择</option>");
	if(dataList.length>0){
		for(var index in dataList){
			var data = dataList[index];
			strArr.push("<option value='");
			var keyVal = data[''+key+''];
			strArr.push(keyVal);
			strArr.push("'");
			if(dbId && dbId == keyVal){
				strArr.push("selected='selected'");
			}
			strArr.push(">");
			strArr.push(data[''+value+'']);
			strArr.push("</option>");
		}
	}
	idObj.empty();
	idObj.append(strArr.join(""));
}

//String 字符替换[不能挑换带正则表达式字符，因为indexOf不支持]
String.prototype.replaceAll = function(stringToFind, stringToReplace) {
	var temp = this;
	var index = temp.indexOf(stringToFind);
	while (index != -1) {
		temp = temp.replace(stringToFind, stringToReplace);
		index = temp.indexOf(stringToFind);
	}
	return temp;
}

