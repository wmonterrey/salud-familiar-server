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
			<spring:url value="/info/editHsf/{idFamilia}" var="edit1Url">
				<spring:param name="idFamilia" value="${familia.idFamilia}" />
			</spring:url>
			<spring:url value="/info/editChs/{idCaractHig}" var="edit2Url">
				<spring:param name="idCaractHig" value="${carHigSan.idCaractHig}" />
			</spring:url>
			<spring:url value="/info/editFse/{idFactSocioEc}" var="edit3Url">
				<spring:param name="idFactSocioEc" value="${factSocEc.idFactSocioEc}" />
			</spring:url>
			<spring:url value="/info/editFf/{idFuncFamiliar}" var="edit4Url">
				<spring:param name="idFuncFamiliar" value="${funcFam.idFuncFamiliar}" />
			</spring:url>
			<spring:url value="/info/editChs/{idCaractHig}" var="add2Url">
				<spring:param name="idCaractHig" value="${familia.idFamilia}" />
			</spring:url>
			<spring:url value="/info/editFse/{idFactSocioEc}" var="add3Url">
				<spring:param name="idFactSocioEc" value="${familia.idFamilia}" />
			</spring:url>
			<spring:url value="/info/editFf/{idFuncFamiliar}" var="add4Url">
				<spring:param name="idFuncFamiliar" value="${familia.idFamilia}" />
			</spring:url>
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
													<p class="form-control-static"><c:out value="${familia.comunidad}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="novivienda" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.numVivienda}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="nofamilia" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.numFamilia}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="address" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.direccion}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="noficha" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.numFicha}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="dispen" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.dispensarizada}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<a href="${fn:escapeXml(edit1Url)}" class="btn btn-success"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</div>
									</div>
									<!-- END ROW -->
									<h4 class="form-section"><spring:message code="visit.list" /></h4>
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12">
											<div class="table-responsive">
											<table class="table table-striped table-hover table-bordered" id="lista_visitas">
											<thead>
												<tr>
													<th><spring:message code="visit.date" /></th>
													<th><spring:message code="personnel" /></th>
													<th><spring:message code="profession" /></th>
													<th></th>
												</tr>
											</thead>
											<c:forEach items="${visitas}" var="visita">
												<spring:url value="/info/editVisita/{idVisita}" var="edit6Url">
													<spring:param name="idVisita" value="${visita.idVisita}" />
												</spring:url>
												<tr>
													<td><fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy" /></td>
													<td><c:out value="${visita.personaVisita}" /></td>
													<td><c:out value="${visita.personaVisitaProfesion}" /></td>
													<td><a href="${fn:escapeXml(edit6Url)}" class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a></td>
												</tr>
											</c:forEach>
											</table>
											</div>
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
												<spring:url value="/info/editPersona/{idPersona}" var="edit5Url">
													<spring:param name="idPersona" value="${persona.idPersona}" />
												</spring:url>
												<tr>
													<td><c:out value="${persona.numPersona}" /></td>
													<td><c:out value="${persona.nombres}" /></td>
													<td><c:out value="${persona.primerApellido}" /></td>
													<td><c:out value="${persona.segundoApellido}" /></td>
													<td><c:out value="${persona.cedula}" /></td>
													<td><fmt:formatDate value="${persona.fechaNacimiento}" pattern="dd/MM/yyyy" /></td>
													<td><c:out value="${persona.grupoDisp}" /></td>
													<td><a href="${fn:escapeXml(edit5Url)}" class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a></td>
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
													<c:forEach items="${sinons}" var="snns">
														<c:if test="${snns.codigo eq carHigSan.hacinamiento}">
															<c:set var="hac" value="${snns.valor}"/>
														</c:if>
													</c:forEach>
													<p class="form-control-static"><c:out value="${hac}"/></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="animdom" /></label>
												<div class="col-md-6">
													<c:forEach items="${animales}" var="animal">
														<c:if test="${fn:contains(carHigSan.animalesDom, animal.codigo)}">
															<c:set var="adom" value="${adom}, ${animal.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="adom" value="${fn:substring(adom, 2, fn:length(adom))}" />
													<p class="form-control-static"><c:out value="${adom}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.nat" /></label>
												<div class="col-md-6">
													<c:forEach items="${rgnats}" var="rgnat">
														<c:if test="${fn:contains(carHigSan.riesgoNatural, rgnat.codigo)}">
															<c:set var="riesgnat" value="${riesgnat}, ${rgnat.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="riesgnat" value="${fn:substring(riesgnat, 2, fn:length(riesgnat))}" />
													<p class="form-control-static"><c:out value="${riesgnat}"/></p>
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
													<c:forEach items="${rgmets}" var="rgmet">
														<c:if test="${fn:contains(carHigSan.riesgoMeteorologico, rgmet.codigo)}">
															<c:set var="riesgmet" value="${riesgmet}, ${rgmet.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="riesgmet" value="${fn:substring(riesgmet, 2, fn:length(riesgmet))}" />
													<p class="form-control-static"><c:out value="${riesgmet}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.bio" /></label>
												<div class="col-md-6">
													<c:forEach items="${rgbios}" var="rgbio">
														<c:if test="${fn:contains(carHigSan.riesgoBiologico, rgbio.codigo)}">
															<c:set var="riesgbio" value="${riesgbio}, ${rgbio.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="riesgbio" value="${fn:substring(riesgbio, 2, fn:length(riesgbio))}" />
													<p class="form-control-static"><c:out value="${riesgbio}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="risks.social" /></label>
												<div class="col-md-6">
													<c:forEach items="${rgsocs}" var="rgsoc">
														<c:if test="${fn:contains(carHigSan.riesgoSocial, rgsoc.codigo)}">
															<c:set var="riesgsoc" value="${riesgsoc}, ${rgsoc.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="riesgsoc" value="${fn:substring(riesgsoc, 2, fn:length(riesgsoc))}" />
													<p class="form-control-static"><c:out value="${riesgsoc}" /></p>
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
													<c:forEach items="${factoresMedAmbs}" var="factoresMedAmb">
														<c:if test="${fn:contains(carHigSan.factoresMedAmb, factoresMedAmb.codigo)}">
															<c:set var="factmed" value="${factmed}, ${factoresMedAmb.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="factmed" value="${fn:substring(factmed, 2, fn:length(factmed))}" />
													<p class="form-control-static"><c:out value="${factmed}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="comb.coc" /></label>
												<div class="col-md-6">
													<c:forEach items="${combCocinars}" var="combCocinar">
														<c:if test="${fn:contains(carHigSan.combCocinar, combCocinar.codigo)}">
															<c:set var="combcoc" value="${combcoc}, ${combCocinar.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="combcoc" value="${fn:substring(combcoc, 2, fn:length(combcoc))}" />
													<p class="form-control-static"><c:out value="${combcoc}" /></p>
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
											<c:if test="${carHigSan.idCaractHig == null}">
												<a href="${fn:escapeXml(add2Url)}" class="btn btn-info"><i class="fa fa-plus"></i> <spring:message code="add" /></a>
											</c:if>
											<c:if test="${carHigSan.idCaractHig != null}">
												<a href="${fn:escapeXml(edit2Url)}" class="btn btn-success"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
											</c:if>
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
													<c:forEach items="${sinons}" var="snns">
														<c:if test="${snns.codigo eq factSocEc.satNecBasicas}">
															<c:set var="sat" value="${snns.valor}"/>
														</c:if>
													</c:forEach>
													<p class="form-control-static"><c:out value="${sat}" /></p>
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
													<c:forEach items="${accionesComunitarias}" var="accionComunitaria">
														<c:if test="${fn:contains(factSocEc.accionesComunitarias, accionComunitaria.codigo)}">
															<c:set var="acccom" value="${acccom}, ${accionComunitaria.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="acccom" value="${fn:substring(acccom, 2, fn:length(acccom))}" />
													<p class="form-control-static"><c:out value="${acccom}" /></p>
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
											<c:if test="${factSocEc.idFactSocioEc == null}">
												<a href="${fn:escapeXml(add3Url)}" class="btn btn-info"><i class="fa fa-plus"></i> <spring:message code="add" /></a>
											</c:if>
											<c:if test="${factSocEc.idFactSocioEc != null}">
												<a href="${fn:escapeXml(edit3Url)}" class="btn btn-success"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
											</c:if>
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
													<c:forEach items="${crisisNormativas}" var="crisisNormativa">
														<c:if test="${fn:contains(funcFam.crisisNormativa, crisisNormativa.codigo)}">
															<c:set var="crinor" value="${crinor}, ${crisisNormativa.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="crinor" value="${fn:substring(crinor, 2, fn:length(crinor))}" />
													<p class="form-control-static"><c:out value="${crinor}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="crisis.para" /></label>
												<div class="col-md-6">
													<c:forEach items="${crisisParanormativas}" var="crisisParanormativa">
														<c:if test="${fn:contains(funcFam.crisisParanormativa, crisisParanormativa.codigo)}">
															<c:set var="criparnor" value="${criparnor}, ${crisisParanormativa.valor}"/>
														</c:if>
													</c:forEach>
													<c:set var="criparnor" value="${fn:substring(criparnor, 2, fn:length(criparnor))}" />
													<p class="form-control-static"><c:out value="${criparnor}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="med.trad" /></label>
												<div class="col-md-6">
													<c:forEach items="${sinons}" var="snns">
														<c:if test="${snns.codigo eq funcFam.usoMedTradicional}">
															<c:set var="medtra" value="${snns.valor}"/>
														</c:if>
													</c:forEach>
													<p class="form-control-static"><c:out value="${medtra}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<c:if test="${funcFam.idFuncFamiliar == null}">
												<a href="${fn:escapeXml(add4Url)}" class="btn btn-info"><i class="fa fa-plus"></i> <spring:message code="add" /></a>
											</c:if>
											<c:if test="${funcFam.idFuncFamiliar != null}">
												<a href="${fn:escapeXml(edit4Url)}" class="btn btn-success"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
											</c:if>
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