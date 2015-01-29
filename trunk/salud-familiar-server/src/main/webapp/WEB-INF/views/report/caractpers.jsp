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
						<i class="fa fa-angle-right"></i> <a href="<spring:url value="/report/pers" htmlEscape="true "/>"><spring:message code="persons" /></a>
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
									<div class="col-md-12">
										<div id="variable-div" class="form-group">
											<label class="control-label col-md-3"><spring:message code="variable" />:
											<span class="required">
												 *
											</span>
											</label>
											<div class="col-md-9">
												<select data-placeholder="<spring:message code="select" /> <spring:message code="variable" />" name="variable" id="variable" class="form-control">
													<option value=""></option>
													<option value="discapacidades"><spring:message code="variable.disp" /></option>
													<option value="factRiesgoMod"><spring:message code="variable.frmod" /></option>
													<option value="factRiesgoNoMod"><spring:message code="variable.frnomod" /></option>
													<option value="factRiesgoSocial"><spring:message code="variable.frsoc" /></option>
													<option value="escolaridad"><spring:message code="variable.escda" /></option>
													<option value="etnia"><spring:message code="variable.etnia" /></option>
													<option value="grupoDisp"><spring:message code="variable.gd" /></option>
													<option value="ocupacion"><spring:message code="variable.ocup" /></option>
													<option value="religion"><spring:message code="variable.relig" /></option>
													<option value="sexo"><spring:message code="variable.sexo" /></option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<div class="row">
									<div class="modal-footer">
										<button type="submit" id="view-report" class="btn btn-info"><i class="fa fa-refresh"></i> <spring:message code="update" /></button>
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
							<i class="fa fa-table"></i><spring:message code="persons" />
						</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar1">
						</div>
						<table class="table table-striped table-hover table-bordered" id="datos">
							<thead>
								<tr>
									<th><spring:message code="values" /></th>
									<th><spring:message code="total" /></th>
									<th><spring:message code="percent" /></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- END PORTLET-->
			</div>
			<div class="clearfix">
			</div>
			<!-- START GRAPHIC 1-->
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div id="grafico-div" class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bar-chart-o"></i><spring:message code="persons" />
							</div>
							<div class="actions">
								<a href="#" onclick="exportGrafico();" class="btn btn-info btn-sm"><i class="fa fa-download"></i></a>
							</div>
						</div>
						<div class="portlet-body">
							<spring:url value="/resources/img/loading.gif" var="loading" />
							<div id="site_statistics_loading">
								<img src="${loading}" alt="loading"/>
							</div>
							<div id="site_statistics_content" class="display-none">
								<div id="grafico-title" align="center"></div>
								<div id="site_statistics" class="chart"></div>
								<div id="grafico-foot"></div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<!-- END GRAPHIC 1-->
			<!-- START GRAPHIC 2-->
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div id="grafico-div2" class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bar-chart-o"></i><spring:message code="persons" />
							</div>
							<div class="actions">
								<a href="#" onclick="exportGrafico2();" class="btn btn-info btn-sm"><i class="fa fa-download"></i></a>
							</div>
						</div>
						<div class="portlet-body">
							<spring:url value="/resources/img/loading.gif" var="loading" />
							<div id="site_statistics_loading_2">
								<img src="${loading}" alt="loading"/>
							</div>
							<div id="site_statistics_content_2" class="display-none">
								<div id="grafico-title-2" align="center"></div>
								<div id="site_statistics_2" class="chart"></div>
								<div id="grafico-foot-2"></div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<!-- END GRAPHIC 2-->
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
<!-- jQuery Flot-->
<spring:url value="/resources/plugins/flot/jquery.flot.js" var="jQFlot" />
<script type="text/javascript" src="${jQFlot}"></script>
<spring:url value="/resources/plugins/flot/jquery.flot.resize.js" var="jQFlotResize" />
<script type="text/javascript" src="${jQFlotResize}"></script>
<spring:url value="/resources/plugins/html2canvas/html2canvas.js" var="html2Canvas" />
<script src="${html2Canvas}"></script>
<spring:url value="/resources/plugins/flot/jquery.flot.categories.js" var="categories" />
<script src="${categories}"></script>
<spring:url value="/resources/plugins/jquery-knob/js/jquery.knob.js" var="jKnob" />
<script src="${jKnob}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/view-report12.js" var="viewReport" />
<script src="${viewReport}"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<spring:url value="/opciones/municipios" var="opcMuniUrl"/>
<spring:url value="/opciones/sectores" var="opcSectUrl"/>
<spring:url value="/opciones/comunidades" var="opcComuUrl"/>
<spring:url value="/report/caractpers" var="reportUrl"/>
<c:set var="exportar"><spring:message code="export" /></c:set>
<c:set var="summary"><spring:message code="summary" /></c:set>
<c:set var="summaryNac"><spring:message code="summary1" /></c:set>
<c:set var="titleApp"><spring:message code="title" /></c:set>
<c:set var="heading"><spring:message code="heading" /></c:set>
<c:set var="percent"><spring:message code="percent" /></c:set>
<c:set var="count"><spring:message code="count" /></c:set>

<script>
    $(function () {
    	$("li.reports").removeClass("reports").addClass("active");
    	$("li.caracterizacion").removeClass("caracterizacion").addClass("active");
        $("li.pers").removeClass("pers").addClass("active");
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
			, summary: "${summary}"
			, summaryNac: "${summaryNac}"
			, titleApp: "${titleApp}"
			, heading: "${heading}"
			, percent: "${percent}"
			, count: "${count}"
			,language:"${pageContext.request.locale.language}",dataTablesTTSWF: "${dataTablesTTSWF}", exportar: "${exportar}"};
		ViewReport.init(parametros);
	});
	
	function exportGrafico()
	{
		html2canvas($("#grafico-div"), {
	        onrendered: function(canvas) {
	            // canvas is the final rendered <canvas> element
	        	var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");  /// here is the most important part because if you dont replace you will get a DOM 18 exception.
	        	document.location.href=image;
	        }
	    });
	}
	
	function exportGrafico2()
	{
		html2canvas($("#grafico-div2"), {
	        onrendered: function(canvas) {
	            // canvas is the final rendered <canvas> element
	        	var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");  /// here is the most important part because if you dont replace you will get a DOM 18 exception.
	        	document.location.href=image;
	        }
	    });
	}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>