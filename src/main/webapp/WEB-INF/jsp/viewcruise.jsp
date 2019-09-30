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
    <title><fmt:message key="message.viewcruise.cruiseInfo"/></title>
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
        <fmt:message key="message.viewcruise.cruiseInfo"/>
    </div>

    <table border="1" cellpadding="3" cellspacing="0">
        <thead>
        <tr>
            <td colspan="5">
                <fmt:message key="message.viewcart.cruisenum"/> <b> ${cruise.cruiseId}&nbsp;</b>
                <br/> <fmt:message key="message.viewcart.shipname"/> <b> ${cruise.shipName} </b>
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

        <c:forEach var="port" items="${cruise.printableCruisePorts}">
            <tr>
                <td><c:out value="${port.country}"/></td>
                <td><c:out value="${port.city}"/></td>
                <td><date:out value="${port.dateIn}"/></td>
                <td><date:out value="${port.dateOut}"/></td>
                <td>
                    <form method="post" action="${StringStorage.CONTROLLER}">
                        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.VIEW_PORT}">
                        <input type="hidden" name="${StringStorage.SELECTED_PORT_ID}"
                               value="${port.portId}">
                        <br/><input type="submit" value="<fmt:message key="message.viewcart.moredetails"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
<br/><br/><br/>


<c:if test="${shipserviceList != null && shipserviceList.size() != 0}">
    <br/><br/>
    <div class="form-style-2">
        <div class="form-style-2-heading">
            <fmt:message key="message.managebonuses.allavailableserv"/>
        </div>
        <table border="1" cellpadding="3" cellspacing="0">
            <thead>
            <tr>
                <td><fmt:message key="message.addshipservicetoship.td1"/></td>
                <td><fmt:message key="message.addshipservicetoship.td2"/></td>
            </tr>
            </thead>
            <c:forEach var="shipservicen" items="${shipserviceList}">
                <tr>
                    <td><c:out value="${shipservicen.serviceName}"/></td>
                    <td>
                        <c:if test="${shipservicen.payable == 0}"> <fmt:message
                                key="message.addshipservicetoship.free"/>
                        </c:if>
                        <c:if test="${shipservicen.payable != 0}"> <fmt:message
                                key="message.addshipservicetoship.payable"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${allBonusesForTicketClass1 != null && allBonusesForTicketClass1.size() != 0}">
    <br/><br/>
    <div class="form-style-2">
        <div class="form-style-2-heading">
            <fmt:message key="message.managebonuses.bforfirstclass"/>
        </div>

        <table border="1" cellpadding="3" cellspacing="0">
            <thead>
            <tr>
                <td><fmt:message key="message.addshipservicetoship.td1"/></td>
            </tr>
            </thead>

            <c:forEach var="bonuseForTicketClass1" items="${allBonusesForTicketClass1}">
                <tr>
                    <td><c:out value="${bonuseForTicketClass1.printableServiceOnShip.serviceName}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <hr/>
    </div>
</c:if>


<c:if test="${allBonusesForTicketClass2 != null && allBonusesForTicketClass2.size() != 0}">
    <br/><br/>
    <div class="form-style-2">
        <div class="form-style-2-heading">
            <fmt:message key="message.managebonuses.bforsecclass"/>
        </div>
        <table border="1" cellpadding="3" cellspacing="0">

            <thead>
            <tr>
                <td><fmt:message key="message.addshipservicetoship.td1"/></td>
            </tr>
            </thead>

            <c:forEach var="bonuseForTicketClass2" items="${allBonusesForTicketClass2}">
                <tr>
                    <td><c:out value="${bonuseForTicketClass2.printableServiceOnShip.serviceName}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <hr/>
    </div>
</c:if>


