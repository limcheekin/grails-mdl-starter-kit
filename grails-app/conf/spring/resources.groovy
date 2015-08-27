import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.json.JsonRenderer
import org.grails.mdl.*

beans = {
    personRenderer(JsonRenderer, Person) {
        excludes = ['class']
    }

    peopleRenderer(JsonCollectionRenderer, Person) {
        excludes = ['class']
    }	

    communicationRenderer(JsonRenderer, Communication) {
        excludes = ['class', 'person']
    }

    communicationsRenderer(JsonCollectionRenderer, Communication) {
        excludes = ['class']
    }

    jsonEnumMarshallerRegistrar(JsonEnumMarshallerRegistrar)	
}
