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
   
    <div class="alert alert-success">
      <div class="row-fluid">
        <strong>Von: ${trip?.getStart()} </strong><br>
        <strong>Nach: ${trip?.getEnd()}</strong><br>
        <strong>Ankunft: ${trip?.getEndTime()?.format('dd.MM.yy HH:mm')}</strong>
      </div>
    </div>

    <hr>
    
  <g:each in="${trip?.connections.sort{it.start_time}}">
    <div>
      <a href="../tripmanagement/scratch_mobile?start=${it.start}&end=${trip.getEnd()}&date=${new Date().format('dd.MM.yyyy')}&time=${new Date().format('HH:mm')}"> 
      
        <div class="alert alert-info">
          
          <strong>${it.transMean.name}</strong> <br>
          
          <i class="icon-time"></i> 
          ${it.getDuration()} min <br>
          
          <strong>start:</strong> ${it.start_time.format('HH:mm')} <br>
          <strong>end:</strong> ${it.end_time.format('HH:mm')} <br>
          
          

          <strong>von: </strong>${it.start} <br>
          <strong>nach: </strong> ${it.end} 
          
          <div class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-road"></i><b class="caret"></b></a>
            <a class="dropdown-menu"><img src="http://maps.google.com/maps/api/staticmap?center=${it.end}&zoom=15&size=256x256&maptype=roadmap
            &sensor=false"></img>
            </a>
          </div>

        </div>
      
      </a>
    </div>
  </g:each>

  </body>
</html>