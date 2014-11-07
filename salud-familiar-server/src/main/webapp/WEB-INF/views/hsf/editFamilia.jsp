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
			<spring:url value="/info/viewHsf/{idFamilia}" var="familiaUrl">
				<spring:param name="idFamilia" value="${familia.idFamilia}" />
			</spring:url>
			<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
			<c:set var="processError"><spring:message code="process.error" /></c:set>
			<c:set var="deniedError"><spring:message code="denied" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-pencil"></i><spring:message code="edit" /> - <spring:message code="heading" /> - <spring:message code="step1" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="edit-hsf-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group" hidden="true">
												<label class="control-label col-md-6"><spring:message code="nofamilia" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-6">
													<input type="text" value="${familia.idFamilia}" id="idFamilia" name="idFamilia" class="form-control"/>
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
														<c:forEach items="${entidades}" var="entidad">
															<c:choose> 
																<c:when test="${entidad.codigo eq silais.codigo}">
																	<option selected value="${entidad.codigo}">${entidad.nombre}</option>
																</c:when>
																<c:otherwise>
																	<option value="${entidad.codigo}">${entidad.nombre}</option>
																</c:otherwise>
															</c:choose> 
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
														<option selected value="${municipio.codigoNacional}">${municipio.nombre}</option>
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
														<option selected value="${sector.codigo}">${sector.nombre}</option>
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
												<div class="col-md-5">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="comunity" />" name="comunidad" id="comunidad" class="form-control">
														<option selected value="${familia.comunidad.codigo}">${familia.comunidad.nombre}</option>
													</select>
												</div>
												<div class="col-md-1">
													<button type="button" class="btn btn-default btn-xs" onclick="mostrarSelects()"><i class="fa fa-refresh"></i></button>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
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
														<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="novivienda" />" value="${familia.numVivienda}" class="form-control" id="numVivienda" name="numVivienda" />
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
														<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="nofamilia" />" value="${familia.numFamilia}" class="form-control" id="numFamilia" name="numFamilia"/>
														<span class="input-group-addon">
															<i class="fa fa-sort-numeric-asc"></i>
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
												<label class="control-label col-md-6"><spring:message code="address" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-6">
													<div class="input-group">
														<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="address" />" value="${familia.direccion}" class="form-control" id="direccion" name="direccion" />
														<span class="input-group-addon">
															<i class="fa fa-sort-alpha-asc"></i>
														</span>
													</div>
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
													<div class="input-group">
														<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="noficha" />" value="${familia.numFicha}" class="form-control" id="numFicha" name="numFicha"/>
														<span class="input-group-addon">
															<i class="fa fa-sort-numeric-asc"></i>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-9 col-md-3">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
						            	<a href="${fn:escapeXml(familiaUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
									</div>
								</div>
							</form>
						</div>
					</div>
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
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="selectDos" />
<script type="text/javascript" src="${selectDos}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="datepicker" />
<script type="text/javascript" src="${datepicker}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/hsf-edit.js" var="hsfEdit" />
<script src="${hsfEdit}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/info/editFamilia" var="editFamiliaUrl"/>
<spring:url value="/opciones/municipios" var="opcMuniUrl"/>
<spring:url value="/opciones/sectores" var="opcSectUrl"/>
<spring:url value="/opciones/comunidades" var="opcComuUrl"/>
<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfsearch").removeClass("hsfsearch").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = {editFamiliaUrl: "${editFamiliaUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, deniedError: "${deniedError}"
			, opcMuniUrl: "${opcMuniUrl}"
			, opcSectUrl: "${opcSectUrl}"
			, opcComuUrl: "${opcComuUrl}"
			,language:"${pageContext.request.locale.language}" };
		FormEditHSF.init(parametros1);
	});
	
	function mostrarSelects() {
		$("#silais").select2('data',null);
		$("#municipio").select2('data',null);
		$("#sector").select2('data',null);
		$("#comunidad").select2('data',null);
		$("#municipio").empty();
		$("#sector").empty();
		$("#comunidad").empty();
	}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>