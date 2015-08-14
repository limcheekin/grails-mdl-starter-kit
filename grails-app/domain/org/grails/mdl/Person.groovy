package org.grails.mdl

class Person {
	String firstName
	String lastName
	Integer age
	String description

	static hasMany = [communications: Communication]

    static constraints = {
    	firstName blank:false, size:5..60
    }
}
