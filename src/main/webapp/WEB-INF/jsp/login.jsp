<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ua.study.poject.cruise.commands.ActionStorage"%>
<%@ page import="ua.study.poject.cruise.util.StringStorage"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${StringStorage.BANDLE_MESSAGE}"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="message.login.title"/></title>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>

<c:remove var="hdr" scope="request"/>
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
        <fmt:message key="message.login.signin"/>
    </div>
    <br/>
    <form method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.SIGN_IN}"/>
        <input type="hidden" name="${StringStorage.SIGN_IN_FORM}" value="${StringStorage.SIGN_IN_FORM}"/>
        <label for="name"><fmt:message key="message.login.login"/>
            <input class="input-field" type="text" id="name" name="${StringStorage.LOGIN}" autofocus>
        </label>
        <label for="password"><fmt:message key="message.login.password"/>
            <input class="input-field" type="password" id="password" name="${StringStorage.PASSWORD}">
        </label>
        <input type="submit" value="<fmt:message key="message.login.tologin"/>">
    </form>
    <p style="color: red">
        <c:if test="${not empty signinMessage}"><fmt:message key="${signinMessage}"/></c:if>
    </p>

</div>
</body>
</html>