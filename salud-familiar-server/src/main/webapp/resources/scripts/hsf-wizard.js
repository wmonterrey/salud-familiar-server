var FormWizardHSF = function () {
	
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

        $("#numFicha").inputmask({
            "mask": "9",
            "repeat": 6,
            "greedy": false
        });
        
        $("#numFamilia").inputmask({
            "mask": "9",
            "repeat": 6,
            "greedy": false
        });
        
        $("#numVivienda").inputmask({
            "mask": "9",
            "repeat": 6,
            "greedy": false
        });
        
        $("#fechaVisita").inputmask("d/m/y", {
            "placeholder": "dd/mm/yyyy"
        }); //multi-char placeholder
    };

    var handleMultiSelect = function () {
        $('#factRiesgoMod').multiSelect();
        $('#factRiesgoNoMod').multiSelect();
        $('#factRiesgoSocial').multiSelect();
        $('#discapacidades').multiSelect();
        $('#animalesDom').multiSelect();
        $('#riesgoNatural').multiSelect();
        $('#riesgoMeteorologico').multiSelect();
        $('#riesgoBiologico').multiSelect();
        $('#riesgoSocial').multiSelect();
        $('#factoresMedAmb').multiSelect();
        $('#combCocinar').multiSelect();
        $('#accionesComunitarias').multiSelect();
        $('#crisisNormativa').multiSelect();
        $('#crisisParanormativa').multiSelect();
    };
    
    var handleSelect2 = function () {
    	$("#personaVisitaProfesion").select2({
        });
        $("#silais").select2({
        });
        $("#municipio").select2({
        });
        $("#sector").select2({
        });
        $("#comunidad").select2({
        });
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
        $("#fallecido").select2({
        }); 
        $("#hacinamiento").select2({
        });
        $("#aAgua").select2({
        });
        $("#cAgua").select2({
        });
        $("#electricidad").select2({
        });
        $("#depExcretas").select2({
        });
        $("#depBasura").select2({
        });
        $("#depResLiq").select2({
        });
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
        $("#tamFamilia").select2({
        });
        $("#ontogenesis").select2({
        });
        $("#etapaCicloVital").select2({
        });
        $("#usoMedTradicional").select2({
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
            
            var pageContent = $('.page-content');
            handleDatePickers(parametros.language);
            handleInputMasks();
            handleMultiSelect();
            handleSelect2();
            
            
            var table1 = $('#lista_personas').DataTable({
            	bFilter: false, bInfo: false, bPaginate: false
            });
            
            var table2 = $('#lista_enfermedades').DataTable({
            	bFilter: false, bInfo: false, bPaginate: false
            });
            
            var table3 = $('#lista_enfermedadessoc').DataTable({
            	bFilter: false, bInfo: false, bPaginate: false
            });

            $('#silais').change(
            		function() {
            			App.blockUI(pageContent, false);
            			$.getJSON(parametros.opcMuniUrl, {
            				entidadId : $('#silais').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#municipio").select2("val", "");
            				$("#sector").select2("val", "");
            				$("#comunidad").select2("val", "");
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].codigoNacional + '">'
            							+ data[i].nombre + '</option>';
            				}
            				html += '</option>';
            				$('#municipio').html(html);
            				App.unblockUI(pageContent);
            			});
                    });
            
            $('#municipio').change(
            		function() {
            			App.blockUI(pageContent, false);
            			$.getJSON(parametros.opcSectUrl, {
            				municipioId : $('#municipio').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#sector").select2("val", "");
            				$("#comunidad").select2("val", "");
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].codigo + '">'
            							+ data[i].nombre + '</option>';
            				}
            				html += '</option>';
            				$('#sector').html(html);
            				App.unblockUI(pageContent);
            			});
                    });
            
            $('#sector').change(
            		function() {
            			App.blockUI(pageContent, false);
            			$.getJSON(parametros.opcComuUrl, {
            				sectorId : $('#sector').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#comunidad").select2("val", "");
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].codigo + '">'
            							+ data[i].nombre + '</option>';
            				}
            				html += '</option>';
            				$('#comunidad').html(html);
            				App.unblockUI(pageContent);
            			});
                    });
            
            var form = $('#submit_form');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            
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
                    //Datos generales
                	silais: {
                        required: true
                    },
                    municipio: {
                        required: true
                    },
                    sector: {
                        required: true
                    },
                    comunidad: {
                        required: true
                    },
                    numVivienda: {
                    	min:1,
                        required: true
                    },
                    numFamilia: {
                    	min:1,
                        required: true
                    },
                    direccion: {
                    	minlength:10,
                        required: true
                    },
                    numFicha: {
                    	min:1,
                        required: true
                    },
                    personaVisita: {
                    	minlength:10,
                        required: true
                    },
                    personaVisitaProfesion: {
                        required: true
                    },
                    fechaVisita: {
                        required: true
                    },
                    noPersonasFamilia: {
                    	min:1,
                    	required: true
                    },
                    hacinamiento: {
                        required: true
                    },
                    animalesDom: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    riesgoNatural: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    riesgoMeteorologico: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    riesgoBiologico: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    riesgoSocial: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    factoresMedAmb: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    combCocinar: {
                    	required: true,
                    	noNingunoyOtro:true
                    },
                    aAgua: {
                    	required: true
                    },
                    cAgua: {
                    	required: true
                    },
                    electricidad: {
                    	required: true
                    },
                    depExcretas: {
                    	required: true
                    },
                    depBasura: {
                    	required: true
                    },
                    depResLiq: {
                    	required: true
                    },
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
                    },
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
                
                messages: { 
                	noPersonasFamilia: {
                        required: parametros.valPersonas,
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success.hide();
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
                    success.show();
                    error.hide();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                    alert("ddd");
                }
            });

            var displayConfirm = function() {
                $('#tab6 .form-control-static', form).each(function(){
                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                    if (input.is(":text") || input.is("textarea")) {
                        $(this).html(input.val());
                    } else if (input.is("select")) {
                        $(this).html(input.find('option:selected').text());
                    } else if (input.is(":radio") && input.is(":checked")) {
                        $(this).html(input.attr("data-title"));
                    }
                });
            };

            var handleTitle = function(tab, navigation, index) {
                var total = navigation.find('li').length;
                var current = index + 1;
                // set wizard title
                $('.step-title', $('#form_wizard_1')).text('Paso ' + (index + 1) + ' de ' + total);
                // set done steps
                jQuery('li', $('#form_wizard_1')).removeClass("done");
                var li_list = navigation.find('li');
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }

                if (current == 1) {
                    $('#form_wizard_1').find('.button-previous').hide();
                } else {
                    $('#form_wizard_1').find('.button-previous').show();
                }

                if (current >= total) {
                    $('#form_wizard_1').find('.button-next').hide();
                    $('#form_wizard_1').find('.button-submit').show();
                    displayConfirm();
                } else {
                    $('#form_wizard_1').find('.button-next').show();
                    $('#form_wizard_1').find('.button-submit').hide();
                }
                App.scrollTo($('.page-title'));
            };

            // default form wizard
            $('#form_wizard_1').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index, clickedIndex) {
                    success.hide();
                    error.hide();
                    if (form.valid() == false) {
                        return false;
                    }
                    else {
                    	App.blockUI(pageContent, false);
                    	guardarHSF(index + 1);
                    	App.unblockUI(pageContent);
                    }
                    handleTitle(tab, navigation, clickedIndex);
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();
                    
                    if (form.valid() == false) {
                        return false;
                    }
                    else{
                    	App.blockUI(pageContent, false);
                    	guardarHSF(index);
                    	App.unblockUI(pageContent);
                    }
                    handleTitle(tab, navigation, index);
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();
                    handleTitle(tab, navigation, index);
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_1').find('.progress-bar').css({
                        width: $percent + '%'
                    });
                }
            });

            $('#form_wizard_1').find('.button-previous').hide();
            $('#form_wizard_1 .button-submit').click(function () {
            	var IsValid = true;
                // Validate Each Bootstrap tab
                $(".hsfcompleta").find("div.tab-pane").each(function (index, tab) {
                    var id = $(tab).attr("id");
                    $('a[href="#' + id + '"]').tab('show');
                    var IsTabValid = form.valid();
                    if (!IsTabValid) {
                        IsValid = false;
                        return false; // Break each loop
                    }
                });
                if (IsValid){
	        		guardarFamiliaVisita();
	        		guardarCarHigSan();
	        		guardarFactSocEc();
	        		guardarFuncFam();
	        		table1.fnClearTable();
	        		
	        		form.find('input:text, input:password, textarea').val('');
	        		form.find('input:radio, input:checkbox').prop('checked', false);
	        		form.find('select').select2('val','');
	        		form.find('select').multiSelect('deselect_all');
	        		jQuery('li', $('#form_wizard_1')).removeClass("done");
	        		$('a[href="#tab1"]').tab('show');	
	        		$('#form_wizard_1').find('.button-submit').hide();
	        		$('#form_wizard_1').find('.button-previous').hide();
	        		$('#form_wizard_1').find('.button-next').show();
                }
            }).hide();
            
            function guardarHSF(index){
            	if (index == 1){
            		guardarFamiliaVisita();
            	}
            	else if (index == 3){
            		guardarCarHigSan();
            	}
            	else if (index == 4){
            		guardarFactSocEc();
            	}
            	else if (index == 5){
            		guardarFuncFam();
            	}
            }
            
            function guardarFamiliaVisita()
        	{
            	$.post( parametros.addFamiliaVisitaUrl
    		            , $('#submit_form').serialize()
    		            , function( data )
    		            {
    						visita = JSON.parse(data);
    						toastr.success(parametros.processSuccess);
    						$('#idFamilia').val(visita.familia.idFamilia);
    						$('#idVisita').val(visita.idVisita);
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			    		alert( parametros.processError + " : " + errorThrown);
    			  		});
        	}
            
            function guardarCarHigSan()
        	{
            	$.post( parametros.addCarHigSanUrl
    		            , $('#submit_form').serialize()
    		            , function( data )
    		            {
    						caract = JSON.parse(data);
    						toastr.success(parametros.processSuccess);
    						$('#idCaractHig').val(caract.idCaractHig);
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			  			alert( parametros.processError + " : " + errorThrown);
    			  		});
        	}
            
            function guardarFactSocEc()
        	{
            	$.post( parametros.addFactSocEcUrl
    		            , $('#submit_form').serialize()
    		            , function( data )
    		            {
    						factores = JSON.parse(data);
    						toastr.success(parametros.processSuccess);
    						$('#idFactSocioEc').val(factores.idFactSocioEc);
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			  			alert( parametros.processError + " : " + errorThrown);
    			  		});
        	}
            
            function guardarFuncFam()
        	{
            	$.post( parametros.addFuncFamUrl
    		            , $('#submit_form').serialize()
    		            , function( data )
    		            {
    						funcionamiento = JSON.parse(data);
    						toastr.success(parametros.processSuccess);
    						$('#idFuncFamiliar').val(funcionamiento.idFuncFamiliar);
    		            }
    		            , 'text' )
    			  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
    			  			alert( parametros.processError + " : " + errorThrown);
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
            
                      
            $("#personamodalform").on("shown.bs.modal", function () { 
            	$('a[href="#tab2_1"]').tab('show');
            	$('#nombres').focus();
            });
            
            $("#personamodalform").on("hidden.bs.modal", function () { 
            	$('#add_person_form .alert-danger').hide();
            	$('#add_person_form .alert-success').hide();
            	$('#add_person_form').find('input:text, input:password, textarea').val('');
                $('#add_person_form').find('input:radio, input:checkbox').prop('checked', false);
                $('#add_person_form').find('select').select2('val','');
                $('#add_person_form').find('select').multiSelect('deselect_all');
                table2.fnClearTable();
                table3.fnClearTable();
				$("#save-person").unbind("click");
				$("#save-person-add").unbind("click");
				$("#dismiss-modalperson").unbind("click");
				$('a[href="#tab2_1"]').tab('show');
            	$('#nombres').focus();
            });
            
            $("#enferform").on("shown.bs.modal", function () { 
            	$("#save-person").click();
            });
            
            $("#enferform").on("hidden.bs.modal", function () { 
            	$('#add_enfermedad_form .alert-danger').hide();
            	$('#add_enfermedad_form .alert-success').hide();
            	$('#add_enfermedad_form').find('input:text, input:password, textarea').val('');
                $('#add_enfermedad_form').find('input:radio, input:checkbox').prop('checked', false);
                $('#add_enfermedad_form').find('select').select2('val','');
                $('#add_enfermedad_form').find('select').multiSelect('deselect_all');
                $('#enfermedad').select2('data', null);
				$("#save-enf").unbind("click");
				$("#save-enf-add").unbind("click");
				$("dismiss-modalenf").unbind("click");
              });
            
            $("#enfersocform").on("shown.bs.modal", function () { 
            	$("#save-person").click();
            });
            
            $("#enfersocform").on("hidden.bs.modal", function () { 
            	$('#add_enfermedadsoc_form .alert-danger').hide();
            	$('#add_enfermedadsoc_form .alert-success').hide();
            	$('#add_enfermedadsoc_form').find('input:text, input:password, textarea').val('');
                $('#add_enfermedadsoc_form').find('input:radio, input:checkbox').prop('checked', false);
                $('#add_enfermedadsoc_form').find('select').select2('val','');
                $('#add_enfermedadsoc_form').find('select').multiSelect('deselect_all');
				$("#save-enfsoc").unbind("click");
				$("#save-enfsoc-add").unbind("click");
				$("dismiss-modalenfsoc").unbind("click");
              });
        }
    };

}();