<c:if test="${allBonusesForTicketClass3 != null && allBonusesForTicketClass3.size() != 0}">
    <br/><br/>
    <div class="form-style-2">
        <div class="form-style-2-heading">
            <fmt:message key="message.managebonuses.bforthirdclass"/>
        </div>
        <table border="1" cellpadding="3" cellspacing="0">
            <thead>
            <tr>
                <td><fmt:message key="message.addshipservicetoship.td1"/></td>
            </tr>
            </thead>
            <c:forEach var="bonuseForTicketClass3" items="${allBonusesForTicketClass3}">
                <tr>
                    <td><c:out value="${bonuseForTicketClass3.printableServiceOnShip.serviceName}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <hr/>
    </div>
</c:if>


<c:if test="${allBonusesForTicketClass4 != null && allBonusesForTicketClass4.size() != 0}">
    <br/><br/>
    <div class="form-style-2">
        <div class="form-style-2-heading">
            <fmt:message key="message.managebonuses.bforfourclass"/>
        </div>

        <table border="1" cellpadding="3" cellspacing="0">
            <thead>
            <tr>
                <td><fmt:message key="message.addshipservicetoship.td1"/></td>
            </tr>
            </thead>

            <c:forEach var="bonuseForTicketClass4" items="${allBonusesForTicketClass4}">
                <tr>
                    <td><c:out value="${bonuseForTicketClass4.printableServiceOnShip.serviceName}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <hr/>
    </div>
</c:if>


<br/><br/><br/>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <fmt:message key="message.viewcruise.buy"/>
    </div>

    <table border="0" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <td align="center" width="170px"><fmt:message key="message.viewcruise.firstclass"/></td>
            <td align="center" width="170px"><fmt:message key="message.viewcruise.secondclass"/></td>
            <td align="center" width="170px"><fmt:message key="message.viewcruise.thirdclass"/></td>
            <td align="center" width="170px"><fmt:message key="message.viewcruise.fourthclass"/></td>
        </tr>
        </thead>
        <tr>
            <td align="center" width="170px">
                <form method="post" action="${StringStorage.CONTROLLER}">
                    <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.ADD_TO_CART}">
                    <input type="hidden" name="${StringStorage.TICKETCLASS}" value="${StringStorage.FIRST}">
                    <input type="hidden" name="${StringStorage.CRUISE_ID_TO_CART}" value="${cruise.cruiseId}">
                    <br/><input type="submit" value="${cruise.priceFirstClass} $">
                </form>
            </td>
            <td align="center" width="170px">
                <form method="post" action="${StringStorage.CONTROLLER}">
                    <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.ADD_TO_CART}">
                    <input type="hidden" name="${StringStorage.TICKETCLASS}" value="${StringStorage.SECOND}">
                    <input type="hidden" name="${StringStorage.CRUISE_ID_TO_CART}" value="${cruise.cruiseId}">
                    <br/><input type="submit" value="${cruise.priceSecondClass} $">
                </form>
            </td>
            <td align="center" width="170px">
                <form method="post" action="${StringStorage.CONTROLLER}">
                    <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.ADD_TO_CART}">
                    <input type="hidden" name="${StringStorage.TICKETCLASS}" value="${StringStorage.THIRD}">
                    <input type="hidden" name="${StringStorage.CRUISE_ID_TO_CART}" value="${cruise.cruiseId}">
                    <br/><input type="submit" value="${cruise.priceThirdClass} $">
                </form>
            </td>
            <td align="center" width="170px">
                <form method="post" action="${StringStorage.CONTROLLER}">
                    <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.ADD_TO_CART}">
                    <input type="hidden" name="${StringStorage.TICKETCLASS}" value="${StringStorage.FOURTH}">
                    <input type="hidden" name="${StringStorage.CRUISE_ID_TO_CART}" value="${cruise.cruiseId}">
                    <br/><input type="submit" value="${cruise.priceFourthClass} $">
                </form>
            </td>
        </tr>

    </table>
</div>


<p style="color: red">
    <c:if test="${not empty viewcruiseMessage}"><fmt:message key="${viewcruiseMessage}"/></c:if>
</p>
</body>
</html>
