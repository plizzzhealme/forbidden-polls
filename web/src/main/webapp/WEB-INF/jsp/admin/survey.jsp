<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>
<c:set var="s" value="${requestScope.survey}"/>

<%--suppress ELValidationInspection --%>
<c:forEach var="q" items="${s.questions}">
    <div class="grid-container-1">
        <div class="grid-item">${q.body}</div>
    </div>

    <c:set var="voicesCount" value="0"/>
    <c:forEach var="o" items="${q.options}">
        <c:set var="voicesCount" value="${voicesCount + o.count}"/>
    </c:forEach>

    <div class="grid-container-2">
        <c:forEach var="o" items="${q.options}">
            <div class="grid-item">${o.body}</div>
            <div class="grid-item">${o.count} (${o.count / voicesCount * 100}%)</div>
        </c:forEach>
    </div>
</c:forEach>

<div class="grid-container-1">
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SEARCH_SURVEY_PAGE_COMMAND}">
            ${back}
        </a>
    </div>
</div>
</body>
</html>