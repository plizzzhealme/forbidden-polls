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
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<p>${select_category_message}:</p>

<c:forEach var="category" items="${requestScope.category_list}">
    <p>
        <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORY_PAGE_COMMAND}&${Util.CATEGORY}=${category}">
                ${category}
        </a>
    </p>
</c:forEach>

<p><a class="btn" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a></p>
</body>
</html>