<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>

<form action="controller" method="get">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.SEARCH_USER_COMMAND}">

    <div class="grid-container-1">
        <div class="grid-item"><label for="name">${name}:</label></div>
        <div class="grid-item"><input class="classic" id="name" type="search" name="${Util.USER_NAME}"></div>

        <div class="grid-item"><label for="email">${email}:</label></div>
        <div class="grid-item"><input class="classic" id="email" type="search" name="${Util.USER_EMAIL}"></div>

        <div class="grid-item"><label for="gender">${gender}:</label></div>
        <div class="grid-item"><input class="classic" id="gender" type="search" name="${Util.USER_GENDER}"></div>
    </div>

    <div class="grid-container-2">
        <div class="grid-item">
            <input class="classic" type="submit" value="search">
        </div>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a>
        </div>
    </div>
</form>

<div class="grid-container-1">
    <c:forEach var="user" items="${requestScope.user_list}">
        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_USER_PAGE_COMMAND}&${Util.USER_ID}=${user.id}">
                id${user.id} ${user.email}
            </a>
        </div>
    </c:forEach>

    <div class="grid-item">
        <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.SEARCH_NEXT_USER_COMMAND}">next</a>
    </div>

    <div class="grid-item">
        <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.SEARCH_PREVIOUS_USER_COMMAND}">previous</a>
    </div>
</div>

</body>
</html>