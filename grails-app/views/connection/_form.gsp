<%@ page import="conn.Connection" %>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'transMean', 'error')} required">
  <label for="transMean">
    <g:message code="connection.transMean.label" default="Trans Mean" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="transMean" name="transMean.id" from="${conn.TransportationMean.list()}" optionKey="id" required="" value="${connectionInstance?.transMean?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'start', 'error')} required">
  <label for="start">
    <g:message code="connection.start.label" default="Start" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="start" required="" value="${connectionInstance?.start}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'end', 'error')} required">
  <label for="end">
    <g:message code="connection.end.label" default="End" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="end" required="" value="${connectionInstance?.end}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'start_time', 'error')} required">
  <label for="start_time">
    <g:message code="connection.start_time.label" default="Starttime" />
    <span class="required-indicator">*</span>
  </label>
  <g:datePicker name="start_time" precision="day"  value="${connectionInstance?.start_time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'end_time', 'error')} required">
  <label for="end_time">
    <g:message code="connection.end_time.label" default="Endtime" />
    <span class="required-indicator">*</span>
  </label>
  <g:datePicker name="end_time" precision="day"  value="${connectionInstance?.end_time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'distance', 'error')} ">
  <label for="distance">
    <g:message code="connection.distance.label" default="Distance" />
  </label>
  <g:field type="number" name="distance" value="${fieldValue(bean: connectionInstance, field: 'distance')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: connectionInstance, field: 'trips', 'error')} ">
  <label for="trips">
    <g:message code="connection.trips.label" default="Trips" />
  </label>
</div>