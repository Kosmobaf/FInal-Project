<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN THE BASIS</title>
</head>
<body>

<h1>List of Users </h1>
<c:forEach var="user" items="${requestScope.userList}">
    <ul>
        <li>
            Id: <c:out value="${user.id}"/>
            Login: <c:out value="${user.login}"/>
            Password: <c:out value="${user.password}"/>
            Cash: <c:out value="${user.cash}"/>
            Status: <c:out value="${user.status}"/>
        </li>
    </ul>
    <hr/>
</c:forEach>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>