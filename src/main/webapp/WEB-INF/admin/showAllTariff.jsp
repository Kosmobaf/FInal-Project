<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h1>Список тарифів</h1>
<div class="container">

    <div class="row">
        <div class="col-8"></div>
        <div class="col-4">
            <form action="${pageContext.request.contextPath}/showAllTariff" method="get">
                <select name="sortCommand" class="form-select">
                    <option selected>Сортування</option>
                    <option value="sortByName">По назві</option>
                    <option value="sortByNameReverse">По назві у зворотньому порядку</option>
                    <option value="sortByCoast">По ціні</option>
                </select>
                <button type="submit" class="btn btn-primary mb-3">Сортувати</button>
            </form>
        </div>
    </div>
</div>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Назва тарифу</th>
        <th scope="col">Id сервіса</th>
        <th scope="col">Вартість</th>
        <th scope="col">Видалити тариф</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tariff" items="${sessionScope.tariffList}">
        <tr>
            <th scope="row"><c:out value="${tariff.id}"/></th>
            <td><c:out value="${tariff.nameTariff}"/></td>
            <td><c:out value="${tariff.idServices}"/></td>
            <td><c:out value="${tariff.cost}"/> грн</td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteTariff" method="post">
                    <input type="number" hidden name="idTariff" value="${tariff.id}">
                    <input type="submit" value="Видалити тариф">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<br/>

<form action="${pageContext.request.contextPath}/addTariff" method="post">
    <input type="submit" value="Добавити тариф">
</form>
<br/>
<p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись на головну сторінку</a></p>
<a href="${pageContext.request.contextPath}/logout">Вихід</a>

</body>
</html>