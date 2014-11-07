var FormEdit6HSF = function () {
	
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
        
        $("#cedula").inputmask({
        	"mask": "[999-999999-9999A]",
        	"greedy": false
        });

        $("#fechaNacimiento").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        }); //multi-char placeholder
    };
    
    var handleMultiSelect = function () {
        $('#factRiesgoMod').multiSelect();
        $('#factRiesgoNoMod').multiSelect();
        $('#factRiesgoSocial').multiSelect();
        $('#discapacidades').multiSelect();
    };
   
    var handleSelect2 = function () {
    	$("#actaNacimiento").select2({
        });
    	$("#etnia").select2({
        });
        $("#sexo").select2({
        });
        $("#escolaridad").select2({
        });
        $("#ocupacion").select2({
        });
        $("#religion").select2({
        });
        $("#embarazada").select2({
        });
        $("#cpnActualizado").select2({
        });
        $("#mujerEdadFertil").select2({
        });
        $("#planFamiliar").select2({
        });  
        $("#men1A").select2({
        });
        $("#men1AVPCD").select2({
        }); 
        $("#grupoDisp").select2({
        });
        $('#enfermedad').select2({
        	minimumInputLength: 3,
        	id: function(enfermedad){ return enfermedad.codigoCie10; },
            ajax: {
                url: '/hsf/opciones/enfermedades',
                dataType: 'json',
                quietMillis: 100,
                data: function(term, page) {
                    return {
                    	filtro: term
                    };
                },
                results: function(data, page ) {
                    return {
                    	results: data
                    };
                }
            },
            formatResult: function(enfermedad) { 
            	var markup = "<table'><tr>";
                markup += "<td valign='top'><h5>" + enfermedad.codigoCie10 + "</h5>";
                markup += "<div>" + enfermedad.nombreCie10 + "</div>";
                markup += "</td></tr></table>";
                return markup; 
            },
            formatSelection: function(enfermedad) { 
                return enfermedad.nombreCie10; 
            },
            dropdownCssClass: "bigdrop",
            initSelection: function (item, callback) {
                return item;
            },
            escapeMarkup: function (m) { return m; }
        });
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {  
        	if (!jQuery().bootstrapWizard) {
                return;
            }
        	var aPos = null;
            var pageContent = $('.page-content');
            handleDatePickers(parametros.language);
            handleInputMasks();
            handleSelect2();
            handleMultiSelect();
            var form = $('#add-person-form');
            var error = $('.alert-danger', form);
            
            var table2 = $('#lista_enfermedades').DataTable({
            	bFilter: false, bInfo: false, bPaginate: false
            });
            
            var table3 = $('#lista_enfermedadessoc').DataTable({
            	bFilter: false, bInfo: false, bPaginate: false
            });
            
            jQuery.validator.addMethod("noNingunoyOtro", function(value, select) { 
            	var isValid = true;
            	var number = $('option:selected', select).size();
                if (number > 1 && select.options[select.options.length-1].selected) {
                	isValid = false;
                }
                return isValid;
	      	}, "Invalido");
                       
            var validatorPerson = form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
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
                    guardarPersona();
                }
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
                	$('#add-person-form .alert-danger').hide();
                	$('#add-person-form .alert-success').hide();
                    if (form.valid() == false) {
                        return false;
                    }
                    handleTitle(tab, navigation, clickedIndex);
                },
                onNext: function (tab, navigation, index) {
                	$('#add-person-form .alert-danger').hide();
                	$('#add-person-form .alert-success').hide();
                    
                    if (form.valid() == false) {
                        return false;
                    }
                    handleTitle(tab, navigation, index);
                },
                onPrevious: function (tab, navigation, index) {
                	$('#add-person-form .alert-danger').hide();
                	$('#add-person-form .alert-success').hide();
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
                    var IsTabValid = form.valid();
                    if (!IsTabValid) {
                        IsValid = false;
                        return false; // Break each loop
                    }
                });
                if (IsValid){
                	App.blockUI(pageContent, false);
                	guardarPersona();
                	jQuery('li', $('#form_wizard_2')).removeClass("done");
                	App.unblockUI(pageContent);
                }
            }).hide();
            
            $('#lista_enfermedades tbody tr').click(function() {
            	// Get the position of the current data from the node
                aPos = table2.fnGetPosition( this );
            });
            
    	    $('#quitar-enf').click(function() {
        		App.blockUI(pageContent, false);
        		$.getJSON(parametros.quitarEnfermedadUrl, { idEnfermedad: $('#accionUrl').val() }, function(borrado) {
        	        if (borrado) {
        	        	table2.fnDeleteRow(aPos);
        	        	toastr.success(parametros.processSuccess);
        	        } else {
        	        	toastr.success(parametros.processError);
        	        }
        	    });
        		$('#basic').modal('hide');
        		App.unblockUI(pageContent);
    	    });
    	    
    	    $(".quitarenf").click(function(){ 
    	    	$('#accionUrl').val($(this).data('id'));
    	    	$('#titulo').html('<h4 class="modal-title">'+$(this).data('enf')+'</h4>');
    	    	$('#cuerpo').html('<h4>'+parametros.quitarenf+'</h4>');
    	    	$('#basic').modal('show');
    	     });
            
            
            function guardarPersona()
        	{
            	App.blockUI(pageContent, false);
            	$.post( parametros.editPersonaUrl
    		            , $('#add-person-form').serialize()
    		            , function( data )
    		            {
		            		if (data == ""){
		    					toastr.error(parametros.deniedError);
		    				}
		    				else{
		    					persona = JSON.parse(data);
		    					toastr.success(parametros.processSuccess);
		    				}
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			    		alert( parametros.processError + " : " + errorThrown);
    			    		App.unblockUI(pageContent);
    			  		});
            	window.setTimeout(function(){
			        window.location.href = parametros.familiaUrl;
			    }, 1500);
            	App.unblockUI(pageContent);
        	}
            
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
            
 		   $("#fechaNacimiento").change(
 	           		function() {
 	           			$("#edad").val(getAge($("#fechaNacimiento").val()));
 	           			$("#sexo").change();
 	           });
 		   
 		  function getAge(dateString) {
			   var now = new Date();

			   var yearNow = now.getYear();
			   var monthNow = now.getMonth();
			   var dateNow = now.getDate();

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