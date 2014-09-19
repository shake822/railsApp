<%@ page import="com.shake.test.Wife" %>



<div class="fieldcontain ${hasErrors(bean: wifeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="wife.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${wifeInstance?.name}"/>

</div>

