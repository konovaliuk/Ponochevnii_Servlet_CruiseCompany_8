<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Registred</title>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header-form">
    <div class="header-button-div">
        <form style="float: right" method="POST" action="/controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value="LogOut"/>
        </form>
    </div>

    <div class="header-button-div">
        <form style="float: right" method="post" action="/controller">
            <input type="hidden" name="command" value="myaccount"/>
            <input type="submit" value="My account"/>
        </form>
    </div>

    <div class="header-button-div">
        <form style="float: right" method="post" action="/controller">
            <input type="hidden" name="command" value="mycart"/>
            <input type="submit" value="My cart"/>
        </form>
    </div>

    <div class="header-button-div">
        <form style="float: right" method="post" action="/controller">
            <input type="hidden" name="command" value="mycruises"/>
            <input type="submit" value="My cruises"/>
        </form>
    </div>
</div>

</body>
</html>
