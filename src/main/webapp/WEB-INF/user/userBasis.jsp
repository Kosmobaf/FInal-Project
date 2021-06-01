<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>
    Головна сторінка користувача <br/>
</h1>
<h2>
    Баланс коштів: ${sessionScope.cash} грн <br/>
</h2>
<h3>
    <c:choose>
        <c:when test="${sessionScope.userOrderList.size() > 0}">
            Замовлені послуги:<br/>
        </c:when>
        <c:otherwise>
            Послуги ще не замовлені
        </c:otherwise>
    </c:choose>

    <c:forEach var="order" items="${sessionScope.userOrderList}">
        <ul>
            <li>Послуга - *<c:out value="${order.nameService}"/>*</li>
            <li>Тариф - *<c:out value="${order.nameTariff}"/>*</li>
            <li>Статус - *<c:out value="${order.status}"/>*</li>

            <c:set var="blocked" scope="session" value="blocked"/>

            <c:if test="${order.status.toString() == blocked}">
                <form action="${pageContext.request.contextPath}/activateTariff" method="post">
                    <input type="number" hidden name="idTariff" value="${order.tariffId}">
                    <input type="submit" value="Активувати">
                </form>
            </c:if>

            <form action="${pageContext.request.contextPath}/deleteTariff" method="post">
                <input type="number" hidden name="idOrder" value="${order.id}">
                <input type="submit" value="Видалити послугу">
            </form>
        </ul>
        <hr/>
    </c:forEach>
</h3>
<a href="${pageContext.request.contextPath}/getAllService ">Вибрати послугу</a><br/>
<a href="${pageContext.request.contextPath}/WEB-INF/user/addCash.jsp">Поповнити рахунок</a><br/>
<a href="${pageContext.request.contextPath}/getFileServices ">Скачати прайс лист на послуги</a><br/>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>