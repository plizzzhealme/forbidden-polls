<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: analB
  Date: 07.02.2022
  Time: 01:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="s" value="${requestScope.survey}"/>

<c:forEach var="q" items="${s.questions}">
    ${q.body}
    <c:forEach var="o" items="${q.options}">
        ${o.body} ${o.count}
    </c:forEach>
</c:forEach>
</body>
</html>
