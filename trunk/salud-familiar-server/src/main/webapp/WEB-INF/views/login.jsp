<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<jsp:include page="fragments/headTag.jsp" />
<head>
<spring:url value="/resources/css/pages/login.css" var="login" />
<link href="${login}" rel="stylesheet" type="text/css" />
</head>
<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<spring:url value="/resources/img/logo-72.png" var="logo" />
		<img src="${logo}" alt="<fmt:message key="heading" />" />
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form"
			action="<c:url value='j_spring_security_check' />" method="post">
			<h3 class="form-title">
				<fmt:message>heading</fmt:message>
			</h3>
			<div class="alert alert-error display-hide">
				<button class="close" data-close="alert"></button>
				<span> <fmt:message>login</fmt:message>
				</span>
			</div>
			<c:if test="${not empty error}">
			<div class="alert alert-error">
				<fmt:message key="loginfailed" />
				<br>
				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
			</div>
			</c:if>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9"><fmt:message>users.username</fmt:message></label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off"
						placeholder="<fmt:message>users.username</fmt:message>"
						name="j_username" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9"><fmt:message>users.password</fmt:message></label>
				<div class="input-icon">
					<i class="fa fa-lock"></i> <input
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off"
						placeholder="<fmt:message>users.password</fmt:message>"
						name="j_password" />
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" type="submit" class="btn btn-info pull-right">
					<fmt:message>gologin</fmt:message></button>
			</div>
		</form>
		<!-- END LOGIN FORM -->
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">2014 &copy; <fmt:message>title</fmt:message>.</div>
	<!-- END COPYRIGHT -->
	<jsp:include page="fragments/corePlugins.jsp" />
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQueryValidation" />
	<script src="${jQueryValidation}" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<spring:url value="/resources/scripts/app.js" var="App" />
	<spring:url value="/resources/scripts/login.js" var="Login" />
	<script src="${App}" type="text/javascript"></script>
	<script src="${Login}" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>