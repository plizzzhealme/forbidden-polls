<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>${authorization}</title>
</head>
<body>

<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<c:set var="error_message" value="${requestScope.error_message}"/>

<c:if test="${error_message == ControllerUtil.EMPTY_FIELDS_ERROR}">
    <c:set var="error" value="${empty_fields}"/>
</c:if>

<c:if test="${error_message == ControllerUtil.INVALID_CREDENTIALS_ERROR}">
    <c:set var="error" value="${invalid_credentials}"/>
</c:if>

<p><c:out value="${error}"/></p>

<form action="controller" method="post">
    <input type="hidden" name="command" value="sign_in">

    <label for="email">${email}:</label><br/>
    <input id="email" type="email" name="email"><br/>

    <label for="password">${password}:</label><br/>
    <input id="password" type="password" name="password"><br/>

    <input type="submit" value="${sign_in}">
</form>

<p><a href="controller?command=to_start_page">${back}</a></p>
</body>
</html>