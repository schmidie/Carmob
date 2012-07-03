<%@ page import="conn.Angle" %>

<!doctype html>

<html>
  
  <head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'angle.label', default: 'Angle')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  
  <body>
    
    <div class="row-fluid">
		
      <div class="span3">
	<div class="well">
          <ul class="nav nav-list">
            <li class="nav-header">${entityName}</li>
            <li>
              <g:link class="list" action="list">
                <i class="icon-list"></i>
                <g:message code="default.list.label" args="[entityName]" />
              </g:link>
            </li>
            <li>
              <g:link class="create" action="create">
		<i class="icon-plus"></i>
		<g:message code="default.create.label" args="[entityName]" />
              </g:link>
            </li>
          </ul>
	</div>
      </div>
			
      <div class="span9">
	<div class="page-header">
          <h1><g:message code="default.show.label" args="[entityName]" /></h1>
	</div>

	<g:if test="${flash.message}">
          <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
	</g:if>

        <dl>
          <g:if test="${angleInstance?.name}">
            <dt><g:message code="angle.name.label" default="Name" /></dt>
            <dd><g:fieldValue bean="${angleInstance}" field="name"/></dd>
          </g:if>
				
          <g:if test="${angleInstance?.address}">
            <dt><g:message code="angle.address.label" default="Address" /></dt>
            <dd><g:fieldValue bean="${angleInstance}" field="address"/></dd>
          </g:if>
	
          <g:if test="${angleInstance?.post_code}">
            <dt><g:message code="angle.post_code.label" default="Postcode" /></dt>
            <dd><g:fieldValue bean="${angleInstance}" field="post_code"/></dd>
          </g:if>
	
          <g:if test="${angleInstance?.city}">
            <dt><g:message code="angle.city.label" default="City" /></dt>
            <dd><g:fieldValue bean="${angleInstance}" field="city"/></dd>
          </g:if>
	</dl>

	<g:form>
          <g:hiddenField name="id" value="${angleInstance?.id}" />
          <div class="form-actions">
            <g:link class="btn" action="edit" id="${angleInstance?.id}">
              <i class="icon-pencil"></i>
              <g:message code="default.button.edit.label" default="Edit" />
            </g:link>
            <button class="btn btn-danger" type="submit" name="_action_delete">
              <i class="icon-trash icon-white"></i>
              <g:message code="default.button.delete.label" default="Delete" />
            </button>
          </div>
	</g:form>

      </div>
    </div>
    
  </body>
</html>
