<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>EditAccount</title>
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

<h1 style="color: red">editaccount.jsp</h1>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Login and phone number must be unique!
    </div>
    <br/>
<form name="editaccountForm" method="post" action="/controller">
    <input type="hidden" name="command" value="editaccount"/>
    <input type="hidden" name="editaccountForm" value="editaccountForm"/> Login:
    <br/><input type="text" name="login" value="${currenuser.login}" disabled/>
    <br/><br/>Password:
    <br/><input type="password" name="password" value=""/>
    <br/><br/>Password (duplicate):
    <br/><input type="password" name="password2" value="${param.password2}"/>
    <br/><br/>First name:
    <br/><input type="text" name="firstname" value="${currenuser.firstName}"/>
    <br/><br/>Second name:
    <br/><input type="text" name="secondname" value="${currenuser.secondName}"/>
    <br/><br/>Email:
    <br/><input type="email" name="email" value="${currenuser.email}"/>
    <br/><br/>Telephone:
    <br/><input type="tel" name="tel" value="${currenuser.tel}"/>
    <br/>
    ${editAccountMessage}
    ${editAccountErrorMessage}
    <br/> <input type="submit" value="Edit Account"/>
    <br/>  <p style="color: red">${errorMessage}</p>
</form>
</div>

</body>
</html>
