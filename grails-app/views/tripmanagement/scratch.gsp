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
   
    <div class="span12">
      <div class="row-fluid">

           
        <div  class="span10">
        
      <div class="alert alert-info">
        <strong>Von: ${params.start} </strong></br>
        <strong>Nach: ${params.end}</strong></br>
        <strong>Am: ${params.date_day}.${params.date_month}.${params.date_year}</strong>
        <hr>
         <form action="../TripSearch/index">                     
          <input name="start" id="start" type="hidden" value="${params.start}">
          <input name="end" id="end" type="hidden" value="${params.end}">
          <input name="date" id="date" type="hidden" value="${params.date}">
          <input name="time" id="time" type="hidden" value="${params.time}">
          <button class="btn btn-info" type="submit">ändern</button>
        </form>
      </div>

       <!-- <g:if test="${params.show_all}">
          <h2>Alle gefundenen Routen</h2>
          ${trips.size()} Routen gefunden </br>
          <a href="../tripmanagement/scratch?time=${params.time}&start=${params.start}&date=${params.date}&end=${params.end}"> 
          weniger anzeigen </a>
        </g:if>
        <g:else>
          <h2>Empfohlene Routen</h2>
          <a href="../tripmanagement/scratch?time=${params.time}&start=${params.start}&date=${params.date}&end=${params.end}&show_all=true"> 
          alle anzeigen </a>
        </g:else>-->

        <hr>
          <h3>Filterfunktionen</h3>
          
          <table class="table table-bordered">
            <thead>
              <tr>
                <th><g:checkBox name="oeko" value="${false}" /> Öko</th>
                <th><g:checkBox name="shopper" value="${false}" /> Shopper</th>
                <th><g:checkBox name="sportler" value="${false}" /> Sportler</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>    
                  <blockquote><small>Es werden nur Routen gesucht, welche die angegebenen CO2-Grenze nicht überschreiten.</small></blockquote>
                  CO2 / km: <g:textField name="co2" value="" />
                </td>
                <td>   
                  <blockquote><small>Es werden Routen bevorzugt, auf welchen Einkaufsmöglichkeiten vorhanden sind.</small></blockquote>       
                </td>
                <td>
                  <blockquote><small>Es werden Routen mit Fahrradstrecken erstellt, wobei die Fahrradstrecken die angegebenen km nicht überschreiten.</small></blockquote> 
                  km pro trip: <g:textField name="sportler" value="" />
                </td>
              </tr>
            </tbody>
          </table>
          <button class="btn" type="submit">aktualisieren</button>
        <hr>

        <g:each var="m_trip" in="${trips}">

          <div class="accordion-group">              
            <div class="accordion-heading"> 
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#${m_trip?.id}">
                  <ul class="breadcrumb">
                    <li>${m_trip?.getStartTime()?.format('dd.MM.yy')} <span class="divider">|</span></li>
                    <li>${m_trip?.name} Minuten <span class="divider">|</span></li>
                    <g:each in="${m_trip?.connections}">
                    <li>${it.transMean.name}<span class="divider">|</span></li>
                  </g:each>
                  </ul>
                </a>
              </div>
              <div id="${m_trip?.id}" class="accordion-body collapse">               
                <div>
                <div class="accordion-inner"> 
                  <div class="row-fluid">
                  <div class="scrollable">
                   <table border="0">
                    <tbody>
                      <tr>
                        
                        <td>
                          <div class="alert alert-success ">
                            <i class="icon-flag"></i>
                          </div> 
                        </td>
                                                            
                        <g:each in="${m_trip?.connections}">
                          <td>
                              <div class="alert alert-info ">
                                <table border="0" rel="tooltip" title="${it.transMean.name}, Von: ${it.start},Nach: ${it.end}">
                                  <tr>
                                    <td colspan="3">
                                      <label class="radio">
                                      <!-- <input type="radio" name="optionsRadios1" id="optionsRadios1" value="option1" checked=""> -->
                                        <!-- <g:img dir="images" file="${it.transMean.name}.png" width="20" height="20"/> -->
                                            ${it.transMean.name} <br>
                                            <i class="icon-time"></i>  ${it.getDuration()} min
                                      </label>
                                    </td>
                                  </tr>
                                    <tr>
                                      <td>ab:${it.start_time.format('HH:mm')}</td>
                                      <td>&nbsp;&nbsp;&nbsp;</td>
                                      <td>an:${it.end_time.format('HH:mm')}</td>
                                    </tr>
                                </table>
                              </div>
                          </td>
                          
                        <td>
                          <div class="alert alert-success ">
                            <i class="icon-flag"></i>
                          </div> 
                        </td>
                        
                        </g:each>
                        </tr>
                        </tbody>
                   </table>     
                    </div>
                    
                    <hr>
                    <div>
                   <form class="pull-right" action="../Tripmanagement/save_trip">                     
                     <!--<g.include controller="Tripmanagement" action="save_trip" params="[]"/>-->
                     <input name="id" id="id" type="hidden" class="span1" value="${m_trip.id}">
                     <button class="btn" type="submit">speichern</button>
                   </form>
                    </div>
                    
                   </div>
                </div>
               </div>
              </div>
            </div>
          </g:each>
      </div>
        <div  class="span2">
          <div align="center" style="width:130px;border:1px solid #ccc;font-color:#ddd;font-weight:bold;margin:0px 0px 0px 0px;">
            <a style="font-size:14px;text-decoration:none;color:#000;" href="http://www.weatherforecastmap.com/germany/${params.start_city}/">Weather in ${params.start_city}</a>
            <script src="http://www.weatherforecastmap.com/weather2001.php?zona=germany_${params.start}"></script>
            <div align="center" style="font-color:#ddd;font-weight:normal;">   
            </div>
          </div>
          
          <div align="center" style="width:130px;border:1px solid #ccc;font-color:#ddd;font-weight:bold;margin:0px 0px 0px 0px;">
            <a style="font-size:14px;text-decoration:none;color:#000;" href="http://www.weatherforecastmap.com/germany/${params.end_city}/">Weather in ${params.end_city}</a>
            <script src="http://www.weatherforecastmap.com/weather2001.php?zona=germany_${params.end}"></script>
            <div align="center" style="font-color:#ddd;font-weight:normal;">   
            </div>
          </div>
          
        </div>
      </div>
      </div>
     </div>
  </body>
</html>
