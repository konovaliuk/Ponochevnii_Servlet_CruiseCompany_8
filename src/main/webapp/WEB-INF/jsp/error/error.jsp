<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Error Page</title>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
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

<h1>Произошла ошибка</h1><br/>
<h1>Request from ${pageContext.errorData.requestURI} is failed</h1>
<table border="2" cellpadding="3" cellspacing="0">
    <tr>
        <td>Servlet name or type:</td><td>${pageContext.errorData.servletName}</td>
    </tr>
    <tr>
        <td>Status code:</td><td>${pageContext.errorData.statusCode}</td>
    </tr>
    <tr>
        <td>Exception:</td><td>${pageContext.errorData.throwable}</td>
    </tr>

</table>


<p><a href="/">Вернуться на главную страницу</a></p>

</body>

</html>