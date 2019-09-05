<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>Add service to ship</title>
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

<h1 style="color: red">addshipservicetoship.jsp</h1>

<div class="form-style-2">

    <div class="form-style-2-heading">
        Add service to ship or create new service in system
    </div>

    <form name="selectshipservicesForm" method="post" action="/controller">
        <input type="hidden" name="command" value="addshipservicetoship">
        <input type="hidden" name="selectshipservicesForm" value="selectshipservicesForm">
        <select name="selectedship">
            <c:if test="${sship == null}">
                <option value="">Please select the ship</option>
            </c:if>
            <c:if test="${sship != null}">
                <option value="${sship.id}">${sship.id}. ${sship.shipName}</option>
            </c:if>
            <c:forEach var="shipn" items="${allShips}">
                <option value="${shipn.id}">${shipn.id}. ${shipn.shipName}</option>
            </c:forEach>
        </select>
        <br/><br/> <input type="submit" value="Select ship"/>
        <br/><br/><br/><br/>


        <select name="selectedservice">
            <option value="">Please select the service</option>
            <c:forEach var="servicen" items="${allServicesInSystem}">
                <option value="${servicen.id}">${servicen.serviceName}</option>
            </c:forEach>
        </select>
        <br/><br/>
        <label><input type="radio" name="payable" value="0" checked> Free</label>
        <label><input type="radio" name="payable" value="1"> Payable</label>
        <br/>
        <br/> <input type="submit" value="Add service to ship"/>
        <br/>
        <p style="color: red">${errorMessage}</p>
    </form>


<br/><br/><br/><br/>
<form name="addshipservicestosystemForm" method="post" action="/controller">
    <input type="hidden" name="command" value="addshipservicestosystem"/>
    <br/> <input type="submit" value="Create service"/>
</form>
</div>



<br/><br/><br/><br/><br/>
<c:if test="${allServicesOnSelectedShip != null}">

<div class="form-style-2">
    <div class="form-style-2-heading">
        Services that are on this ship
    </div>


    <form method="post" name="deleteservicesfromshipForm" action="/command">
        <input type="hidden" name="command" value="deleteshipservicefromship">
        <input type="hidden" name="selectedship" value="${sship.id}">

        <table border="1" cellpadding="3" cellspacing="0">
            <thead>
            <tr>
                <td>Service name</td>
                <td>Payable</td>
                <td>Delete</td>
            </tr>
            </thead>
            <c:forEach var="servicefromlist" items="${allServicesOnSelectedShip}">
                <tr>
                    <td><c:out value="${servicefromlist.serviceName}"/></td>
                    <td>
                        <c:if test="${servicefromlist.payable == 0}"> <c:out value="free"/> </c:if>
                        <c:if test="${servicefromlist.payable != 0}"> <c:out value="payable"/> </c:if>
                    </td>
                    <td><input type="checkbox" name="shipservice" value="${servicefromlist.serviceId}"></td>
                </tr>
            </c:forEach>
        </table>
        <br/><input type="submit" value="Удалить сервисы с корабля">
    </form>

    </c:if>
</div>
</body>
</html>
