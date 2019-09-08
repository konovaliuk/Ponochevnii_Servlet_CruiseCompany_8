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
    <title><fmt:message key="message.createport.title"/></title>
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

<h1 style="color: red">createport.jsp</h1>

<div class="form-style-2">

    <div class="form-style-2">
        <div class="form-style-2-heading">
            <title><fmt:message key="message.createport.createnewport"/></title>
        </div>
        <br/>
        <form name="createportForm" method="post" action="/controller">
            <input type="hidden" name="command" value="createport"/>
            <input type="hidden" name="createportForm" value="createportForm"/><fmt:message key="message.createport.country"/>
            <br/><input type="text" name="country" value=""/>
            <br/><br/><fmt:message key="message.createport.city"/>
            <br/><input type="text" name="city" value=""/>
            <br/>

            <br/> <input type="submit" value="<fmt:message key="message.createport.createbtn"/>"/>
            <br/>
            <p style="color: red">
                <c:if test="${not empty createnewportMessage}"><fmt:message key="${createnewportMessage}"/></c:if>
            </p>
        </form>
    </div>


</div>


</body>
</html>
