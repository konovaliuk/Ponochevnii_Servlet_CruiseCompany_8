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
    <title><fmt:message key="message.createship.title"/></title>
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

<h1 style="color: red">createship.jsp</h1>

<div class="form-style-2">

    <div class="form-style-2">
        <div class="form-style-2-heading">
            <fmt:message key="message.createship.createnewship"/>
        </div>
        <br/>
        <form name="createshipForm" method="post" action="/controller">
            <input type="hidden" name="command" value="createship"/>
            <input type="hidden" name="createshipForm" value="createshipForm"/><fmt:message key="message.createship.shipname"/>
            <br/><input type="text" name="shipName" value=""/>
            <br/><br/><fmt:message key="message.createship.numstaff"/>
            <br/><input type="text" name="nStaff" value=""/>
            <br/><br/><fmt:message key="message.createship.ticketfirstclass"/>
            <br/><input type="text" name="nFirstClass" value=""/>
            <br/><br/><fmt:message key="message.createship.ticketsecondclass"/>
            <br/><input type="text" name="nSecondClass" value=""/>
            <br/><br/><fmt:message key="message.createship.ticketthirdclass"/>
            <br/><input type="text" name="nThirdClass" value=""/>
            <br/><br/><fmt:message key="message.createship.ticketfourthclass"/>
            <br/><input type="text" name="nFourthClass" value=""/>
            <br/>

            <br/> <input type="submit" value="<fmt:message key="message.createship.btn"/>"/>
            <br/>
            <p style="color: red">
                <c:if test="${not empty createshipMessage}"><fmt:message key="${createshipMessage}"/></c:if>
            </p>
        </form>
    </div>


</div>


</body>
</html>
