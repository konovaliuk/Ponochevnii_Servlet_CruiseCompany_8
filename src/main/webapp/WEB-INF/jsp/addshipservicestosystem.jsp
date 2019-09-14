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
    <title><fmt:message key="message.addshipservicestosystem.title"/></title>
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
        <fmt:message key="message.addshipservicestosystem.title"/>
    </div>
    <br/>
    <form method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.ADD_SHIP_SERVICES_TO_SYSTEM}"/>
        <input type="hidden" name="${StringStorage.ADD_SHIP_SERVICES_TO_SYSTEM_FORM}"
               value="${StringStorage.ADD_SHIP_SERVICES_TO_SYSTEM_FORM}"/>
        <fmt:message key="message.addshipservicestosystem.service"/>
        <br/><input type="text" name="${StringStorage.NEW_SERVICE_IN_SYSTEM}" value=""/>
        <br/>

        <br/> <input type="submit" value="<fmt:message key="message.addshipservicestosystem.createservicebtn"/>"/>
        <br/>
        <p style="color: red">
            <c:if test="${not empty addshipservicestosystemMessage}"><fmt:message
                    key="${addshipservicestosystemMessage}"/></c:if>
        </p>
    </form>
</div>

<br/><br/><br/>
<div class="form-style-2-heading">
    <fmt:message key="message.addshipservicestosystem.existinginsystem"/>
</div>
<table border="1" cellpadding="3" cellspacing="0">
    <c:forEach var="servicefromlist" items="${allServices}">
    <tr>
        <td><c:out value="${servicefromlist.serviceName}"/></td>
    </tr>
    </c:forEach>

</body>
</html>
