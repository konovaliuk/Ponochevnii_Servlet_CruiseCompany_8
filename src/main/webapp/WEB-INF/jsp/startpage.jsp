<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.study.poject.cruise.commands.ActionStorage" %>
<%@ page import="ua.study.poject.cruise.util.StringStorage" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="${StringStorage.BANDLE_MESSAGE}"/>

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
        <c:when test="${not empty sessionScope.currentuser}">
            /WEB-INF/jsp/headers/HeaderRegistred.jsp
        </c:when>
        <c:otherwise>
            /WEB-INF/jsp/headers/HeaderUnsigned.jsp
        </c:otherwise>
    </c:choose>
</c:set>
<jsp:include page="${hdr}"/>

<br/><br/><br/>

<div class="form-style-2">
    <div class="form-style-2-heading">

    </div>

    <c:set var="tempCruise" value="-1"></c:set>

    <table>
        <c:forEach var="cruisefromlist" items="${allCruises}">
        <c:if test="${tempCruise != cruisefromlist.cruiseId}">
    </table>
    <br/><br/>
    <table border="1" cellpadding="3" cellspacing="0">
        <thead>
        <tr>
            <td colspan="5">Cruise number: ${tempCruise = cruisefromlist.cruiseId}&nbsp;
                <br/> Ship name: ${cruisefromlist.shipName}</td>

        </tr>
        <tr>
            <td>Country</td>
            <td>City</td>
            <td>Date in</td>
            <td>Date out</td>
            <td>Details</td>
        </tr>
        </thead>
        </c:if>

        <tr>
            <td><c:out value="${cruisefromlist.country}"/></td>
            <td><c:out value="${cruisefromlist.city}"/></td>
            <td><c:out value="${cruisefromlist.dateIn}"/></td>
            <td><c:out value="${cruisefromlist.dateOut}"/></td>
            <td>
                <form method="post" action="${StringStorage.CONTROLLER}">
                    <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.VIEW_PORT}">
                    <input type="hidden" name="${StringStorage.SELECTED_PORT_ID}" value="${cruisefromlist.portId}">
                    <br/><input type="submit" value="More details">
                </form>
            </td>

        </tr>
        </c:forEach>

    </table>
</div>
<hr/>
</body>
</html>
