<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html lang="ee">
<head>
    <title></title>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>

<c:set var="questions" value="${sessionScope.new_survey.questions}"/>

<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.EDIT_SURVEY_COMMAND}">

    <div class="grid-container-1">
        <div class="grid-item">
            <label for="edit"></label>
            <select class="classic" id="edit" name="edit">
                <optgroup label="${survey_header}">
                    <option value="header">${edit_survey_header}</option>
                </optgroup>

                <optgroup label="${question}">
                    <c:forEach var="q" items="${questions}">
                        <option value="${q.index}">${q.index + 1}. ${q.body}</option>
                    </c:forEach>
                </optgroup>
            </select>
        </div>
    </div>

    <div class="grid-container-2">
        <div class="grid-item"><input class="classic" type="submit" value="${edit}"></div>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.ADD_SURVEY_COMMAND}">${add}</a>
        </div>
    </div>
</form>
</body>
</html>