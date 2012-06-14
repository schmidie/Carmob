<%@ page import="conn.Trip" %>



<div class="fieldcontain ${hasErrors(bean: tripInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="trip.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${tripInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tripInstance, field: 'connections', 'error')} ">
	<label for="connections">
		<g:message code="trip.connections.label" default="Connections" />
		
	</label>
	<g:select name="connections" from="${conn.Connection.list()}" multiple="multiple" optionKey="id" size="5" value="${tripInstance?.connections*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tripInstance, field: 'temp', 'error')} ">
	<label for="temp">
		<g:message code="trip.temp.label" default="Temp" />
		
	</label>
	<g:checkBox name="temp" value="${tripInstance?.temp}" />
</div>

