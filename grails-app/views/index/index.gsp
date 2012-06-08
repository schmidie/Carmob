<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
  <auth:ifLoggedIn>

    <div style="float:left; width:55%;">
      <form class="well">
        <g:img dir="images" file="logo.png" width="120" height="120"/>
        
        <div style="float:right">
          <h2>Willkommen <auth:user/>!</h2>
        </div>
        
        <br />
        <center><u><b>Zeit bis zur nächsten Reise</u>:</b></center>
        <center><h3>${jetzt.getTime()}</h3></center>
        
      </form>
      
    </div>
            
    <div style="float:right; width:40%;">
      <form class="well">
        <b><i><u>Anstehende Routen</u>:</i></b>
        
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>Startzeit / Datum</th>
              <th>Startort</th>
              <th>Zielzeit / Datum</th>
              <th>Zielort</th>
            </tr>
          </thead>
          <tbody>
            <g:findAll in="${myTrips}" expr="it != null">
              <tr>
                <td>${it.name}</td>
                <td>${it.name}</td>
                <td>${it.name}</td>
                <td>${it.name}</td>
              </tr>
            </g:findAll>
          </tbody>
        </table>
      </form>
    </div>
       
  
  </div>
  </auth:ifLoggedIn>

  <auth:ifUnconfirmed>
    Um die Registrierung abzuschließen, bestätigen Sie Ihre E-Mail Adresse.
    <g:link action="reconfirm">Senden Sie mir bitte eine weitere Bestätigungsmail.</g:link>
  </auth:ifUnconfirmed>

</body>
</html>