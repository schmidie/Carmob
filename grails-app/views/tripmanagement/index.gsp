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
      <div class="row-fluid span12">

         <h2>Empfohlene Routen</h2>

                                
        <g:each var="m_trip" in="${trips}">
          
            <div class="accordion-group">              
              <div class="accordion-heading"> 
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#${m_trip.name.replaceAll(' ', '')}">
                  ${m_trip.name}
                </a>
              </div>
              <div id="${m_trip.name.replaceAll(' ', '')}" class="accordion-body collapse in">
                <div class="accordion-inner">
                  
                   <table class="table-bordered">
                    <thead>
                      <tr>
                        <th>Nahverkehr</th>
                        <th></th>
                        <th>Fernverkehr</th>
                        <th></th>
                        <th>Nahverkehr</th>
                        
                      </tr>
                    </thead>
                    <tbody>
                      <tr>                      
                        <td>

                            <div class="btn-group" data-toggle="buttons-radio">
                              <g:findAll in="${m_trip.connections}" expr="it != null && it.area == 'local_start'">
                                <button class="btn btn-info span${(((it.distance/it.transMean.average_speed)*60)/12).toInteger()}">${it.transMean.name} </br><i class="icon-time"></i> ${((it.distance/it.transMean.average_speed)*60).toInteger()} Minuten</button>
                                </br>
                              </g:findAll>
                            </div>

                        </td>
                        <td class="span1"><hr style="color: blue; background: black; height: 5px;" /></td>
                        <td>

                            <div class="btn-group" data-toggle="buttons-radio">
                              <g:findAll in="${m_trip.connections}" expr="it != null && it.area == 'intercity'">
                                <button class="btn btn-info span${(((it.distance/it.transMean.average_speed)*60)/12).toInteger()}">${it.transMean.name} </br><i class="icon-time"></i> ${((it.distance/it.transMean.average_speed)*60).toInteger()} Minuten</button>
                                </br>
                              </g:findAll>
                            </div>

                        </td>
                         <td class="span1"><hr style="color: blue; background: black; height: 5px;" /></td>
                        <td>

                            <div class="btn-group" data-toggle="buttons-radio">
                              <g:findAll in="${m_trip.connections}" expr="it != null && it.area == 'local_end'">
                                <button class="btn btn-info span${(((it.distance/it.transMean.average_speed)*60)/12).toInteger()}">${it.transMean.name} </br><i class="icon-time"></i> ${((it.distance/it.transMean.average_speed)*60).toInteger()} Minuten</button>
                                </br>
                              </g:findAll>
                            </div>

                        </td>
          
                      </tr>
                    </tbody>
                   </table>

                </div>
              </div>
            </div>
          
            </g:each>
          
      </div>
    </div>
  </body>
</html>
