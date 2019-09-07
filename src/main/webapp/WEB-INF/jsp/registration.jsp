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
    <title><fmt:message key="message.register.title"/></title>
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

<h1 style="color: red">registration.jsp</h1>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <fmt:message key="message.register.registrationadv"/>
    </div>
    <br/>
<form name="registrationForm" method="POST" action="/controller">
    <input type="hidden" name="command" value="signup"/> <fmt:message key="message.register.login"/>
    <input type="hidden" name="signupForm" value="signupForm"/>
    <br/><input type="text" name="login" value="${param.login}" autofocus/>
    <br/><br/><fmt:message key="message.register.password"/>
    <br/><input type="password" name="password" value="${param.password}"/>
    <br/><br/><fmt:message key="message.register.passworddupl"/>
    <br/><input type="password" name="password2" value="${param.password2}"/>
    <br/><br/><fmt:message key="message.register.firstname"/>
    <br/><input type="text" name="firstname" value="${param.firstname}"/>
    <br/><br/><fmt:message key="message.register.lastname"/>
    <br/><input type="text" name="secondname" value="${param.secondname}"/>
    <br/><br/><fmt:message key="message.register.email"/>
    <br/><input type="email" name="email" value="${param.email}"/>
    <br/><br/><fmt:message key="message.register.tel"/>
    <br/><input type="tel" name="tel" value="${param.tel}"/>
    <br/>


    <br/> <input type="submit" value="<fmt:message key="message.register.registrbtn"/>"/>
    <p style="color: red">
        <c:if test="${not empty registrMessage}"><fmt:message key="${registrMessage}"/></c:if>
    </p>
</form>
</div>

</body>
</html>
