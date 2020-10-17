<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html  lang="${sessionScope.lang}">
<head>
<meta charset="ISO-8859-1">
<title><fmt:message key="internet.provider"/></title>
</head> 
<body>
<jsp:include page="header.jsp"/>
<h2><fmt:message key="main.page"/></h2>
</body>
</html>