var FormEditHSF = function () {
	
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
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {            
            var pageContent = $('.page-content');
            handleDatePickers(parametros.language);
            handleInputMasks();
            handleSelect2();

            $('#silais').change(
            		function() {
            			App.blockUI(pageContent, false);
            			$.getJSON(parametros.opcMuniUrl, {
            				entidadId : $('#silais').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#municipio").select2('data',null);
            				$("#sector").select2('data',null);
            				$("#comunidad").select2('data',null);
            				$("#municipio").empty();
            				$("#sector").empty();
            				$("#comunidad").empty();
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
            				$("#sector").select2('data',null);
            				$("#comunidad").select2('data',null);
            				$("#sector").empty();
            				$("#comunidad").empty();
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
            				$("#comunidad").select2('data',null);
            				$("#comunidad").empty();
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
            
            var form = $('#edit-hsf-form');
            var error = $('.alert-danger', form);
                       
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
                    guardarFamiliaVisita();
                }
            });
            
            
            function guardarFamiliaVisita()
        	{
            	App.blockUI(pageContent, false);
            	$.post( parametros.addFamiliaVisitaUrl
    		            , $('#edit-hsf-form').serialize()
    		            , function( data )
    		            {
    						visita = JSON.parse(data);
    						App.unblockUI(pageContent);
    						toastr.success(parametros.processSuccess);
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