var FormWizard = function () {


    return {
        //main function to initiate the module
        init: function () {
            if (!jQuery().bootstrapWizard) {
                return;
            }

            function format(state) {
                if (!state.id) return state.text; // optgroup
                return "<img class='flag' src='assets/img/flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
            }

            $("#country_list").select2({
                placeholder: "Select",
                allowClear: true,
                formatResult: format,
                formatSelection: format,
                escapeMarkup: function (m) {
                    return m;
                }
            });

            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);

            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                	/**
                    //联系信息
                	loginId: {
                        required: true
                    },
                	connectionName: {
                        required: true
                    },
                    connectionPhone: {
                        required: true
                    },
                    connectionEmail: {
                        required: true
                    },
                    //经营信息
                    managementProjects: {
                        required: true
                    },
                    managementProjects1: {
                        required: true
                    },
                    managementProjects2: {
                        required: true
                    },
                    shortName: {
                        required: true
                    },
                    serviceTel: {
                        required: true
                    },
                    shopName:{
                    	required : true
                    },
                    //基本信息
                    merchantName: {
                        required: true
                    },
                    registeredAddress: {
                        required: true
                    },
                    //营业执照
                    bussinessLicenseNum: {
                        required: true
                    },
                	bussinessScope: {
                        required: true
                    },
                    bussinessStartTime: {
                        required: true
                    },
                    bussinessEndTime: {
                        required: true
                    },
                    //组织机构信息
                    organizationCode: {
                        required: true
                    },
                    organizationStartDate: {
                        required: true
                    },
                    organizationEndDate: {
                        required: true
                    },
                    //企业法人
                    certificateHolderName: {
                        required: true
                    },
                    idCardNum: {
                        required: true
                    },
                    idCardStartDate: {
                        required: true
                    },
                    idCardEndDate: {
                        required: true
                    },
                    
                    //结算账号
                    companyName: {
                        required: true
                    },
                    companyAddress: {
                        required: true
                    },
                    bankCode: {
                        required: true
                    },
                    bankName: {
                        required: true
                    },
                    bankAddress: {
                        required: true
                    },
                    bankAccount: {
                        required: true
                    },
                    'payment[]': {
                        required: true,
                        minlength: 1
                    }**/
                },

                messages: { // custom messages for radio buttons and checkboxes
                    'payment[]': {
                        required: "Please select at least one option",
                        minlength: jQuery.format("Please select at least one option")
                    },
                    connectionName:{
                		required: " "
                	}
                	
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.attr("name") == "connectionName") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_gender_error");
                    } else if (element.attr("name") == "connectionPhone") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_payment_error");
                    } else {
                        //error.insertAfter(element); // for other inputs, just perform default behavoir
                    	error.addClass("no-left-padding").insertAfter("#form_payment_error");
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success.hide();
                    error.show();
                    App.scrollTo(error, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                    $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change dony by hightlight
                    $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
                },

                success: function (label) {
                    if (label.attr("for") == "gender" || label.attr("for") == "payment[]") { // for checkboxes and radip buttons, no need to show OK icon
                        label
                            .closest('.control-group').removeClass('error').addClass('success');
                        label.remove(); // remove error label here
                    } else { // display success icon for other inputs
                        label
                            .addClass('valid ok') // mark the current input as valid and display OK icon
                        .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
                    }
                },

                submitHandler: function (form) {
                    success.show();
                    error.hide();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });
            $('.chosen, .chosen-with-diselect', form).change(function () {
                form.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
            });

            var displayConfirm = function() {
                $('.display-value', form).each(function(){
                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                    if (input.is(":text") || input.is("textarea")) {
                        $(this).html(input.val());
                    } else if (input.is("select")) {
                        $(this).html(input.find('option:selected').text());
                    } else if (input.is(":radio") && input.is(":checked")) {
                        $(this).html(input.attr("data-title"));
                    } else if ($(this).attr("data-display") == 'card_expiry') {
                        $(this).html($('[name="card_expiry_mm"]', form).val() + '/' + $('[name="card_expiry_yyyy"]', form).val());
                    } else if ($(this).attr("data-display") == 'payment') {
                        var payment = [];
                        $('[name="payment[]"]').each(function(){
                            payment.push($(this).attr('data-title'));
                        });
                        $(this).html(payment.join("<br>"));
                    }
                });
            }

            // default form wizard
            $('#form_wizard_1').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index) {
//                    alert('on tab click disabled');
//                    return false;
                   if(index==0){
                	   if($("#connectionName").val()=='' || $("#connectionPhone").val()=='' || $("#connectionEmail").val()==''){
                	   }
                	   if($("#shortName").val()=='' || $("#managementProjects").val()=='' || $("#managementProjects1").val()==''
                		   || $("#managementProjects2").val()=='' || $("#serviceTel").val()==''){
                	   }
                   }else if(index==1){
                	   
                   }else if(index==2){
                	   
                   }else if(index==3){
                	   
                   }
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();
                    if (form.valid() == false) {
                        return false;
                    }

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                        displayConfirm();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }
                    App.scrollTo($('.page-title'));
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }

                    App.scrollTo($('.page-title'));
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_1').find('.bar').css({
                        width: $percent + '%'
                    });
                    success.hide();
                    error.hide();
                    if (form.valid() == false) {
                        return false;
                    }
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                        displayConfirm();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }
                    App.scrollTo($('.page-title'));
                }
            });

            $('#form_wizard_1').find('.button-previous').hide();
            $('#form_wizard_1 .button-submit').click(function () {
            	$.ajax({
    				type : "post",
    				aync : false,
    				data : {
    					"merchantId" : $("#merchantId").val(),
    					//"loginId" :$("#loginId").val(),
    					"connectionName" : $("#connectionName").val(),
    					"connectionPhone" : $("#connectionPhone").val(),
    					"connectionEmail" : $("#connectionEmail").val(),
    					"shortName" : $("#shortName").val(),
    					"managementProjects" : $("#managementProjects").val(),
    					"managementProjectsPics" : $("#managementProjectsPics").val(),
    					//"managementProjects1" : $("#managementProjects1").val(),
    					//"managementProjects2" : $("#managementProjects2").val(),
    					"serviceTel" : $("#serviceTel").val(),
    					"website" : $("#website").val(),
    					"manageFiles" : $("#manageFiles").val(),
    					"logoFiles" : $("#logoFiles").val(),
    					
    					"businessLicenseNum" : $("#businessLicenseNum").val(),
    					"businessScope" : $("#businessScope").val(),
    					"businessStartTime" : $("#businessStartTime").val(),
    					"businessEndTime" : $("#businessEndTime").val(),
    					"businessPics" : $("#businessPics").val(),
    					"businessLicenseProvince":$("#businessLicenseProvince").val(),
    					"businessLicenseCity":$("#businessLicenseCity").val(),
    					//"organizationCode" : $("#organizationCode").val(),
    					//"organizationStartDate" : $("#organizationStartDate").val(),
    					//"organizationEndDate" : $("#organizationEndDate").val(),
    					//"organizationPics" : $("#organizationPics").val(),
    					
    					"taxNo" : $("#taxNo").val(),
    					
    					"certificateHolderType" : $("#certificateHolderType").val(),
    					"certificateHolderName" : $("#certificateHolderName").val(),
    					"certificateType" : $("#certificateType").val(),
    					"idCardStartDate" : $("#idCardStartDate").val(),
    					"idCardEndDate" : $("#idCardEndDate").val(),
    					"idCardNum" : $("#idCardNum").val(),
    					//"idCardObversePics" : $("#idCardObversePics").val(),
    					//"idCardReversePics" : $("#idCardReversePics").val(),
    					"idCardPics" : $("#idCardPics").val(),
    					
    					"accountType" : $("#accountType").val(),
    					"companyName" : $("#companyName").val(),
    					"companyAddress" : $("#companyAddress").val(),
    					"bankCode" : $("#bankCode").val(),
    					"bankName" : $("#bankName").val(),
    					"bankAddress" : $("#bankAddress").val(),
    					"bankAccount" : $("#bankAccount").val(),
    					"bankAccountName" :$("#bankAccountName").val(),
    					"personalBankAccountMobile" : $("#personalBankAccountMobile").val(),
    					"personalBankHolderName" : $("#personalBankHolderName").val(),
    					"personalBankHolderCertNo" : $("#personalBankHolderCertNo").val(),
    					
    					"storeName" :$("#storeName").val(),
    					"storePics" :$("#storePics").val(),
    					"storeContactName" :$("#storeContactName").val(),
    					"storeContactMobile" :$("#storeContactMobile").val(),
    					"storeContactPhone" :$("#storeContactPhone").val(),
    					"storeAddress" :$("#storeAddress").val(),
    					"dayTradeAmount" :$("#dayTradeAmount").val()
    				},
    				url : basePathUrl+"/merchant/save.json",
    				success : function(data) {
    					//var dataObj=eval("("+data+")");// 转换为json对象
    					if(data=='success'){
    						alert("提交成功");
    						location.href=basePathUrl+"/merchant/detail.htm";
    					}else{
    						alert("提交失败");
    					}
    					
    				}
    			});
            }).hide();
        }

    };

}();