<%@ page import="conn.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'auto', 'error')} ">
	<label for="auto">
		<g:message code="user.auto.label" default="Auto" />
		
	</label>
	<g:checkBox name="auto" value="${userInstance?.auto}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'bahn_card_100', 'error')} ">
	<label for="bahn_card_100">
		<g:message code="user.bahn_card_100.label" default="Bahncard100" />
		
	</label>
	<g:checkBox name="bahn_card_100" value="${userInstance?.bahn_card_100}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'bahn_card_50', 'error')} ">
	<label for="bahn_card_50">
		<g:message code="user.bahn_card_50.label" default="Bahncard50" />
		
	</label>
	<g:checkBox name="bahn_card_50" value="${userInstance?.bahn_card_50}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fahrrad', 'error')} ">
	<label for="fahrrad">
		<g:message code="user.fahrrad.label" default="Fahrrad" />
		
	</label>
	<g:checkBox name="fahrrad" value="${userInstance?.fahrrad}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} ">
	<label for="login">
		<g:message code="user.login.label" default="Login" />
		
	</label>
	<g:textField name="login" value="${userInstance?.login}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="user.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="status" required="" value="${fieldValue(bean: userInstance, field: 'status')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'trips', 'error')} ">
	<label for="trips">
		<g:message code="user.trips.label" default="Trips" />
		
	</label>
	<g:select name="trips" from="${conn.Trip.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.trips*.id}" class="many-to-many"/>
</div>

