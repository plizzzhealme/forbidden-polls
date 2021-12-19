<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authorization</title>
</head>
<body>
<h2>Welcome To Forbidden Polls</h2>

<p>Hi, username! Welcome to Forbidden Polls. Take a survey or do whatever you want</p>

<form action="controller">
    <input type="hidden" name="command" value="to_authorization_page">
    <input type="submit" value="authorization">
</form>

<form action="controller">
    <input type="hidden" name="command" value="to_registration_page">
    <input type="submit" value="registration">
</form>


</body>
</html>
