<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
    <auth:ifNotLoggedIn>
      <h2>Registrieren</h2>
      <auth:form authAction="signup" 
                 success="[controller:'index', action:'signup']" 
                 error="[controller:'index', action:'signup']" class="form-horizontal">
        <fieldset> 
          <div class="control-group">
            <label class="control-label" for="login">Benutzername:</label>
            <div class="controls">
              <g:textField name="login" value="${flash.signupForm?.login?.encodeAsHTML()}" class="span3"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="email">Email:</label>
            <div class="controls">
              <g:textField name="email" value="${flash.signupForm?.email?.encodeAsHTML()}" class="span3"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label"  for="password">Passwort:</label>
            <div class="controls">
              <input name="password" value="" type="password" class="span3"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label"  for="passwordConfirm">Passwort wiederholen:</label>
            <div class="controls">
              <input name="passwordConfirm" value="" type="password" class="span3"/>
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