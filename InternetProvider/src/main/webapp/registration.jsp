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
    <form action="/examples/actions/confirmation.php" method="post">
        <h2 class="text-center">Registration</h2>       
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter username">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter password">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Confirm password">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </div>           
    </form>
    
</div>
</body>
</html>                                		
