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
<%@include file="../jspf/header.jspf" %>

<div class="grid-container-1">
    <div class="grid-item">
        <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_PROFILE_INFO_PAGE_COMMAND}">${profile_info}</a>
    </div>

    <c:if test="${sessionScope.user_role == Util.ADMIN}">
        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_ADD_HEADER_PAGE_COMMAND}">${create_survey}</a>
        </div>

        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SEARCH_GENERAL_STATISTICS_PAGE_COMMAND}">
                    ${search_surveys}
            </a>
        </div>

        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SEARCH_USER_PAGE_COMMAND}">
                    ${search_users}
            </a>
        </div>
    </c:if>

    <c:if test="${sessionScope.user_role == Util.USER}">
        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_CATEGORIES_PAGE_COMMAND}">${survey_categories}</a>
        </div>

        <div class="grid-item">
            <a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND}">${passed_surveys}</a>
        </div>
    </c:if>

    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.SIGN_OUT_COMMAND}">${sign_out}</a>
    </div>
</div>
</body>
</html>