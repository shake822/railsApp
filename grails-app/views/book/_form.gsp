<%@ page import="com.shake.test.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="book.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${com.shake.test.Author.list()}" optionKey="id" required="" value="${bookInstance?.author?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'bookName', 'error')} required">
	<label for="bookName">
		<g:message code="book.bookName.label" default="Book Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="bookName" required="" value="${bookInstance?.bookName}"/>

</div>

