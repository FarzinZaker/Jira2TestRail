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
            <g:if test="${flash.message}">
                <div class="success">${flash.message}</div>
            </g:if>
            <h1>Ticket</h1>

            <g:form controller="import" action="edit">
                <div class="panel">
                    <h2>Enter Jira Issue ID</h2>

                    <div class="field">
                        <label for="issueId">Issue ID:</label>
                        <input id="issueId" name="issueId" type="text" autocomplete="off" autofocus="autofocus">
                    </div>
                </div>

                <div class="toolbar">
                    <input type="submit" value="Extract Test Case" class="k-button k-primary"/>
                </div>
            </g:form>
        </div>
    </section>
</div>

</body>
</html>
