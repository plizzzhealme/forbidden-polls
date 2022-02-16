<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${requestScope.survey.name}</title>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>

<div class="grid-container-1">
    <div class="grid-item">${requestScope.survey.name}</div>
    <div class="grid-item">${created}: ${requestScope.survey.creationDate}</div>
    <div class="grid-item">${requestScope.survey.description}</div>
    <div class="grid-item">${requestScope.survey.instructions}</div>
    <div class="grid-item"><img src="${requestScope.survey.imageUrl}" alt=""/></div>
</div>

<div class="grid-container-2">
    <div class="grid-item">
        <a class="classic"
           href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TAKE_SURVEY_COMMAND}&${Util.SURVEY_ID}=${requestScope.survey.id}">
            ${start_survey}
        </a>
    </div>

    <div class="grid-item">
        <a class="classic"
           href="${Util.REDIRECT_URL_PATTERN}${Util.TO_CATEGORY_PAGE_COMMAND}&${Util.CATEGORY}=${requestScope.survey.category}">
            ${back}
        </a>
    </div>
</div>
</body>
</html>