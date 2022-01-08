<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="eu" xml:lang="eu">

<head>
    <title>home</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<c:set var="name" value="${sessionScope.user.name}"/>
<p><c:out value="Hello, ${name}! Now you are logged in, and you can take our surveys."/></p>
<c:forEach items="${sessionScope.values()}" var="elem">
    <c:out value="${elem}"/>
</c:forEach>
</body>
</html>