<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h1>
    <form method="post" action="${pageContext.request.contextPath}/createUser">
        <label> Логін
            <input type="text" name="login">
        </label>
        <label> Пароль
            <input type="password" name="password">
        </label>
        <input class="button" type="submit" value="Створити">
    </form>
</h1>
<h3>
    <p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись назад</a></p>
</h3>
</body>
</html>