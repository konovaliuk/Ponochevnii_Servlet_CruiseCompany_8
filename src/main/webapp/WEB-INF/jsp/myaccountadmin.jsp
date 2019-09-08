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

<h1 style="color: red">MyAccountAdmin.jsp</h1>

    <div class="form-style-2">
        <div class="form-style-2-heading">

        </div>
        <br/>

        <form name="createshipAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="createship"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.createship"/>"/>
        </form>

        <form name="createportAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="createport"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.createport"/>"/>
        </form>

        <form name="createexcursionAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="createexcursion"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.createexcursion"/>"/>
        </form>

        <form name="editaccountAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="editaccount"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.editaccountbtn"/>"/>
        </form>

        <form name="createcruiseAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="createcruise"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.createcruise"/>"/>
        </form>

        <form name="changeuserroleAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="changeuserrole"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.changeuserrole"/>"/>
        </form>

        <form name="addservicestosystemAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="addshipservicestosystem"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.addservicestosystem"/>"/>
        </form>

        <form name="addservicestoshipAdmin" method="POST" action="/controller">
            <input type="hidden" name="command" value="addshipservicetoship"/>
            <br/> <input type="submit" value="<fmt:message key="message.myaccount.addremoveservicestoship"/>"/>
        </form>

    </div>


</body>
</html>
