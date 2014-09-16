<%@ page import="com.shake.MyApk" %>



<div class="fieldcontain ${hasErrors(bean: myApkInstance, field: 'sss', 'error')} required">
	<label for="sss">
		<g:message code="myApk.sss.label" default="Sss" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sss" required="" value="${myApkInstance?.sss}"/>

</div>

