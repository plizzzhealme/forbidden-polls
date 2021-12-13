<%--
  Created by IntelliJ IDEA.
  User: analB
  Date: 13.12.2021
  Time: 02:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authorization</title>
</head>
<body>
<h2>Authorize here</h2>

<form action="controller">
    <input type="hidden" name="command" value="authorization">
    <label>
        <input type="email" name="email">
    </label>
    <label>
        <input type="password" name="password">
    </label>
    <input type="submit" value="login">
</form>
</body>
</html>

