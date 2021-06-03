<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<c:choose>
    <c:when test="${sessionScope.userOrderList.size() > 0}">
        Замовлені послуги<br/>
    </c:when>
    <c:otherwise>
        Послуги ще не замовлені
    </c:otherwise>
</c:choose>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Послуга</th>
        <th scope="col">Тариф</th>
        <th scope="col">Статус</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tariff" items="${sessionScope.userOrderList}">
        <tr>
            <th scope="row"><c:out value="${tariff.nameService}"/></th>
            <th><c:out value="${tariff.nameTariff}"/></th>
            <th><c:out value="${tariff.status}"/></th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>

Cтатус користувача - ${sessionScope.statusUser}
<br/>

<c:choose>
    <c:when test="${sessionScope.statusUser == 'blocked'}">
        <form action="${pageContext.request.contextPath}/changeStatusUser" method="post">
            <input type="number" hidden name="idUser" value="${sessionScope.idUser}">
            <input type="submit" value="Активувати">
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/changeStatusUser" method="post">
            <input type="number" hidden name="idUser" value="${sessionScope.idUser}">
            <input type="submit" value="Заблокувати">
        </form>
    </c:otherwise>
</c:choose>
<br/>

<p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись на головну сторінку</a></p>
<a href="${pageContext.request.contextPath}/logout">Вихід</a>

</body>
</html>