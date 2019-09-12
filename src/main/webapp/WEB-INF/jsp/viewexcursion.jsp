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
    <title><fmt:message key="message.viewexcursion.detail"/></title>
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

<h1 style="color: red">viewexcursion.jsp</h1>

<div class="form-style-2">


    <div class="form-style-2-heading">
        <fmt:message key="message.viewexcursion.detail"/>
    </div>
    <br/><br/>
    <c:if test="${not empty excursion}">
    <h3>${excursion.excursionName}</h3>
    <br/><br/><fmt:message key="message.viewexcursion.description"/> ${excursion.description}
    <br/><br/><fmt:message key="message.viewexcursion.price"/> ${excursion.price} $
    <br/> <br/>
    <form method="post" action="/controller">
        <input type="hidden" name="command" value="addtocart">
        <input type="hidden" name="excursionIdToCart" value="${excursion.id}">
        <br/><input type="submit" value="<fmt:message key="message.viewexcursion.addtocart"/>">
    </form>
    <br/>

    <p style="color: red">
        <c:if test="${not empty viewexcursionMessage}"><fmt:message key="${viewexcursionMessage}"/></c:if>
    </p>

    </c:if>
</body>
</html>
