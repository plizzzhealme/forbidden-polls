<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authorization</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="authorization">

    <fieldset>
        <legend>Authorization:</legend>

        <label for="email">Email:</label><br/>
        <input id="email" type="email" name="email"><br/>

        <label for="password">Password:</label><br/>
        <input id="password" type="password" name="password"><br/>

        <input type="submit" value="login">

        <input type="button" onclick="history.back();" value="Back">
    </fieldset>

</form>
</body>
</html>

