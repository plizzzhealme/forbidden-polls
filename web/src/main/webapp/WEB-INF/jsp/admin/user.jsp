<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${profile_info}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../../jspf/header.jspf" %>

<div class="grid-container-2">
    <div class="grid-item">${name}</div>
    <div class="grid-item">${requestScope.user.name}</div>

    <div class="grid-item">${email}</div>
    <div class="grid-item">${requestScope.user.email}</div>

    <div class="grid-item">${gender}</div>
    <div class="grid-item">
        <c:choose>
            <c:when test="${requestScope.user.gender == Util.MALE}">${male}</c:when>
            <c:when test="${requestScope.user.gender == Util.FEMALE}">${female}</c:when>
            <c:otherwise>${other}</c:otherwise>
        </c:choose>
    </div>

    <div class="grid-item">${role}</div>
    <div class="grid-item">
        <c:choose>
            <c:when test="${requestScope.user.userRole == Util.ADMIN}">${admin}</c:when>
            <c:otherwise>${requestScope.user.userRole}</c:otherwise>
        </c:choose>
    </div>

    <div class="grid-item">${birthday}</div>
    <div class="grid-item">${requestScope.user.birthday}</div>

    <div class="grid-item">${country}</div>
    <div class="grid-item">${requestScope.user.country}</div>
</div>

<form action="controller" method="post">
    <div class="grid-container-2">
        <c:if test="${requestScope.user.userRole != Util.ADMIN}">
            <input type="hidden" name="${Util.COMMAND}" value="${Util.BLOCK_USER_COMMAND}">
            <input type="hidden" name="${Util.USER_ID}" value="${requestScope.user.id}">
            <div class="grid-item">
                <input class="classic" type="submit" value="Block">
            </div>
        </c:if>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SEARCH_USER_PAGE_COMMAND}">${back}</a>
        </div>
    </div>
</form>
</body>
</html>