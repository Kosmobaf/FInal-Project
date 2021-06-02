<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>
    <form method="post" action="${pageContext.request.contextPath}/addTariff">
        <label> Id сервісу
            <input type="text" name="id_service">
        </label>
        <label> Назва тарифу
            <input type="text" name="nameTariff">
        </label>
        <label> Вартість
            <input type="number" min="0.01" step="0.01" name="cost">
        </label>
        <input class="button" type="submit" value="Створити">
    </form>
</h1>
<h3>
    <p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись назад</a></p>
</h3>
</body>
</html>