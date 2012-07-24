<!doctype html>

<html>
  
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>TWOT | The way of traveling</title>
  </head>

  <body>
    <auth:ifLoggedIn>
        <!--<div class="span3"><g:img dir="images" file="logo.png" width="120" height="120"/></div >-->
        <div class="row-fluid">
          <div class="span2">
            <a class="btn btn-success btn-large" href="TripSearch/index"><i class="icon-edit"></i> Route planen </a>
            <!--<g:link controller="TripSearch" action="index"><a class="btn btn-success btn-large" ><i class="icon-edit"></i> Route planen</a></g:link>-->
          </div>
          <div class="span8">&nbsp</div>
          <div class="span2">
            <strong><g:img dir="images" file="leaf.png" width="28" height="28"/> Mein CO2 Verbrauch: ${m_user?.getCo2()} g </strong>
          </div>

        </div>

        <hr>
    
        <div>
          <form>
            <div  class="scrollable_vertical">
            <h3>Anstehende Trips</h3>
            <table>
              <tr>
                <td bgcolor="#00FF00"><strong>gestartete Trips</strong></td>
                <td>&nbsp</td>
                <td bgcolor="#FFFF00"><strong>in kürze startende Trips</strong></td>
              </tr>

            <hr>
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
                  <g:if test="${it.getEndTime()> new Date()}">

                    <g:if test="${it.getStartTime() < new Date() && it.getEndTime()> new Date()}">
                      <tr bgcolor="#00FF00">
                    </g:if>
                    <g:elseif test="${it.nowToTrip() > 0 && it.nowToTrip() < 60}">
                      <tr bgcolor="#FFFF00">
                    </g:elseif>
                    <g:else>
                      <tr>
                    </g:else>
                        <td><center><a href="../onTheWay/index?trip_id=${it.id}"><g:img dir="images" file="gobutton.png" width="32" height="32"/></center></a></td>
                        <td vertical-align>${it.getStartTime().format('dd.MM.yyyy')}</td>
                        <td>${it.getStartTime().format('HH:mm')}</td>
                        <td>${it.getEndTime().format('HH:mm')}</td>
                        <td>${it.getStart()}</td>
                        <td>${it.getEnd()}</td>
                      </tr>
                  </g:if>
                </g:findAll>
              </tbody>
            </table>
            </div>
            
            <h3>Vergangene Trips</h3>
            <div class="scrollable_vertical">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Datum</th>
                  <th>Startzeit</th>
                  <th>Zielzeit</th>
                  <th>Startort</th>
                  <th>Zielort</th>
                </tr>
              </thead>
              <tbody>
                <g:findAll in="${myTrips}" expr="it != null">
                  <g:if test="${it.getEndTime()< new Date()}">
                      <tr>
                       <td vertical-align>${it.getStartTime().format('dd.MM.yyyy')}</td>
                        <td>${it.getStartTime().format('HH:mm')}</td>
                        <td>${it.getEndTime().format('HH:mm')}</td>
                        <td>${it.getStart()}</td>
                        <td>${it.getEnd()}</td>
                      </tr>
                  </g:if>
                </g:findAll>
              </tbody>
            </table>
            </div>
            
          </form>
        </div>
    
    </auth:ifLoggedIn>

    <auth:ifUnconfirmed>
      Um die Registrierung abzuschließen, bestätigen Sie Ihre E-Mail Adresse.
      <g:link action="reconfirm">Senden Sie mir bitte eine weitere Bestätigungsmail.</g:link>
    </auth:ifUnconfirmed>

  </body>
</html>