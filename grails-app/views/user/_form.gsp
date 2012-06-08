<%@ page import="conn.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'surName', 'error')} ">
	<label for="surName">
		<g:message code="user.surName.label" default="Sur Name" />
		
	</label>
	<g:textField name="surName" value="${userInstance?.surName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'hasFahrrad', 'error')} ">
	<label for="hasFahrrad">
		<g:message code="user.hasFahrrad.label" default="Has Fahrrad" />
		
	</label>
	<g:checkBox name="hasFahrrad" value="${userInstance?.hasFahrrad}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'hasAuto', 'error')} ">
	<label for="hasAuto">
		<g:message code="user.hasAuto.label" default="Has Auto" />
		
	</label>
	<g:checkBox name="hasAuto" value="${userInstance?.hasAuto}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'hasBCard50', 'error')} ">
	<label for="hasBCard50">
		<g:message code="user.hasBCard50.label" default="Has BC ard50" />
		
	</label>
	<g:checkBox name="hasBCard50" value="${userInstance?.hasBCard50}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'hasBCard100', 'error')} ">
	<label for="hasBCard100">
		<g:message code="user.hasBCard100.label" default="Has BC ard100" />
		
	</label>
	<g:checkBox name="hasBCard100" value="${userInstance?.hasBCard100}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'useFahrrad', 'error')} ">
	<label for="useFahrrad">
		<g:message code="user.useFahrrad.label" default="Use Fahrrad" />
		
	</label>
	<g:checkBox name="useFahrrad" value="${userInstance?.useFahrrad}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'useTaxi', 'error')} ">
	<label for="useTaxi">
		<g:message code="user.useTaxi.label" default="Use Taxi" />
		
	</label>
	<g:checkBox name="useTaxi" value="${userInstance?.useTaxi}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'useDBahn', 'error')} ">
	<label for="useDBahn">
		<g:message code="user.useDBahn.label" default="Use DB ahn" />
		
	</label>
	<g:checkBox name="useDBahn" value="${userInstance?.useDBahn}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'useSBahn', 'error')} ">
	<label for="useSBahn">
		<g:message code="user.useSBahn.label" default="Use SB ahn" />
		
	</label>
	<g:checkBox name="useSBahn" value="${userInstance?.useSBahn}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'useUBahn', 'error')} ">
	<label for="useUBahn">
		<g:message code="user.useUBahn.label" default="Use UB ahn" />
		
	</label>
	<g:checkBox name="useUBahn" value="${userInstance?.useUBahn}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'useBus', 'error')} ">
	<label for="useBus">
		<g:message code="user.useBus.label" default="Use Bus" />
		
	</label>
	<g:checkBox name="useBus" value="${userInstance?.useBus}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${userInstance?.email}"/>
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

