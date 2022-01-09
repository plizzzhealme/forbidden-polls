<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="localization.jsp" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <title>${project_name}</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">


</head>
<body>
<jsp:include page="header.jsp"/>

<p>${start_page_message}</p>

<form action="controller" method="get" class="st">
    <input type="hidden" name="command" value="to_authorization_page"/>

    <input type="submit" value="${sign_in}"/>
</form>

<form action="controller" method="get" class="st">
    <input type="hidden" name="command" value="to_registration_page"/>

    <input type="submit" value="${sign_up}"/>
</form>
</body>
</html>