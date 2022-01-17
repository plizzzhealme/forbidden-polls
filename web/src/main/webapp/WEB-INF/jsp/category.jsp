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

<p><c:out value="surveys from category ${requestScope.category_name}"/></p>

<c:forEach var="survey" items="${requestScope.surveys}">
    <a href="controller?command=to_survey_page&survey_id=${survey.id}">
        <c:out value="${survey.name}"/>
    </a>
</c:forEach>

<p><a href="controller?command=to_categories_page">${back}</a></p>
</body>
</html>
