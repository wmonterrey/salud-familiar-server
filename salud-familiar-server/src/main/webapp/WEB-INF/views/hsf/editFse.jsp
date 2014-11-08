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
<spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss" />
<link rel="stylesheet" type="text/css" href="${jqmscss}"/>
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
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/info/searchHsf" htmlEscape="true "/>"><spring:message code="hsf.search" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/info/viewHsf/{idFamilia}" var="familiaUrl">
				<spring:param name="idFamilia" value="${identFamilia}" />
			</spring:url>
			<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
			<c:set var="processError"><spring:message code="process.error" /></c:set>
			<c:set var="deniedError"><spring:message code="denied" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-pencil"></i><spring:message code="edit" /> - <spring:message code="heading" /> - <spring:message code="step4" />
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
												<label class="control-label col-md-4"><spring:message code="step4" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<input type="text" value="${fse.idFactSocioEc}" id="idFactSocioEc" name="idFactSocioEc" class="form-control"/>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group" hidden="true">
												<label class="control-label col-md-4"><spring:message code="noficha" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<input type="text" value="${identFamilia}" id="idFamilia" name="idFamilia" class="form-control"/>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group" hidden="true">
												<label class="control-label col-md-6"><spring:message code="visit.date" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-6">
													<fmt:formatDate  value="${fechaDeVisita}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
													<input type="text" value="<c:out value="${parsedEmpDate}" />" id="fechaVisita" name="fechaVisita" class="form-control"/>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"><spring:message code="piso" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="piso" />" name="tipoPiso" id="tipoPiso" class="form-control">
														<option value=""></option>
														<c:forEach items="${pisos}" var="piso">
															<c:choose> 
																<c:when test="${piso.codigo eq fse.tipoPiso.codigo}">
																	<option selected value="${piso.codigo}">${piso.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${piso.codigo}">${piso.valor}</option>
																</c:otherwise>
															</c:choose> 
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"><spring:message code="techo" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="techo" />" name="tipoTecho" id="tipoTecho" class="form-control">
														<option value=""></option>
														<c:forEach items="${techos}" var="techo">
															<c:choose> 
																<c:when test="${techo.codigo eq fse.tipoTecho.codigo}">
																	<option selected value="${techo.codigo}">${techo.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${techo.codigo}">${techo.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="paredes" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="paredes" />" name="tipoPared" id="tipoPared" class="form-control">
														<option value=""></option>
														<c:forEach items="${paredes}" var="pared">
															<c:choose> 
																<c:when test="${pared.codigo eq fse.tipoPared.codigo}">
																	<option selected value="${pared.codigo}">${pared.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${pared.codigo}">${pared.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="cult.san" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="cult.san" />" name="culturaSanitaria" id="culturaSanitaria" class="form-control">
														<option value=""></option>
														<c:forEach items="${culturasSanitaria}" var="culturaSanitaria">
															<c:choose> 
																<c:when test="${culturaSanitaria.codigo eq fse.culturaSanitaria.codigo}">
																	<option selected value="${culturaSanitaria.codigo}">${culturaSanitaria.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${culturaSanitaria.codigo}">${culturaSanitaria.valor}</option>
																</c:otherwise>
															</c:choose> 
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"><spring:message code="car.psic" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="car.psic" />" name="carPsicosociales" id="carPsicosociales" class="form-control">
														<option value=""></option>
														<c:forEach items="${carsPsicosociales}" var="carPsicosocial">
															<c:choose> 
																<c:when test="${carPsicosocial.codigo eq fse.carPsicosociales.codigo}">
																	<option selected value="${carPsicosocial.codigo}">${carPsicosocial.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${carPsicosocial.codigo}">${carPsicosocial.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="nec.basicas" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="nec.basicas" />" name="satNecBasicas" id="satNecBasicas" class="form-control">
														<option value=""></option>
														<c:forEach items="${sinons}" var="sn">
															<c:choose> 
																<c:when test="${sn.codigo eq fse.satNecBasicas}">
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
												<label class="control-label col-md-4"><spring:message code="ten.viv" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="ten.viv" />" name="tenenciaVivienda" id="tenenciaVivienda" class="form-control">
														<option value=""></option>
														<c:forEach items="${tenenciasVivienda}" var="tenenciaVivienda">
															<c:choose> 
																<c:when test="${tenenciaVivienda.codigo eq fse.tenenciaVivienda.codigo}">
																	<option selected value="${tenenciaVivienda.codigo}">${tenenciaVivienda.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${tenenciaVivienda.codigo}">${tenenciaVivienda.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="acc.com" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select multiple="multiple" class="multi-select" id=accionesComunitarias name="accionesComunitarias">
														<c:forEach items="${accionesComunitarias}" var="accionComunitaria">
															<c:choose> 
																<c:when test="${fn:contains(fse.accionesComunitarias, accionComunitaria.codigo)}">
																	<option selected value="${accionComunitaria.codigo}">${accionComunitaria.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${accionComunitaria.codigo}">${accionComunitaria.valor}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"><spring:message code="obs" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<div class="input-group">
														<textarea placeholder="<spring:message code="please.enter" /> <spring:message code="obs" />" class="form-control"  rows="3" id="obsFactSocioEc" name="obsFactSocioEc">${fse.obsFactSocioEc}</textarea>
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
<spring:url value="/resources/plugins/select2/select2.min.js" var="selectDos" />
<script type="text/javascript" src="${selectDos}"></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js" var="jQueryMultiSelect" />
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/hsf-edit3.js" var="hsfEdit" />
<script src="${hsfEdit}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/info/newFactSocEc" var="addFactSocEcUrl"/>
<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfsearch").removeClass("hsfsearch").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = {addFactSocEcUrl: "${addFactSocEcUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			, deniedError: "${deniedError}"
			,language:"${pageContext.request.locale.language}" };
		FormEdit3HSF.init(parametros1);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>