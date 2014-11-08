<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade" id="portlet-config" tabindex="-1" data-role="dialog" data-aria-labelledby="myModalLabel" data-aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
				<h4 class="modal-title"><spring:message code="modal.title"/></h4>
			</div>
			<div class="modal-body">
				 <spring:message code="modal.body"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success"><spring:message code="modal.save"/></button>
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="modal.close"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<!-- BEGIN STYLE CUSTOMIZER -->
<div class="theme-panel hidden-xs hidden-sm">
	<div class="toggler">
		<i class="fa fa-gear"></i>
	</div>
	<div class="theme-options">
		<div class="theme-option theme-colors clearfix">
			<span>
				 <spring:message code="theme.color"/>
			</span>
			<ul>
				<li class="color-black current color-default tooltips" data-style="default" data-original-title=<spring:message code="theme.dark" />>
				</li>
				<li class="color-grey tooltips" data-style="grey" data-original-title=<spring:message code="theme.grey"/>>
				</li>
				<li class="color-blue tooltips" data-style="blue" data-original-title=<spring:message code="theme.blue"/>>
				</li>
				<li class="color-red tooltips" data-style="red" data-original-title=<spring:message code="theme.red"/>>
				</li>
				<li class="color-light tooltips" data-style="light" data-original-title=<spring:message code="theme.light"/>>
				</li>
			</ul>
		</div>
		<div class="theme-option">
			<span>
				 <spring:message code="theme.layout"/>
			</span>
			<select class="layout-option form-control input-small">
				<option value="fluid" selected="selected"><spring:message code="theme.fluid"/></option>
				<option value="boxed"><spring:message code="theme.boxed"/></option>
			</select>
		</div>
		<div class="theme-option">
			<span>
				 <spring:message code="theme.header"/>
			</span>
			<select class="header-option form-control input-small">
				<option value="fixed" selected="selected"><spring:message code="theme.fixed"/></option>
				<option value="default"><spring:message code="theme.default"/></option>
			</select>
		</div>
		<div class="theme-option">
			<span>
				 <spring:message code="theme.sidebar"/>
			</span>
			<select class="sidebar-option form-control input-small">
				<option value="fixed" selected="selected"><spring:message code="theme.fixed"/></option>
				<option value="default"><spring:message code="theme.default"/></option>
			</select>
		</div>
		<div class="theme-option">
			<span>
				 <spring:message code="theme.sbposition"/>
			</span>
			<select class="sidebar-pos-option form-control input-small">
				<option value="left" selected="selected"><spring:message code="theme.left"/></option>
				<option value="right"><spring:message code="theme.right"/></option>
			</select>
		</div>
		<div class="theme-option">
			<span>
				 <spring:message code="theme.footer"/>
			</span>
			<select class="footer-option form-control input-small">
				<option value="fixed" selected="selected"><spring:message code="theme.fixed"/></option>
				<option value="default"><spring:message code="theme.default"/></option>
			</select>
		</div>
	</div>
</div>
<!-- END BEGIN STYLE CUSTOMIZER -->