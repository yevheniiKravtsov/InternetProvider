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
	    	<div class="col-4">
	    		<label>User ID:</label>
	    		<h4>${user.getId()}</h4>
	    	</div>
	    	<div class="col-4">
	      		<label>User name:</label>
	    		<h4>${user.getLogin()}</h4>
	    	</div>
	    	<div class="col-4">
	      		<label>User role:</label>
	    		<h4>${user.getRole()}</h4>
	    	</div>
	  	</div><br>
	  	<h2>User Tariffs</h2>        
</div>
</body>
</html>                                		
