<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../fragments/headTag.jsp" />
<spring:url value="/resources/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" var="daterangecss" />
<link rel="stylesheet" type="text/css" href="${daterangecss}"/>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.css" var="dtbootcss" />
<link rel="stylesheet" href="${dtbootcss}"/>
<spring:url value="/resources/plugins/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
<link rel="stylesheet" href="${dtttcss}"/>
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
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<jsp:include page="../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="heading" /> <small><spring:message code="statisctics" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li class="pull-right">
							<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="<spring:message code="change.date.range" />">
								<i class="fa fa-calendar"></i>
								<span>
								</span>
								<i class="fa fa-angle-down"></i>
							</div>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<div class="clearfix">
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div id="visitas-dia-div" class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bar-chart-o"></i><spring:message code="visit.day" />
								<c:choose> 
									<c:when test="${usuario.nivel.codigo eq 'HSF_NIVELES|SILAIS'}">
										<c:out value="${usuario.entidad.nombre}" />
									</c:when>
									<c:when test="${usuario.nivel.codigo eq 'HSF_NIVELES|UNIDAD'}">
										<c:out value="${usuario.unidad.nombre}" />
									</c:when>
								</c:choose>
							</div>
							<div class="actions">
								<a href="#" onclick="exportVisitaDia();" class="btn btn-info btn-sm"><i class="fa fa-download"></i></a>
							</div>
						</div>
						<div class="portlet-body">
							<spring:url value="/resources/img/loading.gif" var="loading" />
							<div id="site_statistics_loading">
								<img src="${loading}" alt="loading"/>
							</div>
							<div id="site_statistics_content" class="display-none">
								<div id="site_statistics" class="chart">
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div id="visitas-area-div" class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bar-chart-o"></i><spring:message code="visit.area" />
								<c:choose> 
									<c:when test="${usuario.nivel.codigo eq 'HSF_NIVELES|SILAIS'}">
										<c:out value="${usuario.entidad.nombre}" />
									</c:when>
									<c:when test="${usuario.nivel.codigo eq 'HSF_NIVELES|UNIDAD'}">
										<c:out value="${usuario.unidad.nombre}" />
									</c:when>
								</c:choose>
							</div>
							<div class="actions">
								<a href="#" onclick="exportVisitaArea();" class="btn btn-info btn-sm"><i class="fa fa-download"></i></a>
							</div>
						</div>
						<div class="portlet-body">
							<spring:url value="/resources/img/loading.gif" var="loading" />
							<div id="site_statistics_2_loading">
								<img src="${loading}" alt="loading"/>
							</div>
							<div id="site_statistics_2_content" class="display-none">
								<div id="site_statistics_2" class="chart">
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<div class="clearfix">
			</div>
			<div class="row ">
				<div class="col-md-6 col-sm-12">
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
				<div class="col-md-6 col-sm-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-table"></i><spring:message code="visit.area" />
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
							<div class="table-toolbar2">
							</div>
							<table class="table table-striped table-hover table-bordered" id="visitas_area">
								<thead>
									<tr>
										<th><spring:message code="area" /></th>
										<th><spring:message code="visit.first" /></th>
										<th><spring:message code="visit.followup" /></th>
										<th><spring:message code="total" /></th>
									</tr>
								</thead>
								<c:forEach items="${visitasArea}" var="visitaArea">
									<tr>
										<td><c:out value="${visitaArea[0]}" /></td>
										<td align="right"><c:out value="${visitaArea[1]}" /></td>
										<td align="right"><c:out value="${visitaArea[2]}" /></td>
										<td align="right"><c:out value="${visitaArea[3]}" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
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
<!-- Date Range-->
<spring:url value="/resources/plugins/bootstrap-daterangepicker/moment.min.js" var="DateRangeMoment" />
<script src="${DateRangeMoment}" type="text/javascript"></script>
<spring:url value="/resources/plugins/bootstrap-daterangepicker/daterangepicker.js" var="DateRange" />
<script src="${DateRange}" type="text/javascript"></script>
<!-- Data Tables-->
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
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/home.js" var="IndexScript" />
<script src="${IndexScript}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
<c:set var="processError"><spring:message code="process.error" /></c:set>
<c:set var="deniedError"><spring:message code="denied" /></c:set>
<c:set var="yesterday"><spring:message code="yesterday" /></c:set>
<c:set var="today"><spring:message code="today" /></c:set>
<c:set var="last7days"><spring:message code="last7days" /></c:set>
<c:set var="last30Days"><spring:message code="last30Days" /></c:set>
<c:set var="tMonth"><spring:message code="tmonth" /></c:set>
<c:set var="lMonth"><spring:message code="lmonth" /></c:set>
<c:set var="applyLabel"><spring:message code="applyLabel" /></c:set>
<c:set var="fromLabel"><spring:message code="fromLabel" /></c:set>
<c:set var="toLabel"><spring:message code="toLabel" /></c:set>
<c:set var="customRangeLabel"><spring:message code="customRangeLabel" /></c:set>
<c:set var="Su"><spring:message code="Su" /></c:set>
<c:set var="Mo"><spring:message code="Mo" /></c:set>
<c:set var="Tu"><spring:message code="Tu" /></c:set>
<c:set var="We"><spring:message code="We" /></c:set>
<c:set var="Th"><spring:message code="Th" /></c:set>
<c:set var="Fr"><spring:message code="Fr" /></c:set>
<c:set var="Sa"><spring:message code="Sa" /></c:set>
<c:set var="january"><spring:message code="january" /></c:set>
<c:set var="february"><spring:message code="february" /></c:set>
<c:set var="march"><spring:message code="march" /></c:set>
<c:set var="april"><spring:message code="april" /></c:set>
<c:set var="may"><spring:message code="may" /></c:set>
<c:set var="june"><spring:message code="june" /></c:set>
<c:set var="july"><spring:message code="july" /></c:set>
<c:set var="august"><spring:message code="august" /></c:set>
<c:set var="september"><spring:message code="september" /></c:set>
<c:set var="october"><spring:message code="october" /></c:set>
<c:set var="november"><spring:message code="november" /></c:set>
<c:set var="december"><spring:message code="december" /></c:set>
<c:set var="initial"><spring:message code="visit.first" /></c:set>
<c:set var="followup"><spring:message code="visit.followup" /></c:set>
<c:set var="total"><spring:message code="total" /></c:set>
<spring:url value="/updateDashboard" var="dashUrl"/>
<c:set var="exportar"><spring:message code="export" /></c:set>
<script>
    $(function () {
        $("li.start").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		Index.init();
		var parametrosDateRange = {dashUrl: "${dashUrl}",yesterday: "${yesterday}", today: "${today}", last7days: "${last7days}"
			, last30Days: "${last30Days}",tMonth:"${tMonth}",lMonth: "${lMonth}", applyLabel: "${applyLabel}", fromLabel: "${fromLabel}"
			, toLabel: "${toLabel}",customRangeLabel:"${customRangeLabel}",Su: "${Su}", Mo: "${Mo}", Tu: "${Tu}",We: "${We}", Th: "${Th}", Fr: "${Fr}", Sa: "${Sa}"
			, january: "${january}",february:"${february}",march: "${march}", april: "${april}", may: "${may}",june: "${june}", july: "${july}", august: "${august}"
			, september: "${september}", october: "${october}", november: "${november}", december: "${december}", dataTablesTTSWF: "${dataTablesTTSWF}", exportar: "${exportar}"
			, initial: "${initial}", followup: "${followup}", total: "${total}"};
		Index.initDashboardDaterange(parametrosDateRange);
		var iniciales = [];
		var segs = [];
		var totales = [];
		var fechas = [];
		var sumInicial = 0;
		var sumSeg = 0;
		var sumTotal = 0;
		var ainiciales = [];
		var asegs = [];
		var atotales = [];
		var areas = [];
		<c:forEach var="visita" items="${visitasDia}">
			fechas.push(["${visita[0]}"]);
			iniciales.push(["${visita[0]}", "${visita[1]}"]);
			sumInicial = sumInicial + parseInt("${visita[1]}");
			segs.push(["${visita[0]}", "${visita[2]}"]);
			sumSeg = sumSeg + parseInt("${visita[2]}");
			totales.push(["${visita[0]}", "${visita[3]}"]);
			sumTotal = sumTotal + parseInt("${visita[3]}");
		</c:forEach>
		<c:forEach var="areas" items="${visitasArea}">
			areas.push([ "${areas[0]}"]);
			ainiciales.push([ "${areas[0]}", "${areas[1]}"]);
			asegs.push([ "${areas[0]}", "${areas[2]}"]);
			atotales.push([ "${areas[0]}", "${areas[3]}"]);
		</c:forEach>
		var parametrosChart = {fechas: fechas,iniciales: iniciales, segs: segs, totales: totales,ainiciales: ainiciales, asegs: asegs, atotales: atotales, areas: areas
			, sumInicial: sumInicial, sumSeg: sumSeg, sumTotal: sumTotal, initial: "${initial}", followup: "${followup}", total: "${total}"};
		Index.initCharts(parametrosChart);
	});
	
	function exportVisitaDia()
	{
		html2canvas($("#visitas-dia-div"), {
	        onrendered: function(canvas) {
	            // canvas is the final rendered <canvas> element
	        	var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");  /// here is the most important part because if you dont replace you will get a DOM 18 exception.
	        	document.location.href=image;
	        }
	    });
	}
	
	function exportVisitaArea()
	{
		html2canvas($("#visitas-area-div"), {
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