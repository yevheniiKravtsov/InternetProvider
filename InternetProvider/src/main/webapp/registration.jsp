<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
        <%@include file="/css/style.css" %>
</style>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="registration-form">
    <form method="post" action="${pageContext.request.contextPath}/registration">
        <h2 class="text-center">Registration</h2>       
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter username" name="login">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Enter password" name="password">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Confirm password" name="confirmPassword">
        </div>
        <small style="color:red">${message}</small>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </div>           
    </form>
    
</div>
</body>
</html>                                		
