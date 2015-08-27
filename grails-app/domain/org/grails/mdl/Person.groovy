package org.grails.mdl

class Person {
	String firstName
	String lastName
	Integer age
	String description

	static hasMany = [communications: Communication]

    static constraints = {
    	firstName nullable:true
    	lastName nullable:true
    	age nullable:true
    	description nullable:true, maxSize:100
    }
}
