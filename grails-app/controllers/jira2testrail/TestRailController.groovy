package jira2testrail

import com.gurock.testrail.APIClient
import grails.converters.JSON

class TestRailController {

    def testRailService

    def projects() {

        def settings = Settings.list().find()
        if (!settings) {
            redirect(controller: 'import', action: 'settings')
            return
        }

        render(testRailService.getProjects(settings) as JSON)
    }

    def suites() {

        def settings = Settings.list().find()
        if (!settings) {
            redirect(controller: 'import', action: 'settings')
            return
        }

        def projectId = params.'filter[filters][0][value]'
        render(testRailService.getSuites(settings, projectId) as JSON)
    }

    def sections() {

        def settings = Settings.list().find()
        if (!settings) {
            redirect(controller: 'import', action: 'settings')
            return
        }

        def projectId = params.'filter[filters][0][value]'.split('_').first()
        def suiteId = params.'filter[filters][0][value]'.split('_').last()

        render(testRailService.getSections(settings, projectId, suiteId) as JSON)
    }
}
