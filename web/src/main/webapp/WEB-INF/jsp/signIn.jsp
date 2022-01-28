<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
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

<c:if test="${error_message == Util.EMPTY_FIELDS_ERROR}">
    <c:set var="error" value="${empty_fields}"/>
</c:if>

<c:if test="${error_message == Util.INVALID_CREDENTIALS_ERROR}">
    <c:set var="error" value="${invalid_credentials}"/>
</c:if>

<p>${error}</p>

<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.SIGN_IN_COMMAND}">

    <label for="email">${email}:</label><br/>
    <input id="email" type="email" name="${Util.USER_EMAIL}"><br/>

    <label for="password">${password}:</label><br/>
    <input id="password" type="password" name="${Util.USER_PASSWORD}"><br/>

    <input type="submit" value="${sign_in}">
</form>

<p>
    <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_START_PAGE_COMMAND}">
        ${back}
    </a>
</p>
</body>
</html>