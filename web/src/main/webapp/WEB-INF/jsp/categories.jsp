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


<c:forEach var="category" items="${requestScope.category_list}">
    <p>
        <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORY_PAGE_COMMAND}&${Util.CATEGORY_ID}=${category.id}&${Util.CATEGORY_NAME}=${category.name}">
                ${category.name}
        </a></p>
</c:forEach>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_USER_PAGE_COMMAND}">${back}</a></p>
</body>
</html>
