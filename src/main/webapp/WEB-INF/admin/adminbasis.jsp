<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN THE BASIS</title>
</head>
<body>

<h1>Hello ADMIN!</h1>

<p> <a href="${pageContext.request.contextPath}/getAllUsers">Get all Users</a></p>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>