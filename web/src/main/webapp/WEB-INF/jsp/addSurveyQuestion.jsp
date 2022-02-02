<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
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
                container.appendChild(document.createElement("p"));
                container.appendChild(document.createTextNode("${question_option} " + (i + 1) + ":"));
                container.appendChild(document.createElement("br"));

                const input = document.createElement("input");
                input.type = "text";
                input.name = "option";

                container.appendChild(input);
            }
        }
    </script>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>
<c:set var="edit_question" value="${sessionScope.new_survey.questions[sessionScope.edit_index]}"/>

<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_QUESTION_COMMAND}">

    <p>
        <label for="question">${question}:</label><br/>
        <textarea id="question"
                  name="${Util.QUESTION_BODY}"
                  rows="2"
                  cols="44">${edit_question.body}</textarea>
    </p>

    <p>
        <label for="description">${question_description}:</label><br/>
        <textarea id="description"
                  name="${Util.QUESTION_DESCRIPTION}"
                  rows="7"
                  cols="44">${edit_question.description}</textarea>
    </p>

    <p>
        <label for="image_url">${question_image_url}:</label><br/>
        <input id="image_url" type="url" name="${Util.QUESTION_IMAGE_URL}" value="${edit_question.imageUrl}">
    </p>

    <p>
        <label for="options_number">${question_options_number}:</label><br/>
        <input type="number"
               min="0" max="10"
               step="1"
               id="options_number"
               name="options_number"
               value="0"> <a href="#" id="add_options" onclick="addOptionFields()">${add}</a>
    </p>


    <div id="options"></div>

    <p>
        <input type="submit" value="${add_question}">
    </p>

</form>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.TO_EDIT_SURVEY_PAGE_COMMAND}">
    ${complete}
</a></p>


</body>
</html>