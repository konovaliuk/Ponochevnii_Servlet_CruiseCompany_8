<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
    <title>index.jsp</title>
</head>
<body>

<% if(request.getParameter("hdr") != null)
    System.out.println(request.getParameter("hdr"));%>


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

<br/>
<h1 style="color: red">index.jsp</h1>
Здесь будет содержимое страницы
<br/><br/>
<br/><a href="/signin">Перейти на signin.jsp</a>
<br/><a href="/error">Перейти на error.jsp</a>


<form method="post" action="/controller">
    <input type="hidden" name="command" value="addshipservicestodb"/>
    <input type="submit" value="add ship services to db">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="addshipservicestoship"/>
    <input type="submit" value="add ship services to ship">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="viewcart"/>
    <input type="submit" value="view cart">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="changeuserrole"/>
    <input type="submit" value="change user role">
</form>


<form method="post" action="/controller">
    <input type="hidden" name="command" value="createcruise"/>
    <input type="submit" value="create cruise">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="createexcursion"/>
    <input type="submit" value="create excursion">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="viewexcursioninports"/>
    <input type="submit" value="view excursion in ports">
</form>

<form method="post" action="/controller">
    <input type="hidden" name="command" value="add ship services to db"/>
    <input type="submit" value="addshipservicestodb">
</form>
<hr/>
<table>
<c:forEach var="cruisefromlist" items="${allCruises}">
    <tr><td><c:out value="${cruisefromlist.shipName}"/></td>
        <td><c:out value="${cruisefromlist.country}"/>
        </td><td><c:out value="${cruisefromlist.city}"/></td>
        <td><c:out value="${cruisefromlist.dateIn}"/></td>
        <td><c:out value="${cruisefromlist.dateOut}"/></td>
    </tr>
</c:forEach>

</table>
<hr/>
</body>
</html>
