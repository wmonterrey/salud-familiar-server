var SearchHSF = function () {
	
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
            
            var table1 = $('#resultados').dataTable( {  
                "aoColumns" : [   
                               null,   
                               {sClass: "aw-right" },null,{sClass: "aw-right" },{sClass: "aw-right" },null  
                           ]   } );
    		$('#resultados_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            $('#resultados_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            $('#resultados_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialize select2 dropdown
            
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
            
            var form = $('#search_form');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
                   
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
                        required: false
                    },
                    numFamilia: {
                    	min:1,
                        required: false
                    },
                    numFicha: {
                    	min:1,
                        required: false
                    },
                    fechaVisita: {
                        required: false
                    },
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
                    success.hide();
                    error.hide();
                    table1.fnClearTable();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                    getHSFs();
                }
            });
            
            function getHSFs() {
    			App.blockUI(pageContent, false);
    			$.getJSON(parametros.hsfsUrl, {
    				codComunidad : $('#comunidad').val(),
    				numVivienda : $('#numVivienda').val(),
    				numFamilia : $('#numFamilia').val(),
    				numFicha : $('#numFicha').val(),
    				fechaVisita : $('#fechaVisita').val(),
    				ajax : 'true'
    			}, function(data) {
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					var d = new Date(data[i].fechaVisita);
						var visitaUrl = parametros.visitUrl + '/'+data[i].idVisita;
						table1.fnAddData(
    							[d.yyyymmdd(), data[i].numFicha, data[i].personaVisita, data[i].familia.numVivienda, data[i].familia.numFamilia, '<a href='+ visitaUrl + ' class="btn btn-default btn-xs"><i class="fa fa-search"></i></a>']);
    				}
    				App.unblockUI(pageContent);
    			})
    			.fail(function() {
				    alert( "error" );
				    App.unblockUI(pageContent);
				});
            };
            
            Date.prototype.yyyymmdd = function() {         
		        
		        var yyyy = this.getFullYear().toString();                                    
		        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
		        var dd  = this.getDate().toString();             
		                            
		        return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
		   }; 
        }
    };

}();