var EditUser = function () {
	
	var handleMultiSelect = function () {
        $('#authorities').multiSelect();
    };

    var handleValidation = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var form1 = $('#edit-user-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "No space");

            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	username: {
                        minlength: 5,
                        maxlength: 50,
                        noSpace:true,
                        required: true
                    },
                    completeName: {
                        minlength: 5,
                        maxlength: 250,
                        required: true
                    },
                    email: {
                        required: true,
                        minlength: 3,
                        maxlength: 100,
                        noSpace:true,
                        email: true
                    },
                    nivel: {
                        required: true,
                    },
                    entidad: {
                    	required: {
                            depends: function(element){
                                return $("#nivel option:selected").val()=="HSF_NIVELES|SILAIS";
                            }
                        }
                    },
                    unidad: {
                    	required: {
                            depends: function(element){
                                return $("#nivel option:selected").val()=="HSF_NIVELES|UNIDAD";
                            }
                        }
                    },
                    authorities: {
                        required: true
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
                    success1.show();
                    error1.hide();
                    editUser();
                }
            });

    };

    return {
        //main function to initiate the module
        init: function (parametros) {
        	
            handleValidation();
            handleMultiSelect();
            
            if ($('#nivel option:selected').val() == "HSF_NIVELES|SILAIS"){
				$('#entidad-div').show();
			}
			else if ($('#nivel option:selected').val() == "HSF_NIVELES|UNIDAD"){
				$('#unidad-div').show();
			}
            
            $("#nivel").select2({
            	placeholder: parametros.selectMessage
            });
            
            $("#entidad").select2({
            	placeholder: parametros.selectMessage
            });
            
            $("#municipio").select2({
            	placeholder: parametros.selectMessage
            });
            
            $("#unidad").select2({
            	placeholder: parametros.selectMessage
            });
            
            $('#nivel').change(
            		function() {
            			$("#entidad").select2("val", "");
            			$("#municipio").select2("val", "");
        				$("#unidad").select2("val", "");
            			if ($('#nivel option:selected').val() == "HSF_NIVELES|CENTRAL"){
            				$('#entidad-div').hide();
            				$('#municipio-div').hide();
            				$('#unidad-div').hide();
            			}
            			else if ($('#nivel option:selected').val() == "HSF_NIVELES|SILAIS"){
            				$('#entidad-div').show();
            				$('#municipio-div').hide();
            				$('#unidad-div').hide();
            			}
            			else if ($('#nivel option:selected').val() == "HSF_NIVELES|UNIDAD"){
            				$('#entidad-div').show();
            				$('#municipio-div').show();
            				$('#unidad-div').show();
            			}
                    });
            
            $('#entidad').change(
            		function() {
            			$.getJSON('/hsf/opciones/municipios', {
            				entidadId : $('#entidad').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#municipio").select2("val", "");
            				$("#unidad").select2("val", "");
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
            			$.getJSON('/hsf/opciones/unidades', {
            				muniId : $('#municipio').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#unidad").select2("val", "");
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
    	    
    	    $(document).on('keypress','form input',function(event)
    		{                
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
        }
    };

}();