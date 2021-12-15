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
    <title>Registration</title>
</head>
<body>
<h2>Registration</h2>
<form action="controller">
    <input type="hidden" name="command" value="registration">

    <label >email:<br/>
        <input type="email" name="email">
    </label>

    <label>
        <input type="text" name="name">
    </label>


    <label>
        <input type="password" name="password">
    </label>
    <label>
        <input type="password" name="confirm_password">
    </label>
    <input type="submit" value="login">
</form>
</body>
</html>

