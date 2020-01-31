package jira2testrail

import grails.gorm.transactions.Transactional

@Transactional
class JiraService {

    Map getTaskBriefInformation(Settings settings, String issueKey) {

        def jiraClient = new JiraRestClient(new URI(settings.jiraServer), JiraRestClient.getClient(settings.jiraUsername, settings.jiraPassword))

        String query = "key=$issueKey"

        def result = jiraClient.getURL("${settings.jiraServer}/rest/api/latest/search?jql=" + URLEncoder.encode(query, 'UTF-8') + '&maxResults=1&expand=renderedFields')

        def issue = result.issues.myArrayList.find()
        [
                key: issue.key,
                url: "${settings.jiraServer}/browse/${issue.key}",
                summary: issue.fields.summary,
                description: issue.renderedFields.description
        ]
    }
}
