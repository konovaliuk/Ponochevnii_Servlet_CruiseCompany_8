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
    <title><fmt:message key="message.createexcursion.title"/></title>
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

<h1 style="color: red">createexcursion.jsp</h1>

<div class="form-style-2">


    <div class="form-style-2-heading">
        <fmt:message key="message.createexcursion.selectorcreateport"/>
    </div>
    <br/>
    <form name="createnewportForm" method="post" action="/controller">
        <input type="hidden" name="command" value="createport"/>
        <input type="hidden" name="createnewportForm" value="createnewportForm"/>
        <br/> <input type="submit" value="<fmt:message key="message.createexcursion.createportbtn"/>"/>
    </form>
    <br/><br/><br/><br/>

    <form name="createexcursionForm" method="post" action="/controller">
        <select name="selectedport">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn" items="${allPorts}">
                <option value="${portn.id}">"${portn.id}", "${portn.country}", "${portn.city}"</option>
            </c:forEach>
        </select>

        <br/>
        <br/><br/>
        <input type="hidden" name="command" value="createexcursion"/>
        <input type="hidden" name="createexcursionForm" value="createexcursionForm"/> <fmt:message key="message.createexcursion.excursionname"/>
        <br/><input type="text" name="excursionName" value=""/>
        <br/><br/><fmt:message key="message.createexcursion.excursiondesc"/>
        <br/><textarea name="description" maxlength="2000" style="margin: 0px; height: 80px; width: 700px;"></textarea>
        <br/><br/><fmt:message key="message.createexcursion.price"/>
        <br/><input type="number" name="price" value=""/>
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
