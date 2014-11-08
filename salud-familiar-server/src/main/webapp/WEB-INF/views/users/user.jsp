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
<jsp:include page="../fragments/headTag.jsp" />
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
<jsp:include page="../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../fragments/bodyNavigation.jsp" />
<!-- BEGIN CONTENT -->
<c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
<c:set var="userDisabledLabel"><spring:message code="loginfailed.userDisabled" /></c:set>
<c:set var="userLockedLabel"><spring:message code="loginfailed.accountLocked" /></c:set>
<c:set var="userUnlockedLabel"><spring:message code="login.accountNonLocked" /></c:set>
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<jsp:include page="../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<c:set var="rolesString">
								<c:forEach var="rol" items="${user.authorities}">
									<spring:message code="${rol.authId.authority}" />
								</c:forEach>
					</c:set>
					<h3 class="page-title">
					<spring:message code="users.profile" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/users/profile" htmlEscape="true "/>"><spring:message code="users.profile" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<c:set var="exportar"><spring:message code="export" /></c:set>
			<spring:url value="/users/edit/" var="editUrl"/>
			<spring:url value="/users/chgpass/" var="chgpassUrl"/>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN FORM PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i><spring:message code="view" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
									
								</div>
								<div class="btn-group pull-right">
									<button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><spring:message code="actions" /> <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right">
										<li>
											<a href="${fn:escapeXml(editUrl)}"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</li>
										<li>
											<a href="${fn:escapeXml(chgpassUrl)}"><i class="fa fa-key"></i> <spring:message code="users.changepass" /></a>
										</li>
									</ul>
								</div>
							</div>
							<!-- BEGIN FORM-->
							<form class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section"><c:out value="${user.username}" /></h3>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.desc" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.completeName}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.email" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.email}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.nivel" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.nivel}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.entity" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.unidad.nombre}" /><c:out value="${user.entidad.nombre}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.enabled" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.enabled}">
																<spring:message code="yes" />
															</c:when>
															<c:otherwise>
																<spring:message code="no" />
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.notlocked" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.accountNonLocked}">
																<spring:message code="yes" />
															</c:when>
															<c:otherwise>
																<spring:message code="no" />
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.roles" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${rolesString}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.createdBy" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.createdBy}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.dateCreated" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.created}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.modifiedBy" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.modifiedBy}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.dateModified" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.modified}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.lastaccess" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.lastAccess}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="users.dateCredentials" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.lastCredentialChange}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END FORM PORTLET-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-key"></i><spring:message code="users.access" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar2">
								<div class="btn-group">
								</div>
							</div>
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="lista_accesos">
							<thead>
								<tr>
									<th><spring:message code="users.user" /></th>
									<th class="hidden-xs"><spring:message code="users.session" /></th>
									<th class="hidden-xs"><spring:message code="users.ipaddress" /></th>
									<th><spring:message code="users.logindate" /></th>
									<th><spring:message code="users.logoutdate" /></th>
									<th class="hidden-xs"><spring:message code="users.logouturl" /></th>
								</tr>
							</thead>
							<c:forEach items="${accesses}" var="acceso">
								<tr>
									<td><c:out value="${acceso.username}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.sessionId}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.remoteIpAddress}" /></td>
									<td><c:out value="${acceso.loginDate}" /></td>
									<td><c:out value="${acceso.logoutDate}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.logoutRefererUrl}" /></td>
								</tr>
							</c:forEach>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
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
<jsp:include page="../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../fragments/corePlugins.jsp" />
<jsp:include page="../fragments/bodyUtils.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.users").removeClass("users").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		
		var table  = $('#lista_accesos').DataTable({
            "aLengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "Todos"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 5,
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
		 
	    $( tt.fnContainer() ).insertBefore('div.table-toolbar2');

        jQuery('#lista_accesos_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
        jQuery('#lista_accesos_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
        jQuery('#lista_accesos_wrapper .dataTables_length select').select2({
            showSearchInput : false //hide search box with special css class
        }); // initialize select2 dropdown
	});

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>