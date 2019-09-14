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
    <title><fmt:message key="message.createexcursion.title"/></title>
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
        <fmt:message key="message.createexcursion.selectorcreateport"/>
    </div>
    <br/>
    <form method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.CREATE_PORT}"/>
        <br/> <input type="submit" value="<fmt:message key="message.createexcursion.createportbtn"/>"/>
    </form>
    <br/><br/><br/><br/>

    <form method="post" action="${StringStorage.CONTROLLER}">
        <select name="${StringStorage.SELECTED_PORT}">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn" items="${allPorts}">
                <option value="${portn.id}">"${portn.id}", "${portn.country}", "${portn.city}"</option>
            </c:forEach>
        </select>

        <br/>
        <br/><br/>
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.CREATE_EXCURSION}"/>
        <input type="hidden" name="${StringStorage.CREATE_EXCURSION_FORM}"
               value="${StringStorage.CREATE_EXCURSION_FORM}"/> <fmt:message
            key="message.createexcursion.excursionname"/>
        <br/><input type="text" name="${StringStorage.EXCURSION_NAME}" value=""/>
        <br/><br/><fmt:message key="message.createexcursion.excursiondesc"/>
        <br/><textarea name="${StringStorage.DESCRIPTION}" maxlength="8000"
                       style="margin: 0px; height: 80px; width: 700px;"></textarea>
        <br/><br/><fmt:message key="message.createexcursion.price"/>
        <br/><input type="number" name="${StringStorage.PRICE}" value=""/>
        <br/><br/>

        <br/> <input type="submit" value="<fmt:message key="message.createexcursion.createexcurbtn"/>"/>
        <br/>
        <p style="color: red">
            <c:if test="${not empty createexcursionMessage}"><fmt:message key="${createexcursionMessage}"/></c:if>
        </p>
    </form>


</div>


</body>
</html>
