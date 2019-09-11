<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>startPage.jsp</title>
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

<br/>
<h1 style="color: red">startPage.jsp</h1>

<hr/>
<c:set var="tempCruise" value="-1"></c:set>


<table>
    <c:forEach var="cruisefromlist" items="${allCruises}">
        <c:if test="${tempCruise != cruisefromlist.cruiseId}">
</table><br/><br/>
<table border="1" cellpadding="3" cellspacing="0">
    <thead><tr>
        <td colspan="4">Cruise number: ${tempCruise = cruisefromlist.cruiseId}&nbsp;<br/>
            Ship name: ${cruisefromlist.shipName}</td>
    </tr>
    <tr>
        <td>Country</td>
        <td>City</td>
        <td>Date in</td>
        <td>Date out</td>
    </tr></thead>
        </c:if>

        <tr><td><c:out value="${cruisefromlist.country}"/></td>
            <td><c:out value="${cruisefromlist.city}"/></td>
            <td><c:out value="${cruisefromlist.dateIn}"/></td>
            <td><c:out value="${cruisefromlist.dateOut}"/></td>
        </tr>
    </c:forEach>

</table>
<hr/>
</body>
</html>
