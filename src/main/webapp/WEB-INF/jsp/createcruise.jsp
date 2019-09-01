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

    
<table align="left" border="1" cellpadding="0" cellspacing="0" style="width: 700px">
	<thead>
		<tr>
			<th scope="col">
				<p>Ship id</p>
			</th>
			<th scope="col">Price first class</th>
			<th scope="col">Price second class</th>
			<th scope="col">Price third class</th>
			<th scope="col">Price fourth class</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
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
<table align="left" border="1" cellpadding="0" cellspacing="0" style="width: 500px">
	<thead>
		<tr>
			<th scope="col">Port id</th>
			<th scope="col">Date In</th>
			<th scope="col">Date Out</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>&nbsp;</td>
			<td>
				<label for="age">Дата рождения<em>*</em></label>
<input id="age" type="datetime-local" min="1920-01-01" max="2000-01-01"><br>
			</td>
			<td>select</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
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
<p><br>
	&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
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
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>







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
</div>
</body>
</html>       
