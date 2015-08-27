<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label ">
	<label for="${property}" class="mdl-textfield__label">${label}</label>
	<g:textField class="mdl-textfield__input" name="${property}" pattern="-?[0-9]*(\\.[0-9]+)?" value="${value}" />
	<span class="mdl-textfield__error">Input is not a number!</span>
</div>