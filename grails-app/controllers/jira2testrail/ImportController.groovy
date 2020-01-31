package jira2testrail

class ImportController {

    def jiraService
    def testCaseParseService
    def testRailService

    def ticket() {
        if (!Settings.list())
            redirect(action: 'settings')
    }

    def edit() {

        def settings = Settings.list().find()
        if (!settings) {
            redirect(action: 'settings')
            return
        }

        def taskInfo = jiraService.getTaskBriefInformation(settings, params.issueId)

        def testSection = [
                name       : "${taskInfo.key}: ${taskInfo.summary}",
                description: taskInfo.url
        ]

        def testCase = testCaseParseService.parseTestCase(taskInfo.description)

        [testCase: testCase, testSection: testSection, key: taskInfo.key]
    }

    def result() {

        def settings = Settings.list().find()
        if (!settings) {
            redirect(action: 'settings')
            return
        }

        def project = params.project
        def suite = params.suite.split('_').last()
        def parentSection = params.parentSection.split('_').last()

        def section = testRailService.addSection(settings, project, suite, parentSection, params.sectionName, params.sectionDescription)

        def steps = []

        for (def i = 0; i < params.userInteractionSequence.size(); i++)
            steps << [content: params.userInteractionSequence[i], expected: params.expectedOutcome[i]]

        def result = testRailService.addCase(settings, section.id?.toString(), params.title, params.status.toInteger(), params.references, params.generator, params.preconditions, steps)
        if (result) {
            flash.message = "You created a new Test Case (C${result.id}) successfully! Please continue with importing the next one.."
            redirect(action: 'ticket')
        }
    }

    def settings() {
        def settings = Settings.list().find()
        if (!settings) {
            settings = new Settings()
        }
        [settings: settings]
    }

    def saveSettings() {
        def settings = Settings.list().find()
        if (!settings)
            settings = new Settings()
        settings.properties = params
        settings.save()
        redirect(action: 'ticket')
    }
}
