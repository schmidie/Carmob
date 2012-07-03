<%@ page import="conn.Angle" %>

<div class="fieldcontain ${hasErrors(bean: angleInstance, field: 'name', 'error')} required">
  <label for="name">
    <g:message code="angle.name.label" default="Name" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="name" required="" value="${angleInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: angleInstance, field: 'address', 'error')} required">
  <label for="address">
    <g:message code="angle.address.label" default="Address" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="address" required="" value="${angleInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: angleInstance, field: 'post_code', 'error')} required">
  <label for="post_code">
    <g:message code="angle.post_code.label" default="Postcode" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="post_code" required="" value="${angleInstance?.post_code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: angleInstance, field: 'city', 'error')} required">
    <label for="city">
      <g:message code="angle.city.label" default="City" />
      <span class="required-indicator">*</span>
    </label>
    <g:textField name="city" required="" value="${angleInstance?.city}"/>
</div>