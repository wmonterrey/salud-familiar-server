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
<spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss" />
<link rel="stylesheet" type="text/css" href="${jqmscss}"/>
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
						<spring:message code="heading" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/info/newHsf" htmlEscape="true "/>"><spring:message code="hsf.nuevo" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<c:set var="exportar"><spring:message code="export" /></c:set>
			<c:set var="valPersonas"><spring:message code="person.valtotal" /></c:set>
			<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
			<c:set var="processError"><spring:message code="process.error" /></c:set>
			<c:set var="deniedError"><spring:message code="denied" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> <spring:message code="hsf.nuevo" /> -
								<span class="step-title">
									 <spring:message code="step" /> 1 <spring:message code="of" /> 6
								</span>
							</div>
							<div class="tools hidden-xs">
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" class="form-horizontal" id="submit_form">
								<div class="form-wizard">
									<div class="form-body">
										<ul class="nav nav-pills nav-justified steps hidden-xs">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
													 1
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> <spring:message code="step1" />
												</span>
												</a>
											</li>
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
													 2
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> <spring:message code="step2" />
												</span>
												</a>
											</li>
											<li>
												<a href="#tab3" data-toggle="tab" class="step">
												<span class="number">
													 3
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> <spring:message code="step3" />
												</span>
												</a>
											</li>
											<li>
												<a href="#tab4" data-toggle="tab" class="step">
												<span class="number">
													 4
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> <spring:message code="step4" />
												</span>
												</a>
											</li>
											<li>
												<a href="#tab5" data-toggle="tab" class="step">
												<span class="number">
													 5
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> <spring:message code="step5" />
												</span>
												</a>
											</li>
											<li>
												<a href="#tab6" data-toggle="tab" class="step">
												<span class="number">
													 6
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> <spring:message code="step6" />
												</span>
												</a>
											</li>
										</ul>
										<div id="bar" class="progress progress-striped" data-role="progressbar">
											<div class="progress-bar progress-bar-success">
											</div>
										</div>
										<div class="tab-content hsfcompleta">
											<div class="alert alert-danger display-none">
												<button class="close" data-close="alert"></button>
												<spring:message code="form.errors" />
											</div>
											<div class="alert alert-success display-none">
												<button class="close" data-close="alert"></button>
												<spring:message code="form.success" />
											</div>
											<div class="tab-pane active" id="tab1">
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="nofamilia" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-9">
																<input type="text" id="idFamilia" name="idFamilia" class="form-control"/>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="noficha" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-9">
																<input type="text" id="idVisita" name="idVisita" class="form-control"/>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
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
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="novivienda" />:
															<span class="required">
																 *
															</span>
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
															<span class="required">
																 *
															</span>
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
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="address" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-9">
																<div class="input-group">
																	<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="address" />" class="form-control" id="direccion" name="direccion" />
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="form-section"></div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="noficha" />:
															<span class="required">
																 *
															</span>
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
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="personnel" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="personnel" />" class="form-control" id="personaVisita" name="personaVisita"/>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="profession" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="profession" />" name="personaVisitaProfesion" id="personaVisitaProfesion" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${profesiones}" var="profesion"> 
																		<option value="${profesion.codigo}">${profesion.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="visit.date" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-start-date="-2920d" data-date-end-date="+0d">
																	<input id="fechaVisita" name="fechaVisita" type="text" class="form-control" placeholder="<spring:message code="please.enter" /> <spring:message code="visit.date" />">
																	<span class="input-group-btn">
																		<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab2">
												<div class="row">
													<div class="col-md-12">
														<!-- BEGIN TABLE PORTLET-->
														<div class="portlet">
															<div class="portlet-title">
																<div class="caption">
																	<i class="fa fa-group"></i><spring:message code="person.list" />
																</div>
																<div class="tools">
																</div>
															</div>
															<div class="portlet-body">
																<div class="table-toolbar">
																	<div class="btn-group">
																		<a class="btn btn-success" data-toggle="modal" onclick="validarModalPersona();" data-target="#personamodalform"><spring:message code="person.add" /> <i class="fa fa-plus"></i></a>																		
																	</div>
																</div>
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
																	</tr>
																</thead>
																</table>
																</div>
															</div>
														</div>
														<!-- END TABLE PORTLET-->
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="person.total" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-3">
																<input id="noPersonasFamilia" name="noPersonasFamilia" class="form-control" readonly />
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab3">
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="step3" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-9">
																<input type="text" id="idCaractHig" name="idCaractHig" class="form-control"/>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="hacinamiento" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="hacinamiento" />" name="hacinamiento" id="hacinamiento" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${sinons}" var="sn"> 
																		<option value="${sn.codigo}">${sn.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="animdom" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=animalesDom name="animalesDom">
																	<c:forEach items="${animales}" var="animal"> 
																		<option value="${animal.codigo}">${animal.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-4"><spring:message code="risks.nat" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=riesgoNatural name="riesgoNatural">
																	<c:forEach items="${rgnats}" var="rgnat"> 
																		<option value="${rgnat.codigo}">${rgnat.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="risks.met" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=riesgoMeteorologico name="riesgoMeteorologico">
																	<c:forEach items="${rgmets}" var="rgmet"> 
																		<option value="${rgmet.codigo}">${rgmet.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-4"><spring:message code="risks.bio" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=riesgoBiologico name="riesgoBiologico">
																	<c:forEach items="${rgbios}" var="rgbio"> 
																		<option value="${rgbio.codigo}">${rgbio.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="risks.social" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=riesgoSocial name="riesgoSocial">
																	<c:forEach items="${rgsocs}" var="rgsoc"> 
																		<option value="${rgsoc.codigo}">${rgsoc.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-4"><spring:message code="fact.med.amb" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=factoresMedAmb name="factoresMedAmb">
																	<c:forEach items="${factoresMedAmbs}" var="factoresMedAmb"> 
																		<option value="${factoresMedAmb.codigo}">${factoresMedAmb.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="comb.coc" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select multiple="multiple" class="multi-select" id=combCocinar name="combCocinar">
																	<c:forEach items="${combCocinars}" var="combCocinar"> 
																		<option value="${combCocinar.codigo}">${combCocinar.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-4"><spring:message code="abast.agua" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="abast.agua" />" name="aAgua" id="aAgua" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${abastecimientoAguas}" var="abastecimientoAgua"> 
																		<option value="${abastecimientoAgua.codigo}">${abastecimientoAgua.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="cal.agua" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="cal.agua" />" name="cAgua" id="cAgua" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${calidadAguas}" var="calidadAgua"> 
																		<option value="${calidadAgua.codigo}">${calidadAgua.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-4"><spring:message code="elect" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="elect" />" name="electricidad" id="electricidad" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${electricidads}" var="electricidad"> 
																		<option value="${electricidad.codigo}">${electricidad.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="dep.excretas" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="dep.excretas" />" name="depExcretas" id="depExcretas" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${depExcretas}" var="depExcreta"> 
																		<option value="${depExcreta.codigo}">${depExcreta.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-4"><spring:message code="dep.basura" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="dep.basura" />" name="depBasura" id="depBasura" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${depBasuras}" var="depBasura"> 
																		<option value="${depBasura.codigo}">${depBasura.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-4"><spring:message code="dep.resliq" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-8">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="dep.resliq" />" name="depResLiq" id="depResLiq" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${depResLiqs}" var="depResLiq"> 
																		<option value="${depResLiq.codigo}">${depResLiq.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label class="control-label col-md-2"><spring:message code="obs" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-10">
																<div class="input-group">
																	<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="obs" />" class="form-control" id="obsCaractHig" name="obsCaractHig" />
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
											</div>
											<div class="tab-pane" id="tab4">
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="step4" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-9">
																<input type="text" id="idFactSocioEc" name="idFactSocioEc" class="form-control"/>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="piso" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="piso" />" name="tipoPiso" id="tipoPiso" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${pisos}" var="piso"> 
																		<option value="${piso.codigo}">${piso.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="techo" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="techo" />" name="tipoTecho" id="tipoTecho" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${techos}" var="techo"> 
																		<option value="${techo.codigo}">${techo.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="paredes" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="paredes" />" name="tipoPared" id="tipoPared" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${paredes}" var="pared"> 
																		<option value="${pared.codigo}">${pared.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="cult.san" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="cult.san" />" name="culturaSanitaria" id="culturaSanitaria" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${culturasSanitaria}" var="culturaSanitaria"> 
																		<option value="${culturaSanitaria.codigo}">${culturaSanitaria.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="car.psic" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="car.psic" />" name="carPsicosociales" id="carPsicosociales" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${carsPsicosociales}" var="carPsicosocial"> 
																		<option value="${carPsicosocial.codigo}">${carPsicosocial.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="nec.basicas" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="nec.basicas" />" name="satNecBasicas" id="satNecBasicas" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${sinons}" var="sn"> 
																		<option value="${sn.codigo}">${sn.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="ten.viv" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="ten.viv" />" name="tenenciaVivienda" id="tenenciaVivienda" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${tenenciasVivienda}" var="tenenciaVivienda"> 
																		<option value="${tenenciaVivienda.codigo}">${tenenciaVivienda.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="acc.com" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select multiple="multiple" class="multi-select" id=accionesComunitarias name="accionesComunitarias">
																	<c:forEach items="${accionesComunitarias}" var="accionComunitaria"> 
																		<option value="${accionComunitaria.codigo}">${accionComunitaria.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="obs" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<textarea placeholder="<spring:message code="please.enter" /> <spring:message code="obs" />" class="form-control"  rows="3" id="obsFactSocioEc" name="obsFactSocioEc"></textarea>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
											</div>
											<div class="tab-pane" id="tab5">
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="step5" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-9">
																<input type="text" id="idFuncFamiliar" name="idFuncFamiliar" class="form-control"/>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="fam.size" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="fam.size" />" name="tamFamilia" id="tamFamilia" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${tamanos}" var="tamano"> 
																		<option value="${tamano.codigo}">${tamano.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="onto" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="onto" />" name="ontogenesis" id="ontogenesis" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${ontos}" var="onto"> 
																		<option value="${onto.codigo}">${onto.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="stage.cycle" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="stage.cycle" />" name="etapaCicloVital" id="etapaCicloVital" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${etapasCicloVital}" var="etapaCicloVital"> 
																		<option value="${etapaCicloVital.codigo}">${etapaCicloVital.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="crisis.norm" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select multiple="multiple" class="multi-select" id=crisisNormativa name="crisisNormativa">
																	<c:forEach items="${crisisNormativas}" var="crisisNormativa"> 
																		<option value="${crisisNormativa.codigo}">${crisisNormativa.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="crisis.para" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select multiple="multiple" class="multi-select" id=crisisParanormativa name="crisisParanormativa">
																	<c:forEach items="${crisisParanormativas}" var="crisisParanormativa"> 
																		<option value="${crisisParanormativa.codigo}">${crisisParanormativa.valor}</option> 
																	</c:forEach>
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
															<label class="control-label col-md-6"><spring:message code="med.trad" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="med.trad" />" name="usoMedTradicional" id="usoMedTradicional" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${sinons}" var="sn"> 
																		<option value="${sn.codigo}">${sn.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="obs" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<textarea placeholder="<spring:message code="please.enter" /> <spring:message code="obs" />" class="form-control"  rows="3" id="obsFuncFamiliar" name="obsFuncFamiliar"></textarea>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
											</div>
											<div class="tab-pane" id="tab6">
												<h4 class="form-section"><spring:message code="step1" /></h4>
												<!-- START ROW -->
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="silais" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="silais">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="muni" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="municipio">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="sector" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="sector">
																</p>
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
																<p class="form-control-static" data-display="comunidad">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="novivienda" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="numVivienda">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="nofamilia" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="numFamilia">
																</p>
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
																<p class="form-control-static" data-display="numFicha">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="personnel" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="personaVisita">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="visit.date" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="fechaVisita">
																</p>
															</div>
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
																<p class="form-control-static" data-display="hacinamiento">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="animdom" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="animalesDom">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="risks.nat" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="riesgoNatural">
																</p>
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
																<p class="form-control-static" data-display="riesgoMeteorologico">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="risks.bio" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="riesgoBiologico">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="risks.social" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="riesgoSocial">
																</p>
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
																<p class="form-control-static" data-display="factoresMedAmb">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="comb.coc" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="combCocinar">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="abast.agua" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="aAgua">
																</p>
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
																<p class="form-control-static" data-display="cAgua">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="elect" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="electricidad">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="dep.excretas" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="depExcretas">
																</p>
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
																<p class="form-control-static" data-display="depBasura">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="dep.resliq" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="depResLiq">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="obs" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="obsCaractHig">
																</p>
															</div>
														</div>
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
																<p class="form-control-static" data-display="tipoPiso">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="techo" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="tipoTecho">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="paredes" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="tipoPared">
																</p>
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
																<p class="form-control-static" data-display="culturaSanitaria">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="car.psic" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="carPsicosociales">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="nec.basicas" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="satNecBasicas">
																</p>
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
																<p class="form-control-static" data-display="tenenciaVivienda">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="acc.com" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="accionesComunitarias">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="obs" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="obsFactSocioEc">
																</p>
															</div>
														</div>
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
																<p class="form-control-static" data-display="tamFamilia">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="onto" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="ontogenesis">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="stage.cycle" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="etapaCicloVital">
																</p>
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
																<p class="form-control-static" data-display="crisisNormativa">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="crisis.para" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="crisisParanormativa">
																</p>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="med.trad" /></label>
															<div class="col-md-6">
																<p class="form-control-static" data-display="usoMedTradicional">
																</p>
															</div>
														</div>
													</div>
												</div>
												<!-- END ROW -->
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-offset-3 col-md-9">
													<a href="javascript:;" class="btn btn-default button-previous">
													<i class="m-icon-swapleft"></i><spring:message code="back" /></a>
													<a href="javascript:;" class="btn btn-info button-next">
													<spring:message code="continue" /> <i class="m-icon-swapright m-icon-white"></i>
													</a>
													<a href="javascript:;" class="btn btn-success button-submit">
													<spring:message code="end" /> <i class="m-icon-swapright m-icon-white"></i>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- END PORTLET -->
					<!-- /.modal -->
					<div id="personamodalform" class="modal fade" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog modal-full">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title"><i class="fa fa-male"></i><i class="fa fa-female"></i> <spring:message code="family.info" /></h4>
								</div>
								<div class="modal-body">
									<div class="scroller" style="height:100%" data-always-visible="1" data-rail-visible1="1">
										<div class="portlet" id="form_wizard_2">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-plus"></i> <spring:message code="person.add" /> -
													<span class="step-title">
														 <spring:message code="step" /> 1 <spring:message code="of" /> 4
													</span>
												</div>
												<div class="tools hidden-xs">
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN FORM-->
												<form action="#" class="form-horizontal" data-role="form" id="add_person_form">
													<div class="form-wizard">
														<ul class="nav nav-pills nav-justified steps hidden-xs">
															<li>
																<a href="#tab2_1" data-toggle="tab" class="step">
																<span class="number">
																	 1
																</span>
																<span class="desc">
																	<i class="fa fa-check"></i> <spring:message code="tab1" />
																</span>
																</a>
															</li>
															<li>
																<a href="#tab2_2" data-toggle="tab" class="step">
																<span class="number">
																	 2
																</span>
																<span class="desc">
																	<i class="fa fa-check"></i> <spring:message code="tab2" />
																</span>
																</a>
															</li>
															<li>
																<a href="#tab2_3" data-toggle="tab" class="step">
																<span class="number">
																	 3
																</span>
																<span class="desc">
																	<i class="fa fa-check"></i> <spring:message code="tab3" />
																</span>
																</a>
															</li>
															<li>
																<a href="#tab2_4" data-toggle="tab" class="step">
																<span class="number">
																	 4
																</span>
																<span class="desc">
																	<i class="fa fa-check"></i> <spring:message code="tab4" />
																</span>
																</a>
															</li>
														</ul>
														<div id="bar" class="progress progress-striped" data-role="progressbar">
															<div class="progress-bar progress-bar-success">
															</div>
														</div>
														<div class="alert alert-danger display-none">
															<button class="close" data-close="alert"></button>
															<spring:message code="form.errors" />
														</div>
														<div class="alert alert-success display-none">
															<button class="close" data-close="alert"></button>
															<spring:message code="form.success" />
														</div>
														<div class="form-body">
															<div class="tab-content persona">
																<div class="tab-pane active" id="tab2_1">
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group" hidden="true">
																				<label class="control-label col-md-6"><spring:message code="nofamilia" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="idFamiliaPerson" name="idFamiliaPerson" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group" hidden="true">
																				<label class="control-label col-md-6"><spring:message code="nopersona" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="idPersona" name="idPersona" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- START ROW -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="nopersona" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="numPersona" name="numPersona" readonly placeholder="<spring:message code="please.enter" /> <spring:message code="nopersona" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-numeric-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="completename" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="nombres" name="nombres" placeholder="<spring:message code="please.enter" /> <spring:message code="completename" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
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
																				<label class="control-label col-md-6"><spring:message code="lastname1" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="primerApellido" name="primerApellido" placeholder="<spring:message code="please.enter" /> <spring:message code="lastname1" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="lastname2" />:
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="segundoApellido" name="segundoApellido" placeholder="<spring:message code="please.enter" /> <spring:message code="lastname2" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- END ROW -->
																	<div class="form-section"></div>
																	<!-- START ROW -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.id" />:
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="cedula" name="cedula" placeholder="<spring:message code="please.enter" /> <spring:message code="person.id" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="birthdate" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-end-date="+0d">
																						<input id="fechaNacimiento" name="fechaNacimiento" type="text" class="form-control" placeholder="<spring:message code="please.enter" /> <spring:message code="birthdate" />">
																						<span class="input-group-btn">
																							<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
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
																				<label class="control-label col-md-6"><spring:message code="birthcert" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="birthcert" />" name="actaNacimiento" id="actaNacimiento" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.age" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="edad" name="edad" readonly placeholder="<spring:message code="please.enter" /> <spring:message code="person.age" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- END ROW -->
																	<!-- START ROW -->
																	<div class="row" hidden="true">
																		<div class="col-md-4">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.age" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="edada" name="edada" readonly placeholder="<spring:message code="please.enter" /> <spring:message code="person.age" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-4">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.age" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="edadm" name="edadm" readonly placeholder="<spring:message code="please.enter" /> <spring:message code="person.age" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-4">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.age" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<div class="input-group">
																						<input type="text" id="edadd" name="edadd" readonly placeholder="<spring:message code="please.enter" /> <spring:message code="person.age" />" class="form-control"/>
																						<span class="input-group-addon">
																							<i class="fa fa-sort-alpha-asc"></i>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- END ROW -->
																</div>
																<div class="tab-pane" id="tab2_2">
																	<!-- START ROW -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.eth" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.eth" />" name="etnia" id="etnia" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${etnias}" var="etnia"> 
																							<option value="${etnia.codigo}">${etnia.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.sex" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.sex" />" name="sexo" id="sexo" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sexos}" var="sexo"> 
																							<option value="${sexo.codigo}">${sexo.valor}</option> 
																						</c:forEach>
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
																				<label class="control-label col-md-6"><spring:message code="person.scholar" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.scholar" />" name="escolaridad" id="escolaridad" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${escolaridades}" var="escolaridad"> 
																							<option value="${escolaridad.codigo}">${escolaridad.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.occup" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.occup" />" name="ocupacion" id="ocupacion" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${ocupaciones}" var="ocupacion"> 
																							<option value="${ocupacion.codigo}">${ocupacion.nombre}</option> 
																						</c:forEach>
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
																				<label class="control-label col-md-6"><spring:message code="person.religio" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.religio" />" name="religion" id="religion" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${religiones}" var="religion"> 
																							<option value="${religion.codigo}">${religion.valor}</option> 
																						</c:forEach>
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
																				<label class="control-label col-md-6"><spring:message code="person.pregnant" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select disabled data-placeholder="<spring:message code="select" /> <spring:message code="person.pregnant" />" name="embarazada" id="embarazada" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.cpn" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select disabled data-placeholder="<spring:message code="select" /> <spring:message code="person.cpn" />" name="cpnActualizado" id="cpnActualizado" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
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
																				<label class="control-label col-md-6"><spring:message code="person.mef" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select disabled data-placeholder="<spring:message code="select" /> <spring:message code="person.mef" />" name="mujerEdadFertil" id="mujerEdadFertil" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.pf" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select disabled data-placeholder="<spring:message code="select" /> <spring:message code="person.pf" />" name="planFamiliar" id="planFamiliar" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
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
																				<label class="control-label col-md-6"><spring:message code="person.m1a" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.m1a" />" name="men1A" id="men1A" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.vpdc" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select disabled data-placeholder="<spring:message code="select" /> <spring:message code="person.vpdc" />" name="men1AVPCD" id="men1AVPCD" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${sinons}" var="sn"> 
																							<option value="${sn.codigo}">${sn.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- END ROW -->
																</div>
																<div class="tab-pane" id="tab2_3">
																	<!-- START ROW -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-4"><spring:message code="person.frm" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-8">
																					<select multiple="multiple" class="multi-select" id=factRiesgoMod name="factRiesgoMod">
																						<c:forEach items="${frms}" var="frm"> 
																							<option value="${frm.codigo}">${frm.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-4"><spring:message code="person.frnm" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-8">
																					<select multiple="multiple" class="multi-select" id=factRiesgoNoMod name="factRiesgoNoMod">
																						<c:forEach items="${frnms}" var="frnm"> 
																							<option value="${frnm.codigo}">${frnm.valor}</option> 
																						</c:forEach>
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
																				<label class="control-label col-md-4"><spring:message code="person.frs" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-8">
																					<select multiple="multiple" class="multi-select" id=factRiesgoSocial name="factRiesgoSocial">
																						<c:forEach items="${frss}" var="frs"> 
																							<option value="${frs.codigo}">${frs.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-4"><spring:message code="person.disc" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-8">
																					<select multiple="multiple" class="multi-select" id=discapacidades name="discapacidades">
																						<c:forEach items="${discps}" var="discp"> 
																							<option value="${discp.codigo}">${discp.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- END ROW -->
																</div>
																<div class="tab-pane" id="tab2_4">
																	<!-- START ROW -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label class="control-label col-md-6"><spring:message code="person.gd" />:
																				<span class="required">
																					 *
																				</span>
																				</label>
																				<div class="col-md-6">
																					<select data-placeholder="<spring:message code="select" /> <spring:message code="person.gd" />" name="grupoDisp" id="grupoDisp" class="form-control">
																						<option value=""></option>
																						<c:forEach items="${gds}" var="gd"> 
																							<option value="${gd.codigo}">${gd.valor}</option> 
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																	</div>
																	<!-- END ROW -->
																	<!-- START ROW -->
																	<div class="row">
																		<div class="col-md-6">
																			<!-- BEGIN TABLE PORTLET-->
																			<div class="portlet">
																				<div class="portlet-title">
																					<div class="caption">
																						<i class="fa fa-stethoscope"></i><spring:message code="enf.list" />
																					</div>
																					<div class="tools">
																					</div>
																				</div>
																				<div class="portlet-body">
																					<div class="table-toolbar">
																						<div class="btn-group">
																							<a class="btn btn-success" data-toggle="modal" onclick="validarEnf1();" data-target="#enferform"><spring:message code="add" /> <i class="fa fa-plus"></i></a>																		
																						</div>
																					</div>
																					<div class="table-responsive">
																					<table class="table table-striped table-hover table-bordered" id="lista_enfermedades">
																					<thead>
																						<tr>
																							<th><spring:message code="enf" /></th>
					        																<th><spring:message code="enf.fec" /></th>
					        																<th><spring:message code="enf.atn" /></th>
																						</tr>
																					</thead>
																						
																					</table>
																					</div>
																				</div>
																			</div>
																			<!-- END TABLE PORTLET-->
																		</div>
																		<div class="col-md-6">
																			<!-- BEGIN TABLE PORTLET-->
																			<div class="portlet">
																				<div class="portlet-title">
																					<div class="caption">
																						<i class="fa fa-stethoscope"></i><spring:message code="enf.list" /> <spring:message code="enfsoc.list" />
																					</div>
																					<div class="tools">
																					</div>
																				</div>
																				<div class="portlet-body">
																					<div class="table-toolbar">
																						<div class="btn-group">
																							<a class="btn btn-success" data-toggle="modal" onclick="validarEnf2();" data-target="#enfersocform"><spring:message code="add" /> <i class="fa fa-plus"></i></a>																		
																						</div>
																					</div>
																					<div class="table-responsive">
																					<table class="table table-striped table-hover table-bordered" id="lista_enfermedadessoc">
																					<thead>
																						<tr>
																							<th><spring:message code="enf" /></th>
					        																<th><spring:message code="enf.fec" /></th>
					        																<th><spring:message code="enf.atn" /></th>
																						</tr>
																					</thead>
																						
																					</table>
																					</div>
																				</div>
																			</div>
																			<!-- END TABLE PORTLET-->
																		</div>
																	</div>
																	<!-- END ROW -->
																	<!-- START ROW -->
																	<div class="row modal-footer">
																		<button type="button" id="save-person" class="btn btn-info"><spring:message code="save" /></button>
																	</div>
																	<!-- END ROW -->
																</div>
															</div>
														</div>
														<div class="form-actions fluid">
															<div class="row">
																<div class="col-md-12">
																	<div class="col-md-offset-3 col-md-9">
																		<a href="javascript:;" class="btn btn-default button-previous">
																		<i class="m-icon-swapleft"></i><spring:message code="back" /></a>
																		<a href="javascript:;" class="btn btn-info button-next">
																		<spring:message code="continue" /> <i class="m-icon-swapright m-icon-white"></i>
																		</a>
																		<a href="javascript:;" class="btn btn-success button-submit">
																		<spring:message code="end" /> <i class="m-icon-swapright m-icon-white"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</form>
												<!-- END FORM-->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END MODAL -->
					<!-- /.modal -->
					<div id="enferform" class="modal fade" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog modal-wide">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title"><i class="fa fa-stethoscope"></i> <spring:message code="enf.list" /></h4>
								</div>
								<div class="modal-body">
									<div class="scroller" style="height:50%" data-always-visible="1" data-rail-visible1="1">
										<div class="portlet">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-plus"></i><spring:message code="add" /> <spring:message code="enf" />
												</div>
												<div class="tools">
													
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN FORM-->
												<form action="#" class="form-horizontal" data-role="form" id="add_enfermedad_form">
													<div class="alert alert-danger display-none">
														<button class="close" data-close="alert"></button>
														<spring:message code="form.errors" />
													</div>
													<div class="alert alert-success display-none">
														<button class="close" data-close="alert"></button>
														<spring:message code="form.success" />
													</div>
													<div class="form-body">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="nopersona" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<input type="text" id="idPersonaEnf" name="idPersonaEnf" class="form-control"/>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="enf" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<input type="text" id="idEnfermedad" name="idEnfermedad" class="form-control"/>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="enf" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<input type="text" id="enfermedad" name="enfermedad" class="form-control select2" data-placeholder="<spring:message code="select" /> <spring:message code="enf" />">
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="enf.fec" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-end-date="+0d">
																	<input id="fechaOcurrencia" name="fechaOcurrencia" type="text" class="form-control" placeholder="<spring:message code="please.enter" /> <spring:message code="enf.fec" />">
																	<span class="input-group-btn">
																		<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="enf.atn" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="enf.atn" />" name="personaAtendio" id="personaAtendio" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${profesiones}" var="profesion"> 
																		<option value="${profesion.codigo}">${profesion.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" id="save-enf" class="btn btn-info"><spring:message code="save" /></button>
									<button type="button" id="save-enf-add" class="btn btn-success"><spring:message code="saveadd" /></button>
									<button type="button" id="dismiss-modalenf" data-dismiss="modal" class="btn btn-danger"><spring:message code="end" /></button>
								</div>
							</div>
						</div>
					</div>
					<!-- END MODAL -->
					<!-- /.modal -->
					<div id="enfersocform" class="modal fade" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog modal-wide">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title"><i class="fa fa-stethoscope"></i> <spring:message code="enf.list" /></h4>
								</div>
								<div class="modal-body">
									<div class="scroller" style="height:50%" data-always-visible="1" data-rail-visible1="1">
										<div class="portlet">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-plus"></i><spring:message code="add" /> <spring:message code="enf" />
												</div>
												<div class="tools">
													
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN FORM-->
												<form action="#" class="form-horizontal" data-role="form" id="add_enfermedadsoc_form">
													<div class="alert alert-danger display-none">
														<button class="close" data-close="alert"></button>
														<spring:message code="form.errors" />
													</div>
													<div class="alert alert-success display-none">
														<button class="close" data-close="alert"></button>
														<spring:message code="form.success" />
													</div>
													<div class="form-body">
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="nopersona" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<input type="text" id="idPersonaEnfSoc" name="idPersonaEnfSoc" class="form-control"/>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group" hidden="true">
															<label class="control-label col-md-6"><spring:message code="enf" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group">
																	<input type="text" id="idEnfermedadSoc" name="idEnfermedadSoc" class="form-control"/>
																	<span class="input-group-addon">
																		<i class="fa fa-sort-alpha-asc"></i>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="enf" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="enf" />" name="enfermedadsoc" id="enfermedadsoc" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${enfermedadessocs}" var="enfermedassoc"> 
																		<option value="${enfermedassoc.codigo}">${enfermedassoc.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="enf.fec" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-end-date="+0d">
																	<input id="fechaOcurrenciaSoc" name="fechaOcurrenciaSoc" type="text" class="form-control" placeholder="<spring:message code="please.enter" /> <spring:message code="enf.fec" />">
																	<span class="input-group-btn">
																		<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="enf.atn" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" /> <spring:message code="enf.atn" />" name="personaAtendioSoc" id="personaAtendioSoc" class="form-control">
																	<option value=""></option>
																	<c:forEach items="${profesiones}" var="profesion"> 
																		<option value="${profesion.codigo}">${profesion.valor}</option> 
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" id="save-enfsoc" class="btn btn-info"><spring:message code="save" /></button>
									<button type="button" id="save-enfsoc-add" class="btn btn-success"><spring:message code="saveadd" /></button>
									<button type="button" id="dismiss-modalenfsoc" data-dismiss="modal" class="btn btn-danger"><spring:message code="end" /></button>
								</div>
							</div>
						</div>
					</div>
					<!-- END MODAL -->
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
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js" var="jQBWizard" />
<script type="text/javascript" src="${jQBWizard}"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="selectDos" />
<script type="text/javascript" src="${selectDos}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js" var="jQueryMultiSelect" />
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="datepicker" />
<script type="text/javascript" src="${datepicker}"></script>
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
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
<spring:url value="/resources/scripts/hsf-wizard.js" var="hsfWizard" />
<script src="${hsfWizard}"></script>
<spring:url value="/resources/scripts/hsf-wizard-modal-persona.js" var="hsfWizard2" />
<script src="${hsfWizard2}"></script>
<spring:url value="/resources/scripts/hsf-wizard-modal-enf.js" var="hsfWizard3" />
<script src="${hsfWizard3}"></script>
<spring:url value="/resources/scripts/hsf-wizard-modal-enfsoc.js" var="hsfWizard4" />
<script src="${hsfWizard4}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/info/newPersona" var="addPersonUrl"/>
<spring:url value="/info/newEnfermedad" var="addEnfermedadUrl"/>
<spring:url value="/info/newEnfermedadSC" var="addEnfermedadSCUrl"/>
<spring:url value="/info/newFamiliaVisita" var="addFamiliaVisitaUrl"/>
<spring:url value="/info/newCarHigSan" var="addCarHigSanUrl"/>
<spring:url value="/info/newFactSocEc" var="addFactSocEcUrl"/>
<spring:url value="/info/newFuncFam" var="addFuncFamUrl"/>
<spring:url value="/opciones/municipios" var="opcMuniUrl"/>
<spring:url value="/opciones/sectores" var="opcSectUrl"/>
<spring:url value="/opciones/comunidades" var="opcComuUrl"/>

<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfnuevo").removeClass("hsfnuevo").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = {exportar: "${exportar}"
				, valPersonas: "${valPersonas}"
				, addFamiliaVisitaUrl: "${addFamiliaVisitaUrl}"
				, addCarHigSanUrl: "${addCarHigSanUrl}"
				, addFactSocEcUrl: "${addFactSocEcUrl}"
				, addFuncFamUrl: "${addFuncFamUrl}"
				, opcMuniUrl: "${opcMuniUrl}"
				, opcSectUrl: "${opcSectUrl}"
				, opcComuUrl: "${opcComuUrl}"
				, processSuccess: "${processSuccess}"
				, processError: "${processError}"
				, deniedError: "${deniedError}"
				,language:"${pageContext.request.locale.language}" };
		FormWizardHSF.init(parametros1);
	});
	function validarModalPersona()
	{
		var parametros2 = {addPersonUrl: "${addPersonUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, deniedError: "${deniedError}"
			};
		FormWizardHSFModalPersonaValidation.init(parametros2);
	}
	function validarEnf1()
	{
		var parametros3 = {addEnfermedadUrl: "${addEnfermedadUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, deniedError: "${deniedError}"
			};
		FormWizardHSFModalEnfValidation.init(parametros3);
	}
	function validarEnf2()
	{
		var parametros4 = {addEnfermedadSCUrl: "${addEnfermedadSCUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, deniedError: "${deniedError}"
			};
		FormWizardHSFModalEnfSocValidation.init(parametros4);
	}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>