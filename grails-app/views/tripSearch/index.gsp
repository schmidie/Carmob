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

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">


          <form class="well form-vertical" action="../Tripmanagement/">


            <div class="btn-group" data-toggle="buttons-radio">
              <button class="btn">Jetzt los</button>
              <input type="text" class="btn span1" placeholder="10:00">
            </div>

            </br>

            <strong>Datum:</strong>

            </br></br></br></br></br></br>

            <button class="btn" type="submit">weiter</button>

          </form>

          <!--Sidebar content-->
        </div>
        <div class="span10">
          <!--Body content-->

          <ul id ="RouteTab" class="nav nav-tabs">
            <li class="active"><a href="#tab1" data-toggle="tab">Route erstellen</a></li>
            <li><a href="#tab2" data-toggle="tab">Meine Routen</a></li>
          </ul>
          <div id="RouteTabContent" class="tab-content" align="center">
            <div class="tab-pane fade active in" id="tab1">

              <div class="row-fluid">

                <div class="span3" >

                  <table class="table-bordered"> 
                    
                    <tr><td><strong>Nach:</strong></td></tr>
                    
                    <tr><td>
                        <ul id="FromTab" class="nav nav-pills">
                          <li class="active">
                            <a href="#location1" data-toggle="tab">Berlin</a>
                          </li>
                          <li><a href="#location2" data-toggle="tab">Wolfsburg</a></li>
                        </ul>

                        <div id="startTabContent" class="tab-content span3">
                          <div class="tab-pane fade active in" id="location1">                   

                            <div class="btn-group" data-toggle="buttons-radio">
                              <g:findAll in="${angles}" expr="it.city == 'Berlin'">
                                <button class="btn span3">${it.name}</button>
                              </g:findAll>
                            </div>

                          </div>
                          <div class="tab-pane fade" id="location2">

                            <div class="btn-group" data-toggle="buttons-radio">        
                              <g:findAll in="${angles}" expr="it.city == 'Wolfsburg'">
                                <button class="btn span3">${it.name}</button>
                              </g:findAll>
                            </div>

                          </div>

                        </div>
                      </td> </tr>
                    <tr><td></td></tr>
                  </table> 
                </div>



                <div class="span3" >
                  
                  
                  <table class="table-bordered"> 
                    <tr><td><strong>Nach:</strong></td></tr>
                    <tr><td>
                  <ul id="ToTab" class="nav nav-pills">
                    <li class="active">
                      <a href="#location3" data-toggle="tab">Wolfsburg</a>
                    </li>
                    <li><a href="#location4" data-toggle="tab">Berlin</a></li>
                  </ul>

                  <div id="endTabContent" class="tab-content span3">
                    <div class="tab-pane fade active in" id="location3">
                      <div class="btn-group" data-toggle="buttons-radio"> 
                        <g:findAll in="${angles}" expr="it.city == 'Wolfsburg'">
                          <button class="btn span3">${it.name}</button>
                        </g:findAll>
                      </div>

                    </div>
                    <div class="tab-pane fade" id="location4">
                      <div class="btn-group" data-toggle="buttons-radio">         
                        <g:findAll in="${angles}" expr="it.city == 'Berlin'">
                          <button class="btn span3">${it.name}</button>
                        </g:findAll>
                      </div>

                    </div>

                  </div>
                  
                    </td> </tr>
                    <tr><td></td></tr>
                  </table> 
                  
                </div>
              </div>
              </br></br></br>

              <div class="row-fluid">
                <form action="../Angle/index">

                  <button class="btn span3" type="submit">Neuen Standort anlegen</button>

                </form>
              </div>


            </div> <!--TabPane active-->


            <div class="tab-pane fade" id="tab2">
              Archiv
            </div>

          </div> <!-- TabContent -->

        </div> <!--Row Fluit-->

      </div>

  </body>
</html>
