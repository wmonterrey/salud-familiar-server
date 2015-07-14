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
					<spring:message code="heading" /> <small><spring:message code="monitor" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/monitor/activity" htmlEscape="true "/>"><spring:message code="monitor.activity" /></a>
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
			<div class="row ">
				<div class="col-md-6 col-sm-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-table"></i><spring:message code="monitor.activity" />
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar1">
							</div>
							<table class="table table-striped table-hover table-bordered" id="por_usuario">
								<thead>
									<tr>
										<th><spring:message code="users.user" /></th>
										<th><spring:message code="users.username" /></th>
										<th><spring:message code="users.accesscount" /></th>
										<th><spring:message code="users.firstaccess" /></th>
										<th><spring:message code="users.lastaccess" /></th>
									</tr>
								</thead>
								<c:forEach items="${accesosUsuarios}" var="accesoUsuario">
									<tr>
										<td><c:out value="${accesoUsuario[0]}" /></td>
										<td><c:out value="${accesoUsuario[1]}" /></td>
										<td align="right"><c:out value="${accesoUsuario[2]}" /></td>
										<td><fmt:formatDate value="${accesoUsuario[3]}" pattern="yyyy-MM-dd hh:mm a" /></td>
										<td><fmt:formatDate value="${accesoUsuario[4]}" pattern="yyyy-MM-dd hh:mm a" /></td>
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
								<i class="fa fa-table"></i><spring:message code="monitor.activity" />
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar2">
							</div>
							<table class="table table-striped table-hover table-bordered" id="por_dia">
								<thead>
									<tr>
										<th><spring:message code="date" /></th>
										<th><spring:message code="users.accesscount" /></th>
									</tr>
								</thead>
								<c:forEach items="${accesosDias}" var="accesoDia">
									<tr>
										<td><c:out value="${accesoDia[0]}" /></td>
										<td><c:out value="${accesoDia[1]}" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<div class="clearfix">
			</div>
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div id="actividad-dias-div" class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bar-chart-o"></i><spring:message code="monitor.activity" />
							</div>
							<div class="actions">
								<a href="#" onclick="exportActividadDias();" class="btn btn-info btn-sm"><i class="fa fa-download"></i></a>
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
			</div>
			<div class="clearfix">
			</div>
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div id="actividad-users-div" class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bar-chart-o"></i><spring:message code="monitor.activity" />
							</div>
							<div class="actions">
								<a href="#" onclick="exportActividadUsuarios();" class="btn btn-info btn-sm"><i class="fa fa-download"></i></a>
							</div>
						</div>
						<div class="portlet-body">
							<spring:url value="/resources/img/loading.gif" var="loading" />
							<div id="site_statistics_loading_2">
								<img src="${loading}" alt="loading"/>
							</div>
							<div id="site_statistics_content_2" class="display-none">
								<div id="site_statistics_2" class="chart">
								</div>
							</div>
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
<spring:url value="/resources/scripts/monactivity.js" var="ActivityMon" />
<script src="${ActivityMon}"></script>
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
<spring:url value="/monitor/updateDashboard" var="monitorUrl"/>
<c:set var="exportar"><spring:message code="export" /></c:set>
<c:set var="total"><spring:message code="total" /></c:set>
<script>
    $(function () {
    	$("li.monitor").removeClass("monitor").addClass("active");
    	$("li.monitoractivity").removeClass("monitoractivity").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		ActivityMon.init();
		var parametrosDateRange = {monitorUrl: "${monitorUrl}",yesterday: "${yesterday}", today: "${today}", last7days: "${last7days}"
			, last30Days: "${last30Days}",tMonth:"${tMonth}",lMonth: "${lMonth}", applyLabel: "${applyLabel}", fromLabel: "${fromLabel}"
			, toLabel: "${toLabel}",customRangeLabel:"${customRangeLabel}",Su: "${Su}", Mo: "${Mo}", Tu: "${Tu}",We: "${We}", Th: "${Th}", Fr: "${Fr}", Sa: "${Sa}"
			, january: "${january}",february:"${february}",march: "${march}", april: "${april}", may: "${may}",june: "${june}", july: "${july}", august: "${august}"
			, september: "${september}", october: "${october}", november: "${november}", december: "${december}", dataTablesTTSWF: "${dataTablesTTSWF}", exportar: "${exportar}"};
		ActivityMon.initDashboardDaterange(parametrosDateRange);
		var fechas = [];
		var accesosD = [];
		var usuarios = [];
		var accesosU = [];
		<c:forEach var="accesoD" items="${accesosDias}">
			fechas.push(["${accesoD[0]}"]);
			accesosD.push(["${accesoD[0]}", "${accesoD[1]}"]);
		</c:forEach>
		<c:forEach var="accesoU" items="${accesosUsuarios}">
			usuarios.push(["${accesoU[0]}"]);
			accesosU.push(["${accesoU[0]}", "${accesoU[2]}"]);
		</c:forEach>
		var parametrosChart = {fechas: fechas,accesosD: accesosD,usuarios:usuarios,accesosU: accesosU, total: "${total}"};
		ActivityMon.initCharts(parametrosChart);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>