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
    <input id="name" type="text"><br/>

    <label for="description">Description:</label><br/>
    <textarea id="description" name="description" rows="7" cols="44"></textarea><br/>

    <label for="instructions">Instructions:</label><br/>
    <textarea id="instructions" name="instructions" rows="7" cols="44"></textarea><br/>

    <label for="image_url">Image url:</label><br/>
    <input id="image_url" type="url"><br/>

    <input type="submit">
</form>

</body>
</html>