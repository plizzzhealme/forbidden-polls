<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <title>Error</title>
</head>
<body>
<p><c:out value="${sessionScope.error_message}"/></p>
<p>//todo add kitten image for Olga loyalty</p>
</body>
</html>