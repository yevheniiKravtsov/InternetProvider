<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Internet Provider</title>
<meta charset="utf-8">
</head>
<body>
<jsp:include page="userHeader.jsp"/>
<div class="container pt-3">
		<h2>User Profile</h2>
		<div class="btn-group">
  			<a href="${pageContext.request.contextPath}/user/editUser" class="btn btn-primary" role="button">Edit profile</a>
  			<a href="${pageContext.request.contextPath}/user/removeUserProfile" class="btn btn-danger" role="button">Remove profile</a>
		</div>
		<br><br>
		<div class="row justify-content-start">
	    	<div class="col-3">
	    		<label>Username:</label>
	    		<h4>${user.getLogin()}</h4>
	    	</div>
	    	<div class="col-3">
	      		<label>User role:</label>
	    		<h4>${user.getRole()}</h4>
	    	</div>
	    	<div class="col-3">
	      		<label>User state:</label>
	      		<c:choose>
    				<c:when test="${user.getIsBlocked().equals(true)}">
         				<h4 style="color:red">Blocked</h4>
    				</c:when>     				
	    			<c:otherwise>
	        			<h4 style="color:green">Active</h4>
	        		</c:otherwise>
				</c:choose>
	    	</div>
	    	<div class="col-3">
	      		<label>User account:</label>
	    		<h4>${user.getAccount()}</h4>
				<form method="post" action='<c:url value="/user/editAccount" />' style="display:inline;">
   					<input type="submit" class="btn btn-success" value="Account Updating">
				</form> 
	    	</div>
	  	</div><br>
	  	<h2>User Tariffs</h2>
	  	<table class ="table table-striped" id="tarifsTable">
			<thead>		
				<tr><th>Name</th><th>Description</th><th>Price</th><th>Service</th>
			</thead>
			<tbody>
				<c:forEach var="tarif" items="${user.getTarifList()}">
					<tr class= "clickable-row">
						<td>${tarif.getName()}</td>
						<td>${tarif.getDescription()}</td>
						<td>${tarif.getPrice()}</td>
						<td>${tarif.getService().getName()}</td>
						<td style="text-align: right;"> 
							<form method="post" action='<c:url value="/user/dissconnectTarif" />' style="display:inline;">
	        					<input type="hidden" name="tarifId" value="${tarif.getId()}">
	        					<input type="submit" class="btn btn-danger" value="Dissconnect Tarif">
	    					</form> 
    					</td>
		        		
					</tr>
				</c:forEach>
			</tbody>
		</table>        
</div>
</body>
</html>                                		
