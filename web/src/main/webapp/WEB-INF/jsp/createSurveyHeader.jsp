<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>${requestScope.survey.name}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.CREATE_SURVEY_HEADER_COMMAND}">

    <label for="name">Name:</label><br/>
    <input id="name" type="text" name="${Util.SURVEY_NAME}"><br/>

    <label for="category">Category:</label><br/>
    <input id="category" type="text" name="${Util.SURVEY_CATEGORY}"><br/>

    <label for="description">Description:</label><br/>
    <textarea id="description" name="${Util.SURVEY_DESCRIPTION}" rows="7" cols="44"></textarea><br/>

    <label for="instructions">Instructions:</label><br/>
    <textarea id="instructions" name="${Util.SURVEY_INSTRUCTIONS}" rows="7" cols="44"></textarea><br/>

    <label for="image_url">Image url:</label><br/>
    <input id="image_url" type="url" name="${Util.SURVEY_IMAGE_URL}"><br/>

    <input type="submit" value="next">
</form>
</body>
</html>