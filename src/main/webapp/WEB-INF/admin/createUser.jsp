<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/createUser">
    <label> Логін
        <input type="text" name="login">
    </label>
    <label> Пароль
        <input type="password" name="password">
    </label>
    <input class="button" type="submit" value="Створити">
</form>


<p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись на головну сторінку</a></p>
<a href="${pageContext.request.contextPath}/logout">Вихід</a>

</body>
</html>