
<%@ page import="com.shake.Apk" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'apk.label', default: 'Apk')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-apk" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-apk" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
					 
						<g:sortableColumn property="packageName" title="${message(code: 'apk.packageName.label', default: 'Package Name')}" />
					
						<g:sortableColumn property="projectDesc" title="${message(code: 'apk.projectDesc.label', default: 'Project Desc')}" />
					
						<g:sortableColumn property="projectName" title="${message(code: 'apk.projectName.label', default: 'Project Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${apkInstanceList}" status="i" var="apkInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${apkInstance.id}">${fieldValue(bean: apkInstance, field: "packageName")}</g:link></td>
					 
					
						<td>${fieldValue(bean: apkInstance, field: "projectDesc")}</td>
					
						<td>${fieldValue(bean: apkInstance, field: "projectName")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${apkInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
