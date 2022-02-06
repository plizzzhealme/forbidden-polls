<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <meta charset="UTF-8">
    <title>Completed</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>

<div class="grid-container-1">
    <c:forEach var="survey" items="${requestScope.survey_list}">
        <div class="grid-item">${survey.name}</div>
    </c:forEach>
</div>

<div class="grid-container-1">
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a>
    </div>
</div>
</body>
</html>