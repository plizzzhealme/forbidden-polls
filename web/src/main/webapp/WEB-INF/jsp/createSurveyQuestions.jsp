<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../jspf/localization.jspf" %>
<!DOCTYPE>
<html xml:lang="eu">
<head>
    <title>${requestScope.survey.name}</title>

    <script type='text/javascript'>
        function addFields(){
            // Number of inputs to create
            var number = document.getElementById("options_number").value;
            // Container <div> where dynamic content will be placed
            var container = document.getElementById("container");
            // Clear previous contents of the container
            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            for (i=0;i<number;i++){
                // Append a node with a random text
                container.appendChild(document.createTextNode("Option " + (i+1)));
                container.appendChild(document.createElement("br"));
                // Create an <input> element, set its type and name attributes
                var input = document.createElement("input");
                input.type = "text";
                input.name = "option" + i;
                container.appendChild(input);
                // Append a line break
                container.appendChild(document.createElement("br"));
            }
        }

    </script>
</head>
<body>
<p>
    <%@include file="../jspf/header.jspf" %>
</p>
<form action="${Util.CONTROLLER}" method="post">
    <input type="hidden" name="${Util.COMMAND}" value="${Util.CREATE_SURVEY_HEADER_COMMAND}">

    <label for="question">Question:</label><br/>
    <input id="question" type="text" name="${Util.QUESTION}"><br/>

    <label for="options_number">Options number:</label><br/>
    <input type="number" min="0" max="10" step="1" id="options_number" name="options_number" value=""><br/>
    <a href="#" id="add_options" onclick="addFields()">Add</a>

    <div id="container"></div>
    <br/>
    <input type="submit" value="add">
</form>

<a href="controller?command=finish">add survey</a>


</body>
</html>