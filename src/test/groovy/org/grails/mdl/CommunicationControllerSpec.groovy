package org.grails.mdl

import grails.test.mixin.*
import spock.lang.*

@TestFor(CommunicationController)
@Mock(Communication)
class CommunicationControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.communicationList
            model.communicationCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.communication!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def communication = new Communication()
            communication.validate()
            controller.save(communication)

        then:"The create view is rendered again with the correct model"
            model.communication!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            communication = new Communication(params)

            controller.save(communication)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/communication/show/1'
            controller.flash.message != null
            Communication.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def communication = new Communication(params)
            controller.show(communication)

        then:"A model is populated containing the domain instance"
            model.communication == communication
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def communication = new Communication(params)
            controller.edit(communication)

        then:"A model is populated containing the domain instance"
            model.communication == communication
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/communication/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def communication = new Communication()
            communication.validate()
            controller.update(communication)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.communication == communication

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            communication = new Communication(params).save(flush: true)
            controller.update(communication)

        then:"A redirect is issued to the show action"
            communication != null
            response.redirectedUrl == "/communication/show/$communication.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/communication/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def communication = new Communication(params).save(flush: true)

        then:"It exists"
            Communication.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(communication)

        then:"The instance is deleted"
            Communication.count() == 0
            response.redirectedUrl == '/communication/index'
            flash.message != null
    }
}
