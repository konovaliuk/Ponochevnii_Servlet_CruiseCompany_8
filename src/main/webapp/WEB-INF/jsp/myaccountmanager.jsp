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
    <title><fmt:message key="message.myaccount.title"/></title>
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

<h1 style="color: red">myaccountmanager.jsp</h1>

<div class="form-style-2">
    <div class="form-style-2-heading">

    </div>
    <br/>

    <form name="managebonusesManager" method="POST" action="/controller">
        <input type="hidden" name="command" value="managebonuses"/>
        <br/> <input type="submit" value="<fmt:message key="message.myaccount.managebonus"/>"/>
    </form>

    <form name="editaccountManager" method="POST" action="/controller">
        <input type="hidden" name="command" value="editaccount"/>
        <br/> <input type="submit" value="<fmt:message key="message.myaccount.editaccountbtn"/>"/>
    </form>

    <br/>
</div>


</body>
</html>
