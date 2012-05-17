<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
  </head>

  <body>
    <div class="row-fluid">
      <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
          <h3>Infos</h3>

          <h5>Installed Plugins</h5>
          <ul>
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
              <li>${plugin.name} - ${plugin.version}</li>
            </g:each>
          </ul>
        </div>
      </aside>

      <section id="main" class="span9">

        <div class="hero-unit">
          <h1>Welcome to CARMOB</h1>

          <p>comming soon</p>

          <p></p>
        </div>

        <div class="row-fluid">
          <div class="span4">
            <h2>Controller</h2>
            <ul class="nav nav-list">
              <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                <li><g:link controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
              </g:each>
            </ul>
          </div>
        </div>

      </section>
    </div>
  </body>
</html>
