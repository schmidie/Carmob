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
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#${m_trip?.name.replaceAll(' ', '')}">
                  <ul class="breadcrumb">
                    <li>${m_trip?.name}:<span class="divider">/</span></li>
                    <g:each in="${m_trip?.connections}">
                    <li><i class="icon-star-empty"></i>${it.transMean.name}<span class="divider">/</span></li>
                  </g:each>
                  </ul>
                  
                  
                </a>
              </div>
              <div id="${m_trip?.name.replaceAll(' ', '')}" class="accordion-body collapse in">
                <div class="accordion-inner">
                  
                   <table class="table-bordered">
                 <!--   <thead>
                      <tr>
                        <th>Nahverkehr</th>
                        <th></th>
                        <th>Fernverkehr</th>
                        <th></th>
                        <th>Nahverkehr</th>
                        
                      </tr>
                    </thead> -->
                    <tbody>
                      <tr>
                        <td class="span1"><i class="icon-flag"></i></td>
                        <g:each in="${m_trip?.connections}">
                          <td>

                              <!--<div class="btn-group" data-toggle="buttons-radio">-->
                              <table border="0">
                                <tr>
                                  <td>
                                    <label class="radio">
                                    <!-- <input type="radio" name="optionsRadios1" id="optionsRadios1" value="option1" checked=""> -->
                                      <g:img dir="images" file="${it.transMean.name}.png" width="20" height="20"/>${it.transMean.name}<i class="icon-time"></i> 
                                    </label>
                                  </td>
                                </tr>
                                  <tr>
                                    <td>${it.start_time.format('hh:mm')}</td>
                                    <td>${it.end_time.format('hh:mm')}</td>
                                  </tr>
                              </table>
                              <!--</div>-->

                          </td>
                          <td class="span1"><i class="icon-flag"></i></td>
                        </g:each>
                        </tr>
                        </tbody>
                   </table>                  

                   <form class="pull-right" action="../Tripmanagement/save_trip">
                     
                     <!--<g.include controller="Tripmanagement" action="save_trip" params="[]"/>-->
                     <input name="name" id="name" type="hidden" class="span1" value="${m_trip.name}">

                     <button class="btn" type="submit">go</button>
                   </form>

                </div>
              </div>
            </div>

          </g:each>
          
      </div>
    </div>
  </body>
</html>
