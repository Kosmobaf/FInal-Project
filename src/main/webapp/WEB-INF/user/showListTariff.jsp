<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Список тарифів</h1>
<h2>
    <c:forEach var="order" items="${sessionScope.tariffList}">
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/addTariffWithService" method="post">
                    <input type="number" hidden name="idTariff" value="${order.id}">
                    <input type="submit" name="tariff" value="<c:out value="${order.nameTariff}"/>">
                </form>
            </li>
        </ul>
        <hr/>
    </c:forEach>
</h2>
<a href="${pageContext.request.contextPath}/userBasis "> На головну сторінку користувача</a>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>