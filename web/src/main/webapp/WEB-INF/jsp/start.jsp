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

<form action="controller" method="get" class="st">
    <input type="hidden" name="command" value="to_sign_in_page"/>

    <input type="submit" value="${sign_in}"/>
</form>

<form action="controller" method="get" class="st">
    <input type="hidden" name="command" value="to_sign_up_page"/>

    <input type="submit" value="${sign_up}"/>
</form>
</body>
</html>