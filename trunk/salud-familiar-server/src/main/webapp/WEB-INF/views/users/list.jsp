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
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.css" var="dtbootcss" />
<link rel="stylesheet" href="${dtbootcss}"/>
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
					<fmt:message key="users" />
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
					<!-- BEGIN TABLE PORTLET-->
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
								<div class="btn-group">
									<spring:url value="/users/admin/new"	var="newUser"/>
									<button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(newUser)}'" class="btn btn-success">
									<fmt:message key="users.add" /> <i class="fa fa-plus"></i>
									</button>
								</div>
								<div class="btn-group pull-right">
									<button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><fmt:message key="tools" /> <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right">
										<li>
											<a href="#"><fmt:message key="print" /></a>
										</li>
										<li>
											<a href="#"><fmt:message key="pdf" /></a>
										</li>
										<li>
											<a href="#"><fmt:message key="excel" /></a>
										</li>
									</ul>
								</div>
							</div>
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="lista_usuarios">
							<thead>
								<tr>
									<th><fmt:message key="users.user" /></th>
									<th class="hidden-xs"><fmt:message key="users.desc" /></th>
									<th><fmt:message key="users.nivel" /></th>
									<th class="hidden-xs"><fmt:message key="users.entity" /></th>
									<th><fmt:message key="users.enabled" /></th>
									<th class="hidden-xs"><fmt:message key="users.roles" /></th>
									<th><fmt:message key="actions" /></th>
								</tr>
							</thead>
							<c:forEach items="${usuarios}" var="usuario">
								<tr>
									<spring:url value="/users/admin/{username}"
										var="usuarioUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/users/admin/{username}/edit"
										var="editUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/users/admin/{username}/disable"
										var="disableUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<spring:url value="/users/admin/{username}/chgpass"
										var="chgpassUrl">
										<spring:param name="username" value="${usuario.username}" />
									</spring:url>
									<td><a href="${fn:escapeXml(usuarioUrl)}"><c:out
												value="${usuario.username}" /></a></td>
									<td class="hidden-xs"><c:out value="${usuario.completeName}" /></td>
									<td><c:out value="${usuario.nivel}" /></td>
									<c:choose>
										<c:when test="${usuario.nivel.codigo=='HSF_NIVELES|SILAIS'}">
											<td class="hidden-xs"><c:out value="${usuario.entidad.nombre}" /></td>
										</c:when>
										<c:when test="${usuario.nivel.codigo=='HSF_NIVELES|UNIDAD'}">
											<td class="hidden-xs"><c:out value="${usuario.unidad.nombre}" /></td>
										</c:when>
										<c:otherwise>
											<td class="hidden-xs"></td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${usuario.enabled}">
											<td><span class="label label-success"><c:out
														value="${usuario.enabled}" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-danger"><c:out
														value="${usuario.enabled}" /></span></td>
										</c:otherwise>
									</c:choose>
									<td class="hidden-xs"><c:forEach var="rol" items="${usuario.authorities}">
											<c:out value="${rol.authId.authority}" />
										</c:forEach></td>
									<td>
										<a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-default btn-xs"><i class="fa fa-search"></i> <fmt:message key="view" /></a>
										<a href="${fn:escapeXml(editUrl)}" class="btn btn-default btn-xs"><i class="fa fa-edit"></i> <fmt:message key="edit" /></a>
										<a href="${fn:escapeXml(chgpassUrl)}" class="btn btn-default btn-xs"><i class="fa fa-key"></i> <fmt:message key="chgpass" /></a>
										<c:choose>
										<c:when test="${usuario.enabled}">
											<a href="${fn:escapeXml(disableUrl)}" class="btn btn-default btn-xs"><i class="fa fa-trash-o"></i> <fmt:message key="disable" /></a>
										</c:when>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
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
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/users-list.js" var="userListScript" />
<script src="${userListScript}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
        $("li.users").removeClass("users").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		ListaUsuarios.init();
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>