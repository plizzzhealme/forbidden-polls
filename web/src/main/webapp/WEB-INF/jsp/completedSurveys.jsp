<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <meta charset="UTF-8">
    <title>Completed</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>


<c:forEach var="survey" items="${requestScope.survey_list}">
    <p><c:out value="${survey.name}"/></p>
</c:forEach>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a></p>
</body>
</html>