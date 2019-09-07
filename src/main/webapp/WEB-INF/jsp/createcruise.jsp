<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message"/>

<html>
<head>
	<style>
		<%@include file="/resources/css/style.css"%>
	</style>
	<title>Create cruise</title>
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

<h1 style="color: red">createcruise.jsp</h1>

<div class="form-style-2">


	<div class="form-style-2-heading">
		Create cruise. Select ship for this or create new ship
	</div>
	<br/>
	<form name="createnewshipForm" method="post" action="/controller">
		<input type="hidden" name="command" value="createship"/>
		<input type="hidden" name="createnewshipForm" value="createnewshipForm"/>
		<br/> <input type="submit" value="Create ship"/>
	</form>
	<br/><br/><br/><br/>

	<form name="createcruiseForm" method="post" action="/controller">
		<select name="selectedship">
			<option value="">Please select the ship</option>
			<c:forEach var="shipn" items="${allShips}">    private int id;
				<option value="${shipn.id}">"${shipn.shipName}", "${shipn.nStaff}", "${shipn.nFirstClass}, "${shipn.nSecondClass}", "${shipn.nThirdClass}", "${shipn.nFourthClass}""</option>
			</c:forEach>
		</select>

		<br/><br/>
		<input type="hidden" name="command" value="createcruise"/>
		<input type="hidden" name="createexcursionForm" value="createexcursionForm"/>
		Цена билета первого класса:
		<br/><input type="text" name="priceFirstClass" value=""/>
		<br/><br/>Цена билета второго класса:
		<br/><input type="text" name="priceSecondClass" value=""/>
		<br/><br/>Цена билета третьего класса:
		<br/><input type="text" name="priceThirdClass" value=""/>
		<br/><br/>Цена билета четвертого класса:
		<br/><input type="text" name="priceFourthClass" value=""/>
		<br/><br/>

		<br/> <input type="submit" value="Create cruise"/>
		<br/>
		<p style="color: red">${errorMessage}</p>
	</form>


</div>


</body>
</html>
