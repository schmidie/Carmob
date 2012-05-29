<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <resource:timeline />
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
  </head>
  <body onload="initTimeline();">

<richui:timeline style="height: 350px; width: 450px; border: 1px solid #aaa" startDate="${new Date() - 70}" 
            datasource="http://localhost:8080/tripmanagement/events" eventBandSpanHighlightDecorators="[[date: new Date() + 7, startDate: new Date(), endDate: new Date() + 100, color: '#FFFF00', opacity: 50, startLabel: 'Sample', endLabel: 'End sample'], [date: new Date() + 14, startDate: new Date() + 20, endDate: new Date() + 25, color: '#0000FF', opacity: 50, startLabel: 'Sample', endLabel: 'End sample']]" />

    <div class="container-fluid">
      <div class="row-fluid span12">

       <h2>Empfohlene Routen</h2>
                          
        <g:each var="m_trip" in="${trips}">
          
            <div class="accordion-group">              
              <div class="accordion-heading"> 
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#${m_trip.name.replaceAll(' ', '')}">
                  ${m_trip.name}: &nbsp;&nbsp;&nbsp;
                  <g:findAll in="${m_trip.connections}" expr="it != null">
                    <i class="icon-star-empty"></i>${it.transMean.name},
                  </g:findAll>
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
