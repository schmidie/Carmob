<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
    <script type="text/javascript">
        function get_url_param( name )
          {
            name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");

            var regexS = "[\\?&]"+name+"=([^&#]*)";
            var regex = new RegExp( regexS );
            var results = regex.exec( window.location.href );

            if ( results == null )
                    return "";
            else
                    return results[1];
        }
    </script>
    
  </head>
  <body>

    <div name="row" class="row-fluid">

        <div class="span9">
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

                            <div id="button_from" class="btn-group" data-toggle="buttons-radio">
                              <g:findAll in="${angles}" expr="it != null && it.city == 'Berlin'">
                                <button name="von_btn" class="btn span2" value="${it.name}">${it.name}</button>
                              </g:findAll>
                            </div>
                           
                          </div>
                          <div class="tab-pane fade" id="location2">
                            
                            <div id="button_from" class="btn-group" data-toggle="buttons-radio">        
                              <g:findAll in="${angles}" expr="it != null && it.city == 'Wolfsburg'">
                                <button  name="von_btn" class="btn span2" value="${it.name}" >${it.name}</button>
                              </g:findAll>
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
                      
                      
                      <div id="button_to" class="btn-group" data-toggle="buttons-radio"> 
                        <g:findAll in="${angles}" expr="it != null && it.city == 'Wolfsburg'">
                          <button name="nach_btn" class="btn span2" value="${it.name}">${it.name}</button>
                        </g:findAll>
                      </div>
                      

                    </div>
                    
                    
                    <div class="tab-pane fade" id="location4">
                      
                      
                      <div id="button_to" class="btn-group" data-toggle="buttons-radio">         
                        <g:findAll in="${angles}" expr="it != null && it.city == 'Berlin'">
                          <button name="nach_btn" class="btn span2" value="${it.name}">${it.name}</button>
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
        
              <div class="span3" name="sidebar">
                
                <form name="search_frm_jetzt" id="search_frm_jetzt" class="well form-vertical" action="../Tripmanagement/index?">
            
             <input name="von_jetzt" type="hidden" class="span1">
             <input name="nach_jetzt" type="hidden" class="span1">
                  
            <button class="btn">Jetzt los</button> 
    
            <g:if test="${params.von_btn != null}">
              <script type="text/javascript">
                document.search_frm_jetzt.von_jetzt.value = get_url_param('von_btn');
              </script>
            </g:if>
            <g:if test="${params.nach_btn != null}">
               <script type="text/javascript">
                document.search_frm_jetzt.nach_jetzt.value = get_url_param('nach_btn');
              </script>
            </g:if>

            
           </form>
                
           
                
          <form name="search_frm" class="well form-vertical" action="../Tripmanagement/index?">


            <strong>Zeit:</strong>             
            </br>

            <input name="time" type="text" class="span1" value=""> Uhr
            </br>

            <strong>Datum:</strong>
            </br>

            <g:datePicker name="myDate" value="${new Date()}"
            precision="day"  years="[2012]"/>
            </br>
            
            <input name="von" type="hidden" value="">
            <input name="nach" type="hidden" value="">

            <button class="btn" type="submit">weiter</button>

          </form>
       </div>

      </div>

  </body>
</html>
