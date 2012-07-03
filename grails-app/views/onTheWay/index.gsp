<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <resource:rating />
    <meta name="layout" content="bootstrap"/>
    <title>CARMOB</title>
    
    
  </head>
  <body>
    
    <div class="well">
      
      <a class="btn" href="../index"><i class="icon-chevron-left"></i>beenden</a>
      <strong> <i class="icon-info-sign"></i> Klicke auf ein Routenelement um die Route ab dort zu ändern.</strong>        
    </div>
    
    <div class="alert alert-success">
      <div class="row-fluid">
        <strong>Von: ${trip?.getStart()} </strong><br>
        <strong>Nach: ${trip?.getEnd()}</strong><br>
        <strong>Ankunft: ${trip?.getEndTime()?.format('dd.MM.yy HH:mm')}</strong>
      </div>
    </div>

  <g:each in="${trip?.connections?.sort{it.start_time}}">

        <div class="alert alert-info">
          <a class="close" href="../tripmanagement/scratch_mobile?start=${it.start}&end=${trip.getEnd()}&date=${new Date().format('dd.MM.yyyy')}&time=${new Date().format('HH:mm')}&trip_id=${trip.id}">ändern</a>
          <div>
            
            <div class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#"><strong>${it.transMean.name}</strong><b class="caret"></b></a>
              <a class="dropdown-menu">
                <div >
                  <i class="icon-time"></i> 
                  ${it.getDuration()} min
                </div>
                <div>
                  <strong>start:</strong> ${it.start_time.format('HH:mm')}
                </div>
                <div >
                  <strong>end:</strong> ${it.end_time.format('HH:mm')} <br>
                </div>
                <div>
                  <strong>von: </strong>${it.start} <br>
                </div>
                <div >
                  <strong>nach: </strong> ${it.end} 
                </div>
              </a>
            </div>
          </div>

          <div>
            <strong>start:</strong> ${it.start_time.format('HH:mm')}
          </div>

          <div>
            <strong>von: </strong>${it.start} <br>
          </div>

          <div>
          <div class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-road"></i><b class="caret"></b></a>
            <a class="dropdown-menu"><img src="http://maps.google.com/maps/api/staticmap?center=${it.end}&zoom=15&size=256x256&maptype=roadmap
            &sensor=false"></img>
            </a>
            </div>
              
          </div>
        </div>

  </g:each>

  </body>
</html>