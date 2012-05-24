
<%@ page import="conn.Angle" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'angle.label', default: 'Angle')}" />
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
						
							<g:sortableColumn property="name" title="${message(code: 'angle.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="address" title="${message(code: 'angle.address.label', default: 'Address')}" />
						
							<g:sortableColumn property="post_code" title="${message(code: 'angle.post_code.label', default: 'Postcode')}" />
						
							<g:sortableColumn property="city" title="${message(code: 'angle.city.label', default: 'City')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${angleInstanceList}" var="angleInstance">
						<tr>
						
							<td>${fieldValue(bean: angleInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: angleInstance, field: "address")}</td>
						
							<td>${fieldValue(bean: angleInstance, field: "post_code")}</td>
						
							<td>${fieldValue(bean: angleInstance, field: "city")}</td>
						
							<td class="link">
								<g:link action="show" id="${angleInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${angleInstanceTotal}" />
				</div>
                          
                          
			</div>
                  
                  <form action="../tripSearch/index">

                  <button class="btn" type="submit">zurück</button>

                </form>

		</div>
	</body>
</html>
