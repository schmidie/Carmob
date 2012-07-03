<%@ page import="conn.Angle" %>

<!doctype html>

<html>
  <head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'angle.label', default: 'Angle')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="row-fluid">
      
      <div class="hero-unit">
        <!--<h2>Standort anlegen</h2>-->
        <a class="btn" href="../TripSearch/"><i class="icon-chevron-left"></i>abbrechen</a>
        <hr>
        <strong> <i class="icon-info-sign"></i> Lege einen neuen Standpunkt an. Dieser wird danach in der Schnellauswahl verfügbar sein!</strong>
      </div>
      
      <div class="span9">
        <g:if test="${flash.message}">
          <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:hasErrors bean="${angleInstance}">
          <bootstrap:alert class="alert-error">
            <ul>
              <g:eachError bean="${angleInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
              </g:eachError>
            </ul>
          </bootstrap:alert>
        </g:hasErrors>

      <fieldset>
        <g:form class="form-horizontal" action="create" >
          <fieldset>
            <f:all bean="angleInstance"/>
            <div class="form-actions">
              <button type="submit" class="btn btn-primary">
                <i class="icon-ok icon-white"></i>
                <g:message code="default.button.create.label" default="Create" />
              </button>
            </div>
          </fieldset>
        </g:form>
      </fieldset>

      </div>

    </div>
    
</body>
</html>