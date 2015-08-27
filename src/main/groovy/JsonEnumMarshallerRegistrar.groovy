import javax.annotation.PostConstruct
import grails.converters.JSON

// Ref: http://stackoverflow.com/questions/15030758/grails-enumeration-to-json
class JsonEnumMarshallerRegistrar {

    @PostConstruct
    void registerMarshallers() {
    	//convert all enum values to plain String value
    	JSON.registerObjectMarshaller(Enum, { Enum e -> e.name() }) 
    }
}