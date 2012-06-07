<%@ page import="conn.TempTrip" %>



<div class="fieldcontain ${hasErrors(bean: tempTripInstance, field: 'trips', 'error')} ">
	<label for="trips">
		<g:message code="tempTrip.trips.label" default="Trips" />
		
	</label>
	<g:select name="trips" from="${conn.Trip.list()}" multiple="multiple" optionKey="id" size="5" value="${tempTripInstance?.trips*.id}" class="many-to-many"/>
</div>

