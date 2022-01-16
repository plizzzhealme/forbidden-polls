<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>home</title>
</head>
<body>
<p>
    <jsp:include page="../jspf/header.jspf"/>
</p>

<div>
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

    <%-- birthday --%>
    <c:out value="${birthday}: ${requestScope.user.birthday}"/>

    <%-- country --%>
    <p><c:out value="${country}: ${requestScope.user.country}"/></p>
</div>

<p><a href="controller?command=to_user_page">${back}</a></p>
</body>
</html>