<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN THE BASIS</title>

</head>
<body>

<h1>Список користувачів</h1>
<c:forEach var="user" items="${sessionScope.userList}">
    <ul>
        <li> Id: <c:out value="${user.id}"/></li>
        <li> Login: <c:out value="${user.login}"/></li>
        <li> TypeUser: <c:out value="${user.role.toString()}"/></li>
        <li> Cash: <c:out value="${user.cash}"/></li>

    </ul>
    <form action="${pageContext.request.contextPath}/showUser" method="get">
        <input type="number" hidden name="idUser" value="${user.id}">
        <input type="submit" value="Переглянути деталі користувача">
    </form>
    <hr/>
</c:forEach>

<a href="${pageContext.request.contextPath}/createUser">Створити користувача</a>


<a href="${pageContext.request.contextPath}/logout">Вихід</a>
</body>
</html>