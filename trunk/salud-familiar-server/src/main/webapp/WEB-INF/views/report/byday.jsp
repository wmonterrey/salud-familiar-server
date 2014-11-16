<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
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
		<!-- BEGIN STYLE CUSTOMIZER -->
		<jsp:include page="../fragments/bodyCustomizer.jsp" />
		<!-- BEGIN PAGE HEADER-->
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					<spring:message code="heading" />
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
						<i class="fa fa-angle-right"></i> <a href="<spring:url value="/report/visitbyday" htmlEscape="true "/>"><spring:message code="visit.day" /></a>
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
		<c:set var="noResults"><spring:message code="zeroresults" /></c:set>
		<div class="row">
			<div class="col-md-4">
				<div class="portlet" id="form_search">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-search"></i> <spring:message code="select" />
						</div>
						<div class="tools hidden-xs">
							<a href="javascript:;" class="collapse"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
					</div>
					<div class="portlet-body form">
						<!-- START FORM -->
						<form action="#" class="form-horizontal" id="parameters_form">
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
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-3"><spring:message code="select" /> <spring:message code="users.nivel" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="area" />" name="area" id="area" class="form-control">
													<option value=""></option>
													<c:forEach items="${areas}" var="area"> 
														<option value="${area.codigo}">${area.valor}</option> 
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-12">
										<div id="silais-div" class="form-group" hidden=true>
											<label class="control-label col-md-3"><spring:message code="silais" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="silais" />" name="silais" id="silais" class="form-control">
													<option value=""></option>
													<c:forEach items="${entidades}" var="entidad"> 
														<option value="${entidad.codigo}">${entidad.nombre}</option> 
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-12">
										<div id="muni-div" class="form-group" hidden=true>
											<label class="control-label col-md-3"><spring:message code="muni" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
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
									<div class="col-md-12">
										<div id="unidad-div" class="form-group" hidden=true>
											<label class="control-label col-md-3"><spring:message code="unit" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="unit" />" name="unidad" id="unidad" class="form-control">
													<option value=""></option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<!-- START ROW -->
								<div class="row">
									<div class="col-md-12">
										<div id="sector-div" class="form-group" hidden=true>
											<label class="control-label col-md-3"><spring:message code="sector" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="sector" />" name="sector" id="sector" class="form-control">
													<option value=""></option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div id="comunidad-div" class="form-group" hidden=true>
											<label class="control-label col-md-3"><spring:message code="comunity" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
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
											<div class="col-md-12">
												<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-end-date="+0d">
													<input id="desde" name="desde" type="text" class="form-control" placeholder="<spring:message code="fromLabel" />">
													<span class="input-group-btn">
														<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="col-md-12">
												<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-end-date="+0d">
													<input id="hasta" name="hasta" type="text" class="form-control" placeholder="<spring:message code="toLabel" />">
													<span class="input-group-btn">
														<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
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
											<div class="col-md-offset-9 col-md-1">
												<button type="submit" id="view-report" class="btn btn-info"><i class="fa fa-refresh"></i> <spring:message code="update" /></button>
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
			
			<div class="col-md-8">
				<div class="portlet">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-table"></i><spring:message code="visit.day" />
							<c:choose> 
								<c:when test="${usuario.nivel.codigo eq 'HSF_NIVELES|SILAIS'}">
									<c:out value="${usuario.entidad.nombre}" />
								</c:when>
								<c:when test="${usuario.nivel.codigo eq 'HSF_NIVELES|UNIDAD'}">
									<c:out value="${usuario.unidad.nombre}" />
								</c:when>
							</c:choose>
						</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar1">
						</div>
						<table class="table table-striped table-hover table-bordered" id="visitas_dia">
							<thead>
								<tr>
									<th><spring:message code="visit.date" /></th>
									<th><spring:message code="visit.first" /></th>
									<th><spring:message code="visit.followup" /></th>
									<th><spring:message code="total" /></th>
								</tr>
							</thead>
							<c:forEach items="${visitasDia}" var="visitaDia">
								<tr>
									<td><fmt:formatDate value="${visitaDia[0]}" pattern="yyyy-MM-dd" /></td>
									<td align="right"><c:out value="${visitaDia[1]}" /></td>
									<td align="right"><c:out value="${visitaDia[2]}" /></td>
									<td align="right"><c:out value="${visitaDia[3]}" /></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<!-- END PORTLET-->
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
<!--<spring:url value="/resources/scripts/handleDatePickers.js" var="handleDatePickersJS" />
<script src="${handleDatePickersJS}"></script>-->
<spring:url value="/resources/scripts/view-report1.js" var="viewReport" />
<script src="${viewReport}"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<spring:url value="/opciones/municipios" var="opcMuniUrl"/>
<spring:url value="/opciones/sectores" var="opcSectUrl"/>
<spring:url value="/opciones/comunidades" var="opcComuUrl"/>
<spring:url value="/report/visitsbyday" var="reportUrl"/>

<script>
    $(function () {
    	$("li.reports").removeClass("reports").addClass("active");
        $("li.reportday").removeClass("reportday").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {opcMuniUrl: "${opcMuniUrl}"
			, opcSectUrl: "${opcSectUrl}"
			, opcComuUrl: "${opcComuUrl}"
			, reportUrl: "${reportUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, noResults: "${noResults}"
			,language:"${pageContext.request.locale.language}" };
		//handleDatePickers("${pageContext.request.locale.language}");
		ViewReport.init(parametros);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>