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
				<spring:param name="idFamilia" value="${identFamilia}" />
			</spring:url>
			<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
			<c:set var="processError"><spring:message code="process.error" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i><spring:message code="edit" /> - <spring:message code="heading" /> - <spring:message code="step3" />
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
												<label class="control-label col-md-6"><spring:message code="step3" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-6">
													<input type="text" value="${chs.idCaractHig}" id="idCaractHig" name="idCaractHig" class="form-control"/>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group" hidden="true">
												<label class="control-label col-md-6"><spring:message code="nofamilia" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-6">
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
												<label class="control-label col-md-4"><spring:message code="hacinamiento" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="hacinamiento" />" name="hacinamiento" id="hacinamiento" class="form-control">
														<option value=""></option>
														<c:forEach items="${sinons}" var="sn">
															<c:choose> 
																<c:when test="${sn.codigo eq chs.hacinamiento}">
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
												<label class="control-label col-md-4"><spring:message code="animdom" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select multiple="multiple" class="multi-select" id=animalesDom name="animalesDom">
														<c:forEach items="${animales}" var="animal">
															<c:choose> 
																<c:when test="${fn:contains(chs.animalesDom, animal.codigo)}">
																	<option selected value="${animal.codigo}">${animal.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${animal.codigo}">${animal.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="risks.nat" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select multiple="multiple" class="multi-select" id=riesgoNatural name="riesgoNatural">
														<c:forEach items="${rgnats}" var="rgnat">
															<c:choose> 
																<c:when test="${fn:contains(chs.riesgoNatural, rgnat.codigo)}">
																	<option selected value="${rgnat.codigo}">${rgnat.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${rgnat.codigo}">${rgnat.valor}</option>
																</c:otherwise>
															</c:choose>
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
															<c:choose> 
																<c:when test="${fn:contains(chs.riesgoMeteorologico, rgmet.codigo)}">
																	<option selected value="${rgmet.codigo}">${rgmet.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${rgmet.codigo}">${rgmet.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="risks.bio" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select multiple="multiple" class="multi-select" id=riesgoBiologico name="riesgoBiologico">
														<c:forEach items="${rgbios}" var="rgbio">
															<c:choose> 
																<c:when test="${fn:contains(chs.riesgoBiologico, rgbio.codigo)}">
																	<option selected value="${rgbio.codigo}">${rgbio.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${rgbio.codigo}">${rgbio.valor}</option>
																</c:otherwise>
															</c:choose>
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
															<c:choose> 
																<c:when test="${fn:contains(chs.riesgoSocial, rgsoc.codigo)}">
																	<option selected value="${rgsoc.codigo}">${rgsoc.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${rgsoc.codigo}">${rgsoc.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="fact.med.amb" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select multiple="multiple" class="multi-select" id=factoresMedAmb name="factoresMedAmb">
														<c:forEach items="${factoresMedAmbs}" var="factorMedAmb">
															<c:choose> 
																<c:when test="${fn:contains(chs.factoresMedAmb, factorMedAmb.codigo)}">
																	<option selected value="${factorMedAmb.codigo}">${factorMedAmb.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${factorMedAmb.codigo}">${factorMedAmb.valor}</option>
																</c:otherwise>
															</c:choose>
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
															<c:choose> 
																<c:when test="${fn:contains(chs.combCocinar, combCocinar.codigo)}">
																	<option selected value="${combCocinar.codigo}">${combCocinar.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${combCocinar.codigo}">${combCocinar.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="abast.agua" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="abast.agua" />" name="aAgua" id="aAgua" class="form-control">
														<option value=""></option>
														<c:forEach items="${abastecimientoAguas}" var="abastecimientoAgua">
															<c:choose> 
																<c:when test="${abastecimientoAgua.codigo eq chs.aAgua.codigo}">
																	<option selected value="${abastecimientoAgua.codigo}">${abastecimientoAgua.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${abastecimientoAgua.codigo}">${abastecimientoAgua.valor}</option>
																</c:otherwise>
															</c:choose> 
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
															<c:choose> 
																<c:when test="${calidadAgua.codigo eq chs.cAgua.codigo}">
																	<option selected value="${calidadAgua.codigo}">${calidadAgua.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${calidadAgua.codigo}">${calidadAgua.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="elect" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="elect" />" name="electricidad" id="electricidad" class="form-control">
														<option value=""></option>
														<c:forEach items="${electricidads}" var="electricidad">
															<c:choose> 
																<c:when test="${electricidad.codigo eq chs.electricidad.codigo}">
																	<option selected value="${electricidad.codigo}">${electricidad.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${electricidad.codigo}">${electricidad.valor}</option>
																</c:otherwise>
															</c:choose> 
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
															<c:choose> 
																<c:when test="${depExcreta.codigo eq chs.depExcretas.codigo}">
																	<option selected value="${depExcreta.codigo}">${depExcreta.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${depExcreta.codigo}">${depExcreta.valor}</option>
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
												<label class="control-label col-md-4"><spring:message code="dep.basura" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-8">
													<select data-placeholder="<spring:message code="select" /> <spring:message code="dep.basura" />" name="depBasura" id="depBasura" class="form-control">
														<option value=""></option>
														<c:forEach items="${depBasuras}" var="depBasura">
															<c:choose> 
																<c:when test="${depBasura.codigo eq chs.depBasura.codigo}">
																	<option selected value="${depBasura.codigo}">${depBasura.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${depBasura.codigo}">${depBasura.valor}</option>
																</c:otherwise>
															</c:choose> 
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
															<c:choose> 
																<c:when test="${depResLiq.codigo eq chs.depResLiq.codigo}">
																	<option selected value="${depResLiq.codigo}">${depResLiq.valor}</option>
																</c:when>
																<c:otherwise>
																	<option value="${depResLiq.codigo}">${depResLiq.valor}</option>
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
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-2"><spring:message code="obs" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-10">
													<div class="input-group">
														<input type="text" value="${chs.obsCaractHig}" placeholder="<spring:message code="please.enter" /> <spring:message code="obs" />" class="form-control" id="obsCaractHig" name="obsCaractHig" />
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
<spring:url value="/resources/scripts/hsf-edit2.js" var="hsfEdit" />
<script src="${hsfEdit}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/info/newCarHigSan" var="addCarHigSanUrl"/>
<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsfsearch").removeClass("hsfsearch").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = {addCarHigSanUrl: "${addCarHigSanUrl}"
			, processSuccess: "${processSuccess}"
			, processError: "${processError}"
			,language:"${pageContext.request.locale.language}" };
		FormEdit2HSF.init(parametros1);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>