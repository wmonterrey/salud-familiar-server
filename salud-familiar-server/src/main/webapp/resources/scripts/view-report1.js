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
       
       $("#desde").inputmask("d/m/y", {
           "placeholder": "dd/mm/yyyy"
       }); //multi-char placeholder
       
       $("#hasta").inputmask("d/m/y", {
           "placeholder": "dd/mm/yyyy"
       }); //multi-char placeholder
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
                    },
                    desde: {
                    	required: true,
            		},
            		hasta: {
            			required: true,
            		},
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
            handleDatePickers();
            handleInputMasks();
            
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
            		
            		$('#site_statistics_loading').show();
                    $('#site_statistics_content').hide();
                    
                    if ($('#area option:selected').val() == "HSF_AREAS|CENTRAL"){
    					title = parametros.visitsDay;
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|SILAIS"){
    					title = parametros.visitsDay+' '+$('#silais option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|UNI"){
    					title = parametros.visitsDay+' '+$('#unidad option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|SECTOR"){
    					title = parametros.visitsDay+' '+$('#sector option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|COMU"){
    					title = parametros.visitsDay+' '+$('#comunidad option:selected').text();
    				}
                    var d = new Date();
                    fecha=d.toLocaleString(parametros.language);
                    var pdfMes = title + ', ' +$('#desde').val() +' - '+$('#hasta').val();
                    
                    var table1 = $('#visitas_dia').dataTable( {  
                        "aoColumns" : [null,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }],bFilter: false, bInfo: true, bPaginate: true, bDestroy: true,
                        "aLengthMenu": [[5, 10, 15, 20, -1],[5, 10, 15, 20, "Todos"]], iDisplayLength: 5});
                	
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
    	            	                                     "sPdfMessage": pdfMes,
                    	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
                    	                                     "sPdfSize": "letter"
                    	                                 }
                    	                                 ]
                    	                }
                    	            ]
                    } );
                	
                	$( tt.fnContainer() ).insertBefore('div.table-toolbar1');
                	jQuery('#visitas_dia_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
                	table1.fnClearTable();
            		if (data == ''){
    					toastr.error(parametros.noResults);
    				}
            		else{
            			iniciales = [];
        				segs = [];
        				totales = [];
        				fechas = [];
        				sumInicial = 0;
        				sumSeg = 0;
        				sumTotal = 0;
            			for (var row in data) {
            				var d = new Date(data[row][0]);
	        				table1.fnAddData(
	    							[d.yyyymmdd(), data[row][1], data[row][2], data[row][3]]);
	        				
	        				fechas.push([d.yyyymmdd()]);
	    					iniciales.push([d.yyyymmdd(), data[row][1]]);
	    					sumInicial = sumInicial + parseInt(data[row][1]);
	    					segs.push([d.yyyymmdd(), data[row][2]]);
	    					sumSeg = sumSeg + parseInt(data[row][2]);
	    					totales.push([d.yyyymmdd(), data[row][3]]);
	    					sumTotal = sumTotal + parseInt(data[row][3]);
	        			}
            			nParametrosChart = {fechas: fechas,iniciales: iniciales, segs: segs, totales: totales
            					, sumInicial: sumInicial, sumSeg: sumSeg, sumTotal: sumTotal, title:title, fecha:fecha, titleApp: parametros.titleApp
        						, heading: parametros.heading,
            					initial: parametros.initial, followup: parametros.followup, total: parametros.total};
            			ViewReport.initCharts(nParametrosChart);
            		}
        			App.unblockUI(pageContent);
        		})
        		.fail(function() {
        		    alert( "error" );
        		    App.unblockUI(pageContent);
        		});
        		App.unblockUI(pageContent);
        	} 
            
            Date.prototype.yyyymmdd = function() {         
                
                var yyyy = this.getFullYear().toString();                                    
                var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
                var dd  = this.getDate().toString();             
                                    
                return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
            };
        },
        
        initCharts: function (parametrosChart) {
        	var pageContent = $('.page-content');
        	App.blockUI(pageContent, false);
            if (!jQuery.plot) {
                return;
            }
            $('#consolidado-title').html("<h4>"+ parametrosChart.titleApp +"</h4><h5>"+ parametrosChart.heading +", "+ parametrosChart.title +", "+ $('#desde').val() +" - "+ $('#hasta').val() +"</h5>");
            $('#consolidado-foot').html("<h5>" +parametrosChart.fecha +"</h5>");
            
            if ($('#site_statistics').size() != 0) {

                $('#site_statistics_loading').hide();
                $('#site_statistics_content').show();

    			plot = $.plot("#site_statistics", [{
                    data: parametrosChart.totales,
                    label: parametrosChart.total+'-('+parametrosChart.sumTotal+')'
                }, {
    				data : parametrosChart.iniciales,
    				label : parametrosChart.initial+'-('+parametrosChart.sumInicial+')'
    			}, {
                    data: parametrosChart.segs,
                    label: parametrosChart.followup+'-('+parametrosChart.sumSeg+')'
                }], {
    				series : {
    					lines : {
    						show : true,
    						fill : true,
    						fillColor: {
                                colors: [{
                                        opacity: 0.05
                                    }, {
                                        opacity: 0.01
                                    }
                                ]
                            }
    					},
    					points: {
                            show: true
                        },
                        shadowSize: 2
    				},
    				grid : {
    					hoverable: true,
                        clickable: true,
                        tickColor: "#eee",
                        borderWidth: 0
    				},
    				xaxis : {
    					mode : "categories",
    					ticks: 11,
                        tickDecimals: 0
    				},
    				yaxis: {
                        ticks: 11,
                        tickDecimals: 0
                    },
    				colors: ["#d12610", "#37b7f3", "#52e136"]
    			});
    	
    			$('<div id="tooltip" class="chart-tooltip"><\/div>').css({
                    position: 'absolute',
                    display: 'none',
                    border: '0px solid #ccc',
                    padding: '2px 6px',
                    'background-color': '#fff',
                }).appendTo("body").fadeIn(200);
    	
    			$("#site_statistics").bind("plothover",
    					function(event, pos, item) {
    	
    						/*if ($("#enableTooltip:checked").length > 0) {*/
    						if (item) {
    							var x = item.datapoint[0], y = item.datapoint[1];
    							$("#tooltip").html(
    									'<div class="date">'+parametrosChart.fechas[x]+'</div>'+
    									'<div class="label label-warning">'+y+'</div>')
    									.css({
    								top : item.pageY + 5,
    								left : item.pageX + 5
    							}).fadeIn(200);
    						} else {
    							$("#tooltip").hide();
    						}
    						/*}*/
    					});
    		
    		}
            App.unblockUI(pageContent);
    	}
    };

}();