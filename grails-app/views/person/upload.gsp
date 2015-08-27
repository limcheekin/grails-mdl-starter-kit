<%@ page import="org.grails.mdl.Person" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Upload Data</title>
    <style>
    .mdl-card {
        padding: 1em;
    }
    </style>
</head>


<body>
<div class="mdl-grid">
    <div class="mdl-card mdl-cell mdl-cell--12-col">
        <g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
        <g:uploadForm action="doUpload">
            <fieldset>
                <input type="file" name="file" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"/>
                <g:submitButton class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect" name="doUpload" value="Upload" />
            </fieldset>
        </g:uploadForm>
    </div>
</div>
</body>
</html>