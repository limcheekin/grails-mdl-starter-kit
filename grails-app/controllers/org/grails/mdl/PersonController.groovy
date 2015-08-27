package org.grails.mdl

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import jxl.DateCell
import jxl.LabelCell
import jxl.NumberCell
import jxl.biff.EmptyCell
import jxl.Sheet
import jxl.Workbook
import groovy.json.JsonOutput
import groovy.json.JsonSlurper 



@Transactional(readOnly = true)
class PersonController {
    private final static int COLUMN_LAST_NAME = 0
    private final static int COLUMN_FIRST_NAME = 1
    private final static int COLUMN_AGE = 2
    private final static int COLUMN_DESCRIPTION = 3
    private final static String URL = 'http://c0052724.itcs.hp.com:8012/api/Validation'


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Person.list(params), model:[personCount: Person.count()]
    }

    def show(Person person) {
        respond person
    }

    def create() {
        respond new Person(params)
    }

    def upload() {}    

    // ref: http://grails.asia/grails-example-upload-excel-data-file-to-database
    def doUpload() {
        def file = request.getFile('file')
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        List fields = []
        HttpURLConnection connection
        def jsonSlurper = new JsonSlurper()
        Map errorMessages = [:]
        String lastName
        String firstName
        Integer age
        String description

        // skip first row (row 0) by starting from 1
        for (int row = 1; row < sheet.getRows(); row++) {
            lastName = sheet.getCell(COLUMN_LAST_NAME, row) instanceof EmptyCell?"":sheet.getCell(COLUMN_LAST_NAME, row).string
            firstName = sheet.getCell(COLUMN_FIRST_NAME, row) instanceof EmptyCell?"":sheet.getCell(COLUMN_FIRST_NAME, row).string
            age = sheet.getCell(COLUMN_AGE, row) instanceof EmptyCell?0:sheet.getCell(COLUMN_AGE, row).value
            description = sheet.getCell(COLUMN_DESCRIPTION, row) instanceof EmptyCell?"":sheet.getCell(COLUMN_DESCRIPTION, row).string
            fields << [Name: "lastName", Value: lastName]
            fields << [Name: "firstName", Value: firstName]
            fields << [Name: "age", Value: age]
            fields << [Name: "description", Value: description]

            connection = URL.toURL().openConnection()
            connection.doOutput = true
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json");
            // http://www.groovy-lang.org/json.html
            // println JsonOutput.prettyPrint(JsonOutput.toJson([AccountId: '55cc36acb62ffd1ee049964f', FormId: 'createPerson', Fields: fields]))
            connection.outputStream.withWriter { Writer writer ->
                writer << JsonOutput.toJson([AccountId: '55cc36acb62ffd1ee049964f', FormId: 'createPerson', Fields: fields])
            }
            Map response = jsonSlurper.parseText(connection.inputStream.withReader { Reader reader -> reader.text })
            fields.clear()

            if (!response.IsError) {
                new Person(lastName:lastName , firstName:firstName,
                    age:age, description:description).save()      
            } else {
                errorMessages << ["${row + 1}": response.Fields*.ErrorMessage]
            }
        }

        if (errorMessages.size() > 0) {
            flash.errorMessages = errorMessages
        }
        redirect (action:'index')
    }

    @Transactional
    def save(Person person) {
        if (person == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (person.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond person.errors, view:'create'
            return
        }

        person.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect person
            }
            '*' { respond person, [status: CREATED] }
        }
    }

    def edit(Person person) {
        respond person
    }

    @Transactional
    def update(Person person) {
        if (person == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (person.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond person.errors, view:'edit'
            return
        }

        person.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect person
            }
            '*'{ respond person, [status: OK] }
        }
    }

    @Transactional
    def delete(Person person) {

        if (person == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        person.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
