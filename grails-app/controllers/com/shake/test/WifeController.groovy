package com.shake.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WifeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Wife.list(params), model:[wifeInstanceCount: Wife.count()]
    }

    def show(Wife wifeInstance) {
        respond wifeInstance
    }

    def create() {
        respond new Wife(params)
    }

    @Transactional
    def save(Wife wifeInstance) {
        if (wifeInstance == null) {
            notFound()
            return
        }

        if (wifeInstance.hasErrors()) {
            respond wifeInstance.errors, view:'create'
            return
        }

        wifeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wife.label', default: 'Wife'), wifeInstance.id])
                redirect wifeInstance
            }
            '*' { respond wifeInstance, [status: CREATED] }
        }
    }

    def edit(Wife wifeInstance) {
        respond wifeInstance
    }

    @Transactional
    def update(Wife wifeInstance) {
        if (wifeInstance == null) {
            notFound()
            return
        }

        if (wifeInstance.hasErrors()) {
            respond wifeInstance.errors, view:'edit'
            return
        }

        wifeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Wife.label', default: 'Wife'), wifeInstance.id])
                redirect wifeInstance
            }
            '*'{ respond wifeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Wife wifeInstance) {

        if (wifeInstance == null) {
            notFound()
            return
        }

        wifeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Wife.label', default: 'Wife'), wifeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wife.label', default: 'Wife'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
