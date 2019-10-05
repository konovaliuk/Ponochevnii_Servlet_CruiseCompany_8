<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="/WEB-INF/tld/custom.tld" %>
<%@ page import="ua.study.poject.cruise.commands.ActionStorage" %>
<%@ page import="ua.study.poject.cruise.util.StringStorage" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="${StringStorage.BANDLE_MESSAGE}"/>

<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title><fmt:message key="message.viewcart.cart"/></title>
</head>
<body>

<c:set var="hdr">
    <c:choose>
        <c:when test="${not empty sessionScope.currentuser}">
            /WEB-INF/jsp/headers/HeaderRegistred.jsp
        </c:when>
        <c:otherwise>
            /WEB-INF/jsp/headers/HeaderUnsigned.jsp
        </c:otherwise>
    </c:choose>
</c:set>
<jsp:include page="${hdr}"/>

<br/><br/><br/>

<div class="form-style-2">
    <div class="form-style-2-heading">
        <fmt:message key="message.viewcart.selectedcruises"/>
    </div>
    <br/><br/>

    <c:if test="${cruisesInCart.size() != 0}">

    <c:forEach var="cruisefromlist" begin="0" end="${cruisesInCart.size() - 1}">
    <table border="1" cellpadding="3" cellspacing="0">
        <thead>
        <tr>
            <td colspan="5">
                <fmt:message key="message.viewcart.cruisenum"/> <b> ${cruisesInCart[cruisefromlist].cruiseId}&nbsp;</b>
                <br/> <fmt:message key="message.viewcart.shipname"/> <b> ${cruisesInCart[cruisefromlist].shipName} </b>
                <br/> <fmt:message key="message.viewcart.ticketclass"/> <b> ${ticketclassesInCart[cruisefromlist].ticketclassName} </b>
                <br/> <fmt:message key="message.viewcart.price"/>

				<b>
                <c:choose>
                    <c:when test="${ticketclassesInCart[cruisefromlist].ticketclassName eq StringStorage.FIRST}">
                        ${cruisesInCart[cruisefromlist].priceFirstClass}
                    </c:when>
                    <c:when test="${ticketclassesInCart[cruisefromlist].ticketclassName eq StringStorage.SECOND}">
                        ${cruisesInCart[cruisefromlist].priceSecondClass}
                    </c:when>
                    <c:when test="${ticketclassesInCart[cruisefromlist].ticketclassName eq StringStorage.THIRD}">
                        ${cruisesInCart[cruisefromlist].priceThirdClass}
                    </c:when>
                    <c:when test="${ticketclassesInCart[cruisefromlist].ticketclassName eq StringStorage.FOURTH}">
                        ${cruisesInCart[cruisefromlist].priceFourthClass}
                    </c:when>
                </c:choose> </b>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="message.viewcruise.country"/></td>
            <td><fmt:message key="message.viewcruise.city"/></td>
            <td><fmt:message key="message.viewcruise.arrival"/></td>
            <td><fmt:message key="message.viewcruise.departure"/></td>
            <td><fmt:message key="message.viewcruise.details"/></td>
        </tr>
        </thead>

        <c:forEach var="port" items="${cruisesInCart[cruisefromlist].printableCruisePorts}">
            <tr>
                <td><c:out value="${port.country}"/></td>
                <td><c:out value="${port.city}"/></td>
                <td><date:out value="${port.dateIn}"/></td>
                <td><date:out value="${port.dateOut}"/></td>
                <td>
                    <form method="post" action="${StringStorage.CONTROLLER}">
                        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.VIEW_PORT}">
                        <input type="hidden" name="${StringStorage.SELECTED_PORT_ID}" value="${port.portId}">
                        <br/><input type="submit" value="<fmt:message key="message.viewcart.moredetails"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
    <form class="inline" method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.VIEW_CRUISE}">
        <input type="hidden" name="${StringStorage.SELECTED_CRUISE_ID}" value="${cruisesInCart[cruisefromlist].cruiseId}">
        <br/><input type="submit" value="<fmt:message key="message.viewcart.moredetails"/>">
    </form>
    <form class="inline" method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.DELETE_FROM_CART}">
        <input type="hidden" name="${StringStorage.DELETE_CRUISE_FROM_CART_FORM}"
               value="${cruisesInCart[cruisefromlist].cruiseId}">
        <br/><input type="submit" value="<fmt:message key="message.viewcart.delfromcart"/>">
    </form>

    <br/><br/><br/><br/>
    </c:forEach>
    </c:if>

    <c:forEach var="excursion" items="${excurisionsInCart}">
	<br/><hr/><br/>
    <h3>${excursion.excursionName}</h3>
    <br/><br/><fmt:message key="message.viewexcursion.description"/> <pre>${excursion.description}</pre>
    <br/><br/><fmt:message key="message.viewexcursion.price"/> ${excursion.price} $
    <br/>
    <form class="inline" method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.VIEW_EXCURSION}">
        <input type="hidden" name="${StringStorage.VIEW_EXCURSION_ID}" value="${excursion.id}">
        <br/><input type="submit" value="<fmt:message key="message.viewport.morebtn"/>">
    </form>
	<form class="inline" method="post" action="${StringStorage.CONTROLLER}">
		<input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.DELETE_FROM_CART}">
		<input type="hidden" name="${StringStorage.DELETE_EXCURSION_FROM_CART_FORM}"
			   value="${excursion.id}">
		<br/><input type="submit" value="<fmt:message key="message.viewcart.delfromcart"/>">
	</form>
	<br/><br/>

    </c:forEach>
    <br/>

    <form class="inline" method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.DELETE_ALL}">
        <br/><input type="submit" value="<fmt:message key="message.viewcart.dellall"/>">
    </form>
    <form class="inline" method="post" action="${StringStorage.CONTROLLER}">
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.PAY}">
        <br/><input type="submit" value="<fmt:message key="message.viewcart.payall"/>">
    </form>

    <p style="color: red">
        <c:if test="${not empty viewcartMessage}"><fmt:message key="${viewcartMessage}"/></c:if>
    </p>


</body>
</html>
