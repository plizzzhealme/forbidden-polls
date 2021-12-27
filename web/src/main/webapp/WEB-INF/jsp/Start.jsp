<!DOCTYPE html>
<html lang="en">
<head>
    <title>Forbidden Polls</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<fieldset>
    <legend>Forbidden Polls:</legend>

    <p>Hi, username! Welcome to Forbidden Polls. Take a survey or do whatever you want</p>

    <form class="st">
        <input type="hidden" name="command" value="to_authorization_page"/>
        <input type='submit' value="Sign In"/>
    </form>
    <form class="st">
        <input type="hidden" name="command" value="to_registration_page"/>
        <input type='submit' value="Sign Up"/>
    </form>

</fieldset>
</body>
</html>