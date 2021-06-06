<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/directive/taglib.jspf" %>
<html>
<head>
    <title>Login in system</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

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
<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>
</body>
</html>