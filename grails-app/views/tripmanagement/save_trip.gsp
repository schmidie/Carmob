<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Saved </title>
  </head>
  <body>
    <h1>Saved ${params.name}</h1>
    
  <g:each var="m_trip" in="${trips}">
    <p>${m_trip.name}</p>
  </g:each>
    
  </body>
</html>
