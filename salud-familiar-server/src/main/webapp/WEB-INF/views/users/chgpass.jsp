<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
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
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/users/chgpass" htmlEscape="true "/>"><spring:message code="users.changepass" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/users/chgPass" var="chgPassUrl"/>
			<spring:url value="/users/profile" var="usuarioUrl"/>
			<c:set var="passUpdated"><spring:message code="pass.updated" /></c:set>
			<c:set var="errorProcess"><spring:message code="process.error" /></c:set>
			<c:set var="userName"><sec:authentication property="principal.username" /></c:set>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i><spring:message code="users.changepass" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="chg-pass-form" class="form-horizontal">
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
											<input id="username" name="username" type="text" readonly value="${userName}" class="form-control"/>
										</div>
									</div>
									
									<div class="form-group password-strength">
										<label class="control-label col-md-3"><spring:message code="users.pass1" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<input id="password" name="password" type="password" class="form-control"/>
											<span class="help-block">
												 <spring:message code="Pattern.password.format" />
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="users.pass2" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<input id="confirm_password" name="confirm_password" type="password" class="form-control"/>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-6 col-md-6">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
						            	<a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-danger"><spring:message code="cancel" /></a>
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
<!-- jQuery Password Meter-->
<spring:url value="/resources/plugins/jquery.pwstrength.bootstrap/src/pwstrength.js" var="pwStrength" />
<script src="${pwStrength}" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/chgpass-user.js" var="chgPassScript" />
<script src="${chgPassScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${pageContext.request.locale.language}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.users").removeClass("users").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		ChgPass.init();
		$('#password').focus();
	});
	function chgPassUser()
	{
	    $.post( '${fn:escapeXml(chgPassUrl)}'
	            , $('#chg-pass-form').serialize()
	            , function( data )
	            {
	    			if (data==$('#username').val()){
	    				toastr.success("${passUpdated}", data);
	    				$('#username').val('');
	            		$('#password').val('');
	            		$('#confirm_password').val('');
	    				window.setTimeout(function(){
	    			        window.location.href = "${usuarioUrl}";
	    			    }, 1500);
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
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>