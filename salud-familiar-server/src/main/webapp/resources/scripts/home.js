var Index = function () {


    return {

        //main function
        init: function () {
        	
        },
        
        initCharts: function (parametrosChart) {
        	var pageContent = $('.page-content');
        	App.blockUI(pageContent, false);
            if (!jQuery.plot) {
                return;
            }
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
            
            if ($('#site_statistics_2').size() != 0) {

                $('#site_statistics_2_loading').hide();
                $('#site_statistics_2_content').show();

    			plot = $.plot("#site_statistics_2", [ {
    				data : parametrosChart.atotales,
    				label : parametrosChart.total+'-('+parametrosChart.sumTotal+')'
    			}, {
                    data: parametrosChart.ainiciales,
                    label: parametrosChart.initial+'-('+parametrosChart.sumInicial+')'
                }, {
                    data: parametrosChart.asegs,
                    label: parametrosChart.followup+'-('+parametrosChart.sumSeg+')'
                }], {
    				series : {
    					bars : {
    						show : true,
    						fill : true,
    					},
    					points: {
                            show: true
                        }
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
    				colors: ["#d12610", "#37b7f3", "#52e136"]
    			});
    			
    			$("#site_statistics_2").bind("plothover",
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
            App.unblockUI(pageContent);
    	},
        
        initDashboardDaterange: function (parametrosDateRange) {
        	var table1 = $('#visitas_dia').dataTable( {  
                "aoColumns" : [null,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }],bFilter: false, bInfo: true, bPaginate: true, 
                "aLengthMenu": [[5, 10, 15, 20, -1],[5, 10, 15, 20, "Todos"]], iDisplayLength: 5});
        	
        	var tt = new $.fn.dataTable.TableTools( table1, {
            	"sSwfPath": parametrosDateRange.dataTablesTTSWF,
            	"aButtons": [
            	                {
            	                    "sExtends":    "collection",
            	                    "sButtonText": parametrosDateRange.exportar,
            	                    "aButtons": [
            	                                 {
            	                                     "sExtends": "csv",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1, 2, 3]
            	                                 },
            	                                 {
            	                                     "sExtends": "pdf",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1, 2, 3],
            	                                     "sPdfOrientation": "landscape",
            	                                 }
            	                                 ]
            	                }
            	            ]
            } );
        	
        	$( tt.fnContainer() ).insertBefore('div.table-toolbar1');
        	jQuery('#visitas_dia_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
        	
        	var table2 = $('#visitas_area').dataTable( {  
                "aoColumns" : [null,{sClass: "aw-right" },{sClass: "aw-right" },{sClass: "aw-right" }],bFilter: false, bInfo: true, bPaginate: true, 
                "aLengthMenu": [[5, 10, 15, 20, -1],[5, 10, 15, 20, "Todos"]], iDisplayLength: 5});
        	
        	var tt2 = new $.fn.dataTable.TableTools( table2, {
            	"sSwfPath": parametrosDateRange.dataTablesTTSWF,
            	"aButtons": [
            	                {
            	                    "sExtends":    "collection",
            	                    "sButtonText": parametrosDateRange.exportar,
            	                    "aButtons": [
            	                                 {
            	                                     "sExtends": "csv",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1, 2, 3]
            	                                 },
            	                                 {
            	                                     "sExtends": "pdf",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1, 2, 3],
            	                                     "sPdfOrientation": "landscape",
            	                                 }
            	                                 ]
            	                }
            	            ]
            } );
        	
        	$( tt2.fnContainer() ).insertBefore('div.table-toolbar2');
        	jQuery('#visitas_area_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown

            $('#dashboard-report-range').daterangepicker({
                opens: (App.isRTL() ? 'right' : 'left'),
                startDate: moment().subtract('days', 29),
                endDate: moment(),
                minDate: '01/01/2009',
                maxDate: moment(),
                dateLimit: {
                    days: 91
                },
                showDropdowns: false,
                showWeekNumbers: false,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    'Hoy': [moment(), moment()],
                    'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Ultimos 7 d�as': [moment().subtract('days', 6), moment()],
                    'Ultimos 30 d�as': [moment().subtract('days', 29), moment()],
                    'Este mes': [moment().startOf('month'), moment().endOf('month')],
                    'Mes pasado': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                buttonClasses: ['btn'],
                applyClass: 'btn-success',
                cancelClass: 'btn-default',
                format: 'DD/MM/YYYY',
                separator: ' a ',
                locale: {
                    applyLabel: parametrosDateRange.applyLabel,
                    fromLabel: parametrosDateRange.fromLabel,
                    toLabel: parametrosDateRange.toLabel,
                    customRangeLabel: parametrosDateRange.customRangeLabel,
                    daysOfWeek: [parametrosDateRange.Su, parametrosDateRange.Mo, parametrosDateRange.Tu, parametrosDateRange.We, parametrosDateRange.Th, parametrosDateRange.Fr, parametrosDateRange.Sa],
                    monthNames: [parametrosDateRange.january, parametrosDateRange.february, parametrosDateRange.march, parametrosDateRange.april, parametrosDateRange.may,
                                 parametrosDateRange.june, parametrosDateRange.july, parametrosDateRange.august, parametrosDateRange.september, parametrosDateRange.october, parametrosDateRange.november, parametrosDateRange.december],
                    firstDay: 1
                }
            },
            function (start, end) {
                console.log("Callback has been called!"+start+end);
                $('#dashboard-report-range span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                getTable(start.format("YYYY-MM-DD"), end.format("YYYY-MM-DD"));
                
            }
            );
            
            $('#dashboard-report-range span').html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
            $('#dashboard-report-range').show();
            
            function getTable(start, end) {
            	var pageContent = $('.page-content');
            	App.blockUI(pageContent, false);
        		$.getJSON(parametrosDateRange.dashUrl, {
        			start : start,
        			end : end,
    				ajax : 'true'
    			},function(data) {
    				table1.fnClearTable();
    				table2.fnClearTable();
    				iniciales = [];
    				segs = [];
    				totales = [];
    				ainiciales = [];
    				asegs = [];
    				atotales = [];
    				fechas = [];
    				areas = [];
    				sumInicial = 0;
    				sumSeg = 0;
    				sumTotal = 0;
        			for (var row in data.lista1) {
    					table1.fnAddData(
    							[data.lista1[row][0], data.lista1[row][1], data.lista1[row][2], data.lista1[row][3]]);
    					fechas.push([data.lista1[row][0]]);
    					iniciales.push([data.lista1[row][0], data.lista1[row][1]]);
    					sumInicial = sumInicial + parseInt(data.lista1[row][1]);
    					segs.push([data.lista1[row][0], data.lista1[row][2]]);
    					sumSeg = sumSeg + parseInt(data.lista1[row][2]);
    					totales.push([data.lista1[row][0], data.lista1[row][3]]);
    					sumTotal = sumTotal + parseInt(data.lista1[row][3]);
        			}
        			for (var row in data.lista2) {
    					table2.fnAddData(
    							[data.lista2[row][0], data.lista2[row][1], data.lista2[row][2], data.lista2[row][3]]);
    					areas.push([data.lista2[row][0]]);
    					ainiciales.push([data.lista2[row][0], data.lista2[row][1]]);
    					asegs.push([data.lista2[row][0], data.lista2[row][2]]);
    					atotales.push([data.lista2[row][0], data.lista2[row][3]]);
        			}
        			nParametrosChart = {fechas: fechas,iniciales: iniciales, segs: segs, totales: totales,ainiciales: ainiciales, asegs: asegs, atotales: atotales, areas: areas
        					, sumInicial: sumInicial, sumSeg: sumSeg, sumTotal: sumTotal, initial: parametrosDateRange.initial, followup: parametrosDateRange.followup, total: parametrosDateRange.total};
        			Index.initCharts(nParametrosChart);
        		}).fail(function() {
				    alert( "error" );
				    
				});
        	} 
        }
    };

}();