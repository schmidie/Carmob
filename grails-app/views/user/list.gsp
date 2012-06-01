
<%@ page import="conn.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
						
							<g:sortableColumn property="auto" title="${message(code: 'user.auto.label', default: 'Auto')}" />
						
							<g:sortableColumn property="bahn_card_100" title="${message(code: 'user.bahn_card_100.label', default: 'Bahncard100')}" />
						
							<g:sortableColumn property="bahn_card_50" title="${message(code: 'user.bahn_card_50.label', default: 'Bahncard50')}" />
						
							<g:sortableColumn property="email" title="${message(code: 'user.email.label', default: 'Email')}" />
						
							<g:sortableColumn property="fahrrad" title="${message(code: 'user.fahrrad.label', default: 'Fahrrad')}" />
						
							<g:sortableColumn property="login" title="${message(code: 'user.login.label', default: 'Login')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${userInstanceList}" var="userInstance">
						<tr>
						
							<td><g:formatBoolean boolean="${userInstance.auto}" /></td>
						
							<td><g:formatBoolean boolean="${userInstance.bahn_card_100}" /></td>
						
							<td><g:formatBoolean boolean="${userInstance.bahn_card_50}" /></td>
						
							<td>${fieldValue(bean: userInstance, field: "email")}</td>
						
							<td><g:formatBoolean boolean="${userInstance.fahrrad}" /></td>
						
							<td>${fieldValue(bean: userInstance, field: "login")}</td>
						
							<td class="link">
								<g:link action="show" id="${userInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${userInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
