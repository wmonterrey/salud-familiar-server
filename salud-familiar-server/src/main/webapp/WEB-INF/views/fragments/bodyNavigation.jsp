<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu">
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<div class="clearfix">
					</div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="start">
					<a href="<spring:url value="/" htmlEscape="true "/>">
					<i class="fa fa-home"></i>
					<span class="title">
						<fmt:message key="dashboard" />
					</span>
					<span class="selected">
					</span>
					</a>
				</li>
				<li class="hsf">
					<a href="javascript:;">
					<i class="fa fa-table"></i>
					<span class="title">
						<fmt:message key="hsf" />
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="hsfnuevo">
							<a href="<spring:url value="/info/newHsf" htmlEscape="true "/>">
							<i class="fa fa-file-o"></i>
							<fmt:message key="hsf.nuevo" /></a>
						</li>
						<li class="hsfsearch">
							<a href="<spring:url value="/info/searchHsf" htmlEscape="true "/>">
							<i class="fa fa-search"></i>
							<fmt:message key="hsf.search" /></a>
						</li>
					</ul>
				</li>
				<li class="analysis">
					<a href="javascript:;">
					<i class="fa fa-bar-chart-o"></i>
					<span class="title">
						<fmt:message key="analysis" />
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="visitday">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-calendar"></i>
							<fmt:message key="visit.day" /></a>
						</li>
						<li class="visitarea">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-map-marker"></i>
							<fmt:message key="visit.area" /></a>
						</li>
					</ul>
				</li>
				<li class="reports">
					<a href="javascript:;">
					<i class="fa fa-folder-open-o"></i>
					<span class="title">
						<fmt:message key="reports" />
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="reportday">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-calendar"></i>
							<fmt:message key="report.day" /></a>
						</li>
						<li class="reportarea">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-map-marker"></i>
							<fmt:message key="report.area" /></a>
						</li>
					</ul>
				</li>
				<li class="monitor">
					<a href="javascript:;">
					<i class="fa fa-dashboard"></i>
					<span class="title">
						<fmt:message key="monitor" />
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="monitoractivity">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-cogs"></i>
							<fmt:message key="monitor.activity" /></a>
						</li>
						<li class="monitornoactivity">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-frown-o"></i>
							<fmt:message key="monitor.noactivity" /></a>
						</li>
					</ul>
				</li>
				<li class="users">
					<a href="<spring:url value="/admin/users/" htmlEscape="true "/>">
					<i class="fa fa-group"></i>
					<span class="title">
						<fmt:message key="users" />
					</span>
					</a>
				</li>
				<li class="last ">
					<a href="<spring:url value="/logout" htmlEscape="true" />">
					<i class="fa fa-sign-out"></i>
					<span class="title">
						<fmt:message key="log_out" />
					</span>
					</a>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
</div>
<!-- END SIDEBAR -->
