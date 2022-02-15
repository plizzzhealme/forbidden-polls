<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>

<!DOCTYPE>
<html xml:lang="eu">
<meta charset="UTF-8">
<head>
    <title>${project_name}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="grid-container-1">
    <div class="grid-item"><p>${start_page_message}</p></div>
</div>

<div class="grid-container-2">
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SIGN_IN_PAGE_COMMAND}">${sign_in}</a>
    </div>
    <div class="grid-item">
        <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_SIGN_UP_PAGE_COMMAND}">${sign_up}</a>
    </div>
</div>

</body>
</html>