<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

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
<spring:url value="/info/updateHsf/{idFamilia}" var="familiaUrl">
	<spring:param name="idFamilia" value="${familia.idFamilia}" />
</spring:url>
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
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/info/newVisit" htmlEscape="true "/>"><spring:message code="hsf.visit" /></a>
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
										<div class="col-md-6">
											<div class="form-group" hidden="true">
												<label class="control-label col-md-6"><spring:message code="nofamilia" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-9">
													<input type="text" id="idFamilia" name="idFamilia" value="${familia.idFamilia}" class="form-control"/>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group" hidden="true">
												<label class="control-label col-md-6"><spring:message code="comunity" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-9">
													<input type="text" id="comunidad" name="comunidad" value="${familia.comunidad.codigo}" class="form-control"/>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
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
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="created.date" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><fmt:formatDate value="${familia.created}" pattern="dd/MM/yyyy" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="created.by" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.createdBy}" /></p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="voided" /></label>
												<div class="col-md-6">
													<p class="form-control-static"><c:out value="${familia.pasive}" /></p>
												</div>
											</div>
										</div>
									</div>
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="visit.date" />:
												<span class="required">
													 *
												</span>
												</label>
												<div class="col-md-6">
													<div class="input-group date date-picker" data-date-format="dd/MM/yyyy" data-date-end-date="+0d">
														<input id="fechaVisita" name="fechaVisita" type="text" class="form-control" placeholder="<spring:message code="please.enter" /> <spring:message code="visit.date" />">
														<span class="input-group-btn">
															<button class="btn btn-info" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="reason" />:
												</label>
												<div class="col-md-6">
													<div class="input-group">
														<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="reason" />" class="form-control" id="motivoVisita" name="motivoVisita"/>
														<span class="input-group-addon">
															<i class="fa fa-sort-alpha-asc"></i>
														</span>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="problems" />:
												</label>
												<div class="col-md-6">
													<div class="input-group">
														<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="problems" />" class="form-control" id="problemasEncontrados" name="problemasEncontrados"/>
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
										<div class="col-md-4">
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
										<div class="col-md-4">
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
									<!-- END ROW -->
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-12 modal-footer">
											<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
						            		<a href="<spring:url value="/info/newVisit" htmlEscape="true "/>" class="btn btn-danger"><spring:message code="cancel" /></a>
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
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="datepicker" />
<script type="text/javascript" src="${datepicker}"></script>
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/newvisit.js" var="newVisit" />
<script src="${newVisit}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
<c:set var="processError"><spring:message code="process.error" /></c:set>
<c:set var="deniedError"><spring:message code="denied" /></c:set>
<spring:url value="/info/newVisita" var="addVisitaUrl"/>
<script>
    $(function () {
    	$("li.hsf").removeClass("hsf").addClass("active");
        $("li.hsffollow").removeClass("hsffollow").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = { processSuccess: "${processSuccess}", addVisitaUrl: "${addVisitaUrl}"
			, processError: "${processError}", deniedError: "${deniedError}", familiaUrl: "${familiaUrl}"
			,language:"${pageContext.request.locale.language}" };
		NewVisit.init(parametros1);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>