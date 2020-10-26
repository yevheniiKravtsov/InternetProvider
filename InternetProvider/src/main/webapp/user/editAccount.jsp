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
    <form method="post" action="${pageContext.request.contextPath}/user/editAccount">
        <h2 class="text-center">Edit</h2>       
        <div class="form-group">
            <input type="text" class="form-control" value="${account}" name="account">
        </div>
        <small style="color:red">${message}</small>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Save</button>
        </div>           
    </form>
    
</div>
</body>
</html>                                		
