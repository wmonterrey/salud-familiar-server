var FormWizardHSFModalEnfValidation = function () {
	
	return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	var enfForm = $('#add_enfermedad_form');
        	var pageContent = $('.page-content');
    	    
    	    var validatorEnf = enfForm.validate({
    	        doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
    	        errorElement: 'span', //default input error message container
    	        errorClass: 'help-block', // default input error message class
    	        focusInvalid: false, // do not focus the last invalid input
    	        rules: {
    	        	enfermedad: {
     	                required: true
     	            },
     	            fechaOcurrencia: {
    	                required: true
    	            },
    	            personaAtendio: {
    	                required: true
    	            },
    	        },
    	
    	        invalidHandler: function (event, validator) { //display error alert on form submit   
    	        	$('#add_enfermedad_form .alert-danger').show();
                	$('#add_enfermedad_form .alert-success').hide();
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
    	                $('#add_enfermedad_form .alert-danger').hide();
                    	$('#add_enfermedad_form .alert-success').hide();
    	        },
    	
    	    });
    	    
    	    $('#save-enf').click(function() {
            	if (enfForm.valid()){
            		App.blockUI(pageContent, false);
            		$('#idPersonaEnf').val($('#idPersona').val());
            		guardarEnfermedad();
            		App.unblockUI(pageContent);
            	}
    	    });
    	    
    	    $('#save-enf-add').click(function() {
            	if (enfForm.valid()){
            		App.blockUI(pageContent, false);
            		$('#idPersonaEnf').val($('#idPersona').val());
            		guardarEnfermedadAgregar();
		            $('#enfermedad').focus();
		            App.unblockUI(pageContent);
            	}
    	    });
    	    
    	    $('#dismiss-modalenf').click(function() {
    	    	$('#enfermedad').select2('data', null);
	            validatorEnf.elements().closest('.form-group').removeClass('has-error');
	            validatorEnf.resetForm();
    	    });
    	    
    	    
    	    function guardarEnfermedad(){
				$.post( parametros.addEnfermedadUrl
		            , $('#add_enfermedad_form').serialize()
		            , function( data )
		            {
						if (data == ""){
	    					toastr.error(parametros.deniedError);
	    				}
	    				else{
							enfermedad = JSON.parse(data);
							if ($('#idEnfermedad').val()==''){
								var d = new Date(Date.parse(enfermedad.fechaOcurrencia));
								$('table#lista_enfermedades').dataTable().fnAddData( [
								  enfermedad.enfermedad.nombreCie10, d.yyyymmdd(), enfermedad.personaAtendio.valor,
								  '']);
							}
							$('#idEnfermedad').val(enfermedad.idEnfermedad);
							validatorEnf.resetForm();
	    				}
		            }
		            , 'text' )
			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
			  			alert( parametros.processError + " : " + errorThrown);
			  		});
    	    }
    	    
    	    function guardarEnfermedadAgregar(){
				$.post( parametros.addEnfermedadUrl
		            , $('#add_enfermedad_form').serialize()
		            , function( data )
		            {
						if (data == ""){
	    					toastr.error(parametros.deniedError);
	    				}
	    				else{
							enfermedad = JSON.parse(data);
							if ($('#idEnfermedad').val()==''){
								var d = new Date(Date.parse(enfermedad.fechaOcurrencia));
								$('table#lista_enfermedades').dataTable().fnAddData( [
								  enfermedad.enfermedad.nombreCie10, d.yyyymmdd(), enfermedad.personaAtendio.valor,
								  '']);
							}
			    	    	$('#add_enfermedad_form').find('input:text, input:password, textarea').val('');
			                $('#add_enfermedad_form').find('input:radio, input:checkbox').prop('checked', false);
			                $('#add_enfermedad_form').find('select').select2('val','');
			                $('#add_enfermedad_form').find('select').multiSelect('deselect_all');
			    	    	$('#enfermedad').select2('data', null);
				            validatorEnf.elements().closest('.form-group').removeClass('has-error');
				            validatorEnf.resetForm();
	    				}
		            }
		            , 'text' )
			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
			  			alert( parametros.processError + " : " + errorThrown);
			  		});
    	    }
    	    
    	    Date.prototype.yyyymmdd = function() {         
		        
		        var yyyy = this.getFullYear().toString();                                    
		        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
		        var dd  = this.getDate().toString();             
		                            
		        return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
		    }; 
        }
	};
}();