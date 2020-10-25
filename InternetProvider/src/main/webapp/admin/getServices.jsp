<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="val" value="USER"/>
<html>
<head>
    <title>Internet Provider</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<div class="container pt-3">
		<h2>Services</h2>
		<div class="btn-group">
  			<a href="${pageContext.request.contextPath}/admin/createService" class="btn btn-success" role="button">Create Service</a>
		</div>
		<br><br>
		<table class ="table table-striped" id="servicessTable">
			<thead>		
				<tr><th>Name</th><th></th><th></th>
			</thead>
			<tbody>
				<c:forEach var="service" items="${serviceList}">
					<tr class= "clickable-row">
					<td>${service.getName()}</td>
					<td style="text-align: right;"> 
						<form method="post" action='<c:url value="/admin/editService" />' style="display:inline;">
       						<input type="hidden" name="serviceId" value="${service.getId()}">
       						<input type="submit" class="btn btn-primary" value="Edit Service">
   						</form> 
  						</td>
       				<td style="text-align: right;"> 
						<form method="post" action='<c:url value="/admin/deleteService" />' style="display:inline;">
	        				<input type="hidden" name="serviceId" value="${service.getId()}">
	        				<input type="submit" class="btn btn-danger" value="Delete Service">
	    				</form> 
	    			</td>
					
					
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>	
</body>
</html>