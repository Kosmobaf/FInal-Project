<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
</head>
<body>
<h2>Поповнення рахунку</h2>
<h3>
    <form action="${pageContext.request.contextPath}/addCash" method="post">
        <label> Введіть суму для поповнення рахунку
            <input type="number" min="0.01" step="0.01" name="inputCash">
        </label>
        <input type="submit" value="Поповнити"/>
    </form>
</h3>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>
