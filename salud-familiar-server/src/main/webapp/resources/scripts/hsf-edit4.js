var FormEdit4HSF = function () {
	  
    var handleSelect2 = function () {
    	$("#tamFamilia").select2({
        });
        $("#ontogenesis").select2({
        });
        $("#etapaCicloVital").select2({
        });
        $("#usoMedTradicional").select2({
        });
    };
    
    var handleMultiSelect = function () {
        $('#crisisNormativa').multiSelect();
        $('#crisisParanormativa').multiSelect();
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {            
            var pageContent = $('.page-content');
            handleSelect2();
            handleMultiSelect();
            
            var form = $('#edit-hsf-form');
            var error = $('.alert-danger', form);
            
            jQuery.validator.addMethod("noNingunoyOtro", function(value, select) { 
            	var isValid = true;
            	var number = $('option:selected', select).size();
                if (number > 1 && select.options[select.options.length-1].selected) {
                	isValid = false;
                }
                return isValid;
	      	}, "Invalido");
                       
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                	tamFamilia: {
                    	required: true
                    },
                    ontogenesis: {
                    	required: true
                    },
                    etapaCicloVital: {
                    	required: true
                    },
                    crisisNormativa: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    crisisParanormativa: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    usoMedTradicional: {
                    	required: true
                    }
                },
                
                invalidHandler: function (event, validator) { //display error alert on form submit   
                    error.show();
                    App.scrollTo(error, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    if (label.attr("for") == "gender" || label.attr("for") == "payment[]") { // for checkboxes and radio buttons, no need to show OK icon
                        label
                            .closest('.form-group').removeClass('has-error');
                        label.remove(); // remove error label here
                    } else { // display success icon for other inputs
                        label
                        .closest('.form-group').removeClass('has-error'); 
                    }
                },

                submitHandler: function (form) {
                    error.hide();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                    guardarFuncFam();
                }
            });
            
            
            function guardarFuncFam()
        	{
            	App.blockUI(pageContent, false);
            	$.post( parametros.addFuncFamUrl
    		            , $('#edit-hsf-form').serialize()
    		            , function( data )
    		            {
		            		if (data == ""){
		    					toastr.error(parametros.deniedError);
		    				}
		    				else{
	            				funcionamiento = JSON.parse(data);
	            				$('#idFuncFamiliar').val(funcionamiento.idFuncFamiliar);
	    						toastr.success(parametros.processSuccess);
		    				}
    						App.unblockUI(pageContent);
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			  			alert( parametros.processError + " : " + errorThrown);
    			  			App.unblockUI(pageContent);
    			  		});
        	}
            
            
            $(document).on('keypress','form input',function(event){                
    		    event.stopImmediatePropagation();
    		    if( event.which == 13 )
    		    {
    		        event.preventDefault();
    		        var $input = $('form input');
    		        if( $(this).is( $input.last() ) )
    		        {
    		            //Time to submit the form!!!!
    		        }
    		        else
    		        {
    		            $input.eq( $input.index( this ) + 1 ).focus();
    		        }
    		    }
    		});
        }
    };
}();