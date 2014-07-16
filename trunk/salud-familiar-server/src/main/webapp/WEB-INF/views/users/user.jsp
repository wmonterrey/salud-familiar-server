<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
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
					<fmt:message key="users.user" />	<c:out value="${SUCCESS}" />
					</h3>
					<c:set var="rolesString">
								<c:forEach var="rol" items="${user.authorities}">
									<c:out value="${rol.authId.authority}" />
								</c:forEach>
							</c:set>
					<ul class="page-breadcrumb breadcrumb">
						
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN FORM PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i><fmt:message key="users.list" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group pull-right">
									<button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><fmt:message key="actions" /> <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right">
										<li>
											<a href="#"><fmt:message key="edit" /></a>
										</li>
										<li>
											<a href="#"><fmt:message key="chgpass" /></a>
										</li>
										<c:choose>
											<c:when test="${user.enabled}">
												<li>
													<a href="#"><fmt:message key="disable" /></a>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="#">Habilitar</a>
												</li>
											</c:otherwise>
										</c:choose>
										<li>
											<a href="#"><fmt:message key="users.list" /></a>
										</li>
									</ul>
								</div>
							</div>
							<!-- BEGIN FORM-->
							<form class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section"><c:out value="${user.username}" /></h3>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><fmt:message key="users.desc" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.completeName}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><fmt:message key="users.email" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.email}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><fmt:message key="users.enabled" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.enabled}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><fmt:message key="users.nivel" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.nivel}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><fmt:message key="users.entity" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.nivel.codigo=='HSF_NIVELES|SILAIS'}">
																<td><c:out value="${user.entidad.nombre}" /></td>
															</c:when>
															<c:when test="${user.nivel.codigo=='HSF_NIVELES|UNIDAD'}">
																<td><c:out value="${user.unidad.nombre}" /></td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><fmt:message key="users.roles" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${rolesString}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">Creado por:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.usuario}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">Fecha:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.created}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END FORM PORTLET-->
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
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>