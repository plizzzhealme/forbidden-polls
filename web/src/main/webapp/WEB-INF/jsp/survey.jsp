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

<%-- before survey start --%>
<c:if test="${sessionScope.survey == null}">
    <p>${requestScope.survey.name}</p>
    <p>${requestScope.survey.description}</p>

    <c:set var="submit_button_text" value="${start_survey}"/>
    <c:set var="submit_button_command" value="${Util.START_SURVEY_COMMAND}"/>
</c:if>


<%-- after survey was started --%>
<c:if test="${sessionScope.survey != null}">
    <c:set var="i" value="${sessionScope.question_index}"/>
    <c:set var="question" value="${sessionScope.survey.questions[i]}"/>
    <c:set var="options" value="${question.options}"/>

    <c:set var="submit_button_text" value="${answer}"/>
    <c:set var="submit_button_command" value="${Util.ANSWER_COMMAND}"/>
</c:if>

<%-- print question --%>
<p><c:out value="${question.body}"/></p>

<form action="${Util.CONTROLLER}" method="post">

    <%-- print options --%>
    <c:forEach var="option" items="${options}">
        <p><label>
            <input type="radio" name="option" value="${option.id}"> ${option.body}
        </label></p>
    </c:forEach>


    <input type="hidden" name="${Util.COMMAND}" value="${submit_button_command}">
    <input type="hidden" name="${Util.SURVEY_ID}" value="${requestScope.survey.id}">
    <input type="submit" value="${submit_button_text}">
</form>


</body>
</html>