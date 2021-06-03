<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

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


<p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись на головну сторінку</a></p>
<a href="${pageContext.request.contextPath}/logout">Вихід</a>

</body>
</html>