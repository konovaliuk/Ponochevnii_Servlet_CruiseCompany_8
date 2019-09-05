<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>Registration</title>
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

<h1 style="color: red">Cart.jsp</h1>

<div class="form-style-2">







<p>&nbsp;</p>
<p>Ship id: label</p>
<p>Ship name: label</p>
<p>&nbsp;</p>
<table align="left" border="1" cellpadding="0" cellspacing="0" style="width: 700px;">
	<thead>
		<tr>
			<th scope="col">&nbsp;Ship servece
				<p>&nbsp;</p>
			</th>
			<th scope="col">Payable</th>
			<th scope="col">&nbsp;</th>
			<th scope="col">&nbsp;</th>
			<th scope="col">&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Service 1</td>
			<td><input type="checkbox"></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</tbody>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>create button</p>
<form action="/command" method="post" name="createButton">&nbsp;</form>
<p>&nbsp;</p>
<p>&nbsp;</p>









</div>








<div class="form-style-2">
    <div class="form-style-2-heading">
        Login and phone must be unique!
    </div>
    <br/>
    <form name="managebonusesForm" method="post" action="/controller">
        <input type="hidden" name="command" value="managebonuses"/>
        <input type="hidden" name="managebonusesForm" value="managebonusesForm"/> Login:
        <br/><input type="text" name="login" value="${currenuser.login}" disabled/>
        <br/><br/>Password:
        <br/><input type="password" name="password" value=""/>
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
