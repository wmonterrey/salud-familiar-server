var SearchDivPol = function () {
	
    var handleSelect2 = function () {
        $("#silais").select2({
        });
       
   };

    return {
        //main function to initiate the module
        init: function (parametros) {
        	var pageContent = $('.page-content');
            handleSelect2();
            
            var form = $('#search_form');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            
            var table1  = $('#lista_munis').DataTable({
    			"aoColumns" : [null,{sClass: "aw-right" },null],
                "aLengthMenu": [
                    [5, 10, 15, 20, -1],
                    [5, 10, 15, 20, "Todos"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sPaginationType": "bootstrap"
            });
            
            $(".pagination").click(function() {
            	$(".borrar").click(function(){
	            	$('#accionUrl').val($(this).data('id'));
	            	$('#titulo').html('<h2 class="modal-title">'+parametros.confirmar+'</h2>');
	            	$('#cuerpo').html('<h4 class="modal-title">'+parametros.deshabilitar+'</h4>');
	            	$('#basic').modal('show');
	             });
            });
            
    		
    		var tt = new $.fn.dataTable.TableTools( table1, {
            	"sSwfPath": "${dataTablesTTSWF}",
            	"aButtons": [
            	                {
            	                    "sExtends":    "collection",
            	                    "sButtonText": parametros.exportar,
            	                    "aButtons": [
            	                                 {
            	                                     "sExtends": "csv",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1 ]
            	                                 },
            	                                 {
            	                                     "sExtends": "pdf",
            	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
            	                                     "mColumns": [ 0, 1 ],
            	                                     "sPdfOrientation": "landscape",
            	                                 }
            	                                 ]
            	                }
            	            ]
            } );
    		 
    	    $( tt.fnContainer() ).insertBefore('div.table-toolbar');

            jQuery('#lista_munis_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            jQuery('#lista_munis_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            jQuery('#lista_munis_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialize select2 dropdown

                   
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //Datos generales
                	silais: {
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
                    table1.fnClearTable();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                    getMunis();
                }
            });
            
            $('#silais').change(
            		function() {
            			$('#lista_munis_new').removeAttr('disabled');
            			form.submit();
            		}
            	);	
            
            
            function getMunis() {
    			App.blockUI(pageContent, false);
    			$.getJSON(parametros.opcMuniUrl, {
    				entidadId : $('#silais').val(),
    				ajax : 'true'
    			}, function(data) {
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
						var editUrl = parametros.editUrl + '/'+data[i].famEstDivPolId;
						var delUrl = parametros.delUrl + '/'+data[i].famEstDivPolId;
						table1.fnAddData(
    							[data[i].municipio.nombre, data[i].famEstimadas,
    							 '<a href='+ editUrl + ' class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a>' +  
    							 '<a data-toggle="modal" data-id=' + delUrl + ' class="btn btn-default btn-xs borrar"><i class="fa fa-trash-o"></i></a>'] );
    				}
    				$(".borrar").click(function(){
    	            	$('#accionUrl').val($(this).data('id'));
    	            	$('#titulo').html('<h2 class="modal-title">'+parametros.confirmar+'</h2>');
    	            	$('#cuerpo').html('<h4 class="modal-title">'+parametros.deshabilitar+'</h4>');
    	            	$('#basic').modal('show');
    	             });
    				App.unblockUI(pageContent);
    			})
    			.fail(function() {
				    alert( "error" );
				    App.unblockUI(pageContent);
				});
            };
            
        }
    };

}();