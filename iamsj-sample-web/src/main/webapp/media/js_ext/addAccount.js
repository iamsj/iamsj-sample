var FormValidation = function() {
	return {
		// main function to initiate the module
		init : function() {
			
			var form2 = $("#form");
			var error2 = $('.alert-error', form2);
			form2.validate({
						errorElement : 'span', 
						errorClass : 'help-inline', 
						focusInvalid : false,  
						ignore : "",
						rules : {
							loginName : {
								minlength : 4,
								required : true
							},
							password : {
								required : true,
							},
							repassword : {
								required : true,
								equalTo: "#password"
							},
							name : {
								required : true
							},
							mobile : {
								required : true
							},
							roleId : {
								required : true
							},
							status : {
								required : true
							},
							unitId : {
								required : true,
							}
						},
						messages : {  
							loginName : {
								required : "请输入用户名"
							},
							password : {
								required : "请输入密码"
							},
							repassword : {
								required : "请输入确认密码",
								equalTo: "两次密码输入不一致"
							},
							name : {
								required : "请输入姓名"
							},
							mobile : {
								required : "请输入手机号"
							},
							roleId : {
								required : "请选择角色"
							},
							status : {
								required : "请选择状态"
							},
							unitId : {
								required : "请选择单位",
							}
						},
						submitHandler : function(form) {
							error2.hide();
							form.submit();
						}

					});

		}
	}
}();