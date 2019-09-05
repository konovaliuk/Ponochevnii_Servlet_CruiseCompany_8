<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>My account</title>
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
            <br/> <input type="submit" value="Manage Bonuses"/>
        </form>

        <form name="editaccountManager" method="POST" action="/controller">
            <input type="hidden" name="command" value="editaccount"/>
            <br/> <input type="submit" value="Edit account"/>
        </form>

            <br/> <p style="color: red">${errorMessage}</p>
        </form>
    </div>


</body>
</html>
