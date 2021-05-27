<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login in system</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <style>
        body { background: url(META-INF/3.jpg); }
    </style>
</head>
<body>

<h1>Вход в систему</h1><br/>

<form method="post" action="${pageContext.request.contextPath}/login">
    <div class="mb-3">
        <label for="InputEmail" class="form-label">Email address</label>
        <input type="email" name="login" id="InputEmail" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
        <label for="InputPassword" class="form-label">Password</label>
        <input type="password" name="password" id="InputPassword">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>


<br/>
<a href="${pageContext.request.contextPath}/logout">На головну</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>