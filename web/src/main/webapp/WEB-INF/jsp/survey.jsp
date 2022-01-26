<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>${sessionScope.survey.name}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<c:if test="${sessionScope.survey != null}">
    <c:set var="i" value="${sessionScope.question_index}"/>
    <c:set var="question" value="${sessionScope.survey.questions[i]}"/>
    <c:set var="options" value="${question.options}"/>
</c:if>

<%-- print question --%>
<p><c:out value="${question.body}"/></p>
<p><c:out value="${question.description}"/></p>
<img src="${question.imageUrl}" alt=""/>

<form action="${Util.CONTROLLER}" method="post">

    <%-- print options --%>
    <c:forEach var="option" items="${options}" varStatus="loop">
        <p><label>
            <input type="radio"
                   name="option"
                   value="${loop.index}"
                   <c:if test="${loop.index == 0}">checked</c:if> >${option.body}
        </label></p>
    </c:forEach>

    <input type="hidden" name="${Util.COMMAND}" value="${Util.ANSWER_COMMAND}">
    <input type="submit" value="${answer}">
</form>
</body>
</html>