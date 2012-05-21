<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
  </head>

  <body>
  <auth:ifLoggedIn>
    Sie sind derzeit eingeloggt als: <auth:user/>
    <h2>Logout</h2>
    <auth:form authAction="logout" success="[controller:'index', action:'index']" error="[controller:'index', action:'index']" class="form-horizontal">
      <div class="form-actions">
      <g:actionSubmit value="Log out" class="btn btn-primary"/>
      </div>
    </auth:form>
  </auth:ifLoggedIn>

  <auth:ifUnconfirmed>
    Um die Registrierung abzuschließen, bestätigen Sie Ihre E-Mail Adresse.
    <g:link action="reconfirm">Senden Sie mir bitte eine weitere Bestätigungsmail.</g:link>
  </auth:ifUnconfirmed>

  <auth:ifNotLoggedIn>
    <g:if test="${flash.authenticationFailure}">
      Ihr Login/Registrierung ist fehlgeschlagen:
      <g:message code="authentication.failure.${flash.authenticationFailure.result}"/><br/>
    </g:if>

    <p>Sie sind nicht eingeloggt. Bitte loggen Sie sich ein:</p>
    <h2>Log in</h2>
    <auth:form authAction="login" success="[controller:'index', action:'index']" error="[ controller:'index', action:'index']" class="form-horizontal">
      <fieldset>
        <div class="control-group">
          <label class="control-label" for="login">Benutzername:</label>
          <div class="controls">
            <g:textField name="login" value="${flash.loginForm?.login?.encodeAsHTML()}" class="span3"/>
            <span class="help-block"">
                  <g:hasErrors bean="${flash.loginFormErrors}" field="login">
                <g:renderErrors bean="${flash.loginFormErrors}" as="list" field="login"/>
              </g:hasErrors>
            </span>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="password">Passwort:</label>
          <div class="controls">
            <input name="password" value="" type="password" class="span3"/>
            <span class="help-block"">
                  <g:hasErrors bean="${flash.loginFormErrors}" field="password">
                <g:renderErrors bean="${flash.loginFormErrors}" as="list" field="password"/>
              </g:hasErrors>
             
            </span>
          </div>
        </div>
        <div class="form-actions">
            <g:actionSubmit value="Log in" class="btn btn-primary"/>
         </div>
        
      </fieldset>
    </auth:form>

    <h2>Registrieren</h2>
    <auth:form authAction="signup" success="[controller:'index', action:'index']" error="[controller:'index', action:'index']" class="form-horizontal">
      <fieldset>
        <div class="control-group">
          <label class="control-label" for="login">Benutzername:</label>
          <div class="controls">
            <g:textField name="login" value="${flash.signupForm?.login?.encodeAsHTML()}" class="span3"/>
            <span class="help-block"">
                  <g:hasErrors bean="${flash.signupFormErrors}" field="login">
                <g:renderErrors bean="${flash.signupFormErrors}" as="list" field="login"/>
              </g:hasErrors>
            </span>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="email">Email:</label>
          <div class="controls">
            <g:textField name="email" value="${flash.signupForm?.email?.encodeAsHTML()}" class="span3"/>
            <span class="help-block">
              <g:hasErrors bean="${flash.signupFormErrors}" field="email">
                <g:renderErrors bean="${flash.signupFormErrors}" as="list" field="email"/>
              </g:hasErrors>
            </span>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="password">Passwort:</label>
          <div class="controls">
            <input name="password" value="" type="password" class="span3"/>
            <span class="help-block">
              <g:hasErrors bean="${flash.signupFormErrors}" field="password">
                <g:renderErrors bean="${flash.signupFormErrors}" as="list" field="password"/>
              </g:hasErrors>
            </span>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="passwordConfirm">Passwort wiederholen:</label>
          <div class="controls">
            <input name="passwordConfirm" value="" type="password" class="span3"/>
            <span class="help-block">
              <g:hasErrors bean="${flash.signupFormErrors}" field="passwordConfirm">
                <g:renderErrors bean="${flash.signupFormErrors}" as="list" field="passwordConfirm"/>
              </g:hasErrors>
            </span>
          </div>
        </div>
        <div class="form-actions">
        <g:actionSubmit value="Sign up" class="btn btn-primary"/>
        </div>
      </fieldset>
    </auth:form>
  </auth:ifNotLoggedIn>
</body>
</html>
