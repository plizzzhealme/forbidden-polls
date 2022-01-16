<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>home</title>
</head>
<body>
<p>
    <jsp:include page="../jspf/header.jspf"/>
</p>

<p><a href="controller?command=to_profile_page">see profile</a></p>
<p><a href="controller?command=to_categories_page">see survey categories</a></p>

<form action="controller" method="post">
    <input type="hidden" name="command" value="sign_out">

    <input type="submit" value="${sign_out}">
</form>
</body>
</html>