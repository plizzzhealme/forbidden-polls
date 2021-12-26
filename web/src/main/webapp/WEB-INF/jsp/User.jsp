<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="eu" xml:lang="eu">

<head>
    <title>home</title>
</head>
<body>
<c:out value="Email: ${sessionScope.user.email}"/><br/>
<c:out value="Name: ${sessionScope.user.name}"/><br/>
<c:out value="Gender: ${sessionScope.user.gender}"/><br/>
<c:out value="Country: ${sessionScope.user.country}"/><br/>
<c:out value="Role: ${sessionScope.user.userRole}"/><br/>
</body>
</html>
