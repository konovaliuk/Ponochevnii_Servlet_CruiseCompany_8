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
<table align="left" border="1" cellpadding="0" cellspacing="0" style="width: 500px">
	<thead>
		<tr>
			<th scope="col">Cruise :</th>
			<th scope="col">Country, City</th>
			<th scope="col">&nbsp; &nbsp;
				<form action="/controller" method="post" name="formDelete">
				<input name="command" type="hidden" value="deletefromcart"/>
				<input type="submit" value="Registration"/>
				</form>
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>&nbsp;Citi, Country</td>
			<td>&nbsp;Excursion</td>
			<td>
				<form action="/controller" method="post" name="formDelete">&nbsp;</form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>
				<form action="/controller" method="post" name="deleteForm">&nbsp;</form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>
				<form action="/controller" method="post" name="formDelete">&nbsp;</form>
			</td>
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
<form action="/controller" method="post" name="Pay">&nbsp;</form>
</div>
<body id="cke_pastebin" style="position: absolute; top: 61px; width: 1px; height: 1px; overflow: hidden; left: -1000px;"><br>
</body>







<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!
    </div>
    <br/>
<form name="registrationForm" method="POST" action="/controller">
    <input type="hidden" name="command" value="signup"/> Login:
    <br/><input type="text" name="login" value="${param.login}" autofocus/>
    <br/><br/>Password:
    <br/><input type="password" name="password" value="${param.password}"/>
    <br/><br/>First name:
    <br/><input type="text" name="firstname" value="${param.firstname}"/>
    <br/><br/>Second name:
    <br/><input type="text" name="secondname" value="${param.secondname}"/>
    <br/><br/>Email:
    <br/><input type="email" name="email" value="${param.email}"/>
    <br/><br/>Telephone:
    <br/><input type="tel" name="tel" value="${param.tel}"/>
    <br/>
    ${registrationMessage}

    <br/> <input type="submit" value="Registration"/>
    <br/> ${errorMessage}
</form>
</div>

</body>
</html>       
