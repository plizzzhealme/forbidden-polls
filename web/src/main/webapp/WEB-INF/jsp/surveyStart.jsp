<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>Survey</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>
<p>
    "${requestScope.survey.name}"
</p>

<c:set var="button_text"/>
<c:set var="button_command"/>


<c:choose>
    <c:when test="${requestScope.status} == 1">
        <c:set var="button_text" value="next"/>
        <c:set var="button_command" value="next_question"/>
    </c:when>
    <c:otherwise>
        <c:set var="button_text" value="start"/>
        <c:set var="button_command" value="to_survey_start_page"/>
    </c:otherwise>
</c:choose>


<form action="controller" method="post">
    <input type="hidden" name="command" value="${button_command}">
    <input type="hidden" name="survey" value="${requestScope.survey.id}">
    <input type="submit" value="start">
</form>


</body>
</html>