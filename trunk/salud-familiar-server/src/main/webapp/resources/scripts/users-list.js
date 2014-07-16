var ListaUsuarios = function () {

    return {

        //main function to initiate the module
        init: function () {

           $('#lista_usuarios').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "Todos"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sPaginationType": "bootstrap",
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#lista_usuarios_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            jQuery('#lista_usuarios_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            jQuery('#lista_usuarios_1_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialize select2 dropdown
        }

    };

}();