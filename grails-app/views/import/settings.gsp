<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Settings</title>
</head>

<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <div class="col-12">
            <h1>Settings</h1>

            <g:form controller="import" action="saveSettings">
                <div class="panel">
                    <h2>Jira</h2>

                    <div class="field">
                        <label for="jiraServer">Server:</label>
                        <input id="jiraServer" name="jiraServer" type="text" value="${settings.jiraServer}">
                    </div>

                    <div class="field">
                        <label for="jiraUsername">Username:</label>
                        <input id="jiraUsername" name="jiraUsername" type="password" autocomplete="off"
                               value="${settings.jiraUsername}">
                    </div>

                    <div class="field">
                        <label for="jiraPassword">Password:</label>
                        <input id="jiraPassword" name="jiraPassword" type="password" autocomplete="off"
                               value="${settings.jiraPassword}">
                    </div>
                </div>

                <div class="panel">
                    <h2>TestRail</h2>

                    <div class="field">
                        <label for="testRailServer">Server:</label>
                        <input id="testRailServer" name="testRailServer" type="text" value="${settings.testRailServer}">
                    </div>

                    <div class="field">
                        <label for="testRailUsername">Username:</label>
                        <input id="testRailUsername" name="testRailUsername" type="password"
                               value="${settings.testRailUsername}" autocomplete="off">
                    </div>

                    <div class="field">
                        <label for="testRailPassword">Password:</label>
                        <input id="testRailPassword" name="testRailPassword" type="password"
                               value="${settings.testRailPassword}" autocomplete="off">
                    </div>
                </div>

                <div class="toolbar">
                    <input type="submit" value="Save Settings" class="k-button k-primary"/>
                </div>
            </g:form>
        </div>
    </section>
</div>

</body>
</html>
