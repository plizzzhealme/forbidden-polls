<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${requestScope.survey.name}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>
<p>${requestScope.survey.name}</p>
<p>${created}: ${requestScope.survey.creationDate}</p>
<p>${requestScope.survey.description}</p>
<p>${requestScope.survey.instructions}</p>
<img src="${requestScope.survey.imageUrl}" alt=""/>

<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.START_SURVEY_COMMAND}">
    <input type="hidden" name="${Util.SURVEY_ID}" value="${requestScope.survey.id}">
    <input type="submit" value="${start_survey}">
</form>

</body>
</html>