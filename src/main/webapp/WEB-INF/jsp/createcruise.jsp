<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.study.poject.cruise.commands.ActionStorage" %>
<%@ page import="ua.study.poject.cruise.util.StringStorage" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="${StringStorage.BANDLE_MESSAGE}"/>

<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title><fmt:message key="message.createcruise.title"/></title>
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
        <fmt:message key="message.createcruise.createcruisebtn"/>
    </div>
    <br/><br/>

    <form method="post" action="${StringStorage.CONTROLLER}">
        <fmt:message key="message.createcruise.ship"/><br/>
        <select name="${StringStorage.SELECTED_SHIP}" required>
            <option value=""><fmt:message key="message.createcruise.selectshipplease"/></option>
            <c:forEach var="shipn"
                       items="${allShips}">   <%--id, ship_name, n_staff, n_first_class, n_second_class, n_third_class, n_fourth_class--%>
                <option value="${shipn.id}">${shipn.shipName}"</option>
            </c:forEach>
        </select>

        <br/><br/>
        <input type="hidden" name="${ActionStorage.COMMAND}" value="${ActionStorage.CREATE_CRUISE}"/>
        <input type="hidden" name="${StringStorage.CREATE_CRUISE_FORM}" value="${StringStorage.CREATE_CRUISE_FORM}"/>
        <fmt:message key="message.createcruise.pricefirst"/>
        <br/><input type="number" step="1" name="${StringStorage.PRICE_FIRST_CLASS}" value="" required/>
        <br/><br/><fmt:message key="message.createcruise.pricesecond"/>
        <br/><input type="number" step="1" name="${StringStorage.PRICE_SECOND_CLASS}" value="" required/>
        <br/><br/><fmt:message key="message.createcruise.pricethird"/>
        <br/><input type="number" step="1" name="${StringStorage.PRICE_THIRD_CLASS}" value="" required/>
        <br/><br/><fmt:message key="message.createcruise.pricefourth"/>
        <br/><input type="number" step="1" name="${StringStorage.PRICE_FOURTH_CLASS}" value="" required/>
        <br/><br/><br/><br/><br/><fmt:message key="message.createcruise.startport"/>
        <select name="${StringStorage.SELECTED_PORT_START}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portnstart" items="${allPorts}">
                <option value="${portnstart.id}">${portnstart.country}, ${portnstart.city}</option>
            </c:forEach>
        </select><br/><br/>
        <fmt:message key="message.createcruise.startcruise"/>
        <input name="${StringStorage.DATE_START}" type="datetime-local" required><br/>
        <br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 1:
        <select name="${StringStorage.SELECTED_PORT_1}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn1" items="${allPorts}">
                <option value="${portn1.id}">${portn1.country}, ${portn1.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_1_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_1_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 2:
        <select name="${StringStorage.SELECTED_PORT_2}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn2" items="${allPorts}">
                <option value="${portn2.id}">${portn2.country}, ${portn2.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_2_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_2_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 3:
        <select name="${StringStorage.SELECTED_PORT_3}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn3" items="${allPorts}">
                <option value="${portn3.id}">${portn3.country}, ${portn3.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_3_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_3_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 4:
        <select name="${StringStorage.SELECTED_PORT_4}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn4" items="${allPorts}">
                <option value="${portn4.id}">${portn4.country}, ${portn4.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_4_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_4_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 5:
        <select name="${StringStorage.SELECTED_PORT_5}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn5" items="${allPorts}">
                <option value="${portn5.id}">${portn5.country}, ${portn5.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_5_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_5_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 6:
        <select name="${StringStorage.SELECTED_PORT_6}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn6" items="${allPorts}">
                <option value="${portn6.id}">${portn6.country}, ${portn6.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_6_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_6_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.port"/> 7:
        <select name="${StringStorage.SELECTED_PORT_7}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portn7" items="${allPorts}">
                <option value="${portn7.id}">${portn7.country}, ${portn7.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.arrival"/> <input name="${StringStorage.DATE_7_IN}"
                                                                      type="datetime-local"><br/>
        <br/><fmt:message key="message.createcruise.departure"/> <input name="${StringStorage.DATE_7_OUT}"
                                                                        type="datetime-local">
        <br/><br/>
        <hr/>
        <br/>

        <fmt:message key="message.createcruise.finishport"/>
        <select name="${StringStorage.SELECTED_PORT_STOP}">
            <option value=""><fmt:message key="message.createcruise.selectportplease"/></option>
            <c:forEach var="portnstop" items="${allPorts}">
                <option value="${portnstop.id}">${portnstop.country}, ${portnstop.city}</option>
            </c:forEach>
        </select><br/>
        <br/><fmt:message key="message.createcruise.finishcruise"/> <input name="${StringStorage.DATE_STOP}"
                                                                           type="datetime-local" required>
        <br/><br/><br/>

        <br/> <input type="submit" value="<fmt:message key="message.createcruise.createcruisebtn"/>"/>
        <br/>
        <p style="color: red">
            <c:if test="${not empty createcruiseMessage}"><fmt:message key="${createcruiseMessage}"/></c:if>
        </p>
    </form>


</div>


</body>
</html>
