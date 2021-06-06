<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/directive/taglib.jspf" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1>Список тарифів</h1>

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
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Назва тарифу</th>
        <th scope="col">Вартість</th>
        <th scope="col">Вибрати</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="tariffListForService" type="java.util.List"--%>
    <c:forEach var="tariff" items="${tariffListForService}">
        <tr>
            <th scope="row"> <c:out value="${tariff.nameTariff}"/></th>
            <td><c:out value="${tariff.cost}"/> грн </td>
            <td>
                <form action="${pageContext.request.contextPath}/addTariffWithService" method="post">
                    <input type="number" hidden name="idTariff" value="${tariff.id}">
                    <input type="submit" value="Вибрати">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/userBasis "> На головну сторінку користувача</a><br/>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>