<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/directive/taglib.jspf" %>
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
    <%--@elvariable id="cash" type="java.math.BigDecimal"--%>
    Баланс коштів: ${cash} грн <br/>
</h2>
<br/>
<h3>
    <c:choose>
        <%--@elvariable id="userOrderList" type="java.util.List"--%>
        <c:when test="${userOrderList.size() > 0}">
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
    <%--@elvariable id="userOrderList" type="java.util.List"--%>
    <c:when test="${userOrderList.size() > 0}">
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
            <%--@elvariable id="userOrderList" type="java.util.List"--%>
            <c:forEach var="user_order" items="${userOrderList}">
                <tr>
                    <th scope="row"><c:out value="${user_order.nameService}"/></th>
                    <td><c:out value="${user_order.nameTariff}"/></td>
                    <td><c:out value="${user_order.status}"/></td>
                    <td>
                        <c:set var="blocked" scope="session" value="blocked"/>
                        <c:if test="${user_order.status.toString() == blocked}">
                            <form action="${pageContext.request.contextPath}/activateTariff" method="post">
                                <input type="number" hidden name="idTariff" value="${user_order.tariffId}">
                                <input type="submit" value="Оплатити">
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/deleteTariffFromUser" method="post">
                            <input type="number" hidden name="idOrder" value="${user_order.id}">
                            <input type="submit" value="Видалити послугу">
                        </form>
                    </td>
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