<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>startPage.jsp</title>
</head>
<body>

<% if(request.getParameter("hdr") != null)
    System.out.println(request.getParameter("hdr"));%>


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

<br/>
<h1 style="color: red">startPage.jsp</h1>

<hr/>
<table border="1" cellpadding="3" cellspacing="0">
    <thead><tr>
        <td>Cruise id</td>
        <td>Ship name</td>
        <td>Country</td>
        <td>City</td>
        <td>Date in</td>
        <td>Date out</td>
    </tr>
    </thead>
    <c:forEach var="cruisefromlist" items="${allCruises}">
        <tr><td><c:out value="${cruisefromlist.cruiseId}"/></td>
            <td><c:out value="${cruisefromlist.shipName}"/></td>
            <td><c:out value="${cruisefromlist.country}"/></td>
            <td><c:out value="${cruisefromlist.city}"/></td>
            <td><c:out value="${cruisefromlist.dateIn}"/></td>
            <td><c:out value="${cruisefromlist.dateOut}"/></td>
        </tr>
    </c:forEach>

</table>
<hr/>
</body>
</html>
