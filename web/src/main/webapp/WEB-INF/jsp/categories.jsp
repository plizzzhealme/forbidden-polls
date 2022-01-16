<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: analB
  Date: 16.01.2022
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="category" items="${requestScope.categories}">
    <p><a href="controller?command=to_category_page&category_id=${category.id}"><c:out value="${category.name}"/></a>
    </p>
</c:forEach>
</body>
</html>
