<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
  <auth:ifLoggedIn>
   
     <div class="hero-unit">
        <h2>Hallo, <auth:user/>!</h2>
        <p>Du kannst zur Zeit folgende Aktionen durchführen:</p>

        <img src="images/logo.png" align="center">

        
        <h3>Route planen</h3>
        
     </div>
    
  </auth:ifLoggedIn>
  
    <auth:ifUnconfirmed>
      Um die Registrierung abzuschließen, bestätigen Sie Ihre E-Mail Adresse.
      <g:link action="reconfirm">Senden Sie mir bitte eine weitere Bestätigungsmail.</g:link>
    </auth:ifUnconfirmed>

</body>
</html>
