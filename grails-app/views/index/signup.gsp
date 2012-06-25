<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
    <auth:ifNotLoggedIn>


      <auth:form authAction="signup" 
                 success="[controller:'index', action:'signup']" 
                 error="[controller:'index', action:'signup']" class="form-well">
        <fieldset>
          <h3>Registrierung</h3>
          <br />
          <div class="control-group">
            <label class="control-label" for="login"><b>Login:</b></label>
            <div class="controls">
              <g:textField name="login" value="${flash.signupForm?.login?.encodeAsHTML()}" class="span4"/>                          </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="email"><b>Email:</b></label>
            <div class="controls">
              <g:textField name="email" value="${flash.signupForm?.email?.encodeAsHTML()}" class="span4"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label"  for="password"><b>Passwort:</b></label>
            <div class="controls">
              <input name="password" value="" type="password" class="span4"/>
              <span class="help-block">(mindestens 6 Zeichen)</span>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label"  for="passwordConfirm"><b>Passwort wiederholen:</b></label>
            <div class="controls">
              <input name="passwordConfirm" value="" type="password" class="span4"/>
            </div>
          </div>
          <div class="form-well">
          <g:actionSubmit value="Sign up" class="btn btn-primary"/>
          </div>
        </fieldset>
      </auth:form>
    </auth:ifNotLoggedIn>
  </body>
</html>