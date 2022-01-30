<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../jspf/localization.jspf" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ee">
<head>
    <meta content='text/html; charset=UTF-8'/>
    <title>${requestScope.survey.name}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.CREATE_SURVEY_HEADER_COMMAND}">

    <p>
        <label for="name">${survey_title}:</label><br/>
        <input id="name" type="text" name="${Util.SURVEY_NAME}">
    </p>

    <p>
        <label for="category">${survey_category}:</label><br/>
        <input id="category" type="text" name="${Util.SURVEY_CATEGORY}">
    </p>

    <p>
        <label for="description">${survey_description}:</label><br/>
        <textarea id="description" name="${Util.SURVEY_DESCRIPTION}" rows="7" cols="44"></textarea>
    </p>

    <p>
        <label for="instructions">${survey_instructions}:</label><br/>
        <textarea id="instructions" name="${Util.SURVEY_INSTRUCTIONS}" rows="7" cols="44"></textarea>
    </p>

    <p>
        <label for="image_url">${survey_image_url}:</label><br/>
        <input id="image_url" type="url" name="${Util.SURVEY_IMAGE_URL}">
    </p>

    <p>
        <input type="submit" value="${add_survey_header}">
    </p>
</form>
</body>
</html>