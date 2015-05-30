$(document).ready(function(){
	//设置module
	moduleInst.setName("validRule");
	
	//校验
	if(typeof($.Validator) != 'undefined'){
		vSettings = {
				rules:{
					enName:{
						required:true,
						maxLength:20
					},
					cnName:{
						required:true,
						maxLength:25
					}
				},
				messages:{
					enName:{
						required:"'名称'不能为空",
						maxLength:"'名称'字符长度不能超过20个"
					}
				}
			};
	}
	
})

//查询
function query(){
	$("#queryForm",document).submit();
}