class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/persons"(resources: 'person') {
            '/communications'(resources: 'communication')
        }               

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
