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
    
    <div class="well"> 
      <a class="btn" href="../onTheWay/index?trip_id=${params.trip_id}"><i class="icon-chevron-left"></i>zurück</a>
      <strong> <i class="icon-info-sign"></i> Klicke auf ein Routenelement um die Route ab dort fortzufahren.</strong>
    </div>
    
      <div class="alert alert-success">
        <strong>Von: ${params.start} </strong></br>
        <strong>Nach: ${params.end}</strong></br>
        <strong>Am: ${params.date}</strong>
      </div>
    
      <div>
           
        <g:each var="m_trip" in="${trips?.sort{it.getStartTime()}}">
          
           <a href="../Tripmanagement/save_trip_mobile?id=${m_trip.id}"> 
            <div class="alert alert-info span2">

              <div>
                
              <strong>ab:${m_trip?.getStartTime()?.format('HH:mm')} an:${m_trip?.getEndTime()?.format('HH:mm')} (${m_trip?.name}min)</strong></div>

              <g:each in="${m_trip?.connections}">                          
                
                <div>${it.transMean.name} 
                
                (${it.start_time.format('HH:mm')}-${it.end_time.format('HH:mm')})
                </div>
              </g:each>

            </div>
           </a>
          </g:each>
     </div>
  </body>
</html>
