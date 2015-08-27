<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <style>
            fieldset {
                width: 550px;
            }

            .mdl-card {
                padding: 1em
            }            
        </style>
    </head>
    <body>
        <div class="mdl-grid">
          <div class="mdl-card mdl-cell mdl-cell--12-col">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.person}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.person}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <f:all bean="person" />
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
          </div>
        </div> 
    </body>
</html>
