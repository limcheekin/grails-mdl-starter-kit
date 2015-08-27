<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="mdl-grid">
          <div class="mdl-card mdl-cell mdl-cell--12-col">
            <g:if test="${flash.errorMessages}">
                <ul class="errors">
                <g:each in="${flash.errorMessages.entrySet()}" var="fieldError">
                    <g:each in="${fieldError.value}" var="errorMessage">
                    <li>Row ${fieldError.key}: ${errorMessage?.encodeAsHTML()}</li>
                    </g:each>
                </g:each>
                </ul>
            </g:if>

            <div id="toolbar">
                <g:link class="mdl-button mdl-js-button mdl-button--primary mdl-js-ripple-effect" action="upload">Upload</g:link>
            </div>
            <table data-classes="mdl-data-table mdl-js-data-table mdl-shadow--2dp" data-toggle="table" data-url="/persons.json" data-id-field="id" data-toolbar="#toolbar" data-toolbar-align="right">
                <thead>
                    <tr>
                        <th data-field="id" class="mdl-data-table__cell--numeric">ID.</th>
                        <th data-field="lastName" class="mdl-data-table__cell--non-numeric">Last Name</th>
                        <th data-field="firstName" class="mdl-data-table__cell--non-numeric">First Name</th>
                        <th data-field="age" class="mdl-data-table__cell--numeric">Age</th>
                        <th data-field="description" class="mdl-data-table__cell--non-numeric">Description</th>                        
                    </tr>
                </thead>
            </table>
            <g:link class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect" action="create"><i class="material-icons">add</i></g:link>
          </div>
        </div> 
    </body>
</html>