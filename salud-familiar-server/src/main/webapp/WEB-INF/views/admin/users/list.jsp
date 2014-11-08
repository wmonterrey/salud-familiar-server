<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.css" var="dtbootcss" />
<link rel="stylesheet" href="${dtbootcss}"/>
<spring:url value="/resources/plugins/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
<link rel="stylesheet" href="${dtttcss}"/>
<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../../fragments/bodyNavigation.jsp" />
<!-- BEGIN CONTENT -->
<c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
<c:set var="userDisabledLabel"><spring:message code="loginfailed.userDisabled" /></c:set>
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<jsp:include page="../../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="users" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/" htmlEscape="true "/>"><spring:message code="users.list" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<c:set var="exportar"><spring:message code="export" /></c:set>
			<c:set var="habilitar"><spring:message code="enable.user" /></c:set>
			<c:set var="deshabilitar"><spring:message code="disable.user" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i><spring:message code="users.list" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
									<spring:url value="/admin/users/newUser"	var="newUser"/>
									<button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(newUser)}'" class="btn btn-success">
									<spring:message code="users.add" /> <i class="fa fa-plus"></i>
									</button>
								</div>
							</div>
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="lista_usuarios">
							<thead>
								<tr>
									<th><spring:message code="users.user" /></th>
									<th class="hidden-xs"><spring:message code="users.desc" /></th>
									<th class="hidden-xs"><spring:message code="users.email" /></th>
									<th><spring:message code="users.nivel" /></th>
									<th class="hidden-xs"><spring:message code="users.entity" /></th>
									<th><spring:message code="users.enabled" /></th>
									<th><spring:message code="users.notlocked" /></th>
									<th class="hidden-xs"><spring:message code="users.roles" /></th>
									<th><spring:message code="actions" /></th>
								</tr>
							</thead>
							<c:forEach items="${usuarios}" var="usuario">
								<tr>
									<spring:url value="/admin/users/{username}"
										var="usuarioUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/admin/users/edit/{username}"
										var="editUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/admin/users/habdes/disable1/{username}"
										var="disableUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/admin/users/habdes/enable1/{username}"
										var="enableUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/admin/users/chgpass/{username}"
										var="chgpassUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<td><c:out value="${usuario.username}" /></td>
									<td class="hidden-xs"><c:out value="${usuario.completeName}" /></td>
									<td class="hidden-xs"><c:out value="${usuario.email}" /></td>
									<td><c:out value="${usuario.nivel}" /></td>
									<td class="hidden-xs"><c:out value="${usuario.unidad.nombre}" /><c:out value="${usuario.entidad.nombre}" /></td>
									<c:choose>
										<c:when test="${usuario.enabled}">
											<td><span class="label label-success"><spring:message code="yes" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-danger"><spring:message code="no" /></span></td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${usuario.accountNonLocked}">
											<td><span class="label label-success"><spring:message code="yes" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-danger"><spring:message code="no" /></span></td>
										</c:otherwise>
									</c:choose>
									<td class="hidden-xs">
										<c:forEach var="rol" items="${usuario.authorities}">
											<spring:message code="${rol.authId.authority}" />
										</c:forEach></td>
									<td>
										<a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-default btn-xs"><i class="fa fa-search"></i></a>
										<a href="${fn:escapeXml(editUrl)}" class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a>
										<a href="${fn:escapeXml(chgpassUrl)}" class="btn btn-default btn-xs"><i class="fa fa-key"></i></a>
										<c:choose>
										<c:when test="${usuario.enabled}">
											<a data-toggle="modal" data-id= "${fn:escapeXml(disableUrl)}" class="btn btn-default btn-xs desact"><i class="fa fa-trash-o"></i></a>
										</c:when>
										<c:otherwise>
											<a data-toggle="modal" data-id= "${fn:escapeXml(enableUrl)}" class="btn btn-default btn-xs act"><i class="fa fa-check"></i></a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
					<div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
									<div id="titulo"></div>
								</div>
								<div class="modal-body">
									<input type=hidden id="accionUrl"/>
									<div id="cuerpo"></div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
									<button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>
	</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../../fragments/corePlugins.jsp" />
<jsp:include page="../../fragments/bodyUtils.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
        $("li.users").removeClass("users").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var table  = $('#lista_usuarios').DataTable({
            "aLengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "Todos"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 10,
            "sPaginationType": "bootstrap"
        });
		
		var tt = new $.fn.dataTable.TableTools( table, {
        	"sSwfPath": "${dataTablesTTSWF}",
        	"aButtons": [
        	                {
        	                    "sExtends":    "collection",
        	                    "sButtonText": "${exportar}",
        	                    "aButtons": [
        	                                 {
        	                                     "sExtends": "csv",
        	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
        	                                     "mColumns": [ 0, 1, 2, 3, 4 ]
        	                                 },
        	                                 {
        	                                     "sExtends": "pdf",
        	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
        	                                     "mColumns": [ 0, 1, 2, 3, 4 ],
        	                                     "sPdfOrientation": "landscape",
        	                                 }
        	                                 ]
        	                }
        	            ]
        } );
		 
	    $( tt.fnContainer() ).insertBefore('div.table-toolbar');

        jQuery('#lista_usuarios_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
        jQuery('#lista_usuarios_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
        jQuery('#lista_usuarios_wrapper .dataTables_length select').select2({
            showSearchInput : false //hide search box with special css class
        }); // initialize select2 dropdown
        
        if ("${usuarioHabilitado}"){
			toastr.success("${userEnabledLabel}", "${nombreUsuario}" );
		}
		if ("${usuarioDeshabilitado}"){
			toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
		}
        
        $(".act").click(function(){ 
        	$('#accionUrl').val($(this).data('id'));
        	$('#titulo').html('<h2 class="modal-title">'+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'</h2>');
        	$('#cuerpo').html('<h4>'+"${habilitar}"+'</h4>');
        	$('#basic').modal('show');
         });
        
        $(".desact").click(function(){ 
        	$('#accionUrl').val($(this).data('id'));
        	$('#titulo').html('<h2 class="modal-title">'+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'</h2>');
        	$('#cuerpo').html('<h4 class="modal-title">'+"${deshabilitar}"+'</h4>');
        	$('#basic').modal('show');
         });
	});
	
	function ejecutarAccion() {
		window.location.href = $('#accionUrl').val();		
	}
	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>