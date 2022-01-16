<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <title>${project_name}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">


</head>
<body>
<jsp:include page="../jspf/header.jspf"/>

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