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
    <input type="hidden" name="${Util.COMMAND}" value="${Util.SEARCH_GENERAL_STATISTICS_COMMAND}">

    <div class="grid-container-1">
        <div class="grid-item"><label for="title">${survey_title}:</label></div>
        <div class="grid-item"><input class="classic" id="title" type="search" name="${Util.SURVEY_TITLE}"></div>

        <div class="grid-item"><label for="category">${category}:</label></div>
        <div class="grid-item"><input class="classic" id="category" type="search" name="${Util.CATEGORY}"></div>
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
    <c:forEach var="survey" items="${requestScope.survey_list}">
        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_GENERAL_STATISTICS_PAGE_COMMAND}&${Util.SURVEY_ID}=${survey.id}">
                    ${survey.name}
            </a>
        </div>
    </c:forEach>
</div>

</body>
</html>