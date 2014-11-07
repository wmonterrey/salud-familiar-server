var FormEdit3HSF = function () {
	  
    var handleSelect2 = function () {
    	$("#tipoPiso").select2({
        });
        $("#tipoTecho").select2({
        });
        $("#tipoPared").select2({
        });
        $("#culturaSanitaria").select2({
        });
        $("#carPsicosociales").select2({
        });
        $("#satNecBasicas").select2({
        });
        $("#tenenciaVivienda").select2({
        });
    };
    
    var handleMultiSelect = function () {
    	$('#accionesComunitarias').multiSelect();
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
                	tipoPiso: {
                    	required: true
                    },
                    tipoTecho: {
                    	required: true
                    },
                    tipoPared: {
                    	required: true
                    },
                    culturaSanitaria: {
                    	required: true
                    },
                    carPsicosociales: {
                    	required: true
                    },
                    satNecBasicas: {
                    	required: true
                    },
                    tenenciaVivienda: {
                    	required: true
                    },
                    accionesComunitarias: {
                    	required: true,
                    	noNingunoyOtro:true
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
                    guardarFactSocEc();
                }
            });
            
            
            function guardarFactSocEc()
        	{
            	App.blockUI(pageContent, false);
            	$.post( parametros.addFactSocEcUrl
    		            , $('#edit-hsf-form').serialize()
    		            , function( data )
    		            {
		            		if (data == ""){
		    					toastr.error(parametros.deniedError);
		    				}
		    				else{
	    						factores = JSON.parse(data);
	    						$('#idFactSocioEc').val(factores.idFactSocioEc);
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