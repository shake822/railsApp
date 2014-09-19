package com.shake.test



import grails.test.mixin.*
import spock.lang.*

@TestFor(BController)
@Mock(B)
class BControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.bInstanceList
            model.bInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.bInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def b = new B()
            b.validate()
            controller.save(b)

        then:"The create view is rendered again with the correct model"
            model.bInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            b = new B(params)

            controller.save(b)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/b/show/1'
            controller.flash.message != null
            B.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def b = new B(params)
            controller.show(b)

        then:"A model is populated containing the domain instance"
            model.bInstance == b
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def b = new B(params)
            controller.edit(b)

        then:"A model is populated containing the domain instance"
            model.bInstance == b
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/b/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def b = new B()
            b.validate()
            controller.update(b)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.bInstance == b

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            b = new B(params).save(flush: true)
            controller.update(b)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/b/show/$b.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/b/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def b = new B(params).save(flush: true)

        then:"It exists"
            B.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(b)

        then:"The instance is deleted"
            B.count() == 0
            response.redirectedUrl == '/b/index'
            flash.message != null
    }
}
