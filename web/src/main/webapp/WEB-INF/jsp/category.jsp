<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>Title</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<p><c:out value="Surveys from category: ${requestScope.category_name}"/></p>

<c:forEach var="survey" items="${requestScope.surveys}">
    <p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SURVEY_START_PAGE_COMMAND}&${Util.SURVEY_ID}=${survey.id}">
        <c:out value="${survey.name}"/>
    </a></p>
</c:forEach>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORIES_PAGE_COMMAND}">${back}</a></p>
</body>
</html>
