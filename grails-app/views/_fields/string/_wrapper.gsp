<%@ page import="grails.validation.ConstrainedProperty" %>

<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label ">
	<label for="${property}" class="mdl-textfield__label">${label}</label>
	<g:if test="${constraints.supportsContraint(ConstrainedProperty.MAX_SIZE_CONSTRAINT)}">
		<g:set var="maxSize" value="${constraints.maxSize}" />
	</g:if>
	<g:elseif test="${constraints.supportsContraint(ConstrainedProperty.SIZE_CONSTRAINT)}">
    	<g:set var="maxSize" value="${constraints.size.to}" />
	</g:elseif>	
	
	<g:if test="${maxSize <= 80}">
		<g:textField class="mdl-textfield__input" name="${property}" value="${value}" />
	</g:if>
	<g:else>
		<g:textArea class="mdl-textfield__input" name="${property}" value="${value}" rows="3" cols="40" />
	</g:else>
</div>