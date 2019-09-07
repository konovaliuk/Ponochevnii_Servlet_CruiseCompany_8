<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message"/>

<html>
<head>

    <title>Registred</title>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header-form">
    <form class="header-button-left" name="selectshipservicesForm" method="post" action="/controller">
        <input type="hidden" name="command" value="changelanguage">
        <select name="language" onchange="submit()">
            <option value="en" <c:if test="${language == 'en'}">selected</c:if> >En</option>
            <option value="ru" <c:if test="${language == 'ru'}">selected</c:if> >Ru</option>
            <option value="ua" <c:if test="${language == 'ua'}">selected</c:if> >Ua</option>
            <option value="es" <c:if test="${language == 'es'}">selected</c:if> >Es</option>
        </select>
    </form>
    <div class="header-button-div">
        <form style="float: right" method="POST" action="/controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value="<fmt:message key="message.headerregistred.logout"/>"/>
        </form>
    </div>

    <div class="header-button-div">
        <form style="float: right" method="post" action="/controller">
            <input type="hidden" name="command" value="myaccount"/>
            <input type="submit" value="<fmt:message key="message.headerregistred.myaccount"/>"/>
        </form>
    </div>

    <div class="header-button-div">
        <form style="float: right" method="post" action="/controller">
            <input type="hidden" name="command" value="mycart"/>
            <input type="submit" value="<fmt:message key="message.headerregistred.mycart"/>"/>
        </form>
    </div>

    <div class="header-button-div">
        <form style="float: right" method="post" action="/controller">
            <input type="hidden" name="command" value="mycruises"/>
            <input type="submit" value="<fmt:message key="message.headerregistred.mycruises"/>"/>
        </form>
    </div>
</div>

</body>
</html>
