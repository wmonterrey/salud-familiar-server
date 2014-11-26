<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
<spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss" />
<link rel="stylesheet" type="text/css" href="${jqmscss}"/>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.css" var="dtbootcss" />
<link rel="stylesheet" href="${dtbootcss}"/>
<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-footer-fixed">
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
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/info/searchHsf" htmlEscape="true "/>"><spring:message code="hsf.search" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/info/viewHsf/{idFamilia}" var="familiaUrl">
				<spring:param name="idFamilia" value="${persona.familia.idFamilia}" />
			</spring:url>
			<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
			<c:set var="processError"><spring:message code="process.error" /></c:set>
			<c:set var="quitarenf"><spring:message code="disable.enf" /></c:set>
			<c:set var="deniedError"><spring:message code="denied" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet" id="form_wizard_2">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-pencil"></i><spring:message code="edit" /> - <spring:message code="person.list" /> -
								<span class="step-title">
									 <spring:message code="step" /> 1 <spring:message code="of" /> 4
								</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
							<div class="actions">
								<a href="${fn:escapeXml(familiaUrl)}" class="btn btn-danger btn-sm"><i class="fa fa-undo"></i> <spring:message code="cancel" /></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="add-person-form" class="form-horizontal">
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
								</div>
								<div class="alert alert-danger display-hide">
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
																<input type="text" value="${persona.familia.idFamilia}" id="idFamiliaPerson" name="idFamiliaPerson" class="form-control"/>
																<span class="input-group-addon">
																	<i class="fa fa-sort-alpha-asc"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-6" hidden="true">
													<div class="form-group">
														<label class="control-label col-md-6"><spring:message code="nopersona" />:
														<span class="required">
															 *
														</span>
														</label>
														<div class="col-md-6">
															<div class="input-group">
																<input type="text" value="${persona.idPersona}" id="idPersona" name="idPersona" class="form-control"/>
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
																<input type="text" value="${persona.numPersona}" id="numPersona" name="numPersona" readonly placeholder="<spring:message code="please.enter" /> <spring:message code="nopersona" />" class="form-control"/>
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
																<input type="text" value="${persona.nombres}" id="nombres" name="nombres" placeholder="<spring:message code="please.enter" /> <spring:message code="completename" />" class="form-control"/>
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
																<input type="text" value="${persona.primerApellido}" id="primerApellido" name="primerApellido" placeholder="<spring:message code="please.enter" /> <spring:message code="lastname1" />" class="form-control"/>
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
																<input type="text" value="${persona.segundoApellido}" id="segundoApellido" name="segundoApellido" placeholder="<spring:message code="please.enter" /> <spring:message code="lastname2" />" class="form-control"/>
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
																<input type="text" value="${persona.cedula}" id="cedula" name="cedula" placeholder="<spring:message code="please.enter" /> <spring:message code="person.id" />" class="form-control"/>
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
																<input id="fechaNacimiento" name="fechaNacimiento" type="text" value="<fmt:formatDate value="${persona.fechaNacimiento}" pattern="dd/MM/yyyy" />" class="form-control" placeholder="<spring:message code="please.enter" /> <spring:message code="birthdate" />">
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.actaNacimiento}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${etnia.codigo eq persona.etnia.codigo}">
																			<option selected value="${etnia.codigo}">${etnia.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${etnia.codigo}">${etnia.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sexo.codigo eq persona.sexo.codigo}">
																			<option selected value="${sexo.codigo}">${sexo.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sexo.codigo}">${sexo.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${escolaridad.codigo eq persona.escolaridad.codigo}">
																			<option selected value="${escolaridad.codigo}">${escolaridad.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${escolaridad.codigo}">${escolaridad.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${ocupacion.codigo eq persona.ocupacion.codigo}">
																			<option selected value="${ocupacion.codigo}">${ocupacion.nombre}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${ocupacion.codigo}">${ocupacion.nombre}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${religion.codigo eq persona.religion.codigo}">
																			<option selected value="${religion.codigo}">${religion.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${religion.codigo}">${religion.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.embarazada}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.cpnActualizado}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.mujerEdadFertil}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.planFamiliar}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.men1A}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${sn.codigo eq persona.men1AVPCD}">
																			<option selected value="${sn.codigo}">${sn.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${sn.codigo}">${sn.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
																	<c:choose> 
																		<c:when test="${fn:contains(persona.factRiesgoMod, frm.codigo)}">
																			<option selected value="${frm.codigo}">${frm.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${frm.codigo}">${frm.valor}</option>
																		</c:otherwise>
																	</c:choose>
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
																	<c:choose> 
																		<c:when test="${fn:contains(persona.factRiesgoNoMod, frnm.codigo)}">
																			<option selected value="${frnm.codigo}">${frnm.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${frnm.codigo}">${frnm.valor}</option>
																		</c:otherwise>
																	</c:choose>
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
																	<c:choose> 
																		<c:when test="${fn:contains(persona.factRiesgoSocial, frs.codigo)}">
																			<option selected value="${frs.codigo}">${frs.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${frs.codigo}">${frs.valor}</option>
																		</c:otherwise>
																	</c:choose>
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
																	<c:choose> 
																		<c:when test="${fn:contains(persona.discapacidades, discp.codigo)}">
																			<option selected value="${discp.codigo}">${discp.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${discp.codigo}">${discp.valor}</option>
																		</c:otherwise>
																	</c:choose>
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
																	<c:choose> 
																		<c:when test="${gd.codigo eq persona.grupoDisp.codigo}">
																			<option selected value="${gd.codigo}">${gd.valor}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${gd.codigo}">${gd.valor}</option>
																		</c:otherwise>
																	</c:choose> 
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
       																<th></th>
																</tr>
															</thead>
															<c:forEach items="${enfermedades}" var="enfermedad">
																<tr>
																	<td><c:out value="${enfermedad.enfermedad.nombreCie10}" /></td>
																	<td><fmt:formatDate value="${enfermedad.fechaOcurrencia}" pattern="yyyy-MM-dd" /></td>
																	<td><c:out value="${enfermedad.personaAtendio.valor}" /></td>
																	<td><a data-toggle="modal" data-enf="${enfermedad.enfermedad.nombreCie10}" data-id= "${enfermedad.idEnfermedad}" class="btn btn-default btn-xs quitarenf"><i class="fa fa-trash-o"></i></a></td>
																</tr>
															</c:forEach>	
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
       																<th></th>
																</tr>
															</thead>
															<c:forEach items="${enfermedadesSoc}" var="enfsoc">
																<tr>
																	<td><c:out value="${enfsoc.enfermedad.valor}" /></td>
																	<td><fmt:formatDate value="${enfsoc.fechaOcurrencia}" pattern="yyyy-MM-dd" /></td>
																	<td><c:out value="${enfsoc.personaAtendio.valor}" /></td>
																	<td><a data-toggle="modal" data-enf="${enfsoc.enfermedad.valor}" data-id= "${enfsoc.idEnfSocioC}" class="btn btn-default btn-xs quitarenfsoc"><i class="fa fa-trash-o"></i></a></td>
																</tr>
															</c:forEach>	
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
							</form>
						</div>
					</div>
					<div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
									<div id="tituloEnfModal"></div>
								</div>
								<div class="modal-body">
									<input type=hidden id="enfModal"/>
									<div id="cuerpoEnfModal"></div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
									<button type="button" id="quitar-enf" class="btn btn-info"><spring:message code="ok" /></button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<div class="modal fade" id="basic2" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
									<div id="tituloEnfSocModal"></div>
								</div>
								<div class="modal-body">
									<input type=hidden id="enfSocModal"/>
									<div id="cuerpoEnfSocModal"></div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
									<button type="button" id="quitar-enf-soc" class="btn btn-info"><spring:message code="ok" /></button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
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
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
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
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/hsf-edit6.js" var="hsfEdit6" />
<script src="${hsfEdit6}"></script>
<spring:url value="/resources/scripts/hsf-wizard-modal-enf.js" var="hsfWizard3" />
<script src="${hsfWizard3}"></script>
<spring:url value="/resources/scripts/hsf-wizard-modal-enfsoc.js" var="hsfWizard4" />
<script src="${hsfWizard4}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/info/newPersona" var="editPersonaUrl"/>
<spring:url value="/info/newEnfermedad" var="addEnfermedadUrl"/>
<spring:url value="/info/quitarenf" var="quitarEnfermedadUrl"/>
<spring:url value="/info/quitarenfsoc" var="quitarEnfermedadSocUrl"/>
<spring:url value="/info/newEnfermedadSC" var="addEnfermedadSCUrl"/>

<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfsearch").removeClass("hsfsearch").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = {editPersonaUrl: "${editPersonaUrl}"
			, familiaUrl: "${familiaUrl}"
			, quitarEnfermedadUrl: "${quitarEnfermedadUrl}"
			, quitarEnfermedadSocUrl: "${quitarEnfermedadSocUrl}"
			, quitarenf: "${quitarenf}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, deniedError: "${deniedError}"
			,language:"${pageContext.request.locale.language}" };
		FormEdit6HSF.init(parametros1);
		$('#fechaNacimiento').change();
	});
	
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