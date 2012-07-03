<!doctype html>

<html>

  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
    <div class="hero-unit span4">
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
            <label class="control-label" for="login"><b>Login:</b></label>
            <div class="controls">
              <g:textField name="login" value="${flash.loginForm?.login?.encodeAsHTML()}" class="span3"/>
            </div>
            <div class="control-group">
              <label class="control-label" for="password"><b>Passwort:</b></label>
              <div class="controls">
                <input name="password" value="" type="password" class="span3"/>
              </div>
            </div>
            <div  class="form-well">
              <g:actionSubmit value="log in" class="btn btn-success"/>
              <br />
              <br />
              Neu hier? Dann können Sie sich jetzt <g:link controller="Index" action="signup"> registrieren</g:link>.
            </div>
        </fieldset>
      </auth:form>
    </div> 
    
    <div class="span6">
      <table border="0">
        <tr>
          <td v-align="top"><g:img dir="images" file="logo.png" width="120" height="120"/></td>
          <td><h3>Intermodale Dynamische Routenplanung</h3>
          <hr>
            <p>Das System soll die Möglichkeit bieten intermodale Routen zu planen. 
            Dabei sollen dem Nutzer alle nötigen Informationen wie beispielsweise eine Wettervorhersage angezeigt werden, 
            damit eine individuell optimale Reise geplant werden kann. </p>
          </td>
        </tr>
        <tr>
          <td colspan="2">
          <p>Die geplanten Routen können mit der Reisebegleiter-Ansicht auf dem Smartphone betrachtet werden. 
          Wenn der Nutzer die Reise spontan ändern will, hat dieser die Möglichkeit schnell eine alternative Route zu finden und fortzusetzen.</p>
          </td>
        </tr>
      </table>
    </div>

    </auth:ifNotLoggedIn>
  </div>
 
  <div class="span12">
    <h3>Top-Greens</h3>
    <blockquote><small>Hier werden die Nutzer aufgelistet, welche am wenigsten CO2 Ausstoß pro Kilometer erreicht haben.</small></blockquote>
           
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Name</th>
          <th>CO2 Ausstoß / KM</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td>
          <td></td>
        </tr>
      </tbody>
    </table>
      
    <h3>Top-Sportler</h3>
    <blockquote><small>Hier werden die Nutzer aufgelistet, welche am meisten Kilometer mit dem Rad gefahren sind.</small></blockquote>
                  
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Name</th>
          <th> Durchschnittliche KM / Tag</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td>
          <td></td>
        </tr>
      </tbody>
    </table>
      
  </div>
    
  </body>
</html>