
<%@ page import="com.shake.test.Man" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'man.label', default: 'Man')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-man" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-man" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list man">
			
				<g:if test="${manInstance?.children}">
				<li class="fieldcontain">
					<span id="children-label" class="property-label"><g:message code="man.children.label" default="Children" /></span>
					
						<g:each in="${manInstance.children}" var="c">
						<span class="property-value" aria-labelledby="children-label"><g:link controller="child" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${manInstance?.myWife}">
				<li class="fieldcontain">
					<span id="myWife-label" class="property-label"><g:message code="man.myWife.label" default="My Wife" /></span>
					
						<span class="property-value" aria-labelledby="myWife-label"><g:link controller="wife" action="show" id="${manInstance?.myWife?.id}">${manInstance?.myWife?.name}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${manInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="man.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${manInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:manInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${manInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
