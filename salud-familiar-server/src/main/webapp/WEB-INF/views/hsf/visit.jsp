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
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
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
			<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
			<c:set var="processError"><spring:message code="process.error" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet" id="form_vist">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> <spring:message code="heading" />
							</div>
							<div class="tools hidden-xs">
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" class="form-horizontal" id="visit_form">
								<div class="form-body">
									<h4 class="form-section"><spring:message code="step1" /></h4> 
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="silais" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${silais.nombre}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="muni" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${municipio.nombre}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="sector" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${sector.nombre}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="comunity" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${visita.familia.comunidad}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="novivienda" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${visita.familia.numVivienda}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="nofamilia" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${visita.familia.numFamilia}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="noficha" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${visita.numFicha}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="personnel" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${visita.personaVisita}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="visit.date" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${visita.fechaVisita}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<a href="#" class="btn btn-info"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</div>
									</div>
									<!-- END ROW -->
									<h4 class="form-section"><spring:message code="step2" /></h4>
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12">
											<div class="table-responsive">
											<table class="table table-striped table-hover table-bordered" id="lista_personas">
											<thead>
												<tr>
													<th><spring:message code="nopersona" /></th>
													<th><spring:message code="completename" /></th>
													<th><spring:message code="lastname1" /></th>
													<th><spring:message code="lastname2" /></th>
													<th><spring:message code="person.id" /></th>
													<th><spring:message code="birthdate" /></th>
													<th><spring:message code="person.gd" /></th>
													<th></th>
												</tr>
											</thead>
											<c:forEach items="${personas}" var="persona">
												<tr>
													<td><c:out value="${persona.numPersona}" /></td>
													<td><c:out value="${persona.nombres}" /></td>
													<td><c:out value="${persona.primerApellido}" /></td>
													<td><c:out value="${persona.segundoApellido}" /></td>
													<td><c:out value="${persona.cedula}" /></td>
													<td><c:out value="${persona.fechaNacimiento}" /></td>
													<td><c:out value="${persona.grupoDisp}" /></td>
													<td><a href="${fn:escapeXml(editUrl)}" class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a></td>
												</tr>
											</c:forEach>
											</table>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<h4 class="form-section"><spring:message code="step3" /></h4>
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="hacinamiento" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.hacinamiento}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="animdom" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.animalesDom}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.nat" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.riesgoNatural}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.met" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.riesgoMeteorologico}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.bio" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.riesgoBiologico}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.social" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.riesgoSocial}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="fact.med.amb" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.factoresMedAmb}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="comb.coc" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.combCocinar}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="abast.agua" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.aAgua}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="cal.agua" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.cAgua}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="elect" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.electricidad}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="dep.excretas" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.depExcretas}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="dep.basura" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.depBasura}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="dep.resliq" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.depResLiq}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="obs" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${carHigSan.obsCaractHig}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<a href="#" class="btn btn-info"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</div>
									</div>
									<!-- END ROW -->
									<h4 class="form-section"><spring:message code="step4" /></h4>
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="piso" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.tipoPiso}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="techo" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.tipoTecho}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="paredes" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.tipoPared}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="cult.san" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.culturaSanitaria}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="car.psic" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.carPsicosociales}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="nec.basicas" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.satNecBasicas}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="ten.viv" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.tenenciaVivienda}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="acc.com" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.accionesComunitarias}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="obs" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${factSocEc.obsFactSocioEc}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<a href="#" class="btn btn-info"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</div>
									</div>
									<!-- END ROW -->
									<h4 class="form-section"><spring:message code="step5" /></h4>
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="fam.size" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${funcFam.tamFamilia}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="onto" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${funcFam.ontogenesis}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="stage.cycle" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${funcFam.etapaCicloVital}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="crisis.norm" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${funcFam.crisisNormativa}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="crisis.para" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${funcFam.crisisParanormativa}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="med.trad" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${funcFam.usoMedTradicional}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<a href="#" class="btn btn-info"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</div>
									</div>
									<!-- END ROW -->
								</div>
							</form>
						</div>
					</div>
					<!-- END PORTLET -->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
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
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfsearch").removeClass("hsfsearch").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>