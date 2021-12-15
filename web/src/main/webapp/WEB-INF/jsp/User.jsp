<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="eu" xml:lang="eu">

<head>
    <title>Title</title>
</head>
<body>
<c:out value="${sessionScope.user.email}"/>
</body>
</html>
