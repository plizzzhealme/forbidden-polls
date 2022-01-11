<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="localization.jsp" %>
<!DOCTYPE html>
<html lang="eu" xml:lang="eu">

<head>
    <title>home</title>
</head>

<body>
<p>
    <jsp:include page="header.jsp"/>
</p>
<p><c:out value="Hello! Now you are logged in, and you can take our surveys."/></p>

<%-- name --%>
<p><c:out value="${name}: ${requestScope.user.name}"/></p>

<%-- email --%>
<p><c:out value="${email}: ${requestScope.user.email}"/></p>

<%-- gender --%>
<p><c:choose>
    <c:when test="${requestScope.user.gender == 'male'}">
        <c:out value="${gender}:  ${male}"/>
    </c:when>
    <c:when test="${requestScope.user.gender == 'female'}">
        <c:out value="${gender}:  ${female}"/>
    </c:when>
    <c:otherwise>
        <c:out value="${gender}:  ${other}"/>
    </c:otherwise>
</c:choose></p>

<%-- role --%>
<p><c:choose>
    <c:when test="${requestScope.user.userRole == 'user'}">
        <c:out value="${role}:  ${user}"/>
    </c:when>
    <c:otherwise>
        <c:out value="${role}:  ${admin}"/>
    </c:otherwise>
</c:choose></p>

<%-- country --%>
<p><c:out value="${country}: ${requestScope.user.country}"/></p>
</body>
</html>