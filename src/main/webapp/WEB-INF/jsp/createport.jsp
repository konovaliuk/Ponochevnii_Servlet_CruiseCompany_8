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
    <title><fmt:message key="message.createport.title"/></title>
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
        <fmt:message key="message.createport.createnewport"/>
    </div>
    <br/>
    <form method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.CREATE_PORT}"/>
        <input type="hidden" name="${StringStorage.CREATE_PORT_FORM}"
               value="${StringStorage.CREATE_PORT_FORM}"/><fmt:message key="message.createport.country"/>
        <br/><input type="text" name="${StringStorage.COUNTRY}" value=""/>
        <br/><br/><fmt:message key="message.createport.city"/>
        <br/><input type="text" name="${StringStorage.CITY}" value=""/>
        <br/>

        <br/> <input type="submit" value="<fmt:message key="message.createport.createbtn"/>"/>
        <br/>
        <p style="color: red">
            <c:if test="${not empty createnewportMessage}"><fmt:message key="${createnewportMessage}"/></c:if>
        </p>
    </form>
</div>


</body>
</html>
