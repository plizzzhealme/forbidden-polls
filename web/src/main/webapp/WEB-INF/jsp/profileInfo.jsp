<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>${profile_info}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<div>
    <%-- name --%>
    <p><c:out value="${name}: ${requestScope.user.name}"/></p>

    <%-- email --%>
    <p><c:out value="${email}: ${requestScope.user.email}"/></p>

    <%-- gender --%>
    <p><c:choose>
        <c:when test="${requestScope.user.gender == Util.MALE}">
            <c:out value="${gender}:  ${male}"/>
        </c:when>
        <c:when test="${requestScope.user.gender == Util.FEMALE}">
            <c:out value="${gender}:  ${female}"/>
        </c:when>
        <c:otherwise>
            <c:out value="${gender}:  ${other}"/>
        </c:otherwise>
    </c:choose></p>

    <%-- role --%>
    <p><c:choose>
        <c:when test="${requestScope.user.userRole == Util.ADMIN}">
            <c:out value="${role}:  ${admin}"/>
        </c:when>
        <c:otherwise>
            <c:out value="${role}:  ${user}"/>
        </c:otherwise>
    </c:choose></p>

    <%-- birthday --%>
    <p><c:out value="${birthday}: ${requestScope.user.birthday}"/></p>

    <%-- country --%>
    <p><c:out value="${country}: ${requestScope.user.country}"/></p>
</div>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_PAGE_COMMAND}">${back}</a></p>
</body>
</html>