<%--
  Created by IntelliJ IDEA.
  User: AutoRun
  Date: 3/15/2021
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>new tournament</title>
</head>
<body>
    <div class="menu">
        <a href="hello-servlet">Hello Servlet</a> |
        <a href="Users">Users</a> |
        <a href="Tournaments">Tournaments</a> |
        <a href="NewQuestion">New questions</a>
    </div>

    <form action="/Tournaments" method="get">
        <div>
            <label>Name of tournament</label>
            <input type="text" name="name" value="" placeholder="">
        </div>
        <div>
            <label>Start date/time</label>
            <input type="datetime-local" name="dataStart" value="" placeholder="">
        </div>
        <div>
            <label>Stop date/time</label>
            <input type="datetime-local" name="dataStop" value="" placeholder="">
        </div>
        <button type="submit" name="submit" value="Save">Save</button>
    </form>
    <div>
</body>
</html>
