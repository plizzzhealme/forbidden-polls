<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${authorization}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.SIGN_IN_COMMAND}">

    <div class="grid-container-1">
        <div class="grid-item"><label for="email">${email}:</label></div>
        <div class="grid-item"><input class="classic" id="email" type="email" name="${Util.USER_EMAIL}"></div>

        <div class="grid-item"><label for="password">${password}:</label></div>
        <div class="grid-item"><input class="classic" id="password" type="password" name="${Util.USER_PASSWORD}"></div>
    </div>

    <div class="grid-container-2">
        <div class="grid-item">
            <input class="classic" type="submit" value="${sign_in}">
        </div>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_HOME_PAGE_COMMAND}">${back}</a>
        </div>
    </div>
</form>
</body>
</html>