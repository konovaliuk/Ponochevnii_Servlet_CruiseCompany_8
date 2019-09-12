<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message"/>

<html>
<head>
	<style>
		<%@include file="/resources/css/style.css"%>
	</style>
	<title>Chow cruise</title>
</head>
<body>

<c:set var="hdr">
	<c:choose>
		<c:when test="${not empty sessionScope.currenuser}">
			/WEB-INF/jsp/headers/HeaderRegistred.jsp
		</c:when>
		<c:otherwise>
			/WEB-INF/jsp/headers/HeaderUnsigned.jsp
		</c:otherwise>
	</c:choose>
</c:set>
<jsp:include page="${hdr}"/>

<h1 style="color: red">showcruise.jsp</h1>

Здесь будет отображаться все товары, добавленные в корзину























</body>
</html>       
