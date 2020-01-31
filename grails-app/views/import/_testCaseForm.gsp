<input type="hidden" name="references" value="${key}"/>
<input type="hidden" name="generator" value="f_zaker@yahoo.com"/>
<input type="hidden" name="status" value="${5}"/>
<div class="field">
    <label for="title">Title :</label>
    <textarea id="title" name="title" type="text" style="height: 60px">${testCase.title}</textarea>
</div>

<div class="field">
    <label for="preconditions">Preconditions :</label>
    <textarea id="preconditions" name="preconditions" type="text"
              style="height: 180px">${testCase.preconditions}</textarea>
</div>

<div class="field">
    <label for="preconditions">Steps :</label>
    <table>
        <tr>
            <th>
                #
            </th>
            <th>
                User Interaction Sequence
            </th>
            <th>
                Expected Outcome
            </th>
        </tr>
        <g:each in="${testCase.steps}" var="step">
            <tr>
                <td>
                    ${step.index}
                </td>
                <td>
                    <textarea name="userInteractionSequence" type="text"
                              style="height: 180px">${step.userInteractionSequence}</textarea>
                </td>
                <td>
                    <textarea name="expectedOutcome" type="text"
                              style="height: 180px">${step.expectedOutcome}</textarea>
                </td>
            </tr>
        </g:each>
    </table>
</div>