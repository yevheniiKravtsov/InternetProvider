<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Internet Provider</title>
<meta charset="utf-8">
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<div class="registration-form">
    <form method="post" action="${pageContext.request.contextPath}/admin/editTarif">
        <h2 class="text-center">Edit</h2>       
        <div class="form-group">
            <input type="text" class="form-control" value="${tarif.getName()}" name="newName">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" value="${tarif.getDescription()}" name="newDescription">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" value="${tarif.getPrice()}" name="newPrice">
        </div>
        <div class="form-group">
    		<select name="serviceId" class="custom-select mb-3">	    
				    <c:forEach var="service" items="${serviceList}">
				    	<c:choose>
    						<c:when test="${currentService.getId() == service.getId()}">
         						<option  value="${service.getId()}" selected>${currentService.getName()}</option>
    						</c:when>    
	    					<c:otherwise>
	        					<option value="${service.getId()}">${service.getName()}</option>	
	        				</c:otherwise>
						</c:choose>
				    </c:forEach>
			    </select>
			</div>
		<small style="color:red">${message}</small>
        <div class="form-group">
        	<input type="hidden" name="tarifId" value="${tarif.getId()}">
            <button type="submit" class="btn btn-primary btn-block">Save</button>
        </div>           
    </form>
    
</div>
</body>
</html>                                		
