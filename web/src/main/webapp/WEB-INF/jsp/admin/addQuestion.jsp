<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${requestScope.survey.name}</title>
    <script type='text/javascript'>
        function addOptionFields() {
            const number = document.getElementById("options_number").value;
            const container = document.getElementById("options");

            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }

            for (let i = 0; i < number; i++) {
                const inputArea = document.createElement("div");
                inputArea.className = "grid-item";
                const input = document.createElement("input");
                input.type = "text";
                input.name = "option";
                input.className = "classic"

                inputArea.appendChild(input);
                container.appendChild(inputArea);
            }
        }
    </script>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>

<c:set var="edit_question" value="${sessionScope.new_survey.questions[sessionScope.edit_index]}"/>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_QUESTION_COMMAND}">

    <div class="grid-container-1">
        <div class="grid-item"><label for="question">${question}:</label></div>
        <div class="grid-item">
            <textarea id="question"
                      class="classic"
                      name="${Util.QUESTION_BODY}"
                      rows="2"
                      cols="44">${pageScope.edit_question.body}</textarea>
        </div>

        <div class="grid-item"><label for="description">${question_description}:</label></div>
        <div class="grid-item">
            <textarea id="description"
                      class="classic"
                      name="${Util.QUESTION_DESCRIPTION}"
                      rows="7"
                      cols="44">${pageScope.edit_question.description}</textarea>
        </div>

        <div class="grid-item"><label for="image_url">${question_image_url}:</label></div>
        <div class="grid-item">
            <input id="image_url"
                   class="classic"
                   type="url"
                   name="${Util.QUESTION_IMAGE_URL}"
                   value="${pageScope.edit_question.imageUrl}">
        </div>
    </div>

    <div class="grid-container-1">
        <div class="grid-item">
            <label for="options_number">${question_options_number}:</label>
        </div>
    </div>

    <div class="grid-container-2">
        <div class="grid-item">
            <input id="options_number"
                   class="classic"
                   type="number"
                   min="0" max="10"
                   step="1"

                   name="options_number"
                   value="0" onclick="addOptionFields()">
        </div>
        <div class="grid-item"><a class="classic" href="#" id="add_options" onclick="addOptionFields()">${add}</a></div>
    </div>

    <div class="grid-container-1" id="options"></div>

    <div class="grid-container-2">
        <div class="grid-item"><input class="classic" type="submit" value="${add_question}"></div>
        <div class="grid-item">
            <a class="classic" href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_ADD_SURVEY_PAGE_COMMAND}">
                ${complete}
            </a>
        </div>
    </div>
</form>
</body>
</html>