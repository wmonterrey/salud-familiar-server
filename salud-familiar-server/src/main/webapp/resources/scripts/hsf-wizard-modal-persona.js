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
        
        $("#cedula").inputmask({
        	"mask": "[999-999999-9999A]",
        	"greedy": false
        });
        
        $("#fechaNacimiento").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        }); 
        
        $("#fechaOcurrencia").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        });
        
        $("#fechaOcurrenciaSoc").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        });//multi-char placeholder
    };
	
	return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	handleInputMasks();
        	var pageContent = $('.page-content');
        	var personForm = $('#add_person_form');
    	    if ($('#noPersonasFamilia').val()==''){
    	    	$('#numPersona').val(1);
    	    }else{
    	    	$('#numPersona').val(parseInt($('#noPersonasFamilia').val()) + 1);
    	    }
    	    jQuery.validator.addMethod("notEqual", function(value, element, param) {
    	    	  return this.optional(element) || value != param;
    	    	}, "Inválido");
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
    	                required: false,
    	                minlength:16
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
    	                required: true,
                    	noNingunoyOtro:true
    	            },
    	            factRiesgoNoMod: {
    	                required: true,
                    	noNingunoyOtro:true
    	            },
    	            factRiesgoSocial: {
    	                required: true,
                    	noNingunoyOtro:true
    	            },
    	            discapacidades: {
    	                required: true,
                    	noNingunoyOtro:true
    	            },
    	            grupoDisp: {
    	                required: true
    	            },
    	            edad:{
    	            	required:true,
    	            	notEqual: "Edad inválida!"
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
    	                $('#add_person_form .alert-danger').hide();
                    	$('#add_person_form .alert-success').hide();
    	        },
    	
    	    });
    	    
    	    var handleTitle = function(tab, navigation, index) {
                var total = navigation.find('li').length;
                var current = index + 1;
                // set wizard title
                $('.step-title', $('#form_wizard_2')).text('Paso ' + (index + 1) + ' de ' + total);
                // set done steps
                jQuery('li', $('#form_wizard_2')).removeClass("done");
                var li_list = navigation.find('li');
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }

                if (current == 1) {
                    $('#form_wizard_2').find('.button-previous').hide();
                } else {
                    $('#form_wizard_2').find('.button-previous').show();
                }

                if (current >= total) {
                    $('#form_wizard_2').find('.button-next').hide();
                    $('#form_wizard_2').find('.button-submit').show();
                } else {
                    $('#form_wizard_2').find('.button-next').show();
                    $('#form_wizard_2').find('.button-submit').hide();
                }
                App.scrollTo($('.page-title'));
            };

            // default form wizard
            $('#form_wizard_2').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index, clickedIndex) {
                	$('#add_person_form .alert-danger').hide();
                	$('#add_person_form .alert-success').hide();
                    if (personForm.valid() == false) {
                        return false;
                    }
                    handleTitle(tab, navigation, clickedIndex);
                },
                onNext: function (tab, navigation, index) {
                	$('#add_person_form .alert-danger').hide();
                	$('#add_person_form .alert-success').hide();
                    
                    if (personForm.valid() == false) {
                        return false;
                    }
                    handleTitle(tab, navigation, index);
                },
                onPrevious: function (tab, navigation, index) {
                	$('#add_person_form .alert-danger').hide();
                	$('#add_person_form .alert-success').hide();
                    handleTitle(tab, navigation, index);
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_2').find('.progress-bar').css({
                        width: $percent + '%'
                    });
                }
            });

            $('#form_wizard_2').find('.button-previous').hide();
            $('#save-person').hide();
            $('#form_wizard_2').find('.button-next').show();
            $('#form_wizard_2 .button-submit').click(function () {
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
                if (IsValid){
                	App.blockUI(pageContent, false);
                	$('#idFamiliaPerson').val($('#idFamilia').val());
                	guardarPersona();
                	jQuery('li', $('#form_wizard_2')).removeClass("done");
                	$('#personamodalform').modal('hide');
                	App.unblockUI(pageContent);
                }
            }).hide();
    	    
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
                	App.blockUI(pageContent, false);
                	$('#idFamiliaPerson').val($('#idFamilia').val());
                	guardarPersona();
                	App.unblockUI(pageContent);
                }
    	    });
    	    
    	    function guardarPersona(){
		    if (personForm.valid()){
				$.post( parametros.addPersonUrl
			            , $('#add_person_form').serialize()
			            , function( data )
			            {
							if (data == ""){
		    					toastr.error(parametros.deniedError);
		    				}
		    				else{
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
		   
		   $("#fechaNacimiento").change(
           		function() {
           			$("#edad").val(getAge($("#fechaNacimiento").val(), $("#fechaVisita").val()));
           			$("#sexo").change();
           });
		   
		   $("#sexo").change(
	           		function() {
	           			if($("#sexo").val()=='SEXO|F' && $("#edada").val()>10 && $("#edada").val()<55){
	           				$("#embarazada").select2("enable", true);
	           				$("#mujerEdadFertil").select2("val", 1);
	         				$("#mujerEdadFertil").select2("readonly", true);
	           			}
	           			else{
	           				$("#embarazada").select2("val", '');
	           				$("#cpnActualizado").select2("val", '');
	           				$("#mujerEdadFertil").select2("val", '');
	           				$("#planFamiliar").select2("val", '');
	           				$("#embarazada").select2("enable", false);
	           				$("#cpnActualizado").select2("enable", false);
	           				$("#mujerEdadFertil").select2("enable", false);
	           				$("#planFamiliar").select2("enable", false);
	           				$("#embarazada").closest('.form-group').removeClass('has-error');
	           				$("#cpnActualizado").closest('.form-group').removeClass('has-error');
	           				$("#mujerEdadFertil").closest('.form-group').removeClass('has-error');
	           				$("#planFamiliar").closest('.form-group').removeClass('has-error');
	           				validatorPerson.resetForm();
		           			if($("#sexo").val()=='SEXO|F'){
		           				$("#mujerEdadFertil").select2("val", 0);
	         				    $("#mujerEdadFertil").select2("readonly", true);
		           			}
	           			}
	       });
		   
		   $("#embarazada").change(
	           		function() {
	           			if($("#embarazada").val()=='1'){
	           				$("#cpnActualizado").select2("enable", true);
	           				$("#planFamiliar").select2("val", '');
	           				$("#planFamiliar").select2("enable", false);
	           			}
	           			else{
	           				$("#planFamiliar").select2("enable", true);
	           				$("#cpnActualizado").select2("val", '');
	           				$("#cpnActualizado").select2("enable", false);
	           			}
	       });
		   
		   function getAge(dateString,dateVisita) {
			   var dov = new Date(dateVisita.substring(6,10),
					   dateVisita.substring(3,5)-1,                   
					   dateVisita.substring(0,2)                  
	                      );
			   
			   var yearNow = dov.getYear();
			   var monthNow = dov.getMonth();
			   var dateNow = dov.getDate();

			   var dob = new Date(dateString.substring(6,10),
			                      dateString.substring(3,5)-1,                   
			                      dateString.substring(0,2)                  
			                      );

			   var yearDob = dob.getYear();
			   var monthDob = dob.getMonth();
			   var dateDob = dob.getDate();
			   var age = {};
			   var ageString = "";
			   var yearString = "";
			   var monthString = "";
			   var dayString = "";
			   var monthAge = 0, dateAge = 0, yearAge = 0;


			   yearAge = yearNow - yearDob;

			   if (monthNow >= monthDob)
			     monthAge = monthNow - monthDob;
			   else {
			     yearAge--;
			     monthAge = 12 + monthNow -monthDob;
			   }

			   if (dateNow >= dateDob)
			     dateAge = dateNow - dateDob;
			   else {
			     monthAge--;
			     dateAge = 31 + dateNow - dateDob;

			     if (monthAge < 0) {
			       monthAge = 11;
			       yearAge--;
			     }
			   }

			   age = {
			       years: yearAge,
			       months: monthAge,
			       days: dateAge
			       };
			   
			   if ( age.years > 1 ) yearString = " años";
			   else yearString = " año";
			   if ( age.months> 1 ) monthString = " meses";
			   else monthString = " mes";
			   if ( age.days > 1 ) dayString = " días";
			   else dayString = " día";


			   if ( (age.years > 0) && (age.months > 0) && (age.days > 0) )
			     ageString = age.years + yearString + ", " + age.months + monthString + ", " + age.days + dayString;
			   else if ( (age.years == 0) && (age.months == 0) && (age.days > 0) )
			     ageString = age.days + dayString;
			   else if ( (age.years > 0) && (age.months == 0) && (age.days == 0) )
			     ageString = age.years + yearString;
			   else if ( (age.years > 0) && (age.months > 0) && (age.days == 0) )
			     ageString = age.years + yearString + ", " + age.months + monthString;
			   else if ( (age.years == 0) && (age.months > 0) && (age.days > 0) )
			     ageString = age.months + monthString + ", " + age.days + dayString;
			   else if ( (age.years > 0) && (age.months == 0) && (age.days > 0) )
			     ageString = age.years + yearString + ", " + age.days + dayString;
			   else if ( (age.years == 0) && (age.months > 0) && (age.days == 0) )
			     ageString = age.months + monthString;
			   else ageString = "Edad inválida!";
			   
			   $("#edada").val(yearAge);
			   $("#edadm").val(monthAge);
			   $("#edadd").val(dateAge);
			   
			   if(age.years < 1){
				   $("#men1A").select2("val", 1);
				   $("#men1A").select2("readonly", true);
				   $("#men1AVPCD").select2("val", '');
				   $("#men1AVPCD").select2("enable", true);
			   }
			   else{
				   $("#men1A").select2("val", 0);
				   $("#men1A").select2("readonly", true);
				   $("#men1AVPCD").select2("val", '');
				   $("#men1AVPCD").select2("enable", false);
				   $("#men1AVPCD").closest('.form-group').removeClass('has-error');
				   validatorPerson.resetForm();
			   }

			   return ageString;
			 }
        }
	
	};
	
}();