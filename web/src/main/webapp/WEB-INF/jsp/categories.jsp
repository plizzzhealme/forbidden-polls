<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>Title</title>
</head>
<body>
<p>
    <jsp:include page="../jspf/header.jspf"/>
</p>

<c:forEach var="category" items="${requestScope.categories}">
    <p>
        <a href="controller?command=to_category_page&category_id=${category.id}&category_name=${category.name}">
                ${category.name}
        </a>
    </p>
</c:forEach>
</body>
</html>
