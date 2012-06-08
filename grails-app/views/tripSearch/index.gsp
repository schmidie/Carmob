<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
        
    <resource:dateChooser />
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
    
  </head>
  <body>
    
    <form name="search_frm" id="search_frm" action="../Tripmanagement/index?">

    <div name="row" class="row-fluid">

        <div class="span8">
          <!--Body content-->

          <ul id ="RouteTab" class="nav nav-tabs">
            <li class="active"><a href="#tab1" data-toggle="tab">Route erstellen</a></li>
            <li><a href="#tab2" data-toggle="tab">Meine Routen</a></li>
          </ul>
          <div id="RouteTabContent" class="tab-content" align="center">
            <div class="tab-pane fade active in" id="tab1">

              <div class="row-fluid span8">

                <div class="span4" >

                  <table class="table-bordered"> 
                    
                    <tr><td><strong>Von:</strong></td></tr>
                    
                    <tr><td>
                        <ul id="FromTab" class="nav nav-pills">
                          <li class="active">
                            <a href="#location1" data-toggle="tab">Berlin</a>
                          </li>
                          <li><a href="#location2" data-toggle="tab">Wolfsburg</a></li>
                        </ul>

                        <div id="startTabContent" class="tab-content span2">

                          <div class="tab-pane fade active in" id="location1">                   

                            <div class="control-group">
                              <div class="controls">
                                <g:findAll in="${angles}" expr="it != null && it.city == 'Berlin'">
                                <label class="radio">
                                  <input type="radio" name="start" id="start" value="${it.address}">
                                  ${it.name}
                                </label> 
                                </g:findAll>
                              </div>
                            </div>

                           
                          </div>
                          
                          <div class="tab-pane fade" id="location2">
        
                            <div class="control-group">
                              <div class="controls">
                                <g:findAll in="${angles}" expr="it != null && it.city == 'Wolfsburg'">
                                <label class="radio">
                                  <input type="radio" name="start" id="start" value="${it.address}">
                                  ${it.name}
                                </label> 
                                </g:findAll>
                              </div>
                            </div>

                          </div>

                        </div>
                      </td> </tr>
                    <tr><td></td></tr>
                  </table> 
                </div>



                <div class="span4" >
                  
                  
                  <table class="table-bordered"> 
                    <tr><td><strong>Nach:</strong></td></tr>
                    <tr><td>
                  <ul id="ToTab" class="nav nav-pills">
                    <li class="active">
                      <a href="#location3" data-toggle="tab">Wolfsburg</a>
                    </li>
                    <li><a href="#location4" data-toggle="tab">Berlin</a></li>
                  </ul>

                  <div id="endTabContent" class="tab-content span2">
                    <div class="tab-pane fade active in" id="location3">

                      <div class="control-group">
                        <div class="controls">
                          <g:findAll in="${angles}" expr="it != null && it.city == 'Wolfsburg'">
                            <label class="radio">
                              <input type="radio" name="end" id="end" value="${it.address}">
                                ${it.name}
                            </label> 
                          </g:findAll>
                        </div>
                      </div>

                    </div>

                    
                    <div class="tab-pane fade" id="location4">
                      
                      
                        <div class="control-group">
                              <div class="controls">
                                <g:findAll in="${angles}" expr="it != null && it.city == 'Berlin'">
                                <label class="radio">
                                  <input type="radio" name="end" id="end" value="${it.address}">
                                  ${it.name}
                                </label> 
                                </g:findAll>
                              </div>
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
                <form class="pull-left" action="../Angle/index">
                  </br>
                  <button class="btn btn-info" type="submit">Neuen Standort anlegen</button>

                </form>
              </div>


            </div> <!--TabPane active-->


            <div class="tab-pane fade" id="tab2">
              Archiv
            </div>

          </div> <!-- TabContent -->

        </div> <!--Row Fluit-->
        
            <div class="hero-unit span2" name="sidebar">

              <button class="btn">Jetzt los</button> 

              <hr>
              
              <strong>Zeit:</strong>             
              </br>
              <span class="add-on">
                <i class="icon-time"></i>
              </span>
              <input name="time" id="time" type="text" class="span1" value=""> Uhr
              </br>

              <strong>Datum:</strong>
              </br>
              <span class="add-on">
                <i class="icon-calendar"></i>
              </span>
              <input name="date" id="date" type="text" class="span1" value="">
               
             <!-- <richui:dateChooser name="date2" id="date2" class="span1" format="dd.MM.yyyy"/> -->
             
             <hr>
             
              <button class="btn" type="submit">weiter</button>
            </div>
    </div>      
    </form>
  </body>
</html>
