<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
  <auth:ifLoggedIn>
    
        
    
    <div class="span4">

        <g:img dir="images" file="logo.png" width="120" height="120"/>
        
        <div style="float:right">
          <h2>Willkommen <auth:user/>!</h2>
        </div>

       <!-- <center><u><b>Zeit bis zur nächsten Reise</u>:</b></center>
        <center><h3>${timeToNextTrip} Minuten</h3></center> -->

      
    </div>
    
    <div class="span8">
      <form>
        <h3>Anstehende Routen</h3>
        
        <table class="table table-bordered">
          <thead>
            <tr>
              <th></th>
              <th>Datum</th>
              <th>Startzeit</th>
              <th>Zielzeit</th>
              <th>Startort</th>
              <th>Zielort</th>
            </tr>
          </thead>
          <tbody>
            <g:findAll in="${myTrips}" expr="it != null">
              
              <g:if test="${it.getEndTime() < new Date()}">
                <tr bgcolor="#838B8B">
              </g:if>
              <g:elseif test="${it.getStartTime() < new Date()}">
                <tr bgcolor="#FF0000">
              </g:elseif>
              <g:elseif test="${it.nowToTrip() > 0 && it.nowToTrip() < 60}">
                <tr bgcolor="#00FF00">
              </g:elseif>
              <g:else>
                <tr>
              </g:else>
                <td><a href="../onTheWay/index?trip_id=${it.id}"><i class="icon-play"></i>go</a></td>
                <td>${it.getStartTime().format('dd.MM.yyyy')}</td>
                <td>${it.getStartTime().format('HH:mm')}</td>
                <td>${it.getEndTime().format('HH:mm')}</td>
                <td>${it.getStart()}</td>
                <td>${it.getEnd()}</td>
              </tr>
            </g:findAll>
          </tbody>
        </table>
      </form>
    </div>

       
  </auth:ifLoggedIn>

  <auth:ifUnconfirmed>
    Um die Registrierung abzuschließen, bestätigen Sie Ihre E-Mail Adresse.
    <g:link action="reconfirm">Senden Sie mir bitte eine weitere Bestätigungsmail.</g:link>
  </auth:ifUnconfirmed>

</body>
</html>