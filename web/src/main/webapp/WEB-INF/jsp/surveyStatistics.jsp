<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: analB
  Date: 07.02.2022
  Time: 01:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../jspf/header.jspf" %>
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
</body>
</html>
