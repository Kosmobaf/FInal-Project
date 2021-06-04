<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1>Список тарифів</h1>

<%--
<div class="container">
    <div class="row">
        <div class="col-8"></div>
        <div class="col-4">
            <form action="${pageContext.request.contextPath}/getAllTariffList" method="get">
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
--%>

    <c:forEach var="tariff" items="${sessionScope.tariffListForService}">
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/addTariffWithService" method="post">
                    <input type="number" hidden name="idTariff" value="${tariff.id}">
                    <input type="submit" name="tariff" value="<c:out value="${tariff.nameTariff}"/>">
                </form>
            </li>
        </ul>
        <hr/>
    </c:forEach>

<a href="${pageContext.request.contextPath}/userBasis "> На головну сторінку користувача</a><br/>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>