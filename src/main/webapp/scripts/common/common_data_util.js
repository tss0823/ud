/**
 * 线性列表工具类
 */
var List = function(){
	this.item = new Array();
}

//添加元素
List.prototype.add = function(e){
	this.item.push(e);
}

//删除元素
List.prototype.remove = function(e){
	for(var i = 0; i < this.size(); i++){
		var data = this.item[i];
		if(data == e){
			this.item.splice(i,1);
			break;
		}
	}
}

//清空所有
List.prototype.clear = function(){
	while(this.size() > 0){
		this.item.pop();
	}
}

//大小
List.prototype.size = function(){
	return this.item.length;
}

//获得元素
List.prototype.get = function(index){
	return this.item[i];
}

//是否包含元素
List.prototype.contains = function(e){
	for(var i = 0; i < this.size(); i++){
		var data = this.item[i];
		if(data == e){
			return true;
		}
	}
}

/**
 * Map容器工具
 */
var Map = function(){
	this.item = new Array();
	this.Entry = function(k,v){
		this.key = k;
		this.value = v;
	}
}

//添加元素
Map.prototype.put = function(k,v){
	if(k == null){
		return v;
	}
	for(var i = 0; i < this.item.length; i++){
		var data = this.item[i];
		if(data.key = k){
			data.value = v;
			return v;
		}
	}
	var entry = new this.Entry(k, v);
	this.item.push(entry);
	return v;
}

//获得元素
Map.prototype.get = function(k){
	if(k == null){
		return null;
	}
	for(var i = 0; i < this.item.length; i++){
		var data = this.item[i];
		if(data.key = k){
			return data.value;
		}
	}
	return null;
}
//获得元素
Map.prototype.remove = function(k){
	if(k == null){
		return null;
	}
	for(var i = 0; i < this.item.length; i++){
		var data = this.item[i];
		if(data.key = k){
			var value = data.value;
			//移出
			this.item.splice(i,1);
			return value;
		}
	}
	return null;
}

