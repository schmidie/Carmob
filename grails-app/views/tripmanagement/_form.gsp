<%@ page import="conn.Tripmanagement" %>



<div class="fieldcontain ${hasErrors(bean: tripmanagementInstance, field: 'trips', 'error')} ">
	<label for="trips">
		<g:message code="tripmanagement.trips.label" default="Trips" />
		
	</label>
	<g:select name="trips" from="${conn.Trip.list()}" multiple="multiple" optionKey="id" size="5" value="${tripmanagementInstance?.trips*.id}" class="many-to-many"/>
</div>

