var FormWizardHSFModalEnfSocValidation = function () {
	
	return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	var enfSocForm = $('#add_enfermedadsoc_form');
        	var pageContent = $('.page-content');
    	    
    	    var validatorEnfSoc = enfSocForm.validate({
    	        doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
    	        errorElement: 'span', //default input error message container
    	        errorClass: 'help-block', // default input error message class
    	        focusInvalid: false, // do not focus the last invalid input
    	        rules: {
    	        	enfermedadsoc: {
     	                required: true
     	            },
     	            fechaOcurrenciaSoc: {
    	                required: true
    	            },
    	            personaAtendioSoc: {
    	                required: true
    	            },
    	        },
    	
    	        invalidHandler: function (event, validator) { //display error alert on form submit   
    	        	$('#add_enfermedadsoc_form .alert-danger').show();
                	$('#add_enfermedadsoc_form .alert-success').hide();
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
    	                $('#add_enfermedadsoc_form .alert-danger').hide();
                    	$('#add_enfermedadsoc_form .alert-success').hide();
    	        },
    	
    	    });
    	    
    	    $('#save-enfsoc').click(function() {
            	if (enfSocForm.valid()){
            		App.blockUI(pageContent, false);
            		$('#idPersonaEnfSoc').val($('#idPersona').val());
            		guardarEnfermedadSoc();
            		App.unblockUI(pageContent);
            	}
    	    });
    	    
    	    $('#save-enfsoc-add').click(function() {
            	if (enfSocForm.valid()){
            		App.blockUI(pageContent, false);
            		$('#idPersonaEnfSoc').val($('#idPersona').val());
            		guardarEnfermedadSocAgregar();
		            $('#enfermedad').focus();
		            App.unblockUI(pageContent);
            	}
    	    });
    	    
    	    $('#dismiss-modalenfsoc').click(function() {
    	    	$('#enfermedadsoc').select2('data', null);
	            validatorEnfSoc.elements().closest('.form-group').removeClass('has-error');
	            validatorEnfSoc.resetForm();
    	    });
    	    
    	    
    	    function guardarEnfermedadSoc(){
				$.post( parametros.addEnfermedadSCUrl
		            , $('#add_enfermedadsoc_form').serialize()
		            , function( data )
		            {
						if (data == ""){
	    					toastr.error(parametros.deniedError);
	    				}
	    				else{
							enfermedadsoc = JSON.parse(data);
							if ($('#idEnfermedadSoc').val()==''){
								var d = new Date(Date.parse(enfermedadsoc.fechaOcurrencia));
								$('table#lista_enfermedadessoc').dataTable().fnAddData( [
								  enfermedadsoc.enfermedad.valor, d.yyyymmdd(), enfermedadsoc.personaAtendio.valor]);
							}
							$('#idEnfermedadSoc').val(enfermedadsoc.idEnfSocioC);
							validatorEnfSoc.resetForm();
	    				}
		            }
		            , 'text' )
			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
			  			alert( parametros.processError + " : " + errorThrown);
			  		});
    	    }
    	    
    	    function guardarEnfermedadSocAgregar(){
				$.post( parametros.addEnfermedadSCUrl
		            , $('#add_enfermedadsoc_form').serialize()
		            , function( data )
		            {
						if (data == ""){
	    					toastr.error(parametros.deniedError);
	    				}
	    				else{
							enfermedadsoc = JSON.parse(data);
							if ($('#idEnfermedadSoc').val()==''){
								var d = new Date(Date.parse(enfermedadsoc.fechaOcurrencia));
								$('table#lista_enfermedadessoc').dataTable().fnAddData( [
								  enfermedadsoc.enfermedad.valor, d.yyyymmdd(), enfermedadsoc.personaAtendio.valor]);
							}
			    	    	$('#add_enfermedadsoc_form').find('input:text, input:password, textarea').val('');
			                $('#add_enfermedadsoc_form').find('input:radio, input:checkbox').prop('checked', false);
			                $('#add_enfermedadsoc_form').find('select').select2('val','');
			                $('#add_enfermedadsoc_form').find('select').multiSelect('deselect_all');
			    	    	$('#enfermedadsoc').select2('data', null);
				            validatorEnfSoc.elements().closest('.form-group').removeClass('has-error');
				            validatorEnfSoc.resetForm();
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