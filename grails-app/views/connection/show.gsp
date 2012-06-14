
<%@ page import="conn.Connection" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'connection.label', default: 'Connection')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${connectionInstance?.transMean}">
						<dt><g:message code="connection.transMean.label" default="Trans Mean" /></dt>
						
							<dd><g:link controller="transportationMean" action="show" id="${connectionInstance?.transMean?.id}">${connectionInstance?.transMean?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${connectionInstance?.start}">
						<dt><g:message code="connection.start.label" default="Start" /></dt>
						
							<dd><g:fieldValue bean="${connectionInstance}" field="start"/></dd>
						
					</g:if>
				
					<g:if test="${connectionInstance?.end}">
						<dt><g:message code="connection.end.label" default="End" /></dt>
						
							<dd><g:fieldValue bean="${connectionInstance}" field="end"/></dd>
						
					</g:if>
				
					<g:if test="${connectionInstance?.start_time}">
						<dt><g:message code="connection.start_time.label" default="Starttime" /></dt>
						
							<dd><g:formatDate date="${connectionInstance?.start_time}" /></dd>
						
					</g:if>
				
					<g:if test="${connectionInstance?.end_time}">
						<dt><g:message code="connection.end_time.label" default="Endtime" /></dt>
						
							<dd><g:formatDate date="${connectionInstance?.end_time}" /></dd>
						
					</g:if>
				
					<g:if test="${connectionInstance?.distance}">
						<dt><g:message code="connection.distance.label" default="Distance" /></dt>
						
							<dd><g:fieldValue bean="${connectionInstance}" field="distance"/></dd>
						
					</g:if>
				
					<g:if test="${connectionInstance?.trips}">
						<dt><g:message code="connection.trips.label" default="Trips" /></dt>
						
							<g:each in="${connectionInstance.trips}" var="t">
							<dd><g:link controller="trip" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${connectionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${connectionInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
