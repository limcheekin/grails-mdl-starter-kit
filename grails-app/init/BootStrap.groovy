import org.grails.mdl.*

class BootStrap {

    def init = { servletContext ->
    	
        new Person(firstName: "Chee Kin", lastName: 'Lim', age: 25, 
    			  description: 'Person 1')
    		.addToCommunications(type: CommunicationType.WORK_NUMBER, value: "123456789")
    		.save(failOnError: true)

    	new Person(firstName: "Chee Keong", lastName: 'Lee', age: 25, 
    			  description: 'Person 2')
    		.addToCommunications(type: CommunicationType.WORK_NUMBER, value: "123456789")
    		.addToCommunications(type: CommunicationType.MOBILE_NUMBER, value: "987654321")
    		.save(failOnError: true)
            	    	
    }
    def destroy = {
    }
}
