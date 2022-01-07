<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <!-- localization section -->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="locale"/>
    <fmt:message bundle="${locale}" key="project-name" var="project"/>
    <fmt:message bundle="${locale}" key="start-page.message" var="message"/>
    <fmt:message bundle="${locale}" key="start-page.authorization-button" var="auth"/>
    <fmt:message bundle="${locale}" key="start-page.registration-button" var="reg"/>

    <title>${project}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<p>
    <a href="controller?command=change_language&locale=ru_RU">ru</a>
    <a href="controller?command=change_language&locale=en_US">us</a>
</p>


<fieldset>
    <legend>${project}:</legend>

    <p>${message}</p>

    <form class="st">
        <input type="hidden" name="command" value="to_authorization_page"/>
        <input type="submit" value=${auth}/>
    </form>
    <form class="st">
        <input type="hidden" name="command" value="to_registration_page"/>
        <input type="submit" value=${reg}/>
    </form>

</fieldset>
</body>
</html>