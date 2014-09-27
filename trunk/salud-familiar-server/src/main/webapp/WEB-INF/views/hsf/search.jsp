<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="select2css" />
<link rel="stylesheet" type="text/css" href="${select2css}"/>
<spring:url value="/resources/plugins/bootstrap-datepicker/css/datepicker.css" var="datepickercss" />
<link rel="stylesheet" type="text/css" href="${datepickercss}"/>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.css" var="dtbootcss" />
<link rel="stylesheet" href="${dtbootcss}"/>
<spring:url value="/resources/plugins/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
<link rel="stylesheet" href="${dtttcss}"/>
<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../fragments/bodyNavigation.jsp" />
<!-- BEGIN WRAPPER -->
<div class="page-content-wrapper">
	<!-- BEGIN CONTENT -->
	<div class="page-content">
		<!-- BEGIN PAGE HEADER-->
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->

				<ul class="page-breadcrumb breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
						<i class="fa fa-angle-right"></i> <a href="<spring:url value="/info/searchHsf" htmlEscape="true "/>"><spring:message code="hsf.search" /></a>
					</li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<c:set var="exportar"><spring:message code="export" /></c:set>
		<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
		<c:set var="processError"><spring:message code="process.error" /></c:set>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet" id="form_search">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-reorder"></i> <spring:message code="hsf.search" />
						</div>
						<div class="tools hidden-xs">
							<a href="javascript:;" class="collapse"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
					</div>
					<div class="portlet-body form">
						<!-- START FORM -->
						<form action="#" class="form-horizontal" id="search_form">
							<!-- START BODYFORM -->
							<div class="form-body">
								<div class="alert alert-danger display-none">
									<button class="close" data-close="alert"></button>
									<spring:message code="form.errors" />
								</div>
								<div class="alert alert-success display-none">
									<button class="close" data-close="alert"></button>
									<spring:message code="form.success" />
								</div>
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="silais" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-6">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="silais" />" name="silais" id="silais" class="form-control">
													<option value=""></option>
													<c:forEach items="${entidades}" var="entidad"> 
														<option value="${entidad.codigo}">${entidad.nombre}</option> 
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="muni" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-6">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="muni" />" name="municipio" id="municipio" class="form-control">
													<option value=""></option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="sector" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-6">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="sector" />" name="sector" id="sector" class="form-control">
													<option value=""></option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="comunity" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-6">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="comunity" />" name="comunidad" id="comunidad" class="form-control">
													<option value=""></option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="novivienda" />:
											</label>
											<div class="col-md-6">
												<div class="input-group">
													<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="novivienda" />" class="form-control" id="numVivienda" name="numVivienda" />
													<span class="input-group-addon">
														<i class="fa fa-sort-numeric-asc"></i>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="nofamilia" />:
											</label>
											<div class="col-md-6">
												<div class="input-group">
													<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="nofamilia" />" class="form-control" id="numFamilia" name="numFamilia"/>
													<span class="input-group-addon">
														<i class="fa fa-sort-numeric-asc"></i>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-6"><spring:message code="noficha" />:
											</label>
											<div class="col-md-6">
												<div class="input-group">
													<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="noficha" />" class="form-control" id="numFicha" name="numFicha"/>
													<span class="input-group-addon">
														<i class="fa fa-sort-numeric-asc"></i>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<div class="form-actions fluid">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-offset-11 col-md-1">
												<button type="submit" id="search-hsf" class="btn btn-info"><i class="fa fa-search"></i> <spring:message code="search" /></button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- END BODYFORM -->
						</form>
						<!-- END FORM -->	
					</div>
				</div>
				<!-- END PORTLET -->
			</div>
		</div>
		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="resultados">
							<thead>
								<tr>
									<th><spring:message code="comunity" /></th>
									<th><spring:message code="noviv" /></th>
									<th><spring:message code="nofamilia" /></th>
									<th><spring:message code="noficha" /></th>
									<th class="hidden-xs"><spring:message code="shortadd" /></th>
									<th><spring:message code="dispen" /></th>
									<th><spring:message code="finished" /></th>
									<th></th>
								</tr>
							</thead>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
				</div>
			</div>
		<!-- END PAGE CONTENT-->
	</div>
	<!-- END CONTENT -->
</div>
<!-- END WRAPPER -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<jsp:include page="../fragments/corePlugins.jsp" />
<jsp:include page="../fragments/bodyUtils.jsp" />
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="selectDos" />
<script type="text/javascript" src="${selectDos}"></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="datepicker" />
<script type="text/javascript" src="${datepicker}"></script>
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
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
<spring:url value="/resources/scripts/hsf-search.js" var="hsfSearch" />
<script src="${hsfSearch}"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<spring:url value="/opciones/municipios" var="opcMuniUrl"/>
<spring:url value="/opciones/sectores" var="opcSectUrl"/>
<spring:url value="/opciones/comunidades" var="opcComuUrl"/>
<spring:url value="/info/hsfs" var="hsfsUrl"/>
<spring:url value="/info/viewHsf" var="familiaUrl"/>

<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfsearch").removeClass("hsfsearch").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {opcMuniUrl: "${opcMuniUrl}"
			, opcSectUrl: "${opcSectUrl}"
			, opcComuUrl: "${opcComuUrl}"
			, hsfsUrl: "${hsfsUrl}"
			, familiaUrl: "${familiaUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			,language:"${pageContext.request.locale.language}" };
		SearchHSF.init(parametros);
		$('#silais').change();
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>