var FormWizardHSFModalPersonaValidation = function () {
	
	var handleInputMasks = function () {
        $.extend($.inputmask.defaults, {
            'autounmask': true
        });

        $("#numPersona").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
    };
	
	return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	handleInputMasks();
        	var personForm = $('#add_person_form');
    	    var errorPersonForm = $('.alert-danger', personForm);
    	    var successPersonForm = $('.alert-success', personForm);
    	    $('#numPersona').val(parseInt($('#noPersonasFamilia').val()) + 1);
    	    
    	    var validatorPerson = personForm.validate({
    	        doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
    	        errorElement: 'span', //default input error message container
    	        errorClass: 'help-block', // default input error message class
    	        focusInvalid: false, // do not focus the last invalid input
    	        rules: {
    	            //Datos generales
    	        	idFamilia: {
     	                required: true
     	            },
    	            numPersona: {
    	                required: true,
    	                min : 1,
    	                max: 20
    	            },
    	            nombres: {
    	                required: true
    	            },
    	            primerApellido: {
    	                required: true
    	            }
    	        },
    	
    	        invalidHandler: function (event, validator) { //display error alert on form submit   
    	            successPersonForm.hide();
    	            errorPersonForm.show();
    	            $('#nombres').focus();
    	            
    	        },
    	
    	        highlight: function (element) { // hightlight error inputs
    	            $(element)
    	                .closest('.form-group').addClass('has-error'); // set error class to the control group
    	        },
    	
    	        unhighlight: function (element) { // revert the change done by hightlight
    	            $(element)
    	                .closest('.form-group').removeClass('has-error'); // set error class to the control group
    	        },
    	
    	        success: function (label) {
    	                label
    	                .closest('.form-group').removeClass('has-error'); // set success class to the control group
    	        },
    	
    	    });
    	    
    	    $('#save-person').click(function() {
    	    	var IsValid = true;

    	    	// Validate Each Bootstrap tab
                $(".persona").find("div.tab-pane").each(function (index, tab) {
                    var id = $(tab).attr("id");
                    $('a[href="#' + id + '"]').tab('show');

                    var IsTabValid = personForm.valid();

                    if (!IsTabValid) {
                        IsValid = false;
                        return false; // Break each loop
                    }
                });
            	if (IsValid) {
            		$('#idFamiliaPerson').val($('#idFamilia').val());
        	    	guardarPersona();
                }
    	    });
    	    
    	    $('#save-person-add').click(function() {
    	    	// Validate Each Bootstrap tab
                $(".persona").find("div.tab-pane").each(function (index, tab) {
                    var id = $(tab).attr("id");
                    $('a[href="#' + id + '"]').tab('show');

                    var IsTabValid = personForm.valid();

                    if (!IsTabValid) {
                        IsValid = false;
                        return false; // Break each loop
                    }
                });
    	    	$('#idFamiliaPerson').val($('#idFamilia').val());
    	    	guardarPersonaAgregar();
    	    });
    	    
    	    $('#dismiss-modalperson').click(function() {
    	    	successPersonForm.hide();
	            errorPersonForm.hide();
    	    	validatorPerson.elements().closest('.form-group').removeClass('has-error');
    	    	validatorPerson.resetForm();
    	    });
    	    
    	    function guardarPersona(){
		    if (personForm.valid()){
				$.post( parametros.addPersonUrl
			            , $('#add_person_form').serialize()
			            , function( data )
			            {
							persona = JSON.parse(data);
							if ($('#idPersona').val()==''){
								$('table#lista_personas').dataTable().fnAddData( [
								  persona.numPersona, persona.nombres, persona.primerApellido, persona.segundoApellido, persona.cedula, persona.fechaNacimiento] );
							}
							$('#idPersona').val(persona.idPersona);
							$('#noPersonasFamilia').val($('#numPersona').val());
			        		validatorPerson.resetForm();
			            }
			            , 'text' )
				  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				  			alert( parametros.processError + " : " + errorThrown);
				  		});
			 }
		   }
    	    
		   function guardarPersonaAgregar(){
	    	    if (personForm.valid()){
	    			$.post( parametros.addPersonUrl
	    		            , $('#add_person_form').serialize()
	    		            , function( data )
	    		            {
	    						persona = JSON.parse(data);
	    						if ($('#idPersona').val()==''){
	    						$('table#lista_personas').dataTable().fnAddData( [
	    							persona.numPersona, persona.nombres, persona.primerApellido, persona.segundoApellido ] );
	    						}
	    						$('#noPersonasFamilia').val($('#numPersona').val());
	    						$('#add_person_form').trigger("reset");
	    						$('#numPersona').val(parseInt($('#noPersonasFamilia').val()) + 1);
	    		        		$('#nombres').focus();
	    		        		validatorPerson.resetForm();
	    		            }
	    		            , 'text' )
	    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
	    			  			alert( parametros.processError + " : " + errorThrown);
	    			  		});
	    		 }
	    	}
        }
	
	};
	
}();