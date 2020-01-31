package jira2testrail

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'import', action: 'ticket')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
