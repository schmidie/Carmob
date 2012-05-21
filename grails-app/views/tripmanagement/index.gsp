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

        <table class="table-bordered">
          <thead>
            <tr>
              <th>Nahverkehr</th>
              <th>Fernverkehr</th>
              <th>Nahverkehr</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <div class="hero-unit">
                  
                  <div class="btn-group" data-toggle="buttons-radio">
                    <g:findAll in="${connections_filtered}" expr="it != null && it.area == 'local_start'">
                      <button class="btn span3">${it.transMean.name}</button>
                    </g:findAll>
                  </div>
                  
                </div>
              </td>
              <td>
                <div class="hero-unit">

                    <div class="btn-group" data-toggle="buttons-radio">
                    <g:findAll in="${connections_filtered}" expr="it != null && it.area == 'intercity'">
                      <button class="btn span3">${it.transMean.name}</button>
                    </g:findAll>
                  </div>
                  
                </div>
              </td>
              <td>
                <div class="hero-unit">

                  <div class="btn-group" data-toggle="buttons-radio">
                    <g:findAll in="${connections_filtered}" expr="it != null && it.area == 'local_end'">
                      <button class="btn span3">${it.transMean.name}</button>
                    </g:findAll>
                  </div>
                  
                </div>
              </td>
            </tr>
          </tbody>
        </table>

      </div>
    </div>
  </body>
</html>
