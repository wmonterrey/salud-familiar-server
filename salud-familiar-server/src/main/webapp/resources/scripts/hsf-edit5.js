var FormEdit5HSF = function () {
	
	var handleDatePickers = function (idioma) {
        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                language: idioma,
                format:'dd/mm/yyyy',
                autoclose: true
            });
            $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
        }
    };
    
    var handleInputMasks = function () {
        $.extend($.inputmask.defaults, {
            'autounmask': true
        });

        $("#fechaVisita").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        }); //multi-char placeholder
    };
   
    var handleSelect2 = function () {
    	$("#personaVisitaProfesion").select2({
        });
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {            
            var pageContent = $('.page-content');
            handleDatePickers(parametros.language);
            handleInputMasks();
            handleSelect2();
            var form = $('#edit-hsf-form');
            var error = $('.alert-danger', form);
                       
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //Datos generales
                	personaVisita: {
                    	minlength:10,
                        required: true
                    },
                    personaVisitaProfesion: {
                        required: true
                    },
                    fechaVisita: {
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
                    guardarVisita();
                }
            });
            
            
            function guardarVisita()
        	{
            	App.blockUI(pageContent, false);
            	$.post( parametros.editVisitaUrl
    		            , $('#edit-hsf-form').serialize()
    		            , function( data )
    		            {
		            		if (data == ""){
		    					toastr.error(parametros.deniedError);
		    				}
		    				else{
		    					visita = JSON.parse(data);
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