<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h5>
    <form action="${pageContext.request.contextPath}/showAllTariff" method="get">
        <select name="sortCommand" class="form-select" >
            <option selected>Сортування </option>
            <option value="sortByName">По назві</option>
            <option value="sortByNameReverse">По назві у зворотньому порядку</option>
            <option value="sortByCoast">По ціні</option>
        </select>
        <button type="submit" class="btn btn-primary mb-3">Сортувати</button>
    </form>

</h5>
<h6>

    <c:forEach var="tariff" items="${sessionScope.tariffList}">
        <ul>
            <li>Id тарифу - *<c:out value="${tariff.id}"/>*</li>
            <li>Назва тарифу - *<c:out value="${tariff.nameTariff}"/>*</li>
            <li>Id сервіса - *<c:out value="${tariff.idServices}"/>*</li>
            <li>Вартість - *<c:out value="${tariff.cost}"/>*</li>
        </ul>

        <form action="${pageContext.request.contextPath}/changeTariff" method="post">
            <input type="number" hidden name="idTariff" value="${tariff.id}">
            <input type="submit" value="Змінити тариф">
        </form>
        <form action="${pageContext.request.contextPath}/removeTariff" method="post">
            <input type="number" hidden name="idTariff" value="${tariff.id}">
            <input type="submit" value="Видалити тариф">
        </form>
        <hr/>
    </c:forEach>
    <br/>
    <br/>

    <form action="${pageContext.request.contextPath}/addTariff" method="post">
        <input type="submit" value="Добавити тариф">
    </form>
    <br/>
</h6>
<h3>
    <p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись назад</a></p>
</h3>
</body>
</html>