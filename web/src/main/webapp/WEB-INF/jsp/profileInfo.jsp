<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${profile_info}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>

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
            <c:otherwise>${user}</c:otherwise>
        </c:choose>
    </div>

    <div class="grid-item">${birthday}</div>
    <div class="grid-item">${requestScope.user.birthday}</div>

    <div class="grid-item">${country}</div>
    <div class="grid-item">${requestScope.user.country}</div>
</div>

<div class="grid-container-2">
    <div class="grid-item"><a class="classic" href="controller?command=to_edit_profile_info_page">${edit}</a></div>
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a>
    </div>
</div>
</body>
</html>