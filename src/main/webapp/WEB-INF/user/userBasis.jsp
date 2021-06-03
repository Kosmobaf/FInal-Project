<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1>
    Головна сторінка користувача <br/>
</h1>
<br/>
<h2>
    Баланс коштів: ${sessionScope.cash} грн <br/>
</h2>
<br/>
<h3>
    <c:choose>
        <c:when test="${sessionScope.userOrderList.size() > 0}">
            Замовлені послуги:<br/>
        </c:when>
        <c:otherwise>
            Послуги ще не замовлені
        </c:otherwise>
    </c:choose>
    <br/>
</h3>
<br/>
<c:choose>
    <c:when test="${sessionScope.userOrderList.size() > 0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Послуга</th>
                <th scope="col">Тариф</th>
                <th scope="col">Статус</th>
                <th scope="col">Активувати тариф</th>
                <th scope="col">Видалити тариф</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tariff" items="${sessionScope.userOrderList}">
                <tr>
                    <th scope="row"><c:out value="${tariff.nameService}"/></th>
                    <th><c:out value="${tariff.nameTariff}"/></th>
                    <th><c:out value="${tariff.status}"/></th>
                    <th>
                        <c:set var="blocked" scope="session" value="blocked"/>
                        <c:if test="${tariff.status.toString() == blocked}">
                            <form action="${pageContext.request.contextPath}/activateTariff" method="post">
                                <input type="number" hidden name="idTariff" value="${tariff.tariffId}">
                                <input type="submit" value="Активувати">
                            </form>
                        </c:if>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/deleteTariffFromUser" method="post">
                            <input type="number" hidden name="idOrder" value="${tariff.id}">
                            <input type="submit" value="Видалити послугу">
                        </form>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/getAllService ">Вибрати послугу</a><br/>
<a href="${pageContext.request.contextPath}/addCash">Поповнити рахунок</a><br/>
<a href="${pageContext.request.contextPath}/getFileServices ">Скачати прайс лист на послуги</a><br/>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>