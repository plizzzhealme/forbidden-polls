<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <meta charset="UTF-8">
    <title>${survey_categories}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../jspf/header.jspf" %>

<div class="grid-container-1">
    <div class="grid-item">${select_category_message}:</div>
</div>

<div class="grid-container-5">
    <c:forEach var="category" items="${requestScope.category_list}">
        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORY_PAGE_COMMAND}&${Util.CATEGORY}=${category}">
                    ${category}
            </a>
        </div>
    </c:forEach>
</div>

<div class="grid-container-1">
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a>
    </div>
</div>
</body>
</html>