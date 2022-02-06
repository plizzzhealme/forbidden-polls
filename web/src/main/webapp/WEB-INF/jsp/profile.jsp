<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${profile}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
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
    <p>
        <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_ADD_SURVEY_HEADER_PAGE_COMMAND}">
                ${create_survey}
        </a>
    </p>
</c:if>

<a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND}">${passed_surveys}</a>


<a class="btn" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.SIGN_OUT_COMMAND}">${sign_out}</a>
</body>
</html>