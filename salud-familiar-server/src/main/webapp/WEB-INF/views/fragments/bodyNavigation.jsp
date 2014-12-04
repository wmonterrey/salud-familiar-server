<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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
						<spring:message code="dashboard"/>
					</span>
					<span class="selected">
					</span>
					</a>
				</li>
				<sec:authorize url="/info/">
				<li class="hsf">
					<a href="javascript:;">
					<i class="fa fa-table"></i>
					<span class="title">
						<spring:message code="hsf"/>
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="hsfnuevo">
							<a href="<spring:url value="/info/newHsf" htmlEscape="true "/>">
							<i class="fa fa-file-o"></i>
							<spring:message code="hsf.nuevo"/></a>
						</li>
						<li class="hsfsearch">
							<a href="<spring:url value="/info/searchHsf" htmlEscape="true "/>">
							<i class="fa fa-search"></i>
							<spring:message code="hsf.search"/></a>
						</li>
					</ul>
				</li>
				</sec:authorize>
				<sec:authorize url="/report/">
				<li class="reports">
					<a href="javascript:;">
					<i class="fa fa-folder-open-o"></i>
					<span class="title">
						<spring:message code="reports"/>
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="agregados">
							<a href="javascript:;">
							<i class="fa fa-bar-chart-o"></i> <spring:message code="aggregate"/>
							<span class="arrow">
							</span>
							</a>
							<ul class="sub-menu">
								<li class="reportday">
									<a href="<spring:url value="/report/visitbyday" htmlEscape="true "/>">
									<i class="fa fa-calendar"></i>
									<spring:message code="report.day"/></a>
								</li>
								<li class="reportarea">
									<a href="<spring:url value="/report/visitbyarea" htmlEscape="true "/>">
									<i class="fa fa-map-marker"></i>
									<spring:message code="report.area"/></a>
								</li>
								<li class="consolidado">
									<a href="<spring:url value="/report/consolidado" htmlEscape="true "/>">
									<i class="fa fa-table"></i>
									<spring:message code="summary"/></a>
								</li>
								<li class="embarazos">
									<a href="<spring:url value="/report/embarazo" htmlEscape="true "/>">
									<i class="fa fa-heart"></i>
									<spring:message code="pregnancies"/></a>
								</li>
								<li class="cronicos">
									<a href="<spring:url value="/report/cronico" htmlEscape="true "/>">
									<i class="fa fa-user-md"></i>
									<spring:message code="cronics"/></a>
								</li>
								<li class="enfermedades">
									<a href="<spring:url value="/report/enfermedad" htmlEscape="true "/>">
									<i class="fa fa-medkit"></i>
									<spring:message code="diseases"/></a>
								</li>
							</ul>
						</li>
						<li class="listados">
							<a href="javascript:;">
							<i class="fa fa-list-ul"></i> <spring:message code="lists"/>
							<span class="arrow">
							</span>
							</a>
							<ul class="sub-menu">
								<li class="reportfamily">
									<a href="<spring:url value="/report/family" htmlEscape="true "/>">
									<i class="fa fa-group"></i>
									<spring:message code="report.family"/></a>
								</li>
								<li class="reportvisit">
									<a href="<spring:url value="/report/visit" htmlEscape="true "/>">
									<i class="fa fa-map-marker"></i>
									<spring:message code="report.visit"/></a>
								</li>
								<li class="reportperson">
									<a href="<spring:url value="/report/person" htmlEscape="true "/>">
									<i class="fa fa-male"></i>
									<spring:message code="report.person"/></a>
								</li>
								<li class="reportill">
									<a href="<spring:url value="/report/ill" htmlEscape="true "/>">
									<i class="fa fa-stethoscope"></i>
									<spring:message code="report.ill"/></a>
								</li>
							</ul>
						</li>
						<li class="caracterizacion">
							<a href="javascript:;">
							<i class="fa fa-book"></i> <spring:message code="caract"/>
							<span class="arrow">
							</span>
							</a>
							<ul class="sub-menu">
								<li class="chs">
									<a href="<spring:url value="/report/chs" htmlEscape="true "/>">
									<i class="fa fa-building"></i>
									<spring:message code="step3"/></a>
								</li>
								<li class="fse">
									<a href="<spring:url value="/report/fse" htmlEscape="true "/>">
									<i class="fa fa-money"></i>
									<spring:message code="step4"/></a>
								</li>
								<li class="ff">
									<a href="<spring:url value="/report/ff" htmlEscape="true "/>">
									<i class="fa fa-group"></i>
									<spring:message code="step5"/></a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				</sec:authorize>
				<sec:authorize url="/noshown/">
				<li class="monitor">
					<a href="javascript:;">
					<i class="fa fa-dashboard"></i>
					<span class="title">
						<spring:message code="monitor"/>
					</span>
					<span class="arrow ">
					</span>
					</a>
					<ul class="sub-menu">
						<li class="monitoractivity">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-cogs"></i>
							<spring:message code="monitor.activity"/></a>
						</li>
						<li class="monitornoactivity">
							<a href="<spring:url value="/" htmlEscape="true "/>">
							<i class="fa fa-frown-o"></i>
							<spring:message code="monitor.noactivity"/></a>
						</li>
					</ul>
				</li>
				</sec:authorize>
				<sec:authorize url="/noshown/">
				<li class="catalog">
					<a href="<spring:url value="/catalog/catalogs/" htmlEscape="true "/>">
					<i class="fa fa-suitcase"></i>
					<span class="title">
						<spring:message code="catalog"/>
					</span>
					</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/admin/">
				<li class="users">
					<a href="<spring:url value="/admin/users/" htmlEscape="true "/>">
					<i class="fa fa-group"></i>
					<span class="title">
						<spring:message code="users"/>
					</span>
					</a>
				</li>
				</sec:authorize>
				<li class="last ">
					<a href="<spring:url value="/logout" htmlEscape="true" />">
					<i class="fa fa-sign-out"></i>
					<span class="title">
						<spring:message code="log_out"/>
					</span>
					</a>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
</div>
<!-- END SIDEBAR -->
