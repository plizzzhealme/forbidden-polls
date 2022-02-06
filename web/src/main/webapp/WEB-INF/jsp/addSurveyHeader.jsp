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
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<c:set var="survey" value="${sessionScope.new_survey}"/>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_SURVEY_HEADER_COMMAND}">

    <p>
        <label for="name">${survey_title}:</label><br/>
        <input id="name" type="text" name="${Util.SURVEY_NAME}" value="${survey.name}">
    </p>

    <p>
        <label for="category">${survey_category}:</label><br/>
        <input id="category" type="text" name="${Util.SURVEY_CATEGORY}" value="${survey.category}">
    </p>

    <p>
        <label for="description">${survey_description}:</label><br/>
        <textarea id="description"
                  name="${Util.SURVEY_DESCRIPTION}"
                  rows="7"
                  cols="44">${survey.description}</textarea>
    </p>

    <p>
        <label for="instructions">${survey_instructions}:</label><br/>
        <textarea id="instructions"
                  name="${Util.SURVEY_INSTRUCTIONS}"
                  rows="7"
                  cols="44">${survey.instructions}</textarea>
    </p>

    <p>
        <label for="image_url">${survey_image_url}:</label><br/>
        <input id="image_url" type="url" name="${Util.SURVEY_IMAGE_URL}" value="${survey.imageUrl}">
    </p>

    <p>
        <input class="btn" type="submit" value="${add_survey_header}">
    </p>
</form>
</body>
</html>