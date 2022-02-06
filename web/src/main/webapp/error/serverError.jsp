<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="io.github.plizzzhealme.controller.util.Util" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <title>Error</title>
</head>
<body>
<p>
    Internal server error.
    <a href="${pageContext.request.contextPath}/${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_HOME_PAGE_COMMAND}">
        To home page
    </a>
</p>
</body>
</html>