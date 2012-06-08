<!doctype html>

<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>Carmeq GmbH | CARMOB</title>
  </head>
  <body>
    
    <div class="hero-unit">
      <h4>Persönliche Daten:</h4>
    
      <br />
    
      <fieldset>
      <div class="control-group">
        <label class="control-label" for="login"><b>Fixkennung:</b></label>
        <div class="controls">
          <input name="login" value="" class="span4"/>
          <span class="help-block">Finden Sie auf der Rückseite Ihrer KeyCard!</span>
        </div>
      </div>
      
      <div class="control-group">
        <label class="control-label" for="password"><b>Passwort:</b></label>
        <div class="controls">
          <input name="password" value="" type="password" class="span4"/>
        </div>
      </div>
      
      <div class="control-group">
        <label class="control-label" for="surName"><b>Vorname:</b></label>
        <div class="controls">
          <input name="sName" value="" class="span4"/>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lastName"><b>Nachname:</b></label>
        <div class="controls">
          <input name="lName" value="" class="span4"/>
        </div>
      </div>
        
      
        <b><u>Sie besitzen</u>:</b><br />
        <label class="checkbox" for="hasBCard50"><input type="checkbox"> Bahncard 50</label><br />
        <label class="checkbox" for="hasBCard100"><input type="checkbox"> Bahncard 100</label><br />
        <label class="checkbox" for="hasFahrrad"><input type="checkbox"> Fahrrad</label><br />
        <label class="checkbox" for="hasAuto"><input type="checkbox"> Auto</label><br />
      
        <br />
        
        <button class="btn btn-success" type="submit">Einstellungen speichern</button>
        
      </fieldset>
        
           
    </div>
    
    <div class="hero-unit">
      <h4>Reiseverkehrsmittel:</h4>
      
      <fieldset>
        <br />
        <label class="checkbox" for="useFahrrad"><input type="checkbox"> Fahrrad</label><br />
        <label class="checkbox" for="useTaxi"><input type="checkbox"> Taxi</label><br />
        <label class="checkbox" for="useDBahn"><input type="checkbox"> Deutsche Bahn</label><br />
        <label class="checkbox" for="useSBahn"><input type="checkbox"> S-Bahn</label><br />
        <label class="checkbox" for="useUBahn"><input type="checkbox"> U-Bahn</label><br />
        <label class="checkbox" for="useBus"><input type="checkbox"> Bus</label><br />
      
        <br />
        
        <button class="btn btn-success" type="submit">Einstellungen speichern</button>
        
      </fieldset>
      
    </div>
    
  </body>
</html>
