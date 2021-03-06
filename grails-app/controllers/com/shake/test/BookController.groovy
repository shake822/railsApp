package com.shake.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import org.springframework.web.servlet.ModelAndView

@Transactional(readOnly = true)
class BookController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Book.list(params), model:[bookInstanceCount: Book.count()]
	}

	def show(Book bookInstance) {
		respond bookInstance
	}

	def showBook(Book bookInstance){
		println "showBook"
		println request.name
		println params.bookId
		//		[book:  Book.get(params.bookId)]
		Book book =  Book.get(params.bookId)
		println book
		//		respond book
		//		return new ModelAndView("/book/showBook1", [ book : book ])

		def map = [book:book]
//		render(view:"/book/showBook1",model:map)
		def books =  Book.findAll()
		render {
			for (b in books) {
			   div(id: b.id, style:'xxx',b.bookName)
			}
		 } 
	}
	
	def first() {
		chain(action: second, model: [one: 1])
	}

	def second () {
		chain(action: third, model: [two: 2])
	}

	def third() {
		[three: 3]
	}
	def create() {
		respond new Book(params)
	}

	@Transactional
	def save(Book bookInstance) {
		if (bookInstance == null) {
			notFound()
			return
		}

		if (bookInstance.hasErrors()) {
			respond bookInstance.errors, view:'create'
			return
		}

		bookInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'book.label', default: 'Book'),
					bookInstance.id
				])
				redirect bookInstance
			}
			'*' { respond bookInstance, [status: CREATED] }
		}
	}

	def edit(Book bookInstance) {
		respond bookInstance
	}

	@Transactional
	def update(Book bookInstance) {
		if (bookInstance == null) {
			notFound()
			return
		}

		if (bookInstance.hasErrors()) {
			respond bookInstance.errors, view:'edit'
			return
		}

		bookInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Book.label', default: 'Book'),
					bookInstance.id
				])
				redirect bookInstance
			}
			'*'{ respond bookInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Book bookInstance) {

		if (bookInstance == null) {
			notFound()
			return
		}

		bookInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Book.label', default: 'Book'),
					bookInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'book.label', default: 'Book'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
