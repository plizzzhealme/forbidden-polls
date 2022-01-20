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
<p>${requestScope.survey.name}</p>

<p>${requestScope.survey.description}</p>

<c:choose>
    <c:when test="${sessionScope.survey != null}">
        <c:set var="button_text" value="next"/>
        <c:set var="button_command" value="next_question"/>
    </c:when>
    <c:otherwise>
        <c:set var="button_text" value="start"/>
        <c:set var="button_command" value="${Util.START_SURVEY_COMMAND}"/>
    </c:otherwise>
</c:choose>

<c:if test="${sessionScope.survey != null}">
    <c:set var="i" value="${sessionScope.question_index}"/>

    <c:set var="question" value="${sessionScope.survey.questions[sessionScope.question_index]}"/>

    <c:out value="${question.body}"/>

    <c:set var="options" value="${question.options}"/>

    <c:forEach var="option" items="${options}">
        <p>${option.body}</p>
    </c:forEach>

</c:if>
<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${button_command}">
    <input type="hidden" name="${Util.SURVEY_ID}" value="${requestScope.survey.id}">
    <input type="submit" value="${button_text}">
</form>


</body>
</html>