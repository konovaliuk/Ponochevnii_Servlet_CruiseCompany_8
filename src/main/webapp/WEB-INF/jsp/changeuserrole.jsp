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
    <title><fmt:message key="message.changeuserrole.title"/></title>
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

<h1 style="color: red">changeuserrole.jsp</h1>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <fmt:message key="message.changeuserrole.title"/>
    </div>
    <br/>
    <form name="changeuserroleForm" method="post" action="/controller">
        <input type="hidden" name="command" value="changeuserrole"/>
        <input type="hidden" name="changeuserroleForm" value="changeuserroleForm"/>
        <br/><br/><fmt:message key="message.changeuserrole.userlogin"/>
        <br/><input type="text" name="login" value="" autofocus/>
        <br/><br/><fmt:message key="message.changeuserrole.userlpassword"/>
        <br/><input type="password" name="password" value=""/>
        <br/><br/>

        <fmt:message key="message.changeuserrole.newuserrole"/>
        <br/><select name="selectedrole">
            <option value=""><fmt:message key="message.changeuserrole.selectnewrole"/></option>
            <c:forEach var="rolen" items="${allRoles}">
                <option value="${rolen.role}">${rolen.role}</option>
            </c:forEach>
        </select>

        <br/>
        <br/> <input type="submit" value="<fmt:message key="message.changeuserrole.changerolebtn"/>"/>
        <p style="color: red">
            <c:if test="${not empty changeuserroleMessage}"><fmt:message key="${changeuserroleMessage}"/></c:if>
        </p>
    </form>
</div>

</body>
</html>
