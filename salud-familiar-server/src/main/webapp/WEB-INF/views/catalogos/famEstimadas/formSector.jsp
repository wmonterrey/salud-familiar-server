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
<jsp:include page="../../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="select2css" />
<link rel="stylesheet" type="text/css" href="${select2css}"/>
<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../../fragments/bodyNavigation.jsp" />
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<jsp:include page="../../fragments/bodyCustomizer.jsp" />
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
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet" id="form_sector">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> <spring:message code="families.estimated" />
							</div>
							<div class="tools hidden-xs">
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" class="form-horizontal" id="sector_form">
								<div class="form-body">
									<h4 class="form-section"><spring:message code="add" /></h4> 
									<!-- START ROW -->
									<div class="row">
										<div class="col-md-6" hidden = "hidden">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="families.estimated" />:
												</label>
												<div class="col-md-6">
													<div class="input-group">
														<input type="text" value="${famEstSector.famEstSectorId}" placeholder="<spring:message code="please.enter" /> <spring:message code="families.estimated" />" class="form-control" id="famEstSectorId" name="famEstSectorId"/>
														<span class="input-group-addon">
															<i class="fa fa-sort-alpha-asc"></i>
														</span>
													</div>
												</div>
											</div>
										</div>
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
														<c:forEach items="${sectores}" var="sector">
															<c:choose> 
																<c:when test="${sector.codigo eq famEstSector.sector.codigo}">
																	<option selected value="${sector.codigo}">${sector.nombre}</option>
																</c:when>
																<c:otherwise>
																	<option value="${sector.codigo}">${sector.nombre}</option>
																</c:otherwise>
															</c:choose> 
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-6"><spring:message code="families.estimated" />:
												</label>
												<div class="col-md-6">
													<div class="input-group">
														<input type="text" value="${famEstSector.famEstimadas}" placeholder="<spring:message code="please.enter" /> <spring:message code="families.estimated" />" class="form-control" id="famEstimadas" name="famEstimadas"/>
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
										<div class="col-md-12 modal-footer">
											<spring:url value="/catalog/famest/sector" var="famEstSectorUrl"/>
											<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
						            		<a href="${fn:escapeXml(famEstSectorUrl)}" class="btn btn-danger"><spring:message code="cancel" /></a>
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
<jsp:include page="../../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../../fragments/corePlugins.jsp" />
<jsp:include page="../../fragments/bodyUtils.jsp" />
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
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/newestsec.js" var="newSector" />
<script src="${newSector}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
<c:set var="processError"><spring:message code="process.error" /></c:set>
<c:set var="deniedError"><spring:message code="denied" /></c:set>
<spring:url value="/catalog/famest/saveSector" var="saveSectorUrl"/>
<script>
    $(function () {
        $("li.catalogs").removeClass("catalogs").addClass("active");
        $("li.famest").removeClass("famest").addClass("active");
        $("li.sectores").removeClass("sectores").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros1 = { processSuccess: "${processSuccess}", saveSectorUrl: "${saveSectorUrl}"
			, processError: "${processError}", deniedError: "${deniedError}", famEstSectorUrl: "${famEstSectorUrl}"
			,language:"${pageContext.request.locale.language}" };
		NewSector.init(parametros1);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>