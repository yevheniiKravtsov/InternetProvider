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
<div class="registration-form">
    <form method="post" action="${pageContext.request.contextPath}/user/editUser">
        <h2 class="text-center">Create user</h2>       
        <div class="form-group">
            <input type="text" class="form-control" value="${user.getLogin()}" name="newLogin">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Enter old password" name="oldPassword">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Enter new password" name="newPassword">
        </div>
        <small style="color:red">${message}</small>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Save</button>
        </div>           
    </form>
    
</div>
</body>
</html>                                		
