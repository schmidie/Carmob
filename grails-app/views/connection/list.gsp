
<%@ page import="conn.Connection" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'connection.label', default: 'Connection')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<th class="header"><g:message code="connection.transMean.label" default="Trans Mean" /></th>
						
							<g:sortableColumn property="start" title="${message(code: 'connection.start.label', default: 'Start')}" />
						
							<g:sortableColumn property="end" title="${message(code: 'connection.end.label', default: 'End')}" />
						
							<g:sortableColumn property="start_time" title="${message(code: 'connection.start_time.label', default: 'Starttime')}" />
						
							<g:sortableColumn property="end_time" title="${message(code: 'connection.end_time.label', default: 'Endtime')}" />
						
							<g:sortableColumn property="co2" title="${message(code: 'connection.co2.label', default: 'Co2')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${connectionInstanceList}" var="connectionInstance">
						<tr>
						
							<td>${fieldValue(bean: connectionInstance, field: "transMean")}</td>
						
							<td>${fieldValue(bean: connectionInstance, field: "start")}</td>
						
							<td>${fieldValue(bean: connectionInstance, field: "end")}</td>
						
							<td><g:formatDate date="${connectionInstance.start_time}" /></td>
						
							<td><g:formatDate date="${connectionInstance.end_time}" /></td>
						
							<td>${fieldValue(bean: connectionInstance, field: "co2")}</td>
						
							<td class="link">
								<g:link action="show" id="${connectionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${connectionInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
