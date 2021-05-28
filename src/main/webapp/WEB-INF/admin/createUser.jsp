<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h1>
    <form method="post" action="${pageContext.request.contextPath}/createUser">
        <label>
            <input type="text" name="login" value="Логін">
        </label>
        <label>
            <input type="password" name="password" value="Пароль">
        </label>
        <input class="button" type="submit" value="Створити">
    </form>
</h1>
<h3>
    <p><a href="${pageContext.request.contextPath}/getAllUsers">Повернутись назад</a></p>
</h3>
</body>
</html>