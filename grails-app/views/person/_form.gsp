<%@ page import="shake.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'age', 'error')} required">
	<label for="age">
		<g:message code="person.age.label" default="Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="age" type="number" value="${personInstance.age}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'dateOfBirth', 'error')} required">
	<label for="dateOfBirth">
		<g:message code="person.dateOfBirth.label" default="Date Of Birth" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateOfBirth" precision="day"  value="${personInstance?.dateOfBirth}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="person.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${personInstance?.name}"/>

</div>

