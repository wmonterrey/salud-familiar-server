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
    };

    return {
        //main function to initiate the module
        init: function (parametros) {
            if (!jQuery().bootstrapWizard) {
                return;
            }
            
            handleDatePickers(parametros.language);
            handleInputMasks();
            handleMultiSelect();
            handleSelect2();

            $('#silais').change(
            		function() {
            			$.getJSON('/hsf/opciones/municipios', {
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
            			});
                    });
            
            $('#municipio').change(
            		function() {
            			$.getJSON('/hsf/opciones/sectores', {
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
            			});
                    });
            
            $('#sector').change(
            		function() {
            			$.getJSON('/hsf/opciones/comunidades', {
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
            			});
                    });
            
            var form = $('#submit_form');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
                       
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //Datos generales
                	silais: {
                        required: false
                    },
                    municipio: {
                        required: false
                    },
                    sector: {
                        required: false
                    },
                    comunidad: {
                        required: false
                    },
                    numVivienda: {
                    	min:1,
                        required: false
                    },
                    numFamilia: {
                    	min:1,
                        required: false
                    },
                    direccion: {
                    	minlength:10,
                        required: false
                    },
                    numFicha: {
                    	min:1,
                        required: false
                    },
                    personaVisita: {
                    	minlength:10,
                        required: false
                    },
                    personaVisitaProfesion: {
                        required: false
                    },
                    fechaVisita: {
                        required: false
                    },
                    hacinamiento: {
                        required: false
                    },
                    noPersonasFamilia: {
                    	min:1,
                    	required: false
                    },
                    accionesComunitarias: {
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
                    	guardarHSF(index + 1);
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
                    	guardarHSF(index);
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
            	if (IsValid) {
            		guardarFamiliaVisita();
            		guardarCarHigSan();
            		guardarFactSocEc();
            		guardarFuncFam();
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
    		            //alert( 'Hooray .....' );
    		        }
    		        else
    		        {
    		            $input.eq( $input.index( this ) + 1 ).focus();
    		        }
    		    }
    		});
            
                      
            $("#personamodalform").on("shown.bs.modal", function () { 
            	$('#nombres').focus();
            });
            
            $('body').on('hidden.bs.modal', '.modal', function () {
            	$('#add_person_form').trigger("reset");
            	$('#add_person_form .alert-danger').hide();
            	$('#add_person_form .alert-success').hide();
				$("#save-person").unbind("click");
				$("#save-person-add").unbind("click");
              });
            
            var table  = $('#lista_personas').DataTable({
                "aLengthMenu": [
                    [5, 10, 15, 20, -1],
                    [5, 10, 15, 20, "Todos"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sPaginationType": "bootstrap"
            });
    		
    		var tt = new $.fn.dataTable.TableTools( table, {
            	"sSwfPath": "${dataTablesTTSWF}",
            	"aButtons": [
            	                {
            	                    "sExtends":    "collection",
            	                    "sButtonText": parametros.exportar,
            	                    "aButtons": [
            	                                 {
            	                                     "sExtends": "csv",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1, 2, 3, 4 ]
            	                                 },
            	                                 {
            	                                     "sExtends": "pdf",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1, 2, 3, 4 ],
            	                                     "sPdfOrientation": "landscape",
            	                                 }
            	                                 ]
            	                }
            	            ]
            } );
    		 
    	    $( tt.fnContainer() ).insertBefore('div.table-toolbar');

            jQuery('#lista_personas_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            jQuery('#lista_personas_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            jQuery('#lista_personas_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialize select2 dropdown
        }
    };

}();