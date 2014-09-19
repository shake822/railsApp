<%@ page import="com.shake.test.Child" %>



<div class="fieldcontain ${hasErrors(bean: childInstance, field: 'father', 'error')} required">
	<label for="father">
		<g:message code="child.father.label" default="Father" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="father" name="father.id" from="${com.shake.test.Man.list()}" optionKey="id" optionValue="name"required="" value="${childInstance?.father?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: childInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="child.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${childInstance?.name}"/>

</div>

