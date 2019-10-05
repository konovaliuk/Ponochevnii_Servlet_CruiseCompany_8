<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="/WEB-INF/tld/custom.tld" %>
<%@ page import="ua.study.poject.cruise.commands.ActionStorage" %>
<%@ page import="ua.study.poject.cruise.util.StringStorage" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="${StringStorage.BANDLE_MESSAGE}"/>

<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title><fmt:message key="message.viewmycruises.title"/></title>
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
        <fmt:message key="message.viewmycruises.title"/>
    </div>
    <br/><br/>

    <c:if test="${myPrintableCruises.size() != 0}">

    <c:forEach var="cruisefromlist" begin="0" end="${myPrintableCruises.size() - 1}">
    <table border="1" cellpadding="3" cellspacing="0">
        <thead>
        <tr>
            <td colspan="4">
                <fmt:message key="message.viewcart.cruisenum"/> <b> ${myPrintableCruises[cruisefromlist].cruiseId}&nbsp;</b>
                <br/> <fmt:message key="message.viewcart.shipname"/> <b> ${myPrintableCruises[cruisefromlist].shipName} </b>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="message.viewcruise.country"/></td>
            <td><fmt:message key="message.viewcruise.city"/></td>
            <td><fmt:message key="message.viewcruise.arrival"/></td>
            <td><fmt:message key="message.viewcruise.departure"/></td>
        </tr>
        </thead>

        <c:forEach var="port" items="${myPrintableCruises[cruisefromlist].printableCruisePorts}">
            <tr>
                <td><c:out value="${port.country}"/></td>
                <td><c:out value="${port.city}"/></td>
                <td><date:out value="${port.dateIn}"/></td>
                <td><date:out value="${port.dateOut}"/></td>
            </tr>
        </c:forEach>

    </table>


    <br/><br/><br/><br/>
    </c:forEach>
    </c:if>

    <c:forEach var="excursion" items="${myExcursions}">
    <br/><hr/><br/>
    <h3>${excursion.excursionName}</h3>
    <br/><br/><fmt:message key="message.viewexcursion.description"/><pre> ${excursion.description}</pre>
    <br/>
    <br/><br/>

    </c:forEach>
    <br/>


    <p style="color: red">
        <c:if test="${not empty viewmycruisesMessage}"><fmt:message key="${viewmycruisesMessage}"/></c:if>
    </p>


</body>
</html>
