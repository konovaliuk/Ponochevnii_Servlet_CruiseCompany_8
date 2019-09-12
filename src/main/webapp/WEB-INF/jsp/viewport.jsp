<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>

<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title><fmt:message key="message.viewport.title"/></title>
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

<h1 style="color: red">viewport.jsp</h1>

<div class="form-style-2">


    <div class="form-style-2-heading">
        <fmt:message key="message.viewport.excursion"/>${selectedPort.country}. ${selectedPort.city}
    </div>

    <c:if test="${not empty allExcursions}">
    <c:forEach var="excursion" items="${allExcursions}">
    <h3><c:out value="${excursion.excursionName}"/></h3>
        <fmt:message key="message.viewport.price"/> <c:out value="${excursion.price}"/> $
    <br/>
    <form class="inline" method="post" action="/controller">
        <input type="hidden" name="command" value="addtocart">
        <input type="hidden" name="excursionIdToCart" value="${excursion.id}">
        <br/><input type="submit" value="<fmt:message key="message.viewport.addbtn"/>">
    </form>

    <form class="inline" method="post" action="/controller">
        <input type="hidden" name="command" value="viewexcursion">
        <input type="hidden" name="viewExcursionId" value="${excursion.id}">
        <br/><input type="submit" value="<fmt:message key="message.viewport.morebtn"/>">
    </form>


    <br/>
    <hr/>

    </c:forEach>
    </c:if>
    <p style="color: red">
        <c:if test="${not empty viewportMessage}"><fmt:message key="${viewportMessage}"/></c:if>
    </p>

</body>
</html>
