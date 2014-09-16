<%@ page import="com.shake.Apk" %>



<div class="fieldcontain ${hasErrors(bean: apkInstance, field: 'apkFile', 'error')} required">
	<label for="apkFile">
		<g:message code="apk.apkFile.label" default="Apk File" />
		<span class="required-indicator">*</span>
	</label>
	 <input type="file" name="apkFile" id="apkFile"/>

</div>

<div class="fieldcontain ${hasErrors(bean: apkInstance, field: 'packageName', 'error')} required">
	<label for="packageName">
		<g:message code="apk.packageName.label" default="Package Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="packageName" required="" value="${apkInstance?.packageName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: apkInstance, field: 'projectDesc', 'error')} required">
	<label for="projectDesc">
		<g:message code="apk.projectDesc.label" default="Project Desc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="projectDesc" required="" value="${apkInstance?.projectDesc}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: apkInstance, field: 'projectName', 'error')} required">
	<label for="projectName">
		<g:message code="apk.projectName.label" default="Project Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="projectName" required="" value="${apkInstance?.projectName}"/>

</div>

