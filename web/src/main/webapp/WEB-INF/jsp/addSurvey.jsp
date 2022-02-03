<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE>
<html lang="ee">
<head>

    <title>Title</title>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<c:set var="questions" value="${sessionScope.new_survey.questions}"/>

<form action="controller" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.EDIT_SURVEY_COMMAND}">

    <p>
        <label for="edit">Edit</label>
        <select id="edit" name="edit">
            <optgroup label="Header">
                <option value="header">Edit survey header</option>
            </optgroup>

            <optgroup label="Questions">
                <c:forEach var="q" items="${questions}">
                    <option value="${q.index}">${q.index + 1}. ${q.body}</option>
                </c:forEach>
            </optgroup>
        </select>
    </p>

    <p>
        <input type="submit" value="edit">
    </p>
</form>

<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_SURVEY_COMMAND}">
    <input type="submit" value="save">
</form>
</body>
</html>
