package com.shake



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ApkController {
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    def index(Integer max) {
        println max
        params.max = Math.min(max ?: 10, 100)
        respond Apk.list(params), model:[apkInstanceCount: Apk.count()]
    }
    
    def show(Apk apkInstance) {
        getFileContent(apkInstance)
        respond apkInstance
    }
    
    def create() {
        respond new Apk(params)
    }
    
    def getFileContent(Apk apkInstance){
        println '中文地步'
        println new String(apkInstance.apkFile,"UTF-8")
        println new String(apkInstance.apkFile,"ISO-8859-1")
        println new String(apkInstance.apkFile,"GBK")
    }
    
    @Transactional
    def save(Apk apkInstance) {
        if (apkInstance == null) {
            notFound()
            return
        }
        
        def f = request.getFile('apkFile')
        println f.getOriginalFilename()
        f.transferTo(new File("/home/zhaoqunqi",f.getOriginalFilename()))
        getFileContent(apkInstance)
        if (apkInstance.hasErrors()) {
            respond apkInstance.errors, view:'create'
            return
        }
        
        apkInstance.save flush:true
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [
                    message(code: 'apk.label', default: 'Apk'),
                    apkInstance.id
                ])
                redirect apkInstance
            }
            '*' { respond apkInstance, [status: CREATED] }
        }
    }
    
    def edit(Apk apkInstance) {
        respond apkInstance
    }
    
    @Transactional
    def update(Apk apkInstance) {
        if (apkInstance == null) {
            notFound()
            return
        }
        
        if (apkInstance.hasErrors()) {
            respond apkInstance.errors, view:'edit'
            return
        }
        
        apkInstance.save flush:true
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [
                    message(code: 'Apk.label', default: 'Apk'),
                    apkInstance.id
                ])
                redirect apkInstance
            }
            '*'{ respond apkInstance, [status: OK] }
        }
    }
    
    @Transactional
    def delete(Apk apkInstance) {
        
        if (apkInstance == null) {
            notFound()
            return
        }
        
        apkInstance.delete flush:true
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [
                    message(code: 'Apk.label', default: 'Apk'),
                    apkInstance.id
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
                    message(code: 'apk.label', default: 'Apk'),
                    params.id
                ])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
