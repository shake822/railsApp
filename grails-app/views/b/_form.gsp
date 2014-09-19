<%@ page import="com.shake.test.B" %>



<div class="fieldcontain ${hasErrors(bean: bInstance, field: 'a', 'error')} required">
	<label for="a">
		<g:message code="b.a.label" default="A" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="a" name="a.id" from="${com.shake.test.A.list()}" optionKey="id" required="" value="${bInstance?.a?.id}" class="many-to-one"/>

</div>

