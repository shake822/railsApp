
<%@ page import="com.shake.Apk" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'apk.label', default: 'Apk')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-apk" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-apk" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list apk">
			
				<g:if test="${apkInstance?.apkFile}">
				<li class="fieldcontain">
					<span id="apkFile-label" class="property-label"><g:message code="apk.apkFile.label" default="Apk File" /></span>
					
						<span class="property-value" aria-labelledby="apkFile-label"><g:fieldValue bean="${apkInstance}" field="apkFile"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${apkInstance?.packageName}">
				<li class="fieldcontain">
					<span id="packageName-label" class="property-label"><g:message code="apk.packageName.label" default="Package Name" /></span>
					
						<span class="property-value" aria-labelledby="packageName-label"><g:fieldValue bean="${apkInstance}" field="packageName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${apkInstance?.projectDesc}">
				<li class="fieldcontain">
					<span id="projectDesc-label" class="property-label"><g:message code="apk.projectDesc.label" default="Project Desc" /></span>
					
						<span class="property-value" aria-labelledby="projectDesc-label"><g:fieldValue bean="${apkInstance}" field="projectDesc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${apkInstance?.projectName}">
				<li class="fieldcontain">
					<span id="projectName-label" class="property-label"><g:message code="apk.projectName.label" default="Project Name" /></span>
					
						<span class="property-value" aria-labelledby="projectName-label"><g:fieldValue bean="${apkInstance}" field="projectName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:apkInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${apkInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
