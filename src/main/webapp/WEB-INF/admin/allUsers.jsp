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
        <li>
            Id: <c:out value="${user.id}"/>
            Login: <c:out value="${user.login}"/>
            Password: <c:out value="${user.password}"/>
            TypeUser: <c:out value="${user.role.toString()}"/>
            Cash: <c:out value="${user.cash}"/>
        </li>
    </ul>
    <hr/>
</c:forEach>

<a href="${pageContext.request.contextPath}/createUser">Створити користувача</a>


<a href="${pageContext.request.contextPath}/logout">Вихід</a>
</body>
</html>