<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <meta charset="UTF-8">
    <title>${requestScope.category_name}</title>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>

<div class="grid-container-1">
    <div class="grid-item">${category}: ${requestScope.category_name}</div>
</div>

<div class="grid-container-1">
    <c:forEach var="survey" items="${requestScope.survey_list}">
        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_HEADER_PAGE_COMMAND}&${Util.SURVEY_ID}=${survey.id}">
                <c:out value="${survey.name}"/>
            </a>
        </div>
    </c:forEach>
</div>

<div class="grid-container-1">
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORIES_PAGE_COMMAND}">${back}</a>
    </div>
</div>
</body>
</html>