<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="io.github.plizzzhealme.controller.util.Util" %>
<!DOCTYPE html>
<html xml:lang="eu">
<head>
    <title>404</title>
</head>
<body>
<p>
    Page is not found.
    <a href="${pageContext.request.contextPath}/${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_HOME_PAGE_COMMAND}">
        To home page
    </a>
</p>
</body>
</html>