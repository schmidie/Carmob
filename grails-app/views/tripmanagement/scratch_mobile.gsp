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
      <div class="alert alert-info">
        <strong>Von: ${params.start} </strong></br>
        <strong>Nach: ${params.end}</strong></br>
        <strong>Am: ${params.date}</strong>
      </div>
    </div>
    
   
    <div class="span12">
      <div class="row-fluid">
           
        <g:each var="m_trip" in="${trips}">
          
            <div class="accordion-group">              
              <div class="accordion-heading"> 
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#${m_trip?.id}">
                  <ul class="breadcrumb">
                    <li>${m_trip?.name} Minuten <span class="divider">|</span></li>
                    <g:each in="${m_trip?.connections}">
                    <li><i class="icon-star-empty"></i>${it.transMean.name}<span class="divider">|</span></li>
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
                            <i class="icon-time"></i>
                          </div> 
                        </td>
                                                            
                        <g:each in="${m_trip?.connections}">
                          <td>
                              <div class="alert alert-info ">
                                <table border="0" rel="tooltip" title="${it.transMean.name}, Von: ${it.start},Nach: ${it.end}">
                                  <tr>
                                    <td>
                                      <label class="radio">
                                      <!-- <input type="radio" name="optionsRadios1" id="optionsRadios1" value="option1" checked=""> -->
                                        <!-- <g:img dir="images" file="${it.transMean.name}.png" width="20" height="20"/> -->
                                            ${it.transMean.name}
                                            <i class="icon-time"></i> 
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
                            <i class="icon-time"></i>
                          </div> 
                        </td>
                        
                        </g:each>
                        </tr>
                        </tbody>
                   </table>     
                    </div>
                    
                    <hr>
                    <div>
                   <form class="pull-right" action="../Tripmanagement/save_trip_mobile">                     
                     <!--<g.include controller="Tripmanagement" action="save_trip" params="[]"/>-->
                     <input name="id" id="id" type="hidden" class="span1" value="${m_trip.id}">
                     <button class="btn" type="submit">starten</button>
                   </form>
                    </div>
                    
                   </div>
                </div>
               </div>
              </div>
            </div>
          </g:each>
      </div>
     </div>
  </body>
</html>
