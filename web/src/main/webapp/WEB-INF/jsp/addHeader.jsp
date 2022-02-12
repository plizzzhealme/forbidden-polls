<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../jspf/localization.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ee">
<head>
    <meta content='text/html; charset=UTF-8'/>
    <title>${requestScope.survey.name}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>

<c:set var="survey" value="${sessionScope.new_survey}"/>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_HEADER_COMMAND}">

    <div class="grid-container-1">
        <div class="grid-item"><label for="name">${survey_title}:</label></div>
        <div class="grid-item">
            <input class="classic" id="name" name="${Util.SURVEY_NAME}" type="text" value="${survey.name}">
        </div>

        <div class="grid-item"><label for="category">${survey_category}:</label></div>
        <div class="grid-item">
            <input class="classic" id="category" type="text" name="${Util.SURVEY_CATEGORY}" value="${survey.category}">
        </div>

        <div class="grid-item"><label for="description">${survey_description}:</label></div>
        <div class="grid-item">
            <textarea class="classic"
                      cols="44"
                      id="description"
                      name="${Util.SURVEY_DESCRIPTION}"
                      rows="7">${survey.description}</textarea>
        </div>

        <div class="grid-item"><label for="instructions">${survey_instructions}:</label></div>
        <div class="grid-item">
            <textarea class="classic"
                      cols="44"
                      id="instructions"
                      name="${Util.SURVEY_INSTRUCTIONS}"
                      rows="7">${survey.instructions}</textarea>
        </div>

        <div class="grid-item"><label for="image_url">${survey_image_url}:</label></div>
        <div class="grid-item">
            <input class="classic" id="image_url" type="url" name="${Util.SURVEY_IMAGE_URL}" value="${survey.imageUrl}">
        </div>
    </div>

    <div class="grid-container-2">
        <div class="grid-item"><input class="classic" type="submit" value="${add_survey_header}"></div>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a>
        </div>
    </div>
</form>
</body>
</html>