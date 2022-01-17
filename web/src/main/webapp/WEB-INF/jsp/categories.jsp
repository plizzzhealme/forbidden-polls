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

<c:forEach var="category" items="${requestScope.categories}">
    <p>
        <a href="controller?command=to_category_page&category_id=${category.id}&category_name=${category.name}">
                ${category.name}
        </a>
    </p>
</c:forEach>

<p><a href="controller?command=to_user_page">${back}</a></p>
</body>
</html>
