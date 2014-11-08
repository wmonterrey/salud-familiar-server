<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<jsp:include page="../../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
<spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss" />
<link rel="stylesheet" type="text/css" href="${jqmscss}"/>
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
					<spring:message code="users" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="dashboard" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/" htmlEscape="true "/>"><spring:message code="users.list" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/edit/${user.username}" htmlEscape="true "/>"><spring:message code="users.edit" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/admin/users/editUser" var="editUserUrl"></spring:url>
			<spring:url value="/admin/users/{username}"
				var="usuarioUrl">
				<spring:param name="username" value="${user.username}" />
			</spring:url>
			<c:set var="userUpdated"><spring:message code="user.updated" /></c:set>
			<c:set var="errorProcess"><spring:message code="process.error" /></c:set>
			<c:set var="selectMessage"><spring:message code="select" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i><spring:message code="users.edit" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="edit-user-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="users.username" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input id="username" name="username" type="text" readonly value="${user.username}" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="users.desc" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input id="completeName" name="completeName" type="text" value="${user.completeName}" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="users.email" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input id="email" name="email" type="text" value="${user.email}" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-envelope"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="users.nivel" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select name="nivel" id="nivel" class="form-control">
												<option value=""></option>
												<c:forEach items="${niveles}" var="nivel"> 
													<c:choose> 
														<c:when test="${nivel.valor eq user.nivel}">
															<option selected value="${nivel.codigo}">${nivel.valor}</option>
														</c:when>
														<c:otherwise>
															<option value="${nivel.codigo}">${nivel.valor}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div id="entidad-div" class="form-group" hidden=true>
										<label class="control-label col-md-3"><spring:message code="silais" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select name="entidad" id="entidad" class="form-control">
												<option value=""></option>
												<c:forEach items="${entidades}" var="entidad">
													<c:choose> 
														<c:when test="${entidad.nombre eq user.entidad.nombre}">
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
									<div id="municipio-div" class="form-group" hidden=true>
										<label class="control-label col-md-3"><spring:message code="muni" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select name="municipio" id="municipio" class="form-control">
												<option value=""></option>
											</select>
										</div>
									</div>
									<div id="unidad-div" class="form-group" hidden=true>
										<label class="control-label col-md-3"><spring:message code="unit" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="row">
											<div class="col-md-11">
											<select name="unidad" id="unidad" class="form-control">
												<option value=""></option>
												<option selected value="${user.unidad.codigo}">${user.unidad.nombre}</option>
											</select>
											</div>
											<div class="col-md-1">
											<button type="button" class="btn btn-default btn-xs" onclick="mostrarSelects()"><i class="fa fa-refresh"></i></button>
											</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="users.roles" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select multiple="multiple" class="multi-select" id="authorities" name="authorities">
											<c:forEach items="${user.authorities}" var="auth" varStatus="stat">
												<c:set var="rolesUsuario" value="${stat.first ? '' : rolesUsuario} ${auth.authId.authority}" />
											</c:forEach>
											<c:forEach items="${roles}" var="rol">
												<c:choose> 
													<c:when test="${fn:contains(rolesUsuario, rol.authority)}">
														<option selected value="${rol.authority}"><spring:message code="${rol.authority}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${rol.authority}"><spring:message code="${rol.authority}" /></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-6 col-md-6">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
						            	<a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
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
<jsp:include page="../../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../../fragments/corePlugins.jsp" />
<jsp:include page="../../fragments/bodyUtils.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js" var="jQueryMultiSelect" />
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/edit-user.js" var="editUserScript" />
<script src="${editUserScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.users").removeClass("users").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {selectMessage: "${selectMessage}"
		};
		EditUser.init(parametros);
		$('#completeName').focus();
	});
	function editUser()
	{
    $.post( '${fn:escapeXml(editUserUrl)}'
            , $('#edit-user-form').serialize()
            , function( data )
            {
    			if (data==$('#username').val()){
    				toastr.success("${userUpdated}", data);
    				//window.setTimeout(function(){
    			    //    window.location.href = "${userUrl}";
    			    //}, 2000);
    			}
    			else{
    				toastr.error(data,"${errorProcess}");
    			}
            }
            , 'text' )
	  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
	    		alert( "error:" + errorThrown);
	  		});
	}
	
	function mostrarSelects() {
		$('#entidad').select2('val', '');
		$('#municipio').select2('val', '');
		$('#unidad').select2('val', '');
		$('#municipio').html('<option value=""></option>');
		$('#unidad').html('<option value=""></option>');
		$('#entidad-div').show();
		$('#municipio-div').show();
		$('#unidad-div').show();	
	}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>