<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>

  <body>
  <auth:ifLoggedIn>

    <div class="hero-unit">
      <table>
        <tr>
          <td>
            <ul class="thumbnails">
              <li>
                <a class="thumbnail">
                  <img src="images/logo.png">
                </a>
              </li>
          </td>
          <td>
            <table>
              <tr>
                <td valign="top"> <h2>Hallo, <auth:user/>!</h2> </td>
              </tr>
              <tr>
                <td> Das Benutzerprofil ist in Bearbeitung </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>

    </div>


  </auth:ifLoggedIn>

  <auth:ifUnconfirmed>
    Um die Registrierung abzuschließen, bestätigen Sie Ihre E-Mail Adresse.
    <g:link action="reconfirm">Senden Sie mir bitte eine weitere Bestätigungsmail.</g:link>
  </auth:ifUnconfirmed>

</body>
</html>