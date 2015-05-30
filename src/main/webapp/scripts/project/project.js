$(document).ready(function(){
	//设置module
	moduleInst.setName("project");
	
	//校验
	if(typeof($.Validator) != 'undefined'){
		vSettings = {
				rules:{
					name:{
						required:true,
						maxLength:20
					},
					cnName:{
						required:true,
						maxLength:25
					}
				},
				messages:{
					name:{
						required:"'名称'不能为空",
						maxLength:"'名称'字符长度不能超过20个"
					}
				}
			};
	}
	
})