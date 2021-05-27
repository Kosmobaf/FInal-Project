<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h1>
    <form method="post" action="${pageContext.request.contextPath}/createUser">
        <input type="text" name="login">
        <input type="password" name="password">
        <input class="button" type="submit" value="Створити">
    </form>
</h1>
<h3>
    <p><a href="${pageContext.request.contextPath}/getAllUsers">Повернутись назад</a></p>
</h3>
</body>
</html>
