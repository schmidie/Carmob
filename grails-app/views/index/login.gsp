<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
    <auth:ifNotLoggedIn>
      <g:if test="${flash.authenticationFailure}">
        Ihre Anmeldung ist fehlgeschlagen:
        <g:message code="authentication.failure.${flash.authenticationFailure.result}"/><br/>
      </g:if>
      
            
      <auth:form authAction="login" 
                 success="[controller:'index', action:'login']" 
                 error="[ controller:'index', action:'login']" class="form-well">
        <fieldset>
          <h3>Anmeldung</h3>
          <br />
          <div class="control-group">
            <label class="control-label" for="login"><b>Fixkennung:</b></label>
            <div class="controls">
              <g:textField name="login" value="${flash.loginForm?.login?.encodeAsHTML()}" class="span4"/>
          </div>
          <div class="control-group">
            <label class="control-label" for="password"><b>Passwort:</b></label>
            <div class="controls">
              <input name="password" value="" type="password" class="span4"/>
            </div>
          </div>
          <div class="form-well">
            <g:actionSubmit value="log in" class="btn btn-success"/>
            <br />
            <br />
            Neu hier? Dann können Sie sich jetzt <g:link controller="Index" action="signup"> registrieren</g:link>.
          </div>
        </fieldset>
      </auth:form>
      </div>     
      
    </auth:ifNotLoggedIn>
  </body>
</html>