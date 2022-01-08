<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="Localization.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${authorization}</title>
</head>
<body>
<jsp:include page="Header.jsp"/>

<form action="controller" method="post">
    <input type="hidden" name="command" value="authorization">

    <label for="email">${email}:</label><br/>
    <input id="email" type="email" name="email"><br/>

    <label for="password">${password}:</label><br/>
    <input id="password" type="password" name="password"><br/>

    <input type="submit" value="${sign_in}">
</form>
<br/>

<a href="controller?command=to_start_page">[${to_main_page}]</a>
</body>
</html>