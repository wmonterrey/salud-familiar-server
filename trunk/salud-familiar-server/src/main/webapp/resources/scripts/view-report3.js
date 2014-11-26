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
            		$('#site_statistics_loading').show();
                    $('#site_statistics_content').hide();
                    $('#site_statistics_loading_2').show();
                    $('#site_statistics_content_2').hide();
                    
                    if ($('#area option:selected').val() == "HSF_AREAS|CENTRAL"){
    					title = parametros.summaryNac;
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|SILAIS"){
    					title = parametros.summary+' '+$('#silais option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|UNI"){
    					title = parametros.summary+' '+$('#unidad option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|SECTOR"){
    					title = parametros.summary+' '+$('#sector option:selected').text();
    				}
    				else if ($('#area option:selected').val() == "HSF_AREAS|COMU"){
    					title = parametros.summary+' '+$('#comunidad option:selected').text();
    				}
                    var d = new Date();
                    fecha=d.toLocaleString(parametros.language);
                    var table1 = $('#consolidado').dataTable( {  
    	                "aoColumns" : [null,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }
    	                ,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }],bFilter: false, bInfo: true, bPaginate: true, bDestroy: true,
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
    	            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
    	            	                                     "mColumns": [ 0, 1, 2, 3, 4, 5, 6]
    	            	                                 },
    	            	                                 {
    	            	                                     "sExtends": "pdf",
    	            	                                     "sFileName": title+"-*.pdf",
    	            	                                     "sTitle": parametros.heading,
    	            	                                     "sPdfMessage": title + ' - ' + fecha,
    	            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
    	            	                                     "mColumns": [ 0, 1, 2, 3, 4, 5, 6],
    	            	                                     "sPdfOrientation": "landscape",
    	            	                                 }
    	            	                                 ]
    	            	                }
    	            	            ]
    	            } );
    	        	
    	        	$( tt.fnContainer() ).insertBefore('div.table-toolbar1');
    	        	jQuery('#consolidado_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
    	        	table1.fnClearTable();
    	        	familias = [];
        			famEstimadas = [];
    				famDispensa = [];
    				personas = [];
    				perDispensa = [];
    				porcFamilias = [];
    				areas = [];
    				sumFamilias = 0;
    				sumFamEstimadas = 0;
    				sumFamDispensa = 0;
    				sumPersonas = 0;
    				sumPerDispensa = 0;
    				porcFam=0;
    				porcFamDisp=0;
    				porcPerDisp=0;
            		if (data == '' || data == null){
    					toastr.error(parametros.noResults);
    					nParametrosKnob = {knob1:0,knob2:0,knob3:0,knob4:0,knob5:0,knob6:0,
            					knob7:0,knob8:0,knob9:0};
            			ViewReport.initKnowElements(nParametrosKnob);
    				}
            		else{
        				var len = data.length;
        				for ( var i = 0; i < len; i++) {
    						table1.fnAddData(
        							[data[i].nombre, data[i].viviendas, data[i].familias, data[i].familiasEst, data[i].familiasDisp, data[i].personas, data[i].personasDisp]);
    						areas.push([data[i].nombre]);
    						familias.push([data[i].nombre, data[i].familias]);
    						porcFams = (Math.round(data[i].familias / data[i].familiasEst * 100 * 100)/100);
    						porcFamilias.push([data[i].nombre, porcFams]);
    						sumFamilias = sumFamilias + parseInt(data[i].familias);
    						famEstimadas.push([data[i].nombre, data[i].familiasEst]);
    						sumFamEstimadas = sumFamEstimadas + parseInt(data[i].familiasEst);
    						famDispensa.push([data[i].nombre, data[i].familiasDisp]);
    						sumFamDispensa = sumFamDispensa + parseInt(data[i].familiasDisp);
    						personas.push([data[i].nombre, data[i].personas]);
    						sumPersonas  = sumPersonas + parseInt(data[i].personas);
    						perDispensa.push([data[i].nombre, data[i].personasDisp]);
    						sumPerDispensa  = sumPerDispensa + parseInt(data[i].personasDisp);
        				}
            			nParametrosChart = {areas: areas,familias: familias, porcFamilias:porcFamilias,famDispensa: famDispensa, famEstimadas: famEstimadas
            					, sumFamilias: sumFamilias, sumFamDispensa: sumFamDispensa, sumFamEstimadas: sumFamEstimadas, 
            					famEst: parametros.famEst, famDisp: parametros.famDisp, famVis: parametros.famVis, title:title, fecha:fecha, titleApp: parametros.titleApp
            						, heading: parametros.heading,percent:parametros.percent};
            			ViewReport.initCharts(nParametrosChart);
            			porcFam = (sumFamilias / sumFamEstimadas) * 100;
            			porcFamDisp = (sumFamDispensa / sumFamilias) * 100;
            			porcPerDisp = (sumPerDispensa / sumPersonas) * 100;
            			nParametrosKnob = {knob1:porcFam,knob2:porcFamDisp,knob3:porcPerDisp,knob4:sumFamilias,knob5:sumFamEstimadas,knob6:sumFamDispensa,
            					knob7:sumFamilias,knob8:sumPerDispensa,knob9:sumPersonas};
            			ViewReport.initKnowElements(nParametrosKnob);
            		}
        			App.unblockUI(pageContent);
        		})
        		.fail(function() {
        		    alert( "error" );
        		    App.unblockUI(pageContent);
        		});
        		App.unblockUI(pageContent);
        	} 
            
            Date.prototype.ddmmyyyyy = function() {         
                
                var yyyy = this.getFullYear().toString();                                    
                var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
                var dd  = this.getDate().toString();             
                                    
                return (dd[1]?dd:"0"+dd[0]) + '-' + (mm[1]?mm:"0"+mm[0])+ '-' +yyyy;
            };
        },
        
        initKnowElements : function (parametersKnob) {
            //knob does not support ie8 so skip it
            if (!jQuery().knob || App.isIE8()) {
                return;
            }
            
            if ($(".knobify").size() > 0) {
            	$(".knobify").knob({
                    readOnly: true,
                    skin: "tron",
                    'width': 100,
                    'height': 100,
                    'dynamicDraw': true,
                    'thickness': 0.2,
                    'tickColorizeValues': true,
                    'skin': 'tron',
                    draw: function () {
                        // "tron" case
                        if (this.$.data('skin') == 'tron') {

                            var a = this.angle(this.cv) // Angle
                            ,
                                sa = this.startAngle // Previous start angle
                                ,
                                sat = this.startAngle // Start angle
                                ,
                                ea // Previous end angle
                                ,
                                eat = sat + a // End angle
                                ,
                                r = 1;

                            this.g.lineWidth = this.lineWidth;

                            this.o.cursor && (sat = eat - 0.3) && (eat = eat + 0.3);

                            if (this.o.displayPrevious) {
                                ea = this.startAngle + this.angle(this.v);
                                this.o.cursor && (sa = ea - 0.3) && (ea = ea + 0.3);
                                this.g.beginPath();
                                this.g.strokeStyle = this.pColor;
                                this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
                                this.g.stroke();
                            }

                            this.g.beginPath();
                            this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
                            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
                            this.g.stroke();

                            this.g.lineWidth = 2;
                            this.g.beginPath();
                            this.g.strokeStyle = this.o.fgColor;
                            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
                            this.g.stroke();

                            return false;

                        }
                    }
                });
            }
            
            $('#knob1')
            .val(parametersKnob.knob1)
            .trigger('change');
            
            $('#knob2')
            .val(parametersKnob.knob2)
            .trigger('change');
            
            $('#knob3')
            .val(parametersKnob.knob3)
            .trigger('change');
            
            $('#knob1num').html(parametersKnob.knob4);
            $('#knob2num').html(parametersKnob.knob5);
            $('#knob3num').html(parametersKnob.knob6);
            $('#knob4num').html(parametersKnob.knob7);
            $('#knob5num').html(parametersKnob.knob8);
            $('#knob6num').html(parametersKnob.knob9);
            
        },
        
        initCharts: function (parametrosChart) {
        	var pageContent = $('.page-content');
        	App.blockUI(pageContent, false);
            if (!jQuery.plot) {
                return;
            }
            $('#consolidado-title').html("<h4>"+ parametrosChart.titleApp +"</h4><h5>"+ parametrosChart.heading +" - "+ parametrosChart.title +"</h5>");
            $('#consolidado-foot').html("<h5>" +parametrosChart.fecha +"</h5>");
            $('#consolidado-title-2').html("<h4>"+ parametrosChart.titleApp +"</h4><h5>"+ parametrosChart.heading +" - "+ parametrosChart.title +"</h5>");
            $('#consolidado-foot-2').html("<h5>" +parametrosChart.fecha +"</h5>");
            if ($('#site_statistics').size() != 0) {

                $('#site_statistics_loading').hide();
                $('#site_statistics_content').show();

    			plot = $.plot("#site_statistics", [{
    				data : parametrosChart.familias,
    				label : parametrosChart.famVis+'-('+parametrosChart.sumFamilias+')'
    			}, {
                    data: parametrosChart.famDispensa,
                    label: parametrosChart.famDisp+'-('+parametrosChart.sumFamDispensa+')'
                }], {
    				series : {
    					bars : {
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
    				},
    				yaxis: {
                        ticks: 11,
                        tickDecimals: 0
                    },
    				colors: ["#37b7f3", "#52e136"]
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
    									'<div class="date">'+parametrosChart.areas[x]+'</div>'+
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
            if ($('#site_statistics_2').size() != 0) {

                $('#site_statistics_loading_2').hide();
                $('#site_statistics_content_2').show();

    			plot = $.plot("#site_statistics_2", [{
    				data : parametrosChart.porcFamilias,
    				label : '% ' + parametrosChart.percent
    			}], {
    				series : {
    					bars : {
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
    				},
    				yaxis: {
                        ticks: 11,
                        tickDecimals: 0
                    },
    				colors: ["#37b7f3", "#52e136"]
    			});
    	
    			$('<div id="tooltip" class="chart-tooltip"><\/div>').css({
                    position: 'absolute',
                    display: 'none',
                    border: '0px solid #ccc',
                    padding: '2px 6px',
                    'background-color': '#fff',
                }).appendTo("body").fadeIn(200);
    	
    			$("#site_statistics_2").bind("plothover",
    					function(event, pos, item) {
    	
    						/*if ($("#enableTooltip:checked").length > 0) {*/
    						if (item) {
    							var x = item.datapoint[0], y = item.datapoint[1];
    							$("#tooltip").html(
    									'<div class="date">'+parametrosChart.areas[x]+'</div>'+
    									'<div class="label label-warning">'+y+'%</div>')
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