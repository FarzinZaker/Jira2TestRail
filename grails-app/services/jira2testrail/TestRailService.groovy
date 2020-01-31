package jira2testrail

import com.gurock.testrail.APIClient
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class TestRailService {

    def getProjects(Settings settings) {

        APIClient client = new APIClient(settings.testRailServer)
        client.setUser(settings.testRailUsername)
        client.setPassword(settings.testRailPassword)

        def result = client.sendGet('get_projects').collect { [id: it.id, name: it.name] }
        result
    }

    def getSuites(Settings settings, String projectId) {

        APIClient client = new APIClient(settings.testRailServer)
        client.setUser(settings.testRailUsername)
        client.setPassword(settings.testRailPassword)

        def result = client.sendGet("get_suites/$projectId").collect { [id: "${projectId}_${it.id}", name: it.name] }
        result
    }

    def getSections(Settings settings, String projectId, String suiteId) {

        APIClient client = new APIClient(settings.testRailServer)
        client.setUser(settings.testRailUsername)
        client.setPassword(settings.testRailPassword)

        def result = client.sendGet("get_sections/$projectId&suite_id=$suiteId").collect { [id: "${projectId}_${suiteId}_${it.id}", name: it.name] }
        result
    }

    def addSection(Settings settings, String projectId, String suiteId, String parentSection, String name, String description) {

        def sections = getSections(settings, projectId, suiteId)
        def section = sections.collect { [id: it.id.split('_').last(), name: it.name] }.find { it.name == name }
        if (section)
            return section

        APIClient client = new APIClient(settings.testRailServer)
        client.setUser(settings.testRailUsername)
        client.setPassword(settings.testRailPassword)

        def data = [
                suite_id   : suiteId,
                parent_id  : parentSection,
                name       : name,
                description: description
        ] as JSON

        def result = client.sendPost("add_Section/$projectId", data)

        [
                id  : result.id,
                name: result.name
        ]
    }

    def addCase(Settings settings, String sectionId, String title, Integer status, String references, String generator, String preconditions, steps) {

        APIClient client = new APIClient(settings.testRailServer)
        client.setUser(settings.testRailUsername)
        client.setPassword(settings.testRailPassword)

        def data = [
                title          : title,
                custom_status  : status,
                refs           : references,
                generator      : generator,
                custom_preconds: preconditions,
                custom_steps_separated   : steps
        ] as JSON

        def result = client.sendPost("add_case/$sectionId", data)
        result
    }

}
