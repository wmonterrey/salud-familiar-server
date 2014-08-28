var FormWizardHSFModalPersonaValidation = function () {
	
	
	return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	var personForm = $('#add_person_form');
    	    var errorPersonForm = $('.alert-danger', personForm);
    	    var successPersonForm = $('.alert-success', personForm);
    	    
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
    	                required: true
    	            }
    	        },
    	
    	        invalidHandler: function (event, validator) { //display error alert on form submit   
    	            successPersonForm.hide();
    	            errorPersonForm.show();
    	            App.scrollTo(errorPersonForm, -200);
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
    	    
    	    $('#dismiss-modalperson').click(function() {
    	    	validatorPerson.elements().closest('.form-group').removeClass('has-error');
    	    	validatorPerson.resetForm();
    	    });
    	    
    	    if (personForm.valid()){
    			$.post( parametros.addPersonUrl
    		            , $('#add_person_form').serialize()
    		            , function( data )
    		            {
    						persona = JSON.parse(data);
    						$('table#lista_personas').dataTable().fnAddData( [
    			                                                       persona.idPersona,
    			                                                       persona.nombres ] );
    						$('#noPersonasFamilia').val($("table#lista_personas > tbody > tr").length);
    						$('#numPersona').val('');
    	            		$('#nombres').val('');
    	            		$('#primerApellido').val('');
    	            		$('#segundoApellido').val('');
    	            		$('#numPersona').focus();
    	            		validatorPerson.resetForm();
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			    		alert( "error:" + errorThrown);
    			  		});
    		 }
        }
	
	};
	
}();