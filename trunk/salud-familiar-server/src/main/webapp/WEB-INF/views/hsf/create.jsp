<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<jsp:include page="../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<fmt:message key="welcome" /> <small><fmt:message key="heading" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><fmt:message key="dashboard" /></a>
							<i class="fa fa-angle-right"></i>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> Historia de Salud Familiar -
								<span class="step-title">
									 Paso 1 de 2
								</span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
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
													<i class="fa fa-check"></i> Datos Generales
												</span>
												</a>
											</li>
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
													 2
												</span>
												<span class="desc">
													<i class="fa fa-check"></i> Personas
												</span>
												</a>
											</li>
										</ul>
										<div id="bar" class="progress progress-striped" data-role="progressbar">
											<div class="progress-bar progress-bar-success">
											</div>
										</div>
										<div class="tab-content">
											<div class="alert alert-danger display-none">
												<button class="close" data-close="alert"></button>
												Tiene errores. Favor verificar!
											</div>
											<div class="alert alert-success display-none">
												<button class="close" data-close="alert"></button>
												Su formulario se validó con éxito!
											</div>
											<div class="tab-pane active" id="tab1">
												<h3 class="block">Datos Visita</h3>
												
												<div class="form-group">
													<label class="control-label col-md-3">No Ficha:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" placeholder="Ingresar el número de ficha" class="form-control" id="wzFicha" name="numFicha" maxlength="6"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-md-3">Fecha de visita:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input id="wzFechaVisita" placeholder="Ingrese la fecha de la visita" name="fechaVisita" class="form-control form-control-inline date-picker" type="text" value=""/>
														
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-md-3">Nombre:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" placeholder="Nombre de quien dirige la visita" class="form-control" id="wzPersonaVisita" name="personaVisita" maxlength="25"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-md-3">Profesión:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<select data-placeholder="Profesión de quien dirige la visita" name="personaVisitaProfesion" id="wzProfesion" class="form-control">
															<option value=""></option>
															<c:forEach items="${profesiones}" var="profesion"> 
																<option value="${profesion.codigo}">${profesion.valor}</option> 
															</c:forEach>
														</select>
													</div>
												</div>

												
												<h3 class="block">Familia <a class="btn btn-default" data-toggle="modal" href="#responsive">Seleccionar</a></h3>
												
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">SILAIS:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzSilais" name="silais" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">Municipio:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzMunicipio" name="municipio" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">Sector:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzSector" name="sector" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">Barrio/Comunidad:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzComunidad" name="comunidad" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">No Vivienda:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzVivienda" name="numVivienda" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">No Familia:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzFamilia" name="numFamilia" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-1">Dirección:
													<span class="required">
														 *
													</span>
													</label>
													<div class="col-md-9">
														<input type="text" class="form-control" id="wzDireccion" name="direccion" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">Latitud:
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzLatitud" name="latitud" readonly/>
													</div>
												</div>
												</div>
												<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-2">Longitud:
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="wzlongitud" name="longitud" readonly/>
													</div>
												</div>
												</div>
												<div class="form-group">
												</div>
											</div>
											<div class="tab-pane" id="tab2">
												<h3 class="block">Provide your profile details</h3>
												<div class="form-group">
													<label class="control-label col-md-3">Username:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="fechaVisita">
														</p>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-offset-3 col-md-9">
													<a href="javascript:;" class="btn btn-default button-previous">
													<i class="m-icon-swapleft"></i> Back </a>
													<a href="javascript:;" class="btn btn-info button-next">
													Continue <i class="m-icon-swapright m-icon-white"></i>
													</a>
													<a href="javascript:;" class="btn btn-success button-submit">
													Submit <i class="m-icon-swapright m-icon-white"></i>
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
					<!-- /.modal -->
					<div id="responsive" class="modal fade" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog modal-full">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
									<h4 class="modal-title"><i class="fa fa-search"></i> Buscar Familia</h4>
								</div>
								<div class="modal-body">
									<div class="scroller" style="height:500px" data-always-visible="1" data-rail-visible1="1">
										<div class="portlet">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-reorder"></i>Parámetros de búsqueda
												</div>
												<div class="tools">
													<a href="javascript:;" class="collapse"></a>
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN FORM-->
												<form action="#" class="form-horizontal" data-role="form">
													<div class="form-body">
														<div class="row">
															<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-3">SILAIS:
																<span class="required">
																	 *
																</span>
																</label>
																<div class="col-md-5">
																	<select name="silais" id="bqSilais" class="form-control">
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
																<label class="control-label col-md-3">Municipio:
																<span class="required">
																	 *
																</span>
																</label>
																<div class="col-md-5">
																	<select name="municipio" id="bqMunicipio" class="form-control">
																		<option value=""></option>
																	</select>
																</div>
															</div>
															</div>
															<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-3">Sector:
																<span class="required">
																	 *
																</span>
																</label>
																<div class="col-md-5">
																	<select name="sector" id="bqSector" class="form-control">
																		<option value=""></option>
																	</select>
																</div>
															</div>
															</div>
															<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-3">Barrio/Comunidad:
																<span class="required">
																	 *
																</span>
																</label>
																<div class="col-md-5">
																	<select name="comunidad" id="bqComunidad" class="form-control">
																		<option value=""></option>
																	</select>
																</div>
															</div>
															</div>
														</div>
														<div class="modal-footer">
															<a href="javascript:;" class="btn btn-success button-submit">
															Buscar 
															</a>
														</div>
													</div>
												</form>
												<!-- END FORM-->
											</div>
										</div>
										<!-- BEGIN TABLE PORTLET-->
												<div class="portlet">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-group"></i>Familias encontradas
														</div>
														<div class="tools">
															<a href="javascript:;" class="collapse"></a>
														</div>
													</div>
													<div class="portlet-body">
														<div class="table-responsive">
															<table class="table table-striped table-bordered table-advance table-hover">
															<thead>
															<tr>
																<th>
																	Barrio/Comunidad
																</th>
																<th>
																	Núm. Vivienda
																</th>
																<th>
																	Núm. Familia
																</th>
																<th>
																</th>
															</tr>
															</thead>
															<tbody>
															<tr>
																<td>
																	Julio Buitrago
																</td>
																<td>
																	 23
																</td>
																<td>
																	 1
																</td>
																<td>
																	<a href="#" class="btn btn-default btn-xs purple"><i class="fa fa-share"></i> Seleccionar</a>
																</td>
															</tr>
															<tr>
																<td>
																	San José
																</td>
																<td>
																	 32
																</td>
																<td>
																	 22
																</td>
																<td>
																	<a href="#" class="btn btn-default btn-xs black"><i class="fa fa-share"></i> Seleccionar</a>
																</td>
															</tr>
															</tbody>
															</table>
														</div>
													</div>
												</div>
												<!-- END TABLE PORTLET-->
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-danger">Cancelar</button>
								</div>
							</div>
						</div>
					</div>
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
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js" var="jQBWizard" />
<script type="text/javascript" src="${jQBWizard}"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="selectDos" />
<script type="text/javascript" src="${selectDos}"></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="datepicker" />
<script type="text/javascript" src="${datepicker}"></script>
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/hsf-wizard.js" var="hsfWizard" />
<script src="${hsfWizard}"></script>

<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfnuevo").removeClass("hsfnuevo").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		FormWizardHSF.init();
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>