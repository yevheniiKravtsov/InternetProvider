<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style>
        <%@include file="/css/style.css" %>
</style>

<meta charset="utf-8">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="login-form">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <h2 class="text-center">Log in</h2>       
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Username" name="login">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" name="password">
        </div>
        <small style="color:red">${message}</small>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Log in</button>
        </div>          
    </form>
    <p class="text-center"><a href="${pageContext.request.contextPath}/registration">Create an Account</a></p>
</div>
</body>
</html>                                		
