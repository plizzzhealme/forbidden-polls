<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html lang="ee">
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>

<c:set var="questions" value="${sessionScope.new_survey.questions}"/>

<form action="controller" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.EDIT_SURVEY_COMMAND}">

    <p>
        <label for="edit"></label>
        <select id="edit" name="edit">
            <optgroup label="${survey_header}">
                <option value="header">${edit_survey_header}</option>
            </optgroup>

            <optgroup label="${question}">
                <c:forEach var="q" items="${questions}">
                    <option value="${q.index}">${q.index + 1}. ${q.body}</option>
                </c:forEach>
            </optgroup>
        </select>
        <input class="btn" type="submit" value="${edit}">
    </p>
</form>

<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_SURVEY_COMMAND}">
    <input class="btn" type="submit" value="${add}">
</form>
</body>
</html>
