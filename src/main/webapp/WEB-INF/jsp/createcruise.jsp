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
		Create cruise
	</div>
    <br/><br/>

	<form name="createcruiseForm" method="post" action="/controller">
		<select name="selectedship" required>
			<option value="">Please select the ship</option>
			<c:forEach var="shipn" items="${allShips}">   <%--id, ship_name, n_staff, n_first_class, n_second_class, n_third_class, n_fourth_class--%>
				<option value="${shipn.id}">${shipn.shipName}"</option>
			</c:forEach>
		</select>

		<br/><br/>
		<input type="hidden" name="command" value="createcruise"/>
		<input type="hidden" name="createcruiseForm" value="createcruiseForm"/>
		Цена билета первого класса:
		<br/><input type="number" step="0.01" name="priceFirstClass" value="" required/>
		<br/><br/>Цена билета второго класса:
		<br/><input type="number" step="0.01" name="priceSecondClass" value="" required/>
		<br/><br/>Цена билета третьего класса:
		<br/><input type="number" step="0.01" name="priceThirdClass" value="" required/>
		<br/><br/>Цена билета четвертого класса:
		<br/><input type="number" step="0.01" name="priceFourthClass" value="" required/>
        <br/><br/><br/><br/><br/>Порт начала круиза:
        <select name="selectedportstart">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portnstart" items="${allPorts}">
                <option value="${portnstart.id}">${portnstart.country}, ${portnstart.city}</option>
            </c:forEach>
        </select><br/><br/>
        Начало круиза: <input name="datestart" type="datetime-local" required><br/>
        <br/><hr/><br/>

        Порт 1:
        <select name="selectedport1">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn1" items="${allPorts}">
                <option value="${portn1.id}">${portn1.country}, ${portn1.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date1in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date1out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт 2:
        <select name="selectedport2">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn2" items="${allPorts}">
                <option value="${portn2.id}">${portn2.country}, ${portn2.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date2in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date2out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт 3:
        <select name="selectedport3">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn3" items="${allPorts}">
                <option value="${portn3.id}">${portn3.country}, ${portn3.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date3in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date3out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт 4:
        <select name="selectedport4">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn4" items="${allPorts}">
                <option value="${portn4.id}">${portn4.country}, ${portn4.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date4in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date4out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт 5:
        <select name="selectedport5">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn5" items="${allPorts}">
                <option value="${portn5.id}">${portn5.country}, ${portn5.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date5in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date5out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт 6:
        <select name="selectedport6">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn6" items="${allPorts}">
                <option value="${portn6.id}">${portn6.country}, ${portn6.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date6in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date6out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт 7:
        <select name="selectedport7">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portn7" items="${allPorts}">
                <option value="${portn7.id}">${portn7.country}, ${portn7.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Прибытие: <input name="date7in" type="datetime-local"><br/>
        <br/>Отплытие: <input name="date7out" type="datetime-local">
        <br/><br/><hr/><br/>

        Порт окончания круиза:
        <select name="selectedportstop">
            <option value=""><fmt:message key="message.createexcursion.selectport"/></option>
            <c:forEach var="portnstop" items="${allPorts}">
                <option value="${portnstop.id}">${portnstop.country}, ${portnstop.city}</option>
            </c:forEach>
        </select><br/>
        <br/>Окончание круиза: <input name="datestop" type="datetime-local" required>
        <br/><br/><br/>

        <br/> <input type="submit" value="Create cruise"/>
		<br/>
		<p style="color: red">${errorMessage}</p>
	</form>


</div>


</body>
</html>
