<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%-- localization section --%>
    <fmt:setLocale value="${sessionScope.locale}"/>

    <fmt:setBundle basename="locale" var="locale"/>

    <fmt:message bundle="${locale}" key="authorization-page.title" var="title"/>
    <fmt:message bundle="${locale}" key="authorization-page.email" var="email"/>
    <fmt:message bundle="${locale}" key="authorization-page.password" var="password"/>
    <fmt:message bundle="${locale}" key="authorization-page.authorization-button" var="auth"/>
    <fmt:message bundle="${locale}" key="authorization-page.back-button" var="back"/>

    <title>${title}</title>
</head>
<body>
<jsp:include page="Header.jsp"/>

<form action="controller" method="post">
    <input type="hidden" name="command" value="authorization">

    <label for="email">${email}:</label><br/>
    <input id="email" type="email" name="email"><br/>

    <label for="password">${password}:</label><br/>
    <input id="password" type="password" name="password"><br/>

    <input type="submit" value=${auth}>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="to_start_page">
    <input type="submit" value="${back}">
</form>
</body>
</html>

