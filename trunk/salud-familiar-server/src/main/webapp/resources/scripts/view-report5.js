var ViewReport = function () {
	
	var handleSelect2 = function () {
		$("#area").select2({
        });
        $("#silais").select2({
        });
        $("#municipio").select2({
        });
        $("#unidad").select2({
        });
        $("#sector").select2({
        });
        $("#comunidad").select2({
        });
   };

    return {
        //main function to initiate the module
        init: function (parametros) {
        	var form1 = $('#parameters_form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            var pageContent = $('.page-content');
            var title = "";
            var fecha = "";
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	area: {
                        required: true
                    },
                    silais: {
                    	required: {
                            depends: function(element){
                                return $("#area option:selected").val()=="HSF_AREAS|SILAIS";
                            }
                        }
                    },
                    unidad: {
                    	required: {
                            depends: function(element){
                                return $("#area option:selected").val()=="HSF_AREAS|UNI";
                            }
                        }
                    },
                    sector: {
                    	required: {
                            depends: function(element){
                                return $("#area option:selected").val()=="HSF_AREAS|SECTOR";
                            }
                        }
                    },
                    comunidad: {
                    	required: {
                            depends: function(element){
                                return $("#area option:selected").val()=="HSF_AREAS|COMU";
                            }
                        }
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    App.scrollTo(error1, -200);
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

                submitHandler: function (form) {
                    success1.hide();
                    error1.hide();
                    getData();
                }
            });
            handleSelect2();
            
            $('#area').change(
            		function() {
        				handleSelect2();
            			if ($('#area option:selected').val() == "HSF_AREAS|CENTRAL"){
            				$('#silais-div').hide();
            				$('#muni-div').hide();
            				$('#unidad-div').hide();
            				$('#sector-div').hide();
            				$('#comunidad-div').hide();
            			}
            			else if ($('#area option:selected').val() == "HSF_AREAS|SILAIS"){
            				$('#silais-div').show();
            				$('#muni-div').hide();
            				$('#unidad-div').hide();
            				$('#sector-div').hide();
            				$('#comunidad-div').hide();
            			}
            			else if ($('#area option:selected').val() == "HSF_AREAS|UNI"){
            				$('#silais-div').show();
            				$('#muni-div').show();
            				$('#unidad-div').show();
            				$('#sector-div').hide();
            				$('#comunidad-div').hide();
            			}
            			else if ($('#area option:selected').val() == "HSF_AREAS|SECTOR"){
            				$('#silais-div').show();
            				$('#muni-div').show();
            				$('#unidad-div').hide();
            				$('#sector-div').show();
            				$('#comunidad-div').hide();
            			}
            			else if ($('#area option:selected').val() == "HSF_AREAS|COMU"){
            				$('#silais-div').show();
            				$('#muni-div').show();
            				$('#unidad-div').hide();
            				$('#sector-div').show();
            				$('#comunidad-div').show();
            			}
                    });
            
            $('#silais').change(
            		function() {
            			App.blockUI(pageContent, false);
            			$.getJSON(parametros.opcMuniUrl, {
            				entidadId : $('#silais').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#municipio").select2('data',null);
            				$("#unidad").select2('data',null);
            				$("#sector").select2('data',null);
            				$("#comunidad").select2('data',null);
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
        				$("#sector").select2('data',null);
        				$("#unidad").select2('data',null);
        				$("#comunidad").select2('data',null);
            			$.getJSON(parametros.opcSectUrl, {
            				municipioId : $('#municipio').val(),
            				ajax : 'true'
            			}, function(data) {
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
            			$.getJSON('/hsf/opciones/unidades', {
            				muniId : $('#municipio').val(),
            				ajax : 'true'
            			}, function(data) {
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].codigo + '">'
            							+ data[i].nombre + '</option>';
            				}
            				html += '</option>';
            				$('#unidad').html(html);
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
            
            function getData() {
            	App.blockUI(pageContent, false);
            	$.getJSON(parametros.reportUrl, $('#parameters_form').serialize(), function(data) {
                    
                    if ($('#area option:selected').val() == "HSF_AREAS|CENTRAL"){
    					title = parametros.cronicos + ' - ' + parametros.summaryNac;
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|SILAIS"){
    					title = parametros.cronicos + ' - ' + parametros.summary+' '+$('#silais option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|UNI"){
    					title = parametros.cronicos + ' - ' + parametros.summary+' '+$('#unidad option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|SECTOR"){
    					title = parametros.cronicos + ' - ' + parametros.summary+' '+$('#sector option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|COMU"){
    					title = parametros.cronicos + ' - ' + parametros.summary+' '+$('#comunidad option:selected').text();
    				}
                    var d = new Date();
                    fecha=d.toLocaleString(parametros.language);
                    var table1 = $('#consolidado').dataTable( {  
    	                "aoColumns" : [null,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }
    	                ,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }
    	                ,{sClass: "aw-right" },{sClass: "aw-right" }],bFilter: false, bInfo: true, bPaginate: true, bDestroy: true,
    	                "aLengthMenu": [[5, 10, 15, 20, -1],[5, 10, 15, 20, "Todos"]], iDisplayLength: 15});
    	        	
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
    	        	jQuery('#consolidado_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
    	        	table1.fnClearTable();
            		if (data == '' || data == null){
    					toastr.error(parametros.noResults);
    				}
            		else{
            			for (var row in data) {
    						table1.fnAddData(
        							[data[row][0], data[row][1], data[row][2], data[row][3]
        							, data[row][4], data[row][5], data[row][6]
        							, data[row][7], data[row][8]]);
            			}
            		}
        			App.unblockUI(pageContent);
        		})
        		.fail(function() {
        		    alert( "error" );
        		    App.unblockUI(pageContent);
        		});
        		App.unblockUI(pageContent);
        	} 
        }
    };

}();