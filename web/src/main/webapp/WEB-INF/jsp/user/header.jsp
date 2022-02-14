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


<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.TAKE_SURVEY_COMMAND}">
    <input type="hidden" name="${Util.SURVEY_ID}" value="${requestScope.survey.id}">

    <div class="grid-container-2">
        <div class="grid-item"><input class="classic" type="submit" value="${start_survey}"></div>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORIES_PAGE_COMMAND}">
                ${back}
            </a>
        </div>
    </div>
</form>

</body>
</html>