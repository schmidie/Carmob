<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
  </head>
  <body>
    
    <div class="row-fluid">

      <div class="tabbable tabs-below">
        <ul class="nav nav-tabs">
          <li class="active"><a href="#tab1" data-toggle="tab">Route erstellen</a></li>
          <li><a href="#tab2" data-toggle="tab">Meine Routen</a></li>
        </ul>
        <div class="tab-content">

          <div class="tab-pane active" id="tab1">

            <div class="hero-unit pull-left span5" >
              <h2> Von: </h2>
              <ul class="nav nav-pills">
                <li class="active">
                  <a href="#location1" data-toggle="tab">Berlin</a>
                </li>
                <li><a href="#location1" data-toggle="tab">Wolfsburg</a></li>
              </ul>

              <div class="tab-content"
                   <div class="tab-pane fade" id="location1">
                  <div class="hero-unit">         
                    <g:findAll in="${angles}" expr="it.city == 'Berlin'">
                      <p>${it.name}</p>
                    </g:findAll>
   
                  </div>
                </div>

              </div>

              <div class="hero-unit pull-right span5">
                <h2> Nach: </h2>
                <ul class="nav nav-pills">
                  <li>
                    <a href="#location2" data-toggle="tab">Berlin</a>
                  </li>
                  <li  class="active"><a href="#location2" data-toggle="tab">Wolfsburg</a></li>
                </ul>

                <div class="tab-content"
                     <div class="tab-pane fade" id="location2">
                    <div class="hero-unit">
                    </div>
                  </div>

                </div>


              </div>
            </div>
          </div>
        </div>
      </div>
  </body>
</html>
