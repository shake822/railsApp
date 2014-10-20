<%@ page import="com.shake.test.Book" %>



 

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'bookName', 'error')} required">
	<label for="bookName">
		<g:message code="book.bookName.label" default="Book Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="bookName" required="" value="${bookInstance?.bookName}"/>

</div>

