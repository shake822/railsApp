package com.shake.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond A.list(params), model:[aInstanceCount: A.count()]
    }

    def show(A aInstance) {
        respond aInstance
    }

    def create() {
        respond new A(params)
    }

    @Transactional
    def save(A aInstance) {
        if (aInstance == null) {
            notFound()
            return
        }

        if (aInstance.hasErrors()) {
            respond aInstance.errors, view:'create'
            return
        }

        aInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'a.label', default: 'A'), aInstance.id])
                redirect aInstance
            }
            '*' { respond aInstance, [status: CREATED] }
        }
    }

    def edit(A aInstance) {
        respond aInstance
    }

    @Transactional
    def update(A aInstance) {
        if (aInstance == null) {
            notFound()
            return
        }

        if (aInstance.hasErrors()) {
            respond aInstance.errors, view:'edit'
            return
        }

        aInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'A.label', default: 'A'), aInstance.id])
                redirect aInstance
            }
            '*'{ respond aInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(A aInstance) {

        if (aInstance == null) {
            notFound()
            return
        }

        aInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'A.label', default: 'A'), aInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'a.label', default: 'A'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
