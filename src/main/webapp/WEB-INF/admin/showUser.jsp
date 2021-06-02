<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h3>
    <c:choose>
        <c:when test="${sessionScope.userOrderList.size() > 0}">
            Замовлені послуги:<br/>
        </c:when>
        <c:otherwise>
            Послуги ще не замовлені
        </c:otherwise>
    </c:choose>

    <c:forEach var="tariff" items="${sessionScope.userOrderList}">
    <ul>
        <li>Послуга - *<c:out value="${tariff.nameService}"/>*</li>
        <li>Тариф - *<c:out value="${tariff.nameTariff}"/>*</li>
        <li>Статус - *<c:out value="${tariff.status}"/>*</li>
    </ul>
        <hr/>
    </c:forEach>
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
</h3>
<h3>
    <p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись назад</a></p>
</h3>
</body>
</html>