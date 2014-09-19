package com.shake.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond B.list(params), model:[bInstanceCount: B.count()]
    }

    def show(B bInstance) {
        respond bInstance
    }

    def create() {
        respond new B(params)
    }

    @Transactional
    def save(B bInstance) {
        if (bInstance == null) {
            notFound()
            return
        }

        if (bInstance.hasErrors()) {
            respond bInstance.errors, view:'create'
            return
        }

        bInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'b.label', default: 'B'), bInstance.id])
                redirect bInstance
            }
            '*' { respond bInstance, [status: CREATED] }
        }
    }

    def edit(B bInstance) {
        respond bInstance
    }

    @Transactional
    def update(B bInstance) {
        if (bInstance == null) {
            notFound()
            return
        }

        if (bInstance.hasErrors()) {
            respond bInstance.errors, view:'edit'
            return
        }

        bInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'B.label', default: 'B'), bInstance.id])
                redirect bInstance
            }
            '*'{ respond bInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(B bInstance) {

        if (bInstance == null) {
            notFound()
            return
        }

        bInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'B.label', default: 'B'), bInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'b.label', default: 'B'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
