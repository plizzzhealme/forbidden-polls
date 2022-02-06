<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${sessionScope.survey.name}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>

<c:if test="${sessionScope.survey != null}">
    <c:set var="i" value="${sessionScope.question_index}"/>
    <c:set var="current_question" value="${sessionScope.survey.questions[i]}"/>
    <c:set var="options" value="${current_question.options}"/>
</c:if>

<%-- print question --%>
<div class="grid-container-1">
    <div class="grid-item">${current_question.body}</div>
    <div class="grid-item">${current_question.description}</div>
    <div class="grid-item"><img src="${current_question.imageUrl}" alt=""/></div>
</div>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ANSWER_SURVEY_QUESTION_COMMAND}">

    <div class="grid-container-1">
        <c:forEach var="option" items="${options}" varStatus="loop">
            <div class="grid-item">
                <label>
                    <input class="classic" type="radio"
                           name="option"
                           value="${loop.index}"
                           <c:if test="${loop.index == 0}">checked</c:if> >${option.body}
                </label>
            </div>
        </c:forEach>
    </div>

    <div class="grid-container-1">
        <div class="grid-item">
            <input class="classic" type="submit" value="${answer}">
        </div>
    </div>
</form>
</body>
</html>