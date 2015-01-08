var ViewReport = function () {

    var handleSelect2 = function () {
        $("#silais").select2({
        });
        $("#municipio").select2({
        });
        $("#sector").select2({
        });
        $("#comunidad").select2({
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
        	var pageContent = $('.page-content');
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
                    enfermedad: {
                        required: true
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
                    success.hide();
                    error.hide();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                    getEnfermedades();
                }
            });
            
            function getEnfermedades() {
    			App.blockUI(pageContent, false);
    			$.getJSON(parametros.reportUrl, {
    				comunidad : $('#comunidad').val(),enfermedad : $('#enfermedad').val(),
    				ajax : 'true'
    			}, function(data) {
    				title = $('#enfermedad').val() + ' - ' + $('#comunidad option:selected').text();
    				var d = new Date();
                    fecha=d.toLocaleString(parametros.language);
    				var table1 = $('#resultados').dataTable( {  
    	                "aoColumns" : [null,null,null,null,null,null,null,null,null],
    	                bFilter: false, bInfo: true, bPaginate: true, bDestroy: true,
    	                "aLengthMenu": [[5, 10, 15, 20, -1],[5, 10, 15, 20, "Todos"]], iDisplayLength: 10});
    				var tt = new $.fn.dataTable.TableTools( table1, {
    	            	"sSwfPath": parametros.dataTablesTTSWF,
    	            	"aButtons": [
    	            	                {
    	            	                    "sExtends":    "collection",
    	            	                    "sButtonText": parametros.exportar,
    	            	                    "aButtons": [
    	            	                                 {
    	            	                                     "sExtends": "csv",
    	            	                                     "sFileName": title+"-*.csv",
    	            	                                     "sTitle": parametros.heading,
    	            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' }
    	            	                                 },
    	            	                                 {
    	            	                                     "sExtends": "pdf",
    	            	                                     "sFileName": title+"-*.pdf",
    	            	                                     "sTitle": parametros.heading,
    	            	                                     "sPdfMessage": title + ' - ' + fecha,
    	            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
    	            	                                     "sPdfOrientation": "landscape"
    	            	                                 }
    	            	                                 ]
    	            	                }
    	            	            ]
    	            } );
    				$( tt.fnContainer() ).insertBefore('div.table-toolbar1');
    	        	jQuery('#resultados_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
    	        	table1.fnClearTable();
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					var d = new Date(data[i].persona.fechaNacimiento);
    					var e = new Date(data[i].fechaOcurrencia);
						table1.fnAddData(
    							[data[i].persona.familia.comunidad.nombre, data[i].persona.familia.direccion, data[i].persona.nombres,
    							 data[i].persona.primerApellido,data[i].persona.segundoApellido,d.yyyymmdd(),data[i].persona.sexo.valor,e.yyyymmdd(),data[i].personaAtendio.valor]);
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