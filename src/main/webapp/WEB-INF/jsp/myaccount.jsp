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

<h1 style="color: red">MyAccount.jsp</h1>
customer

<div class="form-style-2">
    <div class="form-style-2-heading">

    </div>
    <br/>

    <form name="editaccountCustomer" method="POST" action="/controller">
        <input type="hidden" name="command" value="editaccount"/>
        <br/> <input type="submit" value="Edit account"/>
    </form>


    <br/> ${errorMessage}
    </form>
</div>


</body>
</html>
