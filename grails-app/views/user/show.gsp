
<%@ page import="conn.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
				
					<g:if test="${userInstance?.auto}">
						<dt><g:message code="user.auto.label" default="Auto" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.auto}" /></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.bahn_card_100}">
						<dt><g:message code="user.bahn_card_100.label" default="Bahncard100" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.bahn_card_100}" /></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.bahn_card_50}">
						<dt><g:message code="user.bahn_card_50.label" default="Bahncard50" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.bahn_card_50}" /></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.email}">
						<dt><g:message code="user.email.label" default="Email" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="email"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.fahrrad}">
						<dt><g:message code="user.fahrrad.label" default="Fahrrad" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.fahrrad}" /></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.login}">
						<dt><g:message code="user.login.label" default="Login" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="login"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.password}">
						<dt><g:message code="user.password.label" default="Password" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="password"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.status}">
						<dt><g:message code="user.status.label" default="Status" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="status"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.trips}">
						<dt><g:message code="user.trips.label" default="Trips" /></dt>
						
							<g:each in="${userInstance.trips}" var="t">
							<dd><g:link controller="trip" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${userInstance?.id}">
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
