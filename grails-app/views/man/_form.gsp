<%@ page import="com.shake.test.Man" %>



<div class="fieldcontain ${hasErrors(bean: manInstance, field: 'children', 'error')} ">
	<label for="children">
		<g:message code="man.children.label" default="Children" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${manInstance?.children?}" var="c">
    <li><g:link controller="child" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="child" action="create" params="['man.id': manInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'child.label', default: 'Child')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: manInstance, field: 'myWife', 'error')} required">
	<label for="myWife">
		<g:message code="man.myWife.label" default="My Wife" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="myWife" name="myWife.id" from="${com.shake.test.Wife.list()}" optionKey="id" optionValue="name" required="" value="${manInstance?.myWife?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: manInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="man.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${manInstance?.name}"/>

</div>

