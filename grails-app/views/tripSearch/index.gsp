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

      <div class="span6">
        <!--Body content-->

        <ul id ="RouteTab" class="nav nav-tabs">
          <li class="active"><a href="#tab1" data-toggle="tab">Route erstellen</a></li>
          <li><a href="#tab2" data-toggle="tab">Meine Routen</a></li>
        </ul>

        <div id="RouteTabContent" class="tab-content" align="center">
          <div class="tab-pane fade active in" id="tab1">

            <div class="span6">
              <div class="row-fluid">

                <div class="span6" >

                  <table class="table-bordered"> 
                    <tr>
                      <td>
                        <strong>Von:</strong>       
                      </td>
                      <td>
                        <a class="btn btn-mini" href="../Angle/create"><i class=" icon-plus"></i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>      
                    <g:if test="${cities?.size() > 0}" >
                      <ul id="FromTab" class="nav nav-pills">

                        <li class="active">
                          <a href="#location${cities[0].toString().replaceAll(' ', '')}" data-toggle="tab">${cities[0].toString()}</a>
                        </li>

                        <g:each in="${cities[1..(cities.size()-1)]}">
                          <li>
                            <a href="#location${it.toString().replaceAll(' ', '')}" data-toggle="tab">${it.toString()}</a>
                          </li>
                        </g:each>
                      </ul>   
                      
                      <hr>

                      <div id="startTabContent" class="tab-content span2">


                        <g:each var="city" in="${cities}">
                          <g:if test ="${city == cities.get(0)}">
                            <div class="tab-pane fade in active" id="location${city.toString().replaceAll(' ', '')}">
                          </g:if>
                          <g:else>
                            <div class="tab-pane fade in" id="location${city.toString().replaceAll(' ', '')}">
                          </g:else>

                          <div class="control-group">
                            <div class="controls">

                              <g:each var="m_angle" in="${angles}">
                                <g:if test="${ m_angle.city.toString() == city.toString()}">
                                  <label class="radio">
                                    <input type="radio" name="start" id="start" value="${m_angle.address}">
                                    <input type="radio" name="start_city" id="start_city" value="${m_angle.city}">
                                    ${m_angle.name}
                                  </label> 
                                </g:if>
                              </g:each>
                            </div>
                          </div>                          
                          <a class="btn btn-mini" href="../Angle/create?city=${city}"><i class=" icon-plus"></i></a>                          
                      </div>  
                      </g:each> 
                      </div>
                    </g:if>

                    </td> 
                    </tr> 
                  </table> 
                </div>

                <div class="span6" >


                  <table class="table-bordered"> 
                    <tr>
                      <td>
                        <strong>Nach:</strong>
                      </td>
                      <td>
                        <a class="btn btn-mini" href="../Angle/create"><i class=" icon-plus"></i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>

                    <g:if test="${cities?.size() > 0}" >
                      <ul id="NachTab" class="nav nav-pills">

                        <li class="active">
                          <a href="#nach_location${cities[0].toString().replaceAll(' ', '')}" data-toggle="tab">${cities[0].toString()}</a>
                        </li>

                        <g:each in="${cities[1..(cities.size()-1)]}">
                          <li>
                            <a href="#nach_location${it.toString().replaceAll(' ', '')}" data-toggle="tab">${it.toString()}</a>
                          </li>
                        </g:each>
                      </ul>    
                      <hr>

                      <div id="endTabContent" class="tab-content span2">

                        <g:each var="city" in="${cities}">
                          <g:if test ="${city == cities.get(0)}">
                            <div class="tab-pane fade in active" id="nach_location${city.toString().replaceAll(' ', '')}">
                          </g:if>
                          <g:else>
                            <div class="tab-pane fade in" id="nach_location${city.toString().replaceAll(' ', '')}">
                          </g:else>

                          <div class="control-group">
                            <div class="controls">

                              <g:each var="m_angle" in="${angles}">
                                <g:if test="${ m_angle.city.toString() == city.toString()}">
                                  <label class="radio">
                                    <input type="radio" name="end" id="end" value="${m_angle.address}">
                                    
                                    ${m_angle.name}
                                  </label> 
                                </g:if>
                              </g:each>
                            </div>
                          </div>                          
                          <a class="btn btn-mini" href="../Angle/create?city=${city}"><i class=" icon-plus"></i></a>                          
                      </div>  
                      </g:each> 
                      </div>
                    </g:if>

                    </td> 
                    </tr>
                    <tr><td></td></tr>
                  </table> 

                </div>
              </div>
            </div>
                </br></br></br>

              </div> <!--TabPane active-->

              <div class="tab-pane fade" id="tab2">
                
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th></th>
                        <th>Startort</th>
                        <th>Zielort</th>
                      </tr>
                    </thead>
                    <tbody>
                   
                    <g:each var="trip" in="${trips}">
                      <tr>
                        <td>
                          <label class="radio">
                              <input type="radio" name="start" id="start" value="${trip.getStart()}">
                              <input type="radio" name="end" id="end" value="${trip.getEnd()}">
                          </label> 
                          
                        </td>
                        <td>
                          ${trip.getStart()}
                        </td>
                        <td>
                          ${trip.getEnd()}
                        </td>
                      </tr>

                      </g:each>
                    
                    </tbody>
                  </table>

              </div>

            </div> <!-- TabContent -->

          </div> <!--Row Fluit-->

          <div class="span3">

            <div class="hero-unit" name="sidebar2">
          <hr>

          <strong>Zeit:</strong>             
          </br>
          <span class="add-on">
            <i class="icon-time"></i>
          </span>
          <input name="time" id="time" type="text" class="span2" value="${new Date().format('HH:mm')}"> Uhr
          </br>

          <strong>Datum:</strong>
          </br>
          <span class="add-on">
            <i class="icon-calendar"></i>
          </span>
          <input name="date" id="date" type="text" class="span2" value="${new Date().format('dd.MM.yyyy')}">

          <!-- <richui:dateChooser name="date2" id="date2" class="span1" format="dd.MM.yyyy"/> -->

          <hr>

          <button class="btn btn-primary"  type="submit">suche</button> 

        </div>
      </div>
    </div>      
  </form>
</body>
</html>
