package org.grails.mdl

class Communication {

	CommunicationType type
	String value
	
	static belongsTo = [person:Person]
	
	static constraints = {
		type nullable:false
		value blank:false
	}
}

enum CommunicationType {
	WORK_NUMBER("Work Number"),
	HOME_NUMBER("Home Number"),
	MOBILE_NUMBER("Mobile Number"),
	EMAIL_ADDRESS("Email Address")

	private final String value

	CommunicationType(String value) { this.value = value }
  	public String value() { return value }
  	public String toString() { return value }
}

