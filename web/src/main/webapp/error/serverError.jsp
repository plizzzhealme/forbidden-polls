<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <title>Error</title>
</head>
<body>
<p>Internal server error. <a href="${pageContext.request.contextPath}/controller?command=to_start_page">To start
    page.</a></p>
</body>
</html>