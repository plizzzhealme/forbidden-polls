<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="io.github.plizzzhealme.controller.util.Util" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>Fatal</title>
</head>
<body>
<p>
    Fatal error.
    <a href="${pageContext.request.contextPath}/${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_HOME_PAGE_COMMAND}">
        To home page
    </a>
</p>
</body>
</html>