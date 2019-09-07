<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>

<c:remove var="hdr" scope="request"/>
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

<h1 style="color: red">login.jsp</h1>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign In!
    </div>
    <br/>
    <form method="post" action="/controller">
        <input type="hidden" name="command" value="signin"/>
        <input type="hidden" name="signinForm" value="signinForm"/>
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Login">
    </form>
    <br/> <p style="color: red"><c:if test="${not empty errorMessage}"><fmt:message key="message.loginerror"/></c:if></p>
</div>
</body>
</html>