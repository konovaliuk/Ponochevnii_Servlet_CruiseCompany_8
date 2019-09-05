<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>Change user role</title>
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

<h1 style="color: red">changeuserrole.jsp</h1>
<div class="form-style-2">
    <div class="form-style-2-heading">
        You can change user role!
    </div>
    <br/>
    <form name="changeuserroleForm" method="post" action="/controller">
        <input type="hidden" name="command" value="changeuserrole"/>
        <input type="hidden" name="changeuserroleForm" value="changeuserroleForm"/>
        <br/><br/>User login:
        <br/><input type="text" name="login" value="" autofocus/>
        <br/><br/>User password:
        <br/><input type="password" name="password" value=""/>
        <br/><br/>

        New user role:
        <br/><select name="selectedrole">
            <option value="">Please select new role</option>
            <c:forEach var="rolen" items="${allRoles}">
                <option value="${rolen.role}">${rolen.role}</option>
            </c:forEach>
        </select>

        <br/>
        <br/> <input type="submit" value="Change the role"/>
        <br/>  <p style="color: red">${errorMessage}</p>
    </form>
</div>

</body>
</html>
