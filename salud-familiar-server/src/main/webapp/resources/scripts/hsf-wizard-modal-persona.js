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
        
        $("#fechaNacimiento").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        }); //multi-char placeholder
    };
	
	return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	handleInputMasks();
        	var personForm = $('#add_person_form');
    	    if ($('#noPersonasFamilia').val()==''){
    	    	$('#numPersona').val(1);
    	    }else{
    	    	$('#numPersona').val(parseInt($('#noPersonasFamilia').val()) + 1);
    	    }
    	    var table2 = $('#lista_enfermedades').DataTable();
            var table3 = $('#lista_enfermedadessoc').DataTable();
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
    	            },
    	            cedula: {
    	                required: true
    	            },
    	            fechaNacimiento: {
    	                required: true
    	            },
    	            actaNacimiento: {
    	                required: true
    	            },
    	            etnia: {
    	                required: true
    	            },
    	            sexo: {
    	                required: true
    	            },
    	            escolaridad: {
    	                required: true
    	            },
    	            ocupacion: {
    	                required: true
    	            },
    	            religion: {
    	                required: true
    	            },
    	            embarazada: {
    	                required: true
    	            },
    	            cpnActualizado: {
    	                required: true
    	            },
    	            mujerEdadFertil: {
    	                required: true
    	            },
    	            planFamiliar: {
    	                required: true
    	            },
    	            men1A: {
    	                required: true
    	            },
    	            men1AVPCD: {
    	                required: true
    	            },
    	            factRiesgoMod: {
    	                required: true
    	            },
    	            factRiesgoNoMod: {
    	                required: true
    	            },
    	            factRiesgoSocial: {
    	                required: true
    	            },
    	            discapacidades: {
    	                required: true
    	            },
    	            grupoDisp: {
    	                required: true
    	            }
    	        },
    	
    	        invalidHandler: function (event, validator) { //display error alert on form submit   
                	$('#add_person_form .alert-danger').show();
                	$('#add_person_form .alert-success').hide();
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
                    if (!personForm.valid()) {
                        $('#enferform').modal('hide');
                        IsValid = false;
                        return false; // Break each loop
                    }
                });
                if (IsValid){
                	$('#idFamiliaPerson').val($('#idFamilia').val());
                	guardarPersona();
                	$('#add_person_form .alert-danger').hide();
                	$('#add_person_form .alert-success').show();
                }
    	    });
    	    
    	    $('#save-person-add').click(function() {
    	    	var IsValid = true;
    	    	// Validate Each Bootstrap tab
                $(".persona").find("div.tab-pane").each(function (index, tab) {
                    var id = $(tab).attr("id");
                    $('a[href="#' + id + '"]').tab('show');
                    if (!personForm.valid()) {
                    	$('#enferform').modal('hide');
                    	IsValid = false;
                        return false; // Break each loop
                    }
                });
                if(IsValid){
	    	    	$('#idFamiliaPerson').val($('#idFamilia').val());
	    	    	guardarPersonaAgregar();
					$('a[href="#tab_1_1"]').tab('show');
	        		$('#nombres').focus();
	            	$('#add_person_form .alert-danger').hide();
	            	$('#add_person_form .alert-success').hide();
                }
    	    });
    	    
    	    $('#dismiss-modalperson').click(function() {
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
								var d = new Date(Date.parse(persona.fechaNacimiento));
								$('table#lista_personas').dataTable().fnAddData( [
								  persona.numPersona, persona.nombres, persona.primerApellido, persona.segundoApellido, persona.cedula, d.yyyymmdd(), persona.grupoDisp.valor]);
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
	    							var d = new Date(Date.parse(persona.fechaNacimiento));
	    							$('table#lista_personas').dataTable().fnAddData( [
	    							  persona.numPersona, persona.nombres, persona.primerApellido, persona.segundoApellido , persona.cedula, d.yyyymmdd(), persona.grupoDisp.valor]);
	    						}
	    						validatorPerson.resetForm();
	    						$('#noPersonasFamilia').val($('#numPersona').val());
	    						$('#add_person_form').find('input:text, input:password, textarea').val('');
	    		                $('#add_person_form').find('input:radio, input:checkbox').prop('checked', false);
	    		                $('#add_person_form').find('select').select2('val','');
	    		                $('#add_person_form').find('select').multiSelect('deselect_all');
	    		                table2.fnClearTable();
	    		                table3.fnClearTable();
	    						$('#numPersona').val(parseInt($('#noPersonasFamilia').val()) + 1);
	    		            }
	    		            , 'text' )
	    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
	    			  			alert( parametros.processError + " : " + errorThrown);
	    			  		});
	    		 }
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