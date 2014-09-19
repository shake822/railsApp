package com.shake.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ManController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Man.list(params), model:[manInstanceCount: Man.count()]
    }

    def show(Man manInstance) {
        respond manInstance
    }

    def create() {
        respond new Man(params)
    }

    @Transactional
    def save(Man manInstance) {
        if (manInstance == null) {
            notFound()
            return
        }

        if (manInstance.hasErrors()) {
            respond manInstance.errors, view:'create'
            return
        }

        manInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'man.label', default: 'Man'), manInstance.id])
                redirect manInstance
            }
            '*' { respond manInstance, [status: CREATED] }
        }
    }

    def edit(Man manInstance) {
        respond manInstance
    }

    @Transactional
    def update(Man manInstance) {
        if (manInstance == null) {
            notFound()
            return
        }

        if (manInstance.hasErrors()) {
            respond manInstance.errors, view:'edit'
            return
        }

        manInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Man.label', default: 'Man'), manInstance.id])
                redirect manInstance
            }
            '*'{ respond manInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Man manInstance) {

        if (manInstance == null) {
            notFound()
            return
        }

        manInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Man.label', default: 'Man'), manInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'man.label', default: 'Man'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
