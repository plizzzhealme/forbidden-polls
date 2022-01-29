<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <meta charset="UTF-8">
    <title>${requestScope.category_name}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<p><c:out value="${category}: ${requestScope.category_name}"/></p>

<c:forEach var="survey" items="${requestScope.survey_list}">
    <p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SURVEY_BEGIN_PAGE_COMMAND}&${Util.SURVEY_ID}=${survey.id}">
        <c:out value="${survey.name}"/>
    </a></p>
</c:forEach>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORIES_PAGE_COMMAND}">${back}</a></p>
</body>
</html>