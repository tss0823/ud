var menuData;
var previousCode = null; 
var previousMenuItem = null; 
$(document).ready(function(){
	//获得菜单资源
	$.get('commonAction.htm?method=getRootMenuTree',function(data, textStatus){
		menuData = data;
		//top menu
		var childList = data.childList;
		
		for(var index in childList){
			var strArr = new Array();
			var child = childList[index];
			strArr.push("<li param-code='"+child.code+"' onclick=setLeftMenu(this,");
			strArr.push("'");
			strArr.push(child.id);
			strArr.push("') >");
			var imageUrl = getBackgroundImage(child.code, false);
			strArr.push("<div style=\"width:120px\">")
			strArr.push("<div class='bg_img' style=\"width:106px;\">")
//			strArr.push(" onmouseover=\"setBgImgBackground(this)\" onmouseout=\"delBgImgBackground(this)\" >")
			strArr.push("<div style=\"width:106px;border:0px solid #fff;text-align:center;background-image:");
			strArr.push(imageUrl);
			strArr.push("\" >");
		    strArr.push("<span style=\"border:0px solid #C62828;width:106px;\">");
		    strArr.push(child.name);
		    strArr.push("</span></div></div></div></li>");
			strArr.push("\n");
			var htmlStr = strArr.join("");
			$(".topMiddleMenu").append(htmlStr);
		}
		//默认选中
		$(".topMiddleMenu").find("li[param-code='sy']").trigger("click");
	},'json');

	
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
	/*if(isClick){
		fileName = code+"_p";
	}*/
	//var imageUrl = "url('"+imagesPath+"/imagesCommon/frame/v1/menu_first_"+fileName+".png')";
	var imageUrl = "url('"+imagesPath+"/imagesCommon/frame/v1/menu_first_"+fileName+".png')";
	//var imageUrl = "url('"+imagesPath+"/imagesCommon/frame/v1/bg_top_menu.png')";
	return imageUrl;
}

function getBgBackgroundImage(){
	return "url('"+imagesPath+"/imagesCommon/frame/v1/bg_top_menu.png')";
}

//点击top一级菜单，显示左侧菜单
function setLeftMenu(obj,parentId){
	//改变top样式选中
	var code = $(obj).attr("param-code");
	$(".bg_img").css("background-image","");
	$(obj).find(".bg_img").css("background-image",getBgBackgroundImage());
	
	if(code == 'host'){   //如果是首页，直接展示页面
		var firstList = menuData.childList;
		for(var index in firstList){  //一级
			var data = firstList[index];
			if(data.id != parentId){
				continue;
			}
			var win = topWin.frames[0].frames["frameRight"].window;
			win.location = data.url;
			break;
		}
		return;
	}
	//conitnue
	
	//改变left样式
	if(previousCode != null){
		var prevousObj = $(".topMiddleMenu").find("li[param-code='"+previousCode+"']");
		prevousObj.find("div").css("background-image",getBackgroundImage(previousCode,false))
	}

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