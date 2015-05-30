var menuData;
$(document).ready(function(){
	//获得菜单资源
	$.get('commonAction.htm?method=getRootMenuTree',function(data, textStatus){
		menuData = data;
		//top menu
		var childList = data.childList;
		for(var index in childList){
			var strArr = new Array();
			var child = childList[index];
			strArr.push("<li onclick=setLeftMenu(");
			strArr.push("'");
			strArr.push(child.id);
			strArr.push("') >");
			strArr.push("<div class='");
			strArr.push(child.code);
		    strArr.push("'><span>");
		    strArr.push(child.name);
		    strArr.push("</span></div></li>");
			strArr.push("\n");
			var htmlStr = strArr.join("");
			$(".topMiddleMenu").append(htmlStr);
			
		}
	},'json');
	
	
	$("#left h2").live("click",function(i){
		var st = $(this).attr("st");
		if(st == "close"){
			$(this).attr("st","open");
		}else{
			$(this).attr("st","close");
		}
		//子项处理
		var data = $(this).attr("data");
		$("#left").find("ul").each(function(j){
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
//	$(document).find("#firstMenu").each(function(){
//		alert($(this).innerText);
//	});
});

function setLeftMenu(parentId){
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
			strArr.push("<h2 id='firstMenu' st='close' data='");
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
				strArr.push("<li><a href='");
				
				strArr.push(thirdData.url);
				strArr.push("' ");
				strArr.push("target='frmMain'>");
				strArr.push(thirdData.name);
				strArr.push("</a>");
				strArr.push("</li>");
				strArr.push("\n");
			}
			strArr.push("</ul>");
			strArr.push("\n");
		}
		$("#left").html(strArr.join(""));
		
	}
}