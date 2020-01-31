<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ticket</title>
</head>

<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <div class="col-12">
            <h1>Test Case</h1>

            <g:form controller="import" action="result">
                <div class="panel">
                    <h2>Test Section</h2>
                    <g:render template="testCaseSelector"/>
                </div>

                <div class="panel">
                    <h2>Modify Test Case Details</h2>
                    <g:render template="testCaseForm" model="${[testCase: testCase, testSection: testSection]}"/>
                </div>

                <div class="toolbar">
                    <input type="submit" value="Create Test Case" class="k-button k-primary"/>
                </div>
            </g:form>
        </div>
    </section>
</div>

</body>
</html>
