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
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->

					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<c:set var="exportar"><spring:message code="export" /></c:set>
			<c:set var="valPersonas"><spring:message code="person.valtotal" /></c:set>
			<c:set var="dateFormat"><spring:message code="date.format" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> <spring:message code="hsf.nuevo" /> -
								<span class="step-title">
									 <spring:message code="step" /> 1 <spring:message code="of" /> 2
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
										</ul>
										<div id="bar" class="progress progress-striped" data-role="progressbar">
											<div class="progress-bar progress-bar-success">
											</div>
										</div>
										<div class="tab-content">
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
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="nofamilia" />:
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
														<div class="form-group">
															<label class="control-label col-md-3"><spring:message code="noficha" />:
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
																<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="novivienda" />" class="form-control" id="numVivienda" name="numVivienda" />
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
																<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="nofamilia" />" class="form-control" id="numFamilia" name="numFamilia"/>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="address" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="address" />" class="form-control" id="direccion" name="direccion" />
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="noficha" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="noficha" />" class="form-control" id="numFicha" name="numFicha"/>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="personnel" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="personnel" />" class="form-control" id="personaVisita" name="personaVisita"/>
															</div>
														</div>
													</div>
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
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6"><spring:message code="visit.date" />:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<input id="fechaVisita" placeholder="<spring:message code="select" /> <spring:message code="visit.date" />" name="fechaVisita" class="form-control form-control-inline date-picker" type="text" value=""/>
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
																		<a class="btn btn-success" data-toggle="modal" href="#personamodalform"><spring:message code="person.add" /> <i class="fa fa-plus"></i></a>																		
																	</div>
																</div>
																<div class="table-responsive">
																<table class="table table-striped table-hover table-bordered" id="lista_personas">
																<thead>
																	<tr>
																		<th>First name</th>
        																<th>Last name</th>
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
															<label class="control-label col-md-4"><spring:message code="person.total" />:
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
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-6">Hacinamiento:
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-6">
																<select data-placeholder="<spring:message code="select" />" name="hacinamiento" id="hacinamiento" class="form-control">
																	<option value=""></option>
																	<option value="0">No</option>
																	<option value="1">Si</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">Animales domésticos
															<span class="required">
																 *
															</span>
															</label>
															<div class="col-md-5">
																<select multiple="multiple" class="multi-select" id=animalesDom name="animalesDom">
																	<option value="0">Gatos</option>
																	<option value="1">Perros</option>
																	<option value="2">Gallinas</option>
																	<option value="3">Ganado vacuno</option>
																	<option value="4">Ganado porcino</option>
																	<option value="5">Otros</option>
																	<option value="6">Ninguno</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab4">
												
											</div>
											<div class="tab-pane" id="tab5">
												
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
													<spring:message code="submit" /> <i class="m-icon-swapright m-icon-white"></i>
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
										<div class="portlet">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-plus"></i><spring:message code="person.add" />
												</div>
												<div class="tools">
													
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN FORM-->
												<form action="#" class="form-horizontal" data-role="form" id="add_person_form">
													<div class="form-body">
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3"><spring:message code="nofamilia" />:
																	<span class="required">
																		 *
																	</span>
																	</label>
																	<div class="col-md-9">
																		<input type="text" id="idFamiliaPerson" name="idFamiliaPerson" class="form-control"/>
																	</div>
																</div>
															</div>
														</div>
														<!-- START ROW -->
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3"><spring:message code="nopersona" />:
																	<span class="required">
																		 *
																	</span>
																	</label>
																	<div class="col-md-9">
																		<input type="text" id="numPersona" name="numPersona" placeholder="<spring:message code="please.enter" /> <spring:message code="nopersona" />" class="form-control"/>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3"><spring:message code="completename" />:
																	<span class="required">
																		 *
																	</span>
																	</label>
																	<div class="col-md-9">
																		<input type="text" id="nombres" name="nombres" placeholder="<spring:message code="please.enter" /> <spring:message code="completename" />" class="form-control"/>
																	</div>
																</div>
															</div>
														</div>
														<!-- END ROW -->
														<!-- START ROW -->
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3"><spring:message code="lastname1" />:
																	<span class="required">
																		 *
																	</span>
																	</label>
																	<div class="col-md-9">
																		<input type="text" id="primerApellido" name="primerApellido" placeholder="<spring:message code="please.enter" /> <spring:message code="lastname1" />" class="form-control"/>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3"><spring:message code="lastname2" />:
																	<span class="required">
																		 *
																	</span>
																	</label>
																	<div class="col-md-9">
																		<input type="text" id="segundoApellido" name="segundoApellido" placeholder="<spring:message code="please.enter" /> <spring:message code="lastname2" />" class="form-control"/>
																	</div>
																</div>
															</div>
														</div>
														<!-- END ROW -->
													</div>
												</form>
												<!-- END FORM-->
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" id="dismiss-modalperson" data-dismiss="modal" class="btn btn-danger"><spring:message code="cancel" /></button>
									<button type="button" class="btn btn-info" onclick="validarModalPersona()"><spring:message code="ok" /></button>
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
<spring:url value="/resources/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js" var="jQBWizard" />
<script type="text/javascript" src="${jQBWizard}"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="selectDos" />
<script type="text/javascript" src="${selectDos}"></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js" var="jQueryMultiSelect" />
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
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
<spring:url value="/resources/scripts/hsf-wizard-modal-persona.js" var="hsfWizard" />
<script src="${hsfWizard}"></script>
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
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/info/newPersona" var="addPersonUrl"/>
<spring:url value="/info/newFamiliaVisita" var="addFamiliaVisitaUrl"/>
<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfnuevo").removeClass("hsfnuevo").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {exportar: "${exportar}"
				, valPersonas: "${valPersonas}"
				, addFamiliaVisitaUrl: "${addFamiliaVisitaUrl}"
				,language:"${pageContext.request.locale.language}" };
		FormWizardHSF.init(parametros);
	});
	function validarModalPersona()
	{
		var parametros = {addPersonUrl: "${addPersonUrl}"};
		FormWizardHSFModalPersonaValidation.init(parametros);
	}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>