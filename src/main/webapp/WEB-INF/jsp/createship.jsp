<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>Create ship</title>
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
            Create new ship
        </div>
        <br/>
        <form name="createshipForm" method="post" action="/controller">
            <input type="hidden" name="command" value="createship"/>
            <input type="hidden" name="createshipForm" value="createshipForm"/> Название корабля:
            <br/><input type="text" name="shipName" value=""/>
            <br/><br/>Количество персонала:
            <br/><input type="text" name="nStaff" value=""/>
            <br/><br/>Количество билетов первого класса:
            <br/><input type="text" name="nFirstClass" value=""/>
            <br/><br/>Количество билетов второго класса:
            <br/><input type="text" name="nSecondClass" value=""/>
            <br/><br/>Количество билетов третьего класса:
            <br/><input type="text" name="nThirdClass" value=""/>
            <br/><br/>Количество билетов четвертого класса:
            <br/><input type="text" name="nFourthClass" value=""/>
            <br/>

            <br/> <input type="submit" value="Create ship"/>
            <br/>  <p style="color: red">${errorMessage}</p>
        </form>
    </div>


</div>


</body>
</html>
