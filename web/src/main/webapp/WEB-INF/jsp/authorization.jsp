<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="localization.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${authorization}</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<c:set var="error" value="${sessionScope.error_message}"/>

<c:if test="${error == 'empty'}">
    <p>${empty_fields}</p>
</c:if>

<c:if test="${error == 'invalid'}">
    <p>${invalid_credentials}</p>
</c:if>

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