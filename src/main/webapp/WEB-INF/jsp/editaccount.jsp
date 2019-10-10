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
    <title><fmt:message key="message.editaccount.title"/></title>
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
        <fmt:message key="message.editaccount.title"/>
    </div>
    <br/>
    <form method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.EDIT_ACCOUNT}"/>
        <input type="hidden" name="${StringStorage.EDIT_ACCOUNT_FORM}" value="${StringStorage.EDIT_ACCOUNT_FORM}"/>
        <fmt:message key="message.editaccount.login"/>
        <br/><input type="text" name="${StringStorage.LOGIN}" value="${currentuser.login}" disabled/>
        <br/><br/><fmt:message key="message.editaccount.password"/>
        <br/><input type="password" name="${StringStorage.PASSWORD}" value=""/>
        <br/><br/><fmt:message key="message.editaccount.passworddupl"/>
        <br/><input type="password" name="${StringStorage.PASSWORD2}" value="${param.password2}"/>
        <br/><br/><fmt:message key="message.editaccount.firstname"/>
        <br/><input type="text" name="${StringStorage.FIRST_NAME}" value="${currentuser.firstName}"/>
        <br/><br/><fmt:message key="message.editaccount.lastname"/>
        <br/><input type="text" name="${StringStorage.SECOND_NAME}" value="${currentuser.secondName}"/>
        <br/><br/><fmt:message key="message.editaccount.email"/>
        <br/><input type="email" name="${StringStorage.EMAIL}" value="${currentuser.email}"/>
        <br/><br/><fmt:message key="message.editaccount.tel"/>
        <br/><input type="tel" name="${StringStorage.TEL}" value="${currentuser.tel}"/>
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
