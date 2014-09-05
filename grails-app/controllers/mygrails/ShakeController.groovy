package mygrails

import grails.converters.XML
import shake.Person

class ShakeController {
    ShakeController(){
        println " init "
    }
    //设置默认Action
    static defaultAction = "insertPerson"
    //设置Bean的范围
    //    static scope  = "prototype"
    def index() {
        println "shake.index"
        render(view: "/person/show",model:[person:Person.get(1)])
    }
    //    def beforeInterceptor = { println "Tracing action ${actionUri}" }
    
    //    def beforeInterceptor = [action: this.&auth, except: 'login']// defined with private scope, so it's not considered an action
    private auth() {
        println "auth"
    }
    
    def login() {
        println "login"
        // display login page
    }
    
    def listPersonJson(){
        println "listPersonJson"
        def results = Person.list()
        println "$results"
        render(contentType: "application/json") {
            persons = array {
                for (b in results) {
                    person name: b.name,age:b.age
                }
            }
            
        }
    }
    
    
    def xml(){
        render Person.list() as XML
    }
    def goOther(){
        redirect(controller: "shake.Person", action: "show", params: Person.get(1))
    }
    def insertPerson(){
        println "insertPerson"
        def p = new Person()
        p.name="shake"
        p.age=18
        p.dateOfBirth = new Date()
        p.save()
        flash.newPerson = p
        redirect(action:"showPerson")
    }
    def showPerson(){
        def p = flash.newPerson
        println "showPerson get Person $p"
    }
}
