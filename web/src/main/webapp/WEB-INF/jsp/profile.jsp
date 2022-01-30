<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${profile}</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_INFO_PAGE_COMMAND}">
    ${profile_info}
</a></p>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORIES_PAGE_COMMAND}">
    ${survey_categories}
</a></p>

<c:if test="${sessionScope.user_role == Util.ADMIN}">
    <p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_ADD_SURVEY_HEADER_PAGE_COMMAND}">
            ${create_survey}
    </a></p>
</c:if>


<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.SIGN_OUT_COMMAND}">

    <input type="submit" value="${sign_out}">
</form>
</body>
</html>