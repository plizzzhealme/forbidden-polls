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
                container.appendChild(document.createTextNode("Option " + (i + 1) + ":"));
                container.appendChild(document.createElement("br"));

                const input = document.createElement("input");
                input.type = "text";
                input.name = "option" + i;

                container.appendChild(input);
            }
        }
    </script>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>
<form action="${Util.CONTROLLER}">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.ADD_QUESTION_COMMAND}">

    <p>
        <label for="question">Question:</label><br/>
        <textarea id="question" name="${Util.QUESTION}" rows="2" cols="44"></textarea>
    </p>

    <p>
        <label for="description">Description:</label><br/>
        <textarea id="description" name="${Util.QUESTION_DESCRIPTION}" rows="7" cols="44"></textarea>
    </p>

    <p>
        <label for="image_url">Image url:</label><br/>
        <input id="image_url" type="url" name="${Util.QUESTION_IMAGE_URL}">
    </p>

    <p>
        <label for="options_number">Options number:</label><br/>
        <input type="number"
               min="0" max="10"
               step="1"
               id="options_number"
               name="options_number"
               value=""> <a href="#" id="add_options" onclick="addOptionFields()">Add options</a>
    </p>


    <div id="options"></div>

    <p>
        <input type="submit" value="add question">
    </p>

</form>

<p><a href="${Util.CONTROLLER}?${Util.COMMAND}=${Util.ADD_SURVEY_COMMAND}">
    create survey
</a></p>


</body>
</html>