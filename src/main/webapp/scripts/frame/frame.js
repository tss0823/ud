var menuData;
var previousCode = null; 
var previousMenuItem = null; 
$(document).ready(function(){
	
	//默认选中
	$(".topMiddleMenu").find("li[param-code='system']").trigger("click");
	
	
	$("#mainMenu h2").live("click",function(i){
		var st = $(this).attr("st");
		if(st == "close"){
			$(this).attr("st","open");
			$(this).attr("class","secondMenuUp");
		}else{
			$(this).attr("st","close");
			$(this).attr("class","secondMenuDown");
		}
		//子项处理
		var data = $(this).attr("data");
		$("#mainMenu").find("ul").each(function(j){
			var parentData = $(this).attr("parent-data");
			if(data == parentData){
				if(st == "close"){
					$(this).show(200);
				}else{
					$(this).hide();
				}
			}
		});
	});
	
	
	$("#mainMenu > ul > li").live("click",function(i){
		if(previousMenuItem != null){
			$(previousMenuItem).removeClass("select");
		}
		$(this).addClass("select");
		previousMenuItem = this;
		
	});
	
	
//	$(document).find("#firstMenu").each(function(){
//		alert($(this).innerText);
//	});
	


});

function getBackgroundImage(code,isClick){
	var fileName = code;
	if(isClick){
		fileName = code+"_p";
	}
	var imageUrl = "url('"+imagesPath+"/imagesCommon/frame/v1/menu_first_"+fileName+".png')";
	return imageUrl;
}

function setLeftMenu(obj,parentId){
	//设置样式改变
	if(previousCode != null){
		var prevousObj = $(".topMiddleMenu").find("li[param-code='"+previousCode+"']");
		prevousObj.find("div").css("background-image",getBackgroundImage(previousCode,false))
	}
	var code = $(obj).attr("param-code");
	$(obj).find("div").css("background-image",getBackgroundImage(code,true));
	previousCode = code;
	
	var strArr = new Array();
	var firstList = menuData.childList;
	for(var index in firstList){  //一级
		var data = firstList[index];
		if(data.id != parentId){
			continue;
		}
		var secondList = data.childList;
		for(var seIndex in secondList){  //二级
			var seData = secondList[seIndex];
			strArr.push("<h2 id='secondMenu' class='secondMenuDown' st='close' data='");
			strArr.push(seData.id);
			strArr.push("'>");
			strArr.push(seData.name);
			strArr.push("</h2>\n");
			
			strArr.push("<ul id='menu' parent-data='");
			strArr.push(seData.id);
			strArr.push("' ");
			strArr.push("style='display:none'>");
			strArr.push("\n");
			
			var thirdList = seData.childList;
			for(var thirdIndex in thirdList){  //三级
				var thirdData = thirdList[thirdIndex];
				strArr.push("<li id='leafMenu'><a href='");
				
				strArr.push(thirdData.url);
				strArr.push("' ");
				strArr.push("target='frameRight'>");
				strArr.push(thirdData.name);
				strArr.push("</a>");
				strArr.push("</li>");
				strArr.push("\n");
			}
			strArr.push("</ul>");
			strArr.push("\n");
		}
		var mainMenuObj = $(parent["frameLeft"].document).find("#mainMenu");
		mainMenuObj.html(strArr.join(""));
		
	}
}