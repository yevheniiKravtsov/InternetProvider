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
		<h2>Users</h2>
		<div class="btn-group">
  			<a href="${pageContext.request.contextPath}/admin/createUser" class="btn btn-success" role="button">Create User</a>
  			<a href="${pageContext.request.contextPath}/admin/users" class="btn btn-primary" role="button">Users</a>
  			<a href="${pageContext.request.contextPath}/admin/admins" class="btn btn-primary" role="button">Administrators</a>
		</div>
		<br><br>
		<table class ="table table-striped" id="usersTable">
			<thead>		
				<tr><th>ID</th><th>Login</th><th>Role</th><th>Status</th><th></th>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr class= "clickable-row">
						<td>${user.getId()}</td>
						<td>${user.getLogin()}</td>
						<td>${user.getRole()}</td>
						<c:choose>
    						<c:when test="${user.getIsConfirmed().equals(true)}">
         						 <td>Confirmed</td>
    						</c:when>    
	    					<c:otherwise>
	        					<td style="text-align: left;"> 
									<form method="post" action='<c:url value="/admin/confirmUser" />' style="display:inline;">
	        							<input type="hidden" name="userId" value="${user.getId()}">
	        							<input type="submit" class="btn btn-warning" value="Confirm User">
	    							</form> 
    							</td>
	        				</c:otherwise>
						</c:choose>
						<c:choose>
    						<c:when test="${user.getRole().toString().equals(val)}">
         						<td style="text-align: right;"> 
									<form method="post" action='<c:url value="/admin/deleteUser" />' style="display:inline;">
			        					<input type="hidden" name="userId" value="${user.getId()}">
			        					<input type="submit" class="btn btn-danger" value="Delete User">
			    					</form> 
		    					</td>
    						</c:when>    
	    					<c:otherwise>
	        					
	        				</c:otherwise>
						</c:choose>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>	
</body>
</html>