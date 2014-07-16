<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
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
					<fmt:message key="users.add" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i>Agregar usuario
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form:form modelAttribute="user" method="${method}" autocomplete="off"
								class="form-horizontal form-bordered form-row-stripped" id="add-user-form">
								<form:errors  class="alert-danger" path="*"/>
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										El formulario tiene errores. Favor verificar!
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.username" /></label>
										<div class="col-md-9">
											<form:input id="username" path="username" type="text" placeholder="" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.desc" /></label>
										<div class="col-md-9">
											<form:input id="completeName" path="completeName" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.email" /></label>
										<div class="col-md-9">
											<form:input id="email" path="email" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.pass1" /></label>
										<div class="col-md-9">
											<input id="password" name="password" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.pass2" /></label>
										<div class="col-md-9">
											<input id="confirm_password" name="confirm_password" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.enabled" /></label>
										<div class="col-md-9">
											<form:checkbox id="enabled" path="enabled" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Category</label>
										<div class="col-md-9">
											<form:select path = "nivel.codigo" class="form-control select2_category">
												<c:forEach items="${niveles}" var="nivel"> 
													<option value="${nivel.codigo}">${nivel.valor}</option> 
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><fmt:message key="users.entity" /></label>
										<div class="col-md-9">
											<form:input id="entidad" path="entidad" type="text" class="form-control"/>
										</div>
									</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-success"><fmt:message key="users.save" /></button>
									<spring:url value="/usuarios/admin/list" var="usuarioUrl">
					            	</spring:url>
					            	<a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-danger"><fmt:message key="users.cancel" /></a>
								</div>
							</form:form>
							<!-- END FORM-->
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
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
        $("li.users").removeClass("users").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
        // use select2 dropdown instead of chosen as select2 works fine with bootstrap on responsive layouts.
        $('.select2_category').select2({
            allowClear: true
        });
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>