<div class="field">
    <label for="project">Project :</label>
    <input id="project" name="project" type="text" style="width: 100%"/>
</div>

<div class="field">
    <label for="suite">Test Suite :</label>
    <input id="suite" name="suite" type="text" style="width: 100%"/>
</div>

<div class="field">
    <label for="parentSection">Parent Section :</label>
    <input id="parentSection" name="parentSection" type="text" style="width: 100%"/>
</div>

<div class="field">
    <label for="title">Name :</label>
    <textarea id="title" name="sectionName" type="text" style="height: 60px">${testSection.name}</textarea>
</div>

<div class="field">
    <label for="preconditions">Description :</label>
    <textarea id="preconditions" name="sectionDescription" type="text"
              style="height: 120px">${testSection.description}</textarea>
</div>

<script>
    $(document).ready(function () {
        var project = $("#project").kendoDropDownList({
            optionLabel: "Select Project...",
            dataTextField: "name",
            dataValueField: "id",
            dataSource: {
                type: "json",
                serverFiltering: false,
                transport: {
                    read: "${createLink(controller: 'testRail', action: 'projects')}"
                }
            }
        }).data("kendoDropDownList");

        var suites = $("#suite").kendoDropDownList({
            autoBind: false,
            cascadeFrom: "project",
            optionLabel: "Select Suite...",
            dataTextField: "name",
            dataValueField: "id",
            dataSource: {
                type: "json",
                serverFiltering: true,
                transport: {
                    read: "${createLink(controller: 'testRail', action: 'suites')}"
                }
            }
        }).data("kendoDropDownList");

        var sections = $("#parentSection").kendoDropDownList({
            autoBind: false,
            cascadeFrom: "suite",
            optionLabel: "Select Parent Section...",
            dataTextField: "name",
            dataValueField: "id",
            dataSource: {
                type: "json",
                serverFiltering: true,
                transport: {
                    read: "${createLink(controller: 'testRail', action: 'sections')}"
                }
            }
        }).data("kendoDropDownList");
    });
</script>