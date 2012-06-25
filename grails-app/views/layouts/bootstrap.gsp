
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="initial-scale = 1.0">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

  <r:require modules="scaffolding"/>
  <r:require modules="bootstrap"/>
  <!-- Le fav and touch icons -->
  <!--<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">-->
  <!--<link rel="apple-touch-icon" href="{resource(dir: 'images', file: 'apple-touch-icon.png')}">-->
  <!--<link rel="apple-touch-icon" sizes="72x72" href="{resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">-->
  <!--<link rel="apple-touch-icon" sizes="114x114" href="{resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">-->


  <g:layoutHead/>
  <r:layoutResources/>
</head>

<body>
  <nav class="navbar navbar-fixed-top">

    <div class="navbar-inner">
      <div class="container-fluid">

        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>

        <a class="brand" href="${createLink(uri: '/')}">CARMOB</a>
        <auth:ifLoggedIn>
          <div class="nav-collapse">
            <ul class="nav">
              <!--<li><g:link controller="User" action="edit" id="${authenticationService?.sessionUser?.userObjectId}"> <i class="icon-edit icon-white"></i> Profil editieren</g:link></li>-->
              <li><a href="${createLinkTo(dir:'docs', file:'Bedienungsanleitung.pdf')}" target="_blank"> <i class="icon-question-sign icon-white"></i> Hilfe</a></li>
              <li> <a href="mailto:carmob.intermodal@gmail.com?subject=[Feedback]"> <i class="icon-envelope icon-white"></i> Feedback </a> </li>
            </ul>
          </div>
          <div class="nav pull-right">

            <li>
              <i><b><auth:user/></b></i>
            </li>

            <li>
              <auth:form authAction="logout" success="[controller:'index', action:'index']" error="[controller:'index', action:'index']" class="form-horizontal">
                <g:actionSubmit class="icon-off icon-white" value="Logout" class="btn pull-right"/>
              </auth:form>
            </li>
          </div>
        </auth:ifLoggedIn>
      </div>
    </div>
  </nav>

  <div class="container-fluid">
    
    <g:hasErrors bean="${flash.loginFormErrors}">
      <div class="alert alert-block">
        <a class="close" data-dismiss="alert" href="#">×</a>
        <h4 class="alert-heading">Warnung</h4>
        <g:renderErrors bean="${flash.loginFormErrors}" as="list"/>
      </div>
    </g:hasErrors>
    
    <g:hasErrors bean="${flash.signupFormErrors}">
      <div class="alert alert-block">
        <a class="close" data-dismiss="alert" href="#">×</a>
        <h4 class="alert-heading">Warnung</h4>
        <g:renderErrors bean="${flash.signupFormErrors}" as="list"/>
      </div>
    </g:hasErrors>
    
    <g:layoutBody/>

  </div>

<r:layoutResources/>

</body>
</html>