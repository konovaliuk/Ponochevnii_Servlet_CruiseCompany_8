<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.study.poject.cruise.commands.ActionStorage" %>
<%@ page import="ua.study.poject.cruise.util.StringStorage" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="${StringStorage.BANDLE_MESSAGE}"/>

<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title><fmt:message key="message.register.title"/></title>
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
        <fmt:message key="message.register.registrationadv"/>
    </div>
    <br/>
    <form method="POST" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.SIGN_UP}"/>
        <fmt:message key="message.register.login"/>
        <input type="hidden" name="${StringStorage.SIGN_UP_FORM}" value="${StringStorage.SIGN_UP_FORM}"/>
        <br/><input type="text" name="${StringStorage.LOGIN}" value="${param.login}" autofocus/>
        <br/><br/><fmt:message key="message.register.password"/>
        <br/><input type="password" name="${StringStorage.PASSWORD}" value="${param.password}"/>
        <br/><br/><fmt:message key="message.register.passworddupl"/>
        <br/><input type="password" name="${StringStorage.PASSWORD2}" value="${param.password2}"/>
        <br/><br/><fmt:message key="message.register.firstname"/>
        <br/><input type="text" name="${StringStorage.FIRST_NAME}" value="${param.firstname}"/>
        <br/><br/><fmt:message key="message.register.lastname"/>
        <br/><input type="text" name="${StringStorage.SECOND_NAME}" value="${param.secondname}"/>
        <br/><br/><fmt:message key="message.register.email"/>
        <br/><input type="email" name="${StringStorage.EMAIL}" value="${param.email}"/>
        <br/><br/><fmt:message key="message.register.tel"/>
        <br/><input type="tel" name="${StringStorage.TEL}" value="${param.tel}"/>
        <br/>


        <br/> <input type="submit" value="<fmt:message key="message.register.registrbtn"/>"/>
        <p style="color: red">
            <c:if test="${not empty registrMessage}"><fmt:message key="${registrMessage}"/></c:if>
        </p>
    </form>
</div>

</body>
</html>
