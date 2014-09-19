<%@ page import="com.shake.test.A" %>



<div class="fieldcontain ${hasErrors(bean: aInstance, field: 'b', 'error')} required">
	<label for="b">
		<g:message code="a.b.label" default="B" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="b" name="b.id" from="${com.shake.test.B.list()}" optionKey="id" required="" value="${aInstance?.b?.id}" class="many-to-one"/>

</div>

