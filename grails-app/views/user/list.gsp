
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
						
							<g:sortableColumn property="surName" title="${message(code: 'user.surName.label', default: 'Sur Name')}" />
						
							<g:sortableColumn property="lastName" title="${message(code: 'user.lastName.label', default: 'Last Name')}" />
						
							<g:sortableColumn property="hasFahrrad" title="${message(code: 'user.hasFahrrad.label', default: 'Has Fahrrad')}" />
						
							<g:sortableColumn property="hasAuto" title="${message(code: 'user.hasAuto.label', default: 'Has Auto')}" />
						
							<g:sortableColumn property="hasBCard50" title="${message(code: 'user.hasBCard50.label', default: 'Has BC ard50')}" />
						
							<g:sortableColumn property="hasBCard100" title="${message(code: 'user.hasBCard100.label', default: 'Has BC ard100')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${userInstanceList}" var="userInstance">
						<tr>
						
							<td>${fieldValue(bean: userInstance, field: "surName")}</td>
						
							<td>${fieldValue(bean: userInstance, field: "lastName")}</td>
						
							<td><g:formatBoolean boolean="${userInstance.hasFahrrad}" /></td>
						
							<td><g:formatBoolean boolean="${userInstance.hasAuto}" /></td>
						
							<td><g:formatBoolean boolean="${userInstance.hasBCard50}" /></td>
						
							<td><g:formatBoolean boolean="${userInstance.hasBCard100}" /></td>
						
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
