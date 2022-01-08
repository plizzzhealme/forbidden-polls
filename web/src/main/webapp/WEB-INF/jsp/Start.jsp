<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <%-- localization section --%>
    <fmt:setLocale value="${sessionScope.locale}"/>

    <fmt:setBundle basename="locale" var="locale"/>

    <fmt:message bundle="${locale}" key="project-name" var="project"/>
    <fmt:message bundle="${locale}" key="start-page.message" var="message"/>
    <fmt:message bundle="${locale}" key="start-page.authorization-button" var="auth"/>
    <fmt:message bundle="${locale}" key="start-page.registration-button" var="reg"/>

    <title>${project}</title>
</head>
<body>
<jsp:include page="Header.jsp"/>

<p>${message}</p>

<form action="controller" class="st">
    <input type="hidden" name="command" value="to_authorization_page"/>
    <input type="submit" value=${auth}/>
</form>

<form action="controller" class="st">
    <input type="hidden" name="command" value="to_registration_page"/>
    <input type="submit" value=${reg}/>
</form>
</body>
</html>