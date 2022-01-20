<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>

<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>${project_name}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<p>${start_page_message}</p>

<form action="${Util.CONTROLLER}" method="post" class="st">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.TO_SIGN_IN_PAGE_COMMAND}"/>

    <input type="submit" value="${sign_in}"/>
</form>

<form action="${Util.CONTROLLER}" method="post" class="st">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.TO_SIGN_UP_PAGE_COMMAND}"/>

    <input type="submit" value="${sign_up}"/>
</form>
</body>
</html>