<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN THE BASIS</title>

</head>
<body>

<h1>Привіт Адмін!</h1>

<p> <a href="${pageContext.request.contextPath}/getAllUser">Подивитись всіх користувачів</a></p>
<p> <a href="${pageContext.request.contextPath}/showAllTariff">Подивитись всі тарифи</a></p>
<a href="${pageContext.request.contextPath}/logout">Вихід</a>
</body>
</html>