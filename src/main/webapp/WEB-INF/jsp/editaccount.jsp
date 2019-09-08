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
    <title><fmt:message key="message.editaccount.title"/></title>
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

<h1 style="color: red">editaccount.jsp</h1>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <fmt:message key="message.editaccount.uniq"/>
    </div>
    <br/>
<form name="editaccountForm" method="post" action="/controller">
    <input type="hidden" name="command" value="editaccount"/>
    <input type="hidden" name="editaccountForm" value="editaccountForm"/><fmt:message key="message.editaccount.login"/>
    <br/><input type="text" name="login" value="${currenuser.login}" disabled/>
    <br/><br/><fmt:message key="message.editaccount.password"/>
    <br/><input type="password" name="password" value=""/>
    <br/><br/><fmt:message key="message.editaccount.passworddupl"/>
    <br/><input type="password" name="password2" value="${param.password2}"/>
    <br/><br/><fmt:message key="message.editaccount.firstname"/>
    <br/><input type="text" name="firstname" value="${currenuser.firstName}"/>
    <br/><br/><fmt:message key="message.editaccount.lastname"/>
    <br/><input type="text" name="secondname" value="${currenuser.secondName}"/>
    <br/><br/><fmt:message key="message.editaccount.email"/>
    <br/><input type="email" name="email" value="${currenuser.email}"/>
    <br/><br/><fmt:message key="message.editaccount.tel"/>
    <br/><input type="tel" name="tel" value="${currenuser.tel}"/>
    <br/>
    <br/> <input type="submit" value="<fmt:message key="message.editaccount.btn"/>"/>
    <br/>
    <p style="color: red">
        <c:if test="${not empty editaccountMessage}"><fmt:message key="${editaccountMessage}"/></c:if>
    </p>
</form>
</div>

</body>
</html>
