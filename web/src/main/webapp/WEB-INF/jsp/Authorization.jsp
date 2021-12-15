<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authorization</title>
</head>
<body>
<h2>Authorization</h2>
<form action="controller" method="post">

    <br/>

    <input type="hidden" name="command" value="authorization">
    <label>
        Email: <input type="email" name="email">
    </label>
    <label>
        Password: <input type="password" name="password">
    </label>
    <input type="submit" value="login">
</form>
</body>
</html>

