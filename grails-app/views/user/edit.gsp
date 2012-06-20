<%@ page import="conn.User" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
  <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
  <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
  <div class="row-fluid">

    <div class="span9">

      <div class="page-header">
        <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
      </div>

      <g:if test="${flash.message}">
        <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
      </g:if>

      <g:hasErrors bean="${userInstance}">
        <bootstrap:alert class="alert-error">
          <ul>
            <g:eachError bean="${userInstance}" var="error">
              <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
</g:eachError>
</ul>
</bootstrap:alert>
</g:hasErrors>

  <fieldset>
    
  </fieldset>

</div>

</div>
</body>
</html>
