package jira2testrail

import grails.gorm.transactions.Transactional
import org.cyberneko.html.parsers.SAXParser

@Transactional
class TestCaseParseService {

    Map parseTestCase(String description) {

        def slurper = new XmlSlurper(new SAXParser())
        def htmlParser = slurper.parseText(description)

        def tables = htmlParser.'**'.findAll { it.name() == 'TABLE' }

        def caseTitle = formatText(tables[0])

        def preconditions = tables[1].'**'.findAll { it.name() == 'TR' }
        preconditions.remove(preconditions.first())
        preconditions = preconditions.collect { row ->
            def parts = row.'**'.findAll { it.name() == 'TD' || it.name() == 'TH' }
            "${formatText(parts[0])}: ${formatText(parts[1])} ${formatText(parts[2])}".trim()
        }.join('\r\n\r\n')

        def steps = tables[2].'**'.findAll { it.name() == 'TR' }
        steps.remove(steps.first())
        steps = steps.collect { row ->
            def parts = row.'**'.findAll { it.name() == 'TD' || it.name() == 'TH' }
            [
                    index                  : formatText(parts[0]),
                    userInteractionSequence: formatText(parts[1]),
                    expectedOutcome        : formatText(parts[2])
            ]
        }

        [
                title        : caseTitle,
                preconditions: preconditions,
                steps        : steps
        ]
    }

    String formatText(node) {
        def prefix = ''
        def postFix = ''
        def body = ''
        def children = node.childNodes().toList()
        if (!children?.size()) {
            def textChildren = node.children.toList()
            if (textChildren.size())
                body += textChildren.collect {
                    it instanceof String ? it : formatText(it)
                }.join('\r\n')
            else
                body += node.localText().join('')
        } else
            body += node.localText().join('') + children.collect { formatText(it) }.join('\r\n')


        switch (node.name()) {
            case 'LI':
                prefix = ' - '
        }
        prefix + body + postFix
    }
}