
<%@ page import="com.shake.test.Man" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'man.label', default: 'Man')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-man" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-man" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'man.name.label', default: 'Name')}" />
						<th><g:message code="man.myWife.label" default="My Wife" /></th>
						<th><g:message code="man.children.label" default="Children" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${manInstanceList}" status="i" var="manInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						<td><g:link action="show" id="${manInstance.id}">${fieldValue(bean: manInstance, field: "name")}</g:link></td>
						<td>${fieldValue(bean: manInstance, field: "myWife.name")}</td>
						<td>${fieldValue(bean: manInstance, field: "children.name")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${manInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
